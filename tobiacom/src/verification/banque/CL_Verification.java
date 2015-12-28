package verification.banque;
import java.util.ArrayList;
import java.util.Iterator;
import structure.CL_Structure;
import verification.Verification;
import verification.VerificationOutils;

public class CL_Verification extends Verification {
    private boolean suiteLibelle;
    
    
    public CL_Verification(ArrayList<String> fic) {
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
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.CodeEtab_D, CL_Structure.CodeEtab_F));
	// Vérification du code guichet
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.CodeGuichet_D, CL_Structure.CodeGuichet_F));
	// Vérification du numéro de compte
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.NumCompte_D, CL_Structure.NumCompte_F));
	// Vérification du type d'extrait
	VerificationOutils.verifTypeExtrait(ligne.substring(CL_Structure.TypeExtrait_D, CL_Structure.TypeExtrait_F));
	// Vérification du code devise
	VerificationOutils.verifCodeDevise(ligne.substring(CL_Structure.CodeDevise_D, CL_Structure.CodeDevise_F));
	// Vérification du numéro de carte
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.NumCarte_D, CL_Structure.NumCarte_F));
	// Vérification du compteur d'enregistrement
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.CompteurEnreg_D, CL_Structure.CompteurEnreg_F));
	// Vérification du message
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Message_D, CL_Structure.Message_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg1(String ligne) throws Exception {
	// Vérification de la clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.CleControle_D, CL_Structure.CleControle_F));
	// Vérification du message
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Message_D, CL_Structure.Message_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg2(String ligne) throws Exception {
	// Vérification de la clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.CleControle_D, CL_Structure.CleControle_F));
	// Vérification de la raison sociale
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.RaisonSociale_D, CL_Structure.RaisonSociale_F));
	// Vérification du nom du guichet
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.NomGuichet_D, CL_Structure.NomGuichet_F));
	// Vérification du téléphone
	String telephone = ligne.substring(CL_Structure.Telephone_D, CL_Structure.Telephone_F);
	if(telephone.trim().length() > 0)
		VerificationOutils.verifNumerique(telephone);
	// Vérification du code d'accès
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.CodeAcces_D, CL_Structure.CodeAcces_F));
	// Vérification de la clé RIB
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.CleRIB_D, CL_Structure.CleRIB_F));
	// Vérification de la date de traitement
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.DateTraitement_D, CL_Structure.DateTraitement_F));
    }

    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg3(String ligne) throws Exception {
	// Vérification de la clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.CleControle_D, CL_Structure.CleControle_F));
	// Vérification du porteur
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Titulaire_D, CL_Structure.Titulaire_F));
	// Vérification de la 1ère ligne d'adresse
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Adresse1_D, CL_Structure.Adresse1_F));
    }

    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg4(String ligne) throws Exception {
	// Vérification de la clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.CleControle_D, CL_Structure.CleControle_F));
	// Vérification de la 2ème ligne d'adresse
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Adresse2_D, CL_Structure.Adresse2_F));
	// Vérification de la 3ème ligne d'adresse
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Adresse3_D, CL_Structure.Adresse3_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg5(String ligne) throws Exception {
	// Vérification de la clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.CleControle_D, CL_Structure.CleControle_F));
	// Vérification de la 2ème ligne d'adresse
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Adresse4_D, CL_Structure.Adresse4_F));
	// Vérification de la 3ème ligne d'adresse
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Adresse5_D, CL_Structure.Adresse5_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg6(String ligne) throws Exception {
	// Vérification de la clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.CleControle_D, CL_Structure.CleControle_F));
	// Vérification de la date
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.AncienSolde_Date_D, CL_Structure.AncienSolde_Date_F));
	// Vérification du signe
	VerificationOutils.verifSigne(ligne.substring(CL_Structure.AncienSolde_Signe_D, CL_Structure.AncienSolde_Signe_F));
	// Vérification du montant EUR
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.AncienSolde_EUR_D, CL_Structure.AncienSolde_EUR_F));
	// Vérification du montant FRF
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.AncienSolde_FRF_D, CL_Structure.AncienSolde_FRF_F));
	// Vérification du libellé
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.AncienSolde_Libelle_D, CL_Structure.AncienSolde_Libelle_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg7(String ligne) throws Exception {
	// Vérification de la clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.CleControle_D, CL_Structure.CleControle_F));
	// Soit il s'agit d'une ligne classique, soit de la suite d'un libellé
	if(!getSuiteLibelle()) {
	    // Vérification de la date
	    VerificationOutils.verifNumerique(ligne.substring(CL_Structure.Mvt_Date_D, CL_Structure.Mvt_Date_F));
	    // Vérification du signe
	    VerificationOutils.verifSigne(ligne.substring(CL_Structure.Mvt_Signe_D, CL_Structure.Mvt_Signe_F));
	    // Vérification du montant EUR
	    VerificationOutils.verifNumerique(ligne.substring(CL_Structure.Mvt_EUR_D, CL_Structure.Mvt_EUR_F));
	    // Vérification du montant FRF
	    VerificationOutils.verifNumerique(ligne.substring(CL_Structure.Mvt_FRF_D, CL_Structure.Mvt_FRF_F));
	    // Vérification du libellé
	    VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Mvt_Libelle1_D, CL_Structure.Mvt_Libelle1_F));
	}
	// il s'agit de la suite d'un libellé
	else {
	    VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.Mvt_Libelle2_D, CL_Structure.Mvt_Libelle2_F));
	}
	
	// on sauvegarde l'état du libellé
	// à la fin des enregistrements 7, il ne peut être que à "false"
	setSuiteLibelle(ligne.charAt(108));
    }


    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg8(String ligne) throws Exception {
	// Vérification de la clé de contrôle
	VerificationOutils.verifAlphaNumerique(ligne.substring(CL_Structure.CleControle_D, CL_Structure.CleControle_F));
	// Vérification de la date
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.NouveauSolde_Date_D, CL_Structure.NouveauSolde_Date_F));
	// Vérification du signe
	VerificationOutils.verifSigne(ligne.substring(CL_Structure.NouveauSolde_Signe_D, CL_Structure.NouveauSolde_Signe_F));
	// Vérification du montant EUR
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.NouveauSolde_EUR_D, CL_Structure.NouveauSolde_EUR_F));
	// Vérification du montant FRF
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.NouveauSolde_FRF_D, CL_Structure.NouveauSolde_FRF_F));
	// Vérification de l'indicateur de nouveau solde
	VerificationOutils.verifIndicateurSolde(ligne.substring(CL_Structure.NouveauSolde_Indicateur_D, CL_Structure.NouveauSolde_Indicateur_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg9(String ligne) throws Exception {
	// Vérification du code établissement
	VerificationOutils.verifNumerique(ligne.substring(CL_Structure.CodeEtab_D, CL_Structure.CodeEtab_F));
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
	// on ne vérifie que le solde des relevés "compte" ou non "carte", 
	// désigné par le type extrait (charAt(22)) : 0 = Compte, 1 = Carte
	if(ancien.charAt(22) == '0') {
	    // ancien solde
	    int ancienSolde = Integer.parseInt(ancien.substring(CL_Structure.AncienSolde_EUR_D, CL_Structure.AncienSolde_EUR_F));
	    if(ancien.substring(CL_Structure.AncienSolde_Signe_D, CL_Structure.AncienSolde_Signe_F).equals("-"))
		ancienSolde *= -1;

	    // ancien solde
	    int nouveauSolde = Integer.parseInt(nouveau.substring(CL_Structure.NouveauSolde_EUR_D, CL_Structure.NouveauSolde_EUR_F));
	    if(nouveau.substring(CL_Structure.NouveauSolde_Signe_D, CL_Structure.NouveauSolde_Signe_F).equals("-"))
		nouveauSolde *= -1;

	    // total des opérations
	    int totalOperations = 0;	

	    Iterator<String> iterator = ope.iterator();
	    while(iterator.hasNext()) {	    
		String ligne = iterator.next();

		// on calcule s'il ne s'agit pas de la suite d'un libellé
		if(!getSuiteLibelle()) {
		    int aOpe = Integer.parseInt(ligne.substring(CL_Structure.Mvt_EUR_D, CL_Structure.Mvt_EUR_F));
		    if(ligne.substring(CL_Structure.Mvt_Signe_D, CL_Structure.Mvt_Signe_F).equals("-"))
			aOpe *= -1;

		    totalOperations += aOpe;
		}
	    }

	    int monNouveauSolde = ancienSolde + totalOperations;

	    if(ancienSolde + totalOperations != nouveauSolde)
		throw new Exception("Problème solde (Fichier / Logiciel): " + nouveauSolde + " / " + monNouveauSolde);
	}
    }
    
    /**
     * 
     * @param fic
     * @param calcule
     * @throws Exception 
     */
    @Override
    public void verifNbReleves(String fic, int calcule) throws Exception {
	
    }
    
    /**
     * 
     * @param fic
     * @param calcule
     * @throws Exception 
     */
    @Override
    public void verifNbEnreg(String fic, int calcule) throws Exception {
	
    }
    
    /**
     * 
     * @param suiteLibelle 
     */
    public void setSuiteLibelle(char suiteLibelle) {
	this.suiteLibelle = suiteLibelle == '*';
    }
    
    /**
     * 
     * @return 
     */
    public boolean getSuiteLibelle() {
	return suiteLibelle;
    }

    /**
     * 
     * @param ligne 
     */
    @Override
    public String razMontant(String ligne) {
	String raz = "000000000000";
	ligne.replace(ligne.substring(CL_Structure.Mvt_EUR_D, CL_Structure.Mvt_EUR_F), raz);
	
	return ligne;
    }
}
