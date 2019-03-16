package poker_game;

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class HW1_main {

	public static void main(String[] args) throws Exception {
		// ====================== read file start==================
		String path="D:\\大二\\java\\";//檔案位置
		File file = new File(path+"input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		ArrayList<String> read_s = new ArrayList<String>();
		while ((st = br.readLine()) != null) {

			st = st.replaceAll("10", "t");
			System.out.println(st);
			read_s.add(st);
		}

		br.close();
		// ====================== read file end==================
		String towrite1="";
		String towrite2="";
		// towrite1 2 是最終寫入txt的String
		
		for (int i = 0; i < read_s.size(); i++) {
			Decoder d = new Decoder(read_s.get(i));
			//將檔案中的資料轉成 before now 兩個 Arraylist 存在Decoder中
			//before 表已出現過的牌  After表手牌
			Count c = new Count();
			for (int ii = 0; ii < d.before.size(); ii++) {
				c.sub(d.before.get(ii));//去除已出現過的牌
			}
			
			for (int ii = 0; ii < d.now.size(); ii++) {
				c.addcard(d.now.get(ii));//將牌加入手牌
			}
			
			Answer ans = c.re_ValueAndPossibility();//將預測結果輸出成Answer data type
			ans.set_round(i);//設定第幾輪
			
			towrite1=towrite1+ans.output1();//將Answer datatype 轉成String(for Q1)
			towrite2=towrite2+ans.output2();//將Answer datatype 轉成String(for Q2)
			// ans.show();
			// c.show();
			c.clear();//清理Count 給下一輪繼續用
		}
		//========================write file starts==========================
		System.out.print(towrite1);
		System.out.print(towrite2);
		BufferedWriter writer = new BufferedWriter(new FileWriter(path+"output1.txt"));
	    writer.write(towrite1);
	    writer.close();
	    writer = new BufferedWriter(new FileWriter(path+"output2.txt"));
	    writer.write(towrite2);
	    writer.close();
	    //==========================write file end====================
	}

}

class Decoder {
	// 輸入: 2,4,6,7/K,3,2 (String)
	// 輸入的10要轉成t
	// 將 輸入 decode 成 eg: before(發過的牌) => [2,4,6,7] , now(手牌)=> [K,3,2] (Arraylist)
	public ArrayList<Character> before;
	public ArrayList<Character> now;
	private ArrayList<Character> use;

	public Decoder(String s) {
		before = new ArrayList<Character>();
		now = new ArrayList<Character>();
		char temp;
		use = before;
		for (int i = 0; i < s.length(); i++) {
			temp = s.charAt(i);
			if (temp == ',') {
			} else if (s.charAt(i) == '/') {
				use = now;

			} else {
				use.add(temp);
			}
		}
	}

	public void show() {
		System.out.println(before);
		System.out.println(now);
	}
}

class Count {
	private Dictionary dict = new Hashtable();
	private int[] cards;
	private int now_value;
	private int total_cards_number;
	private int keep_A = 0;
	private String now_card;

	public Count() {
		// 建立一個 牌 to value dict

		this.now_value = 0;
		this.now_card = "";
		this.total_cards_number = 52;
		// 建立一個 cards ,cards[0] => A剩餘張數, cards[1] => 2剩餘張數.....
		cards = new int[10];
		for (int i = 0; i < 9; i++) {
			this.cards[i] = 4;
		}
		this.cards[9] = 16;
		dict.put('A', 1);
		dict.put('2', 2);
		dict.put('3', 3);
		dict.put('4', 4);
		dict.put('5', 5);
		dict.put('6', 6);
		dict.put('7', 7);
		dict.put('8', 8);
		dict.put('9', 9);
		dict.put('t', 10);
		dict.put('J', 10);
		dict.put('Q', 10);
		dict.put('K', 10);
	}

	public Answer re_ValueAndPossibility() {
		for (int i = 0; i < this.keep_A; i++) {
			this.now_value += this.value_of('A');
		}
		this.keep_A = 0;
		Answer re = new Answer();
		if (this.now_value + 11 > 21) {
			re.value[0] = this.now_value + 1;
			re.possibility[0] = this.re_possibility(0);
			re.value[10] = this.now_value + 11;
			re.possibility[10] = 0;
		}
		for (int i = 1; i < 10; i++) {
			re.value[i] = this.now_value + i + 1;
			re.possibility[i] = this.re_possibility(i);
		}

		if (this.now_value + 11 <= 21) {
			re.value[0] = this.now_value + 1;
			;
			re.possibility[0] = 0;
			re.value[10] = this.now_value + 11;
			re.possibility[10] += this.re_possibility(0);
		}
		return re;
	}

	public void sub(char c) {
		// 將 牌c 剩餘張數-1
		this.total_cards_number -= 1;
		int temp;
		temp = (int) dict.get(c);
		this.cards[temp - 1] -= 1;
		// System.out.println("sub "+c);
	}

	public void addcard(char c) {
		// 將牌 c 加入手中
		int temp = 0;
		this.sub(c);
		if (c == 'A') {
			this.keep_A += 1;
		} else {

			this.now_value += this.value_of(c);
		}
		// System.out.println("add "+c);

	}

	public void show() {
		System.out.println("value:" + this.now_value + " cards:");
		if (this.now_value + 11 > 21) {
			System.out.println((this.now_value + 1) + " " + this.re_possibility(0));
		} else {
			System.out.println((this.now_value + 11) + " " + this.re_possibility(0));
		}
		for (int i = 1; i < 13; i++) {
			System.out.println((this.now_value + i + 1) + " " + this.re_possibility(i));
		}
	}

	public int value_of(char c) {
		// 傳回此時牌c代表的value
		if (c != 'A') {
			return (int) dict.get(c);
		} else if (c == 'A') {
			if (this.now_value + 11 > 21) {
				return 1;
			} else {
				return 11;
			}
		} else {
			System.out.print("fatel error");
			return 0;
		}
	}

	public double re_possibility(int i) {
//回傳cards[i] 出線機率  eg (i==0)=> return A剩餘/total cards amount  
		return this.cards[i] / (double) this.total_cards_number;
	}

	public void clear() {
		this.now_value = 0;
		this.now_card = "";
		this.total_cards_number = 52;
		cards = new int[10];
		for (int i = 0; i < 9; i++) {
			cards[i] = 4;
		}
		cards[9] = 16;
		this.keep_A = 0;
	}

}

class Answer {
	private int round;
	public int[] value;
	public double[] possibility;

	public Answer() {
		this.value = new int[11];
		this.possibility = new double[11];
	}

	public void set_round(int i) {
		this.round = i + 1;
	}

	public void show() {
		System.out.println("D=" + this.round);
		for (int i = 0; i < 11; i++) {
			System.out.println(this.value[i] + " " + this.possibility[i]);
		}
	}

	public String output1() {
		String str = "";
		str = str + String.format("D=%d%n", this.round);
		if (this.possibility[0] > 0.0000001) {
			str = str + String.format("%d,%.3f%n", this.value[0], this.possibility[0]);
		}
		for (int i = 1; i < 10; i++) {
			str = str + String.format("%d,%.3f%n", this.value[i], this.possibility[i]);
		}
		if (this.possibility[10] > 0.0000001) {
			str = str + String.format("%d,%.3f%n", this.value[10], this.possibility[10]);
		}
		return str;
	}

	public String output2() {
		String str = "";

		double blow = 0;
		for (int i = 0; i < 11; i++) {
			if (this.value[i] > 21) {
				blow += this.possibility[i];
			}
		}
		str = String.format("D=%d%n%.3f%n", this.round, blow);
		return str;
	}

}
