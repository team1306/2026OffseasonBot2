package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Arm extends SubsystemBase {
    public final ArmIOInputAutoLogged inputs = new ArmIOInputAutoLogged();
    private ArmIO armIO;

    public Arm(ArmIO ArmIO) {
        this.armIO = ArmIO;
    }

    @Override
    public void periodic(){
        armIO.updateInputs(inputs); 
        Logger.processInputs("Arm", inputs);
    }

    public void rotateToPostionVoid(Angle postion){
        Logger.recordOutput("Arm/arm postion", postion);
        armIO.rotateToPostion(postion);
    }

    public Command roatateToPostionCommad(Angle postion){
        return new InstantCommand(() -> rotateToPostionVoid(postion));
    }
}
