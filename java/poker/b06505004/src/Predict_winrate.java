import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;



public class Predict_winrate {
//	int [] card ;
//	public Predict_winrate (int player_point,int [] nowcards) {
//		
//	}
	private int [] card;
	private int numberofcards;
	
	private node_ root;
	private node_ nownode;
	private int bankercard;
	private int handnum=2;
	//private double [] possibility_list;
	// c is the cards that are not include handcard of banker
	public Predict_winrate(int [] c,int[] handcard,int bankercard) {
		this.handnum=handcard.length;
		this.bankercard=bankercard;
		card=new int [11];
		for (int i=1;i<11;i++) {
			card[i]=c[i];
		}
		
		root=new node_(handcard);
		root.parent=root;
		root.create_banker_card(this.copy_cards(), this.bankercard);
		//this.possibility_list=new double[5];
		for (int i=1;i<11;i++) {
			this.numberofcards+=this.card[i];
		}
		this.nownode=this.root;
		//System.out.println("1 "+Arrays.toString(this.card));
	}
	public boolean over_17() {
		if(this.nownode.count_point()>=17) {
			return false;
		}else {
			return true;
		}
	}
	public boolean cal_hit_or_not() {
		double num1=this.root.getNow_winrate();
		double num2=this.root.getNow_lossrate()+0.0000000001;
		while(true) {
			
			if(this.nownode.get_index()>10) {
				
				if(this.nownode==root) {
					
					this.back_to_parent();//將hit or not hit 的勝率比較
					break;
				}else {
					this.back_to_parent();
					this.nownode.next();
				}
			}else {
				//
				if(this.have_card(nownode) && this.nownode.getDeep()<4) {
					
					this.nownode=this.nownode.setchild();
					
					if(this.nownode.getDeep()>2) {
						//沿用parent 的banker 減少計算量
						
						this.nownode.cal_now_win_rate();
						
					}else if(this.nownode.getDeep()>=3){
						//沿用parent 的banker 並回到上一層 減少計算量
						this.nownode.cal_now_win_rate();
						this.back_to_parent();
						
						
					}else {
						//計算banker
						this.nownode.create_banker_card(this.copy_cards(),this.bankercard);
						
					}
				}else {
					
					this.nownode.next();
				}
			}
		}
		//System.out.println(Math.max(root.getNow_winrate(),num1));
		//root.getNow_winrate()/(root.getNow_lossrate()+0.000001)>num1/num2  and
		if(root.getNow_winrate()-num1>0.00001) {;
			return true;
		}else {
			return false;
		}
		
		
		
			
	}
	
	
//	public double [] re_possibility() {
//		double [] re=new double[5];
//		for (int i=0;i<5;i++) {
//			re[i]=this.possibility_list[i];
//		}
//		return re;
//	}
	public int[] copy_cards() {
		int []re=new int [11];
		for (int i=0;i<11;i++) {
			re[i]=this.card[i];
		}
		return re;
	}

	// is there still a card that can be used by nownode.index or not
	private boolean have_card(node_ nod) {
		
		int [] temphistory=nod.get_history();
		int n=0;
		//first two cards already known
		for (int i=this.handnum;i<temphistory.length;i++) {
			if(temphistory[i]==nod.get_index()) {
				n+=1;
			}
		}
		if(this.card[nod.get_index()]-n<=0) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 此node 的勝率和此node的child所帶來的勝率做一次比較留下高的當做此node的勝率
	 * 然後回到parent
	 */
	public void back_to_parent() {
		//System.out.println("happen");
		double tempwin=0;
		double temptie=0;
		double templose=0;
		int [] tempcards=this.copy_cards();
		int [] temphistory=this.nownode.get_history();
		int size=this.numberofcards;
		
		for (int i =this.handnum;i<temphistory.length;i++) {
			tempcards[temphistory[i]]-=1;
			size-=1;
		}
		
		for (int i=1;i<11;i++) {
			
			if(this.nownode.getchild(i)==null) {
				
			}else {
			
			tempwin+=this.nownode.getchild(i).getNow_winrate()*tempcards[i]/size;
			temptie+=this.nownode.getchild(i).getNow_tierate()*tempcards[i]/size;
			templose+=this.nownode.getchild(i).getNow_lossrate()*tempcards[i]/size;
			
			}
		}
		//
		if(tempwin>0.00001 & tempwin/(templose)>this.nownode.getNow_winrate()/(this.nownode.getNow_lossrate())) {
			//System.out.println("true "+tempwin);
			//this.nownode.show_all();
			this.nownode.setNow_winrate(tempwin);
			this.nownode.setNow_tierate(temptie);
			this.nownode.setNow_lossrate(templose);
			
		}
		//System.out.println("false "+tempwin+" "+this.nownode.getNow_winrate());
		this.nownode=this.nownode.parent;
	}
	public void debug_node() {
		node_ testroot=this.root;
		testroot.create_banker_card(this.copy_cards(),this.bankercard );
		node_ testnode=testroot.setchild();
		testnode.cal_now_win_rate();
		node_ testnode1=testnode.setchild();
		testroot.next();
		testroot.show_all();
		testnode.show_all();
		
	}
}


class node_{
	/**
	 * 此node的後續可能 1 to 10
	 */
	private node_ [] child; 
	private int handnum;
	/**
	 * 此時停牌的勝率
	 */
	private double now_winrate;
	public double getNow_winrate() {
		return now_winrate;
	}
	public void setNow_winrate(double now_winrate) {
		this.now_winrate = now_winrate;
	}
	public double getNow_tierate() {
		return now_tierate;
	}
	public void setNow_tierate(double now_tierate) {
		this.now_tierate = now_tierate;
	}
	public double getNow_lossrate() {
		return now_lossrate;
	}
	public void setNow_lossrate(double now_lossrate) {
		this.now_lossrate = now_lossrate;
	}
	/**
	 * 到此node為止的手牌
	 */
	private double now_tierate;
	private double now_lossrate;
	private int[] history; 
	/**
	 * 此node現在在預測拿到哪張牌 1 or 2....or 10
	 */
	private int index;      
	/**
	 * 現在在預測第幾張
	 */
	private int deep;       
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}
	/**
	 * 此時停牌莊家手牌點數的機率 17 to 21
	 */
	private double[] bankercards_rate;  
	public node_ parent;
	
