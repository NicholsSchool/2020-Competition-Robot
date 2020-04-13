# Controllers 

To program a controller for the drivers to use to drive the robot, WPI has [this documentation](https://docs.wpilib.org/en/latest/docs/software/commandbased/binding-commands-to-triggers.html) explaining that. What this article is explaining is some code our team has written to make things slightly easier and efficent. We have created two classes `JoystickController` and `XboxController` which can be used to programs joysticks or xbox controllers, respectively. These classes extend WPI's `Joystick` class and therefore have all the same methods and functionality. What makes our classes easier to use, is that they instantiate all of a controller's buttons when that controller is instantiated. Each button exists as a public instance variable within each controller object and can be used as a normal button object. The `XboxController`class also has specific methods for getting values Xbox Controller's sticks and it also makes a controller's Triggers and POV input (The DPAD) into buttons.

 Both classes can be reused year after year, just copy the files into a Robot Project. They are recommended to be placed in the "util" package.

Here is an example of code without our classes and then code with our classes.

**RobotContainer (Without our classes):** 

	public static Joystick xboxController0, xboxController1;
	public JoystickButton c0A, c0B, c0X, c0Y, c0lBumper, ...;
	public JoystickButton c1A, c1B, c1X, c1Y, c1lBumper, ...;
	
	private void configureButtonBindings() {
		xboxController0 = new Joystick(0);
		xboxController1 = new Joystick(1);
		
	 	c0A = new JoystickButton(xboxController0, RobotMap.XBOX_A_VAL);
	 	c0B = new JoystickButton(xboxController0, RobotMap.XBOX_B_VAL);
	 	c0X = new JoystickButton(xboxController0, RobotMap.XBOX_X_VAL);
	 	c0Y = new JoystickButton(xboxController0, RobotMap.XBOX_Y_VAL);
	 	c0lBumper = new JoystickButton(xboxController0, RobotMap.XBOX_LEFT_BUMPER_VAL);
	 	...
	 
	 	c1A = new JoystickButton(xboxController1, RobotMap.XBOX_A_VAL);
	 	c1B = new JoystickButton(xboxController1, RobotMap.XBOX_B_VAL);
	 	c1X = new JoystickButton(xboxController1, RobotMap.XBOX_X_VAL);
	 	c1Y = new JoystickButton(xboxController1, RobotMap.XBOX_Y_VAL);
	 	c1lBumper = new JoystickButton(xboxController1, RobotMap.XBOX_LEFT_BUMPER_VAL);
	 	...
	 	
	 	c0A.whenPresed(new Shoot());
	 	c0B.whenPressed(new Spin());
	 	...
	}	
	
**RobotContainer (With our classes):** 

	public static XboxController xboxController0, xboxController1;
	
	private void configureButtonBindings() {
		xboxController0 = new XboxController(0);
		xboxController1 = new XboxController(1);
		
		xboxController0.a.whenPresed(new Shoot());
	 	xboxController0.b.whenPressed(new Spin());
	 	...
	
	}

