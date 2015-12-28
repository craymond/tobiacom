package gui.custom;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class JTextPaneCustom extends JTextPane {
    public JTextPaneCustom() {
	super();
    }
    
    /**
     * Pour ne pas avec de retour à la ligne automatique
     * http://www.java2s.com/Code/Java/Swing-JFC/NonWrappingWrapTextPane.htm
     * @return 
     */
    @Override
    public boolean getScrollableTracksViewportWidth() {
	Component parent = getParent();
	ComponentUI uiP = getUI();

	return parent != null ? (uiP.getPreferredSize(this).width <= parent.getSize().width) : true;
    }

    
    public void addStylesToDocument(StyledDocument doc) {
	Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
	Style regular = doc.addStyle("regular", def);
	StyleConstants.setFontFamily(def, "Courier New");
	StyleConstants.setFontSize(def, 14);
	StyleConstants.setBold(def, true);

	Style styleNormal = doc.addStyle("normal", regular);
	StyleConstants.setForeground(styleNormal, Color.BLACK);
	StyleConstants.setBackground(styleNormal, Color.WHITE);

	Style styleErreur = doc.addStyle("erreur", regular);
	StyleConstants.setForeground(styleErreur, Color.WHITE);
	StyleConstants.setBackground(styleErreur, Color.GRAY);
    }
}