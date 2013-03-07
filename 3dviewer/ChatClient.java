/*
 * ChatClient.java
 *
 * Created on September 25, 2000, 3:34 PM
 */
 
import java.io.*;
import java.net.*;

/** 
 *
 * @author  ntuser
 * @version 
 */
public class ChatClient extends java.awt.Frame {

    Client c;
  /** Creates new form ChatClient */
  public ChatClient() {
    initComponents ();
    pack ();
     try {
      c = new Client(InetAddress.getByName(textField3.getText()), 1234, textArea1);
    } catch (Exception e) {
        e.printStackTrace();
      }
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
  private void initComponents () {//GEN-BEGIN:initComponents
    panel1 = new java.awt.Panel ();
    label1 = new java.awt.Label ();
    textField1 = new java.awt.TextField ();
    label2 = new java.awt.Label ();
    textField3 = new java.awt.TextField ();
    textArea1 = new java.awt.TextArea ();
    panel2 = new java.awt.Panel ();
    textField2 = new java.awt.TextField ();
    button2 = new java.awt.Button ();
    addWindowListener (new java.awt.event.WindowAdapter () {
      public void windowClosing (java.awt.event.WindowEvent evt) {
        exitForm (evt);
      }
    }
    );

    panel1.setFont (new java.awt.Font ("Dialog", 0, 11));
    panel1.setName ("panel1");
    panel1.setBackground (new java.awt.Color (204, 204, 204));
    panel1.setForeground (java.awt.Color.black);

      label1.setFont (new java.awt.Font ("Dialog", 0, 11));
      label1.setName ("label1");
      label1.setBackground (new java.awt.Color (204, 204, 204));
      label1.setForeground (java.awt.Color.black);
      label1.setText ("Enter your name:");
  
      panel1.add (label1);
  
      textField1.setBackground (java.awt.Color.white);
      textField1.setName ("textfield1");
      textField1.setFont (new java.awt.Font ("Dialog", 0, 11));
      textField1.setForeground (java.awt.Color.black);
      textField1.setText ("UserName");
  
      panel1.add (textField1);
  
      label2.setFont (new java.awt.Font ("Dialog", 0, 11));
      label2.setName ("label2");
      label2.setBackground (new java.awt.Color (204, 204, 204));
      label2.setForeground (java.awt.Color.black);
      label2.setText ("Server");
  
      panel1.add (label2);
  
      textField3.setBackground (java.awt.Color.white);
      textField3.setName ("textfield3");
      textField3.setFont (new java.awt.Font ("Dialog", 0, 11));
      textField3.setForeground (java.awt.Color.black);
      textField3.setText ("edcntw322");
      textField3.addActionListener (new java.awt.event.ActionListener () {
        public void actionPerformed (java.awt.event.ActionEvent evt) {
          textField3ActionPerformed (evt);
        }
      }
      );
  
      panel1.add (textField3);
  

    add (panel1, java.awt.BorderLayout.NORTH);

    textArea1.setBackground (java.awt.Color.white);
    textArea1.setName ("text1");
    textArea1.setFont (new java.awt.Font ("Dialog", 0, 11));
    textArea1.setForeground (java.awt.Color.black);


    add (textArea1, java.awt.BorderLayout.CENTER);

    panel2.setLayout (new java.awt.BorderLayout ());
    panel2.setFont (new java.awt.Font ("Dialog", 0, 11));
    panel2.setName ("panel2");
    panel2.setBackground (new java.awt.Color (204, 204, 204));
    panel2.setForeground (java.awt.Color.black);

      textField2.setBackground (java.awt.Color.white);
      textField2.setName ("textfield2");
      textField2.setFont (new java.awt.Font ("Dialog", 0, 11));
      textField2.setForeground (java.awt.Color.black);
      textField2.addActionListener (new java.awt.event.ActionListener () {
        public void actionPerformed (java.awt.event.ActionEvent evt) {
          textField2ActionPerformed (evt);
        }
      }
      );
  
      panel2.add (textField2, java.awt.BorderLayout.CENTER);
  
      button2.setFont (new java.awt.Font ("Dialog", 0, 11));
      button2.setLabel ("Send");
      button2.setName ("button2");
      button2.setBackground (java.awt.Color.lightGray);
      button2.setForeground (java.awt.Color.black);
      button2.addActionListener (new java.awt.event.ActionListener () {
        public void actionPerformed (java.awt.event.ActionEvent evt) {
          button2ActionPerformed (evt);
        }
      }
      );
  
      panel2.add (button2, java.awt.BorderLayout.EAST);
  

    add (panel2, java.awt.BorderLayout.SOUTH);

  }//GEN-END:initComponents

  private void textField3ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField3ActionPerformed
// Add your handling code here:
  }//GEN-LAST:event_textField3ActionPerformed

  private void textField2ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
     send();
  }//GEN-LAST:event_textField2ActionPerformed

  private void button2ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
    send();
  }//GEN-LAST:event_button2ActionPerformed
  public void send() {
    try {
      //c.setServer(InetAddress.getByName(textField3.getText()));
      c.send(textField1.getText() + " "+ textField2.getText());
    } catch (Exception e) {
       e.printStackTrace();
    }
    textField2.setText("");
  }
  /** Exit the Application */
  private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
    System.exit (0);
  }//GEN-LAST:event_exitForm

  /**
  * @param args the command line arguments
  */
  public static void main (String args[]) {
    new ChatClient ().show ();
  }


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private java.awt.Panel panel1;
  private java.awt.Label label1;
  private java.awt.TextField textField1;
  private java.awt.Label label2;
  private java.awt.TextField textField3;
  private java.awt.TextArea textArea1;
  private java.awt.Panel panel2;
  private java.awt.TextField textField2;
  private java.awt.Button button2;
  // End of variables declaration//GEN-END:variables

}