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
public class PIDDrive extends PIDCommand {
    /**
     * Creates a new PIDDrive.
     */
    public PIDDrive(double inches) {
        super(
                // The controller that the command will use
                new PIDController(0.0001, 0, 0),
                // This should return the measurement
                () -> RobotContainer.driveTrain.getEncoderValue(),
                // This should return the setpoint (can also be a constant)
                () -> inches / Constants.INCHES_PER_TICK,
                // This uses the output
                output -> {
                    output += Math.copySign(Constants.DRIVE_TRAIN_DRIVE_kF, output); // Feed forward
                    RobotContainer.driveTrain.move(output, output * Constants.DRIVE_TRAIN_EQUALIZIER);
                    System.out.println("Running PIDDrive at " + output);
                    System.out.println("Target position: " + inches / Constants.INCHES_PER_TICK);
                    // Use the output here
                });
        addRequirements(RobotContainer.driveTrain);
        getController().setTolerance(Constants.AUTO_DRIVE_TOLERANCE);
    }

    @Override
    public void initialize() {
        super.initialize();
        RobotContainer.driveTrain.resetEncoders();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
