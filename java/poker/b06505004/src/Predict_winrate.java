import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;



public class Predict_winrate {
	int [] card ;
	public Predict_winrate (int player_point,int [] nowcards) {
		
	}
	
	
	
	
	
	
	
}
//class Decoder {
//	
//	public ArrayList<Integer> before;
//	public ArrayList<Integer> now;
//	private ArrayList<Integer> use;
//	private Dictionary dict = new Hashtable();
//	public Decoder(String s) {
//		dict.put('A', 1);
//		dict.put('2', 2);
//		dict.put('3', 3);
//		dict.put('4', 4);
//		dict.put('5', 5);
//		dict.put('6', 6);
//		dict.put('7', 7);
//		dict.put('8', 8);
//		dict.put('9', 9);
//		dict.put('t', 10);
//		dict.put('J', 10);
//		dict.put('Q', 10);
//		dict.put('K', 10);
//		before = new ArrayList<Integer>();
//		now = new ArrayList<Integer>();
//		int temp;
//		use = before;
//		for (int i = 0; i < s.length(); i++) {
//			temp =(int) dict.get(s.charAt(i));
//			if (temp == ',') {
//			} else if (s.charAt(i) == '/') {
//				use = now;
//
//			} else {
//				use.add(temp);
//			}
//		}
//	}
//
//	public void show() {
//		System.out.println(before);
//		System.out.println(now);
//	}
//}

//class Count {
//	private Dictionary dict = new Hashtable();
//	private int[] cards;  //next
//	private int now_value;   //next
//	private int total_cards_number;   //next
//	private int keep_A = 0;   //next
//	private String now_card;
//	private int before_A_value=0;
//	public Count() {
//		
//
//		this.now_value = 0;
//		this.now_card = "";
//		this.total_cards_number = 52;
//		
//		cards = new int[10];
//		for (int i = 0; i < 9; i++) {
//			this.cards[i] = 4;
//		}
//		this.cards[9] = 16;
//		dict.put('A', 1);
//		dict.put('2', 2);
//		dict.put('3', 3);
//		dict.put('4', 4);
//		dict.put('5', 5);
//		dict.put('6', 6);
//		dict.put('7', 7);
//		dict.put('8', 8);
//		dict.put('9', 9);
//		dict.put('t', 10);
//		dict.put('J', 10);
//		dict.put('Q', 10);
//		dict.put('K', 10);
//	}
//
//	public Answer re_ValueAndPossibility() {
//		this.before_A_value=this.now_value;
//		for (int i = 0; i < this.keep_A; i++) {
//			this.now_value += this.value_of('A');
//		}
//		
//		Answer re = new Answer();
//		if (this.now_value + 11 > 21) {
//			re.value[0] = this.now_value + 1;
//			re.possibility[0] = this.re_possibility(0);
//			re.value[10] = this.now_value + 11;
//			re.possibility[10] = 0;
//		}
//		for (int i = 1; i < 10; i++) {
//			re.value[i] = this.now_value + i + 1;
//			re.possibility[i] = this.re_possibility(i);
//		}
//
//		if (this.now_value + 11 <= 21) {
//			re.value[0] = this.now_value + 1;
//			;
//			re.possibility[0] = 0;
//			re.value[10] = this.now_value + 11;
//			re.possibility[10] += this.re_possibility(0);
//		}
//		if(this.keep_A!=0 && this.before_A_value<11) {
//			for (int i=0;i<11;i++) {
//				re.value[i]=(re.value[i]>21)?(re.value[i]-10):(re.value[i]);
//			}
//		}
//		return re;
//	}
//
//	public void sub(char c) {
//		
//		this.total_cards_number -= 1;
//		int temp;
//		temp = (int) dict.get(c);
//		this.cards[temp - 1] -= 1;
//		// System.out.println("sub "+c);
//	}
//
//	public void addcard(char c) {
//		
//		int temp = 0;
//		this.sub(c);
//		if (c == 'A') {
//			this.keep_A += 1;
//		} else {
//
//			this.now_value += this.value_of(c);
//		}
//		// System.out.println("add "+c);
//
//	}
//
//	public void show() {
//		System.out.println("value:" + this.now_value + " cards:");
//		if (this.now_value + 11 > 21) {
//			System.out.println((this.now_value + 1) + " " + this.re_possibility(0));
//		} else {
//			System.out.println((this.now_value + 11) + " " + this.re_possibility(0));
//		}
//		for (int i = 1; i < 13; i++) {
//			System.out.println((this.now_value + i + 1) + " " + this.re_possibility(i));
//		}
//	}
//
//	public int value_of(char c) {
//		
//		if (c != 'A') {
//			return (int) dict.get(c);
//		} else if (c == 'A') {
//			if (this.now_value + 11 > 21) {
//				return 1;
//			} else {
//				return 11;
//			}
//		} else {
//			System.out.print("fatel error");
//			return 0;
//		}
//	}
//
//	public double re_possibility(int i) {
//
//		return this.cards[i] / (double) this.total_cards_number;
//	}
//
//	public void clear() {
//		this.now_value = 0;
//		this.now_card = "";
//		this.total_cards_number = 52;
//		cards = new int[10];
//		for (int i = 0; i < 9; i++) {
//			cards[i] = 4;
//		}
//		this.before_A_value=0;
//		cards[9] = 16;
//		this.keep_A = 0;
//	}
//
//}
//
//class Answer {
//	private int round;
//	public int[] value;
//	public double[] possibility;
//
//	public Answer() {
//		this.value = new int[11];
//		this.possibility = new double[11];
//	}
//
//	public void set_round(int i) {
//		this.round = i + 1;
//	}
//
//	public void show() {
//		System.out.println("D=" + this.round);
//		for (int i = 0; i < 11; i++) {
//			System.out.println(this.value[i] + " " + this.possibility[i]);
//		}
//	}
//
//	public String output1() {
//		for(int i=1;i<11;i++) {
//			if(this.value[i]<this.value[i-1]) {
//				int [] tempvalue=new int [11];
//				double [] tempP=new double[11];
//				for (int ii=i ,index=0;ii<11;ii++,index++) {
//					tempvalue[index]=this.value[ii];
//					tempP[index]=this.possibility[ii];
//				}
//				for (int ii=0 ,index=11-i;index<11;ii++,index++) {
//					tempvalue[index]=this.value[ii];
//					tempP[index]=this.possibility[ii];
//				}
//				this.value=tempvalue;
//				this.possibility=tempP;
//				break;
//			}
//		}
//		String str = "";
//		str = str + String.format("D=%d%n", this.round);
//		if (this.possibility[0] > 0.0000001) {
//			str = str + String.format("%d,%.3f%n", this.value[0], this.possibility[0]);
//		}
//		for (int i = 1; i < 10; i++) {
//			if (this.possibility[i] > 0.0000001) {
//				str = str + String.format("%d,%.3f%n", this.value[i], this.possibility[i]);
//			}
//		}
//		if (this.possibility[10] > 0.0000001) {
//			str = str + String.format("%d,%.3f%n", this.value[10], this.possibility[10]);
//		}
//		return str;
//	}
//
//	public String output2() {
//		String str = "";
//
//		double blow = 0;
//		for (int i = 0; i < 11; i++) {
//			if (this.value[i] > 21) {
//				blow += this.possibility[i];
//			}
//		}
//		str = String.format("D=%d%n%.3f%n", this.round, blow);
//		return str;
//	}
//
//}