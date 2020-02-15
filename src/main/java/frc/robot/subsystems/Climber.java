package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
    public Solenoid climber;
    public Solenoid climbBreak;

    public Climber() {
        climber = new Solenoid(RobotMap.CLIMBER_SOLENOID_CHANNEL);
        climbBreak = new Solenoid(RobotMap.CLIMBBREAK_SOLENOID_CHANNEL);

    }

    public void engageBreak() {
        climbBreak.set(Constants.BREAK_SWITCH_ENGAGE);
    }

    public void disengageBreak(){
        climbBreak.set(Constants.BREAK_SWITCH_DISENGAGE);
    }

    public void  extend(){        
        climber.set(Constants.CLIMBER_EXTEND);
    }

    public void stop() {
        climber.set(Constants.CLIMBER_STOP);
    }
}
