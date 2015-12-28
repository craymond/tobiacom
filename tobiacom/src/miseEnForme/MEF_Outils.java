package miseEnForme;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import verification.VerificationOutils;

public class MEF_Outils {
    public static final int LG_LIGNE = 39;		// longueur d'une ligne transcrite
    public static final String RC = "\r\n";		// retour chariot
    public static final String SAUT_PAGE = "\f\r\n";    // saut de page

    private static Pattern pDigit    = Pattern.compile("[0-9]+");
    private static Pattern pEspaces  = Pattern.compile("( {2,})"); // détection espaces conséctifs
    private static Pattern pAntoine  = Pattern.compile("\\([0-9]+([A-Z]+)[(0-9]*");	// détecter des lettres dans des nombres
    private static Pattern pSymboles = Pattern.compile("([./*)(=%])");  // symbôles à remplacer


    /**
     * Retourne le nombre d'espace manquant pour compléter une chaîne.
     * On connaît la longueur d'une ligne (= LG_LIGNE)
     * On connaît la longueur des caractères déjà présents sur la ligne (nbCharPresent)
     * Le nombre d'espaces manquants vaut alors : nbEspacesManquants = LG_LIGNE - nbCharPresent
     * @param nbCharPresent
     * @param charSeparation caractère marquant la séparation
     * @return 
     */
    public static String espace(int nbCharPresent, char charSeparation) {
	String espace = "";
	int nbEspaceManquans = LG_LIGNE - nbCharPresent;
	for(int i = 0; i < nbEspaceManquans; i++) {
	    espace += charSeparation;
	}
	return espace;
    }
    
    
    /**
     * 
     * @param nb
     * @param c
     * @return 
     */
    public static String genChar(int n, char c) {
	String ligne = "";
	for(int i = 0; i < n; i++) {
	    ligne += c;
	}
	return ligne;
    }
    
    /**
     * Retourne une date mise en forme (format : JJ'MM ou JJ'MM'AA ou JJ'MM'AAAA)
     * @param date La date au format brut
     * @return 
     */
    public static String date(String date) {
	if(date.length() > 1) {
	    StringBuilder dateMEF = new StringBuilder(date);
	    
	    // Pour CA, la date est de la forme "JJ MM"
	    if(dateMEF.charAt(2) == ' ') {
		dateMEF.setCharAt(2, '\'');
	    }
	    // Pour les autres, elle est de la forme JJMM ou JJMMAA
	    else {
		dateMEF.insert(2, "'");	    // séparation JJMM

		// pour les dates avec les années, il faut séparer MM'AA
		if(date.length() > 4)
		    dateMEF.insert(5, "'"); // 5 car on a déjà ajouté un séparateur
	    }

	    return dateMEF.toString();
	}
	else
	    return date;
    }
    
    
    /**
     * On supprime dans la date le séparateur des éléments d'une date (ie JJ, MM, AAAA)
     * @param date
     * @return 
     */
    public static String dateCA(String date, String separateur) {
	StringBuilder aS = new StringBuilder(date);
	aS.deleteCharAt(5);
	aS.deleteCharAt(2);

	return aS.toString();
    }
    

