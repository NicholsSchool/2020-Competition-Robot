/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class VisionTurn extends CommandBase {
    private NetworkTable table;

    /**
     * Creates a new VisionTurn.
     */
    public VisionTurn() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(RobotContainer.driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        table = NetworkTableInstance.getDefault().getTable("Vision");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double x = table.getEntry("x").getDouble(0);
        double z = table.getEntry("z").getDouble(0);
        double theta = 0;
        if (x != 0 && z != 0) {
            x += 9; // account for shooter offset
            z += 12;
            theta = Math.toDegrees(Math.atan(x / z));
        }

        if (theta > 10) {
            RobotContainer.driveTrain.move(0.5, -0.5);
        } else if (theta > 1) {
            RobotContainer.driveTrain.move(0.35, -0.35);
        } else if (theta < -10) {
            RobotContainer.driveTrain.move(-0.5, 0.5);
        } else if (theta < -1) {
            RobotContainer.driveTrain.move(-0.35, 0.35);
        } else {
            RobotContainer.driveTrain.stop();
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
        return false;
    }
}
