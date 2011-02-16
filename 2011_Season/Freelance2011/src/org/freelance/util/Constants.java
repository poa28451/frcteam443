/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.util;

/**
 *
 * @author mark
 */
public class Constants {

    public static int JOYSTICK_PORT = 1;

    /**
     * DIO Slot
     */
    public final static int DIO_SLOT = 4;

    /**
     * Analog Slot
     */
    public final static int ANLG_SLOT = 1;

    /**
     * Jaguar Channels
     */
    public final static int FL_CHNL = 2;
    public final static int FR_CHNL = 1;
    public final static int RL_CHNL = 3;
    public final static int RR_CHNL = 4;

    /**
     * Jaguar ID's
     */
    public final static int FL_ID = 25;
    public final static int FR_ID = 24;
    public final static int RL_ID = 26;
    public final static int RR_ID = 23;
    public final static int ARM_ID = 27;

    public final static int Extend_ID = 3;

    /**
     * Gyro Channel
     */
    public final static int GYRO_CHNL = 1;

    /**
     * Motor Inversion
     */
    public final static boolean FL_INVERT = false;
    public final static boolean FR_INVERT = false;
    public final static boolean RL_INVERT = true;
    public final static boolean RR_INVERT = true;

    /**
     * Mech Drive Constant List
     */
    public final static int DBMAX = 255;
    public final static int DBMAXBAND = 138;
    public final static int DBCENTER = 128;
    public final static int DBMINBAND = 118;
    public final static int DBMIN = 1;
    public final static double DIRECTION_BIAS = 5.0;

   /**
    * Linetracking Constant List
    */
    public final static int CENTER_SLOT = 12;
    public final static int LEFT_SLOT = 13;
    public final static int RIGHT_SLOT = 14;

     /**
     * PS3 Remote Buttons
     */
    public static final int BUTTON_X = 2;
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 3;
    public static final int BUTTON_Y = 4;

    /**
     * PS3 Bumper Buttons
     */
    public static final int LF_BUMPER = 5;
    public static final int RF_BUMPER = 6;
    public static final int LB_BUMPER = 7;
    public static final int RB_BUMPER = 8;

    /**
     * Misc Buttons
     */
    public static final int BACK_BUTTON = 9;
    public static final int START_BUTTON = 10;

}