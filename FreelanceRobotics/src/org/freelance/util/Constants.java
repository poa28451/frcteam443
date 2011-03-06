/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.util;

/**
 * @author Freelance Robotics (Hadyn Fitzgerald)
 */
public final class Constants {

    /**
     * USB ID's
     */
    public final static int DRIVER_REMOTE_USB = 1;
    public final static int ARM_REMOTE_USB = 2;

    /**
     * Jaguar ID's
     */
    public final static int CLAW_JAGUAR_ID = 4;
    public final static int ROTATION_JAGUAR_ID = 27;
    public final static int EXTEND_JAGUAR_ID = 3;
    public final static int DEPLOYMENT_JAGUAR_ID = 0;
    
    public final static int FL_ID = 25;
    public final static int FR_ID = 24;
    public final static int RL_ID = 26;
    public final static int RR_ID = 23;

    /**
     * Arm Control Buttons
     */
    public static final int OPEN_BUTTON = 6, CLOSE_BUTTON = 8;

    /**
     * Motor Inversion
     */
    public final static boolean FL_INVERT = false;
    public final static boolean FR_INVERT = true;
    public final static boolean RL_INVERT = false;
    public final static boolean RR_INVERT = true;

    public final static int GYRO_CHANNEL = 1;
}
