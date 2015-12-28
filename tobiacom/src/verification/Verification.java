package verification;
import exception.ExceptionIdEnreg;
import exception.ExceptionMvtMontant;
import java.util.ArrayList;
import java.util.Iterator;
import verification.banque.CL_Verification;

public abstract class Verification {
    private ArrayList<String> fic;	// fichier banque
    private ArrayList<LogErreur> log;	// log des erreurs
    private ArrayList<String> ficCorrige;   // fichier corrigé
    private Iterator<String> iterator;	// iterator sur le fichier banque
    private int iLigne;			// numéro de la ligne lue

    public Verification(ArrayList<String> fic) {
	this.fic = fic;
	this.ficCorrige = new ArrayList<String>();
	this.log = new ArrayList<LogErreur>();
    }
    
    /**
     * 
     */
    public void verif() {
	iterator = fic.iterator();
	iLigne = 0;
	String ligneTmp = "";	// ligne lue courante
	int nbReleves = 0;	// nombre de relevés dans le fichier
	int nbEnreg = 0;	// nombre d'enregistrements dans le fichier
	boolean aLire = true;	// marqueur pour savoir si on doit lire une ligne
	String ancienSolde;
	String nouveauSolde;
	ArrayList<String> operations = new ArrayList<String>();

	// On peut vérifier que le fichier fasse au moins 8 lignes (total des enregistrements obligatoires)
	if(fic.size() < 8) {
	    log.add(new LogErreur(1, "Fichier", "Le fichier est bizarre"));
	}
	else {	    
	    for(int i = 0; iterator.hasNext(); i++) {
		// Bloc try-catch pour les erreurs d'id d'enregistrements
		// il prend toute la boucle car s'il y a une erreur, on arrête l'analyse
		try {
		    // Pour CL, on ne doit pas lire la ligne dans le cas où le fichier
		    // passe de 7 à 0 ou 2
		    if(aLire)
			ligneTmp = lireLigne();
		    else
			aLire = true;

		    if(ligneTmp.trim().length() <= 1)
			break;

		    // on vérifie que l'enregistrement est valide
		    char idEnreg  = ligneTmp.charAt(0);
		    if(!(idEnreg == '0' || idEnreg == '2' || idEnreg == '9')) {
			throw new ExceptionIdEnreg("Erreur du numéro de l'engistrement");
		    }


		    // Enregistrement 9
		    // certains fichiers ne se finissent pas vraiment au 9
		    if(ligneTmp.charAt(0) == '9') {
			if(!iterator.hasNext())
			    break;
			else {
			    String sauvegarde = ligneTmp;
			    ligneTmp = iterator.next();

			    if(ligneTmp.trim().length() <= 1 || (ligneTmp.charAt(0) != '0' && ligneTmp.charAt(0) != '2')) {
				ligneTmp = sauvegarde;
				break;
			    }
			}
		    }
			

		    // Nouveau relevé
		    nbReleves++;


		    // Enregistrement 0 (facultatif pour les n+1 relevés)		
		    if(ligneTmp.charAt(0) == '0') {
			try {
			    verifEnreg0(ligneTmp);
			}
			catch(Exception e) {
			    log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
			}
			finally {
			    ficCorrige.add(ligneTmp);
			}

			nbEnreg++;
			ligneTmp = lireLigne();

			// Enregistrement 1 (facultatif et n'existe pas sans l'enreg 0)
			if(ligneTmp.charAt(0) == '1') {
			    try { 
				verifEnreg1(ligneTmp);
			    }
			    catch(Exception e) { 
				log.add(new LogErreur(iLigne, ligneTmp, e.getMessage())); 
			    }
			    finally {
				ficCorrige.add(ligneTmp);
			    }

			    nbEnreg++;
			    ligneTmp = lireLigne(); // lecture pour l'enregistrement 2
			}
			// l'id ne peut être que 2
			else if(ligneTmp.charAt(0) != '2')
			    throw new ExceptionIdEnreg("Erreur du numéro de l'engistrement (Attendu: 2)");
		    }
		    // si l'id n'est pas égal à 0, alors il ne peut valoir que 2, sinon erreur
		    else if(ligneTmp.charAt(0) != '2') {
			throw new ExceptionIdEnreg("Erreur du numéro de l'engistrement (Attendu: 2)");
		    }


		    // Enregistrement 2 (la ligne est déjà lu)
		    VerificationOutils.verifIdEnreg("2", ligneTmp.substring(0, 1));
		    try {
			verifEnreg2(ligneTmp);
		    }
		    catch(Exception e) {
			log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
		    }
		    finally {
			ficCorrige.add(ligneTmp);
		    }

		    nbEnreg++;
		    ligneTmp = lireLigne();

		    // Enregistrement 3
		    VerificationOutils.verifIdEnreg("3", ligneTmp.substring(0, 1));
		    try {
			verifEnreg3(ligneTmp);
		    }
		    catch(Exception e) {
			log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
		    }
		    finally {
			ficCorrige.add(ligneTmp);
		    }

		    nbEnreg++;
		    ligneTmp = lireLigne();

		    // Enregistrement 4 (facultatif)
		    if(ligneTmp.charAt(0) == '4') {
			try {
			    verifEnreg4(ligneTmp);
			}
			catch(Exception e) { 
			    log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
			}
			finally {
			    ficCorrige.add(ligneTmp);
			}

			nbEnreg++;
			ligneTmp = lireLigne();	// lecture pour l'enregistrement 5
		    }


		    // Enregistrement 5 (la ligne est déjà lu)
		    VerificationOutils.verifIdEnreg("5", ligneTmp.substring(0, 1));
		    try {
			verifEnreg5(ligneTmp);
		    }
		    catch(Exception e) { 
			log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
		    }
		    finally {
			ficCorrige.add(ligneTmp);
		    }

		    nbEnreg++;
		    ligneTmp = lireLigne();

		    // Enregistrement 6
		    VerificationOutils.verifIdEnreg("6", ligneTmp.substring(0, 1));
		    try {
			verifEnreg6(ligneTmp);
		    }
		    catch(Exception e) {
			log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
		    }
		    finally {
			ficCorrige.add(ligneTmp);
		    }

		    nbEnreg++;

		    // on garde l'ancien solde pour la vérification du nouveau solde
		    ancienSolde = ligneTmp;
		    
		    // on vide ici car on vérifie toujours le nouveau solde même s'il
		    // n'y a pas d'opérations
		    operations.clear();

		    ligneTmp = lireLigne();
		    // Enregistrements 7 (facultatif)
		    if(ligneTmp.charAt(0) == '7') {
			while(ligneTmp.charAt(0) == '7') {
			    try {
				verifEnreg7(ligneTmp);
			    }
			    catch(ExceptionMvtMontant e) {
				log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
				
				// mise à zéro du montant
				ligneTmp = razMontant(ligneTmp);
			    }
			    catch(Exception e) {
				log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
			    }
			    finally {
				ficCorrige.add(ligneTmp);
				
				// pour vérifier NouveauSolde = AncienSolde - Operations
				// finally => pour le sauvegarder même s'il y a une erreur
				operations.add(ligneTmp);
			    }
			    
			    nbEnreg++;
			    ligneTmp = lireLigne();
			    
			    // Des CL n'ont pas de 8, ils passent au 0 ou 2 directement
			    // Ces relevés concernent le type 'carte'
			    if(this instanceof CL_Verification) {
				char charAtCL = ligneTmp.charAt(0);
				if(!(charAtCL == '0' || charAtCL == '2' || charAtCL == '7' || charAtCL == '8')) {
				    throw new ExceptionIdEnreg("Erreur du numéro de l'engistrement (Attendu: 0, 2, 7 ou 8)");
				}
				// on ne lit pas la prochaine ligne, car il s'agit du
				// 0 ou 2, cf début de la boucle
				if(charAtCL == '0' || charAtCL == '2')
				    aLire = false;
			    }
			    // Pour les autres banques
			    else {
				if(!(ligneTmp.charAt(0) == '7' || ligneTmp.charAt(0) == '8')) {
				    throw new ExceptionIdEnreg("Erreur du numéro de l'engistrement (Attendu: 7 ou 8)");
				}
			    }
			}
		    }
		    else if(ligneTmp.charAt(0) != '8')
			throw new ExceptionIdEnreg("Erreur du numéro de l'engistrement (Attendu: 8)");


		    // Enregistrement 8 (la ligne est déjà lu)
		    if(ligneTmp.charAt(0) == '8') {
			VerificationOutils.verifIdEnreg("8", ligneTmp.substring(0, 1));
			try {
			    verifEnreg8(ligneTmp);
			}
			catch(Exception e) { 
			    log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
			}
			finally {
			    ficCorrige.add(ligneTmp);
			}

			nbEnreg++;

			nouveauSolde = ligneTmp;

			try {
			    verifSolde(ancienSolde, operations, nouveauSolde);
			}
			catch(Exception e) {
			    log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
			}
		    }
		}

		// interception de l'exception liée aux mauvais id des enregistrements
		catch(ExceptionIdEnreg e) {
		    log.add(new LogErreur(iLigne, ligneTmp, e.getMessage(), LogErreur.PRIORITE_HIGH));
		    break; // on arrête l'analyse car le fichier est invalide
		}
		catch(Exception e) {
		    System.out.println(ligneTmp + "\n" + e.getMessage());
		}
	    }

	    // début du BREAK
	    if(ligneTmp.length() >= 1) {
		// Enregistrement 9	
		try {
		    VerificationOutils.verifIdEnreg("9", ligneTmp.substring(0, 1));
		    //verifEnreg9(ligneTmp);

		    nbEnreg++;

		    //verifNbReleves(ligneTmp, nbReleves);
		    //verifNbEnreg(ligneTmp, nbEnreg);
		}
		catch(ExceptionIdEnreg e) {		
		    log.add(new LogErreur(iLigne, ligneTmp, e.getMessage(), LogErreur.PRIORITE_HIGH));
		}
		// exception liée aux verifNbReleves et verifNbEnreg
		catch(Exception e) {
		    log.add(new LogErreur(iLigne, ligneTmp, e.getMessage()));
		}
		finally {
		    ficCorrige.add(ligneTmp);
		}
	    }
	    else {
		System.out.println("j'ai été coupé");
	    }
	}
    }

