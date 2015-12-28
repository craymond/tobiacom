package construction.sg;
import construction.ExtraitCompte;
import construction.Operation;
import java.util.ArrayList;
import structure.SG_Structure;


public class SG_ExtraitCompte extends ExtraitCompte {
    public SG_ExtraitCompte() {
	operations = new ArrayList<Operation>();
    }
    
    @Override
    public void setAncienSolde(String ancienSolde) {
	dateAncienSolde  = ancienSolde.substring(SG_Structure.AncienSolde_Date_D, SG_Structure.AncienSolde_Date_F);
	signeAncienSolde = ancienSolde.substring(SG_Structure.AncienSolde_Signe_D, SG_Structure.AncienSolde_Signe_F);
	// on supprime les 0 superflus
	ancienSoldeEUR = String.valueOf(Integer.parseInt(ancienSolde.substring(SG_Structure.AncienSolde_EUR_D, SG_Structure.AncienSolde_EUR_F)));
	ancienSoldeFRF = String.valueOf(Integer.parseInt(ancienSolde.substring(SG_Structure.AncienSolde_FRF_D, SG_Structure.AncienSolde_FRF_F)));
	libelleCompte = ancienSolde.substring(SG_Structure.AncienSolde_Libelle_D, SG_Structure.AncienSolde_Libelle_F);
    }
    
    @Override
    public void setNouveauSolde(String nouveauSolde) {
	dateNouveauSolde  = nouveauSolde.substring(SG_Structure.NouveauSolde_Date_D, SG_Structure.NouveauSolde_Date_F);
	signeNouveauSolde = nouveauSolde.substring(SG_Structure.NouveauSolde_Signe_D, SG_Structure.NouveauSolde_Signe_F);
	// on supprime les 0 superflus
	nouveauSoldeEUR = String.valueOf(Integer.parseInt(nouveauSolde.substring(SG_Structure.NouveauSolde_EUR_D, SG_Structure.NouveauSolde_EUR_F)));
	nouveauSoldeFRF = String.valueOf(Integer.parseInt(nouveauSolde.substring(SG_Structure.NouveauSolde_FRF_D, SG_Structure.NouveauSolde_FRF_F)));
    }
    
    @Override
    public void setOperation(String op) {
	Operation operation = new SG_Operation();
	operation.setOperation(op);
	operations.add(operation);
    }
}