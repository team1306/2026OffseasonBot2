package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

import badgerutils.advantagekit.PIDTunable;
import badgerutils.advantagekit.talonfx.TalonFXSignals;
import edu.wpi.first.units.measure.Angle;

public class ElevatorIOReal implements ElevatorIO {
    private TalonFX rightMotor = new TalonFX(ElevatorConstants.rightMotorID);
    private TalonFX leftMotor = new TalonFX(ElevatorConstants.leftMotorID);

    private final TalonFXSignals leftMotorSignals;
    private final TalonFXSignals rightMotorSignals;

    private final PIDTunable pidTunable = new PIDTunable("Elevator", SlotConfigs.from(ElevatorConstants.config.Slot0), leftMotor, rightMotor);

    private final PositionTorqueCurrentFOC positionTorque = new PositionTorqueCurrentFOC(0);

    public ElevatorIOReal() {
        leftMotorSignals = new TalonFXSignals(leftMotor);
        rightMotorSignals = new TalonFXSignals(rightMotor);
        leftMotor.getConfigurator().apply(ElevatorConstants.invertedConfig);
        rightMotor.getConfigurator().apply(ElevatorConstants.config);
    }

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
    public void holdPostion(){
        positionTorque.Position = 0;
        rightMotor.setControl(new NeutralOut());
        leftMotor.setControl(new NeutralOut());
    }
}
