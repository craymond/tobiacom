package verification;
import exception.ExceptionIdEnreg;
import exception.ExceptionMvtMontant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificationOutils {
    // en static pour ne compiler à chaque fois la regex
    public static Pattern pNum     = Pattern.compile("([^\\p{Digit}])");
    public static Pattern pAlphNum = Pattern.compile("([^\\p{Graph}\\p{Blank}])");
    
    // liste des caractères non autorisés mais remplaçables
    // cf caracteres_corriges.xlsx
    private static final Integer[] charOKs = { 128, 163, 176, 180, 192, 193, 194, 199, 200, 201, 203, 218 };
    public  static final ArrayList<Integer> listeCharOK = new ArrayList<Integer>(Arrays.asList(charOKs));	
    
    /**
     * Expliquer que ça doit arrêter l'analyse
     * @param idAttendu
     * @param idRecu
     * @throws ExceptionIdEnreg 
     */
    public static void verifIdEnreg(String idAttendu, String idRecu) throws ExceptionIdEnreg {
	if(!idAttendu.equals(idRecu))
	    throw new ExceptionIdEnreg("Erreur du numéro de l'engistrement (Attendu: " + idAttendu + ")");
    }
    
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    public static void verifNumerique(String ligne) throws Exception {
	Matcher m = pNum.matcher(ligne);
	if(m.find()) {	    
	    throw new Exception("Caractère numérique interdit: " + m.group(1));
	}
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    public static void verifAlphaNumerique(String ligne) throws Exception {		
	// docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.html
	// Ce pattern analyse :
	//  [a-z], [A-Z], [0-9],
	//  [!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]
	//  [ \t] 

	Matcher m = pAlphNum.matcher(ligne);
	if(m.find()) {
	    char[] group = { m.group(1).charAt(0) };
	    int intGroup = Character.codePointAt(group, 0);

	    if(!listeCharOK.contains(intGroup)) {
		throw new Exception("Caractère interdit: " + intGroup);
	    }
	    // le code point 128 correspond à plusieurs choses:
	    //	- au caractère ° dans N(128) ...
	    //	- au caractère € pour les prix
	    else if(intGroup == 128) {
		int indexOf = ligne.indexOf(m.group());
		char charAtPrev;
		
		if(indexOf - 1 >= 1) {
		    charAtPrev = ligne.charAt(indexOf - 1);
		    if(charAtPrev != 'N' && !Character.isDigit(charAtPrev)) {
			throw new Exception("Caractère interdit: " + intGroup);
		    }
		}
	    }
	}
    }
    
    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    public static void verifCodeMonnaie(String ligne) throws Exception {
	// le code monnaie n'est pas toujours là, mais cette info n'est pas importante
	if(!(ligne.equals("EUR") || ligne.equals("FRF")) && !ligne.trim().isEmpty())
	    throw new Exception("Code Monnaie incorrect : " + ligne + " (Attendu: EUR ou FRF)");
    }

    /**
     * 
     * @param ligne
     * @throws Exception 
     */
    public static void verifTypeCompte(String ligne) throws Exception {
	if(!(ligne.equals(" ") || ligne.equals("0") || ligne.equals("1")))
	    throw new Exception("Type Monnaie incorrect : " + ligne + " (Attendu: 0 ou 1)");
    }

    /**
     * Vérification du signe d'un montant
     * @param ligne
     * @throws Exception 
     */
    public static void verifSigne(String ligne) throws Exception {
	if(!(ligne.equals("+") || ligne.equals("-")))
	    throw new Exception("Signe Montant incorrect : " + ligne + " (Attendu: - ou +)");
    }
    
    
    /**
     * Vérification du montant, avec la détection de caractère alphanumérique
     * On créé cette méthode pour lever une exception personnalisée, pour permettre
     * de traiter l'erreur dans le catch() de la classe Verification.
     * 
     * @param ligne
     * @throws ExceptionMvtMontant 
     */
    public static void verifMontant(String ligne) throws ExceptionMvtMontant {
	Matcher m = pNum.matcher(ligne);
	if(m.find()) {	    
	    throw new ExceptionMvtMontant("Erreur dans le montant: " + m.group(1));
	}
    }

    
    
    /**
     * Pour le Crédit Lyonnais
     * 0 = compte, 1 = carte
     * @param ligne
     * @throws Exception 
     */
    public static void verifTypeExtrait(String ligne) throws Exception {
	if(!(ligne.equals("0") || ligne.equals("1")))
	    throw new Exception("Type Extrait incorrect : " + ligne + " (Attendu: 0 ou 1)");
    }
    
    /**
     * Pour le Crédit Lyonnais
     * 000 = FRF, 879 = EUR
     * @param ligne
     * @throws Exception 
     */
    public static void verifCodeDevise(String ligne) throws Exception {
	if(!(ligne.equals("000") || ligne.equals("879")))
	    throw new Exception("Code devise incorrect : " + ligne + " (Attendu: 000 ou 879)");
    }
    
    /**
     * Pour le Crédit Lyonnais
     * Trouver son utilité ...
     * @param ligne
     * @throws Exception 
     */
    public static void verifIndicateurSolde(String ligne) throws Exception {
	if(!(ligne.equals("0") || ligne.equals("1")))
	    throw new Exception("Indicateur de nouveau solde erroné : " + ligne);
    }
}