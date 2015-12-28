package verification.banque;
import exception.ExceptionLibelle;
import exception.ExceptionMvtMontant;
import java.util.ArrayList;
import java.util.Iterator;
import structure.BQ_Structure;
import verification.Verification;
import verification.VerificationOutils;

public class BQ_Verification extends Verification {
    
    /**
     * 
     */
    public BQ_Verification(ArrayList<String> fic) {
	super(fic);
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg0(String ligne) throws Exception {
	// Vérification du code établissement
	VerificationOutils.verifNumerique(ligne.substring(BQ_Structure.CodeEtab_D, BQ_Structure.CodeEtab_F));
	
	// Vérification du message
	// Attention, il n'y pas forcément de caractère pour les messages (=> pas de substring)
	if(ligne.length() > 6) {
	    VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Message_D, ligne.length()));
	}
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg1(String ligne) throws Exception {
	// Vérification du code établissement
	VerificationOutils.verifNumerique(ligne.substring(BQ_Structure.CodeEtab_D, BQ_Structure.CodeEtab_F));
	
	// Vérification du message
	// Attention, il n'y pas forcément de caractère pour les messages (=> pas de substring)
	if(ligne.length() > 6) {
	    VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Message_D, ligne.length()));
	}
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg2(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.CleControle_D, BQ_Structure.CleControle_F));
	// Raison sociale
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.RaisonSociale_D, BQ_Structure.RaisonSociale_F));
	// Nom guichet
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.NomGuichet_D, BQ_Structure.NomGuichet_F));
	// Code Monnaie
	VerificationOutils.verifCodeMonnaie(ligne.substring(BQ_Structure.CodeMonnaie_D, BQ_Structure.CodeMonnaie_F));
	// Type Compte
	if(ligne.length() > BQ_Structure.TypeCompte_D)
	    VerificationOutils.verifTypeCompte(ligne.substring(BQ_Structure.TypeCompte_D, BQ_Structure.TypeCompte_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg3(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.CleControle_D, BQ_Structure.CleControle_F));
	// Nom du titulaire
	if(ligne.trim().length() <= BQ_Structure.Adresse1_D) {
	    VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Titulaire_D, ligne.length()));
	}
	else {
	    VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Titulaire_D, BQ_Structure.Titulaire_F));
	}
	    
	// Adresse 1ère ligne (si existante)
	if(ligne.length() > BQ_Structure.Adresse1_D)
	    VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Adresse1_D, ligne.length()));
    }

    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg4(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.CleControle_D, BQ_Structure.CleControle_F));
	// Adresse 2ème ligne
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Adresse2_D, ligne.length()));
	// Adresse 3ème ligne (si existante)
	if(ligne.length() > BQ_Structure.Adresse3_D)
	    VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Adresse3_D, ligne.length()));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg5(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.CleControle_D, BQ_Structure.CleControle_F));
	// Adresse 5ème ligne
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Adresse5_D, ligne.length()));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg6(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.CleControle_D, BQ_Structure.CleControle_F));
	// Date Ancien Solde
	VerificationOutils.verifNumerique(ligne.substring(BQ_Structure.AncienSolde_Date_D, BQ_Structure.AncienSolde_Date_F));
	// Signe Ancien Solde
	VerificationOutils.verifSigne(ligne.substring(BQ_Structure.AncienSolde_Signe_D, BQ_Structure.AncienSolde_Signe_F));
	// Montant EUR Ancien Solde
	VerificationOutils.verifNumerique(ligne.substring(BQ_Structure.AncienSolde_EUR_D, BQ_Structure.AncienSolde_EUR_F));
	// Montant FRF Ancien Solde	
	// Certains enregistrements n'ont pas de FRF, mais directement le libellé
	String soldeFRFString = ligne.substring(BQ_Structure.AncienSolde_FRF_D, BQ_Structure.AncienSolde_FRF_F);
	try {
	    // on convertit, si une erreur est levée, il ne s'agit pas du solde FRF
	    Integer.parseInt(soldeFRFString);
	    VerificationOutils.verifNumerique(soldeFRFString);

	    // Libellé Ancien Solde
	    if(ligne.length() > BQ_Structure.AncienSolde_Libelle_D)
		VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.AncienSolde_Libelle_D, ligne.length()));   
	}
	catch(NumberFormatException e) {
	    // on vérifie en alphanumérique pour le libellé
	    VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.AncienSolde_FRF_D, ligne.length()));
	}
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg7(String ligne) throws Exception, ExceptionLibelle {
	// Clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.CleControle_D, BQ_Structure.CleControle_F));
	// Date Mouvement
	VerificationOutils.verifNumerique(ligne.substring(BQ_Structure.Mvt_Date_D, BQ_Structure.Mvt_Date_F));
	// Début Libellé
	//VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Mvt_Libelle1_D, BQ_Structure.Mvt_Libelle1_F));
	String debLibelle = ligne.substring(BQ_Structure.Mvt_Libelle1_D, BQ_Structure.Mvt_Libelle1_F);
	// Signe Mouvement
	VerificationOutils.verifSigne(ligne.substring(BQ_Structure.Mvt_Signe_D, BQ_Structure.Mvt_Signe_F));
	// Montant Mouvement
	VerificationOutils.verifMontant(ligne.substring(BQ_Structure.Mvt_EUR_D, BQ_Structure.Mvt_EUR_F));
	// Fin Libellé
	//VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.Mvt_Libelle2_D, ligne.length()));
	//VerificationOutils.verifLibelle(ligne.substring(BQ_Structure.Mvt_Libelle2_D, ligne.length()));
	String finLibelle = ligne.substring(BQ_Structure.Mvt_Libelle2_D, ligne.length());
	VerificationOutils.verifAlphaNumerique(debLibelle + finLibelle);
	// Frais bancaire
	//VerifUtil.verifAlphaNumerique(ligne.substring(BQ_Structure.Mvt_Frais_D, BQ_Structure.Mvt_Frais_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg8(String ligne) throws Exception, ExceptionMvtMontant {
	// Clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(BQ_Structure.CleControle_D, BQ_Structure.CleControle_F));
	// Date Nouveau Solde
	VerificationOutils.verifNumerique(ligne.substring(BQ_Structure.NouveauSolde_Date_D, BQ_Structure.AncienSolde_Date_F));
	// Signe Nouveau Solde
	VerificationOutils.verifSigne(ligne.substring(BQ_Structure.NouveauSolde_Signe_D, BQ_Structure.AncienSolde_Signe_F));
	// Montant EUR Nouveau Solde
	VerificationOutils.verifNumerique(ligne.substring(BQ_Structure.NouveauSolde_EUR_D, BQ_Structure.AncienSolde_EUR_F));
	// Montant FRF Nouveau Solde
	if(ligne.length() > BQ_Structure.NouveauSolde_FRF_D) {
	    String nouveauSoldeFRF = ligne.substring(BQ_Structure.NouveauSolde_FRF_D, BQ_Structure.AncienSolde_FRF_F);
	    if(nouveauSoldeFRF.trim().length() > 0)
		VerificationOutils.verifNumerique(nouveauSoldeFRF);
	}
	
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg9(String ligne) throws Exception {
	// Vérification du code établissement
	VerificationOutils.verifNumerique(ligne.substring(BQ_Structure.CodeEtab_D, BQ_Structure.CodeEtab_F));
	// Vérification de relevés de compte => verifNbReleves
	// Vérification du nombre d'enregistrements => verifNbEnreg
	
    }
    
    /**
     * 
     * @param ancien
     * @param ope
     * @param nouveau
     * @throws Exception 
     */
    @Override
    public void verifSolde(String ancien, ArrayList<String> ope, String nouveau) throws Exception {
	// ancien solde
	int ancienSolde = Integer.parseInt(ancien.substring(BQ_Structure.AncienSolde_EUR_D, BQ_Structure.AncienSolde_EUR_F));
	if(ancien.substring(BQ_Structure.AncienSolde_Signe_D, BQ_Structure.AncienSolde_Signe_F).equals("-"))
	    ancienSolde *= -1;
	
	// ancien solde
	int nouveauSolde = Integer.parseInt(nouveau.substring(BQ_Structure.NouveauSolde_EUR_D, BQ_Structure.NouveauSolde_EUR_F));
	if(nouveau.substring(BQ_Structure.NouveauSolde_Signe_D, BQ_Structure.NouveauSolde_Signe_F).equals("-"))
	    nouveauSolde *= -1;
	
	// total des opérations
	int totalOperations = 0;	
	
	Iterator<String> iterator = ope.iterator();
	while(iterator.hasNext()) {
	    String ligne = iterator.next();
	    
	    int aOpe = Integer.parseInt(ligne.substring(BQ_Structure.Mvt_EUR_D, BQ_Structure.Mvt_EUR_F));

	    if(ligne.substring(BQ_Structure.Mvt_Signe_D, BQ_Structure.Mvt_Signe_F).equals("-"))
		aOpe *= -1;

	    totalOperations += aOpe;
	}
	
	int monNouveauSolde = ancienSolde + totalOperations;
	
	if(ancienSolde + totalOperations != nouveauSolde)
	    throw new Exception("Problème solde (Fichier / Logiciel): " + nouveauSolde + " / " + monNouveauSolde);
    }
    
    /**
     * 
     * @param fic
     * @param calcule
     * @throws Exception 
     */
    @Override
    public void verifNbReleves(String fic, int calcule) throws Exception {
	// Comparaison des valeurs du fichier banque et calculées
	if(fic.trim().length() > BQ_Structure.Queue_Extrait_D) {
	    String nbExtraits = fic.substring(BQ_Structure.Queue_Extrait_D, BQ_Structure.Queue_Extrait_F);
	    
	    try {
		int nbExtraitsFic = Integer.parseInt(nbExtraits);
		if(nbExtraitsFic != calcule)
		    throw new Exception("Problème nombre relevés (Fichier / Logiciel) : " + nbExtraitsFic + " / " + calcule);
	    }
	    catch(NumberFormatException e) {
		VerificationOutils.verifNumerique(nbExtraits);
	    }
	}
	else
	    throw new Exception("Le nombre de relevés manque");
    }
    
    /**
     * 
     * @param fic
     * @param calcule
     * @throws Exception 
     */
    @Override
    public void verifNbEnreg(String fic, int calcule) throws Exception {
	if(fic.trim().length() >= BQ_Structure.Queue_Enreg_D) {
	    // parfois, il y a des caractères, mais invalides (saut de page ...), => trim()
	    String nbEnregistrements = fic.substring(BQ_Structure.Queue_Enreg_D).trim();
	    
	    try {			    
		if(nbEnregistrements.length() > 0) {
		    int nbEnregistrementsFic = Integer.parseInt(nbEnregistrements);

		    // Pour une raison inexpliquée, il faut +1 le nombre d'enregistrements calculé
		    // pour faire correspondre la valeur du fichier
		    //calcule += 1;
		    
		    if(nbEnregistrementsFic != calcule)
			throw new Exception("Problème nbEnregs (Fichier / Calculé) : " + nbEnregistrementsFic + " / " + calcule);
		}
	    }
	    catch(NumberFormatException e) {
		VerificationOutils.verifNumerique(nbEnregistrements);
	    }
	}
	else
	    throw new Exception("Le nombre d'enregistrements manque");
    }
    
    @Override
    public String razMontant(String ligne) {
	String raz = "000000000000";
	StringBuilder foo = new StringBuilder(ligne);
	foo.replace(BQ_Structure.Mvt_EUR_D, BQ_Structure.Mvt_EUR_F, raz);

	return foo.toString();
    }
}
