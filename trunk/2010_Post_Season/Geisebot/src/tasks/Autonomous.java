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

import edu.wpi.first.wpilibj.Jaguar;

public class Autonomous {

    private MecanumDrive MecanumDriveAutonomous;
    private Kicker autonomousKicker;
    int counter;
    double time;
    final double FORWARD_DIR = 0.0;
    final double FORWARD_MAG_100 = 1.0;
    final double FORWARD_MAG_75 = 0.75;
    final double FORWARD_MAG_50 = 0.50;
    final double FORWARD_MAG_25 = 0.25;

    public Autonomous() {

        counter = 0;
        MecanumDriveAutonomous = new MecanumDrive(false);
        autonomousKicker = new Kicker(false);

    }

    public void performAutonomous() {

        counter++;
        time = counter * 0.01;

        if (time <= 1.0) {

            autonomousKicker.autonomousKick(-0.5);
            MecanumDriveAutonomous.autonomousDrive(FORWARD_MAG_25, 0.0);

        }

        if (time > 1.0 && time <= 2.0){

            autonomousKicker.autonomousKick(-0.5);
            MecanumDriveAutonomous.autonomousDrive(FORWARD_MAG_50, 0.0);

        }

        if (time > 2.0 && time <= 3.0){

            autonomousKicker.autonomousKick(-0.5);
            MecanumDriveAutonomous.autonomousDrive(FORWARD_MAG_75, 0.0);
        }

        if (time > 3.0 && time <= 10.0){

            autonomousKicker.autonomousKick(-0.5);
            MecanumDriveAutonomous.autonomousDrive(FORWARD_MAG_100, 0.0);
        }

        if (time > 10.0){

            autonomousKicker.autonomousKick(0.0);
            MecanumDriveAutonomous.autonomousDrive(0.0, 0.0);
        }
    }
}
