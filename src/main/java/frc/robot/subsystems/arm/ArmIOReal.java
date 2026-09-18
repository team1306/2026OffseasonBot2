package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

import badgerutils.advantagekit.PIDTunable;
import badgerutils.advantagekit.talonfx.TalonFXSignals;
import edu.wpi.first.units.measure.Angle;
import frc.robot.subsystems.elevator.ElevatorConstants;

public class ArmIOReal implements ArmIO {
    
    private TalonFX motor = new TalonFX(ArmConstants.motorID);

    private final PositionTorqueCurrentFOC positionTorque = new PositionTorqueCurrentFOC(0);

    private final PIDTunable pidTunable = new PIDTunable("Elevator", SlotConfigs.from(ElevatorConstants.config.Slot1), motor);

    private final TalonFXSignals motorSignals;

    public ArmIOReal(){
        motorSignals = new TalonFXSignals(motor);
        motor.getConfigurator().apply(ArmConstants.config);
    }


    @Override
    public void updateInputs(ArmIOInput inputs){
        inputs.motor = motorSignals.createLoggedTalonFX();
    }

    @Override
    public void rotateToPostion(Angle postion){
        positionTorque.Position = postion.in(Rotations);
        motor.setControl(positionTorque);
    }

    @Override 
    public void rotate(double speed){
        motor.setControl(new DutyCycleOut(speed));
    }

    public void holdPostion(){
        motor.setControl(new NeutralOut());
    }
}
