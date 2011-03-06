/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.devices;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import org.freelance.control.Controller;
import org.freelance.util.Constants;
import org.freelance.util.Device;

/**
 */
public class Arm extends Device {

    private CANJaguar armRotation;
    private CANJaguar armExtend;

    public void init() {
        try {
            armRotation = new CANJaguar(Constants.ROTATION_JAGUAR_ID);
            armExtend = new CANJaguar(Constants.EXTEND_JAGUAR_ID);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void process(Controller controller) {
        double extendAmt = controller.getYAxis();
        System.out.println("Extend Amt: "+extendAmt);
        extend(extendAmt);
        reset();
    }

    public void extend(double amt) {
        try {
            armExtend.setX(amt);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void reset() {
        try {
            armRotation.setX(0.0);
            //armExtend.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
