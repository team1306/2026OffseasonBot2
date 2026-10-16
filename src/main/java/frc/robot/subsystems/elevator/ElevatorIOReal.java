package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.MotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.hardware.TalonFX;

import badgerutils.advantagekit.PIDTunable;
import badgerutils.advantagekit.talonfx.TalonFXSignals;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycle;

public class ElevatorIOReal implements ElevatorIO {
    private TalonFX rightMotor = new TalonFX(ElevatorConstants.rightMotorID);
    private TalonFX leftMotor = new TalonFX(ElevatorConstants.leftMotorID);

    public static DigitalInput elevatorLimitSwitch = new DigitalInput(0);

    private final TalonFXSignals leftMotorSignals;
    private final TalonFXSignals rightMotorSignals;

    private final PIDTunable pidTunable = new PIDTunable("Elevator", SlotConfigs.from(ElevatorConstants.config.Slot0), leftMotor, rightMotor);

    // TODO: After talking with Sam L, we're gonna try using motion magic, which gives velocity and acceleration limits.
    /// Swap PositionTorqueCurrentFOC for MotionMagicTorqueCurrentFOC, and add those to the configs.
    private final MotionMagicTorqueCurrentFOC positionTorque = new MotionMagicTorqueCurrentFOC(0);

    public ElevatorIOReal() {
        leftMotorSignals = new TalonFXSignals(leftMotor);
        rightMotorSignals = new TalonFXSignals(rightMotor);
        leftMotor.getConfigurator().apply(ElevatorConstants.invertedConfig);
        rightMotor.getConfigurator().apply(ElevatorConstants.config);
    }

    /// TODO: Add a method that polls the limit switch. It should be called in the perioidic loop of Elevator.java.
    /// When the elevator reaches the limit switch, you can manually set the motor encoder values to the min height in rotations (make a constant for this)
    /// Also log the value of the limit switch

    @Override
    public void updateInputs(ElevatorIOInputs inputs){
        inputs.leftMotor = leftMotorSignals.createLoggedTalonFX();
        inputs.rightMotor = rightMotorSignals.createLoggedTalonFX();
    }

    @Override
    public void moveToPosition(Angle postion) {
        positionTorque.Position = postion.in(Rotations);
        rightMotor.setControl(positionTorque);
        leftMotor.setControl(positionTorque);
    }


    @Override
    public void limitSwitchHit(){
        setMotorPostion(ElevatorPostion.down.getHeight().in(Inches));
        rightMotor.setControl(new NeutralOut());
        leftMotor.setControl(new NeutralOut());
    }

    @Override
    public void setMotorPostion(Double inches){
        rightMotor.setPosition(inches);
        leftMotor.setPosition(inches);
    }

    @Override
    public void homeElevator(){
        if(leftMotor.getStatorCurrent() == Amps.of(30)){

            if(rightMotor.getStatorCurrent() == Amps.of(30)){

                rightMotor.setControl(new NeutralOut());
                leftMotor.setControl(new NeutralOut());
                return;
            }
        }

        rightMotor.setControl(new DutyCycleOut(0.05));
        leftMotor.setControl(new DutyCycleOut(0.05));
    }

    @Override
    public void setDutyCycle(double speed){
        rightMotor.setControl(new DutyCycleOut(speed));
        leftMotor.setControl(new DutyCycleOut(speed));
    }

    @Override
    public void neutralOut(){
        rightMotor.setControl(new NeutralOut());
        leftMotor.setControl(new NeutralOut());
    }

    @Override
    public StatusSignal<Current> getMotorCurrent(){
        return leftMotor.getStatorCurrent(); 
    }

    @Override
    public void zeroMotors(){
        rightMotor.setPosition(0);
        leftMotor.setPosition(0);
    }
}
