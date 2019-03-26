
public class Circle extends Shape {

	public Circle(double length) {
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
		return Math.round(Math.PI*length*length/4*100)/100.;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return Math.round(Math.PI*length*100)/100.;
	}

}
