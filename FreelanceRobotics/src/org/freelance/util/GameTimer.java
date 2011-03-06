/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.util;

import edu.wpi.first.wpilibj.Timer;

/**
 */
public class GameTimer {

    public static boolean started = false;
    public static boolean autonomous = false;
    public static boolean teleoperation = false;
    public static boolean endgame = false;

    public static void startTimer() {
        if(started)
            return;
        new Thread(new Runnable() {
            public void run() {
                init();
            }
        }).start();
        started = true;
    }

    public static void init() {
        autonomous = true;
        Timer.delay(15);
        autonomous = false;
        teleoperation = true;
        Timer.delay(110);
        endgame = true;
        Timer.delay(10);
        teleoperation = false;
        endgame = false;
    }
}
