package construction.cl;
import construction.LectureConstruction;
import construction.Releve;
import java.util.ArrayList;
import java.util.Iterator;
import structure.CL_Structure;

public class CL_LectureConstruction extends LectureConstruction {
    // quand un libellé est coupé, on sauvegarde les 2 parties dans un même String,
    // mais on insère un séparateur entre ces deux parties
    public static final String MARQUEUR_LIBELLE = "§";
    
    /**
     * 
     * @param fic 
     */
    public CL_LectureConstruction(ArrayList<String> fic) {
	super(fic);
    }
    
    
    // si c'est un type carte, on ne relit ni le numCompte?, ni l'adresse etc etc
    @Override
    public ArrayList<Releve> lireModele() {
	Iterator<String> iterator = ficBanque.iterator();
	String ligneTmp = "";
	int idReleve     = 0;
	int idRelevePrec = 1;		    // idReleve du relevé précédent
	String numCompte = "";		    // n° compte du relevé courant
	String numComptePrec = "";	    // n° compte du relevé précédent
	boolean aLire = true;		    // doit-on lire la ligne ?
	boolean libelleCoupe = false;	    // indique si un libellé est coupé
	String  libelleParties = "";	    // on sauvegarde la 1ère partie du libellé
	

	while(iterator.hasNext()) {
 	    // Pour CL, on ne doit pas lire la ligne dans le cas où le fichier
	    // passe de 7 à 0 ou 2
	    if(aLire)
		ligneTmp = iterator.next();
	    else {
		aLire = true;
	    }


	    // Enregistrement 9
	    if(ligneTmp.charAt(0) == '9')
		break;
	    
	    // Si le caractère est un SpaceChar, on doit être arriver à la fin
	    // Ça évite les problèmes de caractères de merde à la fin du fichier si
	    // l'enregistrement 9 est absent.
	    if(!Character.isLetterOrDigit(ligneTmp.charAt(0))) {
		break;
	    }


	    // Création d'un nouveau relevé
	    Releve aReleve = newReleve(idReleve + 1);	// +1 pour être à 1 à la 1ère itération
	    releves.add(aReleve);

	    // Enregistrement 0 (facultatif pour les n+1 relevés)
	    if(ligneTmp.charAt(0) == '0') {
		aReleve.lireEnreg0(ligneTmp);
		ligneTmp = iterator.next();
		
		// Enregistrement 1 (facultatif et n'existe pas sans l'enreg 0)
		if(ligneTmp.charAt(0) == '1') {
		    aReleve.lireEnreg1(ligneTmp);
		    ligneTmp = iterator.next();
		}
	    }
	    
	    // Enregistrement 2
	    // la ligne est déjà lu, on ne refait pas next()
	    aReleve.lireEnreg2(ligneTmp);
	    
	    // on garde le numéro du compte pour la mise en forme de CL
	    // si le numCompte est identique, on incrémente pas le numéro du client
	    // dans le .BAN
	    numCompte     = aReleve.getNumCompte();
	    // la suite du relevé
	    if(numCompte.equals(numComptePrec)) {
		aReleve.setId(idReleve);	    // on écrase idReleve
		((CL_Releve)aReleve).setSuiteReleve(true);
	    }
	    // un nouveau relevé
	    else {
		((CL_Releve)aReleve).setSuiteReleve(false);
		idReleve++;
	    }
	    numComptePrec = numCompte;

	    
	    // Enregistrement 3
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg3(ligneTmp);

	    // Enregistrement 4 (facultatif)
	    ligneTmp = iterator.next();
	    if(ligneTmp.charAt(0) == '4') {
		aReleve.lireEnreg4(ligneTmp);
		ligneTmp = iterator.next();
	    }
	    
	    // Enregistrement 5
	    // la ligne est déjà lu, on ne refait pas next()
	    aReleve.lireEnreg5(ligneTmp);

	    // Enregistrement 6
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg6(ligneTmp);
	    
	    // Enregistrements 7
	    ligneTmp = iterator.next();
	    if(ligneTmp.charAt(0) == '7') {
		while(ligneTmp.charAt(0) == '7') {
		    
		    // on sauvegarde les informations des mouvements coupés
		    // un libellé peut être coupé en >=2 fois
		    if(ligneTmp.charAt(CL_Structure.Mvt_SuiteLibelle_D) == '*') {
			libelleCoupe    = true;
			libelleParties += ligneTmp + MARQUEUR_LIBELLE;
		    }
		    // on ajoute la suite du libellé
		    else if(libelleCoupe) {
			libelleCoupe = false;
			libelleParties += ligneTmp;
			aReleve.lireEnreg7(libelleParties);
			libelleParties = "";
		    }
		    else {
			aReleve.lireEnreg7(ligneTmp);
		    }

		    ligneTmp = iterator.next();
		    
		    // 0 ou 2, cf début de la boucle
		    if(ligneTmp.charAt(0) == '0' || ligneTmp.charAt(0) == '2')
		        aLire = false;
		}
	    }
	    
	    // Enregistrement 8 (facultatif pour CL)
	    // la ligne est déjà lu, on ne refait pas next()
	    if(ligneTmp.charAt(0) == '8') {
		aReleve.lireEnreg8(ligneTmp);
	    }
	}
	
	// Enregistrement 9 n'apporte pas d'informations pour les relevés

	return releves;
    }
    
    /**
     * 
     * @param i
     * @return 
     */
    @Override
    public CL_Releve newReleve(int i) {
	return new CL_Releve(i);
    }
}