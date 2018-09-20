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
    
    String[] S1_PLACE_NAMES = {"1-1","2-1","2-2","2-3","5-1"};
    String[] S2_PLACE_NAMES = {"1-1","2-1","2-2","2-3","5-1","5-2","5-3"};
    String[] S3_PLACE_NAMES = {"1-1","2-1","2-2","2-3","4-1","4-2","4-3","5-1","5-2","5-3"};
    String[] PLACE_NAMES = {"1-1","2-1","2-2","2-3","3-1","3-2","3-3","4-1","4-2","4-3","5-1","5-2","5-3","6-1","6-2"};
    //boolean P_CANPUT_PLACE[] = new boolean[15];
    //boolean A_CANPUT_PLACE[] = new boolean[15];
    //boolean S_CANPUT_PLACE[] = new boolean[15];
    boolean CANPUT_PLACE[][];
    
    public Board board;
    
    private int[] money;
    private int[] reserchPoint;
    private int[] currentScore;
    private int[][] workerList; 
    private int[][] usedWorkerList;
    private int[] zemiCount;
    private int currentStartPlayer;
    private int changeFlag;
    private String trend;
    private String[] ifTrend;
    private int[][] resources;
    private int[] operateResources;
    private String operateTrend;
    private int[][] evaluationList;
    private int i;
    
  
    public LaboAI(Game game){
        this.CANPUT_PLACE = new boolean[30][15];
        this.evaluationList = new int[30][15];
        this.money = new int[2];
        this.reserchPoint = new int[2];
        this.currentScore = new int[2];
        this.workerList = new int[2][3];
        this.usedWorkerList = new int[2][3];
        this.zemiCount = new int[3];
        this.resources = new int [10][11];
        this.operateResources = new int[8];
        this.currentStartPlayer = 0;
        this.changeFlag = 0;
        this.gameBoard = game;
        this.ifTrend = new String[10];
        this.i = -1;
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
    
    public boolean canPutWorker(int[] resources,String typeOfWorker,String place){
        if(place.startsWith("2")){
            if(resources[1] >= 2){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("3-1")){
            if(resources[2] >= 2){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("3-2")){
            if(resources[2] >= 4 && resources[1] >= 1){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("3-3")){
            if(resources[2] >= 8 && resources[1] >= 1){
                return true;
            } else {
                return false;
            }
        }
        if(place.startsWith("4")){
            if(resources[2] >= 8 && resources[1] >= 1){
                return true;
            } else {
                return false;
            }
        }
        //タイプが問題ないかを確認
        if(place.startsWith("5")){
            if(typeOfWorker.equals("P") || typeOfWorker.equals("A")){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("6-1")){
            if(typeOfWorker.equals("P") || typeOfWorker.equals("A")){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("6-2")){
            if(typeOfWorker.equals("P") || resources[3] >= 10){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    /*
    public boolean canPutWorker(int player,String typeOfWorker,String place){
        if(place.startsWith("2")){
            if(this.money[player] >= 2){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("3-1")){
            if(this.reserchPoint[player] >= 2){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("3-2")){
            if(this.reserchPoint[player] >= 4 && this.money[player] >= 1){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("3-3")){
            if(this.reserchPoint[player] >= 8 && this.money[player] >= 1){
                return true;
            } else {
                return false;
            }
        }
        if(place.startsWith("4")){
            if(this.reserchPoint[player] >= 8 && this.money[player] >= 1){
                return true;
            } else {
                return false;
            }
        }
        //タイプが問題ないかを確認
        if(place.startsWith("5")){
            if(typeOfWorker.equals("P") || typeOfWorker.equals("A")){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("6-1")){
            if(typeOfWorker.equals("P") || typeOfWorker.equals("A")){
                return true;
            } else {
                return false;
            }
        }
        if(place.equals("6-2")){
            if(typeOfWorker.equals("P") || this.currentScore[player] >= 10){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    */
    
    //playerID（0,1で指定）して、そのプレイヤーの置ける場所を検索
    public void searchCanputPlace(int[] resources,int depth){
        for(int i = depth * 3; i < (depth * 3 + 3); i++){
            for(int j = 0; j < 15; j++){
            CANPUT_PLACE[i][j] = this.canPutWorker(resources, "P", PLACE_NAMES[j]);
            }
            for(int j = 0; j < 15; j++){
            CANPUT_PLACE[i][j] = this.canPutWorker(resources, "A", PLACE_NAMES[j]);
            }
            for(int j = 0; j < 15; j++){
            CANPUT_PLACE[i][j] = this.canPutWorker(resources, "S", PLACE_NAMES[j]);
            }
        }
    }
    
    //受け取ったゲームリソースのデータを変数にセット
    public void getResource(){
        this.money = this.gameBoard.getMoney();
        this.reserchPoint = this.gameBoard.getReserchPoint();
        this.currentScore = this.gameBoard.getScore();
        this.workerList = this.gameBoard.getWorkerList();
        this.currentStartPlayer = this.gameBoard.getStartPlayer();
        this.trend = this.gameBoard.getTrend();
    }
    
    public void setResource(int depth, int playerID){
        this.resources[depth][0] = playerID;
        this.resources[depth][1] = this.money[playerID];
        this.resources[depth][2] = this.reserchPoint[playerID];
        this.resources[depth][3] = this.currentScore[playerID];
        this.resources[depth][4] = this.workerList[playerID][0];
        this.resources[depth][5] = this.workerList[playerID][1];
        this.resources[depth][6] = this.workerList[playerID][2];
        this.resources[depth][10] = this.currentStartPlayer;
        this.ifTrend[depth] = this.trend;
        
    }
    
    /*
    //役職、置いた場所によってリソースを変化させる
    public void ifplay(int playerID,int[] resources,String worker,String place,String trend){
        int workerID = 0;
        if(worker.equals("A")){
            workerID = 1;
        }else if(worker.equals("S")){
            workerID = 2;
        }
        
        if(place.equals("1-1")){
            switch(workerID){
                case 0:
                    this.zemiCount[0] += 1;
                    break;
                case 1:
                    this.zemiCount[1] += 1;
                    break;
                case 2:
                    this.zemiCount[2] += 1;
                    break;
            }
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.startsWith("2")){
            if(this.money[playerID] >= 2){
                this.money[playerID] += -2;
            }
            if(place.equals("2-1")){
                this.reserchPoint[playerID] += 3;
            }else if(place.equals("2-2")){
                this.reserchPoint[playerID] += 4;
            }else if(place.equals("2-3")){
                this.reserchPoint[playerID] += 5;
            }
            //this.workerList[playerID][workerID] += -1;
        } else {
        }
        if(place.equals("3-1")){
            switch (workerID) {
                case 0:
                    this.currentScore[playerID] += 1;
                    break;
                case 1: 
                    this.currentScore[playerID] += 1;
                    break;
                case 2:
                    this.currentScore[playerID] += 2;
                    break;
                default:
                    break;
            }
            this.reserchPoint[playerID] += -2;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("3-2")){
            switch (workerID) {
                case 0:
                    this.currentScore[playerID] += 3;
                    break;
                case 1: 
                    this.currentScore[playerID] += 4;
                    break;
                case 2:
                    this.currentScore[playerID] += 4;
                    break;
                default:
                    break;
            }
            this.reserchPoint[playerID] += -4;
            this.money[playerID] += -1;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("3-3")){
            switch (workerID) {
                case 0:
                    this.currentScore[playerID] += 7;
                    break;
                case 1: 
                    this.currentScore[playerID] += 6;
                    break;
                case 2:
                    this.currentScore[playerID] += 5;
                    break;
                default:
                    break;
            }
            this.reserchPoint[playerID] += -8;
            this.money[playerID] += -1;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("4-1")){
            switch (workerID) {
                case 0:
                    this.currentScore[playerID] += 8;
                    break;
                case 1: 
                    this.currentScore[playerID] += 7;
                    break;
                case 2:
                    this.currentScore[playerID] += 6;
                    break;
                default:
                    break;
            }
            this.reserchPoint[playerID] += -8;
            this.money[playerID] += -1;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("4-2")){
            switch (workerID) {
                case 0:
                    this.currentScore[playerID] += 7;
                    break;
                case 1: 
                    this.currentScore[playerID] += 6;
                    break;
                case 2:
                    this.currentScore[playerID] += 5;
                    break;
                default:
                    break;
            }
            this.reserchPoint[playerID] += -8;
            this.money[playerID] += -1;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("4-3")){
            switch (workerID) {
                case 0:
                    this.currentScore[playerID] += 6;
                    break;
                case 1: 
                    this.currentScore[playerID] += 5;
                    break;
                case 2:
                    this.currentScore[playerID] += 4;
                    break;
                default:
                    break;
            }
            this.reserchPoint[playerID] += -8;
            this.money[playerID] += -1;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("5-1")){
            switch(workerID){
                case 2:
                    break;
                default:
                    this.money[playerID] += 3;
                    if(this.currentStartPlayer != playerID){
                        if(this.currentStartPlayer == 0){
                            this.currentStartPlayer = 1;
                        }else{
                            this.currentStartPlayer = 0;
                        }
                    }
                    //this.workerList[playerID][workerID] += -1;
            }
        }
        
        if(place.equals("5-2")){
            switch(workerID){
                case 2:
                    break;
                default:
                    this.money[playerID] += 5;
                    this.reserchPoint[playerID] += -1;
                    //this.workerList[playerID][workerID] += -1;
            }
        }
        
        if(place.equals("5-3")){
            switch(workerID){
                case 2:
                    break;
                default:
                    this.money[playerID] += 6;
                    this.reserchPoint[playerID] += -3;
                    //this.workerList[playerID][workerID] += -1;
                    this.gameBoard.setTreand(trend);
            }
        }
        
        if(place.equals("6-1")){
            switch(workerID){
                case 2:
                    break;
                default:
                    this.money[playerID] += -3;
                    this.workerList[playerID][2] += 1;
                    //this.workerList[playerID][workerID] += -1;
            }
        }
        
        if(place.equals("6-2")){
            switch(workerID){
                case 0:
                    if(this.currentScore[playerID] >= 10){
                        this.workerList[playerID][2] += 1;
                        //this.workerList[playerID][workerID] += -1;
                    }
                default:
                    break;
            }
        }
        this.workerList[playerID][workerID] += -1;
        this.usedWorkerList[playerID][workerID] += 1;
        
    }
    */
    
    //役職、置いた場所によってリソースを変化させる
    public void ifplay(int[] resources,String worker,String place,String trend){
        int workerID = 0;
        if(worker.equals("A")){
            workerID = 1;
        }else if(worker.equals("S")){
            workerID = 2;
        }
        
        if(place.equals("1-1")){
            switch(workerID){
                case 0:
                    this.zemiCount[0] += 1;
                    break;
                case 1:
                    this.zemiCount[1] += 1;
                    break;
                case 2:
                    this.zemiCount[2] += 1;
                    break;
            }
        }
        
        if(place.startsWith("2")){
            if(resources[1] >= 2){
                resources[1] += -2;
            }
            if(place.equals("2-1")){
                resources[2] += 3;
            }else if(place.equals("2-2")){
                resources[2] += 4;
            }else if(place.equals("2-3")){
                resources[2] += 5;
            }
        }
        if(place.equals("3-1")){
            switch (workerID) {
                case 2:
                    resources[3] += 2;
                    break;
                default:
                    resources[3] += 1;
                    break;
            }
            resources[2] += -2;
        }
        
        if(place.equals("3-2")){
            switch (workerID) {
                case 0:
                    resources[3] += 3;
                    break;
                default:
                    resources[3] += 4;
                    break;
            }
            resources[2] += -4;
            resources[1] += -1;
        }
        
        if(place.equals("3-3")){
            switch (workerID) {
                case 0:
                    resources[3] += 7;
                    break;
                case 1: 
                    resources[3] += 6;
                    break;
                case 2:
                    resources[3] += 5;
                    break;
                default:
                    break;
            }
            resources[2] += -8;
            resources[1] += -1;
        }
        
        if(place.equals("4-1")){
            switch (workerID) {
                case 0:
                    resources[3] += 8;
                    break;
                case 1: 
                    resources[3] += 7;
                    break;
                case 2:
                    resources[3] += 6;
                    break;
                default:
                    break;
            }
            resources[2] += -8;
            resources[1] += -1;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("4-2")){
            switch (workerID) {
                case 0:
                    resources[3] += 7;
                    break;
                case 1: 
                    resources[3] += 6;
                    break;
                case 2:
                    resources[3] += 5;
                    break;
                default:
                    break;
            }
            resources[2] += -8;
            resources[1] += -1;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("4-3")){
            switch (workerID) {
                case 0:
                    resources[3] += 6;
                    break;
                case 1: 
                    resources[3] += 5;
                    break;
                case 2:
                    resources[3] += 4;
                    break;
                default:
                    break;
            }
            resources[2] += -8;
            resources[1] += -1;
            //this.workerList[playerID][workerID] += -1;
        }
        
        if(place.equals("5-1")){
            switch(workerID){
                case 2:
                    break;
                default:
                    resources[1] += 3;
                    if(resources[7] != resources[0]){
                        if(resources[7] == 0){
                            resources[7] = 1;
                        }else{
                            resources[7] = 0;
                        }
                    }
            }
        }
        
        if(place.equals("5-2")){
            switch(workerID){
                case 2:
                    break;
                default:
                    resources[1] += 5;
                    resources[2] += -1;
            }
        }
        
        if(place.equals("5-3")){
            switch(workerID){
                case 2:
                    break;
                default:
                    resources[1] += 6;
                    resources[2] += -3;
                    this.gameBoard.setTreand(trend);
            }
        }
        
        if(place.equals("6-1")){
            switch(workerID){
                case 2:
                    break;
                default:
                    resources[2] += -3;
                    resources[6] += 1;
                    //this.workerList[playerID][workerID] += -1;
            }
        }
        
        if(place.equals("6-2")){
            switch(workerID){
                case 0:
                    if(resources[3] >= 10){
                       resources[5] += 1;
                        //this.workerList[playerID][workerID] += -1;
                    }
                default:
                    break;
            }
        }
        resources[workerID + 4] += -1;
        resources[workerID + 7] += 1;
        
    }
    
    //resourcesの中の動ける駒を確認
    public int confirmHasWorker(int[] resources){
        int workers = 0;
        for(int i = 4; i < 7; i++){
            workers += resources[i];
        }
        return workers;
    }
    
    //手番の変更
    public void changePlayer(int[] resources){
        //if(this.confirmHasWorker((this.changeFlag + 1 )% 2) >= 0){
        if(this.confirmHasWorker(resources) >= 0){
            this.changeFlag = (this.changeFlag + 1 )% 2;
        }
    }
    //季節の変更
    //今のプレイヤーとその前に動いた
    public void changeSeason(){
        for(int i = 0;i<2;i++){
            for(int j=0;j<3;j++){
                this.workerList[i][j] = this.usedWorkerList[i][j];
                this.usedWorkerList[i][j] = 0;
            }
        }
        //ゼミに置いた時の処理
        this.reserchPoint[0] += this.zemiCount[0] * 2 + this.zemiCount[1] * 3 +
                                ( (this.zemiCount[2] / 2) * (this.zemiCount[0] + this.zemiCount[1]) );
        this.reserchPoint[1] += this.zemiCount[0] * 2 + this.zemiCount[1] * 3 +
                                ( (this.zemiCount[2] / 2) * (this.zemiCount[0] + this.zemiCount[1]) );
        for(int i = 0; i <3; i++){
            this.zemiCount[i] = 0;
        }
    }
    
    public int search(int depth,int PlayerID, int[] resources){
        //this.setResource(depth, myPlayerID);
        //for(int i = 0; i < depth; i++){
        i++;
        this.operateResources = resources;
        this.operateTrend = this.ifTrend[i];
        while(i < depth){
            this.searchCanputPlace(this.operateResources, i);
            for(int k = i * 3; k < (i * 3) + 3; k++){
                for(int j = 0; j < 15; j++){
                    if(this.CANPUT_PLACE[k][j]){
                        //ここで置いた場所によるリソースの変化を計算
                        switch(k % 3){
                            case 0:
                                this.ifplay(this.operateResources, "P", this.PLACE_NAMES[j], this.operateTrend);
                            case 1:
                                this.ifplay(this.operateResources, "A", this.PLACE_NAMES[j], this.operateTrend);
                            case 2:
                                this.ifplay(this.operateResources, "S", this.PLACE_NAMES[j], this.operateTrend);
                        }
                        if(this.confirmHasWorker(this.operateResources) == 0){
                            this.changeSeason();
                        }
                        this.evaluationList[k][j] = this.search(depth,(PlayerID + 1) % 2, this.operateResources);
                    }
                }
            }
        }
        //木の一番下まで到達
        //評価
        return this.operateResources[1] + this.operateResources[2];
    }
    
    //-193point!!!
    
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