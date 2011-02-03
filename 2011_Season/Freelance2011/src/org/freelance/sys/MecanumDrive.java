/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.sys;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import org.freelance.util.Constants;

/**
 *
 * @author Freelance Robotics
 */
public class MecanumDrive {

    /**
     * RobotDrive instance variable
     */
    private RobotDrive robotDrive;

    /**
     * Jaguar Motors
     * -Front Left
     * -Rear Left
     * -Front Right
     * -Rear Right
     */
    private Jaguar flMotor;
    private Jaguar rlMotor;
    private Jaguar frMotor;
    private Jaguar rrMotor;

    /**
     * Default Constructor
     */
    public MecanumDrive() {        
        initMechanumDrive();
    }

    /**
     * Initialize MechanumDrive
     */
    private void initMechanumDrive() {
        /**
         * Construct Motors
         */
        flMotor = new Jaguar(Constants.DIO_SLOT, Constants.FL_CHNL);
        rlMotor = new Jaguar(Constants.DIO_SLOT, Constants.FR_CHNL);
        frMotor = new Jaguar(Constants.DIO_SLOT, Constants.RL_CHNL);
        rrMotor = new Jaguar(Constants.DIO_SLOT, Constants.RR_CHNL);

        /**
         * Set DeadbandElimination
         * -Always True
         */
        flMotor.enableDeadbandElimination(true);
        frMotor.enableDeadbandElimination(true);
        rlMotor.enableDeadbandElimination(true);
        rrMotor.enableDeadbandElimination(true);
        
        /**
         * Initialize RobotDrive
         */
        robotDrive = new RobotDrive(flMotor, rlMotor, frMotor, rrMotor);

        /**
         * Invert The Motors
         */
        robotDrive.setInvertedMotor(MotorType.kFrontRight, Constants.FR_INVERT);
        robotDrive.setInvertedMotor(MotorType.kRearRight, Constants.RR_INVERT);
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, Constants.FL_INVERT);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, Constants.RL_INVERT);
    }

    public void controllerDrive(double x, double y, double twist) {
        System.out.println(twist);
        robotDrive.mecanumDrive_Cartesian(x , y, twist * -0.7, 0);
    }

    public void autonomousDrive(double magnitude, double direction) {
        robotDrive.mecanumDrive_Polar(magnitude, direction, 0);
    }
}
