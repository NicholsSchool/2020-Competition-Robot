/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class VisionPIDTurn extends PIDCommand {
  /**
   * Creates a new PIDTurn.
   */
  public VisionPIDTurn() {
    super(
        // The controller that the command will use
        new PIDController(2.5, 0, -0.001),
        // This should return the measurement
        () -> {
          NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
          return table.getEntry("x").getDouble(0) / Math.max(1, table.getEntry("z").getDouble(0));
        },
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          System.out.println("Turning Output: " + output);
          System.out.println("X: " + NetworkTableInstance.getDefault().getTable("Vision").getEntry("x").getDouble(0));
          RobotContainer.driveTrain.move(-output, output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.

    addRequirements(RobotContainer.driveTrain);
  }

  @Override
  public void initialize() {
    super.initialize();
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}