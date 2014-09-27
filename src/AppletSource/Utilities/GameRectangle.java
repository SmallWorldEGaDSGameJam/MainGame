package AppletSource.Utilities;

public class GameRectangle {
	Vector2 position;
	Vector2 dimensions;

	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	public GameRectangle(double x, double y, double width, double height)
	{
		position = new Vector2(x, y);
		dimensions = new Vector2(width, height);
	}
	
	public GameRectangle()
	{
		position = new Vector2();
		dimensions = new Vector2();
	}
	
	public void setX(double x) { position.x = x; }
	public void setY(double y) { position.y = y; }
	public void setWidth(double width) { dimensions.x = width; }
	public void setHeight(double height) { dimensions.y = height; }
	
	public double getX() { return position.x; }
	public double getY() { return position.y; }
	public double getWidth() { return dimensions.x; }
	public double getHeight() { return dimensions.y; }
	
	public boolean contains(Vector2 v) { 
		return v.x >= position.x && v.x <= position.x + dimensions.x &&
				v.y >= position.y && v.y <= position.y + dimensions.y; 
	}
	
//	public boolean intersects(GameRectangle r) {
//		boolean horizontalInside = position.x + dimensions.x > r.position.x && position.x < r.position.x + r.dimensions.x;
//		boolean verticalInside = position.y + dimensions.y > r.position.y && position.y < r.position.y + r.dimensions.y;
//
//		return horizontalInside && verticalInside;
//	}
	
	public int intersects(GameRectangle r){
		double x = this.getX();
        double x2 = x + this.getWidth();
        double y = this.getY();
        double y2 = y + this.getHeight();
        
        double i = r.getX();
        double i2 = i + r.getWidth();
        double j = r.getY();
        double j2 = j + r.getHeight();
        
        double i_x = Math.abs(i - x);
		double x_i2 = Math.abs(x - i2);
		double i_x2 = Math.abs(i - x2);
		double x2_i2 = Math.abs(x2 - i2);
        
        double j_y = Math.abs(j - y);
		double y_j2 = Math.abs(y - j2);
		double j_y2 = Math.abs(j - y2);
		double y2_j2 = Math.abs(y2 - j2);
		
        if (y <= j2 && y > j && ((i >= x && i2 <= x2) || (i2 > x && i < x) || (i < x2 && i2 > x2)) && (j_y > y_j2 && i_x <= x_i2 && i_x2 >= x2_i2)){
        	return UP;
		} else if (j < y2 && j2 > y2 && ((i >= x && i2 <= x2) || (i2 > x && i < x) || (i < x2 && i2 > x2)) && ( j_y2 < y2_j2)){
			return DOWN;
		} else if (i2 > x && i < x && ((j >= y && j2 <= y2) || (j2 > y && j < y) || (j < y2 && j2 > y2)) && ( i_x > x_i2)) {
			return LEFT;
		} else if (i > x && i < x2 && ((j >= y && j2 <= y2) || (j2 > y && j < y) || (j < y2 && j2 > y2)) && ( (i_x2 < x2_i2))){
			return RIGHT;
		}
        return -1;
	}
	
	public String toString()
	{
		return "{" + position.x + ", " + position.y + ", " + dimensions.x + ", " + dimensions.y + "}";
	}
}
