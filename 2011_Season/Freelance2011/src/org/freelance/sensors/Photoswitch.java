/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Freelance Robotics
 */
public class Photoswitch extends DigitalInput {

    public Photoswitch(int channel) {
        super(channel);
    }

    public Photoswitch(int slot, int channel) {
        super(slot, channel);
    }
}
