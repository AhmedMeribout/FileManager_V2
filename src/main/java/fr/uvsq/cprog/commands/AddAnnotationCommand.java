package fr.uvsq.cprog.commands;

import fr.uvsq.cprog.AnnotationManager;
import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class AddAnnotationCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;
    private AnnotationManager annotationManager;

    public AddAnnotationCommand (FileManager fileManager, CommandUsedParam commandUsedParam, AnnotationManager annotationManager) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
        this.annotationManager = annotationManager;
    }

    @Override
    public void execute() {
        int ner = commandUsedParam.getLastNER();
        String[] commandParts = commandUsedParam.getCommandParts();
        if (ner > 0 && commandParts.length > 2) {
            String annotationText = commandParts[2];
            String eRPath = fileManager.getPathByNER(ner).toString();
            AnnotationManager.annotationAdd(eRPath, annotationText);
            annotationManager.setLastAnnotation(AnnotationManager
                .findAnnotation(ner, eRPath, ""));
        } else {
            System.out.println("Mauvais NER ou anno manquante");
        }
    }

    @Override
    public void log() {
            System.out.println("add annotation");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}