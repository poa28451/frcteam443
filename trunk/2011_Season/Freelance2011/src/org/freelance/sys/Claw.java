/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.freelance.sys;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import org.freelance.sensors.LogtechRemote;
import org.freelance.util.Constants;

/**
 *
 * @author mark
 */
public class Claw {

    private int[] virtualLimit = {
        0
    };
    private CANJaguar claw;

    public Claw() {
        initClaw();
    }

    private void initClaw() {
        try {
            claw = new CANJaguar(Constants.CLAW_ID);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void process(LogtechRemote remote) throws CANTimeoutException {
        /*if(remote.ButtonActive(5)) {
            autoOpen();
            return;
        }
        if(remote.ButtonActive(9)) {
            autoClose();
            return;
        }*/
        if (remote.ButtonActive(2)) { //CLOSE
            claw.setX(0.5);
            virtualLimit[0]--;
        } else if(remote.ButtonActive(4)) { //OPEN
            claw.setX(-0.5);
            virtualLimit[0]++;
        } else {
            claw.setX(0);
        }
        System.out.println(virtualLimit[0]);

    }

    public void close(int amt) {
        for(int a = 0; a < amt; a++) {
            try {
                Timer.delay(.2);
                claw.setX(.5);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        }
        try {
            claw.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void autoOpen() {
        int temp = virtualLimit[0];
        for(int a = temp; a < 38; a++)
            try {
                Timer.delay(.1);
                claw.setX(-0.5);               
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        try {
            claw.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        virtualLimit[0] = 30;
    }

    public void autoClose() {
        int temp = virtualLimit[0] - 5;
        for(int a = temp; a > 0; a--)
            try {
                Timer.delay(.2);
                claw.setX(.5);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        virtualLimit[0] = 0;
        try {
            claw.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
