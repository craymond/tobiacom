package misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMatches {
    
    
    
    
    public static int regexOccur(String texte, String regex) {
	Matcher matcher = Pattern.compile(regex).matcher(texte);
	int occur = 0;
	while(matcher.find()) {
	    occur ++;
	}
	return occur;
    }
}
