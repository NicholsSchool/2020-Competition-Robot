# Autonomous

Autonomous is a crucial compenent of FRC games. Generally in FRC, auto does not provide enough points to make it an automatic game winner (unlike FTC), but it still gain provide a major advantage to teams who do this well. The way you make auto commands is pretty much identical how you make normal commands, what differs in how those commands are called. For normal commands, they are generally called by a button press, for auto commands, they are chained together to form command groups which become the auto paths for the robot. Also, auto commands all have to rely on some kind of sensor to know when to end. This article will discuss a variety of types of auto commands (from worst to best) and how to create the paths


## Dead Reckoning (Least Reliable)

Dead Reckoning is when an auto command relies solely on time to know when to end. For example, for driving, you may try having the robot move forward at half speed for 5 seconds to move to a certain point in the field. The issue with this is that the result will always be inconsistent. The mechanism will never be in exact same spot every time because the battery power may be different or certain parts may have gotten worn down. Since the movement is based on time, those differences will cause different results.

This may be a good procedure for a mechanism that doesn't have to the exact same every time or if you need to accomplish a simple task like moving off a line. We have even used dead reckoning for simple robot movements in gameplay, such as in 2019, Dr. Julius Strangepork's placement of hatch panels were quick simple timed movements, where the arm moved down for half a second and retracted for half a second. Idealy, for important auto paths, dead reckoning should only be used when there is no other option.

## Bang Bang 

Bang Bang involves relying on some kind of sensor feedback to know when to stop. For example, if I wanted the robot to drive 10 feet forward, I would first determine what the encoder value equal to 10 feet is, say 10000 ticks, and then just have the robot keep traveling forward until it reaches 10000 ticks. If I wanted it to be exact and not overshoot by a lot, I could write the code so that if the robot goes some arbitrary amount over my desired target, say 500 ticks over, then the robot has to drive backwards. This will result in a robot driving at the speed you set, forward and maybe backwards over and over until it ends up within your allowed range of the target point. As you can see, this can be problematic and result in numerous robot oscilations and time consumption. You can choose to not include the driving backwards portion, this would result in the robot overshooting its target slightly everytime, unless you have it going very slow. 

Bang Bang is good for when its not necessary for the robot to be exact in its movements. In the early years of our team, we used bang bang for the majority of our movements. Before writing the more advanced kinds of auto commands this article will describe, the team should write bang bang commands for each subsystem that uses sensors. 


## PID 

PID control is the ideal way to write auto commands. Using PID allows for the robot to smoothly deaccelerate and arrive at its target correctly and consitently. For more information on PID, read the article that is dedicated to it. Using PID requires a sensor for the subsystem that is moving and it also will require the team to spend time tuning it to be accurate. Even after a thorough tuning process, changes to the robot or broken parts can result in the PID values becoming inaccurate and thus requiring re-tuning. The time it takes for tuning is the main negative for PID, but if done properly, it can result in very precise auto paths. The team should have back up bang bang paths in case the PID begins to fail. 

WPILib provides the `PIDCommand` class which can be extended to easily write a PID Command, its mainly the actual PID values which will have to be tuned. Infomation on the class can be found [here](https://docs.wpilib.org/en/stable/docs/software/commandbased/pid-subsystems-commands.html) and you can use the PID auto commands written for Dr. Teeth as references as well. 


## Trajectory 

Trajectory building is a feature provided by WPILib as of 2020. It is mainly meant for drivetrain movement and has the robot follow paths by using information specific to your robot. The main requirement for this is accurate encoder readings. As of writing this, the team has not had the opportunity to spend time using this feature. **It is highly recommended that the team spend time off-season learning and using this feature.** Information on it can be found [here](https://docs.wpilib.org/en/stable/docs/software/examples-tutorials/trajectory-tutorial/trajectory-tutorial-overview.html). Essentially, you have to use WPI's characterization tool to determine voltage and measurement values specific to your robot and then apply those values to your code so you can integrate the trajectory code. Trajectorys can also be used for arm mechanisms.

## Making Auto Paths

Once you have each individual command for every subsystem movement written, making the actual paths is not that difficult. As of 2020, WPI added lots of convenience features to commands including decorators which make it easy to have a command go right after another or along side another. These decorators are `andThen()` and `alongWith()`. There are many more decorators, but these are probably the ones you will use the most. **You should read the [convenience features documentation](https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html) if you haven't already!** Using these decorators to make paths easily. You should also consider adding `.withTimeout()` to the end of each command that uses sensors as a safety because what can happen is that the sensors fail or give bad values or the robot overshoots and any of these can lead to the command never ending, so a .withTimeout() ensures that it will eventually end. Here is an example:

	Command autoPath = new VisionPIDTurn().withTimeout(2)
					  .alongWith( new PIDDartMove(dartMoveValue).withTimeout(5) )
                      .andThen( new TimedAutoShoot(BALLS_IN_SYSTEM) );
                      
For information on how to run an auto path and how to select it, read the article on the A.P.P. 

Here are some strategies on how to make and improve auto paths. First test each individual auto command by themselves and try to get each of them to be as accurate as possible. Then, plan out the path you want the robot to go on, take measurements, think of the best and easiest path the robot can take to accomplish the task, and if possible, use vision targets or something else to have your robot realign itself during the path. What will often happen is even with the most accurate PID loops, tiny errors build up over time, so if a robot can detect and align itself with a vision target, that will improve accuracy.  After you've done all this, create the auto path. Test it multiple times, if the path doesn't work, look for the following: Is the robot going the distance you inserted? If no, then check the accuracy of the command you are using, if yes, then is the robot going to far or to little? If yes, then change the measurements you inputted. 