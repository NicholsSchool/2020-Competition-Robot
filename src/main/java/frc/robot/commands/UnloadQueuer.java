/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class UnloadQueuer extends CommandBase {
  /**
   * Creates a new UnloadQueuer.
   */
  public UnloadQueuer() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.queuer);
    addRequirements(RobotContainer.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.shooter.isAtVelocity())
        RobotContainer.queuer.unload();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.queuer.stop();
    RobotContainer.intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean isEmpty = true;
    boolean[] sensorValues = RobotContainer.irSystem.getValues();
    for(boolean b : sensorValues)
      if(!b)
        isEmpty = false;
      return isEmpty;
  }
}
