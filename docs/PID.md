# PID Control Loops

PID Control loops are used for smooth control and movement of Robot mechanisms PID stands for Porportional, Integral, and Derivative. Our team also adds a Feed Forward term to our loops. 

### Result = ( P * E(t) ) + ( I * ∑E(t) ) + ( D * dE(t)/dt ) + F
![](/Users/Arnav/PROJECTS/2020-Dr-Teeth/docs/PIDExample.png)

As seen in the graph, the desired temperature is 23 degrees, and the mechanism adjusts smoothly to get to that temperature. To get the best possible control loop, the values for PID have to be tested and adjusted thoroughly.

> Error Function  --- When discussing PID, a common term used is the error function. For our purposes, the error function is simple enough as: Error(currentValue) =  desiredValue - currentValue


##F - Feed Forward
Not all PID Loops have a Feed Forward term, but our team has found tuning PID loops to be a lot easier by using F. Feed Forward is power applied to the mechanism regardless of the error [+ F]. CTRE reccomends when [tuning Velocity loops](https://phoenix-documentation.readthedocs.io/en/latest/ch16_ClosedLoop.html#velocity-closed-loop-control-mode) for talons or falcons, is to find the F value which by itself gets the talon close to the target value. What our team has done for mechanisms not relying on the Talon's built in PID loop, is to find a F value which barely makes the mechanism move. This way, the other values won't be fighting friction or gravity. 

## P - Proportional 

The Proportional term multiplies itself with the current error [ P * E(t) ]. This allows the speed of the mechanism to be fast when the error is very large and the speed to be slow when the error is very small. For example, if a drivetrain has to drive 100 inches, and the P value is 0.005, then P * E(t) = 0.5, and the drivetrain will drive at 50% speed initially, and then when the robot cycles again to check the error, it may be a value of 60 inches, so now the drivetrain will drive at 30% speed, and so on.

In many cases, a P term is all that is necessary for a good control loop, but it can have its issues. The robot may overshoot and repeatedly oscilate trying to get to the desired value. Using the previous example, if the drivetrain accidently travels 110 inches, the robot will then apply -5% power and move backwards, making it travel to say 95 inches, and back and forth from there. 

## D - Derivative

The Derivative is the slope at any given point in a function. The D term multiplies itself with the derivative of the error function [D * dE(t)/dt]. Doing so allows for the robot to smoothly deaccelerate to the desired state. Since the control loop should be minimizing the error, the slope of the error function will be negative. Therefore, adding the derivative term to our loop is slowing down the mechanism (since adding a negative is the same as subtracting percent power to be applied). This helps minimize the oscilation that occurs when using just a P loop. For FRC, almost all scenarios can be accounted for by using just a P or PD loop. 


## I - Integral 

The Integral is the area under any given curve. The I term multiplies itself with the integral of the error function [I * ∑E(t)]. If the error is so small that the power applied doesn't move the mechanism at all (known as steady state error), the I term can help because the area accumulated by the error function over time will grow and therefore the I term will add a greater percentage to the result of the control loop. For almost all cases, it will not be necessary to use the I term, especially if you use the Feed Forward term. 


## Tuning
There are lots of guides explaining tuning
