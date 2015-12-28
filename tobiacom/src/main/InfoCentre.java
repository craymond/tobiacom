package main;

import construction.LectureConstruction;
import construction.Releve;
import construction.bq.BQ_LectureConstruction;
import construction.ca.CA_LectureConstruction;
import construction.cl.CL_LectureConstruction;
import construction.sg.SG_LectureConstruction;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import misc.*;
import miseEnForme.MiseEnForme;
import miseEnForme.bq.BQ_MiseEnForme;
import miseEnForme.ca.CA_MiseEnForme;
import miseEnForme.cl.CL_MiseEnForme;
import miseEnForme.sg.SG_MiseEnForme;
import verification.LogErreur;
import verification.Verification;
import verification.banque.BQ_Verification;
import verification.banque.CL_Verification;
import verification.banque.SG_Verification;

public class InfoCentre {
    public static String marqueurNewCA = "NEW FIC CA";	// marqueur pour séparer les fichiers pour CA
    public static String marqueurFinCA = "FIN CA";	// marqueur fin fichier CA
    
    private static String repBanque;	// dossier des fichiers banque
    private static String repBAN;   // dossier de sauvegarde des .BAN
    private static String repETI;   // dossier de sauvegarde des .ETI
    private static String pathFic;  // chemin (avec le nom du fichier) du fichier banque
    private static String nomFic;   // nom du fichier banque
    private static String modele;   // nom du modèle
    private static boolean lireRepCA;	// veut-on lire tous les fichiers du dossier
    private static boolean ficValide;	// le fichier banque est-il valide ?
    private static ArrayList<String> ficBanqueOriginal;	// fichier banque sans aucune modif
    private static ArrayList<LogErreur> log;
    private static String message0;
    private static String message1;
    private static String messageMEF;	// message mis en forme dans F3
    private static boolean messageValide;
    private static ArrayList<Releve> releves;
    private static int nbReleves;
    private static String pathFicBAN;
    private static String pathFicETI;
    private static boolean inverserEtiquette;	// inversion de l'ordre des étiquettes ?
    private static String ficTranscrit;
    private static String ficEtiquette;
    private static boolean ficEcrit;
    private static int nbPages;

    /**
     * 
     */
    public InfoCentre() throws ClassNotFoundException {
	setOptionsDefault();
    }

    /**
     * 
     */
    public static void initialisation() {
	setPathFic("");
	setNomFic("");
	setModele("");
	setLireRepCA(true);
	setFicValide(false);
	ficBanqueOriginal = null;
	log = null;
	setMessage0("");
	setMessage1("");
	setMessageValide(false);
	releves = null;
	nbReleves = 0;
	setPathFicBAN("");
	setPathFicETI("");
	setFicTranscrit("");
	setFicEtiquette("");
	setFicEcrit(false);
	nbPages = 0;
    }

