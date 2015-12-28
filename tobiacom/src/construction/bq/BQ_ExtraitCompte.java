package construction.bq;

import construction.ExtraitCompte;
import construction.Operation;
import java.util.ArrayList;
import structure.BQ_Structure;

public class BQ_ExtraitCompte extends ExtraitCompte {

    /**
     * 
     */
    public BQ_ExtraitCompte() {
	operations = new ArrayList<Operation>();
    }
    
    /**
     * 
     */
    @Override
    public void setAncienSolde(String ancienSolde) {
	dateAncienSolde  = ancienSolde.substring(BQ_Structure.AncienSolde_Date_D, BQ_Structure.AncienSolde_Date_F);
	signeAncienSolde = ancienSolde.substring(BQ_Structure.AncienSolde_Signe_D, BQ_Structure.AncienSolde_Signe_F);
	// on supprime les 0 superflus
	ancienSoldeEUR = String.valueOf(Integer.parseInt(ancienSolde.substring(BQ_Structure.AncienSolde_EUR_D, BQ_Structure.AncienSolde_EUR_F)));

	// il n'y a pas toujours de solde en FRF, mais le libellé du compte
	ancienSoldeFRF = ancienSolde.substring(BQ_Structure.AncienSolde_FRF_D, BQ_Structure.AncienSolde_FRF_F);
	try {
	    ancienSoldeFRF = String.valueOf(Integer.parseInt(ancienSoldeFRF));
	    typeCompte = ExtraitCompte.COMPTE_COURANT;
	    
	    if(ancienSolde.length() > BQ_Structure.AncienSolde_Libelle_D)
		libelleCompte = ancienSolde.substring(BQ_Structure.AncienSolde_Libelle_D, ancienSolde.length());
	}
	catch(NumberFormatException e) {
	    typeCompte = ExtraitCompte.COMPTE_CHEQUE;
	    libelleCompte = ancienSolde.substring(BQ_Structure.AncienSolde_FRF_D, ancienSolde.length()).trim();
	    ancienSoldeFRF = "";
	}
	
    }
    
    /**
     * 
     * @param nouveauSolde 
     */
    @Override
    public void setNouveauSolde(String nouveauSolde) {
	dateNouveauSolde  = nouveauSolde.substring(BQ_Structure.NouveauSolde_Date_D, BQ_Structure.NouveauSolde_Date_F);
	signeNouveauSolde = nouveauSolde.substring(BQ_Structure.NouveauSolde_Signe_D, BQ_Structure.NouveauSolde_Signe_F);
	// on supprime les 0 superflus
	nouveauSoldeEUR   = String.valueOf(Integer.parseInt(nouveauSolde.substring(BQ_Structure.NouveauSolde_EUR_D, BQ_Structure.NouveauSolde_EUR_F)));
	
	// le compte courant ont la contre-valeur en francs
	if(typeCompte == ExtraitCompte.COMPTE_COURANT) {
	    nouveauSoldeFRF = nouveauSolde.substring(BQ_Structure.NouveauSolde_FRF_D, BQ_Structure.NouveauSolde_FRF_F);
	    nouveauSoldeFRF = String.valueOf(Integer.parseInt(nouveauSoldeFRF));
	}
	// compte chèque
	else {
	    nouveauSoldeFRF = "";
	}
    }
    
    /**
     * 
     * @param op 
     */
    @Override
    public void setOperation(String op) {
	Operation operation = new BQ_Operation();
	operation.setOperation(op);
	operations.add(operation);
    }
}
