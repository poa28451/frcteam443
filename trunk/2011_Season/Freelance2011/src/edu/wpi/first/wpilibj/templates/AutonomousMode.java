package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.Freelance2011;

/**
 * Autonomous.java
 * @version 1.0.0
 * @author Freelance Robotics
 */
public class AutonomousMode {

    boolean done = false;
    boolean done0 = false;
    /**
     * Process for the autonomous mode.
     * @param robot
     */
    public void AutonomousProcess(Freelance2011 robot) {
        //Positive(X) = Backward, Positive(Y) = Right
        //Negitive(X) = Forward, Negitive(Y) = Left
        //Positive Twist = ClockWise, Negitive = Counter ClockWise
        //robot.mecanumDrive.autonomousDrive(0, 0, .5);
        if(done)
            return;
        if(!done0) {
            robot.claw.close(9);
            done0 = true;
        }
        if(robot.linetracking.process(robot.mecanumDrive)) {
            robot.arm.auto();
            robot.claw.autoOpen();
            for(int a = 0; a < 10; a++) {
                robot.mecanumDrive.controllerDrive(.5, 0, 0);
                Timer.delay(.15);
            }
            robot.mecanumDrive.controllerDrive(0, 0, 0);
            done = true;
        }
    }
}
