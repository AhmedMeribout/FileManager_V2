package fr.uvsq.cprog.commands;

import fr.uvsq.cprog.AnnotationManager;
import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class DeleteAnnotationCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;
    private AnnotationManager annotationManager;

    public DeleteAnnotationCommand (FileManager fileManager, CommandUsedParam commandUsedParam, AnnotationManager annotationManager) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
        this.annotationManager = annotationManager;
    }

    @Override
    public void execute() {
        int ner = commandUsedParam.getLastNER();
        if (ner > 0) {
            String eRPath = fileManager.getPathByNER(ner)
                    .toString();
            AnnotationManager.deleteAnnotationNER(eRPath);
            annotationManager.setLastAnnotation("");
        } else {
            System.out.println("NER invalide.");
        }
    }
    @Override
    public void log() {
            System.out.println("delete annotation");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}