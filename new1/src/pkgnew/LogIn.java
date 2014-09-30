/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static pkgnew.Main_Frame.DisplayPanel;
import static pkgnew.Main_Frame.LOGIN;
import static pkgnew.Main_Frame.user_login;

/**
 *
 * @author priyanja
 */
public class LogIn extends javax.swing.JPanel {

    /**
     * Creates new form LogIn
     */
    public LogIn() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bt_Cancel = new javax.swing.JButton();
        tf_Password = new javax.swing.JPasswordField();
        tf_UserName = new javax.swing.JTextField();
        bt_Ok = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        bt_Cancel.setText("Cancel");

        bt_Ok.setText("OK");
        bt_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_OkActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LOG IN");

        jLabel2.setText("UserName :-");

        jLabel3.setText("Password :-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_UserName)
                    .addComponent(tf_Password, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(bt_Ok, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Ok)
                    .addComponent(bt_Cancel))
                .addGap(93, 93, 93))
        );
    }// </editor-fold>//GEN-END:initComponents

    private String li_UserName;
    private String li_Password;
    Socket soc;  
    
    private void bt_OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_OkActionPerformed
        // TODO add your handling code here:

        Thread t = new Thread(){

            public void run(){

                li_UserName=tf_UserName.getText();
                li_Password=tf_Password.getText();

                try {
                    soc = new Socket("localhost",12345);

                    PrintWriter pr = new PrintWriter(soc.getOutputStream(),true);
                    pr.println("LOGIN");

                    BufferedReader buff=new BufferedReader(new InputStreamReader(soc.getInputStream()));
                    String str=buff.readLine();

                    if(str.equals("OK")){
                        System.out.println("mssg fromserver " + str);
                        pr = new PrintWriter(soc.getOutputStream(),true);
                        pr.println(li_UserName);
                        pr.println(li_Password);
                    }

                    buff=new BufferedReader(new InputStreamReader(soc.getInputStream()));
                    str=buff.readLine();

                    if(str.equals("MATCHFOUND")){

                        System.out.println("mssg from server " + str);
                        CardLayout card =(CardLayout) DisplayPanel.getLayout();
                        card.show(DisplayPanel, "category");
                        
                        LOGIN=true;
                        user_login=li_UserName.substring(0);
                        JOptionPane.showMessageDialog(null,"LOG IN SUCCESSFULL!!!" );

                    }
                    else if(str.equals("USERNOTFOUND")){
                        System.out.println("mssg form server " + str);

                    }
                    else if(str.equals("PASSWORDNOTMATCH")){
                        JOptionPane.showMessageDialog(null,"RENTER YOUR PASSWORD" );
                    }

                } catch (IOException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };
        t.start();

    }//GEN-LAST:event_bt_OkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_Ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField tf_Password;
    private javax.swing.JTextField tf_UserName;
    // End of variables declaration//GEN-END:variables
}
