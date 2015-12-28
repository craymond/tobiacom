package miseEnForme;

public abstract class MEF_Pliage_Abstract {
    public static final int RIEN   = 0;
    public static final int PLIAGE = 1;
    public static final int PAGE   = 2;
    
    private int nbPages;
    private int nbLignes;
    
    public abstract boolean isPliage();
    public abstract String getPliage();
    public abstract String getPliageMvt();
    public abstract void init(int idReleve);
    public abstract String lignePliage();
    public abstract String lignePliageMvt();
    public abstract String lignePage();
    public abstract String lignePageMvt();
    public abstract void addCpt();
    public abstract void addCpt(int i);
    public abstract int  getCpt();
}
