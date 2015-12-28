package miseEnForme;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.InfoCentre;
import misc.Modele;

public class MEF_Message {

    private static Pattern pMinuscule = Pattern.compile("([a-zäàéèêëïôöùüç0-9./*()])");
    private static Pattern pMajuscule = Pattern.compile("([A-ZÀÂÉÈÊËÏÖÔÙÜÇ])");
    private static Pattern pChar = Pattern.compile("([./*()=ÀÂÉÈÊËÏÖÔÙÜÇ])");
    private static HashMap<String, String[]> bq = new HashMap<String, String[]>();
    

    /**
     * si tout le mot n'est pas en majuscule, on ajoute # avant la lettre concernée
     * => xxxx#Xxx
     * 
     * si le mot est en majuscule et est composé au plus de trois lettres
     * => #X#X#X
     * 
     * si le mot est en majuscule et composé de plus de 3 lettres
     * => #XXXXXX
     * 
     * 
     * @param message
     * @return 
     */
    public static String message(String message) {
	StringTokenizer tokens = new StringTokenizer(message.trim());	// on découpe la chaîne en mot
	StringBuilder messageMEF = new StringBuilder();	    // message mis en forme
	StringBuilder foo;				    // mot courant
	Matcher m, m2;

	while(tokens.hasMoreTokens()) {
	    foo = new StringBuilder(tokens.nextToken());

	    // on regarde si le mot contient des minuscules
	    m = pMinuscule.matcher(foo);
	    if(m.find()) {
		m2 = pMajuscule.matcher(foo);

		if(m2.find()) {
		    for(int i = 0; i < foo.length(); i++) {
			if(Character.isUpperCase(foo.charAt(i))) {
			    foo.insert(i, "#");
			    i++;
			}
		    }
		}
	    }	    
	    // sinon on regarde la taille du mot
	    else if(foo.length() > 3) {
		foo.insert(0, "##");
	    }
	    else if(foo.length() <= 3) {
		int i = 0;
		while(i < foo.length()) {
		    if(i % 2 == 0)
			foo.insert(i, "#");
		    i++;
		}
	    }

	    messageMEF.append(foo.toString().toUpperCase()).append(" ");
	}

	remplacerChar(messageMEF);
	return messageMEF.toString();
    }
    

