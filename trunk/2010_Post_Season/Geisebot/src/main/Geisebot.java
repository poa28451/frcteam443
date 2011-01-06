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

    //private Controller Controller;
    private MecanumDrive                Mecanum_Drive_Task;
    private Kicker                       Kicker_Task;
    //private Autonomous                   Autonomous_Task;

    /*
    =========================================================================
     * Robot Initialization
    =========================================================================
     */
    public void robotInit() {

        // Activate the Logitech_Dual_Action controller as the FL_Controller
        Controller.initController(Constants.JOYSTICK1_CHNL);

        // Mecanum drive constructor and initialization routine.
        Mecanum_Drive_Task = new MecanumDrive();

        // Kicker initialization
        Kicker_Task = new Kicker();

        //Autonomous_Task = new Autonomous();


    }

    /*
    =========================================================================
     * Autonomous
    =========================================================================
     */
    public void autonomousPeriodic() {

        //Autonomous_Task.performAutonomous();



    }

    /*
    =========================================================================
     * Driver - This method runs at 100 Hz (100 times a second)
    =========================================================================
     */
    public void teleopPeriodic() {

       Kicker_Task.Perform_Teleop();

       Mecanum_Drive_Task.Perform_Teleop();

    }
}
