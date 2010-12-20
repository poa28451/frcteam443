//----------------------------------------------------------------------------
// Copyright (c) FIRST 2008. All Rights Reserved.
// Open Source Software - may be modified and shared by FRC teams. The code
// must be accompanied by the FIRST BSD license file in the root directory of
// the project.
//
// File: LogitechDualActionController.java
//
// Description: This file builds on the wpilibj joystick class for the Logitech
//              dual action controller.
//
// Lead: TBD
// ----------------------------------------------------------------------------
package freelancelibj;

import edu.wpi.first.wpilibj.Joystick;
import com.sun.squawk.util.MathUtils;

public class LogitechDualActionController {

    private Joystick input;
    private double X_Val_Slow_Gain = -0.5;
    private double Y_Val_Slow_Gain = -0.6;
    private double X_Val_Fast_Gain = -1;
    private double Y_Val_Fast_Gain = -0.7;
//    double Y_Axis_Ramp_Val = 0;
//    double interval = .01;
//    double SpeedError;

    public LogitechDualActionController(int slot) {
        input = new Joystick(slot);
    }

    public boolean getButton1() {
        return this.input.getRawButton(1);
    }

    public boolean getButton2() {
        return this.input.getRawButton(2);
    }

    public boolean getButton3() {
        return this.input.getRawButton(3);
    }

    public boolean getButton4() {
        return this.input.getRawButton(4);
    }

    public boolean getL1() {
        return this.input.getRawButton(7);
    }

    public boolean getL2() {
        return this.input.getRawButton(5);
    }

    public boolean getR1() {
        return this.input.getRawButton(8);
    }

    public boolean getR2() {
        return this.input.getRawButton(6);
    }

    public boolean getButton10() {
        return this.input.getRawButton(10);
    }

    public boolean getButton9() {
        return this.input.getRawButton(9);
    }

    public double getLeftStickY() {
        return this.input.getY();
    }

    public double getLeftStickX() {
        return this.input.getX();
    }

    public double getRightStickY() {
        return this.input.getTwist();
    }

    public double getRightStickX() {
        return this.input.getThrottle();
    }

    public double getAxisY() {
        return this.input.getRawAxis(1);
    }

    public double getAxisX() {
        return this.input.getRawAxis(2);
    }

    public double getMagnitude() {
        return this.input.getMagnitude();
    }

    public double getDirection() {
        return this.input.getDirectionDegrees();
    }

    public double getFL_Magnitude() {

        double Magnitude;
        double Logitech_Joystick_Cutoff_Value = 0.05;
        double X_Axis_Val = X_Val_Fast_Gain * this.getAxisX();
        double Y_Axis_Val = Y_Val_Fast_Gain * this.getAxisY();
        //double X_Axis_Val = X_Val_Slow_Gain * this.getAxisX();
        //double Y_Axis_Val = Y_Val_Slow_Gain * this.getAxisY();

        // Logitech potentiometer calibration logic: This logic is designed
        // to stop the wheels from moving when the user takes their hand off
        // the joystick. Sometimes the joystick sticks and leaves a small bias
        // applied to the motors. If the absolute value of the X and Y axis
        // values are less than the parameterized Logitech Joystick Cutoff
        // Value then set the X and Y axis values to 0. Otherwise use the
        // value read from the joystick.
        if (Math.abs(X_Axis_Val) < Logitech_Joystick_Cutoff_Value) {
            X_Axis_Val = 0;
        }

        if (Math.abs(Y_Axis_Val) < Logitech_Joystick_Cutoff_Value) {
            Y_Axis_Val = 0;
        }

        // Y Axis Value Ramping Code
        //SpeedError = (Y_Axis_Val - Y_Axis_Ramp_Val);

//        // If the absolute value of the speed error is greater than 0
//        if (Math.abs(SpeedError) > 0.0001) {
//
//            // If the absolute of the speed error is greater than the predefined
//            // interval
//            if (Math.abs(SpeedError) > interval) {
//
//                if (SpeedError > 0) {
//                    Y_Axis_Ramp_Val = Y_Axis_Ramp_Val + interval;
//                } else {
//                    Y_Axis_Ramp_Val = Y_Axis_Ramp_Val - interval;
//                }
//            } else {
//                Y_Axis_Ramp_Val = Y_Axis_Val;
//            }
//
//            if (Y_Axis_Ramp_Val >= 0.9){
//                Y_Axis_Ramp_Val = 1;
//            }
//
//            if (Y_Axis_Ramp_Val <= -0.9){
//                Y_Axis_Ramp_Val = -1;
//            }
//
//        } // End Ramp code

        // Calculate the magnitude of the joystick from the driver.
        //Magnitude = Math.sqrt(MathUtils.pow(X_Axis_Val, 2) + MathUtils.pow(Y_Axis_Ramp_Val, 2));
        Magnitude = Math.sqrt(MathUtils.pow(X_Axis_Val, 2) + MathUtils.pow(Y_Axis_Val, 2));

        return Magnitude;
    }

    public double getFL_Direction() {

        double X_Axis_Val = this.getAxisX();
        double Y_Axis_Val = -1 * this.getAxisY();
        double DirectionDegrees = Math.toDegrees(MathUtils.atan2(Y_Axis_Val, X_Axis_Val));
        return DirectionDegrees;
    }
}

