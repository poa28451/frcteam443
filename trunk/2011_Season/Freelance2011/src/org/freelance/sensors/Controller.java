/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.sensors;

import edu.wpi.first.wpilibj.Joystick;
import org.freelance.util.Constants;

/**
 * Controller.java
 * @version 1.0.0
 * @author Freelance Robotics (Hadyn Fitzgerald)
 */
public class Controller extends Joystick {

    /**
     * yAxis - Movement Forward(+), Backward(-)
     * xAxis - Rotation Left(+), Right(-)
     */
    protected double xAxis, yAxis;

    /**
     * Each button has a ID#(Constants.java)
     */
    protected boolean[] activeButtons;

    /**
     * Default joystick constructor
     * connects to default joystick port (Constants.java)
     */
    public Controller() {
        super(Constants.JOYSTICK_PORT);
        initController();
    }

    /**
     *
     * @param joystick_port the joystick port to connect to
     */
    public Controller(int joystick_port) {
        super(joystick_port);
        initController();
    }

    /**
     * Initializes the controller
     */
    protected void initController() {
        /**
         * 10 PS3 Buttons
         */
        activeButtons = new boolean[10];
    }

    /**
     * Called every controlled period
     * Sets all the buttons from the controller
     */
    public void getActiveButtons() {
        for(int a = 0; a < activeButtons.length; a++) {
            activeButtons[a] = this.getRawButton(a);
        }
    }

    /**
     * Called every controlled period
     * Sets the axis amt from the controller
     */
    public void getActiveAxis() {
        yAxis = this.getX();
        xAxis = this.getY();
        
        /*
         * Invert the Axis
         */
        xAxis *= -1;
        yAxis *= -1;
    }

    /**
     *
     * @return
     */
    public double getXAxis() {
        return xAxis;
    }

    /**
     *
     * @return
     */
    public double getYAxis() {
        return yAxis;
    }

    /**
     * Checks if the button in the param is active
     * @param button the button to test
     * @return if button is active
     */
    public boolean ButtonActive(int button) {
        return activeButtons[button];
    }

    /**
     * Reset the activeButtons array
     */
    public void resetButtons() {
        for(int a = 0; a < activeButtons.length; a++) {
            activeButtons[a] = false;
        }
    }

    /**
     * 
     */
    public void teleOpProcess() {
        resetButtons();
        getActiveButtons();
        getActiveAxis();
    }
}
