
public class SimpleArrayList {
	private Node head;
    private Node tail;
    private int size=0;
    public SimpleArrayList() {
    	head = new Node();
    	tail = new Node();
    	head.changenext(tail);
    	tail.changepast(head);
    }
	public SimpleArrayList(int j) {
		// new j nodes which value are 0
		head = new Node();
    	tail = new Node();
    	head.changenext(tail);
    	tail.changepast(head);
    	for (int i=0;i<j;i++) {
    		this.add(0);
    	}
	}
	private void insertafter(Node in , Node from) {
		//insert in after from 
		from.next.past=in;
		in.next=from.next;
		in.past=from;
		from.next=in;
	}
	private Node find_n(int n) {
		//search n's Node and return it
		Node re=this.head;
		if(n<0 || (n > this.size-1)) {
			return null;
		}
		for(int i=0;i<=n;i++) {
			re=re.next;
		}
		return re;
	}
	public void showall() {
		// show all contents
		Node temp=head.next;
		while(temp!=tail) {
			System.out.print(" "+temp.value+" ");
			temp=temp.next;
		}	
		System.out.println();
	}
	
	public void add(Integer i) {
		// 將一個value是i的Node插在tail前方
		Node temp= new Node(i);
		tail.past.changenext(temp);
		temp.changepast(tail.past);
		temp.changenext(tail);
		tail.changepast(temp);
		this.size+=1;
	}
	
	public Integer get(int i) {
		// 取得第i個Node 的value
		Node temp=find_n(i);
		if(temp==null) {
			return null;
		}
		return temp.value;
	}
	public Integer set(int i, Integer j) {
		// TODO Auto-generated method stub
		Node temp=find_n(i);
		if(temp==null) {
			return null;
		}
		Integer tempi=temp.value;
		temp.value=j;
		return tempi;
	}
	public boolean remove(int i) {
		// TODO Auto-generated method stub
		Node temp= find_n(i);
		if(temp==null) {
			return false;
		}
		if(temp.value==null) {
			
			return false;
		}
		temp.next.past=temp.past;
		temp.past.next=temp.next;
		this.size-=1;
		return true;
	}
	public void clear() {
		// TODO Auto-generated method stub
		this.size=0;
		head.next=this.tail;
		tail.past=this.head;
	}
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	public boolean retainAll(SimpleArrayList list2) {
		// TODO Auto-generated method stub
		boolean flag=true;
		Node temp=head.next;
		while(temp!=tail) {
			if(list2.inthis(temp.value)) {	
			}
			else {
				temp.past.next=temp.next;
				temp.next.past=temp.past;
				this.size-=1;
				flag=false;
			}
			temp=temp.next;
		}

		return !flag;
	}
	private boolean inthis(Integer n) {
		Node temp=head.next;
		while(temp!=tail) {
			if(n==temp.value) {
				return true;
			}
			temp=temp.next;
		}
		return false;
	}
	class Node {
		private Node next ;
		private Node past ;
		private Integer value;
		public Node () {
			next=null;
			past=null;
			value=0;
		}
		public Node (Integer i) {
			next=null;
			past=null;
			value=i;
		}
		public void changepast(Node p) {
			// TODO Auto-generated method stub
			this.past=p;
			
		}
		public void changenext(Node n) {
			// TODO Auto-generated method stub
			this.next=n;
		}
	}
}
