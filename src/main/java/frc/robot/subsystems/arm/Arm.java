package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.elevator.ElevatorIO;
import frc.robot.subsystems.elevator.ElevatorIOInputsAutoLogged;

public class Arm implements Subsystem {
    public final ArmIOInputAutoLogged inputs = new ArmIOInputAutoLogged();
    private ArmIO ArmIO;

    public Arm(ArmIO ArmIO) {
        this.ArmIO = ArmIO;
    }

    @Override
    public void periodic(){
        ArmIO.updateInputs(inputs); 
        Logger.processInputs("Arm", inputs);
    }


    public Command roatateToPostionCommad(Angle postion){
        return new InstantCommand(() -> ArmIO.rotateToPostion(postion));
    }
}
