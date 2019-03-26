
public class PizzaOrder {
	private int num_of_pizza;
	
	private Pizza p1;
	private Pizza p2;
	private Pizza p3;
	public PizzaOrder(){
		
	}
	public boolean setNumberPizzas(int i) {
		// TODO Auto-generated method stub
		if(0<i && i<=3) {
			this.num_of_pizza=i;
			return true;
		}
		else {
			return false;
		}
	}
	public void setPizza1(Pizza pizza1) {
		// TODO Auto-generated method stub
		p1=pizza1;
	}
	public void setPizza2(Pizza pizza2) {
		// TODO Auto-generated method stub
		p2=pizza2;
	}
	public void setPizza3(Pizza pizza3) {
		// TODO Auto-generated method stub
		p3=pizza3;
	}
	public double calcTotal() {
		// TODO Auto-generated method stub
		double total=0;
		switch(this.num_of_pizza) {
		case 3:
			total=total+p3.calcCost();
		case 2:
			total=total+p2.calcCost();
		case 1:
			total=total+p1.calcCost();
		}
		return total;
	}
	
	
	
}
