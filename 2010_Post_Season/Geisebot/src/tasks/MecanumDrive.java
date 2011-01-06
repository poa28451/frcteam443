//----------------------------------------------------------------------------
//
// Freelance Robotics, Team 443
//
// File: MecanumDrive.java
//
// Description: Primary task logic for the mecanum wheel drive system
//
// Change Log:
// XX/XX - 
// ----------------------------------------------------------------------------
package tasks;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Gyro;
import freelancelibj.PIDController;

public class MecanumDrive {

    // Create variables for the wpilibj classes that we will use
    private RobotDrive mecanumDrive;
    private Jaguar frontLeftMotor;
    private Jaguar frontRightMotor;
    private Jaguar rearLeftMotor;
    private Jaguar rearRightMotor;
    private Gyro yawGyro;
    private PIDController yawController;
    private Controller driveControl;

    // Set to true or false depending on if you want to test the robot with or
    // without the yaw controller enabled
    private boolean enableYawControl = true;

    // Boolean (true/false) value used to easily enable or disable deadband
    // elimination for full drive system
    private boolean enableDeadbandElimination = true;

    // Constructor (initialization) for the Mecanum_Drive Task
    public MecanumDrive() {

        // Initialize deadband parameters. Only need to mess with two parameters
        // here: dbMaxBand and dbMinBand.
        final int DBMAX = 255;
        final int DBMAXBAND = 138;
        final int DBCENTER = 128;
        final int DBMINBAND = 118;
        final int DBMIN = 1;

        // PID Parameters
        double PID_Kp = -0.03;  //-0.01
        double PID_Ki = 0;
        double PID_Kd = -0.001;

        // Instantiate all four motors
        frontLeftMotor = new Jaguar(Constants.DIO_SLOT, Constants.FL_CHNL);
        frontRightMotor = new Jaguar(Constants.DIO_SLOT, Constants.FR_CHNL);
        rearLeftMotor = new Jaguar(Constants.DIO_SLOT, Constants.RL_CHNL);
        rearRightMotor = new Jaguar(Constants.DIO_SLOT, Constants.RR_CHNL);


        // If deadband is enabled then modify the speed controller parameters
        if (enableDeadbandElimination) {

            // Set the deadband bounds for each motor controller
            frontLeftMotor.setBounds(DBMAX, DBMAXBAND, DBCENTER, DBMINBAND, DBMIN);
            frontRightMotor.setBounds(DBMAX, DBMAXBAND, DBCENTER, DBMINBAND, DBMIN);
            rearLeftMotor.setBounds(DBMAX, DBMAXBAND, DBCENTER, DBMINBAND, DBMIN);
            rearRightMotor.setBounds(DBMAX, DBMAXBAND, DBCENTER, DBMINBAND, DBMIN);

            // Enable deadband elimination
            frontLeftMotor.enableDeadbandElimination(true);
            frontRightMotor.enableDeadbandElimination(true);
            rearLeftMotor.enableDeadbandElimination(true);
            rearRightMotor.enableDeadbandElimination(true);
        }

        // Create the robot drive with the four Jaguar objects
        mecanumDrive = new RobotDrive(frontLeftMotor, rearLeftMotor,
                frontRightMotor, rearRightMotor);

        // Invert the front right and rear right motor directions. This is necessary
        // for the mecanum wheels to work correctly.
        mecanumDrive.setInvertedMotor(MotorType.kFrontRight, true);
        mecanumDrive.setInvertedMotor(MotorType.kRearRight, true);

        // Create a new instance of the gyro
        yawGyro = new Gyro(Constants.ANLG_SLOT, Constants.GYRO_CHNL);

        // Create a new instance of the Freelance PID_Controller
        yawController = new PIDController(PID_Kp, PID_Ki, PID_Kd);

        // Initialize the current setpoint to zero which will match the starting
        // gyro heading angle
        //yawController.setSetpoint(yawGyro.getAngle());
        yawController.setSetpoint(0);
        
        // Set the input range to a large range so the robot can spin in circles
        // as much as the driver wants
        yawController.setInputRange(-3600000, 3600000);

        // Enable the PID Controller
        yawController.enable();

        //
        driveControl = new Controller();

    }

    public void autonomousDrive(double Magnitude, double Direction) {

        // Get the gyro angle
        yawController.getInput(yawGyro.getAngle());

        mecanumDrive.holonomicDrive(Magnitude, Direction, yawController.performPID());

    }

    // This is the driver method for the Mecanum_Drive task
    public void Perform_Teleop() {


        // Perform logic below if the Yaw Controller is enabled
        if (enableYawControl) {

            // Get the gyro angle
            yawController.getInput(yawGyro.getAngle());

            // Use holonomic driver where the rotation input is the yaw controller
            mecanumDrive.holonomicDrive(driveControl.getLeftStickMagnitude(),
                    driveControl.getLeftStickDirection(),
                    yawController.performPID()/2 );

            // !!!!DEBUG!!!
            //System.out.println(yawGyro.getAngle());


            // Automatic setpoints for buttons 1 through 4
            if (driveControl.getButton2()) {

                yawController.setSetpoint(0.0);

            } else if (driveControl.getButton1()) {

                yawController.setSetpoint(45.0);

            } else if (driveControl.getButton3()) {

                yawController.setSetpoint(180.0);

            } else if (driveControl.getButton4()) {

                yawController.setSetpoint(-45.0);

            }

        }

        // Perform the following if the Yaw Controller is not enabled
        else {

            // Drive the robot without the yaw controller. Note that rotation
            // is divided by two to make rotating it less responsive.
            mecanumDrive.holonomicDrive(driveControl.getLeftStickMagnitude(),
                    driveControl.getLeftStickDirection(),
                    0);
        }



    }
}
