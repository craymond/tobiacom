package gui;
import java.awt.CardLayout;
import java.awt.Cursor;
import javax.swing.JPanel;
import main.InfoCentre;

public class Aiguilleur {
    private static JPanel fMain;
    private static CardLayout cl;
    
    public Aiguilleur(JPanel fMain) {
	Aiguilleur.fMain = fMain;
	Aiguilleur.cl    = (CardLayout) fMain.getLayout();
    }
    
    public static void aiguillerNouveau() {
	InfoCentre.initialisation();
	
	F1_Selection f1 = (F1_Selection)fMain.getComponent(0);
	f1.updatePanel();
	f1.revalidate();
	
	Aiguilleur.cl.show(fMain, "f1");
    }
    
    public static void aiguiller() {
	// Curseur sablier
	fMain.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	
	// Fenêtre Correction si le fichier est invalide
	if(!InfoCentre.isFicValide()) {
	    F2_Correction f2 = (F2_Correction)fMain.getComponent(1);
	    f2.updatePanel();
	    f2.revalidate();

	    Aiguilleur.cl.show(fMain, "f2");
	}

	// Fenêtre Message si fichier valide et fichier contenant des messages
	else if(InfoCentre.isFicValide() && !InfoCentre.isMessageValide()) {
	    InfoCentre.construire();
	    
	    F3_Message f3 = (F3_Message)fMain.getComponent(2);
	    f3.updatePanel();
	    f3.revalidate();
	    
	    Aiguilleur.cl.show(fMain, "f3");
	}
	// Fenêtre Sauvegarde
	else if(InfoCentre.isFicValide() && InfoCentre.isMessageValide() && !InfoCentre.isFicEcrit()) {
	    F4_Sauvegarde f4 = (F4_Sauvegarde)fMain.getComponent(3);
	    f4.updatePanel();
	    f4.revalidate();
	    
	    Aiguilleur.cl.show(fMain, "f4");
	}
	else if(InfoCentre.isFicEcrit()) {
	    F5_Bilan f5 = (F5_Bilan)fMain.getComponent(4);
	    f5.updatePanel();
	    f5.revalidate();
	    
	    Aiguilleur.cl.show(fMain, "f5");
	}
	
	// Curseur normal
	fMain.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
