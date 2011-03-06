/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.control;

import edu.wpi.first.wpilibj.Joystick;
import org.freelance.util.Constants;

/**
 * @author Freelance Robotics (Hadyn Fitzgerald)
 */
public class Controller extends Joystick {

    /**
     * AxisMod
     *  -Axis Settings
     *
     * AxisX/AxisY *= AxisMod
     * -AxisMod > 1 = More Sensitive
     * -AxisMod < 1 > 0 = Less Sensitive
     * -AxisMod == Negitive (Axis Invert)
     */
    private double xAxismod = 1, yAxismod = 1;
    private double xAxis, yAxis;

    /**
     * Currently Active Buttons
     */
    private boolean[] activeButtons;

    /**
     * @param port The USB Port To Connect To
     */
    public Controller(int port) {
        super(port);
        initController();
    }

    /**
     * Initializes The Un-Constructed Controller Objects
     */
    private void initController() {
        activeButtons = new boolean[13];
    }

    public void setXAxisMod(double axismod) {
        this.xAxismod = axismod;
    }

    public double getXAxis() {
        return xAxis;
    }

    public double getYAxis() {
        return yAxis;
    }
    
    public void setYAxisMod(double axismod) {
        this.yAxismod = axismod;
    }

    public void invertXAxis() {
        xAxismod *= -1;
    }

    public void invertYAxis() {
        yAxismod *= -1;
    }

    public boolean buttonActive(int button) {
        return activeButtons[button];
    }

    public void getActiveButtons() {
        for(int a = 0; a < activeButtons.length; a++)
            activeButtons[a] = this.getRawButton(a);
    }

    public void getActiveAxis() {
        xAxis = (xAxismod * this.getX());
        yAxis = (yAxismod * this.getY());
    }

    public void resetActiveButtons() {
        for(int a = 0; a < activeButtons.length; a++)
            activeButtons[a] = false;
    }
}
