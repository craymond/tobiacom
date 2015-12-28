package miseEnForme.cl;
import construction.ExtraitCompte;
import construction.Releve;
import construction.cl.CL_ExtraitCompte;
import construction.cl.CL_Operation;
import construction.cl.CL_Releve;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.InfoCentre;
import miseEnForme.MEF_Outils;
import miseEnForme.MEF_PliageCL;
import miseEnForme.MiseEnForme;

public class CL_MiseEnForme extends MiseEnForme {
    public static final int FIN_EUR = 24;   // fin de la partie EUR pour les montants EUR
    public static final int FIN_FRF = 39;   // fin de la partie FRF pour les montants FRF
    
    private static Pattern pLigne = Pattern.compile("(" + MEF_Outils.RC + ")");
    
    private MEF_PliageCL pliageCL;

    /**
     * 
     */
    public CL_MiseEnForme() {
	super();
    }
    
    /**
     * 
     * @param releves
     * @return 
     */
    @Override
    public void miseEnForme(ArrayList<Releve> releves) {
	this.releves = releves;

	Iterator<Releve> iterator = this.getReleves().iterator();
	CL_Releve aReleveCL = null;
	CL_ExtraitCompte aExtrait;
	pliageCL = new MEF_PliageCL();
	
	// le relevé commence toujours par un saut de ligne
	appendTR(MEF_Outils.SAUT_PAGE);
	
	while(iterator.hasNext()) {
	    aReleveCL = (CL_Releve)iterator.next();
	    aExtrait  = (CL_ExtraitCompte)aReleveCL.getExtraitCompte();

	    // pliageCL de la page
	    pliageCL.init(aReleveCL.getId(), aReleveCL.getTypeExtrait(), aReleveCL.getSuiteReleve());
	    
	    entete(aReleveCL);			    // mise en forme de l'en-tête
	    etatCompteAncien(aReleveCL, aExtrait);  // mise en forme de l'ancien solde
	    mouvements(aReleveCL, aExtrait);	    // mise en forme des mouvements
	    etatCompteNouveau(aReleveCL, aExtrait); // mise en forme du nouveau solde
	    

	    // pour le pliageCL si la page fait moins de 15 lignes, on ajoute
	    // des lignes blanches et la ligne de pliageCL
	    if(pliageCL.getCpt() >= 2 && pliageCL.getCpt() < 15) {
		while(pliageCL.getCpt() < 15) {
		    ficTranscrit.append(" " + MEF_Outils.RC);
		    pliageCL.addCpt();
		}
		appendTR(pliageCL.getPliage());
		appendTR(MEF_Outils.SAUT_PAGE);
	    }
	    else if(pliageCL.getCpt() == 30) {
		pliageCL.addCpt();
		appendTR(MEF_Outils.SAUT_PAGE);
	    }
	    else if(pliageCL.getCpt() == 1) {
		// on ne fait rien
	    }
	    else
		appendTR(MEF_Outils.SAUT_PAGE);
	    
	    // génération de l'étiquette
	    // On ne fait pas plusieurs étiquettes de la même personne, cf => pas pour les types carte
	    if(aReleveCL.getTypeExtrait().equals("0")) {		
		etiquette(aReleveCL);
	    }
	}
    }
    
