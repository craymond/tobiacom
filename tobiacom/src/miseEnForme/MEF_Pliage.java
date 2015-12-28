package miseEnForme;

public class MEF_Pliage extends MEF_Pliage_Abstract {
    
    private int cpt;
    private String idReleve;
    
    public MEF_Pliage() {
	cpt = 1;
    }

    
    @Override
    public boolean isPliage() {
	cpt++;
	return (cpt == 15 || cpt == 31);
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public String getPliage() {
	if(cpt == 15) {
	    cpt += 2;	 // ligne de pliage + retour à la ligne = 2 lignes supplémentaires
	    return lignePliage();
	}
	else if(cpt == 31) {
	    cpt = 1;
	    return lignePage();
	}	    
	else
	    return "";
    }
    
    /**
     * 
     * @return 
     */
    
    @Override
    public String getPliageMvt() {
	if(cpt == 15) {
	    cpt += 2;	// ligne de pliage
	    return lignePliageMvt();
	}
	else if(cpt == 31) {
	    cpt = 1;
	    return lignePageMvt();
	}
	else
	    return "";
    }

    
    /**
     * 
     * @param idReleve 
     */
    @Override
    public void init(int idReleve) {
	this.idReleve = String.valueOf(idReleve);
	cpt = 1;
    }
    
    
    /**
     * 
     * @return 
     */
    @Override
    public String lignePliage()	{
	return MEF_Outils.espace(idReleve.length(), '-') + idReleve + MEF_Outils.RC + MEF_Outils.RC;
    }
    
    public String lignePliageEnteteCL() {
	String deb = "------V-----------------0";
	String id  = "(" + idReleve;
	String fin = MEF_Outils.espace(deb.length() + id.length(), '-');
	
	return deb + fin + id + MEF_Outils.RC;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String lignePliageMvt()  { 
	return MEF_Outils.espace(idReleve.length(), '-') + idReleve + MEF_Outils.RC;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String lignePage()	    { return MEF_Outils.SAUT_PAGE; }

    @Override
    public String lignePageMvt()    { return "\f"; }
    
    @Override
    public void addCpt()	{ cpt++; }
    
    @Override
    public void addCpt(int i)	{ cpt += i; }
    
    @Override
    public int  getCpt()	{ return cpt; }
}