    /**
     * 
     * @param montant
     * @param signe
     * @return 
     */
    public static String montant(String montant, String signe) {
	int montantLength = montant.length();    	
	//StringBuilder montantMEF = new StringBuilder(montant);
        String montantMEF;
        
        ///////////////////////////////////////
        // montant > 1,00
        if(montantLength < 3) {
            montantMEF = "0," + montant;

            // le montant est égal à 0, on ajoute une décimale : 0,0 => 0,00
            if(montant.equals("0")) {
                montantMEF = montantMEF + "0";
            }
        }
        // montant > 1,00
        else {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            
            // partie entière du montant
            String pEntiere = formatter.format(Long.valueOf(montant.substring(0, montant.length() - 2)));
            pEntiere = pEntiere.replace(',', '\'');
            // partie centimes du montant
            String pCentime = montant.substring(montant.length() - 2);
            
            //
            montantMEF = pEntiere + "," + pCentime;
        }
        
        // ajout du signe négatif
        if(signe.equals("-") && !montant.equals("0")) {
            montantMEF = signe + montantMEF;
        }
        
        return montantMEF;
        
        ///////////////////////////////////////
        
        
        
        
        
        
        /*if(montantLength > 7) {
            // séparateurs
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);           
            DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
            
            // pas les centimes
            String pasCents = formatter.format(Long.valueOf(montant.substring(0, montant.length() - 2)));
            String pasCentsClean = pasCents.replace(',', '\'');
            
            // les centimes
            String centimes = montant.substring(montant.length() - 2);
                      
            System.out.println(montantMEF);
            System.out.println(pasCentsClean + "," + centimes);
            System.out.println("===========");
        }
	
	// si le montant est supérieur à 999,99 => on peut procéder au découpage (centaines...)
	if(montantLength > 5) {
	    int iMarqueur = 0; // quand iMarqueur % 3 == 0, on insère le séparateur
	    // on parcourt à l'envers, -2 pour ne pas prendre en compte les centimes
	    for(int i = montantLength - 2; i > 0; i--) {
		if(iMarqueur % 3 == 0) {
		    montantMEF.insert(i, "'");
		    iMarqueur = 0;
		}
		iMarqueur++;
	    }
	    // séparation des centimes
	    // l'algo précédent ajoute un séparateur à montant.length() -2), on remplace par la ","
	    montantMEF.setCharAt(montantLength - 1, ',');
	}
	// le montant est inférieur à 1000,00 => seulement besoin de séparer les centimes
	else if(montantLength <= 5 && montantLength > 2) {
	    montantMEF.insert(montantLength - 2, ',');
	}
	// insertion d'un '0' si le montant est inférieur à 1
	else {
	    montantMEF.insert(0, "0,");
	    // si le montant est égal à 0, on ajoute une décimale : 0,0 => 0,00
	    if(montant.equals("0"))
		montantMEF.append("0");
	}

	// on rajoute le signe "-"
	if(signe.equals("-") && !montant.equals("0"))
	    montantMEF.insert(0, signe);

	return montantMEF.toString();*/
    }
    
    
    /**
     * Préparation de la mise en forme du montant.
     * Pour le CA, les montants sont "-xx.xxx,xx"
     * Or la fonction "montant" attend une date en centimes et le signe séparément
     * @param montant
     * @param signe
     * @return 
     */
    public static String montantCA(String montant, String signe) {
	StringBuilder montantB = new StringBuilder(montant);
	
	// suppression du signe
	if(signe.equals("-")) {
	    montantB.deleteCharAt(0);
	}
	
	// suppression de la virgule
	montantB.deleteCharAt(montantB.length() - 3);
	
	// suppression du sépateur "." entre les groupes numériques
	int indexOf = montantB.indexOf(".");
	while(indexOf > 0) {
	    montantB.deleteCharAt(indexOf);
	    indexOf = montantB.indexOf(".");
	}
	
	return MEF_Outils.montant(montantB.toString(), signe);
    }
    
    
    /**
     * Un libellé ne peut pas contenir deux espaces consécutifs
     * 
     * @param libelle 
     */
    public static String libelle(String libelle, boolean symbole) {
	StringBuilder libelleMEF = new StringBuilder(libelle);
	Matcher m;

	// remplacement des symbôles si besoin
	if(symbole) {
	    m = pSymboles.matcher(libelleMEF);

	    while(m.find()) {
		//libelleMEF = libelleMEF.replaceAll("([" + m.group() + "])", remplacerChar(libelle, m.group()));
		libelleMEF = libelleMEF.replace(m.start(), m.end(), remplacerChar(libelle, m.group()));
	    }

	    // on remplace les caractères non affichables, avec leurs unicode
	    m = VerificationOutils.pAlphNum.matcher(libelleMEF);
	    
	    while(m.find()) {
		char[] group = { m.group(1).charAt(0) };
		int intGroup = Character.codePointAt(group, 0);

		if(VerificationOutils.listeCharOK.contains(intGroup)) {
		    libelleMEF = libelleMEF.replace(m.start(), m.end(), remplacerCharNonGraph(libelle, m.group().charAt(0)));
		    //libelleMEF = libelleMEF.replaceAll("([" + m.group() + "])", remplacerCharNonGraph(libelle, m.group(1).charAt(0)));
		}
	    }
	    
	    // marque Antoine
	    libelleMEF = new StringBuilder(MEF_Outils.marqueAntoine(libelleMEF.toString()));
	}


	// suppression des double espaces
	String libelleMEF2 = libelleMEF.toString();
	m = pEspaces.matcher(libelleMEF2.trim());
	
	if(m.find())
	    libelleMEF2 = m.replaceAll(" ");
	
	return libelleMEF2;
    }
    