    private static final Pattern pSugg = Pattern.compile("(SG|CL|"
	    + "BASSET|BORDE|BPAL|BPATLA|BPAZ|BPBALM|BPCA|BPFC|BPMC|BPMETZ|BPPACA|BPRP|BPSO|BPT|BPVF|"
	    + "CA2SEV|CAAP|CAAQUI|CAAV|CABRIE|CACOTA|CACOU|CAFC|CAFINI|CAG|CALANG|CALOIR|CAMA|CANEST|"
	    + "CANORM|CANS|CAPERI|CAPOIT|CAVAL|CCP|CE44|CEAL|CEAP|CEAZUR|CEBFC|CELANG|CELDA|CELIMO|CELY|CENORM|CEPAR|CEPCSD|CERA|CETOU|"
	    + "CICES|CICIB|CICIC|CICLY|CICNO|CICO|CICOU|CICPAR|CICSO|CICTR|CMANJ|CMANT|CMANTI|CMBR|"
	    + "CMBRET|CMFED|CMFIN|CMMA|CMMC|CMNEU|CMOCE|CMSO|CNCL|CNFE|CNLC|CNP|"
	    + "CORSE|DIJON|GRENO|LILLE|LYON|MARS|MONTP|NANCY|NANTES|PARIS|RENNES|ROUEN|SDENIS|SOURCE|SS|STRASB|TOUL"
	    + ")");
    
    
    private static void initBqMessage() {
	bq = new HashMap<String, String[]>();
	bq.put("BASSET",    new String[]{"La Poste",		"#LA #POSTE"});
	bq.put("BORDE",	    new String[]{"La Poste Bordeaux",	"#LA #POSTE #BORDEAUX"});
	bq.put("BPAL",	    new String[]{"Banque Populaire Alsace", "#BANQUE #POPULAIRE #ALSACE"});
	bq.put("BPAZ",	    new String[]{"BP Côte d'Azur","#B#P #C4TE D'#AZUR"});
	bq.put("BPATLA",    new String[]{"BP Atlantique",	"#B#P #ATLANTIQUE"});
	bq.put("BPBALM",    new String[]{"BP Occitane",		"#B#P #OCCITANE"});
	bq.put("BPCA",	    new String[]{"BP Centre Atlantique",    "#B#P #CENTRE #ATLANTIQUE"});
	bq.put("BPFC",	    new String[]{"BP Franche-Compté",	"#B#P #FRANCHE-#COMT/"});
	bq.put("BPMC",	    new String[]{"BP Massif-Central",	"#B#P #MASSIF-#CENTRAL"});
	bq.put("BPMETZ",    new String[]{"BP Lorraine Champagne",   "#B#P #LORRAINE #CHAMPAGNE"});
	bq.put("BPPACA",    new String[]{"BP Côte d'Azur","#B#P #C4TE D'#AZUR"});
	bq.put("BPRP",	    new String[]{"BP Rives de Paris",	"#B#P #RIVES DE #PARIS"});
	bq.put("BPSO",	    new String[]{"BP Sud Ouest",	"#B#P #Sud #Ouest"});
	bq.put("BPT",	    new String[]{"La Banque Postale",	"#LA #BANQUE #POSTALE"});
	bq.put("BPVF",	    new String[]{"BP Val de France",	"#B#P #VAL DE #FRANCE"});
	bq.put("CA2SEV",    new String[]{"CA Deux Sèvres",	"#C#A #DEUX #S\\VRES"});
	bq.put("CAAP",	    new String[]{"CA Alpes Provence",	"#C#A #ALPES #PROVENCE"});
	bq.put("CAAQUI",    new String[]{"CA d'Aquitaine",	"#C#A D'#AQUITAINE"});
	bq.put("CAAV",	    new String[]{"CA Atlantique-Vendée",    "#C#A #ATLANTIQUE-#VEND/E"});
	bq.put("CABRIE",    new String[]{"CA Brie Picardie",	"#C#A #BRIE #PICARDIE"});
	bq.put("CACOTA",    new String[]{"CA Côtes d'Armor",	"#C#A #C4TES D'#ARMOR"});
	bq.put("CACOU",	    new String[]{"CA Centre Ouest",	"#C#A #CENTRE #OUEST"});
	bq.put("CAFC",	    new String[]{"CA Franche Comté",	"#C#A #FRANCHE #COMT/"});
	bq.put("CAFINI",    new String[]{"CA Finistère",	"#C#A #FINIST\\RE"});
	bq.put("CAG",	    new String[]{"CA Pyrénée-Gascogne", "#C#A #PYR/N/ES-#GASCOGNE"});
	bq.put("CALANG",    new String[]{"CA du Languedoc",	"#C#A DU #LANGUEDOC"});
	bq.put("CALOIR",    new String[]{"CA Centre Loire",	"#C#A #CENTRE #LOIRE"});
	bq.put("CAMA",	    new String[]{"CA Maine-Anjou",	"#C#A #MAINE-#ANJOU"});
	bq.put("CANEST",    new String[]{"CA Nord-Est",		"#C#A #NORD-#EST"});
	bq.put("CANORM",    new String[]{"CA Normandie",	"#C#A #NORMANDIE"});
	bq.put("CANS",	    new String[]{"CA Normandie-Seine",	"#C#A #NORMANDIE-#SEINE"});
	bq.put("CAPERI",    new String[]{"CA Périgord",		"#C#A #P/RIGORD"});
	bq.put("CAPOIT",    new String[]{"CA Touraine-Poitou",	"#C#A #TOURAINE-#POITOU"});
	bq.put("CAVAL",	    new String[]{"CA Val de France",	"#C#A #VAL DE #FRANCE"});
	bq.put("CCP",	    new String[]{"La Banque Postale",	"#LA #BANQUE #POSTALE"});
	bq.put("CE44",	    new String[]{"CE Bretagne-Pays de Loire",	"#C#E #BRETAGNE-#PAYS DE #LOIRE"});
	bq.put("CEAL",	    new String[]{"CE Alsace",		"#C#E #ALSACE"});
	bq.put("CEAP",	    new String[]{"CE Aquitaine Poitou",	"#C#E #AQUITAINE #POITOU"});
	bq.put("CEAZUR",    new String[]{"CE Côte d'Azur",	"#C#E #C4TE D'#AZUR"});
	bq.put("CEBFC",	    new String[]{"CE Bourgogne Franche Comté", "#C#E #BOURGOGNE #FRANCHE #COMT/"});
	bq.put("CELANG",    new String[]{"CE Languedoc Roussilon",	"#C#E #LANGUEDOC #ROUSSILLON"});
	bq.put("CELDA",	    new String[]{"CE Loire Drôme Ardèche",	"#C#E #LOIRE #DR4ME #ARD\\CHE"});
	bq.put("CELIMO",    new String[]{"CE Auvergne et Limousin",	"#C#E #AUVERGNE ET #LIMOUSIN"});
	bq.put("CELY",	    new String[]{"CE Lyon", "#C#E #LYON"});
	bq.put("CENORM",    new String[]{"CE Normandie",	"#C#E #NORMANDIE"});
	bq.put("CEPAR",	    new String[]{"CE Ile de France",	"#C#E #ILE DE #FRANCE"});
	bq.put("CEPCSD",    new String[]{"CE Provence Alpes Corse",	"#C#E #PROVENCE #ALPES #CORSE"});
	bq.put("CERA",	    new String[]{"CE Rhône Alpes",	"#C#E #RH4NE #ALPES"});
	bq.put("CETOU",	    new String[]{"CE Midi-Pyrénées",	"#C#E #MIDI-#PYR/N/ES"});
	bq.put("CICES",	    new String[]{"CIC Est",		"#C#I#C #EST"});
	bq.put("CICIB",	    new String[]{"CIC Iberbanco",	"#C#I#C #IBERBANCO"});
	bq.put("CICIC",	    new String[]{"CIC Industriel et Commercial",    "#C#I#C #INDUSTRIEL ET #COMMERCIAL"});
	bq.put("CICLY",	    new String[]{"Lyonnaise de Banque",	"#LYONNAISE DE #BANQUE"});
	bq.put("CICNO",	    new String[]{"CIC Nord Ouest",	"#C#I#C #NORD #OUEST"});
	bq.put("CICO",	    new String[]{"CIC Ouest",		"#C#I#C #OUEST"});
	bq.put("CICOU",	    new String[]{"CIC Ouest",		"#C#I#C #OUEST"});
	bq.put("CICPAR",    new String[]{"CIC Paris",		"#C#I#C #PARIS"});
	bq.put("CICSO",	    new String[]{"CIC Sud Ouest",	"#C#I#C #SUD #OUEST"});
	bq.put("CICTR",	    new String[]{"CIC Banque Transatlantique",	"#C#I#C #BANQUE #TRANSATLANTIQUE"});
	bq.put("CMANJ",	    new String[]{"CM Anjou",		"#C#M #ANJOU"});
	bq.put("CMANT",	    new String[]{"Crédit Mutuel Antilles Guyane", "#CR/DIT #MUTUEL #ANTILLES #GUYANE"});
	bq.put("CMANTI",    new String[]{"CM Antilles Guyane",	"#C#M #ANTILLES #GUYANE"});
	bq.put("CMBR",	    new String[]{"CM Bretagne",		"#C#M #BRETAGNE"});
	bq.put("CMBRET",    new String[]{"Crédit Mutuel Bretagne",  "#CR/DIT #MUTUEL #BRETAGNE"});
	bq.put("CMFED",	    new String[]{"Crédit Mutuel Fédéral", "#CR/DIT #MUTUEL #F/D/RAL"});
	bq.put("CMFIN",	    new String[]{"Crédit Mutuel Bretagne", "#CR/DIT #MUTUEL #BRETAGNE"});
	bq.put("CMMA",	    new String[]{"Crédit Mutuel Maine-Anjou", "#CR/DIT #MUTUEL #MAINE-#ANJOU"});
	bq.put("CMMC",	    new String[]{"Crédit Mutuel Massif Central", "#CR/DIT #MUTUEL #MASSIF #CENTRAL"});
	bq.put("CMNEU",	    new String[]{"Crédit Mutuel Nord Europe", "#CR/DIT #MUTUEL #NORD #EUROPE"});
	bq.put("CMOCE",	    new String[]{"Crédit Mutuel Océan", "#CR/DIT #MUTUEL #OC/AN"});
	bq.put("CMSO",	    new String[]{"Crédit Mutuel Sud-Ouest", "#CR/DIT #MUTUEL #SUD-#OUEST"});
	bq.put("CNCL",	    new String[]{"CE Centre Loire", "#C#E #CENTRE #LOIRE"});
	bq.put("CNFE",	    new String[]{"CE Nord France Europe", "#C#E #NORD #FRANCE #EUROPE"});
	bq.put("CNLC",	    new String[]{"CE Lorraine Champagne", "#C#E #LORRAINE #CHAMPAGNE"});
	bq.put("CNP",	    new String[]{"CE Picardie", "#C#E #PICARDIE"});
	bq.put("CORSE",	    new String[]{"La Poste Corse", "#LA #POSTE #CORSE"});
	bq.put("DIJON",	    new String[]{"La Poste Dijon", "#LA #POSTE #DIJON"});
	bq.put("GRENO",	    new String[]{"La Poste Grenoble", "#LA #POSTE #GRENOBLE"});
	bq.put("LILLE",	    new String[]{"La Poste Lille", "#LA #POSTE #LILLE"});
	bq.put("LYON",	    new String[]{"La Poste Lyon", "#LA #POSTE #LYON"});
	bq.put("MARS",	    new String[]{"La Poste Marseille", "#LA #POSTE #MARSEILLE"});
	bq.put("MONTP",	    new String[]{"La Poste Montpellier", "#LA #POSTE #MONTPELLIER"});
	bq.put("NANCY",	    new String[]{"La Poste Nancy", "#LA #POSTE #NANCY"});
	bq.put("NANTES",    new String[]{"La Poste Nantes", "#LA #POSTE #NANTES"});
	bq.put("PARIS",	    new String[]{"La Poste Paris", "#LA #POSTE #PARIS"});
	bq.put("RENNES",    new String[]{"La Poste Rennes", "#LA #POSTE #RENNES"});
	bq.put("ROUEN",	    new String[]{"La Poste Rouen", "#LA #POSTE #ROUEN"});
	bq.put("SDENIS",    new String[]{"La Poste La Réunion", "#LA #POSTE #LA #R/UNION"});
	bq.put("SOURCE",    new String[]{"La Poste Source", "#LA #POSTE #SOURCE"});
	bq.put("SS",	    new String[]{"ss", "SS"});
	bq.put("STRASB",    new String[]{"La Poste Strasbourg", "#LA #POSTE #STRASBOURG"});
	bq.put("TOUL",	    new String[]{"La Poste Toulouse", "#LA #POSTE #TOULOUSE"});
    }
    

