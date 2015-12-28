package exception;

public class ExceptionLibelle extends Exception {
    public ExceptionLibelle(String message) {
	super(message);
    }
    
    public ExceptionLibelle() {
	super("Erreur Libellé Mouvement");
    }
}
