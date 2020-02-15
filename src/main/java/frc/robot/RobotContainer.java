/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.autonomous.*;
import frc.robot.commands.*;	
import frc.robot.sensors.*;	
import frc.robot.subsystems.*;
import frc.robot.util.JoystickController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
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
  public static Climber climb;

  public static Shooter shooter;
  public static Intake intake;
  public static Queuer queuer;
  public static Dart dart;
  public static ColorWheelSpinner spinner;


  public static JoystickController j2;
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    navX = new NavX(new AHRS(SPI.Port.kMXP));
    driveTrain = new DriveTrain();
    climb = new Climber();


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
    
    j0.b7.whenPressed(new BBTurn(90, 0.6));
    j0.b5.whenPressed(new PIDTurn(90));
    j0.b3.whenPressed(new PIDDrive(12));
    j0.b6.whenPressed(new BBDrive(12, 0.5));
    dart.setDefaultCommand(new MoveDart());

    j0.b8.whenPressed(new TakeIn());
    j0.b9.whenPressed(new TakeOut());

    j0.b11.whileHeld(new SpinCWS());
 } 


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
