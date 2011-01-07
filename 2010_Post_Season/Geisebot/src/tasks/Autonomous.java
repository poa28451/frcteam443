//----------------------------------------------------------------------------
//
// Freelance Robotics, Team 443
//
// File: Autonomous.java
//
// Description: Primary logic for the autonomous portion of the competition
//
// Change Log:
// 12/31 - Created autonomous class
//
// ----------------------------------------------------------------------------
package tasks;

public class Autonomous {

    // Variables for mecanum drive and kicker
    MecanumDrive MecanumDriveAutonomous;
    Kicker autonomousKicker;

    // Variables for keeping tack of the current time in autonomous mode
    double cycleCount = 0.0;
    double currentTime = 0.0;

    // Constants used for mecanum drive
    final double FORWARD_DIR = 0.0;
    final double FORWARD_MAG_100 = 1.0;
    final double FORWARD_MAG_75 = 0.75;
    final double FORWARD_MAG_50 = 0.50;
    final double FORWARD_MAG_25 = 0.25;
    final double FORWARD_MAG_0  = 0.0;

    public Autonomous() {

        // Instantiate mecanum drive and kicker systems. The boolean false
        // argument is passed to the constructors to let each object know that
        // it is not in teleop mode.
        MecanumDriveAutonomous = new MecanumDrive(false);
        autonomousKicker = new Kicker(false);

    }

    public void performAutonomous() {

        // Keep track of the current time. cycleCount keeps track of the every
        // time performAutonomous is called and it is multipled by 0.02 which
        // represents how fast performAutonomous is called (200 Hz). 
        // currentTime is in seconds.
        currentTime = cycleCount++ * 0.02;

        // Perform the autonomous movement and kicking as long currentTime
        // is less than 5 seconds
        if (currentTime < 5.0) {

            autonomousKicker.autonomousKick(-0.6);
            MecanumDriveAutonomous.autonomousDrive(FORWARD_MAG_100, FORWARD_DIR);
        
        // Set the autonomous movement and kick speed to 0 once the currentTime
        // exceeds 5 seconds.
        } else if (currentTime >= 5.0) {

            autonomousKicker.autonomousKick(0.0);
            MecanumDriveAutonomous.autonomousDrive(FORWARD_MAG_0, FORWARD_DIR);
        }

    }
}
