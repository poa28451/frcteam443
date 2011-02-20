package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.templates.Freelance2011;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * TeleopMode.java
 * @version 1.0.0
 * @author Freelance Robotics
 */
public class TeleopMode {

    /**
     * Process for the teleop mode
     * @param robot
     */
    public void TeleopProcess(Freelance2011 robot) {
        /**
         * Controller Process
         */
        robot.driveControl.teleOpProcess();
        robot.armControl.teleOpProcess();

        /**
         * MechDrive Process
         */
        robot.mecanumDrive.controllerDrive(robot.driveControl.getXAxis(),
                                           robot.driveControl.getYAxis(),
                                           robot.driveControl.getTwist());
        /**
         * Arm Process
         */
        try {
            robot.arm.process(robot.driveControl);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }

        try {
            robot.claw.process(robot.driveControl);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
