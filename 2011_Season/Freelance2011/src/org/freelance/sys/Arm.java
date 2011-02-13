/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.sys;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import org.freelance.sensors.LogtechRemote;
import org.freelance.util.Constants;

/**
 *
 * @author mark
 */
public class Arm {

    private double curX;
    private CANJaguar armRotate;

    public Arm() {
        initArm();
    }

    private void initArm() {
        try {
            armRotate = new CANJaguar(Constants.ARM_ID);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void process(LogtechRemote remote) throws CANTimeoutException {
        if(remote.AButtonActive())
            armRotate.setX(.5);
        else
            armRotate.setX(0);
    }
}
