/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import com.ctre.phoenix.music.Orchestra;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.autonomous.*;
import frc.robot.commands.*;
import frc.robot.sensors.*;
import frc.robot.subsystems.*;
import frc.robot.util.JoystickController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static DriveTrain driveTrain;
  public static JoystickController j0;
  public static JoystickController j1;
  public static AHRS ahrs;
  public static NavX navX; 
  public static IRSystem irSystem;
  public static Climber climber;

  public static Shooter shooter;
  public static Intake intake;
  public static Queuer queuer;
  public static Dart dart;
  public static ColorWheelSpinner spinner;
  public static Compressor compressor;

  public static JoystickController j2;
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    navX = new NavX(new AHRS(SPI.Port.kMXP));
    irSystem = new IRSystem();
     
    driveTrain = new DriveTrain();
    //climber = new Climber();
    //compressor = new Compressor(RobotMap.COMPRESSOR_ID);



    // Configure the button bindings

    spinner= new ColorWheelSpinner();
    j0 = new JoystickController(0);

    queuer = new Queuer();
    dart = new Dart();
    shooter = new Shooter();
    // Configure the button bindings
    intake = new Intake();
    
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    j0 = new JoystickController(0);
    j1 = new JoystickController(1);
    j2 = new JoystickController(2);
    
    // j0.b7.whenPressed(new BBTurn(90, 0.6));
    // j0.b5.whenPressed(new PIDTurn(90));
    // j0.b3.whenPressed(new PIDDrive(12));
    // j0.b6.whenPressed(new BBDrive(12, 0.5));
     dart.setDefaultCommand(new MoveDart());

    driveTrain.setDefaultCommand(new Drive());
    j2.b2.whileHeld(new TakeIn()).whenReleased(new Queue());
    j2.b3.whenPressed(new Queue());
    j2.b11.whileHeld(new MoveLock(2));
    j2.b9.whileHeld(new MoveLock(3));
    j2.b7.whileHeld(new MoveLock(4));

     j2.b1.whileHeld(new Shoot().alongWith(new UnloadQueuer()));

    // j1.b2.whileHeld(new TakeOut());
    // j1.b11.whileHeld(new MoveLock(2, true));
    // j1.b9.whileHeld(new MoveLock(3, true));
    // j1.b7.whileHeld(new MoveLock(4, true));

    // j2.b2.whileHeld(new TakeIn());


    // j0.b8.whenPressed(new TakeIn());
    // j0.b9.whenPressed(new TakeOut());

    // j0.b11.whileHeld(new SpinCWS());
    
    // j1.b3.and(j2.b3).whenActive(new InstantCommand(() -> climber.extend(), climber));
    // j1.b8.and(j2.b8).whenActive(new InstantCommand(() -> climber.engageBreak(), climber));
    // j1.b9.and(j2.b9).whenActive(new InstantCommand(() -> climber.disengageBreak(), climber));

    //j0.b11.whileHeld(new SpinCWS());

    j0.b12.whenPressed(new PlayMusic());
    j0.b11.whenPressed(new InstantCommand(() -> driveTrain.resetEncoders(), driveTrain));
    j0.b1.whileHeld(new VisionTurn());
    j1.b1.whileHeld(new TakeIn()).whenReleased(new Queue());

 } 


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // // Create a voltage constraint to ensure we don't accelerate too fast
    // var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
    //     new SimpleMotorFeedforward(Constants.ksVolts, 
    //                             Constants.kvVoltSecondsPerMeter,
    //                            Constants.kaVoltSecondsSquaredPerMeter),
    //     Constants.kDriveKinematics, 10);

    // // Create config for trajectory
    // TrajectoryConfig config = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
    //     Constants.kMaxAccelerationMetersPerSecondSquared)
    //         // Add kinematics to ensure max speed is actually obeyed
    //         .setKinematics(Constants.kDriveKinematics)
    //         // Apply the voltage constraint
    //         .addConstraint(autoVoltageConstraint);

    // // An example trajectory to follow. All units in meters.
    // Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
    //     // Start at the origin facing the +X direction
    //     new Pose2d(0, 0, new Rotation2d(0)),
    //     // Pass through these two interior waypoints, making an 's' curve path
    //     List.of(),
    //     // End 3 meters straight ahead of where we started, facing forward
    //     new Pose2d(3, 0, new Rotation2d(90)),
    //     // Pass config
    //     config);

    // RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, driveTrain::getPose,
    //     new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
    //     new SimpleMotorFeedforward(Constants.ksVolts, Constants.kvVoltSecondsPerMeter,
    //         Constants.kaVoltSecondsSquaredPerMeter),
    //     Constants.kDriveKinematics, driveTrain::getWheelSpeeds,
    //     new PIDController(Constants.kPDriveVel, 0, 0), new PIDController(Constants.kPDriveVel, 0, 0),
    //     // RamseteCommand passes volts to the callback
    //     driveTrain::tankDriveVolts, driveTrain);

    // // Run path following command, then stop at the end.
    // return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
    return null;
  }
}
