
public class ShapeFactory {
	public enum Type{Triangle,Square,Circle};
	public void ShapeFactory() {
		
	}
	public Shape createShape (Type s,double length) {
		switch(s) {
			case Triangle:
				return new Triangle(length);
				
			case Square:
				return new Square(length);
				
			case Circle:
				return new Circle(length);
		}
			
		return null;
	}

}
