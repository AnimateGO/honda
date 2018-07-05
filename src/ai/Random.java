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
 */
public class Random extends LaboAI{

    public Random(Game game) {
        super(game);
    }
    
    //ランダムに次の手を作成するメソッド。返り値はString 置ける場所
    public String RandomPut_place(){
        String place ;
        java.util.Random random = new java.util.Random();
        int i = random.nextInt(14);
        place = this.PLACE_NAMES[i];
        /*
        if(str[0].startsWith("5")){
            str[1] = "P";
        }else{
            str[1] = "S";
        }
        times +=1;
        */
        return place;
    }
    
    //資金のとこに教授置かないと話にならないので5で始まる場所の時は教授を、それ以外は学生を返す
    public String RnadomPut_worker(String str){
        String worker;
        if(str.startsWith("5")){
            worker = "P";
        }else{
            worker = "S";
        }
        return worker;
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