	public node_(int[] handcard) {
		this.handnum=handcard.length;
		history=new int[handnum];
		for (int i=0;i<handnum;i++) {
			history[i]=handcard[i];
		}
		
		index=1;
		child=new node_[11];
		parent=null;
		this.deep=0;
		
	}
	public node_(int newcard,node_ parent) {
		this.handnum=parent.handnum;
		history=new int[parent.history.length+1];
		this.bankercards_rate=parent.bankercards_rate;
		
		for (int i =0;i<parent.history.length;i++) {
			history[i]=parent.history[i];
		}
		history[parent.history.length]=newcard;
		index=1;
		child=new node_[11];
		this.deep=parent.deep+1;
		this.parent=parent;
		
	}
	/**
	 * @param cards  牌堆中可能出現的牌(對起始狀態而言)
	 * @param b_handcard   莊家明牌
	 *  功能 :在這個node中加入莊家最終獲得點數的機率  由17 to 21 五格
	 *       順便產生此時停牌的勝率:void cal_now_win_rate()
	 */
	public void create_banker_card(int[] cards,int b_handcard) {
	    for (int i=handnum;i<this.history.length;i++) {
	    	cards[this.history[i]]-=1;
	    }
		Predict_banker temp_banker =new Predict_banker(cards,b_handcard);
		this.bankercards_rate=temp_banker.cal_win_rate();
		this.cal_now_win_rate();
	}
	/**
	 * 此時停牌的勝率
	 */
	public void cal_now_win_rate() {
		int temp_point=this.count_point();
		this.now_winrate=1;
		this.now_tierate=0;
		if(temp_point>21) {
			this.now_lossrate=1;
			this.now_tierate=0;
			this.now_winrate=0;
		}else {
		for(int i=0;i<5;i++) {
			this.now_winrate-=this.bankercards_rate[i];
		}
		for(int i=0;i<temp_point-17;i++) {
			this.now_winrate+=this.bankercards_rate[i];
		}
		if(temp_point>=17) {
			this.now_tierate=this.bankercards_rate[temp_point-17];
		}
		this.now_lossrate=1-this.now_tierate-this.now_winrate;
		}
	}
	
	public node_ getchild() {
		return this.child[this.index];
	}
	public node_ getchild(int i) {
		return this.child[i];
	}
	//set a child node at where index point
	public node_ setchild() {
		node_ tempnode=new node_(this.index,this);
		this.child[this.index]=tempnode;
		return tempnode;
	}
	
	public int get_index() {
		return this.index;
	}
	
	public int[] get_history() {
		return this.history;
	}
	
	public boolean enter_node() {
		if(this.index>10) {
			return false;
		}
		return true;
	}
	public void next() {
		if(this.child[index]!=null) {
			for (int i=0;i<11;i++) {
				this.child[index].child[i]=null;
			}
		}
		index+=1;
	}
	//count from root to index 
	//eg index 1 history 8 history 1 history 1 : 21
	//
	/**
	 * @return 
	 * 此時預測目標的點數
	 */
	public int count_point() {
		boolean thereisA=false;
		int re_point=0;
		for (int i=0;i<history.length;i++) {
			if(this.history[i]==1) {
				thereisA=true;
			}
			re_point+=history[i];
		}
		if(thereisA & re_point+10<=21) {
			return re_point+10;
		}else {
			return  re_point;
		}
	}
	public int[] re_history() {
		
		return history;
	}
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("index "+index);
		result.append("\nhistory "+history.toString());
		
		return result.toString();	
	}
	public void show_all() {
		StringBuffer result = new StringBuffer();
		result.append("histroy");
		for (int i=0;i<this.history.length;i++) {
			result.append(" "+history[i]);
		}
	result.append(" index "+index);
//		result.append(" banker: ");
//		for (int i=0;i<5;i++) {
//			result.append(" "+this.bankercards_rate[i]);
//		}
		//result.append("\ndeep: "+deep);
		result.append("   point: "+this.count_point());
		result.append("  winrate: "+this.now_winrate);
		result.append("  child: ");
		for (int i=1;i<11;i++) {
			if(this.child[i]!=null) {
				result.append(" "+child[i].getNow_winrate());
			}
		}
		
		
		System.out.println(result.toString());
	}
	
	
}

