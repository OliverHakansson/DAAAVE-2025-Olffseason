package frc.robot.Superstructure;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Superstructure extends SubsystemBase{

    public States currentState;
    public States wantedState;

    public Superstructure(){ // add subsystems
        //beep
    }

    public void applyStates(){
        switch (currentState) {
            case HOME:
                break;    
        }
    }
}
//