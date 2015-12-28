package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import main.InfoCentre;
import miseEnForme.MEF_Message;
import miseEnForme.MEF_Outils;

public class F3_Message extends javax.swing.JPanel {

    public F3_Message() {
	initComponents();
	labelNbChar.setText("");
    }

    public void updatePanel() {	
	String message0 = InfoCentre.getMessage0().trim();
	String message1 = InfoCentre.getMessage1().trim();
	
	if(message0.isEmpty()) {
	    message0 = "PAS DE MESSAGE DANS LE FICHIER " + InfoCentre.getNomFic();
	}
	else {
	    MEF_Outils.libelle(message0, false);
	    MEF_Outils.libelle(message1, false);
	}
	labelMessage0.setText(message0);
	labelMessage1.setText(message1);

	String[] suggestion = MEF_Message.suggestionMessage(InfoCentre.getNomFic());
	if(suggestion != null) {
	    tfMessage.setText(suggestion[0]);
	    tfApercu.setText(suggestion[1]);
	    
	    tfMessage.setCaretPosition(0);
	    tfApercu.setCaretPosition(0);
	}
	else {
	    tfMessage.setText("");
	    tfApercu.setText("");
	}
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
        labelMessage0 = new javax.swing.JLabel();
        tfMessage = new javax.swing.JTextField();
        tfApercu = new javax.swing.JTextField();
        btSuivant = new javax.swing.JButton();
        labelMessage1 = new javax.swing.JLabel();
        btEffacerMessage = new javax.swing.JButton();
        btEffacerApercu = new javax.swing.JButton();
        labelTitreMessage = new javax.swing.JLabel();
        labelTitreRetaper = new javax.swing.JLabel();
        labelTitreApercu = new javax.swing.JLabel();
        labelNbChar = new javax.swing.JLabel();

        labelTitre.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        labelTitre.setText("Étape #3 - Message");

        labelMessage0.setText("Message #0");

        tfMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfMessageKeyReleased(evt);
            }
        });

        tfApercu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfApercuKeyPressed(evt);
            }
        });

        btSuivant.setText("Suivant");
        btSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuivantActionPerformed(evt);
            }
        });

        labelMessage1.setText("Message #1");

        btEffacerMessage.setText("Effacer");
        btEffacerMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEffacerMessageActionPerformed(evt);
            }
        });

        btEffacerApercu.setText("Effacer");
        btEffacerApercu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEffacerApercuActionPerformed(evt);
            }
        });

        labelTitreMessage.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelTitreMessage.setText("Message contenu dans le fichier banque");

        labelTitreRetaper.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelTitreRetaper.setText("Message à insérer dans le fichier .BAN");

        labelTitreApercu.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelTitreApercu.setText("Aperçu");

        labelNbChar.setText("Nombre de caractères: 00 / 39");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(labelTitreRetaper)
                                        .add(0, 337, Short.MAX_VALUE))
                                    .add(tfMessage))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btEffacerMessage))
                            .add(layout.createSequentialGroup()
                                .add(labelTitre)
                                .add(0, 541, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(layout.createSequentialGroup()
                                        .add(labelTitreApercu)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(labelNbChar))
                                    .add(tfApercu))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btEffacerApercu))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(btSuivant))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(labelMessage0, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 655, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelMessage1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 655, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelTitreMessage))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(labelTitre)
                .add(13, 13, 13)
                .add(labelTitreMessage)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(labelMessage0)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(labelMessage1)
                .add(30, 30, 30)
                .add(labelTitreRetaper)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tfMessage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btEffacerMessage))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelTitreApercu)
                    .add(labelNbChar))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tfApercu, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btEffacerApercu))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 286, Short.MAX_VALUE)
                .add(btSuivant)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfMessageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMessageKeyReleased
	tfApercu.setText(MEF_Message.message(tfMessage.getText()));
    }//GEN-LAST:event_tfMessageKeyReleased

    private void btSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuivantActionPerformed
	// on vérifie le message
	if(MEF_Message.isMessageValide(tfMessage.getText())) {
	    // on met à jour le message dans InfoCentre
	    InfoCentre.setMessageMEF(tfApercu.getText());
	    InfoCentre.setMessageValide(true);

	    Aiguilleur.aiguiller();
	}
	// erreur si le message est invalide
	else {
	    JOptionPane.showMessageDialog(this, new JLabel("Le Message est vide ou est trop long (39 caractères autorisés)"));
	}
    }//GEN-LAST:event_btSuivantActionPerformed

    private void btEffacerMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEffacerMessageActionPerformed
	tfMessage.setText("");
    }//GEN-LAST:event_btEffacerMessageActionPerformed

    private void btEffacerApercuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEffacerApercuActionPerformed
	tfApercu.setText("");
    }//GEN-LAST:event_btEffacerApercuActionPerformed

    private void tfApercuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfApercuKeyPressed
	//labelNbChar.setText("Nombre de caractères: " + tfApercu.getText().length() + " / 39");
    }//GEN-LAST:event_tfApercuKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEffacerApercu;
    private javax.swing.JButton btEffacerMessage;
    private javax.swing.JButton btSuivant;
    private javax.swing.JLabel labelMessage0;
    private javax.swing.JLabel labelMessage1;
    private javax.swing.JLabel labelNbChar;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JLabel labelTitreApercu;
    private javax.swing.JLabel labelTitreMessage;
    private javax.swing.JLabel labelTitreRetaper;
    private javax.swing.JTextField tfApercu;
    private javax.swing.JTextField tfMessage;
    // End of variables declaration//GEN-END:variables
}
