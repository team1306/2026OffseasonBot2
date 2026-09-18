package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class Arm implements Subsystem {
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

    public void holdArmPostion(){
        armIO.holdPostion();
    }

    public void rotateArm(double speed){
        armIO.rotate(speed);
    }


    public Command roatateToPostionCommad(Angle postion){
        return new InstantCommand(() -> armIO.rotateToPostion(postion));
    }

    public Command rotateArmCommand(double speed){
        return new StartEndCommand(() -> rotateArm(speed), () -> holdArmPostion(), this);
    }
}
