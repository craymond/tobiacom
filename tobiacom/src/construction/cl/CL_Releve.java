package construction.cl;
import construction.Releve;
import structure.CL_Structure;
import main.InfoCentre;

public class CL_Releve extends Releve {
    private String typeExtrait;	    // 0 => Compte Courant, 1 => Compte Carte
    private String numCarte;
    private String telephone;
    private String codeAcces;
    private String cleRIB;
    private String dateTraitement;
    private String libelleCompte;
    private boolean suiteReleve;    // ce relevé est la suite du précédent ?
    
    /**
     * 
     * @param id 
     */
    public CL_Releve(int id) {
	super(id);
	extraitCompte = new CL_ExtraitCompte();
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg0(String ligne) {
	message = ligne.substring(CL_Structure.Message_D).trim();
	if(InfoCentre.getMessage0().isEmpty())
	    InfoCentre.setMessage0(message);
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg1(String ligne) {
	String message1 = ligne.substring(CL_Structure.Message_D).trim();
	message += message1;
	if(InfoCentre.getMessage1().isEmpty())
	    InfoCentre.setMessage1(message1);
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg2(String ligne) {
	codeEtablissement = ligne.substring(CL_Structure.CodeEtab_D, CL_Structure.CodeEtab_F);
	codeGuichet       = ligne.substring(CL_Structure.CodeGuichet_D, CL_Structure.CodeGuichet_F);
	numCompte         = ligne.substring(CL_Structure.NumCompte_D, CL_Structure.NumCompte_F);
	typeExtrait       = ligne.substring(CL_Structure.TypeExtrait_D, CL_Structure.TypeExtrait_F);
	numCarte          = ligne.substring(CL_Structure.NumCarte_D, CL_Structure.NumCarte_F);
	raisonSociale     = ligne.substring(CL_Structure.RaisonSociale_D, CL_Structure.RaisonSociale_F).trim();
	nomGuichet        = ligne.substring(CL_Structure.NomGuichet_D, CL_Structure.NomGuichet_F).trim();
	telephone         = ligne.substring(CL_Structure.Telephone_D, CL_Structure.Telephone_F);
	codeAcces         = ligne.substring(CL_Structure.CodeAcces_D, CL_Structure.CodeAcces_F);
	cleRIB            = ligne.substring(CL_Structure.CleRIB_D, CL_Structure.CleRIB_F);
	dateTraitement    = ligne.substring(CL_Structure.DateTraitement_D, CL_Structure.DateTraitement_F);
    }

    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg3(String ligne) {
	// si le type est "courant", la partie CL_Structure.Titulaire est vide
	//	=> le titulaire se trouve alors dans la partie CL_Structure.Adresse1
	// si le type est "carte", le titulaire se trouve dans sa partie
	//	=> et l'adresse1 est aussi à sa place
	
	// le nom du titulaire peut se trouver à la place de l'adresse1 et/ou vide
	String isTitulaire = ligne.substring(CL_Structure.Titulaire_D, CL_Structure.Titulaire_F).trim();
	if(isTitulaire.length() > 0)
	    titulaire = isTitulaire;
	else
	    titulaire = ligne.substring(CL_Structure.Adresse1_D, CL_Structure.Adresse1_F).trim();
	
	// si le titulaire est à la place de l'adresse 1, on ne fait rien
	String isAdresse1 = ligne.substring(CL_Structure.Adresse1_D, CL_Structure.Adresse1_F).trim();
	if(!isTitulaire.equals(isAdresse1)) {
	    setAdresse(ligne);
	}
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg4(String ligne) {
	setAdresse(ligne);
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg5(String ligne) {
	setAdresse(ligne);
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg6(String ligne) {
	if(typeExtrait.equals("1")) {
	    libelleCompte = ligne.substring(CL_Structure.AncienSolde_Libelle_D, CL_Structure.AncienSolde_Libelle_F);
	}
	else
	    libelleCompte = "";
	
	extraitCompte.setAncienSolde(ligne);
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg7(String ligne) {
	extraitCompte.setOperation(ligne);
    }
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg8(String ligne) {
	extraitCompte.setNouveauSolde(ligne);
    }
    
    /**
     * 
     * @param adresse 
     */
    public void setAdresse(String adresse) {
	// attention, adresse de l'enreg3 n'existe pas toujours
	if(adresse.charAt(0) == '3') {
	    this.adresse.add(adresse.substring(CL_Structure.Adresse1_D, CL_Structure.Adresse1_F).trim());
	    
	}
	else if(adresse.charAt(0) == '4') {
	    this.adresse.add(adresse.substring(CL_Structure.Adresse2_D, CL_Structure.Adresse2_F).trim());
	    this.adresse.add(adresse.substring(CL_Structure.Adresse3_D, CL_Structure.Adresse3_F).trim());
	}
	else if(adresse.charAt(0) == '5') {
	    this.adresse.add(adresse.substring(CL_Structure.Adresse4_D, CL_Structure.Adresse4_F).trim());
	    this.adresse.add(adresse.substring(CL_Structure.Adresse5_D, CL_Structure.Adresse5_F).trim());
	}
	else {
	    System.out.println("Problème adresse CL : " + adresse);
	}
	
    }
    

    public void setSuiteReleve(boolean b) { suiteReleve = b; }
    public boolean getSuiteReleve()       { return suiteReleve; }

    // Getters
    public String getCleRIB()		{ return cleRIB; }
    public String getCodeAcces()	{ return codeAcces; }
    public String getDateTraitement()	{ return dateTraitement; }
    public String getNumCarte()		{ return numCarte; }
    public String getTelephone()	{ return telephone; }
    public String getTypeExtrait()	{ return typeExtrait; }
    public String getLibelleCompte()	{ return libelleCompte; }
}
