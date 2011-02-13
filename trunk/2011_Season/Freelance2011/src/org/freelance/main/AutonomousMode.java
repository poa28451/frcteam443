package org.freelance.main;

/**
 * Autonomous.java
 * @version 1.0.0
 * @author Freelance Robotics
 */
public class AutonomousMode {

    /**
     * Process for the autonomous mode.
     * @param robot
     */
    public void AutonomousProcess(Freelance2011 robot) {
        //Positive(X) = Backward, Positive(Y) = Right
        //Negitive(X) = Forward, Negitive(Y) = Left
        //Positive Twist = ClockWise, Negitive = Counter ClockWise
        //robot.mecanumDrive.autonomousDrive(0, 0, .5);
        robot.linetracking.process(robot.mecanumDrive);
    }
}
