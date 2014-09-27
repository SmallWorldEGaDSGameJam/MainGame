package AppletSource.Utilities;

/**
 * This class saves a 2-dimensional vector and lets you do operations on them
 * @author Fox Tail Games 
 * @version 1
 */
public class Vector2
{
	public double x;
	public double y;
	
	/**
	 * Default constructor. Sets x and y to 0
	 */
	public Vector2()
	{
		this(0, 0);
	}
	
	/**
	 * Main constructor that sets both x and y
	 * @param x Sets x to the number entered. Type double.
	 * @param y Sets y to the number entered. Type double.
	 */
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Secondary constructor. Sets x and y to the same number
	 * @param n Sets x and y to this number
	 */
	public Vector2(double n)
	{
		this(n, n);
	}
	
	/**
	 * Gets the normalised vector (Vector of length 1). The value of the instance of Vector2 
	 * is not changed.
	 * @return The vector2 reduced to length 1 and angle not changed 
	 */
	public Vector2 normalize()
	{
		double length = length();
		
		return new Vector2(x / length, y / length);
	}
	
	/**
	 * Gets the normalised vector (Vector of length 1). The value of the instance of Vector2 
	 * is not changed.
	 * @param scale The amount the vector2 will be scaled to.
	 * @return The vector2 reduced to length <code>scale</code> and angle not changed 
	 */
	public Vector2 normalize(double scale)
	{
		double length = length();
		
		return new Vector2(x / length * scale, y / length* scale);
	}
	
	/**
	 * The length (or magnitude) of the vector2
	 * @return The magnitude of the vector2, based on the pythagorean theorem
	 */
	public double length()
	{
		return Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
	}
	
	/**
	 * The angle of the vector2
	 * @return The angle calculated based on trigonometry
	 */
	public double angle()
	{
		return Math.asin(x/length());
	}
	
	/**
	 * Adds up this vector2 and another vector2 and returns the result. The current vector2 is not changed
	 * @param a The vector2 which to add
	 * @return a new vector2 formed by the addition of the x's and y's of this vector2 and a
	 */
	public Vector2 add(Vector2 a)
	{
		return new Vector2(x + a.x, y + a.y);
	}
	
	/**
	 * Adds two vectors that are inputed and returns the result. No vector2 is changed
	 * @param a Vector2 added to b
	 * @param b Vector2 added to a
	 * @return The addition of the x's and the y's of the Vector2's a and b
	 */
	public static Vector2 add(Vector2 a, Vector2 b)
	{
		return new Vector2(a.x + b.x, a.y + b.y);
	}
	
	/**
	 * Substracts up this vector2 and another vector2 and returns the result. The current vector2 is not changed
	 * @param a The vector2 which to add
	 * @return A new vector2 formed by the substraction of the x's and y's of this vector2
	 * and a <code>(this - a)</code> or <code>(this.x - a.x, this.y - a.y)</code>
	 */
	public Vector2 subtract(Vector2 a)
	{
		return new Vector2(x - a.x, y - a.y);
	}
	
	/**
	 * Subtracts two vectors that are inputed and returns the result. No vector2 is changed
	 * @param a Vector2 added to b
	 * @param b Vector2 added to a
	 * @return The subtraction of the x's and the y's of the Vector2's a and b <code>(a - b)
	 * </code> or <code>(a.x - b.x, a.y - b.y)</code> 
	 */
	public static Vector2 substract(Vector2 a, Vector2 b)
	{
		return new Vector2(a.x - b.x, a.y - b.y);
	}
	
	/**
	 * It does a simple <code>x*n and y*n</code>.
	 * @param n Number to multiply vector to.
	 * @return The current vector times n
	 */
	public Vector2 multiply(double n)
	{
		return new Vector2(this.x * n, this.y * n);
	}
	
	/**
	 * Dot product of this vector and a second vector.
	 * @param v Vector to multiply
	 * @return The product of the vectors
	 */
	public Vector2 multiply(Vector2 v)
	{
		return new Vector2(this.x * v.x, this.y * v.y);
	}
	
	/**
	 * Product of one vector and a number.
	 * @param v Vector to multiply
	 * @return The product of the vectors
	 */
	/**
	 * Product of one vector and a number
	 * @param n number to multiply vector by
	 * @param v Vector to multiply
	 * @return (v.x * n, v.y * n)
	 */
	public static Vector2 multiply(double n, Vector2 v)
	{
		return new Vector2(v.x * n, v.y * n);
	}
	
	/**
	 * Dot product of vector v1 and vector v2 
	 * @param v1 Vector 1
	 * @param v2 Vector 2
	 * @return (v1.x * v2.x, v1.y * v2.y)
	 */
	public static Vector2 multiply(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.x * v2.x, v1.y * v2.y);
	}
	
	/**
	 * Creates a vector2 with values (0, 0). Equivalent to <code>new Vector2()</code>, <code>new Vector2(0, 0)</code> and <code>new Vector2(0)</code>
	 * @return Returns the Vector2 (0, 0)
	 */
	public static Vector2 Zero(){return new Vector2();}
	
	/**
	 * Returns the values of x and y in the following format:<br />
	 * <code>(x, y)</code>
	 */
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
