/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.util.EmpiricalShooterModel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class VisionPIDDartMove extends PIDCommand {
    /**
     * Creates a new PIDDartMove.
     */
    public VisionPIDDartMove(double position) {
        super(
                // The controller that the command will use
                new PIDController(0.007, 0, 0),
                // This should return the measurement
                () -> RobotContainer.distanceSensor.getDistance(),
                // This should return the setpoint (can also be a constant)
                () -> {
                    double distance = NetworkTableInstance.getDefault().getTable("Vision").getEntry("distance")
                            .getDouble(0);
                    if (distance == 0) {
                        return RobotContainer.distanceSensor.getDistance();
                    } else {
                        return EmpiricalShooterModel.get(distance);
                    }
                },
                // This uses the output
                output -> {
                    // Use the output here
                    output += Math.copySign(Constants.DART_kF, output); // Feed forward
                    RobotContainer.dart.move(output);
                });
        // Use addRequirements() here to declare subsystem dependencies.
        // Configure additional PID options by calling `getController` here.
        addRequirements(RobotContainer.dart);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return !RobotContainer.distanceSensor.isRangeValid();
    }
}
