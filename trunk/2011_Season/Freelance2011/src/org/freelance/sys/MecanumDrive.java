/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.sys;

import edu.wpi.first.wpilibj.CANJaguar;
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
    private CANJaguar flMotor;
    private CANJaguar rlMotor;
    private CANJaguar frMotor;
    private CANJaguar rrMotor;

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
        try {
            flMotor = new CANJaguar(Constants.FL_ID);
        } catch(Exception e) {
            System.out.println(Constants.FL_ID+" "+e.getMessage());
        }
        
        try {
            rlMotor = new CANJaguar(Constants.RL_ID);
        } catch(Exception e) {
            System.out.println(Constants.RL_ID+" "+e.getMessage());
        }

        try {
            frMotor = new CANJaguar(Constants.FR_ID);
        } catch(Exception e) {
            System.out.println(Constants.FR_ID+" "+e.getMessage());
        }

        try {
            rrMotor = new CANJaguar(Constants.RR_ID);
        } catch(Exception e) {
            System.out.println(Constants.RR_ID+" "+e.getMessage());
        }

        /**
         * Set DeadbandElimination
         * -Always True
         */
        //flMotor.enableDeadbandElimination(true);
        //frMotor.enableDeadbandElimination(true);
        //rlMotor.enableDeadbandElimination(true);
        //rrMotor.enableDeadbandElimination(true);
        
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

    public void autonomousDrive(double x, double y, double twist) {
        robotDrive.mecanumDrive_Cartesian(x , y, twist * -0.7, 0);
    }
}
