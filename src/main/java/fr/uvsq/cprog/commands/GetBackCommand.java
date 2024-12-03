package fr.uvsq.cprog.commands;

import java.nio.file.Path;

import fr.uvsq.cprog.FileManager;

public class GetBackCommand implements iCommand {
    private FileManager fileManager;
    private Path oldPath;
    private Path newPath;

    public GetBackCommand (FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute() {
        oldPath = fileManager.getCurrentDirectory();
        fileManager.updateCurrentDir("retour");
        newPath = fileManager.getCurrentDirectory();
    }
    @Override
    public void log() {
        System.out.println("back from "+oldPath+" -> "+newPath);
    }

    @Override
    public void undo() {
            fileManager.updateCurrentDir(oldPath.toString());
            System.out.println("undo");
    }

}