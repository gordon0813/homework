
public class Predict_banker {
	private int [] card;
	private int numberofcards;
	private node root;
	private node nownode;
	private double [] possibility_list;
	// c is the cards that are not include handcard of banker
	public Predict_banker(int [] c,int handcard) {
		card=new int [11];
		for (int i=1;i<11;i++) {
			card[i]=c[i];
		}
		root=new node(handcard);
		this.possibility_list=new double[5];
		for (int i=1;i<11;i++) {
			this.numberofcards+=this.card[i];
		}
		this.nownode=this.root;
	}
	public double[] cal_win_rate() {
		while(true) {
			//nownode.show_all();
			
			if(nownode.enter_node()) {
				if(this.have_card(nownode)) {
					int temp_point=nownode.count_point();
					if(temp_point>21) {
						nownode.next();
					}else if(temp_point<17){
						nownode=nownode.setchild();
					}else {
						//System.out.println("get");
						this.possibility_list[temp_point-17]+=this.count_possiblity(nownode);
						nownode.next();
					}
					
				}else {
					nownode.next();
				}
			}else {
				nownode=nownode.parent;
				if(nownode==null) {
					break;
				}
				nownode.next();	
			}	
		}
		
		
		return possibility_list;
		
	}
	
	
	public double [] re_possibility() {
		double [] re=new double[5];
		for (int i=0;i<5;i++) {
			re[i]=this.possibility_list[i];
		}
		return re;
	}
	public int[] copy_cards() {
		int []re=new int [11];
		for (int i=0;i<11;i++) {
			re[i]=this.card[i];
		}
		return re;
	}

	// is there still a card that can be used by nownode.index or not
	private boolean have_card(node nod) {
		
		int [] temphistory=nod.get_history();
		int n=0;
		
		for (int i=1;i<temphistory.length;i++) {
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
	public double count_possiblity(node n) {
		double p=1;
		int []temphistory=n.get_history();
		int []tempcards=this.copy_cards();
		int i;
		for (i =1;i<temphistory.length;i++) {
			p=p*tempcards[temphistory[i]]/(this.numberofcards-i+1);
			
			tempcards[temphistory[i]]-=1;
		}
		p=p*tempcards[n.get_index()]/(this.numberofcards-i+1);
		return p;
	}
	public void debug_node() {
		node testroot=new node(1);
		testroot.show_all();
		node test_node=testroot.setchild();
		test_node.show_all();
		
		node test_node1=test_node.setchild();
		
		test_node1.show_all();
		System.out.println(this.have_card(test_node1));
		System.out.println(this.count_possiblity(test_node1));
		node test_node2=test_node1.setchild();
		
		test_node2.show_all();
		System.out.println(this.have_card(test_node2));
//		test_node.show_all();
//		test_node.parent.show_all();
//		
//		testroot.next();
//		test_node=testroot.setchild();
//		test_node.show_all();
//		test_node=testroot.getchild();
//		test_node.show_all();
//		System.out.println(test_node.count_point());
//		test_node.parent.show_all();
//		test_node.next();
//		test_node.show_all();
//		System.out.println(test_node.count_point());
	}
	
//	public double[] return_predict() {
//		while(true) {
//		}
//		return null;
//	}

}
class node{
	private node [] child;
	private int[] history;
	private int index;
	public node parent;
	
	public node(int handcard) {
		history=new int[1];
		history[0]=handcard;
		index=1;
		child=new node[11];
		parent=null;
	}
	public node(int newcard,node parent) {
		history=new int[parent.history.length+1];
		for (int i =0;i<parent.history.length;i++) {
			history[i]=parent.history[i];
		}
		history[parent.history.length]=newcard;
		index=1;
		child=new node[11];
		this.parent=parent;
	}
	
	
	public node getchild() {
		return this.child[this.index];
	}
	//set a child node at where index point
	public node setchild() {
		node tempnode=new node(this.index,this);
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
		
		this.child[index]=null;
		index+=1;
	}
	//count from root to index 
	//eg index 1 history 8 history 1 history 1 : 21
	
	public int count_point() {
		boolean thereisA=false;
		int re_point=0;
		for (int i=0;i<history.length;i++) {
			if(this.history[i]==1) {
				thereisA=true;
			}
			re_point+=history[i];
		}
		if(this.index==1) {
			thereisA=true;
		}
		re_point+=this.index;
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
		
		for (int i=0;i<this.history.length;i++) {
			result.append(" history "+history[i]);
		}
		
		result.append("index "+index);
		System.out.println(result.toString());
	}
	
	
}

