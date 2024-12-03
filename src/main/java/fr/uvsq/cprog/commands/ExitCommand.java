package fr.uvsq.cprog.commands;

import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class ExitCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;

    public ExitCommand (FileManager fileManager, CommandUsedParam commandUsedParam) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
    }

    @Override
    public void execute() {
        boolean isToCut = commandUsedParam.getIsToCut();
        if (isToCut) {
            fileManager.deleteFileOrDirectory(commandUsedParam
                    .getfileToCopy());
        }
        System.out.println("Au revoir!");
    }
    @Override
    public void log() {
            System.out.println("exit");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}