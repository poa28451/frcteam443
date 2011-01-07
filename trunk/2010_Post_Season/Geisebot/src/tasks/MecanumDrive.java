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
    private static RobotDrive mecanumDrive;
    private static Jaguar frontLeftMotor;
    private static Jaguar frontRightMotor;
    private static Jaguar rearLeftMotor;
    private static Jaguar rearRightMotor;
    private static Gyro yawGyro;
    private static PIDController yawController;
    private Controller driveControl;
    // Set to true or false depending on if you want to test the robot with or
    // without the yaw controller enabled
    private boolean enableYawControl = true;
    // Boolean (true/false) value used to easily enable or disable deadband
    // elimination for full drive system
    private static boolean enableDeadbandElimination = true;
    // PID Parameters
    private static double Kp = -0.04;
    private static double Ki = 0.00001;
    private static double Kd = -0.001;
    // Initialize deadband parameters. Only need to mess with two parameters
    // here: dbMaxBand and dbMinBand.
    private final static int DBMAX = 255;
    private final static int DBMAXBAND = 138;
    private final static int DBCENTER = 128;
    private final static int DBMINBAND = 118;
    private final static int DBMIN = 1;
    // Direction bias
    private final static double DIRECTION_BIAS = 5.0;

    /**
     * Constructor for the mecanum drive system
     */
    public MecanumDrive(boolean enableTeleop) {

        if (enableTeleop) {

            // Instantiate a controller object
            driveControl = new Controller();
        }
    }

    /**
     * Static method used to initialize the mecanum drive system.
     */
    public static void initMecanumDrive() {

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
        yawController = new PIDController(Kp, Ki, Kd);

        // Initialize the current setpoint to zero which will match the starting
        // gyro heading angle
        //yawController.setSetpoint(yawGyro.getAngle());
        yawController.setSetpoint(0);

        // Set the input range to a large range so the robot can spin in circles
        // as much as the driver wants
        yawController.setInputRange(-3600000, 3600000);

        // Enable the PID Controller
        yawController.enable();
    }

    /**
     * A method used for autonomous driving.
     * 
     * @param Magnitude Joystick magnitude
     * @param Direction Joystick direction
     */
    public void autonomousDrive(double Magnitude, double Direction) {

        // Get the gyro angle
        yawController.getInput(yawGyro.getAngle());

        mecanumDrive.holonomicDrive(Magnitude, Direction + DIRECTION_BIAS,
                yawController.performPID());

    }

    /**
     *
     */
    public void performTeleop() {

        // Perform logic below if the Yaw Controller is enabled
        if (enableYawControl) {
            // Get the gyro angle
            yawController.getInput(yawGyro.getAngle());

            // Use holonomic driver where the rotation input is the yaw controller
            mecanumDrive.holonomicDrive(driveControl.getLeftStickMagnitude(),
                    driveControl.getLeftStickDirection() + DIRECTION_BIAS,
                    yawController.performPID() / 2);
        } // Perform the following if the Yaw Controller is not enabled
        else {

            // Drive the robot without the yaw controller. Note that rotation
            // is divided by two to make rotating it less responsive.
            mecanumDrive.holonomicDrive(driveControl.getLeftStickMagnitude(),
                    driveControl.getLeftStickDirection() + DIRECTION_BIAS, 0);
        }

        performYawSetpoints();

    }

    public void performYawSetpoints() {

        // Automatic setpoints for buttons 1 through 4
        if (driveControl.getButton2()) {
            yawController.setSetpoint(0.0);
        } else if (driveControl.getButton1()) {
            yawController.setSetpoint(-45.0);
        } else if (driveControl.getButton3()) {
            yawController.setSetpoint(45.0);
        } else if (driveControl.getButton4()) {
            yawController.setSetpoint(180.0);
        }
    }
}
