package fr.uvsq.cprog.commands;

import fr.uvsq.cprog.FileManager;
import fr.uvsq.cprog.AnnotationManager;
import fr.uvsq.cprog.CommandUsedParam;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CopyCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;
    private List<String> pathToCopy;
    Path theCopy;

    public CopyCommand(FileManager fileManager, CommandUsedParam commandUsedParam) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
    }

    @Override
    public void execute() {
        int ner = commandUsedParam.getLastNER();
        boolean isToCut = commandUsedParam.getIsToCut();

        if (isToCut) {
            fileManager.deleteFileOrDirectory(commandUsedParam
                    .getfileToCopy());
        }

        List<String> contents = fileManager
                .getDirectoryContents(fileManager
                        .getCurrentDirectory());
        if (ner < 1 || ner > contents.size()) {
            System.out.println("NER invalide.");
            return;
        }
        if (this.theCopy == null) {
                String fileName = contents.get(ner - 1);
                this.theCopy = fileManager.getCurrentDirectory()
                        .resolve(fileName);
        }
        commandUsedParam.setfileToCopy(theCopy);
        this.pathToCopy = new ArrayList<>();
        List<String> annotationsToCopy = new ArrayList<>();
        if (Files.isDirectory(theCopy)) {
            Path original = theCopy;
            AnnotationManager.findAddToCopy(theCopy, original,
                    pathToCopy, annotationsToCopy,
                    commandUsedParam);
            commandUsedParam.setPathToCopy(pathToCopy);
            commandUsedParam.setannotationToCopy(
                    annotationsToCopy);
            System.out.println(pathToCopy
                    + "   " + annotationsToCopy);

        } else {
            commandUsedParam.setPathToCopy(
                    Collections.singletonList(
                            theCopy.toString()));
            commandUsedParam.setannotationToCopy(
                    Collections.singletonList(
                            AnnotationManager.findAnnotation(ner,
                                    commandUsedParam.getfileToCopy()
                                            .toString(),
                                    "")));
        }

        commandUsedParam.setIsToCut(false);
    }

        @Override
        public void log() {
                System.out.println("Copied : " + this.theCopy.toString());
        }

        @Override
        public void undo() {             
                commandUsedParam.resetCopy();
        }
}
