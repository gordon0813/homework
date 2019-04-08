import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Recoder {
	int[] cards;
	int[] tempcards;
	int[] handcard;
	
	int bankercard;
	
	public Recoder() {
		cards=new int[11];
		for (int i=1;i<10;i++) {
			cards[i]=4;
		}
		cards[10]=16;
		this.tempcards=this.copy_cards();
	}
	private int[] copy_cards() {
		int [] tempcard =new int[11];
		for (int i=1;i<11;i++) {
			tempcard[i]=this.cards[i];
		}
		return tempcard;
	}
	public void addhandcard(ArrayList<Integer> arrayList) {
		this.handcard=new int[arrayList.size()];
		for (int i=0;i<arrayList.size();i++) {
			
			if(arrayList.get(i)>10) {
				this.tempcards[10]-=1;
				this.handcard[i]=10;
				
			}else {
				this.tempcards[arrayList.get(i)]-=1;
				this.handcard[i]=arrayList.get(i);
			}
		}
	}

	public void endgame(ArrayList<Integer> playerHand, ArrayList<Integer> bankerHand) {
		// TODO Auto-generated method stub
		for (int i = 0; i < playerHand.size(); i++) {

			if (playerHand.get(i) > 10) {
				this.cards[10] -= 1;
			} else {
				this.cards[playerHand.get(i)] -= 1;
			}
		}
		for (int i = 0; i < bankerHand.size(); i++) {

			if (bankerHand.get(i) > 10) {
				this.cards[10] -= 1;
			} else {
				this.cards[bankerHand.get(i)] -= 1;
			}
		}
		this.tempcards=this.copy_cards();
	}
	public boolean hit_or_not() {
		// TODO Auto-generated method stub
		Predict_winrate p=new Predict_winrate(this.cards,this.handcard,this.bankercard);
		return p.cal_hit_or_not();
	}
	public boolean over_17() {
		Predict_winrate p=new Predict_winrate(this.cards,this.handcard,this.bankercard);
		return p.over_17();
	}
	public void addbankercard(ArrayList<Integer> bankerHand) {
		// TODO Auto-generated method stub
		int n=bankerHand.get(1);
		if(n>10) {
			this.tempcards[10]-=1;
			this.bankercard=10;
		}else {
			this.tempcards[n]-=1;
			this.bankercard=n;
		}
		
	}
	public void continue_() {
		this.tempcards=this.copy_cards();
	}
	public void show_all() {
		System.out.println(Arrays.toString(this.cards)+" temp "+Arrays.toString(this.tempcards)+" hand "+Arrays.toString(this.handcard)+" b "+this.bankercard);
	}
	public boolean ran_() {
		Random ran = new Random();
		if(ran.nextInt(10)>5) {
			return true;
		}else {
			return false;
		}
	}
	
}
