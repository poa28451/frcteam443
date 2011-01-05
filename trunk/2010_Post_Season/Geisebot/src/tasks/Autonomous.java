//----------------------------------------------------------------------------
//
// FREELANCE ROBOTICS - TEAM 443
//
// File: Autonomous.java
//
// Description: Primary logic for the autonomous portion of the competition
//
// Revision Log:
// 12/31 - Created autonomous class
//
// ----------------------------------------------------------------------------
package tasks;

import edu.wpi.first.wpilibj.Jaguar;

public class Autonomous {

    private Jaguar KickerAutonomous;
    private MecanumDrive MecanumDriveAutonomous;
    int counter;
    double time;
    final double FORWARD_DIR = 0.0;
    final double FORWARD_MAG = 0.5;

    public Autonomous() {

        counter = 0;
        KickerAutonomous = new Jaguar(Constants.DIO_SLOT, Constants.KICKER_CHNL);
        MecanumDriveAutonomous = new MecanumDrive();

    }

    public void performAutonomous() {

        counter++;
        time = counter * 0.01;

        if (time <= 5.0) {

            KickerAutonomous.set(-0.5);
            MecanumDriveAutonomous.autonomousDrive(1.0, 0.0);

        } else {

            KickerAutonomous.set(0.0);
            MecanumDriveAutonomous.autonomousDrive(0.0, 0.0);

        }
    }
}