    /**
     * 
     * @param aReleve 
     */
    @Override
    public void entete(Releve aReleve) {
	String numPage    = "(" + pliageCL.getNumPage();
	String nomGuichet = MEF_Outils.libelle(aReleve.getNomGuichet());
	String titulaire  = MEF_Outils.libelle(aReleve.getTitulaire().trim());
	String numCompte  = MEF_Outils.marqueAntoineCompte(aReleve.getNumCompte());
	String message    = MEF_Outils.cesureRecursive(InfoCentre.getMessageMEF(), 0, 0, false);
	String date       = "";
	String libelleCompte = "";
	String numCarte;

	// on calcule le nombre de ligne du message
	int nLignes = 0;
	if(message.length() > 0) {
	    Matcher mLigne = pLigne.matcher(message);
		
	    // on incrémente le nombre de lignes à chaque fois qu'on trouve un RC
	    while(mLigne.find()) {
	        nLignes++;
	    }
	}
		
	
	// type compte
	if(((CL_Releve)aReleve).getTypeExtrait().equals("0")) {
	    numCarte = ((CL_Releve)aReleve).getNumCarte();
	    numCarte = String.valueOf(Integer.parseInt(numCarte));
	    date     = MEF_Outils.date(aReleve.getExtraitCompte().getDateNouveauSolde());
	}
	// type carte
	else {
	    numCarte      = ((CL_Releve)aReleve).getNumCarte();
	    numCarte      = numCarte.substring(numCarte.length() - 6);
	    libelleCompte = MEF_Outils.libelle(((CL_Releve)aReleve).getLibelleCompte());
	}

	
	// mise en forme
	if(((CL_Releve)aReleve).getTypeExtrait().equals("0")) {
	    appendTR("#CR/DIT #LYONNAIS" + MEF_Outils.espace(17 + numPage.length(), ' ') + numPage + MEF_Outils.RC);
	    appendTR(nomGuichet + MEF_Outils.RC);
	    appendTR("RELEV/ DE COMPTE N'O (" + numCarte + " AU " + date + MEF_Outils.RC);
	    appendTR(titulaire + MEF_Outils.RC);
	    appendTR("BANQUE: (" + aReleve.getCodeEtablissement() + " AGENCE: (" + aReleve.getCodeGuichet() + MEF_Outils.RC);
	    appendTR("N'O DE COMPTE: " + numCompte + "CL/: (" + ((CL_Releve)aReleve).getCleRIB() + MEF_Outils.RC);
	    if(!message.isEmpty()) {
		appendTR(MEF_Outils.RC);
		appendTR(message + MEF_Outils.RC);
		pliageCL.addCpt(nLignes);
	    }
	    appendTR(MEF_Outils.RC);
	}
	else {
	    appendTR("#CR/DIT #LYONNAIS" + MEF_Outils.espace(17 + numPage.length(), ' ') + numPage + MEF_Outils.RC);
	    appendTR(libelleCompte + MEF_Outils.RC);
	    appendTR(titulaire + MEF_Outils.RC);
	    appendTR("AGENCE: (" + aReleve.getCodeGuichet() + MEF_Outils.RC);
	    appendTR("COMPTE: " + numCompte + MEF_Outils.RC);
	    appendTR(":::::::::::::!::::::::::.::::::::::::::" + MEF_Outils.RC);
	    appendTR("             L MONTANT  $ CONTRE-VALEUR" + MEF_Outils.RC);
	    appendTR("             L EN EUROS $   EN FRANCS  " + MEF_Outils.RC);
	    appendTR(":::::::::::::H::::::::::J::::::::::::::" + MEF_Outils.RC);
	    appendTR("CARTE N'O (" + numCarte + MEF_Outils.RC);
	    appendTR(titulaire + MEF_Outils.RC);
	}
    }
    
    /**
     * 
     * @param aReleve
     * @param ae 
     */
    @Override
    public void etatCompteAncien(Releve aReleve, ExtraitCompte ae) {
	// type courant
	if(((CL_Releve)aReleve).getTypeExtrait().equals("0")) {
	    String ancienEUR = MEF_Outils.montant(ae.getMontantAncienSoldeEUR(), ae.getSigneAncienSolde());
	    String ancienFRF = MEF_Outils.montant(ae.getMontantAncienSoldeFRF(), ae.getSigneAncienSolde());
	    // séparation EUR - FRF (-2 pour les espaces avant et après la sépation)
	    int e2 = FIN_FRF - ancienFRF.length() - FIN_EUR - 2;
	    int e1 = FIN_FRF - ancienFRF.length() - 1 - e2 - 1 - ancienEUR.length();
	
	    appendTR("---------------------------------------" + MEF_Outils.RC);
	    appendTR(" DATE L     MONTANT     $ CONTRE-VALEUR" + MEF_Outils.RC);
	    appendTR("      L     EN EUROS    $   EN FRANCS  " + MEF_Outils.RC);
	    if(aReleve.getMessage().isEmpty())
		appendTR("------V-----------------0--------------" + MEF_Outils.RC);
	    appendTR("  ANCIEN SOLDE:" + MEF_Outils.RC);
	    appendTR(MEF_Outils.genChar(e1, ' ') + ancienEUR + " " + MEF_Outils.genChar(e2, '\'') + " " + ancienFRF + MEF_Outils.RC);
	}
	// type carte
	else {
	    if(ae.getMontantNouveauSoldeEUR() != null)
	    {
	    String sousTotal = MEF_Outils.montant(ae.getMontantNouveauSoldeEUR(), ae.getSigneNouveauSolde());
	    int e1 = FIN_EUR - sousTotal.length() - 11 - 1;	// 11 = "SOUS-TOTAL:"
	    
	    appendTR("SOUS-TOTAL: " + MEF_Outils.genChar(e1, ' ') + sousTotal + MEF_Outils.RC);
	    }
	}
    }

