/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ai.LaboAI;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import network.ServerConnecter;

/**
 *
 * @author koji
 */
public class ClientGUI extends javax.swing.JFrame implements MessageRecevable {
    
    //サーバとの通信クラス
    private ServerConnecter connecter;
    //表示部分のドキュメントを管理するクラス
    private DefaultStyledDocument document;
    //BlockusAI
    private LaboAI myAI;

    private String defaultIP;
    private String defaultPort = "18420";

    int playerID;

    /**
     * コンストラクタ　文字の表示部分のみを初期化する
     */
    public ClientGUI(LaboAI ai) {
        initComponents();
        try {
            defaultIP = InetAddress.getLocalHost().getHostAddress();//"192.168.108.100";
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.document = new DefaultStyledDocument();
        this.jTextPane1.setDocument(this.document);
        this.myAI = ai;
        this.jTextField2.setText(defaultIP);
        this.jTextField4.setText(defaultPort);
        
    }

    public ClientGUI() {
        initComponents();
        try {
            defaultIP = InetAddress.getLocalHost().getHostAddress();//"192.168.108.100";
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.document = new DefaultStyledDocument();
        this.jTextPane1.setDocument(this.document);
        this.myAI = null;
        this.jTextField2.setText(defaultIP);
        this.jTextField4.setText(defaultPort);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Client 1.02 b180419");

        jLabel1.setText("Log");

        jScrollPane1.setViewportView(jTextPane1);

        jLabel2.setText("Message");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Connect");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("192.168.0.2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setText("address");

        jLabel5.setText("port");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("16041");

        jButton2.setText("Connect");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 428, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**　送信ボタンを押したときの動作　*/    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sendText = this.jTextField1.getText();
        this.sendMessage(sendText);
        this.jTextField1.setText("");               
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            this.jButton1.doClick();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    
    /** 接続ボタンを押したときの動作　受信用のスレッドを立ち上げ待機する。 */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String hostname = this.jTextField2.getText();
        int port = Integer.parseInt(this.jTextField4.getText());
        this.connecter = new ServerConnecter(this);
        try {
            this.connecter.connectToServer(hostname, port);
        } catch (UnknownHostException ex) {
            this.addMessage("UnknownHostException");
        } catch (IOException ex) {
            this.addMessage("IOException");
        }
                

        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed
    
    /** 通信先にメッセージを送信する。サーバにつながっていない場合は送らない */
    public void sendMessage(String sendText){
        //属性情報を作成
        SimpleAttributeSet attribute = new SimpleAttributeSet();
        //属性情報の文字色に赤を設定
        attribute.addAttribute(StyleConstants.Foreground, Color.RED);

        try {
            //サーバーへ送信
            if(this.connecter.canWrite()){
                connecter.sendMessage(sendText);
                document.insertString(document.getLength(), "[send]"+sendText+"\n", attribute);
            } else {
                document.insertString(document.getLength(), "(送信失敗)"+sendText+"\n", attribute);
            }
            this.jTextPane1.setCaretPosition(document.getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /** 通信によって文字を取得したときに（だけ）呼び出される */
    @Override
    public void reciveMessage(String text){
        //属性情報の文字色に青を設定
        try {
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            attribute.addAttribute(StyleConstants.Foreground, Color.BLUE);
            //ドキュメントにその属性情報つきの文字列を挿入
            document.insertString(document.getLength(), "[recv]"+text+"\n", attribute);

            if("100 HELLO".equals(text)){
                String sendRanText = "101 NAME YAMADALAB";
                this.sendMessage(sendRanText);
            }

            if("102 PLAYERID 0".equals(text)){
                playerID = 0;
            }else if("102 PLAYERID 1".equals(text)){
                playerID = 1;
            }

            if("204 DOPLAY".equals(text)){
                //ランダムで打つ場所、打つ役職を決定
                String season = "";
                String place = this.myAI.RandomPut_place(season);
                String worker = this.myAI.RandomPut_worker(place);
                if(playerID == 0){
                    String sendRanText0 = "205 PLAY 0 "+worker+" "+place;
                    this.sendMessage(sendRanText0);
                }else if(playerID == 1){
                    String sendRanText1 = "205 PLAY 1 "+worker+" "+place;
                    this.sendMessage(sendRanText1);
                }
                //String sendRanText = "210 COMFPRM";
            }
            
            /*
            
            正規表現でパターンマッチング
            動作確認なし
            
            str = text;
            Matcher mc = resources.matcher(str);
            player_id = Integer.parseInt(mc.group(3));
            if(player_id == 0){
                int i = 5;
                while(i < 15){
                    player_0.add(Integer.parseInt(mc.group(i)));
                    i = i + 2;
                }
            }else if(player_id == 1){
                int i = 5;
                while(i < 15){
                    player_1.add(Integer.parseInt(mc.group(i)));
                    i = i + 2;
                }
            }        
            */
            
            this.jTextPane1.setCaretPosition(document.getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /** ログなどの追加用　黒文字で表示 */
    @Override
    public void addMessage(String text){
        try {
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            attribute.addAttribute(StyleConstants.Foreground, Color.BLACK);
            //ドキュメントにその属性情報つきの文字列を挿入
            document.insertString(document.getLength(), "[log]"+text+"\n", attribute);
            this.jTextPane1.setCaretPosition(document.getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /*
    
    馬場が書きました
    
    String str;
    int player_id;
    //各プレイヤーのArrayListの中身→　1番目：教授の数  2番目：助手の数　3番目：学生の数　4番目：お金の数　5番目：研究成果の数　6番目：負債の数
    ArrayList player_0 = new ArrayList();
    ArrayList player_1 = new ArrayList();
    
    Pattern resources = Pattern.compile("(211)\\s(.*)\\s(0|1)\\s(.)([0-9])\\s(.)([0-9])\\s(.)([0-9])\\s(.)([0-9]+)\\s(.)([0-9]+)\\s(.)([0-9]+)");
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
