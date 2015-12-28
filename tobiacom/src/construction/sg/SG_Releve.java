package construction.sg;
import construction.Releve;
import main.InfoCentre;
import structure.SG_Structure;

public class SG_Releve extends Releve {

    public SG_Releve(int id) {
	super(id);
	extraitCompte = new SG_ExtraitCompte(); // à expliquer
    }
    
    /*
     * Ne marche pas encore le truc 'static', à creuser
     */
    @Override
    public void lireEnreg0(String ligne) {
	// Attention, il n'y pas forcément de caractère pour les messages (=> pas de substring)
	if(ligne.length() > 6) {
	    String foo = ligne.substring(SG_Structure.Message_D, ligne.length());
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
    
    @Override
    public void lireEnreg1(String ligne) {
	// Attention, il n'y pas forcément de caractère pour les messages (=> pas de substring)
	if(ligne.length() > 6) {
	    String foo = ligne.substring(SG_Structure.Message_D, ligne.length());
	    if(foo.length() > 0) {
		message += foo;
		if(InfoCentre.getMessage1().isEmpty())
		    InfoCentre.setMessage1(foo);
	    }
	    else
		message += "";
	}
	else
	    message += "";
    }
    
    @Override
    public void lireEnreg2(String ligne) {
	codeEtablissement = ligne.substring(SG_Structure.CodeEtab_D, SG_Structure.CodeEtab_F);
	codeGuichet       = ligne.substring(SG_Structure.CodeGuichet_D, SG_Structure.CodeGuichet_F);
	numCompte         = ligne.substring(SG_Structure.NumCompte_D, SG_Structure.NumCompte_F);
	raisonSociale     = ligne.substring(SG_Structure.RaisonSociale_D, SG_Structure.RaisonSociale_F).trim();
	nomGuichet        = ligne.substring(SG_Structure.NomGuichet_D, SG_Structure.NomGuichet_F).trim();
	codeMonnaie       = ligne.substring(SG_Structure.CodeMonnaie_D, SG_Structure.CodeMonnaie_F);
	typeCompte        = ligne.substring(SG_Structure.TypeCompte_D, SG_Structure.TypeCompte_F);
    }
    
    @Override
    public void lireEnreg3(String ligne) {
	titulaire = ligne.substring(SG_Structure.Titulaire_D, SG_Structure.Titulaire_F).trim();
	setAdresse(ligne);
    }

    @Override
    public void lireEnreg4(String ligne) {
	setAdresse(ligne);
    }

    @Override
    public void lireEnreg5(String ligne) {
	setAdresse(ligne);
    }

    @Override
    public void lireEnreg6(String ligne) {
	extraitCompte.setAncienSolde(ligne);
    }
    
    @Override
    public void lireEnreg7(String ligne) {
	extraitCompte.setOperation(ligne);
    }
    
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
	    this.adresse.add(adresse.substring(SG_Structure.Titulaire_D, SG_Structure.Titulaire_F).trim());
	
	    if(adresse.length() > SG_Structure.Titulaire_F) {
		adresseTmp = adresse.substring(SG_Structure.Adresse1_D, adresse.length()).trim();
	    
		if(!this.adresse.contains(adresseTmp))
		    this.adresse.add(adresseTmp);
	    }
	}
	// 
	else if(adresse.charAt(0) == '4') {
	    // 1ère partie
	    if(adresse.length() > SG_Structure.Adresse2_D && adresse.length() > SG_Structure.Adresse3_D) {
		adresseTmp = adresse.substring(SG_Structure.Adresse2_D, SG_Structure.Adresse2_F).trim();
		
		// on n'ajoute pas la ligne si elle contient le nom du titulaire
		if(!this.adresse.contains(adresseTmp))
		    this.adresse.add(adresseTmp);
		
		// 2ème partie
		if(adresse.length() > SG_Structure.Adresse3_F) {
		    // on n'ajoute pas la ligne si elle contient le nom du titulaire
		    
		    adresseTmp = adresse.substring(SG_Structure.Adresse3_D, SG_Structure.Adresse3_F).trim();
		    if(!this.adresse.contains(adresseTmp))
			this.adresse.add(adresseTmp);
		    
		    // 3ème partie
		    if(adresse.length() > SG_Structure.Adresse4_D) {
			// on n'ajoute pas la ligne si elle contient le nom du titulaire
			adresseTmp = adresse.substring(SG_Structure.Adresse4_D, adresse.length()).trim();
			
			if(!this.adresse.contains(adresseTmp))
			    this.adresse.add(adresseTmp);
		    }
		}
		else {
		    adresseTmp = adresse.substring(SG_Structure.Adresse3_D, adresse.length()).trim();
		    if(!this.adresse.contains(adresseTmp))
			this.adresse.add(adresseTmp);
		}
	    }
	    else {
		adresseTmp = adresse.substring(SG_Structure.Adresse2_D, adresse.length()).trim();
		if(!this.adresse.contains(adresseTmp))
		    this.adresse.add(adresseTmp);
	    }
	}
	// Adresse 5
	else {
	    this.adresse.add(adresse.substring(SG_Structure.Adresse5_D, adresse.length()).trim());
	}
    }
}