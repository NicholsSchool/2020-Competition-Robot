/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PIDTurn extends PIDCommand {
    /**
     * Creates a new PIDTurn.
     */
    public PIDTurn(double agl) {
        super(
                // The controller that the command will use
                new PIDController(0.005, 0, 0),
                // This should return the measurement
                () -> RobotContainer.navX.getAngle(),
                // This should return the setpoint (can also be a constant)
                () -> agl,
                // This uses the output
                output -> {
                    RobotContainer.driveTrain.move(output, -output);

                    // Use the output here
                });
        addRequirements(RobotContainer.driveTrain);
        getController().setTolerance(Constants.AUTO_TURN_TOLERANCE);
        // Use addRequirements() here to declare subsystem dependencies.
        // Configure additional PID options by calling `getController` here.
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
