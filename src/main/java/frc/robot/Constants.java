// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static String CANBUS_NAME = "rio";

    public static final Mode simMode = Mode.SIM;
    public static final Mode realMode = Mode.REAL;
    public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;
    public static boolean isInAuto = true;
    public static boolean isBlueAlliance = true;
    public static int NUM_LEDS;
    public static double LOOP_TIME = 0.02;
    public static List<Double> intakeTimes = new ArrayList<>();

    public static enum Mode {
        /** Running on a real robot. */
        REAL,

        /** Running a physics simulator. */
        SIM,

        /** Replaying from a log file. */
        REPLAY
    }

    /* Talon FX Device IDs */
    public static final int LEFT_LEADER_ID = 1;
    public static final int LEFT_FOLLOWER_ID = 2;
    public static final int RIGHT_LEADER_ID = 3;
    public static final int RIGHT_FOLLOWER_ID = 4;

    /* Sensor IDs */
    public static final int PIGEON2_ID = 1;

    public static final double maxTelopVelocity = 1;//TODO
    public static final double maxTelopAngularVelocity = 1;//TODO
    public static final double leftYDeadband = 1;//TODO
    public static final double leftXDeadband = 1;//TODO
    public static final double rightXDeadband = 1;//TODO
    public static final double maxAngularVelocity = 1;//TODO
    // public static final enum currentMode = 1;//TODO
    public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    // public static final double maxVelocity = 1;//TODO
    
}
