/* COM1003 - Assignment 3: ARobot Class
* Written by: Charles Lee, Huzaifa Ahmed
* First written: 06/12/2016
* Last updated: 19/1/2017
*/
import ShefRobot.*;

/**
 * Represents a robot.
 *
 * @author Charles Lee
 * @author Huzaifa Ahmed
 */
public class ARobot {
	// Robot instance variables
	private Robot myRobot;
	private Motor leftMotor;
	private Motor rightMotor;
	private final int SPEED;
	private ColorSensor myColorSensor;
	private Speaker mySpeaker;
	
	// Robot position variables
	private int direction = 0;	
	private int curRotation = 0;

	/**
	 * Moves the robot forwards for a set amount of time.
	 *
	 * @param time The time in milliseconds to move forwards for.
	 */
	public void moveForward(int time) {
		leftMotor.setSpeed(SPEED);
		rightMotor.setSpeed(SPEED);
		leftMotor.forward();
		rightMotor.forward();
		myRobot.sleep(time);
	}

	/**
	 * Rotates the robot clockwise by a specific angle.
	 *
	 * @param angle The angle in degrees to rotate clockwise (can be negative to rotate anti-clockwise).
	 */
	public void rotateClockwise(int angle) {
		rightMotor.setSpeed(SPEED);
		leftMotor.setSpeed(SPEED);
		rightMotor.rotate(angle*2, true);
		leftMotor.rotate(-angle*2);

		// Update log of the robot's current angle
		curRotation += angle;

		// Adjust rotation to be between 0 and 360 degrees
		if (curRotation > 360) {
			curRotation = curRotation - 360;
		} else if (curRotation < 0) {
			curRotation = 360 + curRotation;
		}

		// Update direction based on nearest quadrant
		if (curRotation < 45 || curRotation > 315) {
			direction = 0;
		} else if (curRotation < 135) {
			direction = 1;
		} else if (curRotation < 225) {
			direction = 2;
		} else {
			direction = 3;
		}
	}

	/**
	 * Moves the robot wheels for a particular number of degrees.
	 * 
	 * @param angle The amount of degrees to move the robot for.
	 */
	public void moveDegrees(int angle) {
		rightMotor.setSpeed(SPEED);
		leftMotor.setSpeed(SPEED);
		rightMotor.rotate(angle, true);
		leftMotor.rotate(angle);
	}

	/**
	 * Returns the direction the robot is currently facing.
	 * 
	 * @return An integer between 0 and 3 representing the robot's direction.
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Returns which color the robot's color sensor detects.
	 *
	 * @return The ColorSensor enum the robot has detected.
	 */
	public ColorSensor.Color getColor() {
		ColorSensor.Mode COLOR;
		
		ColorSensor.Color detectedColor = myColorSensor.getColor();
		
		return detectedColor;
	}

	/**
	 * Returns whether the color the robot currently detects is a specific color or not.
	 *
	 * @param checkFor The ColorSensor enum to check for.
	 * @return Boolean value of if the detected color matches the chosen color.
	 */
	public Boolean isColor(ColorSensor.Color checkFor) {
		if (checkFor.equals(this.getColor())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Plays a celebratory song.
	 */
	public void celebrationSong() {
		final int TRIP_CROTCHET = 100;
		final int MINUM = 300;
		final int SEMI_BREVE = 600;

		// Super Mario Bros "Flagpole Fanfare"
		mySpeaker.playTone(196, TRIP_CROTCHET);
		mySpeaker.playTone(262, TRIP_CROTCHET);
		mySpeaker.playTone(330, TRIP_CROTCHET);
		mySpeaker.playTone(392, TRIP_CROTCHET);
		mySpeaker.playTone(524, TRIP_CROTCHET);
		mySpeaker.playTone(660, TRIP_CROTCHET);

		mySpeaker.playTone(784, MINUM);
		mySpeaker.playTone(660, MINUM);

		mySpeaker.playTone(208, TRIP_CROTCHET);
		mySpeaker.playTone(262, TRIP_CROTCHET);
		mySpeaker.playTone(311, TRIP_CROTCHET);
		mySpeaker.playTone(415, TRIP_CROTCHET);
		mySpeaker.playTone(524, TRIP_CROTCHET);
		mySpeaker.playTone(622, TRIP_CROTCHET);

		mySpeaker.playTone(831, MINUM);
		mySpeaker.playTone(622, MINUM);

		mySpeaker.playTone(233, TRIP_CROTCHET);
		mySpeaker.playTone(294, TRIP_CROTCHET);
		mySpeaker.playTone(349, TRIP_CROTCHET);
		mySpeaker.playTone(466, TRIP_CROTCHET);
		mySpeaker.playTone(587, TRIP_CROTCHET);
		mySpeaker.playTone(698, TRIP_CROTCHET);

		mySpeaker.playTone(932, MINUM);
		mySpeaker.playTone(932, TRIP_CROTCHET);
		mySpeaker.playTone(932, TRIP_CROTCHET);
		mySpeaker.playTone(932, TRIP_CROTCHET);

		mySpeaker.playTone(1047, SEMI_BREVE);
		myRobot.sleep(4500);

	}

	/**
	 * Performs a "dance" animation.
	 */
	public void celebrationDance() {
		leftMotor.setSpeed(200);
		rightMotor.setSpeed(100);
		leftMotor.forward();
		rightMotor.backward();
		myRobot.sleep(3522);
		myRobot.close();
	}

	/**
	 * Closes the robot when operations using it are completed.
	 */
	public void close() {
		myRobot.close();
	}

	/**
	 * Creates a new Robot.
	 */
	public ARobot() {
		// Initialising robot
		myRobot = new Robot();
		// Motion
		leftMotor = myRobot.getLargeMotor(Motor.Port.B);
		rightMotor = myRobot.getLargeMotor(Motor.Port.C);
		SPEED = 100;
		// Color detection
		myColorSensor = myRobot.getColorSensor(Sensor.Port.S2);
		// Sound
		mySpeaker = myRobot.getSpeaker();
	}

	public static void main(String[] args) {
		//tests some robot functions
		// Create a test robot
		ARobot testBot = new ARobot();
		testBot.moveForward(600);
		testBot.rotateClockwise(180);
		testBot.getColor();

		// List the possible ColorSensor enums
		for (ColorSensor.Color c : ColorSensor.Color.values()){
			System.out.println(c);
		}
	}
}