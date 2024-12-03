package fr.uvsq.cprog.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class GetToCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;
    private Path oldPath;
    private Path potentialDir;

    public GetToCommand (FileManager fileManager, CommandUsedParam commandUsedParam) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
    }

    @Override
    public void execute() {
        int ner = commandUsedParam.getLastNER();
        List<String> contents = fileManager
        .getDirectoryContents(fileManager
                .getCurrentDirectory());
        if (ner < 1 || ner > contents.size()) {
            System.out.println("NER invalide.");
        } else {
            String fileName = contents.get(ner - 1);
            oldPath = fileManager.getCurrentDirectory();
            potentialDir = oldPath
                    .resolve(fileName);
            if (Files.isDirectory(potentialDir)) {
                fileManager
                        .updateCurrentDir(fileName);
            } else {
                System.out
                        .println("Elément non répertoire");
            }
        }
    }
    @Override
    public void log() {
            System.out.println("get to "+oldPath+" -> "+potentialDir);
    }

    @Override
    public void undo() {
            fileManager.updateCurrentDir(oldPath.toString());
            System.out.println("undo");
    }

}