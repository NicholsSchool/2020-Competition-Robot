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
import frc.robot.subsystems.Queuer;

public class MoveLock extends CommandBase {

  private int index;
  private boolean isReversed;

  public MoveLock(int index, boolean isReversed)
  {
    this.index = index - 2;
    this.isReversed = isReversed;
  }

  public MoveLock(int index) {
    this(index, false);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!isReversed)
      RobotContainer.queuer.move(Constants.QUEUE_MOVE_SPEED, index);
    else
      RobotContainer.queuer.move(-Constants.QUEUE_MOVE_SPEED, index);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.queuer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.queuer.checkQueuer();
  }
}
