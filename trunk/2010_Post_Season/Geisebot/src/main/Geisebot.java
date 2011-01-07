//----------------------------------------------------------------------------
//
// Freelance Robotics, Team 443
//
// File: Geisebot.java
//
// Description: This is the primary driver for the Team 443 robot. This is a
// port from Labview to Java.
//
// Change Log:
// XX/XX - 
// ----------------------------------------------------------------------------

package main;
import  edu.wpi.first.wpilibj.IterativeRobot;

// Import all tasks in the task package
import tasks.*;

public class Geisebot extends IterativeRobot {

    private MecanumDrive                mecanumDriveTask;
    private Kicker                       kickerTask;
    private Autonomous                   autonomousTask;

    /**
     *
     */
    public void robotInit() {

        // Activate the Logitech_Dual_Action controller as the FL_Controller
        Controller.initController();

        // Initialize mecanum drive hardware
        MecanumDrive.initMecanumDrive();

        // Initialize kicker hardware
        Kicker.initKicker();
    }

    /**
     *
     */
    public void autonomousInit(){
       autonomousTask = new Autonomous();
    }

    /**
     *
     */
    public void autonomousPeriodic() {

        autonomousTask.performAutonomous();
    }

    /**
     * 
     */
    public void teleopInit(){
       
        // Mecanum drive constructor and initialization routine.
        mecanumDriveTask = new MecanumDrive(true);

        // Kicker initialization
        kickerTask = new Kicker(true);
    }

    /**
     *
     */
    public void teleopPeriodic() {

       kickerTask.Perform_Teleop();

       mecanumDriveTask.performTeleop();
    }
}
