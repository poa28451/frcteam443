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
public class Arm {

    private boolean done = false;
    private int[] virtualLimit = {
        0, 0
    };

    private CANJaguar armRotate;
    private CANJaguar armExtend;

    public Arm() {
        initArm();
    }

    private void initArm() {
        try {
            armRotate = new CANJaguar(Constants.ARM_ID);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }

        try {
            armExtend = new CANJaguar(Constants.Extend_ID);
        } catch (CANTimeoutException ex)  {
            ex.printStackTrace();
        }
    }

    public void setAtLevel(int level) {
        switch(level) {
            
            case 0:

                break;

            case 1:

                break;

            case 2:

                break;
        }
    }

    public boolean isSafe(boolean dir) { //160 = yellow, 175 = red
        if(dir == true) { //POSITIVE
            if(virtualLimit[1] + 1 > 105)
                return false;
            return true;
        }
        if(virtualLimit[1] - 1 < 0)
            return false;
        return true;
    }

    public void process(LogtechRemote remote) throws CANTimeoutException {
        /*if(remote.ButtonActive(10)) {
            auto();
            return;
        }
        if(remote.ButtonActive(10)) {
            resetExtension();
            return;
        }*/
        if(remote.ButtonActive(7)) { //R
            armExtend.setX(1.0);
            return;
        }
        if(remote.ButtonActive(3)) { //UP
            armRotate.setX(0.5);
            virtualLimit[0]++;
        }  else if(remote.ButtonActive(1)) { //DOWN
            armRotate.setX(-.5);
            virtualLimit[0]--;
        } else {
            armRotate.setX(0);
        }

        
        if(remote.RightBottomTrigger()  && isSafe(false)) { //R
            armExtend.setX(1.0);
            virtualLimit[1]--;
        } else if (remote.RightTopBumper() && isSafe(true)) { //E
            armExtend.setX(-1.0);
            virtualLimit[1]++;
        } else {
            armExtend.setX(0);
        }

        System.out.println(armRotate.getOutputCurrent());
        System.out.println(virtualLimit[0]);
        System.out.println(virtualLimit[1]);
    }

    public void auto() {
        if(done)
            return;
        for(int a = 0; a < 7; a++) {
            try {
                armRotate.setX(0.5);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
            virtualLimit[0]++;
            Timer.delay(.2);
        }
        try {
            armRotate.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void resetExtension() throws CANTimeoutException {
        int amt = virtualLimit[1]  - 10;
        for(int a = amt; a > 0; a--) {
            Timer.delay(.1);
            armExtend.setX(1.0);
        }
        armExtend.setX(0); //FIIIIIIIRRREEEE!!!!
        virtualLimit[1] = 0;
    }
}
