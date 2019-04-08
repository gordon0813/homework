import java.util.*;
import java.util.Random;
import java.lang.*;

public class example {

	public static void main(String[] argv) {
//		int[] card = { 0, 4, 3, 1, 4, 3, 3, 2, 3, 0, 0 };
//		Predict_banker p = new Predict_banker(card, 10);
//		// p.debug_node();
//		int [] tempcard = {1,5,3};
//		Predict_winrate pw =new Predict_winrate(card, tempcard, 4);
//		System.out.println(pw.cal_hit_or_not());
//		p.cal_win_rate();
//		System.out.println(Arrays.toString(p.re_possibility()));
		
		
		
		
		
		
		
		
		


		int wintotal = 0; // DON'T MODIFY, Result recording
		int losetotal = 0; // DON'T MODIFY, Result recording
		int pushtotal = 0; // DON'T MODIFY, Result recording
		for (int round = 0; round < 20; round++) { // DON'T MODIFY, Play for 20 rounds
			blackjack b = new blackjack(); // DON'T MODIFY, New Round
			Recoder rec=new Recoder();
			for (int game = 0; game < 5; game++) { // DON'T MODIFY, 5 games for each round
				
				while (!b.getEnded()) { // DON'T MODIFY
					
					ArrayList<Integer> playerHand = b.getPlayerHand(); // API Example
					ArrayList<Integer> bankerHand = b.getBankerHand(); // API Example
					rec.addhandcard(b.getPlayerHand());
					rec.addbankercard(b.getBankerHand());
					
					System.out.println(bankerHand.toString());
					b.getMsg(); // API Example, you can print game status when debugging
					rec.show_all();
					// TODO: Your strategy, you can implement it as a function, of course.
//rec.hit_or_not()               5000*5 round  win/tie/loss : 11201/1952/11822  48.6%
//over_17() when >=17 stand      5000*5 round  win/tie/loss : 10387/2283/12330  45.7%
					if (rec.hit_or_not() ) { // According to strategy, if you want another card
						b.playerHit(); // API Example
						rec.continue_();
					} else { // According to strategy, if you don't want another card
						b.playerStand(); // API Example
					}
					

				}

				int gameResult = b.getGameResult(); // API Example
				ArrayList<Integer> bankerHand = b.getBankerHand(); // API Example, when game ended, you can get all
				rec.endgame(b.getPlayerHand(),b.getBankerHand());													// cards in banker's hand
				if (gameResult == 1) { // DON'T MODIFY, Result recording
					wintotal += 1; // DON'T MODIFY, Result recording
				} else if (gameResult == 0) { // DON'T MODIFY, Result recording
					pushtotal += 1; // DON'T MODIFY, Result recording
				} else { // DON'T MODIFY, Result recording
					losetotal += 1; // DON'T MODIFY, Result recording
				} // DON'T MODIFY, Result recording
				b.continueGame(); // DON'T MODIFY, continue game with left cards
			}
		}

		System.out.println("WIN : " + wintotal); // DON'T MODIFY, print result
		System.out.println("PUSH: " + pushtotal); // DON'T MODIFY, print result
		System.out.println("LOSE: " + losetotal); // DON'T MODIFY, print result
	}
}