    /**
     * 
     * @param nomFic
     * @return 
     */
    public static String[] suggestionMessage(String nomFic) {
	initBqMessage();
	
	Matcher m = pSugg.matcher(nomFic);
	
	if(m.find()) {
	    // SG
	    if(m.group(1).equals("SG"))
		return new String[]{"Société Générale", "#SOCIET/ #G/N/RALE"};
	    // CL
	    else if(m.group(1).equals("CL"))
		return new String[] {
		    "NOUS VOUS INVITONS A PRENDRE CONNAISSANCE DU MESSAGE FIGURANT SUR VOTRE EXTRAIT EDITE SUR PAPIER CLASSIQUE",
		    "NOUS VOUS INVITONS A PRENDRE CONNAISSANCE DU MESSAGE FIGURANT SUR VOTRE EXTRAIT EDITE SUR PAPIER CLASSIQUE"};
	    // BQ
	    else
		return bq.get(m.group(1));  // null si pas trouvé
	}
	// CA
	else if(InfoCentre.getModele().equals(Modele.CA)) {
	    return new String[] {
		    "Crédit Agricole",
		    "#CR/DIT #AGRICOLE"
		    //InfoCentre.getMessage0(),
		    //MEF_Outils.libelle(InfoCentre.getMessage0())
		};
	}
	return null;
    }
    

