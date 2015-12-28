package construction;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class LectureConstruction {
    protected ArrayList<String> ficBanque;
    protected ArrayList<Releve> releves;

    /**
     * 
     * @param fic 
     */
    public LectureConstruction(ArrayList<String> fic) {
	ficBanque = fic;
	releves = new ArrayList<Releve>();
    }


    /**
     * 
     */
    public ArrayList<Releve> lireModele() {
	Iterator<String> iterator = ficBanque.iterator();
	String ligneTmp = "";

	for(int i = 1; iterator.hasNext(); i++) {
	    try {
		ligneTmp = iterator.next();

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
		Releve aReleve = newReleve(i);
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
			aReleve.lireEnreg7(ligneTmp);
			ligneTmp = iterator.next();
		    }
		}

		// Enregistrement 8
		// la ligne est déjà lu, on ne refait pas next()
		aReleve.lireEnreg8(ligneTmp);
	    }
	    catch(Exception e) {
		System.out.println(e.getMessage() + "\n" + ligneTmp);
	    }
	}
	// Enregistrement 9 n'apporte pas d'informations pour les relevés
	
	return releves;
    }
    
    public abstract Releve newReleve(int i);
}