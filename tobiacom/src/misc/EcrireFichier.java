package misc;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import verification.LogErreur;

public class EcrireFichier {
    
    public EcrireFichier() {
	
    }
    
    public void ecrireFichier(String nomFic, ArrayList<LogErreur> fic, String ficSortie, boolean supprimer) throws Exception, IOException {	
	//FileWriter writer = null;
	BufferedWriter writer = null;

	// on n'écrit rien pour les fichiers valides
	if(!fic.isEmpty()) {
	    try {
		File myFile = new File(ficSortie);

		//if file doesnt exists, then create it
		if(!myFile.exists()) {
		    myFile.createNewFile();
		}
		if(supprimer)
		    myFile.delete();
		

		//writer = new FileWriter(new File(ficSortie), true);
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ficSortie, true), "iso-8859-1"));

		writer.write("================================================\n");
		writer.write(nomFic + "\n");
		writer.write("================================================\n");
		Iterator<LogErreur> iterator = fic.iterator();
		while(iterator.hasNext()) {
		    writer.write(iterator.next().toString() + "\n");
		}	    
		writer.write("================================================\n\n");
	    }
	    catch(IOException e) {
		System.out.println(e.getMessage());
	    }

	    catch(Exception e) {
		System.out.println(e.getMessage());
	    }
	    finally {
		if(writer != null)
		    writer.close();
	    }
	}
    }
    
    public void ecrireFichier(String fic, String ficSortie) throws IOException {
	FileWriter writer = null;
	
	// on n'écrit rien pour les fichiers valides
	if(!fic.isEmpty()) {
	    try {
		File myFile = new File(ficSortie);
		myFile.delete();
		
		writer = new FileWriter(new File(ficSortie), false);
		writer.write(fic);
	    }
	    catch(IOException e) {
		System.out.println(e.getMessage());
	    }
	    finally {
		if(writer != null)
		    writer.close();
	    }
	}
    }
}
