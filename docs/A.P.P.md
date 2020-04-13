# The Anti-Programmer Panel (A.P.P.)

Out of fear of us breaking anything on the robot, the electrical team creates an Anti-Programmer Panel, or APP, each year for us to use as a way to insert wired connections to the robot and also to select Autos.

The APP generally has an ethernet port, a USB-B port, an on/off switch, and a dial. The two ports are connected to the rio and can be used for pushing code through wires (Use ethernet for pushing code) or for updating the rio. It can be possible that the APP is not connected properly or was damaged in gameplay, if this is the case, revert to connecting to the rio directly as a last measure. 

The on/off switch and dial are programmable and have been used as a way to select autos. The dial generally has 11 positions (0-10) and therefore we can create 11 different auto paths, each corresponding to a position on the dial. The on/off switch can be used for to indicate any boolean value, such as if the robot is red or blue or if the robot should use a certain sensor or not. Always make sure that the driveteam understands what the switch does, which state of the switch is "on", and what value of the dial corresponds to which auto path. 

The actual code for the sensors in the APP can be reused year after year (This year's code is in the "sensors folder" under the class "APP"), all that has to change is the ID for each sensor in RobotMap. 

To have the APP's dial correspond to an auto path, create an "AutoPathChooser" class, with a static `getPath()` method, which returns a CommandBase object. Inside that method, you can retrieve the APP dial's value, and use a switch statement to return a method which returns the path to run. Then, RobotContainer's `getAutonomousCommand()` can just use AutoPathChooser's static `getPath()` method. Here is an example:

**AutoPathChooser.java:**

    public static CommandBase getPath()
    {
        int dial = RobotContainer.app.getDialPosition();
        switch(dial)
        {
            case 0:
                return getShootPath();
            case 1: 
                return getShootPath2();
            case 2:
                return layupPath();
            case 3:
                return new TimedAutoShoot(BALLS_IN_SYSTEM);
            default:
                return new BBDrive(BB_DRIVE_DISTANCE, BB_DRIVE_SPEED);
        }
    }
    
    ...
    // Example of one of the returns listed above:
    private static CommandBase layupPath()
    {
        return new BBDrive(10*12, 0.75).withTimeout(5).alongWith(new PIDDartMove(270).withTimeout(5))
        .andThen(new TimedAutoShoot(BALLS_IN_SYSTEM).withTimeout(TIMED_AUTO_SHOOT_TIMEOUT));
    }
    
**RobotContainer.java:**

	...
	
    public Command getAutonomousCommand() {
        return AutoPathChooser.getPath();
    }