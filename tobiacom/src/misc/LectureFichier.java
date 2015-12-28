package misc;
import java.io.*;
import java.util.ArrayList;


/**
 * 
 * @author camilleraymond
 */
public class LectureFichier { 
    private ArrayList<String> ficBanque;
    
    /**
     * 
     */
    public LectureFichier() {
        ficBanque = new ArrayList<String>();
    }

    /**
     * Lecture avec un BufferedReader
     * @param fileName 
     */
    public ArrayList<String> lireFichier(String fileName) {
	ficBanque.clear();
	BufferedReader reader = null;
	String line;
	
	try {
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "iso-8859-1"));
	    
	    while((line = reader.readLine()) != null) {
		this.ficBanque.add(line.toUpperCase());
	    }
	}
	catch(FileNotFoundException e) {
	    System.out.println("Fichier Introuvable : " + fileName);
	    e.getMessage();	    
	}
	catch (IOException e) {
	    System.out.println("Problème de lecture : " + fileName);
	    e.getMessage();
	}
	finally {
	    if (reader!=null) {
		try {
		    reader.close();
		}
		catch(IOException e) {
		}
	    }
	}
	return ficBanque;
    }

    
    /**
     * 
     * @param fileName chemin + nom du fichier
     * @param lireRepertoire lit-on l'ensemble des fichiers du répertoire ou seulement le fichier sélectionné ?
     * @return 
     */
    public ArrayList<String> lireFichierCA(String fileName, boolean lireRepertoire) {
	// on veut lire tous les fichiers du répertoire	
	if(lireRepertoire) {
	    File fichier = new File(fileName);

	    // ensemble des fichiers du répertoire
	    File repertoire = new File(fichier.getParent());	// extraction du chemin
	    
	    File[] fichiers = repertoire.listFiles();
	    
	    if(fichiers != null) {
		// ensemble des fichiers concaténés
		ArrayList<String> ficConcatene = new ArrayList<String>();

		for(File aFic : fichiers) {
		    // on ne lit pas les dossiers ni fichiers cachés pour éviter les fichiers de merde
		    if(aFic.isFile() && !aFic.isHidden()) {			
			ficConcatene.addAll(this.lireFichier(aFic.getAbsolutePath()));
		    }
		    
		}
		
		return ficConcatene;
	    }
	}
	
	// on ne veut qu'un fichier
	// pas de else, car si fichiers == null, on vient ici
	return lireFichier(fileName);
    }
    
    
    
    
    
    /**
     * 
     * @param fic
     * @return 
     */
    public ArrayList<String> lireFichierFromString(String fic) {
	String line;
	BufferedReader reader = new BufferedReader(new StringReader(fic));
	ficBanque.clear();
	
	try {
	    while((line = reader.readLine()) != null) {
		ficBanque.add(line);
	    }
	}
	catch(IOException e) {
	    System.out.println("Erreur LireFichierFromString: " + e.getMessage());
	}
	finally {
	    if(reader != null) {
		try {
		    reader.close();
		}
		catch(IOException e) {
		    System.out.println("Erreur .close()");
		}
	    }
	}
	

	return ficBanque;
    }
    
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getFicBanque() { return ficBanque; }
}