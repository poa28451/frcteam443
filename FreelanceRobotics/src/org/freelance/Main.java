/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.freelance;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.freelance.devices.Arm;
import org.freelance.devices.Claw;
import org.freelance.devices.MecanumDrive;
import org.freelance.util.GameTimer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends IterativeRobot {

    /**
     * Devices
     */
    protected Claw claw;
    protected Arm arm;
    protected MecanumDrive mechanumDrive;

    /**
     * Operations
     */
    private AutoOperations autoOp;
    private TeleOperations teleOp;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        /**
         * Construct The Devices
         */
        claw = new Claw();
        arm = new Arm();
        mechanumDrive = new MecanumDrive();
        
        /**
         * Construct The Operations
         */
        autoOp = new AutoOperations();
        teleOp = new TeleOperations();

        /**
         * Initialize The Operations
         */
        autoOp.init();
        teleOp.init();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        GameTimer.startTimer();
        autoOp.process(this);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        teleOp.process(this);
    }   
}
