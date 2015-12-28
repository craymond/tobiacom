package construction.sg;

import construction.Operation;
import structure.SG_Structure;

public class SG_Operation extends Operation {
    
    /**
     * 
     * @param aOperation 
     */
    @Override
    public void setOperation(String aOperation) {
	date    = aOperation.substring(SG_Structure.Mvt_Date_D, SG_Structure.Mvt_Date_F);
	signe   = aOperation.substring(SG_Structure.Mvt_Signe_D, SG_Structure.Mvt_Signe_F);
	montantEUR = String.valueOf(Integer.parseInt(aOperation.substring(SG_Structure.Mvt_EUR_D, SG_Structure.Mvt_EUR_F)));
	libelle = aOperation.substring(SG_Structure.Mvt_Libelle_D, SG_Structure.Mvt_Libelle_F).trim();
	fraisBancaire = (aOperation.charAt(0) == '*');
    }
}
