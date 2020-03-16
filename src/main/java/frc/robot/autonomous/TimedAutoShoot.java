/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TimedAutoShoot extends CommandBase {
  private long startTime;
  private int ballsInSystem;
  /**
   * Creates a new TimedAutoShoot.
   */
  public TimedAutoShoot(int ballsInSystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.queuer);
    addRequirements(RobotContainer.shooter);
    this.ballsInSystem = ballsInSystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.shooter.shoot();
    if(RobotContainer.shooter.isAtVelocity())
    {
      if(startTime == 0)
        startTime = System.currentTimeMillis();

      for(int i = 0; i < ballsInSystem + 1; i ++)
      {
        if(System.currentTimeMillis() - startTime >= Constants.TIMED_SHOOT_GAP * 1000 * i)
          RobotContainer.queuer.move(Constants.QUEUE_MOVE_SPEED, 4 - i);

        if(i == ballsInSystem && System.currentTimeMillis() - startTime >= Constants.TIMED_SHOOT_GAP * 1000 * (i - 1))
          RobotContainer.queuer.move(Constants.QUEUE_MOVE_SPEED, 4 - i);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.stop();
    RobotContainer.queuer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
