package exception;

public class ExceptionIdEnreg extends Exception {
    
    public ExceptionIdEnreg() {
	super("Erreur du numéro de l'engistrement");
    }
    
    public ExceptionIdEnreg(String message) {
	super(message);
    }
}
