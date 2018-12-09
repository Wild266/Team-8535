/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;
//test for Slack integration

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="DistanceSensors_FacingCrater_ClaimMarker_ParkInCrater", group="Linear Opmode")
//@Disabled
public class DistanceSensors_FacingCrater_ClaimMarker_ParkInCrater extends LinearOpMode {

    //Driving Motors
    private DcMotor lf = null; //"lf" stands for left front and so on.
    private DcMotor rf = null;
    private DcMotor lb = null;
    private DcMotor rb = null;
    private DcMotor elevator = null;

    //Sensors
    private DistanceSensor distanceSensor = null;
    

    //Servos
    private Servo flipper = null;

    @Override
    public void runOpMode() { //throws interrupted exception
        //initialize required driving motors
        lf = hardwareMap.get(DcMotor.class, "lf");
        rf = hardwareMap.get(DcMotor.class, "rf");
        lb = hardwareMap.get(DcMotor.class, "lb");
        rb = hardwareMap.get(DcMotor.class, "rb");
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        double distanceFromObject;

        //initialize required servos
        flipper = hardwareMap.get(Servo.class, "flipper");

        //initialize sensors
        // get a reference to the color sensor.

        // get a reference to the distance sensor that shares the same name.
        distanceSensor = hardwareMap.get(DistanceSensor.class, "SCD");


        //declare final variables here
        //motor variables
        final int FULL_POWER_MOTOR = 1;
        final int STOP_POWER_MOTOR = 0;

        //Sensors
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final double SCALE_FACTOR = 255;
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        boolean object = false;
        //true = block

        //Motor Direction settings
        lf.setDirection(DcMotor.Direction.REVERSE);
        rf.setDirection(DcMotor.Direction.FORWARD);
        lb.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.FORWARD);
        elevator.setDirection(DcMotor.Direction.REVERSE);

        //Servo Direction settings
        flipper.setDirection(Servo.Direction.FORWARD);

        //we wil try to use encoders
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        flipper.setPosition(0.2);
        
        distanceFromObject = distanceSensor.getDistance(DistanceUnit.CM);
            //telemetry.addData("Distance (cm)",
            //        String.format(Locale.US, "%.02f", distanceFromObject));

            telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

  //      while (opModeIsActive()){
            
  //      }
        //Methods
//        DriveForward(0.5, 1794);      //one revolution is 1120 Andymark
        //tetrix and modern robotics is 1440
        //about a 200mm circumference
//        StrafeRight(0.8, 1769);

        //      Elevate(1, 5600); //Descend from lander
        // DriveForward(1, 5000); // move off of hook
        //       ElevateAndContinue(1, -5600); // Lower hook while continuing to next part of code
         // move off of hook
            DriveForwardWithSensor(0.2, 30);

   /*     StrafeLeft(0.5, 1800); // move out of lander zone
        DriveBackwardWithSensor(1, 2600); // Drive towards depot, need to be further from wall
        TurnRight(1, -500); //Align with depot
        DriveBackwardWithSensor(1, 4000); //Drive to depot, need to drive more
        StrafeRight(1, 300);
        FlipMarker(1); // Flip Marker off of robot and onto depot
        WaitForFlipper(2100); // Self explanatory
        DriveForwardWithSensor(1, 10000);*/ // drive into crater
        //     Elevate(1, -5500); // Lower hook so it doesn't get caught on arm and so we don't have to physically raise it


     /*   DriveForward(-1, 1794);
        TurnRight(1, 500);
        DriveForward(-1, 6794);
        Elevate(-1, -5500);
        Sensation(object,SCALE_FACTOR, hsvValues);
        */

