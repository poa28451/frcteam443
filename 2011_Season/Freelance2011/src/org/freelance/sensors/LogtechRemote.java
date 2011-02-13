/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.sensors;

import org.freelance.util.Constants;

/**
 *
 * @author Dave
 */
public class LogtechRemote extends Controller {

    /**
     * Default joystick constructor
     * connects to default joystick port (Constants.java)
     */
    public LogtechRemote() {
        super(Constants.JOYSTICK_PORT);
        initController();
    }

    /**
     *
     * @param joystick_port the joystick port to connect to
     */
    public LogtechRemote(int joystick_port) {
        super(joystick_port);
        initController();
    }

       /**
     * Checks if the A Button is active (PS3)
     * @return
     */
    public boolean AButtonActive() {
        return activeButtons[Constants.BUTTON_A];
    }

    /**
     * Checks if the B Button is active (PS3)
     * @return
     */
    public boolean BButtonActive() {
        return activeButtons[Constants.BUTTON_B];
    }

    /**
     * Checks if the X Button is active (PS3)
     * @return
     */
    public boolean XButtonActive() {
        return activeButtons[Constants.BUTTON_X];
    }

    /**
     * Checks if the Y Button is active (PS3)
     * @return
     */
    public boolean YButtonActive() {
        return activeButtons[Constants.BUTTON_Y];
    }

}
