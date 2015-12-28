package miseEnForme;

public class MEF_PliageCL {

    private int cpt;		    // compteur de ligne
    private String idReleve;	    // id du relevé
    private String typeExtrait;	    // 0 => Courant, 1 => Carte
    private int numPage;	    // numéro de la page
    private boolean entete;	    // s'agit-t'il du pliage de l'entête des mouvements
    
    /**
     * 
     */
    public MEF_PliageCL() {
	cpt = 1;
	idReleve = "";
	numPage = 1;
	entete = false;
    }

    
    /**
     * 
     * @param idReleve 
     */
    public void init(int idReleve, String typeExtrait, boolean suiteLibelle) {
	this.idReleve = String.valueOf(idReleve);
	this.typeExtrait = typeExtrait;

	if(suiteLibelle) {
	    numPage++;	// on continue à ajouter des pages
	}
	else {	    
	    numPage = 1;	// reinitialise le numéro de la page
	}
	
	// le 1er pliage est pour l'entête des mouvements (que pour le type compte)
	if(typeExtrait.equals("0"))
	    entete = true;
	cpt = 1;
    }
    

    /**
     * 
     * @return 
     */
    public boolean isPliage() {
	cpt++;
	return (cpt == 15 || cpt == 31);
    }


    /**
     * 
     * @return 
     */
    public String getPliage() {
	// ligne de pliage
	if(cpt == 15) {
	    if(entete) {
		cpt += 1;
		entete = false;
		return lignePliageEntete();
	    }
	    else {
		cpt += 1;	 // ligne de pliage + retour à la ligne = 2 lignes supplémentaires
		return lignePliage();
	    }	    
	}
	// saut de page
	else if(cpt == 31) {
	    cpt = 5;
	    numPage++;	// on ajoute une page au compteur
	    return lignePage();
	}	    
	else
	    return "";
    }


    
    /**
     * 
     * @return 
     */
    public String lignePliage()	{
	// +1 à cause de la marque Antoine
	return MEF_Outils.espace(idReleve.length() + 1, '-') + "(" + idReleve + MEF_Outils.RC;
    }
    
    public String lignePliageEntete() {	
	String deb = "------V-----------------0";
	String id  = "(" + idReleve;
	String fin = MEF_Outils.espace(deb.length() + id.length(), '-');

	return deb + fin + id + MEF_Outils.RC;
    }

    
    /**
     * 
     * @return 
     */
    public String lignePage()	    { 
	String page = "(" + numPage;
	String lignePage = MEF_Outils.SAUT_PAGE;
	lignePage += "::::::!:::::::::::::::::.::::::::::  "   + page + MEF_Outils.RC;
	lignePage += " DATE L     MONTANT     $ CONTRE-VALEUR" + MEF_Outils.RC;
	lignePage += "      L     EN EUROS    $   EN FRANCS  " + MEF_Outils.RC;
	lignePage += "::::::H:::::::::::::::::J::::::::::::::" + MEF_Outils.RC;

	return lignePage;
    }


    public void setCpt(int i)	{ cpt = i; }
    public void addCpt()	{ cpt++; }
    public void addCpt(int i)	{ cpt += i; }
    public int  getCpt()	{ return cpt; }    
    public int  getNumPage()	{ return numPage; }
}
