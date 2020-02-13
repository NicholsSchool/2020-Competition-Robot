/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveHookArm extends CommandBase {
  /**
   * Creates a new MoveHookArm.
   */
  public MoveHookArm() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    addRequirements(RobotContainer.climb);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int pov = RobotContainer.j2.getPOV();
    if(pov == 315 || pov == 0 || pov ==45)
        RobotContainer.climb.move(Constants.CLIMBER_SPEED);
    else if(pov == 225 || pov == 180 || pov == 135)
        RobotContainer.climb.move(-Constants.CLIMBER_SPEED);
    else
        RobotContainer.climb.move(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
