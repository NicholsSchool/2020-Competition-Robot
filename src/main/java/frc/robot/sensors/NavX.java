package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class NavX {

    private AHRS navX;

    /**
     * Creates a new NavX instance
     * @param ahrs the AHRS instance to use
     */
    public NavX(AHRS ahrs) {
        this.navX = ahrs;
    }
    /**
     * Returns the current angle of the robot
     * 
     * @return the current angle of the robot
     */
    public double getAngle() {
        return navX.getYaw();

    }
    /**
     * returns true if at desired angle
     * @param angle angle to compare
     * @return true if at desired angle
     */
    public boolean atAngle(double angle) {
        return (getAngle() < angle + 5 && getAngle() > angle - 5);
    }
    /**
     * Resets navX
     */
    public void reset() {
        navX.reset();
    }
}
