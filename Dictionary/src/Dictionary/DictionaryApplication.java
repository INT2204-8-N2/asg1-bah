/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class DictionaryApplication extends javax.swing.JFrame {

    
    DefaultListModel model;
    private final Dictionary dictionary = new Dictionary();
    private final String filename = "ddd.txt";
    
    /**
     * Creates new form DictionaryApplication
     * @throws java.io.FileNotFoundException
     */
    public DictionaryApplication() throws FileNotFoundException {
        initComponents();
        setLocationRelativeTo(null);
        Output.setContentType("text/html");
        model = new DefaultListModel();
        insertFromFile();
        
    }

    public void insertFromFile() throws FileNotFoundException{
        File inFile = new File(filename);
        try (FileReader fileReader = new FileReader(inFile)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while( (line = reader.readLine())  != null ){
                
                String target = line.substring(0,line.indexOf("<html>")).trim();
                String explain = line.substring(line.indexOf("<html>")).trim();
                dictionary.addWord(target, explain);
            
            }
            ShowAllWord();  // hiển thị từ mới
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ShowAllWord(){
        model.removeAllElements();
        for(int i = 0; i < dictionary.size(); i++){
            model.addElement(dictionary.getWord(i).getTarget());
        }
        ListWord.setModel(model);
       
    }
    
    public void dictionaryLookUp(String str){
        
        int k = -1;
        for(int i = 0; i < dictionary.size(); i++){
            if(dictionary.getWord(i).getTarget().equals(str)){
                k = i;
                break;
            }      
        }
        
        if(k == -1){
            JOptionPane.showMessageDialog(this,"Không có trong từ điển!");
        }else{
            Output.setText(dictionary.getWord(k).getExplain());
        }
        
    }
    
    
    public void dictionarySeacher(String str){
        model.removeAllElements();
        int lenSearch = str.length();
        for(int i = 0; i < dictionary.size(); i++){
            String tar = dictionary.getWord(i).getTarget();
            if(tar.length() >= lenSearch){
                if(tar.substring(0, lenSearch).equals(str)){
                    model.addElement(tar);
                }
            }
        }
        
        ListWord.setModel(model);
    }
    
    
    public void dictionaryExportToFile(){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);
            for (int i=0; i< dictionary.size() ; i++) 
                bw.write(dictionary.getWord(i).getTarget() + " "
                    + dictionary.getWord(i).getExplain() + "\n");
        } catch (IOException e) {
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (IOException e) {
            }
        }
    }
    
    public void delete(){
        String string = ListWord.getSelectedValue().trim();
        int click = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa", 
                "CẢNH BÁO", JOptionPane.YES_NO_OPTION);
        if (click == JOptionPane.YES_OPTION){
            int k = -1;
            for(int i = 0; i < dictionary.size(); i++){        
                if(dictionary.getWord(i).getTarget().equals(string)){
                    k = i;
                    break;
                }
            }
            dictionary.remove(k);
            ShowAllWord();
            JOptionPane.showMessageDialog(this,"Đã xóa từ: " + string);
            dictionaryExportToFile();
        }
    }  

    
    
    public static void speech(String text) {
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(text);
        syntheticVoice.deallocate();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seach = new javax.swing.JButton();
        Input = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListWord = new javax.swing.JList<>();
        Speak = new javax.swing.JButton();
        add = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextPane();
        exit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dictionary");
        setMinimumSize(new java.awt.Dimension(680, 440));
        getContentPane().setLayout(null);

        Seach.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\Dictionary\\image\\seach.jpg")); // NOI18N
        Seach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeachActionPerformed(evt);
            }
        });
        getContentPane().add(Seach);
        Seach.setBounds(510, 50, 30, 30);

        Input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                InputKeyReleased(evt);
            }
        });
        getContentPane().add(Input);
        Input.setBounds(110, 50, 370, 30);

        ListWord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListWordMouseClicked(evt);
            }
        });
        ListWord.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ListWordKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ListWord);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 120, 190, 260);

        Speak.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\Dictionary\\image\\phatam.jpg")); // NOI18N
        Speak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpeakActionPerformed(evt);
            }
        });
        getContentPane().add(Speak);
        Speak.setBounds(600, 70, 40, 39);

        add.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\Dictionary\\image\\add.png")); // NOI18N
        add.setPreferredSize(new java.awt.Dimension(40, 40));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add);
        add.setBounds(260, 120, 40, 40);

        edit.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\Dictionary\\image\\edit.png")); // NOI18N
        edit.setPreferredSize(new java.awt.Dimension(40, 40));
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        getContentPane().add(edit);
        edit.setBounds(260, 190, 40, 40);

        delete.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\Dictionary\\image\\delete.jpg")); // NOI18N
        delete.setPreferredSize(new java.awt.Dimension(40, 40));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete);
        delete.setBounds(260, 260, 40, 40);

        jScrollPane2.setViewportView(Output);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(330, 120, 310, 260);

        exit.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\Dictionary\\image\\exit.png")); // NOI18N
        exit.setPreferredSize(new java.awt.Dimension(40, 40));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit);
        exit.setBounds(260, 340, 40, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\Dictionary\\image\\anhnen.jpg")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 670, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void SeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeachActionPerformed
        // TODO add your handling code here:
        dictionaryLookUp(Input.getText());
    }//GEN-LAST:event_SeachActionPerformed

    private void InputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InputKeyReleased
        // TODO add your handling code here:
        dictionarySeacher(Input.getText());
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            dictionaryLookUp(Input.getText());
        }
    }//GEN-LAST:event_InputKeyReleased

    private void ListWordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListWordMouseClicked
        // TODO add your handling code here:
        dictionaryLookUp(ListWord.getSelectedValue());
        Input.setText(ListWord.getSelectedValue());
    }//GEN-LAST:event_ListWordMouseClicked

    private void ListWordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ListWordKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){      // nếu ấn nút enter
                String str = ListWord.getSelectedValue().trim();
                Input.setText(str);
                dictionaryLookUp(str);
        }
    }//GEN-LAST:event_ListWordKeyPressed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_deleteActionPerformed

    private void SpeakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpeakActionPerformed
        // TODO add your handling code here:
        String str = Input.getText();
        speech(str);
    }//GEN-LAST:event_SpeakActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        Add a = new Add();
        a.setVisible(true);
        a.getOKButton().addActionListener((java.awt.event.ActionEvent e) -> {
            if (a.getInTaget().equals("")  || a.getInExplain().equals("")) {
                JOptionPane.showMessageDialog(DictionaryApplication.this,
                        "Không để trống 'Nhập từ: ' /  'Nhập nghĩa: '", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                int k = -1;
                for(int i = 0; i < dictionary.size(); i++){
                    
                    if(dictionary.getWord(i).getTarget().equals(a.getInTaget())){
                        k = i;
                        break;
                    }
                }
                if (k != -1) {
                    JOptionPane.showMessageDialog(DictionaryApplication.this,
                            "Từ đã tồn tại!", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    a.dispose();       // đóng Frame
                    dictionary.addWord(a.getInTaget(), "<html>" + a.getInExplain() + "<html>");// thêm từ
                    ShowAllWord();
                    JOptionPane.showMessageDialog(this,"Thêm từ thành công!");
                    dictionaryExportToFile();
                }
            }
        });
    }//GEN-LAST:event_addActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        Edit e = new Edit();
        e.setVisible(true);
        e.getOKButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (e.getInTaget().equals("")  || e.getInNewExplain().equals("")) {
                    JOptionPane.showMessageDialog(DictionaryApplication.this,
                            "Không để trống 'Từ cần sửa: ' /  'Nhập nghĩa mới: '", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    int k = -1;
                    for(int i = 0; i < dictionary.size(); i++){
                        
                        if(dictionary.getWord(i).getTarget().equals(e.getInTaget())){
                            k = i;
                            break;
                        }
                    }   
                    if (k != -1){
                        e.dispose();       // đóng Frame
                        dictionary.getWord(k).setExplain("<html>" + e.getInNewExplain() + "<html>");
                        ShowAllWord();
                        JOptionPane.showMessageDialog(DictionaryApplication.this, "Sửa từ thành công!");
                        //dictionaryExportToFile();
                        dictionaryExportToFile();
                    } else {
                        JOptionPane.showMessageDialog(DictionaryApplication.this,
                                "Từ không tồn tại!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }//GEN-LAST:event_editActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DictionaryApplication().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DictionaryApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Input;
    private javax.swing.JList<String> ListWord;
    private javax.swing.JTextPane Output;
    private javax.swing.JButton Seach;
    private javax.swing.JButton Speak;
    private javax.swing.JButton add;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
