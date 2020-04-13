package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is used to instantiate and retrieve values from all buttons, triggers, and pov inputs of 
 * a Xbox Controller
 */
public class XboxController extends Joystick{

    /**
     * Creates a new XboxController instance
     * @param port the id of the port the controller is plugged into
     */
	public XboxController(int port) {
        super(port);
        
        a = new JoystickButton(this, 1);
        b = new JoystickButton(this, 2);
        x = new JoystickButton(this, 3);
        y = new JoystickButton(this, 4);
        lBumper = new JoystickButton(this, 5);
        rBumper = new JoystickButton(this, 6);
        select = new JoystickButton(this, 7);
        start = new JoystickButton(this, 8);
        lStick = new JoystickButton(this, 9);
        rStick= new JoystickButton(this, 10);
        dpadUp = new POVButton(this, 0);
        dpadRight = new POVButton(this, 90);
        dpadDown = new POVButton(this, 180);
        dpadLeft = new POVButton(this, 270);
        lTrigger = new Trigger(this, 2);
        rTrigger = new Trigger(this, 3);
		
    }
    
    public JoystickButton a;
    public JoystickButton b;
    public JoystickButton x;
    public JoystickButton y;
    public JoystickButton lBumper;
    public JoystickButton rBumper;
    public JoystickButton select;
    public JoystickButton start;
    public JoystickButton lStick;
    public JoystickButton rStick;
    public Trigger rTrigger;
    public Trigger lTrigger;

    public POVButton dpadUp, dpadDown, dpadRight, dpadLeft;

    public double getLeftX()
    {
        return getRawAxis(0);
    }

    public double getLeftY()
    {
        return -getRawAxis(1);
    }

    public double getLeftTrigger()
    {
        return getRawAxis(2);
    }

    public double getRightTrigger()
    {
        return getRawAxis(3);
    }

    public double getRightX()
    {
        return getRawAxis(4);
    }

    public double getRightY()
    {
        return -getRawAxis(5);
    }

    /**
     * This class is used to allow the behavior of an XboxController's Trigger to be
     * controlled like any other button
     */
    public class Trigger extends Button {
        private XboxController controller;
        private int axisID;

        /**
         * Creates a Trigger instance
         * 
         * @param controller the controller to link to
         * @param axisID     the axis id of the desired trigger
         */
        public Trigger(XboxController controller, int axisID) {
            this.controller = controller;
            this.axisID = axisID;
        }

        /**
         * returns true if the trigger is pressed down
         * 
         * @return true if the trigger is pressed down
         */
        @Override
        public boolean get() {
            return controller.getRawAxis(axisID) > 0.75;
        }
    }

    /**
     * This class is used to allow the behavior of an XboxController's POV controls
     * (DPAD) to be controlled like any other button
     */
    public class POVButton extends Button {

        private XboxController controller;
        private int povValue;

        /**
         * Creates a new POVButton instance
         * 
         * @param controller the controller to link to
         * @param povValue   the value for the button
         */
        public POVButton(XboxController controller, int povValue) {
            this.controller = controller;
            this.povValue = povValue;
        }

        /**
         * Returns true if the button is pressed
         * 
         * @return true if the button is pressed
         */
        @Override
        public boolean get() {
            return controller.getPOV() == povValue;
        }
    }

}
