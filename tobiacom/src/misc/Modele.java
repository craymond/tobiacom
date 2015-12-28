package misc;

public class Modele {
    public static final String SG = "SG";
    public static final String CL = "CL";
    public static final String BQ = "BQ";
    public static final String CA = "CA";

    public static String ficToModele(String fic) {
	String sub = fic.substring(0, 2);

	if(sub.equals(SG))
	    return "SG";
	else if(sub.equals(CL))
	    return "CL";
	// le nom des fichiers CA comportent 12 caractères sans l'extension, et commence par "C"
	else if(sub.charAt(0) == 'C' && fic.length() >= 12)
	    return "CA";
	else
	    return "BQ";
    }
}
