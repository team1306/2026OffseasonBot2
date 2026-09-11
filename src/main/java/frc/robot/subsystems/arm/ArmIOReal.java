package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

import badgerutils.advantagekit.PIDTunable;
import badgerutils.advantagekit.talonfx.TalonFXSignals;
import edu.wpi.first.units.AngleUnit;
import edu.wpi.first.units.measure.Angle;
import frc.robot.subsystems.elevator.ElevatorConstants;

public class ArmIOReal implements ArmIO {
    
    private TalonFX motor = new TalonFX(1);

    private final PositionTorqueCurrentFOC positionTorque = new PositionTorqueCurrentFOC(0);

    private final PIDTunable pidTunable = new PIDTunable("Elevator", SlotConfigs.from(ElevatorConstants.config.Slot1), motor);

    private final TalonFXSignals motorSignals;

    public ArmIOReal(){
        motorSignals = new TalonFXSignals(motor);
        motor.getConfigurator().apply(ArmConstants.config);
    }


    @Override
    public void rotateToPostion(Angle postion){
        positionTorque.Position = postion.in(Rotations);
        motor.setControl(positionTorque);
    }
}
