package exception;

public class ExceptionMvtMontant extends Exception {
    
    public ExceptionMvtMontant() {
	super("Erreur Libellé Montant");
    }
    
    public ExceptionMvtMontant(String message) {
	super(message);
    }
}
