//----------------------------------------------------------------------------
//
// Freelance Robotics, Team 443
//
// File: LogitechDualActionController.java
//
// Description: This file builds on the wpilibj joystick class for the Logitech
//              dual action controller.
//
// Change Log:
// XX/XX - 
// ----------------------------------------------------------------------------
package tasks;

import edu.wpi.first.wpilibj.Joystick;
import com.sun.squawk.util.MathUtils;

public class Controller {

    // Joystick object (Note that it is static to allow multiple controller
    // instantiations
    public static Joystick input;

    // Constants
    final static double X_AXIS_SLOW_GAIN = 0.5;
    final static double Y_AXIS_SLOW_GAIN = -0.6;
    final static double X_AXIS_FAST_GAIN = 1;
    final static double Y_AXIS_FAST_GAIN = -0.7;
    final static double JOYSTICK_DEADBAND = 0.05;

    // Variables for working with joystick readings
    private double leftStickMagnitude = 0.0;
    private double leftStickDirection = 0.0;
    private double leftStickXAxisVal = 0.0;
    private double leftStickYAxisVal = 0.0;

    // Joystick ramping values
    private double leftStickXAxisRampVal = 0.0;
    private double leftStickYAxisRampVal = 0.0;
    final static double RAMP_INTERVAL = 0.05;

    public Controller() {

    }

    public static void initController() {
        input = new Joystick(Constants.JOYSTICK1_CHNL);
    }

    public boolean getButton1() {
        return input.getRawButton(1);
    }

    public boolean getButton2() {
        return input.getRawButton(2);
    }

    public boolean getButton3() {
        return input.getRawButton(3);
    }

    public boolean getButton4() {
        return input.getRawButton(4);
    }

    public boolean getButton5() {
        return input.getRawButton(5);
    }

    public boolean getButton6() {
        return input.getRawButton(6);
    }

    public boolean getButton7() {
        return input.getRawButton(7);
    }

    public boolean getButton8() {
        return input.getRawButton(8);
    }

    public boolean getButton9() {
        return input.getRawButton(9);
    }

    public boolean getButton10() {
        return input.getRawButton(10);
    }

    public double getLeftStickY() {
        return input.getY();
    }

    public double getLeftStickX() {
        return input.getX();
    }

    public double getRightStickY() {
        return input.getTwist();
    }

    public double getRightStickX() {
        return input.getThrottle();
    }

    public double getLeftStickMagnitude() {

        // Apply speed multipliers, fix wheel orientation, and mecanum wheel
        // direction (Note: XAxis = getLeftStickY and YAxis = getLeftStickY is
        // done on purpose. This is because this robot has a rotated drivetrain)
        leftStickXAxisVal = X_AXIS_FAST_GAIN * this.getLeftStickY();
        leftStickYAxisVal = Y_AXIS_FAST_GAIN * this.getLeftStickX();

        performStickDeadbandCompensation();

        calculateLeftStickMagnitude();

        return leftStickMagnitude;
    }

    public double getLeftStickMagnitude(double xVal, double yVal) {

        // Apply speed multipliers, fix wheel orientation, and mecanum wheel
        // direction (Note: XAxis = getLeftStickY and YAxis = getLeftStickY is
        // done on purpose. This is because this robot has a rotated drivetrain)
        leftStickXAxisVal = X_AXIS_FAST_GAIN * yVal;
        leftStickYAxisVal = Y_AXIS_FAST_GAIN * xVal;

        performStickDeadbandCompensation();

        calculateLeftStickMagnitude();

        return leftStickMagnitude;
    }

    public double getLeftStickDirection() {

        // Apply speed multipliers, fix wheel orientation, and mecanum wheel
        // direction (Note: XAxis = getLeftStickY and YAxis = getLeftStickY is
        // done on purpose. This is because this robot has a rotated drivetrain)
        leftStickXAxisVal = X_AXIS_FAST_GAIN * this.getLeftStickY();
        leftStickYAxisVal = Y_AXIS_FAST_GAIN * this.getLeftStickX();

        performStickDeadbandCompensation();

        calculateLeftStickDirection();

        return leftStickDirection;
    }

    public double getLeftStickDirection(double xVal, double yVal) {

        // Apply speed multipliers, fix wheel orientation, and mecanum wheel
        // direction (Note: XAxis = getLeftStickY and YAxis = getLeftStickY is
        // done on purpose. This is because this robot has a rotated drivetrain)
        leftStickXAxisVal = X_AXIS_FAST_GAIN * yVal;
        leftStickYAxisVal = Y_AXIS_FAST_GAIN * xVal;

        performStickDeadbandCompensation();

        calculateLeftStickDirection();

        return leftStickDirection;


    }

    private void calculateLeftStickMagnitude() {

        // Calculate the magnitude of the left stick using sqrt(x^2+y^2)
        leftStickMagnitude = Math.sqrt(MathUtils.pow(leftStickXAxisVal, 2)
                + MathUtils.pow(leftStickYAxisVal, 2));
    }

    private void calculateLeftStickDirection() {

        // Calculate the direction of the left stick using arctan(y/x)
        leftStickDirection = Math.toDegrees(MathUtils.atan2(leftStickYAxisVal,
                leftStickXAxisVal));
    }

    /** Compensates for deadband in the joystick.
     *
     *  If the value of the stick for both axes is below the
     *  JOYSTICK_DEADBAND parameter then set the axis value to zero.
     */
    private void performStickDeadbandCompensation() {

        // Controller deadband compensation for the x axis
        if (Math.abs(leftStickXAxisVal) < JOYSTICK_DEADBAND) {
            leftStickXAxisVal = 0;
        }

        // Controller deadband compensation for the y axis
        if (Math.abs(leftStickYAxisVal) < JOYSTICK_DEADBAND) {
            leftStickYAxisVal = 0;
        }
    }

    private void joystickRamp() {

        double speedError;

        //Y Axis Value Ramping Code
        speedError = (leftStickYAxisVal - leftStickXAxisRampVal);

        // If the absolute value of the speed error is greater than 0
        if (Math.abs(speedError) > 0.0001) {

            // If the absolute of the speed error is greater than the predefined
            // interval
            if (Math.abs(speedError) > RAMP_INTERVAL) {

                if (speedError > 0) {
                    leftStickXAxisRampVal = leftStickXAxisRampVal + RAMP_INTERVAL;
                } else {
                    leftStickXAxisRampVal = leftStickXAxisRampVal - RAMP_INTERVAL;
                }
            } else {
                leftStickXAxisRampVal = leftStickYAxisVal;
            }

            if (leftStickXAxisRampVal >= 1.0) {
                leftStickXAxisRampVal = 1.0;
            }

            if (leftStickXAxisRampVal <= -1.0) {
                leftStickXAxisRampVal = -1.0;
            }

        }

    }
}
