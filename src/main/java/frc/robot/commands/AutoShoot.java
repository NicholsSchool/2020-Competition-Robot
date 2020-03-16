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

public class AutoShoot extends CommandBase {
  private long timeStarted;

  public AutoShoot() {
        addRequirements(RobotContainer.shooter);
        addRequirements(RobotContainer.queuer);
    }

  @Override
  public void initialize() {
    timeStarted = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    RobotContainer.shooter.shoot();
    System.out.println("Shooting Time: " + (System.currentTimeMillis() - timeStarted));
    if (RobotContainer.shooter.isAtVelocity()
        || System.currentTimeMillis() - timeStarted > Constants.SHOOT_TIMEOUT * 1000) {
      if (RobotContainer.shooter.isAtVelocity())
        timeStarted = System.currentTimeMillis();
      RobotContainer.queuer.unload();
    } else
      RobotContainer.queuer.stop();
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.stop();
    RobotContainer.queuer.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
