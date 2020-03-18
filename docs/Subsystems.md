# Subsystems

Every Robot can have parts of it seperated into "Subsystems." Each Subsystem is relatively independent of one another, containing its own motors, sensors, and constraints. Each subsystem should contain private instances of its motors and sensors so that it is the only place where they can be manipulated. 

A subsystem's constructor is where all the subsystem's talons and sensors should be instantiated. Each talon should have its `.configFactoryDefault()` method called on itself after instantiation to make sure no preexisting settings are enabled. (Troubleshooting tidbit: For our team, we once spent two hours trying to understand why some talons weren't allowing power to be applied, it was because they previously had a soft limit enabled on them. Moral of the story, **always use `.configFactoryDefault()`**) After that, all talon settings should be configured. Here are some important ones to note: 

* `.setInverted(boolean)` --- Inputting an argument of true reverses the rotation of the motor when positive power is applied. 

* `.configSelectedFeedbackSensor(FeedbackDevice)` --- Configures an encoder to the talon. Generally, for TalonSRXs, the encoders we use can be configured using `FeedbackDevice.QuadEncoder`. For Falcons (TalonFX), use `FeedbackDevice.IntegratedSensor`

* `.setSensorPhase(boolean)` --- Inputting an argument of true reverses direction of incrementation for encoder ticks. This should be used if an encoder's value is going down when a motor is spinning in what we want to be the forward direction

* `.set(ControlMode.Follower, int TalonID)` -- Using this will make the talon it is called on to be the follower of the talon's whose ID is inputted. A follower will move at whatever speed the master talon moves at, which is useful and important for when two or more motors are controlling a mechanism. For VictorSPX, use `.follow(int TalonID)`


All Subsystems are required to extend WPILib's SubsystemBase class, and therefore is able to override the `periodic()` method which runs every loop of the robot code. Technically, teams can choose to use this method for any purpose, but our team uses this method to _display the subsystem's information to the Smart Dashboard_. Certain Subsystems should have "default commands" which run whenever the robot is enabled, such as the DriveTrain subsystem and the Drive Command. Setting a subsystem's Default Command should be done in RobotContainer

For our team, any Subsystems with talons within them should have a `.stop()` method which calls `.stopMotor()` on all talons. 


