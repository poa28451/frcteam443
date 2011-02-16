/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.sys;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import org.freelance.sensors.Controller;
import org.freelance.sensors.LogtechRemote;
import org.freelance.util.Constants;

/**
 *
 * @author mark
 */
public class Arm {

    private double curX;
    private CANJaguar armRotate;
    private CANJaguar armExtend;

    public Arm() {
        initArm();
    }

    private void initArm() {
        try {
            armRotate = new CANJaguar(Constants.ARM_ID);
        } catch (CANTimeoutException ex) {
            System.out.println("ARM INIT PROCESS FAILED!!");
            ex.printStackTrace();
        }

        try {
            armExtend = new CANJaguar(Constants.Extend_ID);
        } catch (CANTimeoutException ex)  {
            System.out.println("ARM EXTEND INIT PROCESS FAILED!!");
            ex.printStackTrace();
        }
    }

    public void process(LogtechRemote remote) throws CANTimeoutException {
        if(remote.AButtonActive())
            armRotate.setX(1);
        else if(remote.BButtonActive()){
            armRotate.setX(-1);
        }else{
            armRotate.setX(0);
        }

        if(remote.RightBottomTrigger())
            armExtend.setX(1);
        else if(remote.RightTopBumper()){
            armExtend.setX(-1);
        }else{
            armExtend.setX(0);
        }
    }
}
