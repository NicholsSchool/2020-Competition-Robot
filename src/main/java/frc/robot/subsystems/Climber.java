package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
    public WPI_TalonSRX climber;
    public Solenoid climbBreak;

    public Climber() {
        climber = new WPI_TalonSRX(RobotMap.CLIMBER_MOTOR_ID);
        climbBreak = new Solenoid(0);
    }

    public void engageBreak() {
        climbBreak.set(Constants.BREAK_SWITCH_ENGAGE);
    }

    public void disengageBreak(){
        climbBreak.set(Constants.BREAK_SWITCH_DISENGAGE);
    }

    public void retract() {
        move(-Constants.CLIMBER_SPEED);
    }

    public void move(double speed) {
  
        climber.set(speed);
    }

    public void stop() {
        climber.stopMotor();
    }
}