    /**
     * 
     * @param libelle
     * @return 
     */
    public static String libelle(String libelle) {
	return MEF_Outils.libelle(libelle, true);
    }
    
    
    /**
     * 
     * @param c
     * @return 
     */
    public static String remplacerChar(String ligne, String c) {
	char charPrev = ' ';
	char charNext = ' ';
	int indexOf = ligne.indexOf(c);

        if(indexOf > 0)
	    charPrev = ligne.charAt(indexOf - 1);
	if(indexOf < ligne.length() - 1)
	    charNext = ligne.charAt(indexOf + 1);

        switch(c.charAt(0)) {
	    // pour les dates, on remplace par "-"
	    // sinon on remplace par "'"
	    case '.' : 	
	        if(!Character.isDigit(charPrev) && !Character.isDigit(charNext))
		    return "'";
		else
		    return "-";
	    
	    case '/' : return "-";
	    case '(' : return "<";
	    case ')' : return ">";
	    case '=' : return "\"";
	    case '%' : return "0>J ";
	    default  : return c;
	}
    }
    
    
    public static String remplacerCharNonGraph(String ligne, char c) {
	char[] group = { c };
	int intGroup = Character.codePointAt(group, 0);

	int indexOf = ligne.indexOf(c);
	char charAtPrev = ' ';

	if(indexOf - 1 >= 1)
	    charAtPrev = ligne.charAt(indexOf - 1);
	
        switch(intGroup) {
	    case 128 : 
		if(charAtPrev == 'N')
		    return "O";
		else if(Character.isDigit(charAtPrev))
		    return " E";
		else
		    return "";
	    case 163 : return "0>J ";
	    case 176 : return "O";
	    case 180 : return "A";
	    case 192 : return "A";
	    case 193 : return "A";
	    case 194 : return "A";
	    case 199 : return "C";
	    case 200 : return "\\";
	    case 201 : return "/";
	    case 203 : return "6";
	    case 218 : return "E";
	    default  : return c + "";
	}
    } 

