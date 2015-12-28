package construction.ca;
import construction.LectureConstruction;
import construction.Releve;
import java.util.ArrayList;
import java.util.Iterator;

public class CA_LectureConstruction extends LectureConstruction {
    
    /**
     * 
     * @param ficBanque 
     */
    public CA_LectureConstruction(ArrayList<String> ficBanque) {
	super(ficBanque);
    }
    

    /**
     * Pour CA, il y a un relevé par fichier
     */
    @Override
    public ArrayList<Releve> lireModele() {
	Iterator<String> iterator = ficBanque.iterator();
	String ligneTmp = null;
	
	for(int i = 1; iterator.hasNext(); i++) {
	    
	    // pas bon
	    //if(ligneTmp != null && ligneTmp.length() > 5) {
		//break;
	    //}
	    // pas bon
	    
	    // correction
	    
	    // correction
	    
	    Releve aReleve = newReleve(i);
	    releves.add(aReleve);

	    ligneTmp = iterator.next();

	    // numéro compte & message		    
	    aReleve.lireEnreg0(ligneTmp);
	    
	    // type compte
	    ligneTmp = iterator.next();
	    ((CA_Releve)aReleve).lireTypeCompte(ligneTmp);

	    // "GERE EN EURO" & ligne blanche, on s'en fiche
	    iterator.next();
	    iterator.next();

	    // titulaire du compte & adresse 1
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg3(ligneTmp);

	    // titulaire suite
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg3(ligneTmp);

	    // titulaire suite
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg3(ligneTmp);

	    // guichet & adresse 2
	    ligneTmp = iterator.next();
	    ((CA_Releve)aReleve).lireGuichet(ligneTmp);
	    aReleve.lireEnreg4(ligneTmp);

	    // adresse 2 suite
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg4(ligneTmp);

	    // adresse 3
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg4(ligneTmp);

	    // ligne 6 : date ancien solde
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg6(ligneTmp);

	    iterator.next();    // ligne composée de tiret
	    iterator.next();    // en-tête du tableau
	    iterator.next();    // formatage de fin d'en-tête

	    // ligne 6 : solde ancien solde
	    ligneTmp = iterator.next();
	    aReleve.lireEnreg6(ligneTmp);


	    // ligne 7
	    iterator.next();    // ligne blanche

	    // on s'arrête lorsqu'on rencontre la ligne annonçant le nouveau solde
	    while(!ligneTmp.substring(22, 29).equals("NOUVEAU")) {
		// on regarde si la ligne contient des infos ou s'ils s'agit d'autre chose
		ligneTmp = iterator.next();	// ligne contenant les infos

		// les mouvements arrêtent, on regarde ce qu'il se passe
		if(ligneTmp.endsWith("!________________!") ||
			ligneTmp.endsWith("|________________|")) {
		    ligneTmp = iterator.next();	// "A REPORTER" ou "NOUVEAU SOLDE ..."

		    // fin d'une page, on regarde la suivante
		    if(ligneTmp.substring(22, 32).equals("A REPORTER")) {
			iterator.next();	// fin du tableau
			iterator.next();	// page: x
			iterator.next();	// No COMPTE
			iterator.next();	// type compte
			iterator.next();	// GERE EN EURO
			iterator.next();	// formatage début en-tête
			iterator.next();	// en-tête
			iterator.next();	// formatage fin en-tête
			iterator.next();	// REPORT ...
			iterator.next();	// ligne blanche
			// on continue à lire les mouvements, et ouais mon pote
		    }

		    // else : ligneTmp = annonce du nouveau solde, on arrête dans la condition du while
		}
		else {
		    aReleve.lireEnreg7(ligneTmp);
		    iterator.next();	// ligne blanche après le libellé
		}
	    }

	    // Enregistrement 8
	    // la ligne est déjà lue	    
	    aReleve.lireEnreg8(ligneTmp);
	    
	    // on lit la fin du tableau
	    iterator.next();
	    iterator.next();
	}
	return releves;
    }
    
    /**
     * 
     * @param i
     * @return 
     */
    @Override
    public CA_Releve newReleve(int i) {
	return new CA_Releve(i);
    }
}
