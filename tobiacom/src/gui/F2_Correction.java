package gui;
import gui.custom.JTextPaneCustom;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import main.InfoCentre;
import misc.LectureFichier;
import miseEnForme.MEF_Outils;
import verification.LogErreur;

public class F2_Correction extends javax.swing.JPanel {

    private ArrayList<Integer> iLigneErreurs;		// Ensemble des erreurs du fichier banque
    private HashMap<Integer, Integer> cleDebutLigne;	// On garde en mémoire le caret pour chaque nouvelle ligne
    private DefaultTableModel logData;			// 
    
    
    /**
     * Creates new form F2_Correction
     */
    public F2_Correction() {
	initComponents();
	iLigneErreurs = new ArrayList<Integer>();
	logData = (DefaultTableModel)tableLog.getModel();
	cleDebutLigne = new HashMap<Integer, Integer>();
    }

    
    /**
     * 
     */
    public void updatePanel() {
	// Nettoyage
	taFic.setText("");
	iLigneErreurs.clear();
	cleDebutLigne.clear();
	labelCaret.setText("Ligne : 1");
	
	// On efface le recapitulatif des erreurs
	int nRows = logData.getRowCount();   // Pas dans la boucle, c'est fait exprès
	for(int i = 1; i <= nRows; i++) {
	    logData.removeRow(0);
	}

	// Mise à jour des erreurs
	for(LogErreur aLog : InfoCentre.getLog()) {
	    iLigneErreurs.add(aLog.getiLigne());
	    logData.addRow(new Object[] { aLog.getiLigne(), aLog.getMessage() });
	}

	// Mise à jour du fichier banque
	ArrayList<String> ficBanque = InfoCentre.getFicBanque();


	StyledDocument doc = taFic.getStyledDocument();
	((JTextPaneCustom) taFic).addStylesToDocument(doc);

	int idLigne = 1;
	int caret  = 1;

	for(String ligne : ficBanque) {
	    try {
		if(iLigneErreurs.size() > 0 && idLigne == iLigneErreurs.get(0)) {
		    //doc.insertString(doc.getLength(), ligne + "\n", doc.getStyle("erreur"));
		    doc.insertString(doc.getLength(), ligne + "\n", doc.getStyle("erreur"));
		    iLigneErreurs.remove(0); // on vide, pour toujours avoir l'erreur sur le 0
		}
		else {
		    doc.insertString(doc.getLength(), ligne + "\n", doc.getStyle("normal"));
		}
		
	    }
	    catch(BadLocationException e) {
		System.out.println(e.getMessage());
	    }

	    caret += ligne.length() + 1;
	    cleDebutLigne.put(idLigne, caret);
	    idLigne++;
	}
	taFic.setCaretPosition(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated by the
     * Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitre = new javax.swing.JLabel();
        scrollLog = new javax.swing.JScrollPane();
        tableLog = new javax.swing.JTable();
        btSuivant = new javax.swing.JButton();
        btVerifier = new javax.swing.JButton();
        labelCaret = new javax.swing.JLabel();
        scrollFic = new javax.swing.JScrollPane();
        taFic = new JTextPaneCustom();

        labelTitre.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        labelTitre.setText("Étape #2 - Correction");

        tableLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ligne", "Erreur"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLogMouseClicked(evt);
            }
        });
        scrollLog.setViewportView(tableLog);
        tableLog.getColumnModel().getColumn(0).setMaxWidth(80);

        btSuivant.setText("Suivant");
        btSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuivantActionPerformed(evt);
            }
        });

        btVerifier.setText("Actualiser");
        btVerifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerifierActionPerformed(evt);
            }
        });

        labelCaret.setText("Ligne : 1");

        taFic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taFicMouseClicked(evt);
            }
        });
        taFic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                taFicKeyPressed(evt);
            }
        });
        scrollFic.setViewportView(taFic);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, scrollLog, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .add(scrollFic)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(btVerifier)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(btSuivant))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(labelTitre)
                            .add(labelCaret))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(labelTitre)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrollFic, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(labelCaret)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrollLog, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 204, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btSuivant)
                    .add(btVerifier))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     */
    private void tableLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLogMouseClicked
	// récupération de la ligne de l'erreur à partir du tableau
	int idRow = tableLog.rowAtPoint(evt.getPoint());
	Integer ligneErreur = (Integer)tableLog.getValueAt(idRow, 0);
	
	// on veut centrer l'erreur
	int caretPosition = getLigneCaret();
	// le curseur était au-dessus de l'erreur
	if(caretPosition < ligneErreur && ligneErreur + 7 <= InfoCentre.getFicBanque().size())
	    taFic.setCaretPosition(cleDebutLigne.get(ligneErreur + 7));
	else if(caretPosition < ligneErreur && ligneErreur + 7 > InfoCentre.getFicBanque().size())
	    taFic.setCaretPosition(cleDebutLigne.get(InfoCentre.getFicBanque().size()) - 1);
	// le curseur était en dessous de l'erreur
	else if(caretPosition > ligneErreur && ligneErreur - 7 >= 1)
	    taFic.setCaretPosition(cleDebutLigne.get(ligneErreur - 7));
	else if(caretPosition > ligneErreur && ligneErreur - 7 < 1)
	    taFic.setCaretPosition(0);
        else
	    taFic.setCaretPosition(cleDebutLigne.get(ligneErreur));
    }//GEN-LAST:event_tableLogMouseClicked

    private void taFicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taFicMouseClicked
	labelCaret.setText("Ligne: " + getLigneCaret());
    }//GEN-LAST:event_taFicMouseClicked

    private void taFicKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taFicKeyPressed
	int keyCode = evt.getKeyCode();
	int caret = getLigneCaret();
	if(keyCode == KeyEvent.VK_UP && caret > 1) {
	    labelCaret.setText("Ligne: " + (caret - 1));
	}
	else if(keyCode == KeyEvent.VK_DOWN) {
	    labelCaret.setText("Ligne: " + (caret + 1));
	}
    }//GEN-LAST:event_taFicKeyPressed

    private void btVerifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerifierActionPerformed
	this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	
	InfoCentre.verifFicBanque(new LectureFichier().lireFichierFromString(taFic.getText()));
	updatePanel();
	
	this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btVerifierActionPerformed

    private void btSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuivantActionPerformed
	InfoCentre.verifFicBanque(new LectureFichier().lireFichierFromString(taFic.getText()));
	if(!InfoCentre.isFicValide()) {
	    JLabel question = new JLabel("<html>Le fichier est invalide<br>Continuer quand même ?</html>");
	    int reponse = JOptionPane.showConfirmDialog(null, question);
	    if(reponse == JOptionPane.YES_OPTION) {
		InfoCentre.setFicValide(true);
		Aiguilleur.aiguiller();
	    }
	}
	else {
	    Aiguilleur.aiguiller();
	}
    }//GEN-LAST:event_btSuivantActionPerformed

    /**
     * Permet d'obtenir la position du curseur (n° de la ligne)
     * @return 
     */
    private int getLigneCaret() {
	Element root = taFic.getDocument().getDefaultRootElement();
	return root.getElementIndex(taFic.getCaretPosition()) + 1;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSuivant;
    private javax.swing.JButton btVerifier;
    private javax.swing.JLabel labelCaret;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JScrollPane scrollFic;
    private javax.swing.JScrollPane scrollLog;
    private javax.swing.JTextPane taFic;
    private javax.swing.JTable tableLog;
    // End of variables declaration//GEN-END:variables
}