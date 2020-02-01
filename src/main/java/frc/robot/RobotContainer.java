/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.MoveDart;
import frc.robot.subsystems.Dart;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.TakeIn;
import frc.robot.commands.TakeOut;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Queuer;
import frc.robot.util.JoystickController;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static Shooter shooter;
  // The robot's subsystems and commands are defined here...
  shooter = new Shooter();

  public static Intake intake;
  public static Queuer queuer;
  public static Dart dart;

  public static JoystickController j2;
  public static JoystickController j0;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    j0 = new JoystickController(0);
    queuer = new Queuer();
    dart = new Dart();
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

    dart.setDefaultCommand(new MoveDart());


    j0.b8.whenPressed(new TakeIn());
    j0.b9.whenPressed(new TakeOut());
    
    intake.setDefaultCommand(new TakeIn());
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
