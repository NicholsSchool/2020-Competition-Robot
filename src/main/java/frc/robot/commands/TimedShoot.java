/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TimedShoot extends CommandBase {
    private long startTime;
    private int ballsInSystem;
    private double shootGap;
    private boolean atVelocity;

    /**
     * Creates a new TimedAutoShoot.
     */
    public TimedShoot(double shootGap) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(RobotContainer.queuer);
        addRequirements(RobotContainer.dart);
        
        this.shootGap = shootGap * 1000;

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.ballsInSystem = 5;
        RobotContainer.dart.setBrakeMode(true);

        startTime = System.currentTimeMillis();
        atVelocity = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // RobotContainer.shooter.shoot();

        if ((RobotContainer.shooter.isAtVelocity() || timeElapsed() > Constants.SHOOT_TIMEOUT * 1000) && !atVelocity) {
            atVelocity = true;
            startTime = System.currentTimeMillis();
        }
        if (atVelocity) {
            for (int i = 0; i < ballsInSystem; i++) {
                if (timeElapsed() >= shootGap * i) {
                    System.out.println("Unloading: " + (4 - i));
                    RobotContainer.queuer.move(Constants.QUEUE_UNLOAD_SPEED, 4 - i);
                }
            }
        }

    }

    private long timeElapsed() {
        return System.currentTimeMillis() - startTime;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // RobotContainer.shooter.stop();
        RobotContainer.queuer.stop();
        RobotContainer.dart.setBrakeMode(false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(RobotContainer.irSensorOveride)
            return false;
        return RobotContainer.queuer.isEmpty();
    }
}
