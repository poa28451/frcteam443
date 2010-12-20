//----------------------------------------------------------------------------
// Copyright (c) FIRST 2008. All Rights Reserved.
// Open Source Software - may be modified and shared by FRC teams. The code
// must be accompanied by the FIRST BSD license file in the root directory of
// the project.
//
// File: Mecanum_Drive.java
//
// Description: Primary task logic for the mecanum wheel drive system
//
// Lead: TBD
// ----------------------------------------------------------------------------
package tasks;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Gyro;
import freelancelibj.FL_PIDController;

public class Mecanum_Drive {

    // Create variables for the wpilibj classes that we will use
    private RobotDrive FL_RobotDrive;
    private Jaguar FL_Front_Left_Motor;
    private Jaguar FL_Front_Right_Motor;
    private Jaguar FL_Rear_Left_Motor;
    private Jaguar FL_Rear_Right_Motor;
    private Gyro FL_Gyro;
    private FL_PIDController Yaw_Controller;

    // Set to true or false depending on if you want to test the robot with or
    // without the yaw controller enabled
    private boolean Enable_Yaw_Control = true;

    // Boolean (true/false) value used to easily enable or disable deadband
    // elimination for full drive system
    private boolean Enable_Deadband_Elimination = true;

    // Constructor (initialization) for the Mecanum_Drive Task
    public Mecanum_Drive() {

        // Jaguar Motor Controller parameters
        final int JAGUAR_DIO_SLOT = 4;
        final int JAGUAR_FL_CHANNEL = 1;
        final int JAGUAR_FR_CHANNEL = 4;
        final int JAGUAR_RL_CHANNEL = 2;
        final int JAGUAR_RR_CHANNEL = 3;

        // Initialize deadband parameters. Only need to mess with two parameters
        // here: dbMaxBand and dbMinBand.
        final int DBMAX = 255;
        final int DBMAXBAND = 138;
        final int DBCENTER = 128;
        final int DBMINBAND = 118;
        final int DBMIN = 1;

        // Gyro Parameters. Note that the Yaw channel designates rotation of the
        // robot around the vertical axis.
        final int GYRO_YAW_CHANNEL = 1;

        // PID Parameters
        double PID_Kp = -0.03;  //-0.01
        double PID_Ki = 0;
        double PID_Kd = -0.001;

        // Instantiate all four motors
        FL_Front_Left_Motor = new Jaguar(JAGUAR_DIO_SLOT, JAGUAR_FL_CHANNEL);
        FL_Front_Right_Motor = new Jaguar(JAGUAR_DIO_SLOT, JAGUAR_FR_CHANNEL);
        FL_Rear_Left_Motor = new Jaguar(JAGUAR_DIO_SLOT, JAGUAR_RL_CHANNEL);
        FL_Rear_Right_Motor = new Jaguar(JAGUAR_DIO_SLOT, JAGUAR_RR_CHANNEL);


        // If deadband is enabled then modify the speed controller parameters
        if (Enable_Deadband_Elimination) {

            // Set the deadband bounds for each motor controller
            FL_Front_Left_Motor.setBounds(DBMAX, DBMAXBAND, DBCENTER, DBMINBAND, DBMIN);
            FL_Front_Right_Motor.setBounds(DBMAX, DBMAXBAND, DBCENTER, DBMINBAND, DBMIN);
            FL_Rear_Left_Motor.setBounds(DBMAX, DBMAXBAND, DBCENTER, DBMINBAND, DBMIN);
            FL_Rear_Right_Motor.setBounds(DBMAX, DBMAXBAND, DBCENTER, DBMINBAND, DBMIN);

            // Enable deadband elimination
            FL_Front_Left_Motor.enableDeadbandElimination(true);
            FL_Front_Right_Motor.enableDeadbandElimination(true);
            FL_Rear_Left_Motor.enableDeadbandElimination(true);
            FL_Rear_Right_Motor.enableDeadbandElimination(true);
        }

        // Create the robot drive with the four Jaguar objects
        FL_RobotDrive = new RobotDrive(FL_Front_Left_Motor, FL_Rear_Left_Motor,
                FL_Front_Right_Motor, FL_Rear_Right_Motor);

        // Invert the front right and rear right motor directions. This is necessary
        // for the mecanum wheels to work correctly.
        FL_RobotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        FL_RobotDrive.setInvertedMotor(MotorType.kRearRight, true);

        // Create a new instance of the gyro
        FL_Gyro = new Gyro(GYRO_YAW_CHANNEL);

        // Create a new instance of the Freelance PID_Controller
        Yaw_Controller = new FL_PIDController(PID_Kp, PID_Ki, PID_Kd);

        // Initialize the current setpoint to zero which will match the starting
        // gyro heading angle
        //Yaw_Controller.setSetpoint(FL_Gyro.getAngle());
        Yaw_Controller.setSetpoint(0);
        
        // Set the input range to a large range so the robot can spin in circles
        // as much as the driver wants
        Yaw_Controller.setInputRange(-3600000, 3600000);

        // Enable the PID Controller
        Yaw_Controller.enable();

    }

    // This is the driver method for the Mecanum_Drive task
    public void Perform_Teleop(double Joystick_Magnitude,
            double Joystick_Direction,
            double Joystick_Rotation,
            boolean Joystick_Button1,
            boolean Joystick_Button2,
            boolean Joystick_Button3,
            boolean Joystick_Button4) {


        // Perform logic below if the Yaw Controller is enabled
        if (Enable_Yaw_Control) {

            // Get the gyro angle
            Yaw_Controller.getInput(FL_Gyro.getAngle());

            // Use holonomic driver where the rotation input is the yaw controller
            FL_RobotDrive.holonomicDrive(Joystick_Magnitude,
                    Joystick_Direction,
                    Yaw_Controller.performPID()/2 );

            // !!!!DEBUG!!!
            //System.out.println(FL_Gyro.getAngle());


            // Automatic setpoints for buttons 1 through 4
            if (Joystick_Button1) {

                Yaw_Controller.setSetpoint(0.0);

            } else if (Joystick_Button2) {

                Yaw_Controller.setSetpoint(45.0);

            } else if (Joystick_Button3) {

                Yaw_Controller.setSetpoint(180.0);

            } else if (Joystick_Button4) {

                Yaw_Controller.setSetpoint(-45.0);

            }

        }

        // Perform the following if the Yaw Controller is not enabled
        else {

            // Drive the robot without the yaw controller. Note that rotation
            // is divided by two to make rotating it less responsive.
            FL_RobotDrive.holonomicDrive(Joystick_Magnitude,
                    Joystick_Direction,
                    Joystick_Rotation / 2);
        }



    }
}
