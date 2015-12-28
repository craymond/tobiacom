package construction.cl;
import construction.ExtraitCompte;
import construction.Operation;
import java.util.ArrayList;
import structure.CL_Structure;

public class CL_ExtraitCompte extends ExtraitCompte {
    
    private ArrayList<CL_Operation> operationsCL;
    
    public CL_ExtraitCompte() {
	operationsCL = new ArrayList<CL_Operation>();
    }
    
    /**
     * 
     * @param ancienSolde 
     */
    @Override
    public void setAncienSolde(String ancienSolde) {
	dateAncienSolde  = ancienSolde.substring(CL_Structure.AncienSolde_Date_D, CL_Structure.AncienSolde_Date_F);
	signeAncienSolde = ancienSolde.substring(CL_Structure.AncienSolde_Signe_D, CL_Structure.AncienSolde_Signe_F);
	ancienSoldeEUR   = String.valueOf(Integer.parseInt(ancienSolde.substring(CL_Structure.AncienSolde_EUR_D, CL_Structure.AncienSolde_EUR_F)));	
	ancienSoldeFRF   = String.valueOf(Integer.parseInt(ancienSolde.substring(CL_Structure.AncienSolde_FRF_D, CL_Structure.AncienSolde_FRF_F)));
	libelleCompte    = ancienSolde.substring(CL_Structure.AncienSolde_Libelle_D, CL_Structure.AncienSolde_Libelle_F);
    }
    
    
    /**
     * 
     * @param nouveauSolde 
     */
    @Override
    public void setNouveauSolde(String nouveauSolde) {
	dateNouveauSolde  = nouveauSolde.substring(CL_Structure.NouveauSolde_Date_D, CL_Structure.NouveauSolde_Date_F);
	signeNouveauSolde = nouveauSolde.substring(CL_Structure.NouveauSolde_Signe_D, CL_Structure.NouveauSolde_Signe_F);
	nouveauSoldeEUR   = String.valueOf(Integer.parseInt(nouveauSolde.substring(CL_Structure.NouveauSolde_EUR_D, CL_Structure.NouveauSolde_EUR_F)));
	nouveauSoldeFRF   = String.valueOf(Integer.parseInt(nouveauSolde.substring(CL_Structure.NouveauSolde_FRF_D, CL_Structure.NouveauSolde_FRF_F)));
    }
    
    /**
     * 
     * @param op 
     */
    @Override
    public void setOperation(String op) {
	Operation operation = new CL_Operation();
	operation.setOperation(op);
	operationsCL.add((CL_Operation)operation);
    }
    
    
    
    /**
     * 
     * @return 
     */    
    public ArrayList<CL_Operation> getOperationsCL() {
	return operationsCL;
    }
    
}
