//----------------------------------------------------------------------------
//
// Freelance Robotics, Team 443
//
// File: Kicker.java
//
// Description: Primary task logic for the kicking mechanism
//
// Revision Log:
// 12/1  - First design of kicker task (Mark)
// 12/10 - Fixed bug so kicker will turn off after turning on (Kyle)
// ----------------------------------------------------------------------------

package tasks;

import edu.wpi.first.wpilibj.Jaguar;

public class Kicker {

    private Jaguar Kicker;
    private Controller kickerControl;

    public Kicker(){

        Kicker = new Jaguar(Constants.DIO_SLOT, Constants.KICKER_CHNL);
        
        kickerControl = new Controller();

    }

     public void Perform_Teleop(){
        
         if(kickerControl.getButton6()){
            Kicker.set(-0.9);
        }
        else if(kickerControl.getButton8()){
            Kicker.set(-0.5);
        }
        else if(kickerControl.getButton10()){
            Kicker.set(0.2);
        }
        else {
            Kicker.set(0);
        }

    }

}
