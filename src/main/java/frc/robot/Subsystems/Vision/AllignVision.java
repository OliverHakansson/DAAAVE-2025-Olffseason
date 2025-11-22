package frc.robot.Subsystems.Vision;

public class AllignVision{

    public static AllignVision instance = null;

    public AllignVisionIO io;

    public static AllignVision getInstance(){
        return instance;
    }

    public AllignVision(){
        instance = this;
        io = new AllignVisionIO();
    }


    // public something GetChassiesSpeeds(){
        
    // }
}