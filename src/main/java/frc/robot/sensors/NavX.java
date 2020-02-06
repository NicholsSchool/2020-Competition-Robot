package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class NavX {

    private AHRS navX;

    public NavX(AHRS ahrs) {
        this.navX = ahrs;
    }
    /**
     *  returns the angle to be displayed through smart dashboard.
     * @return
     */
    public double getAngle() {
        return navX.getYaw();

    }
    /**
     * returns if at desired spot.
     * @param angle - current angle
     * @return
     */
    public boolean atAngle(double angle) {
        return (getAngle() < angle + 5 && getAngle() > angle - 5);
    }
    /**
     * resets navX
     */
    public void reset() {
        navX.reset();
    }
}
