/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.devices;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import org.freelance.control.Controller;
import org.freelance.util.Constants;
import org.freelance.util.Device;

/**
 */
public class Claw extends Device {

    /**
     *
     */
    private CANJaguar motor;

    public Claw() {
        super();
    }

    public void init() {
        try {
            motor = new CANJaguar(Constants.CLAW_JAGUAR_ID);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void process(Controller controller) {
        if(controller.buttonActive(Constants.OPEN_BUTTON))
            open(.85);
        else if(controller.buttonActive(Constants.CLOSE_BUTTON))
            close(.85);
        reset();
    }

    public void open(double amt) {
        try {
            motor.setX(amt * 1);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void close(double amt) {
        try {
            motor.setX(amt * -1);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void reset() {
        try {
            motor.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
