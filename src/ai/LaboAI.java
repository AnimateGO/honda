/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import gameElements.Board;
import gameElements.Game;
import gui.ClientGUI;
import gui.MessageRecevable;
import java.util.ArrayList;
import java.util.Random;
import network.ServerConnecter;

/**
 * このクラスは抽象クラスです。
 * 継承して機能を実装しないと使うことができません。
 * @author koji
 */
public abstract class LaboAI implements MessageRecevable{
    protected Game gameBoard;
    protected boolean isThinking;
    
    String[] PLACE_NAMES = {"2-1","2-2","2-3","3-1","3-2","3-3","4-1","4-2","4-3","5-1","5-2","5-3"};
    String[] S1_PLACE_NAMES = {"1-1","2-1","2-2","2-3","5-1"};
    String[] S2_PLACE_NAMES = {"1-1","2-1","2-2","2-3","5-1","5-2","5-3"};
    String[] S3_PLACE_NAMES = {"1-1","2-1","2-2","2-3","4-1","4-2","4-3","5-1","5-2","5-3"};
    //String[] PLACE_NAMES = {"1-1","2-1","2-2","2-3","3-1","3-2","3-3","4-1","4-2","4-3","5-1","5-2","5-3","6-1","6-2"};
    
    public Board board;
  
    public LaboAI(Game game){
        this.gameBoard = game;
    }
    public abstract void getNewMessage(String message);

    public abstract void setConnecter(ServerConnecter connecter);

    public void thinkStart(){
        this.isThinking = true;
    }

    public void stopThinking(){
        this.isThinking = false;
    }
    
    public abstract void setOutputInterface(MessageRecevable mr);
    
    
    
    //ボード上で空いている場所を探索
    //m_placeに自分の置ける手を格納
    //y_placeに相手の置ける手を格納
    public void boardCanputPlace(int PlayerID ,ArrayList<String> place){
        if(PlayerID == 0){
          for(int i = 0; i < 15; i++){
              if(this.board.canPutWorker(PlayerID, PLACE_NAMES[i])){
                  place.add(PLACE_NAMES[i]);
              } 
          }
            //探し終わったら"Last"をArrayListの最後に挿入
            place.add("Last");
        }else if(PlayerID == 1){
            for(int i = 0; i < 15; i++){
                if(this.board.canPutWorker(PlayerID, PLACE_NAMES[i])){
                    place.add(PLACE_NAMES[i]);
                }
            }
            place.add("Last");
        }
    }
    
    //お金、実験成果を参照して置ける手を配列に格納するメソッド
    //boardCanputPlaceで格納した配列を用いる
    public void workerCanputPlace(int PlayerID, ArrayList<String> place, ArrayList<String> p_place, ArrayList<String> s_place){
        String str ;
        if(PlayerID == 0){
            str = place.get(0);
            for(int i = 0;!"Last".equals(str) ;i++){
                if(str.startsWith("5")){
                    if(this.gameBoard.canPutWorker(PlayerID, "P", str)){
                        p_place.add(str);
                    }
                }else{
                    if(this.gameBoard.canPutWorker(PlayerID, "P", str)){
                        p_place.add(str);
                    }
                    if(this.gameBoard.canPutWorker(PlayerID, "S", str)){
                        s_place.add(str);
                    }
                }
                str = place.get(i);
            }
            p_place.add(str);
            s_place.add(str);
        }
        
        if(PlayerID == 1){
            str = place.get(0);
            for(int i = 0;!"Last".equals(str) ;i++){
                if(str.startsWith("5")){
                    if(this.gameBoard.canPutWorker(PlayerID, "P", str)){
                        p_place.add(str);
                    }
                }else{
                    if(this.gameBoard.canPutWorker(PlayerID, "P", str)){
                        p_place.add(str);
                    }
                    if(this.gameBoard.canPutWorker(PlayerID, "S", str)){
                        s_place.add(str);
                    }
                }
                str = place.get(i);
            }
            p_place.add(str);
            s_place.add(str);
        }
    }
    
    
    
    public String my_minimax(int hukasa,int Player0_Money,int Player0_ResearchPoint, int Player0_Score,
                            int Player1_Money,int Player1_ResearchPoint, int Player1_Score,
                            //ArrayList<String> m_place, ArrayList<String> y_place,
                            ArrayList<String> m_p_place, ArrayList<String> m_s_place,
                            ArrayList<String> y_p_place, ArrayList<String> y_s_place){
        //this.boardCanputPlace(0, m_place);
        //this.boardCanputPlace(0, y_place);
        //this.workerCanputPlace(0, m_place, m_p_place, m_s_place);
        //this.workerCanputPlace(1, y_place, y_p_place, y_s_place);
        
        String str = m_p_place.get(0);
        for(int i =1; !"Last".equals(str); i++){
            if(str.startsWith("2")){
                Player0_Money += -2;
            }
            
            
        }
        
         str = "";
        return str;
    }
    
    public String your_minimax(int hukasa,int Player0_Money,int Player0_ResearchPoint, int Player0_Score,
                            int Player1_Money,int Player1_ResearchPoint, int Player1_Score,
                            ArrayList<String> m_place, ArrayList<String> y_place,
                            ArrayList<String> m_p_place, ArrayList<String> m_s_place,
                            ArrayList<String> y_p_place, ArrayList<String> y_s_place){
        String str = "";
        return str;
    }
    
    //ランダムに次の手を作成するメソッド。返り値はString 置ける場所
    //シーズンがわかるなら返り値にすると、そのシーズンでやった方がよい手が打てる
    public String RandomPut_place(String season){
        String place ;
        java.util.Random random = new java.util.Random();
        if(season == "1a" || season == "1b"){
            int i = random.nextInt(5);
            place = this.S1_PLACE_NAMES[i];
        }else if(season == "2a" || season == "2b"){
            int i = random.nextInt(7);
            place = this.S2_PLACE_NAMES[i];
        }else{
            int i = random.nextInt(10);
            place = this.S3_PLACE_NAMES[i];
        }
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
    //5で始まらない場合は教授と助手ランダムに打つ
    public String RandomPut_worker(String str){
        String[] workers = {"P","S"};
        String worker;
        java.util.Random random = new java.util.Random();
        if(str.startsWith("5")){
            worker = "P";
        }else{
            int i = random.nextInt(2);
            worker = workers[i];
        }
        return worker;
    }
    
}
