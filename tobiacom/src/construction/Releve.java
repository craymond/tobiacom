package construction;

import java.util.ArrayList;

public abstract class Releve {
    protected int id;
    protected String codeEtablissement = "";
    protected String message = "";
    protected String codeGuichet = "";
    protected String numCompte = "";
    protected String raisonSociale = "";
    protected String nomGuichet = "";
    protected String codeMonnaie = "";
    protected String typeCompte = "";
    protected String titulaire = "";
    protected ArrayList<String> adresse;
    protected ExtraitCompte extraitCompte = null;
    
    public Releve(int id) {
	this.id = id;
	adresse = new ArrayList<String>();
	message = "";
    }

    public abstract void lireEnreg0(String ligne);
    public abstract void lireEnreg1(String ligne);
    public abstract void lireEnreg2(String ligne);
    public abstract void lireEnreg3(String ligne);
    public abstract void lireEnreg4(String ligne);
    public abstract void lireEnreg5(String ligne);
    public abstract void lireEnreg6(String ligne);
    public abstract void lireEnreg7(String ligne);
    public abstract void lireEnreg8(String ligne);

    // Setter
    public void setId(int id) { this.id = id; }

    // Getter
    public int getId() { return id; }
    public ArrayList<String> getAdresse() { return adresse; }
    public String getCodeEtablissement() { return codeEtablissement; }
    public String getCodeGuichet() { return codeGuichet; }
    public String getCodeMonnaie() { return codeMonnaie; }
    public ExtraitCompte getExtraitCompte() { return extraitCompte; }
    public String getMessage() { return message; } 
    public String getNomGuichet() { return nomGuichet; }
    public String getNumCompte() { return numCompte; }
    public String getRaisonSociale() { return raisonSociale; }
    public String getTitulaire() { return titulaire; }
    public String getTypeCompte() { return typeCompte; }
}
