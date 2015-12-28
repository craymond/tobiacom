package gui;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.InfoCentre;
import misc.Modele;

public class F1_Selection extends JPanel {

    private String modeleSelected;
    
    public F1_Selection() {
	initComponents();
    }

    public void updatePanel() {
	tfSelection.setText("");
	radioGroup.clearSelection();
	checkBoxDossier.setEnabled(false);
	checkBoxDossier.setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioGroup = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        labelSelection = new javax.swing.JLabel();
        tfSelection = new javax.swing.JTextField();
        btParcourir = new javax.swing.JButton();
        labelModele = new javax.swing.JLabel();
        radioCL = new javax.swing.JRadioButton();
        radioSG = new javax.swing.JRadioButton();
        radioBQ = new javax.swing.JRadioButton();
        btSuivant = new javax.swing.JButton();
        labelTitreSelection = new javax.swing.JLabel();
        radioCA = new javax.swing.JRadioButton();
        checkBoxDossier = new javax.swing.JCheckBox();

        jDialog1.setTitle("Erreur");

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        labelSelection.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        labelSelection.setText("Étape #1 - Sélection");

        btParcourir.setText("Parcourir");
        btParcourir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventBtParcourir(evt);
            }
        });

        labelModele.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelModele.setText("Modèle du fichier banque");

        radioGroup.add(radioCL);
        radioCL.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        radioCL.setText("Crédit Lyonnais");
        radioCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCLActionPerformed(evt);
            }
        });

        radioGroup.add(radioSG);
        radioSG.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        radioSG.setText("Société Générale");
        radioSG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSGActionPerformed(evt);
            }
        });

        radioGroup.add(radioBQ);
        radioBQ.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        radioBQ.setText("Autres banques");
        radioBQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBQActionPerformed(evt);
            }
        });

        btSuivant.setText("Suivant");
        btSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuivantActionPerformed(evt);
            }
        });

        labelTitreSelection.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelTitreSelection.setText("Sélectionner le fichier banque");

        radioGroup.add(radioCA);
        radioCA.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        radioCA.setText("Crédit Agricole (Nouveau Modèle)");
        radioCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCAActionPerformed(evt);
            }
        });

        checkBoxDossier.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        checkBoxDossier.setSelected(true);
        checkBoxDossier.setText("Traiter tout le dossier");
        checkBoxDossier.setEnabled(false);
        checkBoxDossier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxDossierActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(btSuivant))
                            .add(layout.createSequentialGroup()
                                .add(labelSelection)
                                .add(0, 0, Short.MAX_VALUE))))
                    .add(layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(tfSelection)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(btParcourir))
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(labelModele)
                            .add(labelTitreSelection)
                            .add(layout.createSequentialGroup()
                                .add(6, 6, 6)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(radioBQ)
                                    .add(radioSG)
                                    .add(radioCL)
                                    .add(radioCA)
                                    .add(layout.createSequentialGroup()
                                        .add(29, 29, 29)
                                        .add(checkBoxDossier)))
                                .add(149, 149, 149)))
                        .add(0, 198, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(labelSelection)
                        .add(30, 30, 30)
                        .add(labelTitreSelection)
                        .add(34, 34, 34))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(btParcourir)
                        .add(tfSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(35, 35, 35)
                .add(labelModele)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(radioCL)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(radioSG)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(radioBQ)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(radioCA)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(checkBoxDossier)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 115, Short.MAX_VALUE)
                .add(btSuivant)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void eventBtParcourir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventBtParcourir
	JFileChooser fileChooser = new JFileChooser(new File(InfoCentre.getRepBanque()));
	int choix = fileChooser.showOpenDialog(this);
	
	if(choix == JFileChooser.APPROVE_OPTION) {
	    String path = fileChooser.getSelectedFile().toString();
	    tfSelection.setText(path);
	    
	    // extraction du nom du fichier
	    String nomFic = new File(path).getName();
	    
	    // détermination du modèle
	    String modele = Modele.ficToModele(nomFic);

	    // on coche le bouton radio correspondant au modèle
	    if(modele.equals(Modele.CL)) {
		radioCL.setSelected(true);
		modeleSelected = Modele.CL;
		checkBoxDossier.setEnabled(false);
	    }
	    else if(modele.equals(Modele.SG)) {
		radioSG.setSelected(true);
		modeleSelected = Modele.SG;
		checkBoxDossier.setEnabled(false);
	    }
	    else if(modele.equals(Modele.CA)) {
		radioCA.setSelected(true);
		modeleSelected = Modele.CA;
		checkBoxDossier.setEnabled(true);
	    }
	    else {
		radioBQ.setSelected(true);
		modeleSelected = Modele.BQ;
		checkBoxDossier.setEnabled(false);
	    }
	    
	}
	else {
	    tfSelection.setText(tfSelection.getText());
	}
    }//GEN-LAST:event_eventBtParcourir

    private void btSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuivantActionPerformed
	// erreur si le fichier n'existe pas
	if(!(new File(tfSelection.getText()).isFile())) {
	    JLabel erreur = new JLabel("Le fichier n'existe pas");
	    JOptionPane.showMessageDialog(this, erreur);
	}
	// erreur si aucun modèle sélectionné
	else if(radioGroup.isSelected(null)) {
	    JLabel erreur = new JLabel("Aucun modèle sélectionné");
	    JOptionPane.showMessageDialog(this, erreur);
	}
	// les conditions sont respectées
	else {
	    // on ajoute les infos dans InfoCentre
	    InfoCentre.setPathFic(tfSelection.getText());
	    InfoCentre.setNomFic(new File(tfSelection.getText()).getName());
	    InfoCentre.setModele(modeleSelected);
	    if(InfoCentre.getModele().equals(Modele.CA))
		InfoCentre.setLireRepCA(checkBoxDossier.isSelected());

	    // TODO : fenêtre de progression
	    InfoCentre.verifFicBanque(null);

	    Aiguilleur.aiguiller();
	}
    }//GEN-LAST:event_btSuivantActionPerformed

    private void radioCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCLActionPerformed
	modeleSelected = Modele.CL;
	checkBoxDossier.setEnabled(false);
    }//GEN-LAST:event_radioCLActionPerformed

    private void radioSGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSGActionPerformed
	modeleSelected = Modele.SG;
	checkBoxDossier.setEnabled(false);
    }//GEN-LAST:event_radioSGActionPerformed

    private void radioBQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBQActionPerformed
	modeleSelected = Modele.BQ;
	checkBoxDossier.setEnabled(false);
    }//GEN-LAST:event_radioBQActionPerformed

    private void radioCAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCAActionPerformed
	modeleSelected = Modele.CA;
	checkBoxDossier.setEnabled(true);
    }//GEN-LAST:event_radioCAActionPerformed

    private void checkBoxDossierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxDossierActionPerformed
	
    }//GEN-LAST:event_checkBoxDossierActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btParcourir;
    private javax.swing.JButton btSuivant;
    private javax.swing.JCheckBox checkBoxDossier;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel labelModele;
    private javax.swing.JLabel labelSelection;
    private javax.swing.JLabel labelTitreSelection;
    private javax.swing.JRadioButton radioBQ;
    private javax.swing.JRadioButton radioCA;
    private javax.swing.JRadioButton radioCL;
    private javax.swing.ButtonGroup radioGroup;
    private javax.swing.JRadioButton radioSG;
    private javax.swing.JTextField tfSelection;
    // End of variables declaration//GEN-END:variables
}
