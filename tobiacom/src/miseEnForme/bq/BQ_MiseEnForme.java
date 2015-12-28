package miseEnForme.bq;

import construction.ExtraitCompte;
import construction.Operation;
import construction.Releve;
import java.util.ArrayList;
import java.util.Iterator;
import miseEnForme.MEF_Outils;
import miseEnForme.MEF_Pliage;
import miseEnForme.MiseEnForme;
import main.InfoCentre;

public class BQ_MiseEnForme extends MiseEnForme {
    
    public BQ_MiseEnForme() {
	super();
    }
    
    
    /**
     * 
     * @return 
     */
    @Override
    public void miseEnForme(ArrayList<Releve> releves) {
	this.releves = releves;

	Iterator<Releve> iterator = getReleves().iterator();
	Releve aReleve = null;
	ExtraitCompte aExtrait;
	pliage = new MEF_Pliage();

	// le relevé commence toujours par un saut de ligne
	appendTR(MEF_Outils.SAUT_PAGE);
	
	while(iterator.hasNext()) {
	    aReleve = iterator.next();
	    aExtrait = aReleve.getExtraitCompte();

	    // pliage de la page
	    pliage.init(aReleve.getId());

	    // mise en forme des informations
	    entete(aReleve);			    // mise en forme de l'en-tête
	    etatCompteAncien(aReleve, aExtrait);    // mise en forme de l'ancien solde
	    mouvements(aReleve, aExtrait);	    // mise en forme des mouvements
	    etatCompteNouveau(aReleve, aExtrait);   // mise en forme du nouveau solde
	    

	    // pour le pliage si la page fait moins de 15 lignes, on ajoute
	    // des lignes blanches et la ligne de pliage
	    if(pliage.getCpt() >= 2 && pliage.getCpt() < 15) {
		while(pliage.getCpt() < 15) {
		    ficTranscrit.append(" " + MEF_Outils.RC);
		    pliage.addCpt();
		}
		appendTR(pliage.getPliage());
		appendTR(MEF_Outils.SAUT_PAGE);
	    }
	    else if(pliage.getCpt() == 30) {
		pliage.addCpt();
		appendTR(MEF_Outils.SAUT_PAGE);
	    }
	    else if(pliage.getCpt() == 1) {
		// on ne fait rien
	    }
	    else
		appendTR(MEF_Outils.SAUT_PAGE);

	    // génération de l'étiquette
	    etiquette(aReleve);
	}
    }
    
    
    
