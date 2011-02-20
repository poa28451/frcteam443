/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.logic;

import edu.wpi.first.wpilibj.Timer;
import org.freelance.sensors.Photoswitch;
import org.freelance.sys.MecanumDrive;
import org.freelance.util.Constants;

/**
 *
 * @author Freelance Robotics (Hadyn Fitzgerald)
 */
public class Linetracking {

    private boolean done = false;
    private boolean left = false;
    private int profile = 1; //1 == Y, 0 == Straight

    private Photoswitch leftSwitch;
    private Photoswitch centerSwitch;
    private Photoswitch rightSwitch;

    private final byte NONE_OPCODE = 0, LEFT_OPCODE = 1, CENTER_OPCODE = 2,
                       RIGHT_OPCODE = 4, ALL_OPCODE = 7;

    private double FORWARD = -.5, BACKWARD = .5, LEFT =  -.425, RIGHT = .425;

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
            type += 1;

        if(centerSwitch.get())
            type += 2;

        if(rightSwitch.get())
            type += 4;

        System.out.println(type);

        return type;
    }

    public boolean process(MecanumDrive drive) {
        byte type = getType();
        switch(type) {

            case NONE_OPCODE:
                if(profile == 0) {
                    drive.controllerDrive(0, 0, 0);
                } else
                    if(stage == 0) {
                        for(int a = 0; a < 50; a++) {
                            if(left)
                                drive.controllerDrive(0, 0, -.3);
                            else
                                drive.controllerDrive(0, 0, .3);
                        }
                        while(getType() != CENTER_OPCODE) {
                            if(getType() == ALL_OPCODE)
                                break;
                            if(left)
                                drive.controllerDrive(0, LEFT, 0);
                            else
                                drive.controllerDrive(0, LEFT, 0);
                        }
                        stage++;
                    } else if(stage == 1) {
                        for(int a = 0; a < 35; a++) {
                            if(left)
                                drive.controllerDrive(0, 0, .3);
                            else
                                drive.controllerDrive(0, 0, -.3);
                        }
                        done = true;
                    } else {
                        drive.controllerDrive(0, 0, 0);
                    }
                break;

            case LEFT_OPCODE:
                while(getType() != CENTER_OPCODE) {
                    if(getType() == NONE_OPCODE || getType() == ALL_OPCODE)
                        break;
                    drive.controllerDrive(0, RIGHT, 0);
                }
                break;

            case CENTER_OPCODE:
                drive.controllerDrive(FORWARD, 0, 0);
                break;

            case RIGHT_OPCODE:
                while(getType() != CENTER_OPCODE) {
                    if(getType() == NONE_OPCODE || getType() == ALL_OPCODE)
                        break;
                    drive.controllerDrive(0, LEFT, 0);
                }
                break;

            case ALL_OPCODE:
                drive.controllerDrive(0, 0, 0);
                if(profile == 0) {
                    done = true;
                }
                break;
        }
        return done;
    }

    public void reset() {
        stage = 0;
    }
}