     /**
     * 
     * @param aReleve
     * @param ae 
     */
    @Override
    public void mouvements(Releve aReleve, ExtraitCompte ae) {
	Iterator<CL_Operation> iterator = ((CL_ExtraitCompte)ae).getOperationsCL().iterator();
	String libelle;
	String date;
	String montantEUR;
	String montantFRF;
	String separation;
	
	while(iterator.hasNext()) {
	    CL_Operation aOperation = iterator.next();

	    libelle = MEF_Outils.cesureRecursive(aOperation.getLibelle(), 2, 2, true);
	    montantEUR = MEF_Outils.montant(aOperation.getMontant(), aOperation.getSigne());
	    montantFRF = MEF_Outils.montant(aOperation.getMontantFRF(), aOperation.getSigne());

	    int e2 = FIN_FRF - montantFRF.length() - FIN_EUR - 2;
	    int e1 = FIN_FRF - montantFRF.length() - 1 - e2 - 1 - montantEUR.length() - 1 - 5 - 1; // 5 = date

	    if(((CL_Releve)aReleve).getTypeExtrait().equals("0")) {
		date = MEF_Outils.date(aOperation.getDate());
		separation = MEF_Outils.genChar(e1, '\'');
	    }
	    else {
		date = "     ";
		separation = MEF_Outils.genChar(e1, ' ');
	    }
	    

	    // on calcule le nombre de lignes que prend libellé + montant
	    // par défault: 1 ligne libellé + 1 ligne montant
	    int nLignes = 2;
	    
	    // le libellé est coupé
	    if(libelle.contains(MEF_Outils.RC)) {
		Matcher mLigne = pLigne.matcher(libelle);
		
		// on incrémente le nombre de lignes à chaque fois qu'on trouve un RC
		while(mLigne.find()) {
		    nLignes++;
		}
	    }


	    // on a la place pour écrire
	    if((pliageCL.getCpt() > 15 && 31 - pliageCL.getCpt() >= nLignes) ||
	       (		   	  15 - pliageCL.getCpt() >= nLignes)) {
		// on ajoute au compteur le nombre de lignes ajoutées
		appendTR("  " + libelle +  MEF_Outils.RC, false);
		pliageCL.addCpt(nLignes - 1);	// - 1, car on part de 2
		appendTR(date + " " + separation + " " + montantEUR + " " + MEF_Outils.genChar(e2, '\'') + " "
			                               + montantFRF + MEF_Outils.RC);
	    }
	    // on n'a pas la place, on coupe
	    else {		
		// pliage
		if(pliageCL.getCpt() <= 15) {
		    // calcule du nombre de lignes à sauter
		    for(int i = pliageCL.getCpt(); i < 15; i++) {
			appendTR(MEF_Outils.RC);
		    }
		}
		// saut de page
		else {
		    // calcule du nombre de lignes à sauter
		    for(int i = pliageCL.getCpt(); i <= 30; i++) {
			appendTR(MEF_Outils.RC);
		    }
		}
		
		// on ajoute au compteur le nombre de lignes ajoutées
		appendTR("  " + libelle +  MEF_Outils.RC, false);
		pliageCL.addCpt(nLignes - 1);	// - 1, car on part de 2
		appendTR(date + " " + separation + " " + montantEUR + " " + MEF_Outils.genChar(e2, '\'') + " "
						       + montantFRF + MEF_Outils.RC);
	    }
	}
    }
	
	
	
	
    
    
    /**
     * 
     * @param aReleve 
     */
    @Override
    public void etatCompteNouveau(Releve aReleve, ExtraitCompte ae) {	
	
	if(ae.getMontantNouveauSoldeEUR() != null) {
	    String nouveauEUR = MEF_Outils.montant(ae.getMontantNouveauSoldeEUR(), ae.getSigneNouveauSolde());
	    
	    if(((CL_Releve)aReleve).getTypeExtrait().equals("0")) {
		String nouveauFRF = MEF_Outils.montant(ae.getMontantNouveauSoldeFRF(), ae.getSigneNouveauSolde());
		int e2 = FIN_FRF - nouveauFRF.length() - FIN_EUR - 2;
		int e1 = FIN_FRF - nouveauFRF.length() - 1 - e2 - 1 - nouveauEUR.length();
	
		appendTR("  SOLDE EN EUROS:" + MEF_Outils.RC);
		appendTR(MEF_Outils.genChar(e1, ' ') + nouveauEUR + " " + MEF_Outils.genChar(e2, '\'') + " " + nouveauFRF + MEF_Outils.RC);
	    }
	    else {
		String dateNouveau = MEF_Outils.date(ae.getDateNouveauSolde());
		int e1 = FIN_EUR - nouveauEUR.length() - 11;	// 11 = "LE XX'XX'XX"
	    
	    
		appendTR(MEF_Outils.RC);
		appendTR("MONTANT COMPTABILIS/" + MEF_Outils.RC);
		
		// false => car on ne veut de pliage car on est à la fin de la mise en forme
		if(pliageCL.getCpt() != 30)
		    appendTR("LE " + dateNouveau + MEF_Outils.genChar(e1, ' ') + nouveauEUR + MEF_Outils.RC);//, false);
		else
		    appendTR("LE " + dateNouveau + MEF_Outils.genChar(e1, ' ') + nouveauEUR + MEF_Outils.RC, false);
	    }
	}
    }
    

    /**
     * Ajout de contenupour le fichier transcrit
     * @param contenu 
     */
    @Override
    public void appendTR(String contenu) {
	//ficTranscrit.append(pliageCL.getCpt() + "|" + contenu);	
	ficTranscrit.append(contenu);	
	if(pliageCL.isPliage())
	    ficTranscrit.append(pliageCL.getPliage());
    }
    
    /**
     * 
     * @param contenu
     * @param pliage 
     */
    public void appendTR(String contenu, boolean pliage) {
	if(!pliage)
	    ficTranscrit.append(contenu);
	else
	    appendTR(contenu);
    }
}