package verification.banque;
import java.util.ArrayList;
import java.util.Iterator;
import structure.SG_Structure;
import verification.Verification;
import verification.VerificationOutils;

public class SG_Verification extends Verification {
    
    
    public SG_Verification(ArrayList<String> fic) {	
	super(fic);	
    }
    
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void verifEnreg0(String ligne) throws Exception {	
	// Vérification du code établissement
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CodeEtab_D, SG_Structure.CodeEtab_F));

	// Vérification du message
	// Attention, il n'y pas forcément de caractère pour les messages (=> pas de substring)
	if(ligne.length() > 6) {
	    VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Message_D, SG_Structure.Message_F));    
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
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CodeEtab_D, SG_Structure.CodeEtab_F));	
	// Vérification du message
	if(ligne.length() > 6) {
	    VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Message_D, SG_Structure.Message_F));
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
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CleControle_D, SG_Structure.CleControle_F));
	// Raison sociale
	VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.RaisonSociale_D, SG_Structure.RaisonSociale_F));
	// Nom guichet
	VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.NomGuichet_D, SG_Structure.NomGuichet_F));
	// Code Monnaie
	VerificationOutils.verifCodeMonnaie(ligne.substring(SG_Structure.CodeMonnaie_D, SG_Structure.CodeMonnaie_F));
	// Type Compte
	VerificationOutils.verifTypeCompte(ligne.substring(SG_Structure.TypeCompte_D, SG_Structure.TypeCompte_F));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg3(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CleControle_D, SG_Structure.CleControle_F));
	// Nom du titulaire
	VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Titulaire_D, SG_Structure.Titulaire_F));
	// Adresse 1ère ligne
	VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse1_D, ligne.length()));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg4(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CleControle_D, SG_Structure.CleControle_F));
	
	
	// Adresse
	if(ligne.length() > SG_Structure.Adresse2_D && ligne.length() > SG_Structure.Adresse3_D) {
	    // Adresse 2ème ligne
	    VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse2_D, SG_Structure.Adresse2_F));

	    if(ligne.length() > SG_Structure.Adresse3_F) {
		    VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse3_D, SG_Structure.Adresse3_F));
		    
		    if(ligne.length() > SG_Structure.Adresse4_D) {
			VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse4_D, ligne.length()));
		    }
		}
		else
		    VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse3_D, ligne.length()));
	    }
	else {
	    // Adresse 2ème ligne
	    VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse2_D, ligne.length()));
	}

	//VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse2_D, SG_Structure.Adresse2_F));
	// Adresse 3ème ligne
	//VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse3_D, SG_Structure.Adresse3_F));
	// Adresse 4ème ligne
	//VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse4_D, ligne.length()));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg5(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CleControle_D, SG_Structure.CleControle_F));
	// Adresse 5ème ligne
	VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Adresse5_D, ligne.length()));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg6(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CleControle_D, SG_Structure.CleControle_F));
	// Date Ancien Solde
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.AncienSolde_Date_D, SG_Structure.AncienSolde_Date_F));
	// Signe Ancien Solde
	VerificationOutils.verifSigne(ligne.substring(SG_Structure.AncienSolde_Signe_D, SG_Structure.AncienSolde_Signe_F));
	// Montant EUR Ancien Solde
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.AncienSolde_EUR_D, SG_Structure.AncienSolde_EUR_F));
	// Montant FRF Ancien Solde
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.AncienSolde_FRF_D, SG_Structure.AncienSolde_FRF_F));
	// Libellé Ancien Solde
	VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.AncienSolde_Libelle_D, ligne.length()));
    }

    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg7(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CleControle_D, SG_Structure.CleControle_F));
	// Date Mouvement
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.Mvt_Date_D, SG_Structure.Mvt_Date_F));
	// Signe Mouvement
	VerificationOutils.verifSigne(ligne.substring(SG_Structure.Mvt_Signe_D, SG_Structure.Mvt_Signe_F));
	// Montant Mouvement
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.Mvt_EUR_D, SG_Structure.Mvt_EUR_F));
	// Libelle Mouvement
	VerificationOutils.verifAlphaNumerique(ligne.substring(SG_Structure.Mvt_Libelle_D, ligne.length()));
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg8(String ligne) throws Exception {
	// Clé de contrôle
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CleControle_D, SG_Structure.CleControle_F));
	// Date Nouveau Solde
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.NouveauSolde_Date_D, SG_Structure.AncienSolde_Date_F));
	// Signe Nouveau Solde
	VerificationOutils.verifSigne(ligne.substring(SG_Structure.NouveauSolde_Signe_D, SG_Structure.AncienSolde_Signe_F));
	// Montant EUR Nouveau Solde
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.NouveauSolde_EUR_D, SG_Structure.AncienSolde_EUR_F));
	// Montant FRF Nouveau Solde
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.NouveauSolde_FRF_D, SG_Structure.AncienSolde_FRF_F));
    }
    
    /**
     * Cet enregistrement est pas toujours bien respecté
     * @param ligne
     * @throws Exception 
     */
    @Override
    public void verifEnreg9(String ligne) throws Exception {
	// Vérification du code établissement
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.CodeEtab_D, SG_Structure.CodeEtab_F));
	// Vérification d'extraits de compte
	VerificationOutils.verifNumerique(ligne.substring(SG_Structure.Queue_Extrait_D, SG_Structure.Queue_Extrait_F));
	// Vérification du nombre d'enregistrements
	if(ligne.length() >= SG_Structure.Queue_Enreg_D) {
	    //String nbExtraitTrim = ligne.substring(SG_Structure.Queue_Enreg_D, SG_Structure.Queue_Enreg_F).trim();
	    String nbExtraitTrim = ligne.substring(SG_Structure.Queue_Enreg_D).trim();
	    if(nbExtraitTrim.length() > 0)	    
		VerificationOutils.verifNumerique(nbExtraitTrim);
	    else
		throw new Exception("Le nombre d'enregistrements manque");
	}
	else
	    throw new Exception("Le nombre d'enregistrements manque");
    }

    /**
     * 
     * @param ancienSolde
     * @param operation
     * @param nouveauSolde 
     */
    @Override
    public void verifSolde(String ancien, ArrayList<String> ope, String nouveau) throws Exception {
	// ancien solde
	int ancienSolde = Integer.parseInt(ancien.substring(SG_Structure.AncienSolde_EUR_D, SG_Structure.AncienSolde_EUR_F));
	if(ancien.substring(SG_Structure.AncienSolde_Signe_D, SG_Structure.AncienSolde_Signe_F).equals("-"))
	    ancienSolde *= -1;
	
	// ancien solde
	int nouveauSolde = Integer.parseInt(nouveau.substring(SG_Structure.NouveauSolde_EUR_D, SG_Structure.NouveauSolde_EUR_F));
	if(nouveau.substring(SG_Structure.NouveauSolde_Signe_D, SG_Structure.NouveauSolde_Signe_F).equals("-"))
	    nouveauSolde *= -1;
	
	// total des opérations
	int totalOperations = 0;	
	
	Iterator<String> iterator = ope.iterator();
	while(iterator.hasNext()) {
	    String ligne = iterator.next();
	    
	    int aOpe = Integer.parseInt(ligne.substring(SG_Structure.Mvt_EUR_D, SG_Structure.Mvt_EUR_F));
	    if(ligne.substring(SG_Structure.Mvt_Signe_D, SG_Structure.Mvt_Signe_F).equals("-"))
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
     */
    @Override
    public void verifNbReleves(String fic, int calcule) throws Exception {
	// Comparaison des valeurs du fichier banque et calculées
	String nbExtraits = fic.substring(SG_Structure.Queue_Extrait_D, SG_Structure.Queue_Extrait_F);
	int nbExtraitsFic = Integer.parseInt(nbExtraits);
	
	if(nbExtraitsFic != calcule)
	    throw new Exception("Problème nombre relevés (Fichier / Logiciel) : " + nbExtraitsFic + " / " + calcule);
    }
    
    /**
     * Comparaison des valeurs du fichier banque et calculées
     * @param fic
     * @param calcule
     * @throws Exception 
     */
    @Override
    public void verifNbEnreg(String fic, int calcule) throws Exception {	
	// parfois, l'enregistrement 9 est incomplet
	if(fic.length() >= SG_Structure.Queue_Enreg_D) {
	    // parfois, il y a des caractères, mais invalides (saut de page ...), => trim()
	    //String nbEnregistrements = fic.substring(SG_Structure.Queue_Enreg_D, SG_Structure.Queue_Enreg_F).trim();
	    String nbEnregistrements = fic.substring(SG_Structure.Queue_Enreg_D).trim();
	    
	    if(nbEnregistrements.length() > 0) {
		int nbEnregistrementsFic = Integer.parseInt(nbEnregistrements);

		// Pour une raison inexpliquée, il faut +1 le nombre d'enregistrements calculé
		// pour faire correspondre la valeur du fichier
		calcule += 1;

		if(nbEnregistrementsFic != calcule)
		    throw new Exception("Problème nbEnregs (Fichier / Logiciel) : " + nbEnregistrementsFic + " / " + calcule);
	    }
	}
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public String razMontant(String ligne) {
	String raz = "000000000000";
	ligne.replace(ligne.substring(SG_Structure.Mvt_EUR_D, SG_Structure.Mvt_EUR_F), raz);
	
	return ligne;
    }
}
