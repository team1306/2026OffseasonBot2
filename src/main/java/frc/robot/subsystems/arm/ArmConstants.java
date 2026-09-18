package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import badgerutils.motor.MotorConfigUtils;

public class ArmConstants {

    public static int motorID = 5;

    static final double KP = 0;
    static final double KS = 0;
    static final double KD = 0;
    static final double KG = 0;

    public static TalonFXConfiguration config = new TalonFXConfiguration()
        .withMotorOutput(MotorConfigUtils.createMotorOutputConfig
            (InvertedValue.Clockwise_Positive,
            NeutralModeValue.Brake))
            .withCurrentLimits(MotorConfigUtils.createCurrentLimitsConfig(
                Amps.of(60), 
                Amps.of(80))).
                withSlot0(MotorConfigUtils.createSlotConfig(
                    KP, KD, KS, 0, KG, GravityTypeValue.Arm_Cosine, 
                    StaticFeedforwardSignValue.UseClosedLoopSign));
}
