/* COM1003 - Assignment 3
* Written by: Jamie Duguid, Charles Lee, Huzaifa Ahmed
* First written: 29/11/2016
* Last updated: 19/1/2017
*/
import ShefRobot.*;

/**
 * Navigates a robot through a grid of colored circles joint with black lines.
 *
 * @author Charles Lee
 * @author Huzaifa Ahmed
 * @author Jamie Duguid
 */
public class Assignment3 {
	// Following black lines
	private static int detectBlackAttempts = 0;

	// Circle detection
	private static ColorSensor.Color previousColor;
	private static int timesColorSeen = 0;
	
	// Circle recording
	private static ColorObject[] circleList = new ColorObject[5];
	private static int noOfCircles = 0;

	public static void main(String[] args) {
		// Initialise robot
		ARobot myRobot = new ARobot();

		// Move until a perpendicular black line is found
		while (!myRobot.isColor(ColorSensor.Color.BLACK)) {
			myRobot.moveForward(100);
		}

		// Rotate 90 degrees on to the black line
		myRobot.moveForward(250);
		myRobot.rotateClockwise(-90);

		// Follow the black line
		followLine(myRobot);
	}

	/**
	 * Follows a black line unless a circle is detected.
	 *
	 * @param myRobot The robot to follow the line.
	 */
	public static void followLine(ARobot myRobot) {
		// Move back onto the black line if WHITE is detected
		if (myRobot.isColor(ColorSensor.Color.WHITE)) {
			// Rotate right up to 5 times
			if (detectBlackAttempts < 5) {
				detectBlackAttempts++;
				myRobot.rotateClockwise(5);

			// Reset rotation
			} else {
				detectBlackAttempts = -5;
				myRobot.rotateClockwise(-55);
			}

			// Call followLine again recursively
			followLine(myRobot);
		} else {
			// Reset rotation attempts counter
			detectBlackAttempts = 0;

			// Record the color detected
			ColorSensor.Color currentColor = myRobot.getColor();

			// Increment counter if color was the same as the previous color detected
			if (currentColor == previousColor) {
				timesColorSeen++;
			} else {
				timesColorSeen = 0;
			}

			// Update previous color
			previousColor = currentColor;

			// A circle is detected when the same color is seen 5 times in a row
			if (timesColorSeen == 5 &&
				currentColor == ColorSensor.Color.BLUE ||
				currentColor == ColorSensor.Color.RED ||
				currentColor == ColorSensor.Color.GREEN ||
				currentColor == ColorSensor.Color.YELLOW) {

				// Rotate slightly to confirm that there is a circle
				myRobot.rotateClockwise(10);

				if (myRobot.isColor(currentColor)) {
					myRobot.rotateClockwise(-10);

					System.out.println("Assignment3: Circle detected that is " + currentColor + ".");

					// Reset circle detection counter
					timesColorSeen = 0;

					// Record that a circle was detected
					recordCircle(myRobot, currentColor);

					// See if a circle with this color was already found
					if (ColorObject.matchColor(circleList, noOfCircles)) {
						myRobot.celebrationSong();
						returnToMatch(myRobot, circleList, currentColor);
					} else {
						// Search circle for a black line
						searchCircle(myRobot, currentColor);
					}
				} else {
					myRobot.rotateClockwise(-10);
					followLine(myRobot);
				}
			} else {
				// A black line is detected so move forward
				myRobot.moveForward(100);
				followLine(myRobot);
			}
		}
	}

	/**
	 * Records that a circle was detected.
	 *
	 * @param myRobot The robot that detected the circle.
	 * @param circleColor The color of the circle to record.
	 */
	public static void recordCircle(ARobot myRobot, ColorSensor.Color circleColor) {
		noOfCircles++;
		circleList[noOfCircles - 1] = new ColorObject(circleColor, myRobot.getDirection());
	}

	/**
	 * Searches a circle for a black line to follow next.
	 *
	 * @param myRobot The robot to search the circle.
	 * @param circleColor The color of the circle to search.
	 */
	public static void searchCircle(ARobot myRobot, ColorSensor.Color circleColor) {
		// Check for black line in front of circle
		while (!myRobot.isColor(ColorSensor.Color.WHITE)) {
			myRobot.moveForward(100);
		}

		// If a black line is detected straight away follow it
		if (myRobot.isColor(ColorSensor.Color.BLACK)) {
			followLine(myRobot);
		}

		// Move into position to look for a black line
		myRobot.rotateClockwise(-60);

		// Move until a black line is found
		while (!myRobot.isColor(ColorSensor.Color.BLACK)) {
			if (myRobot.isColor(circleColor)) {
				myRobot.moveForward(300);
				myRobot.rotateClockwise(-8);
			} else {
				myRobot.rotateClockwise(5);
			}
		}

		// Follow black line once one is detected
		followLine(myRobot);
	}

	/**
	 * Returns to original circle when another circle that is the same color is found.
	 *
	 * @param myRobot The robot to move to the original circle.
	 * @param circleList The array of ColorObjects containing the two circles.
	 * @param circleColor The color of the matching circles. 
	 */
	public static void returnToMatch(ARobot myRobot, ColorObject[] circleList, ColorSensor.Color circleColor) {
		// Rotate to face the circle of matching color
		myRobot.rotateClockwise(180);
		myRobot.rotateClockwise(-(int)ColorObject.calcMatchAngle(circleList));

		// Move to the matching circle
		myRobot.moveDegrees((int)ColorObject.calcMatchHyp(circleList));

		while (!myRobot.isColor(circleColor)) {
			myRobot.moveForward(100);
		}

		myRobot.celebrationDance();
	}
}