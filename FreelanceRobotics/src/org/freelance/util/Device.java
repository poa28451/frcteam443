/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.util;

import org.freelance.control.Controller;

/**
 */
public abstract class Device {

    /**
     * Device
     * -
     */
    public Device() {
        init();
    }
    
    public abstract void init();

    public abstract void process(Controller controller);
}