    public abstract void verifEnreg0(String ligne) throws Exception;
    public abstract void verifEnreg1(String ligne) throws Exception;
    public abstract void verifEnreg2(String ligne) throws Exception;
    public abstract void verifEnreg3(String ligne) throws Exception;
    public abstract void verifEnreg4(String ligne) throws Exception;
    public abstract void verifEnreg5(String ligne) throws Exception;
    public abstract void verifEnreg6(String ligne) throws Exception;
    public abstract void verifEnreg7(String ligne) throws Exception;
    public abstract void verifEnreg8(String ligne) throws Exception;
    public abstract void verifEnreg9(String ligne) throws Exception;
    public abstract void verifSolde(String ancien, ArrayList<String> ope, String nouveau) throws Exception;
    public abstract void verifNbReleves(String fic, int calcule) throws Exception;
    public abstract void verifNbEnreg(String fic, int calcule) throws Exception;
    public abstract String razMontant(String ligne);

    /**
     * 
     * @return 
     */
    public String lireLigne() {
	iLigne++;
	return iterator.next();
    }
    
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getFicCorrige() {
	return this.ficCorrige;
    }
    
    
    /**
     * 
     * @param ligne 
     */
    public void setLog(LogErreur ligne) {
	log.add(ligne);
    }

    /**
     * 
     * @return 
     */
    public ArrayList<LogErreur> getLog() {
	return(log);
    }
    
    public boolean isFicValide() {
	return !(log.size() > 0);
    }
}