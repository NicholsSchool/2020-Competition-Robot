/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
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
import frc.robot.util.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
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
  public static Queuer queuer;
  public static Dart dart;
  public static ColorWheelSpinner spinner;
  public static Compressor compressor;

  public static XboxController c0;
  public static XboxController c1;

  public static JoystickController j2;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    navX = new NavX(new AHRS(SPI.Port.kMXP));
    irSystem = new IRSystem();
    driveTrain = new DriveTrain();
    //climber = new Climber();
    // compressor = new Compressor(RobotMap.COMPRESSOR_ID);
    // Solenoid test = new Solenoid(1);

    spinner = new ColorWheelSpinner();
    queuer = new Queuer();
    dart = new Dart();
    shooter = new Shooter();
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    c0 = new XboxController(0);
    c1 = new XboxController(1);

    dart.setDefaultCommand(new MoveDart());

    driveTrain.setDefaultCommand(new Drive());
    
 
    // c0.select.whenPressed(new VisionTurn(1000));
    c0.start.whileHeld(new VisionPIDTurn());
    c0.rBumper.whenPressed(new PlayMusic());

    c0.rTrigger.whileHeld (new Intake() ).whenReleased( new Queue() );

    c1.lTrigger.whileHeld( new Intake() ).whenReleased( new Queue() );

    c1.rTrigger.whileHeld(new Shoot()); // 5

    c1.rBumper.whileHeld(new ShootOne()); // 1
    double agitateTime = 0.05;
    c1.lBumper.whenPressed(new Agitate().withTimeout(agitateTime).andThen(new ReverseAgitate().withTimeout(agitateTime)));

    // c1.start.and(c1.select).whenActive(new InstantCommand(() -> climber.extend(), climber));
    // c0.y.and(c1.y).whenActive(new InstantCommand(() -> climber.engageBreak(), climber));
    // c0.x.and(c1.x).whenActive(new InstantCommand(() -> climber.disengageBreak(), climber));

    c0.a.whenPressed( new InstantCommand(() -> driveTrain.setFastMode(true)));
    c0.b.whenPressed( new InstantCommand(() -> driveTrain.setFastMode(false)));


    c1.b.whileHeld( new SpinCWS());
    c1.lStick.and(c1.rStick).whileActiveContinuous(new Outtake());
    c0.b.whileHeld(new AutoPath());
    //Need: auto align, arm up and down, control pannel pos

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
