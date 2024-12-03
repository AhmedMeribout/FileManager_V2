package fr.uvsq.cprog.commands;

import java.nio.file.Path;

import fr.uvsq.cprog.AnnotationManager;
import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class DeleteCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;
    private AnnotationManager annotationManager;

    public DeleteCommand(FileManager fileManager, CommandUsedParam commandUsedParam, AnnotationManager annotationManager) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
        this.annotationManager = annotationManager;
    }

    @Override
    public void execute() {
        Path pathRef = fileManager.getPathByNER(commandUsedParam.getLastNER());
        fileManager.deleteFileOrDirectory(pathRef);
        annotationManager.setLastAnnotation("");
        commandUsedParam.setLastNER(-1);
    }
    @Override
    public void log() {
            System.out.println("delete");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}