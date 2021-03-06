package gui;

import main.InfoCentre;

public class F5_Bilan extends javax.swing.JPanel {

    public F5_Bilan() {
	initComponents();
    }

    public void updatePanel() {
	table.setValueAt(InfoCentre.getNomFic(), 0, 1);
	table.setValueAt(InfoCentre.getPathFicBAN(), 1, 1);
	table.setValueAt(InfoCentre.getPathFicETI(), 2, 1);
	table.setValueAt(InfoCentre.getNbReleves(), 4, 1);
	table.setValueAt(InfoCentre.getNbPages(), 5, 1);
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
        btNouveau = new javax.swing.JButton();
        btQuitter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        labelTitre.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        labelTitre.setText("Étape #5 - Bilan");

        btNouveau.setText("Nouveau");
        btNouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNouveauActionPerformed(evt);
            }
        });

        btQuitter.setText("Quitter");
        btQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitterActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Fichier Banque", null},
                {"Fichier .BAN", null},
                {"Fichier .ETI", null},
                {null, null},
                {"Nombre de Relevés", null},
                {"Nombre de Pages", null}
            },
            new String [] {
                "Item", "Valeurs"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(0).setMaxWidth(200);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(btQuitter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(btNouveau))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(labelTitre))
                            .add(layout.createSequentialGroup()
                                .add(46, 46, 46)
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 617, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(0, 131, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(labelTitre)
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 273, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 195, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btNouveau)
                    .add(btQuitter))
                .add(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitterActionPerformed
	System.exit(0);
    }//GEN-LAST:event_btQuitterActionPerformed

    private void btNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNouveauActionPerformed
	Aiguilleur.aiguillerNouveau();
    }//GEN-LAST:event_btNouveauActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btNouveau;
    private javax.swing.JButton btQuitter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
