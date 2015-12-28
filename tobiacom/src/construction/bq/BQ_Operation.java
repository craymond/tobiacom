package construction.bq;
import construction.Operation;
import structure.BQ_Structure;


public class BQ_Operation extends Operation {
    
    /**
     * 
     * @param aOperation 
     */
    @Override
    public void setOperation(String aOperation) {
	date    = aOperation.substring(BQ_Structure.Mvt_Date_D, BQ_Structure.Mvt_Date_F);
	signe   = aOperation.substring(BQ_Structure.Mvt_Signe_D, BQ_Structure.Mvt_Signe_F);
	montantEUR = String.valueOf(Integer.parseInt(aOperation.substring(BQ_Structure.Mvt_EUR_D, BQ_Structure.Mvt_EUR_F)));
	libelle  = aOperation.substring(BQ_Structure.Mvt_Libelle1_D, BQ_Structure.Mvt_Libelle1_F);
	libelle += aOperation.substring(BQ_Structure.Mvt_Libelle2_D, aOperation.length());
	libelle  = libelle.trim();	
	fraisBancaire = (aOperation.charAt(0) == '*');
    }
}
