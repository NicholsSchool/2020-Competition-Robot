/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.autonomous.*;
import frc.robot.commands.*;
import frc.robot.sensors.*;
import frc.robot.subsystems.*;
import frc.robot.util.JoystickController;
import frc.robot.util.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

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
    public static Cameras camera;
    public static APP app;
    public static IRSystem irSystem;
    public static DistanceSensor distanceSensor;
    public static Climber climber;

    public static Shooter shooter;
    public static Queuer queuer;
    public static Dart dart;
    public static ColorWheelSpinner spinner;
    public static Compressor compressor;

    public static XboxController c0;
    public static XboxController c1;

    public static JoystickController j2;

    public static boolean irSensorOveride;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
   


        navX = new NavX(new AHRS(SPI.Port.kMXP));
        irSystem = new IRSystem();
        distanceSensor = new DistanceSensor();
        camera = new Cameras();
        app = new APP();

        irSensorOveride = false;
        compressor = new Compressor(RobotMap.COMPRESSOR_ID);
        driveTrain = new DriveTrain();
        climber = new Climber();

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

        c0.rTrigger.whileHeld(new Intake()).whenReleased(new Queue());

        c1.lTrigger.whileHeld(new Intake()).whenReleased(new Queue());

        c1.rTrigger.whileHeld(new Shoot()); // 5

        c1.rBumper.whileHeld(new ShootOne()); // 1
        c1.lBumper.whenPressed(new Agitate().withTimeout(Constants.QUEUER_AGITATE_TIME)
                .andThen(new ReverseAgitate().withTimeout(Constants.QUEUER_AGITATE_TIME)));
        
        c1.b.whenPressed(new InstantCommand(() -> irSensorOveride = true))
        .whenReleased(new InstantCommand(() -> irSensorOveride = false));

        c1.start.and(c1.select).whenActive(new InstantCommand(() -> climber.toggleExtender(),
        climber));
        c0.y.and(c1.y).whenActive(new InstantCommand(() -> climber.engageBreak(),
        climber));
        c0.x.and(c1.x).whenActive(new InstantCommand(() -> climber.disengageBreak(),
        climber));


        c0.lTrigger.whenPressed(new InstantCommand(() -> driveTrain.engageBackOmnis()));
        c0.lBumper.whenPressed(new InstantCommand(() -> driveTrain.disengageBackOmnis()));

        c0.a.whenPressed(new InstantCommand(() -> driveTrain.setFastMode(true)));
        c0.b.whenPressed(new InstantCommand(() -> driveTrain.setFastMode(false)));

        // c1.b.whileHeld(new SpinCWS());
        c1.lStick.and(c1.rStick).whileActiveContinuous(new Outtake());
        c1.dpadRight.whenPressed(new PIDDartMove(345));

        // Need: auto align, arm up and down, control pannel pos

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return new BBDrive(-36, 0.55)
        .andThen(new VisionPIDTurn().withTimeout(2)).alongWith(new PIDDartMove(350))
        .andThen(new Shoot().withTimeout(5));
    }
}
