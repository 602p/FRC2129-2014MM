
package org.swrobotics.y2014.minneminne;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;


public class Team2129MMRobot extends SimpleRobot {
    public RobotDrive robotdrive = new RobotDrive(CONSTANTS.LEFT_DRIVE_PWM, CONSTANTS.RIGHT_DRIVE_PWM);
    public Joystick leftjoystick = new Joystick(CONSTANTS.LEFT_JOY_ID);
    public Joystick rightjoystick = new Joystick(CONSTANTS.RIGHT_JOY_ID);
    public DriverStation driverstation = DriverStation.getInstance();
    public Jaguar rollerJaguar = new Jaguar(CONSTANTS.ROLLER_PWM);
    public Jaguar hingeJaguar = new Jaguar(CONSTANTS.HINGE_PWM);
    public DigitalInput hingeTopLimit = new DigitalInput(CONSTANTS.HINGE_TOP_LIMIT);
    public final int hingeTopLimit_threshold = 300;
    public int hingeTopLimit_current = 0;
    
    public void operatorControl(){
        while (isOperatorControl()&&isEnabled()){
            if (CONSTANTS.ENABLE_DRIVE) {
                this.robotdrive.tankDrive(this.leftjoystick.getY()*this.driverstation.getAnalogIn(CONSTANTS.DSA_DRIVESCALE_ID),
                    this.rightjoystick.getY()*this.driverstation.getAnalogIn(CONSTANTS.DSA_DRIVESCALE_ID));
            }
            if (CONSTANTS.ENABLE_ROLLER){
                if (this.rightjoystick.getRawButton(CONSTANTS.ROLLER_UP_BTN)){
                    this.rollerJaguar.set(this.driverstation.getAnalogIn(CONSTANTS.DSA_ROLLERSPEED_ID));
                }else if (this.rightjoystick.getRawButton(CONSTANTS.ROLLER_DOWN_BTN)){
                    this.rollerJaguar.set(-this.driverstation.getAnalogIn(CONSTANTS.DSA_ROLLERSPEED_ID));
                }else{
                    this.rollerJaguar.set(0);
                }
            }
            if (CONSTANTS.ENABLE_HINGE){
                if (this.leftjoystick.getRawButton(CONSTANTS.HINGE_UP_BTN) & ((this.hingeTopLimit_current>this.hingeTopLimit_threshold || this.driverstation.getDigitalIn(CONSTANTS.DSB_LIMITOVERRIDE_ID)) || !CONSTANTS.ENABLE_LIMIT_SWITCH)){
                    this.hingeJaguar.set(this.driverstation.getAnalogIn(CONSTANTS.DSA_HINGESPEED_ID));
                }else if (this.leftjoystick.getRawButton(CONSTANTS.HINGE_DOWN_BTN)){
                    this.hingeJaguar.set(-this.driverstation.getAnalogIn(CONSTANTS.DSA_HINGESPEED_ID));
                }else{
                    this.hingeJaguar.set(0);
                }
            }
            
            if (!this.hingeTopLimit.get()){
                this.hingeTopLimit_current+=1;
            }else{
                this.hingeTopLimit_current=0;
            }
            
            this.driverstation.setDigitalOut(1, this.hingeTopLimit.get());
        }
    }
}
