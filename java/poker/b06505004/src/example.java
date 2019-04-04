import java.util.*;
import java.lang.*;
public class example{
    public static void main(String[] argv){
    	int [] card= {0,3,0,1,1,0,0,0,0,0,0};
    	Predict_banker p=new Predict_banker (card,4);
    	//p.debug_node();
    	p.cal_win_rate();
        System.out.println(Arrays.toString(p.re_possibility()));
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//        int wintotal=0;                     //DON'T MODIFY, Result recording
//        int losetotal=0;                    //DON'T MODIFY, Result recording
//        int pushtotal=0;                    //DON'T MODIFY, Result recording
//        for (int round=0;round<20;round++){ //DON'T MODIFY, Play for 20 rounds
//            blackjack b=new blackjack();    //DON'T MODIFY, New Round
//            for(int game=0;game<5;game++){  //DON'T MODIFY, 5 games for each round
//                while(!b.getEnded()){       //DON'T MODIFY
//
//                    ArrayList<Integer> playerHand=b.getPlayerHand(); //API Example
//                    ArrayList<Integer> bankerHand=b.getBankerHand(); //API Example
//                    
//                    b.getMsg();                                      //API Example, you can print game status when debugging
//                     
//                    //TODO: Your strategy, you can implement it as a function, of course.
//                    
//
//                    if(false){               //According to strategy, if you want another card 
//                        b.playerHit();      //API Example
//                    }else{                  //According to strategy, if you don't want another card 
//                        b.playerStand();    //API Example
//                    }
//
//                }
//
//                int gameResult=b.getGameResult(); //API Example
//                ArrayList<Integer> bankerHand=b.getBankerHand(); //API Example, when game ended, you can get all cards in banker's hand
//                if(gameResult==1){          //DON'T MODIFY, Result recording
//                    wintotal+=1;            //DON'T MODIFY, Result recording
//                }else if(gameResult==0){    //DON'T MODIFY, Result recording
//                    pushtotal+=1;           //DON'T MODIFY, Result recording
//                }else{                      //DON'T MODIFY, Result recording
//                    losetotal+=1;           //DON'T MODIFY, Result recording
//                }                           //DON'T MODIFY, Result recording
//                b.continueGame();           //DON'T MODIFY, continue game with left cards
//            }
//        }
//        
//        System.out.println("WIN : "+wintotal);  //DON'T MODIFY, print result
//        System.out.println("PUSH: "+pushtotal); //DON'T MODIFY, print result
//        System.out.println("LOSE: "+losetotal); //DON'T MODIFY, print result
    }
}