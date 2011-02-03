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
     * Gyro Channel
     */
    public final static int GYRO_CHNL = 1;

    /**
     * Motor Inversion
     */
    public final static boolean FL_INVERT = false;
    public final static boolean FR_INVERT = false;
    public final static boolean RL_INVERT = true;
    public final static boolean RR_INVERT = false;

    /**
     * Constant Lists
     */
    public final static int DBMAX = 255;
    public final static int DBMAXBAND = 138;
    public final static int DBCENTER = 128;
    public final static int DBMINBAND = 118;
    public final static int DBMIN = 1;
    public final static double DIRECTION_BIAS = 5.0;

}