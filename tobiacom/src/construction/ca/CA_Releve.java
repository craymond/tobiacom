package construction.ca;

import construction.Operation;
import construction.Releve;
import main.InfoCentre;
import structure.CA_Structure;


/**
 * La classe CA_Releve n'hérite pas de Releve, car la lecture du modèle diffère beaucoup
 * trop. On fait un nouveau parser.
 * @author camilleraymond
 */
public class CA_Releve extends Releve {
    
    public CA_Releve(int id) {
	super(id);
	extraitCompte = new CA_ExtraitCompte();
    }
    
    @Override
    public void lireEnreg0(String ligne) {
	// numéro du compte
	numCompte = ligne.substring(CA_Structure.NumCompte_D, CA_Structure.NumCompte_F).trim();

	// message 0
	message = ligne.substring(CA_Structure.Message_D, ligne.length());
	//InfoCentre.setMessage0(message);
	InfoCentre.setMessage0("");
    }
    
    public void lireTypeCompte(String ligne) {
	// type compte (compte chèque, livret ...)
	typeCompte = ligne.substring(CA_Structure.TypeCompte_D, ligne.length());
    }
    
    @Override
    public void lireEnreg1(String ligne) {}
    
    @Override
    public void lireEnreg2(String ligne) {}
    
    
    /**
     * cas intéressant : C55017120201.txt
     * @param ligne 
     */
    @Override
    public void lireEnreg3(String ligne) {

	if(ligne.trim().length() > 0) {
	    // titulaire de la carte
	    
	    String foo;
	    if(ligne.trim().length() < CA_Structure.Titulaire_F) {
		foo = ligne.substring(CA_Structure.Titulaire_D, ligne.length()).trim();
	    }
	    else {
		foo = ligne.substring(CA_Structure.Titulaire_D, CA_Structure.Titulaire_F).trim();
	    }
	    
	    if(!foo.isEmpty()) {
		titulaire += foo + " ";
	    }

	    setAdresse(ligne);
	}
    }
    
    public void lireGuichet(String ligne) {
	nomGuichet = ligne.substring(CA_Structure.Guichet_D, CA_Structure.Guichet_F);
    }
    
    
    @Override
    public void lireEnreg4(String ligne) {
	// adresse2
	setAdresse(ligne);
    }
    
    @Override
    public void lireEnreg5(String ligne) {
	// adresse 3
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
	// POURQUOI -3, je ne sais pas!!
	if(adresse.length() > CA_Structure.Adresse1_D) {
	    //String adr = adresse.substring(CA_Structure.Adresse1_D - 3, adresse.length()).trim();
	    String adr = adresse.substring(CA_Structure.Adresse1_D, adresse.length()).trim();

	    this.adresse.add(adr);
	}
    }
    
    @Override
    public String toString() {
	String retour = 
		"==Releve=\n" +
		"=========\n" +
		"numCompte : " + numCompte + "\n" +
		"message   : " + message + "\n" +
		"typeCpt   : " + typeCompte + "\n" +
		"titulaire : " + titulaire + "\n" +
		"guichet   : " + nomGuichet + "\n" + 
		"========================" + "\n" + 
		"VieuxSolde: " + extraitCompte.getDateAncienSolde() + "(" + extraitCompte.getMontantAncienSoldeEUR() + ")\n" + 
		"NewSolde  : " + extraitCompte.getDateNouveauSolde() + "(" + extraitCompte.getMontantNouveauSoldeEUR() + ")";
	
	retour += "\n========================\n";

	
	for(Operation aOperation : extraitCompte.getOperations()) {
	    retour +=
		aOperation.getDate() + ":" + aOperation.getLibelle() + " = " + aOperation.getMontant() + "\n";	
	}

	return retour;
     }
}
