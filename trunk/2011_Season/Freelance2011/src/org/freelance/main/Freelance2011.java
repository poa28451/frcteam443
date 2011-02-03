//-----------------------------------------------------------------------------
//
// Freelance Robotics, FIRST Team 443
//
// File:        Freelance2011.java
//
// Description: This class is the entry into the Freelance Robotics 2011
//              Logomotion game robot code.
//
// Change Log:  Mark      01/29      Project created
//              
// ----------------------------------------------------------------------------
package org.freelance.main;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.freelance.sensors.Controller;
import org.freelance.sys.Arm;
import org.freelance.sys.Claw;
import org.freelance.sys.MecanumDrive;

/**
 * Freelance2011.java
 * @version 1.0.0
 * @author Freelance Robotics
 */
public class Freelance2011 extends IterativeRobot {

    /**
     * Operation Objects
     */
    private AutonomousMode autonomous;
    private TeleopMode teleop;
    
    /**
     * System Objects
     * -Protected so that the operation(tele/auto) objects may access them.
     */
    protected Controller driveControl;
    protected Controller armControl;
    protected Claw claw;
    protected Arm arm;
    protected MecanumDrive mecanumDrive;

    /**
     * Robot startup initialization
     */
    public void robotInit() {

        /**
         * Initiate System Objects
         */
        driveControl = new Controller();
        armControl = new Controller();
        claw = new Claw();
        arm = new Arm();
        mecanumDrive = new MecanumDrive();
        
        /**
         * Initiate Auto/Tele Operations
         */
        AutonomousInit();
        TeleopInit();
    }

    /**
     * Initialization routine for the Autonomous Mode
     */
    public void AutonomousInit() {
        if (autonomous == null) {
            autonomous = new AutonomousMode();
        }
    }

    /**
     * Initialization routine for the Teleop Mode
     */
    public void TeleopInit() {
        if (teleop == null) {
            teleop = new TeleopMode();
        }
    }

    /**
     * Autonomous Process
     */
    public void autonomousPeriodic() {
        autonomous.AutonomousProcess(this);
    }

    /**
     * Teleop Process
     */
    public void teleopPeriodic() {
        teleop.TeleopProcess(this);
    }
}