    /**
     * Ajoute le caractère "(" pour indiquer qu'il s'agit d'un chiffre ou d'un nombre.
     * - Si le caractère précédent est 'N', il faut rajouter '0' pour faire comprendre que c'est "N°"
     * - Il faut ajouter un espace après le nombre s'il n'y en a pas
     * Attention :
     *	- 1   s'écrit (1
     *  - 123 s'écrit (123 et non pas (1(2(3
     * @param libelle
     * @return 
     */
    public static String marqueAntoine(String libelle) {
	StringBuilder libelleMEF = new StringBuilder(libelle);

	// insertion du marqueur Antoine
	Matcher m = pDigit.matcher(libelle);
	
	for(int i = 0; m.find(); i++) {
	    // on ne met pas de marque pour NO6L ( <=> NOËL)
	    if(m.group().equals("6") && (m.start() - 2 > 0) &&
		libelleMEF.charAt(m.start() - 2) == 'N' &&
		libelleMEF.charAt(m.start() - 1) == 'O')
	    {		
		// on ne fait sauf compenser l'incrémentation de i car on n'a rien fait
		i--;
	    }
	    
	    else if(m.start() - 1 > 0 && libelleMEF.charAt(m.start()) == ',') {
		i--;
	    }
	    
	    else if(m.end() < libelleMEF.length() - 1 &&
		libelleMEF.charAt(m.start()) == '0' && 
		libelleMEF.charAt(m.start() + 1) == '>')
		{
		    // on ne fait sauf compenser l'incrémentation de i car on n'a rien fait
		    i--;
		}
		    
	    
	    
	    // Si 'Espace'+'N'+Espace, on rajoute 'O' pour signifier en braille 'N°'
	    else if(m.start() > libelleMEF.length() && libelleMEF.charAt(m.start() - 2) == 'N' && 
		    libelleMEF.charAt(m.start() - 1) == ' ' &&
		    libelleMEF.charAt(m.start() - 3) == ' ')
		{
		    libelleMEF.insert(m.start() - 1, "O (");
		    libelleMEF.deleteCharAt(m.start() + 2);
		}
		// + i, car on ajoute un nouveau caractère à chaque fois, et m.start() a été
		// calculé avant ces insertions, du coup yavait un décalage
		else
		    libelleMEF.insert(m.start() + i, "(");
	}


	// séparation [0-9][A-Z][0-9] => [0-9] [A-Z][0-9]
	m = pAntoine.matcher(libelleMEF);
	for(int i = 0; m.find(); i++) {
	    libelleMEF.insert(m.start(1), " ");
	}

	return libelleMEF.toString();
    }
    
    
    /**
     * 
     * @param libelle
     * @return 
     */
    public static String marqueAntoineCompte(String libelle) {
	StringBuilder libelleMEF = new StringBuilder(libelle);
	
	// insertion du marqueur Antoine
	Matcher m = pDigit.matcher(libelle);
	
	for(int i = 0; m.find(); i++) {
	    libelleMEF.insert(m.start() + i, "(");
	}
	
	// insertion des espaces autour de la lettre
	m = pAntoine.matcher(libelleMEF);
	if(m.find()) {
	    libelleMEF.insert(m.start(1), " ");
	    libelleMEF.insert(m.end(1) + 1, " ");   // +1 car on a inséré un char avant
	}
	
	return libelleMEF.toString();
    }
    
