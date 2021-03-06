/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.FlowLayout;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author J
 */
public class Search_Panel extends javax.swing.JPanel {
    private Vector<Integer> idVec;
    /**
     * Creates new form Search_Panel
     */
    public Search_Panel() {
        initComponents();
        summaryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        searchTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        summaryList = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("Search");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        summaryList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                summaryListMouseClicked(evt);
            }
        });
        summaryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                summaryListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(summaryList);

        jButton2.setText("Look");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jButton2)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2))
        );
    }// </editor-fold>//GEN-END:initComponents
    private String[] getWords(String wordText){
        String[] tokens = wordText.split("&");
        return tokens;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Vector<Integer> idSet = Search.goMulWords(Main_Frame.user, getWords(searchTF.getText().trim()));
        CS2Client client = new CS2Client(7);
        Vector<SearchItem> itemSet = client.getSummary(Main_Frame.user, idSet);
        
        DefaultListModel listModel = new DefaultListModel();
        idVec = new Vector();
        for (int i = 0; i < idSet.size();  i++){
            idVec.add(itemSet.elementAt(i).id);
            listModel.add(i, itemSet.elementAt(i).summary);
        }
        summaryList.setModel(listModel);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int[] id = summaryList.getSelectedIndices();
        Vector<Integer> fileId = new Vector();
        for (int i:id)
            System.out.println(fileId.add(idVec.elementAt(i)));
        
        CS2Client client = new CS2Client(4);
        String content = client.getContent(Main_Frame.user, fileId);
        
        StringBuffer SB = new StringBuffer();
        JFrame CF = new JFrame();
        CF.setSize(800, 600);
        JTextPane tp = new JTextPane();
        tp.setText(content);
        CF.add(new JScrollPane(tp));
        
        CF.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void summaryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_summaryListValueChanged
        // TODO add your handling code here:);
    }//GEN-LAST:event_summaryListValueChanged

    private void summaryListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_summaryListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_summaryListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchTF;
    private javax.swing.JList summaryList;
    // End of variables declaration//GEN-END:variables
}
