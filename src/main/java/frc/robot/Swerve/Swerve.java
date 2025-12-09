// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Swerve;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import com.pathplanner.lib.events.EventTrigger;
import com.revrobotics.spark.ClosedLoopSlot;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Swerve.SwerveIO.SwerveIOInputs;

import java.security.cert.TrustAnchor;
import java.util.Optional;
import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.Logger;

/**
 * Basic simulation of a swerve subsystem with the methods needed by PathPlanner
 */
public class Swerve extends SubsystemBase {

    SwerveIO io;
    private final SwerveIOInputs inputs = new SwerveIOInputs();
    private static Swerve INSTANCE = null;

    public static enum DriveState {
        None,
        Manual,
        AlignReef,
        AlignProcessor,
        AlignSource,
        Reverse,
        AlignBarge
    };

    private DriveState driveState = DriveState.None;

    public boolean isAlignedToSource;
    public boolean isAlignedToCoralRight;
    public boolean isAlignedToCoralLeft;
    public boolean isAlignedToProcessor;
    public boolean isAlignedCenterReef; // TODO check if the algae removal needs to be centered on the reef
    public static DriveState previousDriveState;
    public static boolean rotLock = true;

    public static Swerve getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Swerve instance not set");
        }
        return INSTANCE;
    }

    public static Swerve setInstance(SwerveIO io) {
        INSTANCE = new Swerve(io);
        return INSTANCE;
    }

    public Optional<Pose2d> getSimPose() {
        return io.getSimPose();
    }

    public Swerve(SwerveIO io) {
        this.io = io;

    }

    // public boolean ableToAlignBarge () {
    //     if (Constants.isBlueAlliance) {// <7.6, red = > 10
    //         return Swerve.getInstance().getPose().getTranslation().getMeasureX().magnitude() < 7.6;
    //     }
    //     return Swerve.getInstance().getPose().getTranslation().getMeasureX().magnitude() > 10;//red
        
    // }

    public void configurePathplanner() {
        try {

            // NamedCommands.registerCommand("AlignSource", new
            // AlignSource().withTimeout(1));
            // NamedCommands.registerCommand("ScoreCoral_E_L4", new
            // ScoreCoral(ElevatorStates.LEVEL4, ReefTargetSide.RIGHT,
            // ReefTargetOrientation.EF).withTimeout(0.75));
            // NamedCommands.registerCommand("ScoreCoral_D_L4", new
            // ScoreCoral(ElevatorStates.LEVEL4, ReefTargetSide.LEFT,
            // ReefTargetOrientation.CD).withTimeout(0.75));
            // NamedCommands.registerCommand("ScoreCoral_C_L4", new
            // ScoreCoral(ElevatorStates.LEVEL4, ReefTargetSide.RIGHT,
            // ReefTargetOrientation.CD).withTimeout(0.75));
            // NamedCommands.registerCommand("ScoreCoral_B_L4", new
            // ScoreCoral(ElevatorStates.LEVEL4, ReefTargetSide.RIGHT,
            // ReefTargetOrientation.AB).withTimeout(0.75));


            
            RobotConfig config = RobotConfig.fromGUISettings();

            // Configure AutoBuilder
            AutoBuilder.configure(
                    this::getPose,
                    this::resetPose,
                    this::getSpeeds,
                    (speeds, feedforwards) -> driveRobotRelativeAuto(speeds),
                    new PPHolonomicDriveController(
                            Constants.Swerve.translationConstants, Constants.Swerve.rotationConstants),
                    config,
                    () -> {
                        // Boolean supplier that controls when the path will be mirrored for the red
                        // alliance
                        // This will flip the path being followed to the red side of the field.
                        // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

                        var alliance = DriverStation.getAlliance();
                        if (alliance.isPresent()) {
                            return alliance.get() == DriverStation.Alliance.Red;
                        }
                        return false;
                    },
                    this);

        } catch (Exception e) {
            DriverStation.reportError(
                    "Failed to load PathPlanner config and configure AutoBuilder", e.getStackTrace());
        }
    }

    public SwerveIO getIo() {
        return io;
    }

    public void zeroGyro() {
        this.io.zeroGyro();
    }

    @Override
    public void periodic() {
        this.io.updateInputs(inputs);
    }

    public double getGyro() {
        return this.io.getGyro();
    }

    public Pose2d getPose() {
        return this.io.getPose();
    }

    public void resetPose(Pose2d pose) {
        this.io.resetPose(pose);
    }

    public void setDriveState(DriveState state) {
        driveState = state;
    }

    public void setPreviousDriveState(DriveState state) {
        previousDriveState = state;
    }

    public DriveState getDriveState() {
        return driveState;
    }

    public DriveState getPreviousDriveState() {
        return previousDriveState;
    }

    public ChassisSpeeds getSpeeds() {
        return this.io.getSpeeds();
    }

    public void driveFieldRelative(ChassisSpeeds fieldRelativeSpeeds) {
        this.io.driveFieldRelative(fieldRelativeSpeeds);
    }

    public void driveRobotRelativeAuto(ChassisSpeeds fieldRelativeSpeeds) {
        this.io.driveRobotRelativeAuto(fieldRelativeSpeeds);
    }

    public void driveRobotRelative(ChassisSpeeds robotRelativeSpeeds) {
        this.io.driveRobotRelative(robotRelativeSpeeds);
    }

    public SwerveModuleState[] getModuleStates() {
        return this.io.getModuleStates();
    }

    public SwerveModuleState[] getModuleDesiredStates() {
        return this.io.getModuleDesiredStates();
    }

    public SwerveModulePosition[] getPositions() {
        return this.io.getPositions();
    }

    /**
     * Command to drive the robot using translative values and heading as a
     * setpoint.
     *
     * @param translationX
     *            Translation in the X direction.
     * @param translationY
     *            Translation in the Y direction.
     * @param headingX
     *            Heading X to calculate angle of the joystick.
     * @param headingY
     *            Heading Y to calculate angle of the joystick.
     * @return Drive command.
     */
    public Command driveCommand(
            DoubleSupplier translationX,
            DoubleSupplier translationY,
            DoubleSupplier headingX,
            DoubleSupplier headingY) {
        return run(
                () -> {
                    double xInput = Math.pow(translationX.getAsDouble(), 3); // Smooth control out
                    double yInput = Math.pow(translationY.getAsDouble(), 3); // Smooth control out
                    // Make the robot move
                    driveFieldRelative(
                            this.io.getTargetSpeeds(
                                    xInput, yInput, headingX.getAsDouble(), headingY.getAsDouble()));
                });
    }

    /**
     * Command to drive the robot using translative values and heading as angular
     * velocity.
     *
     * @param translationX
     *            Translation in the X direction.
     * @param translationY
     *            Translation in the Y direction.
     * @param angularRotationX
     *            Rotation of the robot to set
     * @return Drive command.
     */
    public Command driveCommand(
            DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier angularRotationX) {
        return run(
                () -> {
                    // Make the robot move
                    this.io.driveRobotRelative(
                            new Translation2d(
                                    translationX.getAsDouble() * this.io.getMaximumChassisVelocity(),
                                    translationY.getAsDouble() * this.io.getMaximumChassisVelocity()),
                            angularRotationX.getAsDouble() * this.io.getMaximumChassisAngularVelocity(),
                            true,
                            false);
                });
    }

    public void addVisionReading(
            Pose2d robotPose, double timestamp, Matrix<N3, N1> visionMeasurementStdDevs) {
        this.io.addVisionReading(robotPose, timestamp, visionMeasurementStdDevs);
    }
}