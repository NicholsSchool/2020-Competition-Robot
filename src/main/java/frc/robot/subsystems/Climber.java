package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
    private WPI_TalonSRX climber;

    public Climber() {
        climber = new WPI_TalonSRX(RobotMap.CLIMBER_MOTOR_ID);
    }

    public void move(double speed){
        climber.set(speed);
    }

    public void stop(){
        climber.stopMotor();
    }
}
