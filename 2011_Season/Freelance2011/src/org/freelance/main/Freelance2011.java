package org.freelance.main;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.freelance.logic.Linetracking;
import org.freelance.sensors.LogtechRemote;
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
    protected LogtechRemote driveControl;
    protected LogtechRemote  armControl;
    protected Claw claw;
    protected Arm arm;
    protected MecanumDrive mecanumDrive;

    /**
     * Logic Objects
     * -Protected so that the operation(tele/auto) objects may access them.
     */
    protected Linetracking linetracking;

    /**
     * Robot startup initialization
     */
    public void robotInit() {

        /**
         * Initiate System Objects
         */
        driveControl = new LogtechRemote();
        armControl = new LogtechRemote();
        claw = new Claw();
        arm = new Arm();
        mecanumDrive = new MecanumDrive();

        /**
         * Initiate Logic Objects
         */
        linetracking = new Linetracking();
        
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
