/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import gameElements.Game;
import gui.MessageRecevable;
import network.ServerConnecter;

/**
 *
 * @author baba
 * LaboAIの抽象メソッドは未実装
 * LaboAIを継承したクラスをインスタンス化して、上位クラスのLaboAIに書いてあるメソッドを使う
 */
public class Random extends LaboAI{

    public Random(Game game) {
        super(game);
    }
    

    @Override
    public void getNewMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setConnecter(ServerConnecter connecter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOutputInterface(MessageRecevable mr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void reciveMessage(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addMessage(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
