package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
    private Solenoid extender;
    private Solenoid climbBreak;

    public Climber() {
        extender = new Solenoid(RobotMap.COMPRESSOR_ID, RobotMap.EXTENDER_SOLENOID_CHANNEL);
        climbBreak = new Solenoid(RobotMap.COMPRESSOR_ID, RobotMap.CLIMBBREAK_SOLENOID_CHANNEL);
    }

    public void engageBreak() {
        climbBreak.set(Constants.BREAK_SWITCH_ENGAGE);
    }

    public void disengageBreak(){
        climbBreak.set(Constants.BREAK_SWITCH_DISENGAGE);
    }

    public void toggleExtender()
    {
        if(extender.get() == Constants.CLIMBER_EXTEND)
            lockExtender();
        else 
            extend();
    }

    public void extend(){        
        extender.set(Constants.CLIMBER_EXTEND);
    }

    public void lockExtender() {
        extender.set(Constants.CLIMBER_LOCK_EXTENDER);
    }

    public boolean isClimbEngaged()
    {
       return climbBreak.get() == Constants.BREAK_SWITCH_ENGAGE;
    }

    public boolean isHookUnlocked() 
    {
        return extender.get() == Constants.CLIMBER_EXTEND;
    }

    @Override
    public void periodic()
    {
        SmartDashboard.putBoolean("**HOOK UNLOCKED**", isHookUnlocked());
        SmartDashboard.putBoolean("**CLIMB ENGAGED**", isClimbEngaged());
    }
}
