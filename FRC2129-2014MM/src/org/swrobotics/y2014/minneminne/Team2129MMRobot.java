
package org.swrobotics.y2014.minneminne;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;


public class Team2129MMRobot extends SimpleRobot {
    public RobotDrive robotdrive = new RobotDrive(CONSTANTS.LEFT_DRIVE_PWM, CONSTANTS.RIGHT_DRIVE_PWM);
    public Joystick leftjoy = new Joystick(CONSTANTS.LEFT_JOY_ID);
    public Joystick rightjoy = new Joystick(CONSTANTS.RIGHT_JOY_ID);
    public DriverStation driverstation = DriverStation.getInstance();
    
    public void autonomous(){
        
    }
    
    public void operatorControl(){
        while (isOperatorControl()&&isEnabled()){
            if (CONSTANTS.ENABLE_DRIVE) {
                this.robotdrive.tankDrive(this.leftjoy.getX()*this.driverstation.getAnalogIn(CONSTANTS.DSA_DRIVESCALE_ID),
                    this.leftjoy.getY()*this.driverstation.getAnalogIn(CONSTANTS.DSA_DRIVESCALE_ID));
            }
        }
    }
}
