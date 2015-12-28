package construction;

import java.util.ArrayList;

public abstract class ExtraitCompte {
    public static final int COMPTE_COURANT = 1;
    public static final int COMPTE_CHEQUE  = 2;
    
    protected String dateAncienSolde  = "";
    protected String signeAncienSolde = "";
    protected String ancienSoldeEUR   = "";
    protected String ancienSoldeFRF   = "";
    protected String libelleCompte    = "";
    protected ArrayList<Operation> operations;
    protected String dateNouveauSolde = "";
    protected String signeNouveauSolde = "";
    protected String nouveauSoldeEUR  = "";
    protected String nouveauSoldeFRF  = "";
    protected int typeCompte;	// Compte Courant = "1", Compte Chèque = "2"

    public ExtraitCompte() {
	operations = new ArrayList<Operation>();
    }

    // Setters
    public abstract void setAncienSolde(String ancienSolde);
    public abstract void setNouveauSolde(String nouveauSolde);
    public abstract void setOperation(String op);
    
    // Getters
    public String getDateAncienSolde() { return dateAncienSolde; }
    public String getDateNouveauSolde() { return dateNouveauSolde; }
    public String getLibelleCompte() { return libelleCompte; }
    public String getMontantAncienSoldeEUR() { return ancienSoldeEUR; }
    public String getMontantAncienSoldeFRF() { return ancienSoldeFRF; }
    public String getMontantNouveauSoldeEUR() { return nouveauSoldeEUR; }
    public String getMontantNouveauSoldeFRF() { return nouveauSoldeFRF; }
    public ArrayList<Operation> getOperations() { return operations; }
    public String getSigneAncienSolde() { return signeAncienSolde; }
    public String getSigneNouveauSolde() { return signeNouveauSolde; }
    public int getTypeCompte() { return typeCompte; } 
    
    @Override
    public String toString() {
	String affich;
	affich  = "ancienSolde (" + dateAncienSolde + ") : " + signeAncienSolde + ancienSoldeEUR + "\n";
	
	for(int i = 0; i < operations.size(); i++) {
	    affich += operations.get(i).toString() + "\n";
	}
	
	affich += "nouveauSolde (" + dateNouveauSolde + ") : " + signeNouveauSolde + nouveauSoldeEUR; 

	return affich;
    }
    

}
