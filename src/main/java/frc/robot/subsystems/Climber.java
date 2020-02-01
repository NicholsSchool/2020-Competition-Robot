package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
    public WPI_TalonSRX climber;

    public Climber() {
        climber = new WPI_TalonSRX(RobotMap.CLIMBER_MOTOR_ID);
    }

    public void climb(){
        move(Constants.CLIMBER_SPEED);
    }

    public void retract(){
        move(-Constants.CLIMBER_SPEED);
    }
        
    private void move(double speed){
        climber.set(speed);
    }

    public void stop(){
        climber.stopMotor();
    }
}
