package miseEnForme;

import construction.ExtraitCompte;
import construction.Releve;
import java.util.ArrayList;
import main.InfoCentre;

public abstract class MiseEnForme {
    protected ArrayList<Releve> releves;
    protected StringBuilder ficTranscrit;
    protected StringBuilder ficEtiquette;
    protected MEF_Pliage pliage;
    
    
    public MiseEnForme() {
	ficTranscrit = new StringBuilder();
	ficEtiquette = new StringBuilder();
    }
    
    public abstract void miseEnForme(ArrayList<Releve> releves);
    public abstract void entete(Releve aReleve);
    public abstract void etatCompteAncien(Releve aReleve, ExtraitCompte aExtrait);
    public abstract void mouvements(Releve aReleve, ExtraitCompte aExtrait);
    public abstract void etatCompteNouveau(Releve aReleve, ExtraitCompte aExtrait);

    /**
     * Une étiquette fait 9 lignes et 39 caractères par ligne
     * @param aReleve 
     */
    public void etiquette(Releve aReleve) {
	StringBuilder aEtiquette = new StringBuilder();
	
	int i = 0;
	for(String ligneEti : aReleve.getAdresse()) {
	    
	    if(ligneEti.length() > 0) {		
		//
		if(i == 0) {
		    int espace = ("" + aReleve.getId()).length();
		    espace += ligneEti.length() - 1;

		    aEtiquette.append(MEF_Outils.libelle(ligneEti, false));
		    aEtiquette.append(MEF_Outils.espace(espace, ' '));
		    aEtiquette.append(aReleve.getId());
		    aEtiquette.append(MEF_Outils.RC);
		}
		else {
		    aEtiquette.append(MEF_Outils.libelle(ligneEti, false)).append(MEF_Outils.RC);
		}
		i++;
	    }
	}

	// on ajoute des lignes blanches pour arriver à 9 lignes
	while(i < 9) {
	    aEtiquette.append(MEF_Outils.RC);
	    i++;
	}

	// on ajoute l'étiquette au fichier étiquette
	// on l'ajoute dans l'ordre défini par InfoCentre.inverserEtiquette
	if(InfoCentre.getInverserEtiquette()) {
	    ficEtiquette.insert(0, aEtiquette);
	}
	else {
	    ficEtiquette.append(aEtiquette);
	}
    }
    
    /**
     * Ajout de contenupour le fichier transcrit
     * @param contenu 
     */
    public void appendTR(String contenu) {
	ficTranscrit.append(contenu);	
	if(pliage.isPliage())
	    ficTranscrit.append(pliage.getPliage());
    }
    
    /**
     * 
     * @param contenu 
     */
    public void appendETI(String contenu) {
	ficEtiquette.append(contenu);
    }

    /**
     * 
     * @return 
     */
    public String getFicTranscrit() {
	return ficTranscrit.toString();
    }


    /**
     * 
     * @return 
     */
    public String getFicEtiquette() {
	return ficEtiquette.toString();
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Releve> getReleves() {
	return releves;
    }
}
