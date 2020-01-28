/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class BBDrive extends CommandBase {
  public double desiredDistance;
  public double speed;
  /**
   * Creates a new BBDrive.
   */
  public BBDrive(double dst, double spd) {

      desiredDistance = dst;
      speed = spd;
      addRequirements(RobotContainer.driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.navX.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Moving");
    if (desiredDistance > 0){
       RobotContainer.driveTrain.move(speed, speed);
    }
    else {
      RobotContainer.driveTrain.move(-speed, -speed);
    }
   

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double currentDistance = RobotContainer.navX.getDistance();
    return (currentDistance < desiredDistance + 5 && currentDistance > desiredDistance - 5);
  }
}