    /**
     * 
     * @throws ClassNotFoundException 
     */
    public static void setOptionsDefault() throws ClassNotFoundException {
	// déserialization des options
	if(new File("options_tobiacom.txt").isFile()) {
	    try {
		FileInputStream fis = new FileInputStream("options_tobiacom.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);		
		Options options = (Options)ois.readObject();
		ois.close();
		
		setRepBanque(options.getPathBanque());
		setRepBAN(options.getPathBAN());
		setRepETI(options.getPathETI());
	    }
	    catch(IOException e) {
		System.out.println("Problème de serialization (InfoCentre)\n" + e.getMessage());
		
		setRepBanque("");
		setRepBAN("");
		setRepETI("");
	    }
	}
	else {
	    setRepBanque("");
	    setRepBAN("");
	    setRepETI("");
	}
    }

    /**
     * 
     */
    public static void lireFicBanque() {
	LectureFichier lireFicBanque = new LectureFichier();
	if(InfoCentre.getModele().equals(Modele.CA))
	    ficBanqueOriginal = new ArrayList<String>(lireFicBanque.lireFichierCA(pathFic, InfoCentre.getLireRepCA()));
	else
	    ficBanqueOriginal = new ArrayList<String>(lireFicBanque.lireFichier(pathFic));
    }

    
    /**
     * 
     */
    public static void verifFicBanque(ArrayList<String> fic) {
	if(fic == null)
	    InfoCentre.lireFicBanque();
	else
	    setFicBanque(fic);

	// Vérification
	// On ne vérifie pas le CA, ils sont toujours ok
	if(modele.equals(Modele.CA)) {
	    setFicValide(true);
	}
	else {
	    Verification verif;
	    if(modele.equals(Modele.SG))
		verif = new SG_Verification(ficBanqueOriginal);
	    else if(modele.equals(Modele.BQ))
		verif = new BQ_Verification(ficBanqueOriginal);
	    else
		verif = new CL_Verification(ficBanqueOriginal);

	    verif.verif();
	    setLog(verif.getLog());
	    setFicValide(verif.isFicValide());
	}
    }
    
    /**
     * 
     */
    public static void construire() {
	LectureConstruction construction;

	if(modele.equals(Modele.SG))
	    construction = new SG_LectureConstruction(ficBanqueOriginal);
	else if(modele.equals(Modele.BQ))
	    construction = new BQ_LectureConstruction(ficBanqueOriginal);
	else if(modele.equals(Modele.CA))
	    construction = new CA_LectureConstruction(ficBanqueOriginal);
	else
	    construction = new CL_LectureConstruction(ficBanqueOriginal);

	setReleves(construction.lireModele());
	setNbReleves(releves.size());
    }
    
    /**
     * 
     */
    public static void miseEnForme() {
	MiseEnForme mef;
	
	if(modele.equals(Modele.SG))
	    mef = new SG_MiseEnForme();
	else if(modele.equals(Modele.BQ))
	    mef = new BQ_MiseEnForme();
	else if(modele.equals(Modele.CA))
	    mef = new CA_MiseEnForme();
	else
	    mef = new CL_MiseEnForme();
	
	mef.miseEnForme(releves);
	setFicTranscrit(mef.getFicTranscrit());
	setFicEtiquette(mef.getFicEtiquette());
	// nombre de pages du fichier transcrit
	// -1 car on finit toujours le fichier par "\f", mais il ne compte pas
    	setNbPages(CountMatches.regexOccur(mef.getFicTranscrit(), "\f") - 1);
    }
    
    
    public static void ecrireFichier() throws IOException {
	try {
	    EcrireFichier writer = new EcrireFichier();
	    writer.ecrireFichier(ficTranscrit, pathFicBAN);
	    writer.ecrireFichier(ficEtiquette, pathFicETI);
	    setFicEcrit(true);
	}
	catch(IOException e) {
	    // propagation de l'erreur
	    throw new IOException(e.getMessage());
	}
    }
    
    

    // Setters
    public static void setRepBanque(String s) { InfoCentre.repBanque = s; }
    public static void setRepBAN(String s) { InfoCentre.repBAN = s; }
    public static void setRepETI(String s) { InfoCentre.repETI = s; }
    public static void setPathFic(String pathFic) { InfoCentre.pathFic = pathFic; }
    public static void setNomFic(String nomFic)   { InfoCentre.nomFic = nomFic.toUpperCase(); }
    public static void setModele(String modele)   { InfoCentre.modele = modele; }
    public static void setLireRepCA(boolean b)    { InfoCentre.lireRepCA = b; }
    public static void setFicValide(boolean b)    { InfoCentre.ficValide = b; }
    public static void setFicBanque(ArrayList<String> fic) { InfoCentre.ficBanqueOriginal = fic; }
    public static void setLog(ArrayList<LogErreur> l) { InfoCentre.log = l; }
    public static void setMessage0(String message) { InfoCentre.message0 = message; }
    public static void setMessage1(String message) { InfoCentre.message1 = message; }
    public static void appendMessage(String message) { InfoCentre.message0 += message; }
    public static void setMessageValide(boolean b)    { InfoCentre.messageValide = b; }
    public static void setMessageMEF(String message) { InfoCentre.messageMEF = message; }
    public static void setReleves(ArrayList<Releve> r) { InfoCentre.releves = r; }
    public static void setNbReleves(int i) { InfoCentre.nbReleves = i; }
    public static void setPathFicBAN(String path) { InfoCentre.pathFicBAN = path; }
    public static void setPathFicETI(String path) { InfoCentre.pathFicETI = path; }
    public static void setInverserEtiquette(boolean b) { InfoCentre.inverserEtiquette = b; }
    public static void setFicTranscrit(String fic) { InfoCentre.ficTranscrit = fic; }
    public static void setFicEtiquette(String fic) { InfoCentre.ficEtiquette = fic; }
    public static void setFicEcrit(boolean b) { InfoCentre.ficEcrit = b; }
    public static void setNbPages(int i) { InfoCentre.nbPages = i; }
    
    // Getters
    public static String getRepBanque() { return InfoCentre.repBanque; }
    public static String getRepBAN() { return InfoCentre.repBAN; }
    public static String getRepETI() { return InfoCentre.repETI; }
    public static String getPathFic() { return InfoCentre.pathFic; }
    public static String getNomFic()  { return InfoCentre.nomFic; }
    public static String getModele()  { return InfoCentre.modele; }
    public static boolean getLireRepCA() { return InfoCentre.lireRepCA; }
    public static boolean isFicValide() { return InfoCentre.ficValide; }
    public static ArrayList<String> getFicBanque() { return InfoCentre.ficBanqueOriginal; }
    public static ArrayList<LogErreur> getLog() { return InfoCentre.log; }
    public static String getMessage0() { return InfoCentre.message0; }
    public static String getMessage1() { return InfoCentre.message1; }
    public static boolean isMessageValide() { return InfoCentre.messageValide; }
    public static String getMessageMEF() { return InfoCentre.messageMEF; }
    public static ArrayList<Releve> getReleves() { return InfoCentre.releves; }
    public static int getNbReleves() { return InfoCentre.nbReleves; }
    public static String getPathFicBAN() { return InfoCentre.pathFicBAN; }
    public static String getPathFicETI() { return InfoCentre.pathFicETI; }
    public static boolean getInverserEtiquette() { return InfoCentre.inverserEtiquette; }
    public static String getFicTranscrit() { return InfoCentre.ficTranscrit; }
    public static String getFicEtiquette() { return InfoCentre.ficEtiquette; }
    public static boolean isFicEcrit() { return InfoCentre.ficEcrit; }
    public static int getNbPages() { return InfoCentre.nbPages; }
}