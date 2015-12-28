package verification;

public class LogErreur {
    public static final int PRIORITE_MAX  = 2; // erreur fichier bizarre
    public static final int PRIORITE_HIGH = 1; // erreur d'enregistrement
    public static final int PRIORITE_MIN  = 0; // erreur mineure
    
    private int iLigne;
    private String ligne;
    private String message;
    private int priorite; // 0 = mineur, 1 = majeur
    
    public LogErreur(int iLigne, String ligne, String message) {
	this.iLigne = iLigne;
	this.ligne = ligne;
	this.message = message;
	this.priorite = PRIORITE_MIN;
    }
    
    public LogErreur(int iLigne, String ligne, String message, int priorite) {
	this.iLigne = iLigne;
	this.ligne = ligne;
	this.message = message;
	this.priorite = priorite;
    }
    
    @Override
    public String toString() {
	return "(" + iLigne + ") " + message + " --- " + ligne.trim();
    }

    public int getiLigne() { return iLigne; }
    public void setiLigne(int iLigne) { this.iLigne = iLigne; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public int getPriorite() { return priorite; }
    public void setPriorite(int priorite) { this.priorite = priorite; }
}
