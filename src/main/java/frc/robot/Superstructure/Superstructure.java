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
            case PREPCLIMB:
                //set elevaterc
                //set wrist
                //set servo




                if(true) // switches + stalled + elevator + wrist + servo
                    wantedState = States.PULLCLIMB;
                break;   
            case PULLCLIMB:
                //move elevator down
                //move wrist up
                //stop coral rollers
                break;    
            case L1:
                //if drivestate not in AllignReef set drivesste AllignReef
                //set elevatorstate to L1
                //set wriststate to L1
                //if allined
                    //set state to Eject coral
                break;        
            case L2:
                //if drivestate not in AllignReef set drivesste AllignReef
                //set elevatorstate to L2
                //set wriststate to L2/3
                //if allined
                    //set state to Eject coral
                break;
            case L3:
                //if drivestate not in AllignReef set drivesste AllignReef
                //set elevatorstate to L3
                //set wriststate to L2/3
                //if allined
                    //set state to Eject coral
                break;        
            case L4:
                //if drivestate not in AllignReef set drivesste AllignReef
                //set elevatorstate to L4
                //set wriststate to L4
                //if allined
                    //set state to Eject coral
                break;
            case HOLDCORAL:
                //set coralrolersstate to HOLD
                //set elevatorstate to L1 if not in L1
                //set wriststate to SAFE if not in SAFE
                break;
            case CORALINTAKE:
                //if armstate in INTAKE armstateset to INTAKE
                //if coralrollerstate in INTAKE coralrollerstate to INTAKE
                break; 
            case SCORE:
                //set coralrollerstate to EXTAKE
                //Cheak if Both BeenBrakes are not detecting
                
                    //set state to reverse
                break;
            case REVERSE:
                //if drivestate not in REVERSE set drivestate to REVERSE
                    //set Elevatorstate to INTAKE
                    //set Wriststate to INTAKE
                //if reverse timer == -1
                    //Set reverse timer to 0
                //iterate reverse timer by 1
                //if reverse timer >= 50 (67)
                    //set state to
                break;    
            case GROUNDALGAE:
                //move elevator down
                //move wrist to ground algae
                //start coral rollers
                  if(true){   
                        //NOT HOLDING ALGAE
                }else{
                    //holding algae
                    wantedState = States.HOLDALGAE;
                }
             
                break;
            case REEFALGAE:
                //move elevator down
                //move wrist to ground algaee
                //start coral rollers
                     if(true){   
                        //NOT HOLDING ALGAE
                }else{
                    //holding algae
                    wantedState = States.HOLDALGAE;
                }
             
                break;
            case HOLDALGAE:
                //stop coral rollers
                //needs driver input
                if(true){  //needs driver input
                    //has algae
                }else{
                    //no algae
                    wantedState = States.HOME;
                } 
                break;
            case NET: 
                //elevater to L4
                //wrist up 
                //needs driver input
                if(true){  //needs driver input
                    //has algae
                }else{
                    //no algae
                    wantedState = States.HOME;
                }
                break; 
            case EJECTALGAE:
                if(true){ // has algae
                    // coral rollers spinn
                }else{
                    wantedState = States.HOME;
                }
                
                break;
            case PROCESSOR: 
                //go to L1 
                if(true){  //needs driver input
                    //has algae
                }else{
                    //no algae
                    wantedState = States.HOME;
                }
                break;
            default:
                //set state to SAFE 
        }

    }

    public void handleStateTransitions(){
        switch (wantedState) {


            case PREPCLIMB:
            if(true){//elevator down
                
            };



            case PULLCLIMB:
                

            case L1:
               if(true){ //have coral
               //elevator to L1
               //align
               wantedState = States.SCORE
               else if(true){//wants to score on L2
               //align
                wantedState = States.L2 
               }
                else if(true){//wants to score on L3
                //align
                wantedState = States.L3 
               }
                else if(true){//wants to score on L4
                //align
                wantedState = States.L4 
               }else{ //not scoring
                wantedState = States.HOLDCORAL
               }
                }  
                break;      
            case L2:
                if(true){ //have coral
               //elevator to L2
               //align
               wantedState = States.SCORE
               else if(true){//wants to score on L1
               //align
                wantedState = States.L1 
               }
                else if(true){//wants to score on L3
                //align
                wantedState = States.L3 
               }
                else if(true){//wants to score on L4
                //align
                wantedState = States.L4 
               }else{ //not scoring
                wantedState = States.HOLDCORAL
               }
                break;
            case L3:
                if(true){ //have coral
               //elevator to L3
               //align
               wantedState = States.SCORE
               else if(true){//wants to score on L2
               //align
                wantedState = States.L2 
               }
                else if(true){//wants to score on L1
                //align
                wantedState = States.L1 
               }
                else if(true){//wants to score on L4
                //align
                wantedState = States.L4 
               }else{ //not scoring
                wantedState = States.HOLDCORAL
               }
                }
                break;        
            case L4:
                if(true){ //have coral
               //elevator to L4
               //align
               wantedState = States.SCORE
               else if(true){//wants to score on L2
               //align
                wantedState = States.L2 
               }
                else if(true){//wants to score on L3
                //align
                wantedState = States.L3 
               }
                else if(true){//wants to score on L1
                //align
                wantedState = States.L1 
               }else{ //not scoring
                wantedState = States.HOLDCORAL
               }
               

                }
                //if hascoral is true and state != PREPCLIMB || state != PULLCLIMB
                    //setstate = L4
                break;   
            case HOLDCORAL:
                //set state to holdcoral
                if(true){
                    //I am main pointer
                } else if(true){
                    //needs driver input goto L1
                } else if(true){
                    //needs driver input goto L2
                }else if(true){
                    //needs driver input goto L3
                }else if(true){
                    //needs driver input goto L4
                };
                break;
            case CORALINTAKE:
                //set state to CORALINTAKE
                break;   
            case SCORE:
                //setstate to score
                break;
            case REVERSE:
                //setstate to reverse
                break;
            case GROUNDALGAE:
                if(true){ // NO GAMEPIECE
                };
                break;
            case NET:
                if(true){ //HAVE ALGAE

                }

               case PREPCLIMB:
             if(true){
                //move elevator down 
                //activate the servo motors
                //close the switches
                //stall the motor
                //move wrist 
                wantedState = States.PULLCLIMB;
            }

            case PULLCLIMB:
           
            
 


    case PREPCLIMB:
                //Stalled motor
                //sswi
                break;   
            case PULLCLIMB:
                //move elevator down
                //move wrist up
                //stop coral rollers
 else{
                    //go to eject
                break;
            case PROCESSOR:
                if(true){
                    //HAVE ALGAE
                } else{
                    //go to eject
                }
            case REEFALGAE:
                if(true){
                    // NO GAMEPIECE
                }
                
                
            case HOLDALGAE:
                if(true){
                    //HAVE ALGAE
                }else if(true){
                    //needs driver input
                    //go to drop
                }else if(true){
                    //needs driver input
                    //go to net
                } else if(true){
                    //needs driver input
                    //go to processor
                }
                    
            case EJECTALGAE:
                if(true){
                    //have Algae
                }

        }
    }
}
//