    /**
     * La césure est effectuée dès que le libellé est plus grand que la place disponible
     * sur une ligne.
     * PlaceDispo = LG_LIGNE - Date - Espace (Date-Libelle) - Espace (Libelle-Montant) - Montant
     * Soit on retourne seulement à la ligne (SAUT), soit on coupe le mot (COUPURE)
     * 
     * http://www.bertrandboutin.ca/Folder_151_Grammaire/P_b_division.htm
     * 
     * V = Voyelle, C = Consonne
     * 
     * Règle de césure :
     * 
     *	- Pas de césure
     *	    o voyelle
     *	    o chiffre
     *	    o marque Antoine
     *	    o caractère unique après la césure
     *	    o voyelle + lettre 'N'
     *	    o entre G et N
     *	    o paire de consonnes dont la 2nd est un L ou un R, et la 1ère n'est pas un L ou un R
     *	    o paire de consonnes dont la 2nd est un H, et la 1ère est un C, un P ou un T
     *
     *	- Césure :
     *	    o retour à la ligne lors d'un espace ou d'un tiret
     *	    o entre deux syllabes; une syllabe = consomme + voyelle(s)
     *	    o entre deux consonnes
     *	    o quand consonnes > 2, césure entre l'avant-dernière et la dernière (cf exceptions)


     *	- détection d'une paire de consonne (!estVoyelle(c) && !estVoyelle(cMoins1)) impossible de couper dans ces cas-là
     *


     *	3. C'est une exception à la règle 2. Il ne peut y avoir de césure entre certaines 
     * consonnes : 
     *	4. C'est une autre exception à la règle 2. Il ne peut 
     *	5. Encore une exception. Il ne peut pas y avoir de 
     *	6. Une césure ne peut pas survenir avant la première voyelle d'un mot. 
     * 
     * @param libelle
     * @return 
     */
    private static String cesureBoulot(String libelle, int placeOccupe, int nbEspaceDebut) {
	StringBuilder libelleMEF = new StringBuilder(libelle);
	
	int placeDispo = LG_LIGNE - placeOccupe;
	
	// césure si besoin
	if(placeDispo < libelleMEF.length()) {
	    boolean cesureTrouve = false;
	    int curseur = placeDispo;
	    String espaceDebut = MEF_Outils.genChar(nbEspaceDebut, ' ');
	    
	    while(!cesureTrouve && curseur > 3) {
		char charAt      = libelleMEF.charAt(curseur);
		char charAtPrev  = libelleMEF.charAt(curseur - 1);
		char charAtPrev2 = libelleMEF.charAt(curseur - 2);
		char charAtNext  = (curseur + 1 < libelleMEF.length() ? libelleMEF.charAt(curseur + 1) : '§');
	    
	    
		// CESURE PAR RETOUR A LA LIGNE
		if(charAt == ' ' || charAt == '-') {
		    //libelleMEF.insert(curseur, MEF_Outils.RC + "     ");
		    //libelleMEF.insert(curseur, MEF_Outils.RC + espaceDebut);
		    libelleMEF.replace(curseur, curseur + 1, MEF_Outils.RC + espaceDebut);
		    //libelleMEF.replace(curseur, curseur + 1, MEF_Outils.RC);
		    
		    
		    cesureTrouve = true;
		}
		
		
		// CESURE PAR COUPURE
		else if(
			curseur     != placeDispo &&
			charAtPrev2 != ' ' && 
			charAtPrev  != ' ' &&
			charAtNext  != ' ' &&
			charAt      != '(' &&
			!isDigit(charAt)   &&
			(charAt != '\'' && charAtPrev != '\'' && charAtPrev2 != '\'' && charAtNext != '\'')
		)
		{
		    // VCV  - une consonne placée entre deux voyelles, on coupe après la première voyelle
		    if(isVoyelle(charAtPrev) && isConsonne(charAt) && isVoyelle(charAtNext)) {
			//libelleMEF.insert(curseur, "-" + MEF_Outils.RC + "      ");
			libelleMEF.insert(curseur, "-" + MEF_Outils.RC + espaceDebut);
			cesureTrouve = true;
		    }

		    // VCCV - deux consonnes placées entre deux voyelles, on coupe entre les deux consonnes
		    else if(isVoyelle(charAtPrev2) && isConsonne(charAtPrev) && isConsonne(charAt) && isVoyelle(charAtNext)) {
			if(
			    !(charAt == 'L' && (charAtPrev == 'B' || charAtPrev == 'C' || charAtPrev == 'F' || charAtPrev == 'G' || charAtPrev == 'P')) &&
			    !(charAt == 'R' && (charAtPrev == 'B' || charAtPrev == 'C' || charAtPrev == 'D' || charAtPrev == 'F' || charAtPrev == 'G' || charAtPrev == 'T' || charAtPrev == 'V')) &&
			    !(charAt == 'H' && (charAtPrev == 'C' || charAtPrev == 'P' || charAtPrev == 'T'))
			)
			{
			    //libelleMEF.insert(curseur, "-" + MEF_Outils.RC + "      ");
			    libelleMEF.insert(curseur, "-" + MEF_Outils.RC + espaceDebut);
			    cesureTrouve = true;
			}

			// on coupe les consonnes doublées
			else if(isConsonne(charAt) && charAt == charAtPrev) {
			    //libelleMEF.insert(curseur, "-" + MEF_Outils.RC + "      ");
			    libelleMEF.insert(curseur, "-" + MEF_Outils.RC + espaceDebut);
			    cesureTrouve = true;
			}
			
			
			// on coupe juste avant si on ne rentre pas dans les cas précédents
			else {
			    //libelleMEF.insert(curseur - 1, "-" + MEF_Outils.RC + "      ");
			    libelleMEF.insert(curseur - 1, "-" + MEF_Outils.RC + espaceDebut);
			    cesureTrouve = true;
			}
		    }
		    
		    // CCC  - trois consonnes consécutifs, on coupe entre la deuxième et troisième
		    else if(isConsonne(charAt) && isConsonne(charAtPrev) && isConsonne(charAtPrev2)) {
			if(libelleMEF.charAt(curseur - 3) == ' ') {}
			
			//
			else if(
			    charAt == 'L' && (charAtPrev == 'B' || charAtPrev == 'C' || charAtPrev == 'F' || charAtPrev == 'G' || charAtPrev == 'P') ||
			    charAt == 'R' && (charAtPrev == 'B' || charAtPrev == 'C' || charAtPrev == 'D' || charAtPrev == 'F' || charAtPrev == 'G' || charAtPrev == 'H' || charAtPrev == 'T' || charAtPrev == 'V') ||
			    charAt == 'H' && (charAtPrev == 'C' || charAtPrev == 'N' || charAtPrev == 'P' || charAtPrev == 'T') ||
			    charAt == 'R' && charAtPrev == 'P'
			) 
			{
			    //libelleMEF.insert(curseur - 1, "-" + MEF_Outils.RC + "      ");
			    libelleMEF.insert(curseur - 1, "-" + MEF_Outils.RC + espaceDebut);
			    cesureTrouve = true;
			}
			    
			else {
			    //libelleMEF.insert(curseur, "-" + MEF_Outils.RC + "      ");
			    libelleMEF.insert(curseur, "-" + MEF_Outils.RC + espaceDebut);
			    cesureTrouve = true;
			}
		    }
		}
		curseur--;
	    }
	}
	return libelleMEF.toString();
    }

    
    /**
     * 
     * @param libelle
     * @param placeOccupe
     * @param nbEspaceDebut
     * @param symbole
     * @return 
     */
    public static String cesure(String libelle, int placeOccupe, int nbEspaceDebut, boolean symbole) {
	if(!symbole)
	    return MEF_Outils.cesureBoulot(MEF_Outils.libelle(libelle, false), placeOccupe, nbEspaceDebut);
	else
	    return MEF_Outils.cesureBoulot(MEF_Outils.libelle(libelle), placeOccupe, nbEspaceDebut);
	
    }


