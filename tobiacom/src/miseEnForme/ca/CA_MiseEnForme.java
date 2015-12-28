package miseEnForme.ca;

import construction.ExtraitCompte;
import construction.Operation;
import construction.Releve;
import java.util.ArrayList;
import java.util.Iterator;
import main.InfoCentre;
import miseEnForme.MEF_Outils;
import miseEnForme.MEF_Pliage;
import miseEnForme.MiseEnForme;

public class CA_MiseEnForme extends MiseEnForme {
    
    public CA_MiseEnForme() {
	super();
    }
    
    @Override
    public void miseEnForme(ArrayList<Releve> releves) {
	this.releves = releves;
	
	Iterator<Releve> iterator = getReleves().iterator();
	Releve aReleve;
	ExtraitCompte aExtrait;
	pliage = new MEF_Pliage();
	
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
    
    
    @Override
    public void entete(Releve aReleve) {
	String message   = InfoCentre.getMessageMEF();
	String guichet   = "BG: " + MEF_Outils.libelle(aReleve.getNomGuichet().trim());
	String compte    = "N'O DE COMPTE: " + MEF_Outils.marqueAntoine(aReleve.getNumCompte());
	String typeCompte = MEF_Outils.libelle(aReleve.getTypeCompte().trim());
	String titulaire = MEF_Outils.libelle(aReleve.getTitulaire().trim(), false).trim();
	String titulaire2 = "";

	if(titulaire.length() > 39) {
	    // on cherche le dernier où on peut couper
	    int iCesure = 0;
	    for(int i = 39; i >= 0; i--) {
		if(titulaire.charAt(i) == ' ') {
		    iCesure = i;
		    break;
		}
	    }

	    //StringBuilder foo = new StringBuilder(titulaire);

	    //foo.replace(iCesure, iCesure, MEF_Outils.RC);
	    
	    titulaire2 = titulaire.substring(iCesure).trim();
	    titulaire  = titulaire.substring(0, iCesure).trim();
	    
	}
	
	appendTR("           #CR/DIT #AGRICOLE           " + MEF_Outils.RC);
	appendTR("                                       " + MEF_Outils.RC);
	appendTR(guichet + MEF_Outils.RC);
	appendTR(compte + MEF_Outils.RC);
	appendTR(typeCompte + MEF_Outils.RC);
	appendTR(titulaire + MEF_Outils.RC);
	if(!titulaire2.isEmpty())
	    appendTR(titulaire2 + MEF_Outils.RC);
	appendTR("                                       " + MEF_Outils.RC);
	//appendTR(message + MEF_Outils.RC);	// RELEVE D OPERATIONS EN EUROS DU ... AU ...
    }
    
    @Override
    public void etatCompteAncien(Releve aReleve, ExtraitCompte ae) {
	String dateAncien = " SOLDE AU " + MEF_Outils.date(ae.getDateAncienSolde());
	String ancienEUR  = MEF_Outils.montantCA(ae.getMontantAncienSoldeEUR(), ae.getSigneAncienSolde());
	
	appendTR("CCCCCCPCCCCCCCCCCCCCCCCCCCPCCCCCCCCCCCC" + MEF_Outils.RC);
	appendTR(" DATE L     OP/RATION     L  MONTANT   " + MEF_Outils.RC);
	appendTR("------V-------------------V------------" + MEF_Outils.RC);
	appendTR(dateAncien + MEF_Outils.espace(dateAncien.length() + ancienEUR.length(), ' ') + ancienEUR + MEF_Outils.RC);
    }
    
    @Override
    public void mouvements(Releve aReleve, ExtraitCompte ae) {
	Iterator<Operation> iterator = ae.getOperations().iterator();
	String date;
	String libelle;
	String montant;
	
	while(iterator.hasNext()) {
	    Operation aOperation = iterator.next();
	    
	    date    = MEF_Outils.date(aOperation.getDate());
	    montant = MEF_Outils.montantCA(aOperation.getMontant(), aOperation.getSigne());
	    
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
		    
		    // on tronque le libellé pour ne pas dépasser 2 lignes de libellés
		    if(placeOccupee > 39) {
			libelle = libelle.substring(0, libelle.length() - (placeOccupee - 39));
		    }
		}
	    }
	    // maintenant qu'on a formaté le libellé, on recalcule la place occupée pour la séparation
	    else
		placeOccupee += libelle.length();

	    appendTR(date + " " + libelle + " " + MEF_Outils.espace(placeOccupee, '\'') + " " + montant + MEF_Outils.RC);
	}
    }
    
    @Override
    public void etatCompteNouveau(Releve aReleve, ExtraitCompte ae) {
	String dateNouveau = " SOLDE AU " + MEF_Outils.date(ae.getDateNouveauSolde());
	String nouveauEUR  = MEF_Outils.montantCA(ae.getMontantNouveauSoldeEUR(), ae.getSigneNouveauSolde());
	
	appendTR(dateNouveau + MEF_Outils.espace(dateNouveau.length() + nouveauEUR.length(), ' ') + nouveauEUR + MEF_Outils.RC);
    }
}
