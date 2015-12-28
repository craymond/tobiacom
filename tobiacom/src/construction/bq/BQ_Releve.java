package construction.bq;
import construction.Releve;
import main.InfoCentre;
import structure.BQ_Structure;

public class BQ_Releve extends Releve {
    /**
     * 
     * @param id 
     */
    public BQ_Releve(int id) {
	super(id);
	extraitCompte = new BQ_ExtraitCompte();
    }


    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg0(String ligne) {
	// Attention, il n'y pas forcément de caractère pour les messages (=> pas de substring)
	if(ligne.length() > 6) {
	    String foo = ligne.substring(BQ_Structure.Message_D, ligne.length());
	    if(foo.length() > 0) {
		message = foo;
	    }
	    else
		message = "";
	}
	else
	    message = "";

	if(InfoCentre.getMessage0().isEmpty())
	    InfoCentre.setMessage0(message);
    }


    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg1(String ligne) {
	// Attention, il n'y pas forcément de caractère pour les messages (=> pas de substring)
	String foo = "";
	
	if(ligne.length() > 6) {
	    foo = ligne.substring(BQ_Structure.Message_D, ligne.length());
	    if(foo.length() > 0) {
		message += foo;
		
		if(InfoCentre.getMessage1().isEmpty())
		    InfoCentre.setMessage1(message);
	    }
	}
	else
	    message += foo;
    }
    
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg2(String ligne) {
	codeEtablissement = ligne.substring(BQ_Structure.CodeEtab_D, BQ_Structure.CodeEtab_F);
	codeGuichet       = ligne.substring(BQ_Structure.CodeGuichet_D, BQ_Structure.CodeGuichet_F);
	numCompte         = ligne.substring(BQ_Structure.NumCompte_D, BQ_Structure.NumCompte_F);
	raisonSociale     = ligne.substring(BQ_Structure.RaisonSociale_D, BQ_Structure.RaisonSociale_F).trim();
	nomGuichet        = ligne.substring(BQ_Structure.NomGuichet_D, BQ_Structure.NomGuichet_F).trim();
	codeMonnaie       = ligne.substring(BQ_Structure.CodeMonnaie_D, BQ_Structure.CodeMonnaie_F);
	if(ligne.length() > BQ_Structure.TypeCompte_D) {
	    typeCompte = "";
	    typeCompte        = ligne.substring(BQ_Structure.TypeCompte_D, BQ_Structure.TypeCompte_F);
	}
    }
    
    
    /**
     * 
     * @param ligne 
     */
    @Override
    public void lireEnreg3(String ligne) {
	if(ligne.trim().length() <= BQ_Structure.Adresse1_D) {
	    titulaire = ligne.substring(BQ_Structure.Titulaire_D, ligne.length());
	}
	else {
	    titulaire = ligne.substring(BQ_Structure.Titulaire_D, BQ_Structure.Titulaire_F);
	}
	
	setAdresse(ligne);
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
	String adresseTmp;
	
	// on construit l'adresse à l'enregistrement 3
	if(adresse.charAt(0) == '3') {
	    if(adresse.length() > BQ_Structure.Adresse1_D) {
		this.adresse.add(adresse.substring(BQ_Structure.Titulaire_D, BQ_Structure.Titulaire_F).trim());
		
		adresseTmp = adresse.substring(BQ_Structure.Adresse1_D, adresse.length()).trim();
		if(!this.adresse.contains(adresseTmp))
		    this.adresse.add(adresseTmp);
	    }
	    else {
		this.adresse.add(adresse.substring(BQ_Structure.Titulaire_D, adresse.length()).trim());
	    }
	}
	// enregistrement 4
	else if(adresse.charAt(0) == '4') {
	    if(adresse.length() < BQ_Structure.Adresse2_F) {
		this.adresse.add(adresse.substring(BQ_Structure.Adresse2_D, adresse.length()).trim());
	    }
	    else {
		this.adresse.add(adresse.substring(BQ_Structure.Adresse2_D, BQ_Structure.Adresse2_F).trim());
		
		// adresse 3 si existant
		if(adresse.length() > BQ_Structure.Adresse3_D) {
		    this.adresse.add(adresse.substring(BQ_Structure.Adresse3_D, adresse.length()).trim());
		}
	    }
	}
	// enregistrement 5
	else {
	    this.adresse.add(adresse.substring(BQ_Structure.Adresse5_D, adresse.length()).trim());
	}
    }   
}