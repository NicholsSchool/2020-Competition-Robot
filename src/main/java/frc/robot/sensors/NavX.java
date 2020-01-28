package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class NavX {

    private AHRS navX;

    public NavX(AHRS ahrs) {
        this.navX = ahrs;
    }

    public double getAngle() {
        return navX.getYaw();

    }

    public boolean atAngle(double angle) {
        return (getAngle() < angle + 5 && getAngle() > angle - 5);
    }

    public void reset() {
        navX.reset();
    }
}
