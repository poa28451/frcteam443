/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance;

import org.freelance.control.Controller;
import org.freelance.util.Constants;
import org.freelance.util.Operation;

/**
 * @author Freelance Robotics (Hadyn Fitzgerald)
 */
public class TeleOperations implements Operation {

    private Controller driveControl, armControl;

    /**
     * TeleOperations Process
     * @param main
     */
    public void process(final Main main) {
        driveControl.getActiveAxis();
        driveControl.getActiveButtons();

        armControl.getActiveAxis();
        armControl.getActiveButtons();

        main.claw.process(armControl);
        main.arm.process(armControl);
        main.mechanumDrive.process(driveControl);
    }

    public void init() {
        driveControl = new Controller(Constants.DRIVER_REMOTE_USB);
        armControl = new Controller(Constants.ARM_REMOTE_USB);

        /**
         * Set Axis Sensitivity
         */
        driveControl.setXAxisMod(.85);
        driveControl.setYAxisMod(.85);
    }

    public void reset() {
        driveControl.resetActiveButtons();
        armControl.resetActiveButtons();
    }
}
