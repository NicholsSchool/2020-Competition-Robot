# RobotContainer

As of 2020, FRC changed the way we structure our code by adding RobotContainer. The purpose of RobotContainer is to be the sole place in our code where subsystems, sensors, and controllers are initialized and where button mappings to commands are done. In year's past, these tasks were split between the Robot class and the OI class (Operator Interface). Now, changes to the Robot Class are unneccesary and the OI class no longer exists. 

Subsystems and sensors should be instantiated within RobotConatainer's constructor and the Constructor should call the private method `configureButtonBindings()` which instantiates all controllers and binds commands to their buttons.

This class also contains a method, `getAutonomousCommand()` which returns the command to run during the autonomous period of gameplay. We can instantiate and return the command within this method itself, but since different matches may require different autopaths, our team has developed a different way to select paths. We use a physical dial on the robot in which each dial position corresponds to a specific auto path for the robot, the article on AutoPathChooser will go more in depth in this. 

## **Example**

***In RobotContainer Class***

	public DriveTrain driveTrain;
	public Arm arm;
	public Camera camera;
	
	public XBoxController c0;
    public RobotContainer() {
    	driveTrain = new DriveTrain();
    	arm = new Arm();
    	
    	camera = new Camera();
    	
    	configureButtonBindings();
    }
    
    private void configureButtonBindings() {
    	c0 = new XBoxController(0);
    	//Set Default Commands
    	driveTrain.setDefaultCommand(new Drive());
    	
    	// Bind Commands
    	c0.y.whenPressed(new ArmUp());
    	c0.a.whenPressed(new ArmDown());
    
    }
    
    public Command getAutonomousCommand() {
        return new DriveForward();
        /*
        		OR!!
        	return AutoPathChooser.getPath();
        */
    }