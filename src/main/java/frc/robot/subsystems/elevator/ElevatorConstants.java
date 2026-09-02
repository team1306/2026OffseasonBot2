package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import badgerutils.motor.MotorConfigUtils;

public class ElevatorConstants {
    public static double inchesPerRotation = 1;

    public static int rightMotorID = 1;
    public static int leftMotorID = 2;

    public static TalonFXConfiguration config = new TalonFXConfiguration()
        .withMotorOutput(MotorConfigUtils.createMotorOutputConfig
            (InvertedValue.Clockwise_Positive,
            NeutralModeValue.Brake))
            .withCurrentLimits(MotorConfigUtils.createCurrentLimitsConfig(
                Amps.of(60), 
                Amps.of(80)));

        public static TalonFXConfiguration invertedConfig = MotorConfigUtils.createInvertedConfig(config);
}
