
public class Square extends Shape {

	public Square(double length) {
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
		return Math.round(this.length*this.length*100)/100.;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return Math.round(4*length*100)/100.;
	}

}