    /**
     * 
     * @param libelle
     * @param placeOccupe
     * @param nbEspaceDebut
     * @return 
     */
    public static String cesure(String libelle, int placeOccupe, int nbEspaceDebut) {
	return MEF_Outils.cesureBoulot(MEF_Outils.libelle(libelle), placeOccupe, nbEspaceDebut);
    }


    /**
     * 
     * @param libelle
     * @param placeOccupe
     * @param nbEspaceDebut
     * @return 
     */
    public static String cesureRecursive(String libelle, int placeOccupe, int nbEspaceDebut, boolean symbole) {
	String partie1;
	String partie2 = "";
	int coupure;


	partie1 = cesure(libelle, placeOccupe, nbEspaceDebut, symbole);
	
	if(partie1.indexOf(MEF_Outils.RC) != -1) {
	    coupure = partie1.indexOf(MEF_Outils.RC);
	    
	    partie2  = partie1.substring(coupure);
	    partie2  = cesure(partie2, placeOccupe, nbEspaceDebut, false);
	    
	    partie1 = partie1.substring(0, coupure);
	}

	return partie1 + partie2;
    }
    

    /**
     * 
     * @param c
     * @return 
     */
    public static boolean isDigit(char c) {	
	return Character.isDigit(c);
    }


    /**
     * Note : a ≠ A, faire gaffe pour le test
     * @param c
     * @return 
     */
    public static boolean isVoyelle(char c) {
	return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y');
    }
    
    /**
     * 
     * @param c
     * @return 
     */
    public static boolean isConsonne(char c) {
	return !(isVoyelle(c) || c == ' ');
    }
}