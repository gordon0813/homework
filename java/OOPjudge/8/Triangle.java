
public class Triangle extends Shape {

	public Triangle(double length) {
	    super(length);
	    
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLength(double length) {
		// TODO Auto-generated method stub
		this.length=length;
		
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return Math.round((Math.sqrt(3)/4*Math.pow(this.length,2))*100)/100.;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return Math.round(3*length*100)/100.;
	}

}
