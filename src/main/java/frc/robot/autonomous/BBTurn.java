package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotContainer;



public class BBTurn extends CommandBase
{
    public double speed;
    public double desiredAngle;
    /**
     * The AngleTurn class turns the robot to a desired angle 
     * using the NavX sensor, using the DriveTrain.
     * 
     * @param agl - the angle the robot will rotate to
     * @param spd 
    **/
    public BBTurn(double agl, double spd)
    {   
        desiredAngle = agl;
       speed = spd;
        
        addRequirements(RobotContainer.driveTrain);
    }
   

    @Override
    public void initialize()
    {
        RobotContainer.navX.reset();
    }

  
    @Override
    public void execute() 
    {
        System.out.println("Turning");
        if(desiredAngle > 0)
            RobotContainer.driveTrain.move(speed,-speed );
        
        else{
            RobotContainer.driveTrain.move(-speed, speed);
        }
           
    }

   

    @Override
    public boolean isFinished()
    {
        double currentAngle = RobotContainer.navX.getAngle();
        return (currentAngle < desiredAngle + 5 && currentAngle > desiredAngle - 5);
    }

    @Override
    public void end(boolean interrupted) {

        RobotContainer.driveTrain.stop();
    }
}