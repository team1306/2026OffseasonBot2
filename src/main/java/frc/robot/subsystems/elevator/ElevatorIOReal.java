package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

import badgerutils.advantagekit.PIDTunable;
import edu.wpi.first.units.measure.Angle;

public class ElevatorIOReal implements ElevatorIO {
    TalonFX rightMotor = new TalonFX(ElevatorConstants.rightMotorID);
    TalonFX leftMotor = new TalonFX(ElevatorConstants.leftMotorID);

    private final PIDTunable pidTunable = new PIDTunable("Elevator", SlotConfigs.from(ElevatorConstants.config.Slot0), leftMotor, rightMotor);

    private final PositionTorqueCurrentFOC positionTorque = new PositionTorqueCurrentFOC(null);

    public ElevatorIOReal() {
        leftMotor.getConfigurator().apply(ElevatorConstants.invertedConfig);
        rightMotor.getConfigurator().apply(ElevatorConstants.config);
    }

    @Override
    public void lift(Angle postion) {
        positionTorque.Position = postion.in(Rotations);
        rightMotor.setControl(positionTorque);
        leftMotor.setControl(positionTorque);
    }

    @Override
    public void motorHoldPostion(){
        rightMotor.setControl(new NeutralOut());
        leftMotor.setControl(new NeutralOut());
    }
}