    /**
     * 
     * @param aReleve 
     */
    @Override
    public void entete(Releve aReleve) {
	String message   = InfoCentre.getMessageMEF();
	String agence    = "AGENCE: " + MEF_Outils.libelle(aReleve.getNomGuichet());
	String codeEtab  = "CE: " + aReleve.getCodeEtablissement();
	String guichet   = "CG: (" + aReleve.getCodeGuichet();
	String compte    = "COMPTE: " + MEF_Outils.marqueAntoineCompte(aReleve.getNumCompte());
	String titulaire = MEF_Outils.libelle(aReleve.getTitulaire().trim());
	
	// Message à centrer : int nPresent = (message.length) + (espaceApres)
	int nPresent = (message.length()) + ((MEF_Outils.LG_LIGNE - message.length()) / 2);
	
	appendTR(MEF_Outils.espace(nPresent, ' ') + message + MEF_Outils.RC);	
	appendTR("                                       " + MEF_Outils.RC);
	appendTR(agence + MEF_Outils.espace(codeEtab.length() + agence.length(), ' ') + codeEtab + MEF_Outils.RC);
	appendTR(guichet + " " + compte + MEF_Outils.RC);
	
	// compte courant
	if(aReleve.getExtraitCompte().getTypeCompte() == ExtraitCompte.COMPTE_COURANT) {
	    appendTR("COMPTE TENU EN EUROS" + MEF_Outils.RC);
	    appendTR("TAUX DE CONVERSION: (1 %E = (6,55957 #F" + MEF_Outils.RC);
	    appendTR(titulaire + MEF_Outils.RC);
	    appendTR("                                       " + MEF_Outils.RC);   
	}
	// compte chèque
	else {
	    appendTR(titulaire + MEF_Outils.RC);
	    appendTR(aReleve.getExtraitCompte().getLibelleCompte() + MEF_Outils.RC);
	}
    }
    
    
    /**
     * 
     * @param aReleve
     * @param ae 
     */
    @Override
    public void etatCompteAncien(Releve aReleve, ExtraitCompte ae) {
	String dateAncien = " SOLDE AU " + MEF_Outils.date(ae.getDateAncienSolde());
	String ancienEUR  = MEF_Outils.montant(ae.getMontantAncienSoldeEUR(), ae.getSigneAncienSolde());
	String ancienFRF;

	appendTR("CCCCCCPCCCCCCCCCCCCCCCCCCCPCCCCCCCCCCCC" + MEF_Outils.RC);
	appendTR(" DATE L     OP/RATION     L  MONTANT   " + MEF_Outils.RC);
	appendTR("------V-------------------V------------" + MEF_Outils.RC);
	appendTR(dateAncien + MEF_Outils.espace(dateAncien.length() + ancienEUR.length(), ' ') + ancienEUR + MEF_Outils.RC);
	// on n'affiche la contre-valeur que pour les comptes courants
	if(ae.getTypeCompte() == ExtraitCompte.COMPTE_COURANT) {
	    ancienFRF  = MEF_Outils.montant(ae.getMontantAncienSoldeFRF(), ae.getDateAncienSolde());
	    appendTR(" SOLDE EN FRANCS" + MEF_Outils.espace(16 + ancienFRF.length(), ' ') + ancienFRF + MEF_Outils.RC);
	}
    }
    
    
    /**
     * 
     * @param aReleve
     * @param ae 
     */
    @Override
    public void mouvements(Releve aReleve, ExtraitCompte ae) {
	Iterator<Operation> iterator = ae.getOperations().iterator();
	String date;
	String libelle;
	String montant;

	while(iterator.hasNext()) {
	    Operation aOperation = iterator.next();
	    

	    
	    date    = MEF_Outils.date(aOperation.getDate());
	    montant = MEF_Outils.montant(aOperation.getMontant(), aOperation.getSigne());
	    
	    // on calcule la place que prend ces deux infos (+3 pour les espaces entre les infos)
	    int placeOccupee = date.length() + montant.length() + 3;
	    libelle = MEF_Outils.cesure(aOperation.getLibelle(), placeOccupee, 6);


	    // le libellé a été coupée, on recalcule la séparation libellé-montant
	    if(libelle.contains(MEF_Outils.RC)) {

		if(pliage.isPliage()) {
		    int iRC = libelle.indexOf(MEF_Outils.RC);
		    
		    String debLib = libelle.substring(0, iRC);
		    String finLib = libelle.substring(iRC);
		    libelle = debLib + MEF_Outils.RC + pliage.getPliageMvt() + finLib;
		    
		    // la sépation commence à la seconde moitié du libellé 
		    // => on recalcule la place occupée
		    placeOccupee = finLib.length() + montant.length();
		}
		else {
		    // la sépation commence à la seconde moitié du libellé 
		    // => on recalcule la place occupée
		    String finLibelle  = libelle.substring(libelle.indexOf((MEF_Outils.RC)));
		    placeOccupee = finLibelle.length() + montant.length();
		}
	    }
	    // maintenant qu'on a formaté le libellé, on recalcule la place occupée pour la séparation
	    else
		placeOccupee += libelle.length();


	    appendTR(date + " " + libelle + " " + MEF_Outils.espace(placeOccupee, '\'') + " " + montant + MEF_Outils.RC);
	}
    }
    
    
    /**
     * 
     * @param aReleve 
     */
    @Override
    public void etatCompteNouveau(Releve aReleve, ExtraitCompte ae) {
	String dateNouveau = " SOLDE AU " + MEF_Outils.date(ae.getDateNouveauSolde());
	String nouveauEUR  = MEF_Outils.montant(ae.getMontantNouveauSoldeEUR(), ae.getSigneNouveauSolde());
	String nouveauFRF;
	
	appendTR(dateNouveau + MEF_Outils.espace(dateNouveau.length() + nouveauEUR.length(), ' ') + nouveauEUR + MEF_Outils.RC);
	// on n'affiche la contre-valeur que pour les comptes courants
	if(ae.getTypeCompte() == ExtraitCompte.COMPTE_COURANT) {
	    nouveauFRF  = MEF_Outils.montant(ae.getMontantNouveauSoldeFRF(), ae.getDateNouveauSolde());
	    appendTR(" SOLDE EN FRANCS" + MEF_Outils.espace(16 + nouveauFRF.length(), ' ') + nouveauFRF + MEF_Outils.RC);
	}
    }
}