        // run until the end of the match (driver presses STOP)
        // Now we're done, yee

    }

    public void DriveForward(double power, int distance)//Drive Forward
    {
        lf.setDirection(DcMotor.Direction.REVERSE);
        rf.setDirection(DcMotor.Direction.FORWARD);
        lb.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.FORWARD);
        //resets encoder values
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sets Target position
        lf.setTargetPosition(distance);
        lb.setTargetPosition(distance);
        rf.setTargetPosition(distance);
        rb.setTargetPosition(distance);

        //sets to runs to position
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //runs
        lf.setPower(power);
        lb.setPower(power);
        rf.setPower(power);
        rb.setPower(power);
        while (lf.isBusy()&&lb.isBusy()&&rf.isBusy()&&rb.isBusy()){                 //RED FLAG CHECK THIS THING!!!!
            //waits for all motors to stop
        }
        DF(0);  //sets power to 0

        //resets mode
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void DriveForwardWithSensor(double power, double goalDistanceInCM)//Drive Forward
    {

        lf.setDirection(DcMotor.Direction.REVERSE);
        rf.setDirection(DcMotor.Direction.FORWARD);
        lb.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.FORWARD);


        double distanceFromObject = distanceSensor.getDistance(DistanceUnit.CM);

        lf.setPower(power);
        lb.setPower(power);
        rf.setPower(power);
        rb.setPower(power);

        //runs
        while  (distanceFromObject > goalDistanceInCM) {
            distanceFromObject = distanceSensor.getDistance(DistanceUnit.CM);
            //telemetry.addData("Distance (cm)",
            //        String.format(Locale.US, "%.02f", distanceSensor.getDistance(DistanceUnit.CM)));

            //telemetry.update();
            if (distanceFromObject <= goalDistanceInCM)
            {
                lf.setPower(0);
                lb.setPower(0);
                rf.setPower(0);
                rb.setPower(0);
                break;
            }

/*            lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //sets Target position
            lf.setTargetPosition(500);
            lb.setTargetPosition(500);
            rf.setTargetPosition(500);
            rb.setTargetPosition(500);

            //sets to runs to position
            lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            lf.setPower(power);
            lb.setPower(power);
            rf.setPower(power);
            rb.setPower(power);

            while (lf.isBusy()&&lb.isBusy()&&rf.isBusy()&&rb.isBusy()){                 //RED FLAG CHECK THIS THING!!!!
                //waits for all motors to stop
            }
*/
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", distanceSensor.getDistance(DistanceUnit.CM)));

            telemetry.update();
        }
        DF(0);
        //resets mode

    }
    public void DriveBackwardWithSensor(double power, double goalDistanceInCM)//Drive Forward
    {
        lf.setDirection(DcMotor.Direction.FORWARD);
        rf.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.FORWARD);
        rb.setDirection(DcMotor.Direction.REVERSE);

        //runs
        lf.setPower(power);
        lb.setPower(power);
        rf.setPower(power);
        rb.setPower(power);

        double distanceFromObject = distanceSensor.getDistance(DistanceUnit.CM);
        //runs
        while  (distanceFromObject < goalDistanceInCM) {
            distanceFromObject = distanceSensor.getDistance(DistanceUnit.CM);

            if (distanceFromObject >= goalDistanceInCM)
            {
                lf.setPower(0);
                lb.setPower(0);
                rf.setPower(0);
                rb.setPower(0);
                break;
            }

        }
        DF(0);
        //resets mode

    }
    public void DF(double power){           //Method with no end, DO NOT USE UNLESS NECESSARY
        lf.setPower(power);
        lb.setPower(power);
        rf.setPower(power);
        rb.setPower(power);
    }
    public void Elevate(double power, int distance){
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setTargetPosition(distance);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevator.setPower(power);
        while (elevator.isBusy()){

        }
        elevator.setPower(0);

    }
    public void ElevateAndContinue(double power, int distance){
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setTargetPosition(distance);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevator.setPower(power);


    }
    public void FlipMarker(double position){

        flipper.setPosition(position);

    }
    public void WaitForFlipper( int millisOfWait){

        sleep(millisOfWait);

    }
    public void TurnRight(double power, int distance)//Drive Forward
    {

        lf.setDirection(DcMotor.Direction.REVERSE);
        rf.setDirection(DcMotor.Direction.FORWARD);
        lb.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.FORWARD);

        //resets encoder values
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sets Target position
        lf.setTargetPosition(distance);
        lb.setTargetPosition(distance);
        rf.setTargetPosition(-distance);
        rb.setTargetPosition(-distance);

        //sets to runs to position
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //runs
        lf.setPower(power);
        lb.setPower(power);
        rf.setPower(-power);
        rb.setPower(-power);
        while (lf.isBusy()&&lb.isBusy()&&rf.isBusy()&&rb.isBusy()){
            //waits for all motors to stop
        }
        DF(0);  //sets power to 0 to stop robot

        //resets mode
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        //resets encoder values
//        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        lf.setPower(-power);
//        lb.setPower(-power);
//        rf.setPower(power);
//        rb.setPower(power);
    }
    public void TurnLeft(double power)//Drive Forward
    {
        lf.setPower(power);
        lb.setPower(power);
        rf.setPower(-power);
        rb.setPower(-power);
    }
    public void StrafeRight(double power, int distance)//Drive Forward
    {

        lf.setDirection(DcMotor.Direction.REVERSE);
        rf.setDirection(DcMotor.Direction.FORWARD);
        lb.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.FORWARD);

        //resets encoder values
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sets Target position
        lf.setTargetPosition(distance);
        lb.setTargetPosition(-distance);
        rf.setTargetPosition(-distance);
        rb.setTargetPosition(distance);

        //sets to runs to position
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //runs
        lf.setPower(power);
        lb.setPower(-power);
        rf.setPower(-power);
        rb.setPower(power);
        while (lf.isBusy()&&lb.isBusy()&&rf.isBusy()&&rb.isBusy()){
            //waits for all motors to stop
        }
        DF(0);  //sets power to 0 to stop robot

        //resets mode
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void StrafeLeft(double power, int distance)//Drive Forward
    {
        lf.setDirection(DcMotor.Direction.REVERSE);
        rf.setDirection(DcMotor.Direction.FORWARD);
        lb.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.FORWARD);

        //resets encoder values
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sets Target position
        lf.setTargetPosition(-distance);
        lb.setTargetPosition(distance);
        rf.setTargetPosition(distance);
        rb.setTargetPosition(-distance);

        //sets to runs to position
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //runs
        lf.setPower(-power);
        lb.setPower(power);
        rf.setPower(power);
        rb.setPower(-power);
        while (lf.isBusy()&&lb.isBusy()&&rf.isBusy()&&rb.isBusy()){
            //waits for all motors to stop
        }
        DF(0);  //sets power to 0 to stop robot

        //resets mode
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
//    public void Alldir(double power, double degrees, int distance){       //Do not worry about this
//        //resets encoder values
//        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //sets Target position
//        lf.setTargetPosition(distance);
//        lb.setTargetPosition(distance);
//        rf.setTargetPosition(distance);
//        rb.setTargetPosition(distance);
//
//        //sets to runs to position
//        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        //runs
//        lf.setPower(power);
//        lb.setPower(power);
//        rf.setPower(power);
//        rb.setPower(power);
//        while (lf.isBusy()&&lb.isBusy()&&rf.isBusy()&&rb.isBusy()){
//            //waits for all motors to stop
//        }
//        DF(0);  //sets power to 0
//
//        //resets mode
//        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//    }
}
