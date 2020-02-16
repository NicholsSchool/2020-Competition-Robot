#RobotMap

Every Robot has a variety of talons, sensors, and pneumatic systems. RobotMap is a class used to store all the IDs of each of these objects. Our reasoning behind storing these values this way, rather than just using them directly when instantiating an object is because if a talon or sensor gets removed, changed, or added, you do not need to scour your code for references to that ID, instead you know that it will be located in RobotMap. You only have to change the necessary value once in RobotMap, and the rest of your code will work as expected.

If you look through our code of any year prior to the 2020 season, you will see that we stored and instantiated each object in RobotMap itself, and then referenced the objects from there. As of 2020, we decided against this, as only the Subsystem using an object should have access to it. So in all future code, IDs should be stored in RobotMap, and objects should be stored and instantiated in their respective subsystem. 

## **Examples**

***In DriveTrain Class***

    //BAD PRACTICE
    public DriveTrain() {
    	WPI_TalonSRX lFMaster = new WPI_TalonSRX(12);
    }
    
--
	//Good Practice
    public DriveTrain() {
    	WPI_TalonSRX lFMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MASTER_ID);
    }
***In RobotMap Class***

    public RobotMap { 
    	public static final int LEFT_FRONT_MASTER_ID = 12;
    }
	