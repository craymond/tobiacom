package construction.ca;
import construction.ExtraitCompte;
import construction.Operation;
import java.util.ArrayList;
import miseEnForme.MEF_Outils;
import structure.CA_Structure;

public class CA_ExtraitCompte extends ExtraitCompte {
    
    public CA_ExtraitCompte() {
	operations = new ArrayList<Operation>();
    }

    @Override
    public void setAncienSolde(String ancienSolde) {
	
	
	
	// deux infos sur deux lignes différentes :
	// - date ancien solde
	// - solde ancien solde (la ligne commence par "!")
	
	// Date
	//if(ancienSolde.charAt(12) != '!')  {  
	if(ancienSolde.charAt(12) == 'D') {
	    // on supprime le séparateur "/" entre les composants de la date
	    dateAncienSolde = MEF_Outils.dateCA(ancienSolde.substring(CA_Structure.AncienSolde_Date_D, CA_Structure.AncienSolde_Date_F), "/");
	}
	// Solde
	else {
	    ancienSoldeEUR = getMontant(ancienSolde);
	    if(ancienSoldeEUR.charAt(0) == '-')
		signeAncienSolde = "-";		
	}
    }
    
    @Override
    public void setNouveauSolde(String nouveauSolde) {
	dateNouveauSolde = MEF_Outils.dateCA(nouveauSolde.substring(CA_Structure.NouveauSolde_Date_D, CA_Structure.NouveauSolde_Date_F), "/");
	nouveauSoldeEUR  = getMontant(nouveauSolde);
	if(nouveauSoldeEUR.charAt(0) == '-')
	    signeNouveauSolde = "-";
    }
    
    @Override
    public void setOperation(String op) {	
	Operation operation = new CA_Operation();
	operation.setOperation(op);
	operations.add(operation);
    }
    
    /**
     * Dans le fichier banque, il y a deux colonnes pour les montants : débit et crédit (dans cet ordre)
     * Cette fonction parse la ligne et retourne le montant
     */
    public static String getMontant(String ligne) {
	// débit
	String debit = ligne.substring(CA_Structure.Montant_Debit_D, CA_Structure.Montant_Debit_F).trim();
	
	if(debit.length() > 0) {
	    return "-" + debit;
	}
	// sinon crédit
	else {
	    return ligne.substring(CA_Structure.Montant_Credit_D, CA_Structure.Montant_Credit_F).trim();
	}
    }
}
