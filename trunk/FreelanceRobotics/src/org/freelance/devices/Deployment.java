/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.devices;

import edu.wpi.first.wpilibj.Victor;

import org.freelance.control.Controller;
import org.freelance.util.Constants;
import org.freelance.util.Device;

/**
 *
 * @author Dave
 */
public class Deployment extends Device {
    
    private Victor motor;

    public void init() {
        motor = new Victor(Constants.DEPLOYMENT_JAGUAR_ID);
    }

    public void process(Controller controller) {
        if(controller.buttonActive(10))
            motor.set(.5);
        else
            motor.set(0.0);
    }
}