    /**
     * 
     * @param message
     * @return 
     */
    public static boolean isMessageValide(String message) {
	if(InfoCentre.getModele().equals(Modele.CL) || InfoCentre.getModele().equals(Modele.CA))
	    return true;
	else
	    return (message.length() > 0 && message.length() <= 39);
    }
    
    
    /*
     * 
     */
    private static void remplacerChar(StringBuilder message) {
	Matcher m = pChar.matcher(message);
	while(m.find()) {
	    char c = message.charAt(m.start());

	    switch(c) {
		case '.' : message.setCharAt(m.start(), '\''); break;
		case '/' : message.setCharAt(m.start(), '-'); break;
		case '*' : message.setCharAt(m.start(), ')'); break;
		case '(' : message.setCharAt(m.start(), '<'); break;
		case ')' : message.setCharAt(m.start(), '>'); break;
		case '=' : message.setCharAt(m.start(), '"'); break;
		case 'À' : message.setCharAt(m.start(), '['); break;
		case 'Â' : message.setCharAt(m.start(), '1'); break;
		case 'É' : message.setCharAt(m.start(), '/'); break;
		case 'È' : message.setCharAt(m.start(), '\\'); break;
		case 'Ê' : message.setCharAt(m.start(), '2'); break;
		case 'Ë' : message.setCharAt(m.start(), '6'); break;
		case 'Î' : message.setCharAt(m.start(), '3'); break;
		case 'Ï' : message.setCharAt(m.start(), '7'); break;
		case 'Ô' : message.setCharAt(m.start(), '4'); break;
		case 'Û' : message.setCharAt(m.start(), '5'); break;
		case 'Ü' : message.setCharAt(m.start(), '8'); break;
		case 'Ù' : message.setCharAt(m.start(), ']'); break;
		case 'Ç' : message.setCharAt(m.start(), '@'); break;
	    }
	}
    }
}