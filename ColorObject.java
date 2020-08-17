/* COM1003 - Assignment 3: ColorObject Class
* Written by: Jamie Duguid, Charles Lee
* First written: 06/12/2016
* Last updated: 19/01/2017
*/
import ShefRobot.*;

/**
 * Creates an ColorObject to represent a coloured circle.
 * The object contains a color, direction and X Y values.
 *
 * @author Jamie Duguid
 * @author Charles Lee
 */
public class ColorObject {
	// Instance Variables
	private ColorSensor.Color color;
	private int direction;
	private int x;
	private int y;

	private static int curX;
	private static int curY;
	private static int counter;

	private static int match1;
	private static int match2;

	// The average distance in tachos between two adjacent circles in the grid
	private static final int CIRCLE_DIST = 440;

	// Constructors

	/**
	* Creates an ColorObject.
	*
	* @param col The color of the circle.
	* @param dir The direction the robot was facing when the circle was detected.
	*/
	public ColorObject(ColorSensor.Color col, int dir) {
		color = col;
		direction = dir;
		counter++;

		// Calculate the x and y positions of the ColorObject
		if (counter > 1) {
			switch (direction) {
				case 0: curY++; break;
				case 1: curX++; break;
				case 2: curY--; break;
				case 3: curX++;
			}
		}

		x = curX;
		y = curY;
	}

	/**
	 * Creates a ColorObject with default values.
	 */
	public ColorObject() {
		color = null;
		direction = 0;
		x = 0;
		y = 0;
	}

	// Instance Methods

	/**
	 * Returns the color of the object.
	 *
	 * @return A ColorSensor enum of the ColorObject's color.
	 */
	public ColorSensor.Color getColor() {
		//return color.toString();
		return color;
	}

	/**
	 * Returns the direction of the object.
	 *
	 * @return The direction the robot was facing when the ColorObject was detected.
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Returns the x coordinate of the object.
	 *
	 * @return The x coordinate of the ColorObject.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of the object.
	 *
	 * @return The y coordinate of the ColorObject.
	 */
	public int getY() {
		return y;
	}

	/**
	* Sets the colour of the object.
	*
	* @param col A ColorSensor enum of the ColorObject's color.
	*/
	public void setColor(ColorSensor.Color col) {
		color = col;
	}

	/**
	 * Sets the direction of the object.
	 *
	 * @param dir The direction the robot is facing when the ColorObject was added.
	 */
	public void setDirection(int dir) {
		direction = dir;
	}

	// Static Methods

	/**
	 * Checks if an array of ColorObjects contains two different circles that are the same color.
	 *
	 * @param colObjArray The array containing the ColorObjects to check.
	 * @param noOfCircles The number of circles present in the array.
	 *
	 * @return Returns true if two circles in the array are the same color.
	 */
	public static Boolean matchColor(ColorObject[] colObjArray, int noOfCircles) {
		// Iterate through each circle in the array
		for (int firstCirNum = 0; firstCirNum < noOfCircles; firstCirNum++) {
			// Compare against each other circle the array
			for (int secondCirNum = 0; secondCirNum < noOfCircles; secondCirNum++) {
				if (colObjArray[firstCirNum].color == colObjArray[secondCirNum].color && (firstCirNum!=secondCirNum)) {
					// Record the position of the match
					System.out.println("####### ColorObject: CIRCLE COLOR MATCH FOUND. #######");
					match1 = firstCirNum;
					match2 = secondCirNum;

					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Calculates the distance in tachos between the two circles found with "matchColor".
	 *
	 * @param circleList The array of ColorObjects containing the two circles.
	 *
	 * @return The distance in tachos between the two circles.
	 */
	public static double calcMatchHyp(ColorObject[] circleList) {
		double xDif = circleList[match1].getX() - circleList[match2].getX();
		double yDif = circleList[match1].getY() - circleList[match2].getY();
		double hyp =  Math.hypot((double)xDif, (double)yDif);

		return hyp * CIRCLE_DIST;
	}

	/**
	 * Calculates the angle between the second and first circles found with "matchColor()".
	 *
	 * @param circleList The array of ColorObjects containing the two circles.
	 *
	 * @return The distance in tachos between the two circles.
	 */
	public static double calcMatchAngle(ColorObject[] circleList) {
		double xDif = circleList[match1].getX() - circleList[match2].getX();
		double yDif = circleList[match1].getY() - circleList[match2].getY();
		double angle = Math.toDegrees(Math.atan((double)xDif / (double)yDif));

		return angle;
	}

	public static void main(String [] args) {
		ColorObject test = new ColorObject(ColorSensor.Color.BLACK, 1);
		System.out.println("Color: " + test.getColor() + " Direction: " + test.getDirection());
	}
}
