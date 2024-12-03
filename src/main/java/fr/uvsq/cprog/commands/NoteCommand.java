package fr.uvsq.cprog.commands;

import fr.uvsq.cprog.AnnotationManager;
import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class NoteCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;
    String eRPath;

    public NoteCommand (FileManager fileManager, CommandUsedParam commandUsedParam) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
    }

    @Override
    public void execute() {
        int ner = commandUsedParam.getLastNER();
        if (ner > 0) {
            eRPath = fileManager.getPathByNER(ner).toString();
            AnnotationManager.findAnnotation(ner, eRPath, "Stream");
        } else {
            System.out.println("NER invalide.");
        }
    }
    @Override
    public void log() {
            System.out.println("note");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}