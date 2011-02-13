/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.logic;

import org.freelance.sensors.Photoswitch;
import org.freelance.sys.MecanumDrive;
import org.freelance.util.Constants;

/**
 *
 * @author Freelance Robotics (Hadyn Fitzgerald)
 */
public class Linetracking {

    private Photoswitch leftSwitch;
    private Photoswitch centerSwitch;
    private Photoswitch rightSwitch;

    private final byte NONE_OPCODE = 0, LEFT_OPCODE = 1, CENTER_OPCODE = 2,
                       RIGHT_OPCODE = 4, ALL_OPCODE = 7;

    private double FORWARD = -.5, BACKWARD = .5, LEFT = - .425, RIGHT = .425;

    private int stage = 0;

    public Linetracking() {
        initLineTracking();
    }

    private void initLineTracking() {
        /**
         * Construct Photoswitches
         */
        leftSwitch = new Photoswitch(Constants.LEFT_SLOT);
        centerSwitch = new Photoswitch(Constants.CENTER_SLOT);
        rightSwitch = new Photoswitch(Constants.RIGHT_SLOT);
    }

    public byte getType() {
        byte type = 0;

        if(leftSwitch.get())
            type |= 0x1;

        if(centerSwitch.get())
            type |= 0x2;

        if(rightSwitch.get())
            type |= 0x4;

        return type;
    }

    public void process(MecanumDrive drive) {
        byte type = getType();
        switch(type) {

            case NONE_OPCODE:
                if(stage == 0) {
                    //for(int a = 0; a < 30; a++) {
                    //    drive.controllerDrive(0, 0, -.5);
                    //    Timer.delay(.25);
                    //}
                    while(getType() != CENTER_OPCODE) {
                        //RIGHT GOES RIGHT
                        //LEFT GOES LEFT
                        drive.controllerDrive(0, LEFT, -.3);
                        //drive.controllerDrive(0, 0, .3);
                    }
                    stage++;
                } else if(stage == 1) {
                    while(getType() != ALL_OPCODE) {
                        drive.controllerDrive(0, 0, .3);
                    }
                    stage++;
                } else {
                    drive.autonomousDrive(0, 0, 0);
                }
                break;

            case LEFT_OPCODE:
                while(getType() != CENTER_OPCODE) {
                    if(getType() == NONE_OPCODE)
                        break;
                    drive.controllerDrive(0, LEFT, 0);
                }
                break;

            case CENTER_OPCODE:
                drive.controllerDrive(FORWARD, 0, 0);
                break;

            case RIGHT_OPCODE:
                while(getType() != CENTER_OPCODE) {
                    if(getType() == NONE_OPCODE)
                        break;
                    drive.controllerDrive(0, RIGHT, 0);
                }
                break;

            case ALL_OPCODE:
                if(stage == 3) {
                    for(int a = 0; a < 3; a++)
                        drive.controllerDrive(BACKWARD, RIGHT, 0);
                    stage++;
                } else {
                    drive.controllerDrive(0, 0, 0);
                }
                break;
        }
    }
}
