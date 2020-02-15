/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class PlayMusic extends CommandBase {

  public PlayMusic() {
    addRequirements(RobotContainer.shooter);
  }

  @Override
  public void initialize() {
    RobotContainer.shooter.playMusic();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.stopMusic();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
