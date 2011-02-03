//-----------------------------------------------------------------------------
//
// Freelance Robotics, FIRST Team 443
//
// File:        Teleop.java
//
// Description: WRITE DESCRIPTION!
//
// Change Log:  Mark      01/29      Project created
//
// ----------------------------------------------------------------------------
package org.freelance.main;

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
    }
}
