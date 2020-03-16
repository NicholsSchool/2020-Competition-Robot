package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
    private Solenoid extender;
    private Solenoid climbBreak;

    /**
     * Creates a new Climber Instance
     */
    public Climber() {
        extender = new Solenoid(RobotMap.COMPRESSOR_ID, RobotMap.EXTENDER_SOLENOID_CHANNEL);
        climbBreak = new Solenoid(RobotMap.COMPRESSOR_ID, RobotMap.CLIMBBREAK_SOLENOID_CHANNEL);
    }

    /**
     * Engages the Climb Break making the robot ready to climb
     */
    public void engageBreak() {
        climbBreak.set(Constants.BREAK_SWITCH_ENGAGE);
    }

    /**
     * Disengages the Climb Break 
     */
    public void disengageBreak(){
        climbBreak.set(Constants.BREAK_SWITCH_DISENGAGE);
    }

    /**
     * Toggles the state of the hook extender, locked or unlocked
     */
    public void toggleExtender()
    {
        if(extender.get() == Constants.CLIMBER_EXTEND)
            lockExtender();
        else 
            extend();
    }

    /**
     * Unlocks the hook extender, allowing it to extend
     */
    public void extend(){        
        extender.set(Constants.CLIMBER_EXTEND);
    }

    /**
     * Locks the extender
     */
    public void lockExtender() {
        extender.set(Constants.CLIMBER_LOCK_EXTENDER);
    }

    /**
     * returns true if the climb break is engaged
     * 
     * @return true if the climb break is engaged
     */
    public boolean isClimbEngaged()
    {
       return climbBreak.get() == Constants.BREAK_SWITCH_ENGAGE;
    }

    /**
     * returns true if the hook is unlocked
     * @return true if the hook is unlocked
     */
    public boolean isHookUnlocked() 
    {
        return extender.get() == Constants.CLIMBER_EXTEND;
    }

    /**
     * Outputs info to the SmartDashboard
     */
    @Override
    public void periodic()
    {
        SmartDashboard.putBoolean("**HOOK UNLOCKED**", isHookUnlocked());
        SmartDashboard.putBoolean("**CLIMB ENGAGED**", isClimbEngaged());
    }
}
