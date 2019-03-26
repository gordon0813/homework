
public class Pizza {
	public Pizza(String s, int i, int j, int k) {
		// TODO Auto-generated constructor stub
		size =s;
		cheese=i;
		pepperoni=j;
		ham=k;
	}
	public Pizza() {
		// TODO Auto-generated constructor stub
		size="small";
		cheese=1;
		pepperoni=1;
		ham=1;
	}
	private String size;
	private int cheese;
	private int pepperoni;
	private int ham;
	public String getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	public int getNumberOfCheese() {
		// TODO Auto-generated method stub
		return cheese;
	}
	public int getNumberOfPepperoni() {
		// TODO Auto-generated method stub
		return pepperoni;
	}
	public int getNumberOfHam() {
		// TODO Auto-generated method stub
		return ham;
	}
	public void setSize(String s) {
		// TODO Auto-generated method stub
		size=s;
	}
	public void setNumberOfCheese(int i) {
		// TODO Auto-generated method stub
		cheese=i;
		
	}
	public void setNumberOfPepperoni(int i) {
		// TODO Auto-generated method stub
		pepperoni=i;
	}
	public void setNumberOfHam(int i) {
		// TODO Auto-generated method stub
		ham=i;
	}
	public double calcCost() {
		// TODO Auto-generated method stub
		int temp;
		if (size=="small") {
			temp=10;
		}
		else if(size=="medium") {
			temp=12;
		}
		else {
			temp=14;
		}
		
		return temp+2*(cheese+this.pepperoni+this.ham);
	}
	public boolean equals(Pizza p) {
		if(size.equals(p.size) && cheese==p.cheese && this.pepperoni==p.pepperoni && this.ham==p.ham) {
			return true;
		}
		else {
			return false;
		}
	}
	public String toString() {
		return "size = "+size+", numOfCheese = "+cheese+", numOfPepperoni = "+this.pepperoni+", numOfHam = "+ham;
	}
	
	
}
