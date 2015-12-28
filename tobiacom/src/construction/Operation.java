package construction;

public abstract class Operation {
    protected String date = "";
    protected String signe = "";
    protected String montantEUR = "";
    protected String libelle = "";
    protected boolean fraisBancaire;
    
    public abstract void setOperation(String aOperation);

    public String getDate() { return date; }
    public boolean getFraisBancaire() { return fraisBancaire; }
    public String getLibelle() { return libelle; }
    public String getMontant() { return montantEUR; }
    public String getSigne() { return signe; }
    
    
    
    
    @Override
    public String toString() {
	return
	    "  " + date + " " + signe + montantEUR + "   " + libelle;
    }
}