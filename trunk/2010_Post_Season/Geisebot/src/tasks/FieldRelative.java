package tasks;

public class FieldRelative {

    double currentAngle = 0;
    double initialAngle = 0;
    double joystickX_Pos = 0;
    double joystickY_Pos = 0;
    double transformX_Pos = 0;
    double transformY_Pos = 0;

    double[][] transformationMatrix = new double[1][1];

    public FieldRelative() {

        /* Initialize the transformation matrix. The matrix has the following
         * format where theta represents the angle of the robot (gyro angle).
         *
         * |  cos(theta)  -sin(theta) |
         * |  sin(theta)   cos(theta) |
         *
         */
        transformationMatrix[0][0] =      Math.cos(initialAngle);
        transformationMatrix[0][1] = -1 * Math.sin(initialAngle);
        transformationMatrix[1][0] =      Math.sin(initialAngle);
        transformationMatrix[1][1] =      Math.cos(initialAngle);

    }

    public double get_Field_Relative_Magnitude() {


        double x = 1;

        return x;

    }

    public double get_Field_Relative_Direction() {

        double x = 1;

        return x;

    }

    public void sendState(double gyroAngle,
            double joystickXAxis,
            double joystickYAxis) {

        // Save the current state
        currentAngle  = gyroAngle;
        joystickX_Pos = joystickXAxis;
        joystickY_Pos = joystickYAxis;

        // Call the transform subroutine. This routine will use the current
        // robot angle and joystick state and determine the "new" positions
        // to be used for conversion to magnitude and direction for holonomic
        // drive
        transform();
    }

    private void transform() {

        // Update the rotation matrix with the current heading angle
        transformationMatrix[0][0] =      Math.cos(currentAngle);
        transformationMatrix[0][1] = -1 * Math.sin(currentAngle);
        transformationMatrix[1][0] =      Math.sin(currentAngle);
        transformationMatrix[1][1] =      Math.cos(currentAngle);
        
        
        // Calculate the transformed x/y axis values
        transformX_Pos = joystickX_Pos * transformationMatrix[0][0] +
                         joystickY_Pos * transformationMatrix[0][1];

        transformY_Pos = joystickX_Pos * transformationMatrix[1][0] +
                         joystickY_Pos * transformationMatrix[1][1];
        
    }
}
