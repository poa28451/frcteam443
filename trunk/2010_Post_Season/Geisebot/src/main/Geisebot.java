//----------------------------------------------------------------------------
// Copyright (c) FIRST 2008. All Rights Reserved.
// Open Source Software - may be modified and shared by FRC teams. The code
// must be accompanied by the FIRST BSD license file in the root directory of
// the project.
//
// File: freelance2010.java
//
// Description: This is the primary driver for the Team 443 robot. This is a
// port from Labview to Java.
//
// Lead: TBD
// ----------------------------------------------------------------------------

package main;
import  edu.wpi.first.wpilibj.IterativeRobot;

// Import all tasks in the task package
import tasks.*;

public class Geisebot extends IterativeRobot {

    private Controller Controller;
    private MecanumDrive                Mecanum_Drive_Task;
    private Kicker                       Kicker_Task;

    /*
    =========================================================================
     * Robot Initialization
    =========================================================================
     */
    public void robotInit() {

        // Activate the Logitech_Dual_Action controller as the FL_Controller
        Controller = new Controller(1);

        // Mecanum drive constructor and initialization routine.
        Mecanum_Drive_Task = new MecanumDrive();

        // Kicker initialization
        Kicker_Task = new Kicker();


    }

    /*
    =========================================================================
     * Autonomous
    =========================================================================
     */
    public void autonomousPeriodic() {



    }

    /*
    =========================================================================
     * Driver - This method runs at 100 Hz (100 times a second)
    =========================================================================
     */
    public void teleopPeriodic() {

        /* Execute the mecanum robot drive task. This tasks uses the joystick
         * magnitude and direction as arguments and controls all four mecanum
         * wheels on the robot.
         */
        Mecanum_Drive_Task.Perform_Teleop(Controller.getFL_Magnitude(),
                                          Controller.getFL_Direction(),
                                          Controller.getRightStickX(),
                                          Controller.getButton1(),
                                          Controller.getButton2(),
                                          Controller.getButton3(),
                                          Controller.getButton4());

        /* Execute the kicker task with controller inputs. TODO! Need to
         * confirm buttons!
         */
       Kicker_Task.Perform_Teleop(Controller.getButton6(),
                                   Controller.getButton8(),
                                   Controller.getButton10());

    }
}
