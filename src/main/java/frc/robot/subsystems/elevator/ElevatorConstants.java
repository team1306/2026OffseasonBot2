package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import badgerutils.motor.MotorConfigUtils;

public class ElevatorConstants {
    public static double inchesPerRotation = 1;

    public static int rightMotorID = 1;
    public static int leftMotorID = 2;

    static double KP = 0;
    static double KS = 0;
    static double KD = 0;
    static double KG = 0;

    public static TalonFXConfiguration config = new TalonFXConfiguration()
        .withMotorOutput(MotorConfigUtils.createMotorOutputConfig
            (InvertedValue.Clockwise_Positive,
            NeutralModeValue.Brake))
            .withCurrentLimits(MotorConfigUtils.createCurrentLimitsConfig(
                Amps.of(60), 
                Amps.of(80))).
                withSlot0(MotorConfigUtils.createSlotConfig(
                    KP, KD, KS, 0, KG, GravityTypeValue.Elevator_Static, 
                    StaticFeedforwardSignValue.UseClosedLoopSign));

        public static TalonFXConfiguration invertedConfig = MotorConfigUtils.createInvertedConfig(config);
}
