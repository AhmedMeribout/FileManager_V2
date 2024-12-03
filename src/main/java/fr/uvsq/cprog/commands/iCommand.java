package fr.uvsq.cprog.commands;

public interface iCommand {
    void execute();
    void undo();
    void log();
}
