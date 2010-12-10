//----------------------------------------------------------------------------
// Copyright (c) FIRST 2008. All Rights Reserved.
// Open Source Software - may be modified and shared by FRC teams. The code
// must be accompanied by the FIRST BSD license file in the root directory of
// the project.
//
// File: Kicker.java
//
// Description: Primary task logic for the kicking mechanism
//
// Lead: TBD
// ----------------------------------------------------------------------------

package tasks;

import edu.wpi.first.wpilibj.Jaguar;


public class Kicker {

    private Jaguar Kicker;

    public Kicker(){

        Kicker = new Jaguar(4,5);

    }

    public void Perform_Teleop(boolean Fast_Kick_Button,
                               boolean Slow_Kick_Button,
                               boolean Reverse_Kick_Button){

        if(Fast_Kick_Button){
            Kicker.set(0.9);
        }
        else if(Slow_Kick_Button){
            Kicker.set(0.5);
        }
        else if(Reverse_Kick_Button){
            Kicker.set(-0.2);
        }
        else {
            Kicker.set(0);
        }
    }

}
