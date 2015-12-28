package construction.cl;
import construction.Operation;
import structure.CL_Structure;

public class CL_Operation extends Operation {
    private String montantFRF;
    
    /**
     * 
     * @param aOperation 
     */
    @Override
    public void setOperation(String aOperation) {
	date    = aOperation.substring(CL_Structure.Mvt_Date_D, CL_Structure.Mvt_Date_F);
	signe   = aOperation.substring(CL_Structure.Mvt_Signe_D, CL_Structure.Mvt_Signe_F);
	montantEUR = String.valueOf(Integer.parseInt(aOperation.substring(CL_Structure.Mvt_EUR_D, CL_Structure.Mvt_EUR_F)));
	montantFRF = String.valueOf(Integer.parseInt(aOperation.substring(CL_Structure.Mvt_FRF_D, CL_Structure.Mvt_FRF_F)));
	fraisBancaire = (aOperation.charAt(0) == '*');
	
	
	// s'il s'agit d'une opération en une seule partie
	if(!aOperation.contains(CL_LectureConstruction.MARQUEUR_LIBELLE)) {
	    libelle  = aOperation.substring(CL_Structure.Mvt_Libelle1_D, CL_Structure.Mvt_Libelle1_F).trim();
	}
	// 
	else {
	    String[] split = aOperation.split(CL_LectureConstruction.MARQUEUR_LIBELLE);
	    // la 1ère partie du libelle
	    libelle  = split[0].substring(CL_Structure.Mvt_Libelle1_D, CL_Structure.Mvt_Libelle1_F);
	    // les autres parties
	    for(int i = 1; i < split.length; i++) {
		libelle += split[i].substring(CL_Structure.Mvt_Libelle2_D, CL_Structure.Mvt_Libelle2_F);
	    }
	    
	    libelle  = libelle.trim();
	}
    }
    
    /**
     * 
     * @return 
     */
    public String getMontantFRF() { return montantFRF; }
}
