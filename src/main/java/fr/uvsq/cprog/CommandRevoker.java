package fr.uvsq.cprog;

import fr.uvsq.cprog.commands.CopyCommand;
import fr.uvsq.cprog.commands.CutCommand;
import fr.uvsq.cprog.commands.HelpCommand;
import fr.uvsq.cprog.commands.DeleteCommand;
import fr.uvsq.cprog.commands.FindCommand;
import fr.uvsq.cprog.commands.MkdirCommand;
import fr.uvsq.cprog.commands.PasteCommand;
import fr.uvsq.cprog.commands.TouchCommand;
import fr.uvsq.cprog.commands.VisuCommand;
import fr.uvsq.cprog.commands.GetBackCommand;
import fr.uvsq.cprog.commands.GetToCommand;
import fr.uvsq.cprog.commands.AddAnnotationCommand;
import fr.uvsq.cprog.commands.DeleteAnnotationCommand;
import fr.uvsq.cprog.commands.NoteCommand;
import fr.uvsq.cprog.commands.ExitCommand;
import fr.uvsq.cprog.commands.iCommand;

import java.util.Stack;

public class CommandRevoker {
    iCommand command;

    private Stack<iCommand> commandHistory = new Stack<>();

    private FileManager fileManager;
    private AnnotationManager annotationManager;
    private CommandUsedParam commandUsedParam;

    public CommandRevoker(FileManager fileManager, CommandUsedParam commandUsedParam, AnnotationManager annotationManager) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
        this.annotationManager = annotationManager;
    }

    public void setCommand(String commandName) {
        iCommand newCommand = null;
        switch (commandName) {
            case "copy":
                newCommand = new CopyCommand(fileManager, commandUsedParam);
                break;
            case "cut":
                newCommand = new CutCommand(fileManager, commandUsedParam);
                break;
            case "delete":
                newCommand = new DeleteCommand(fileManager, commandUsedParam, annotationManager);
                break;
            case "find":
                newCommand = new FindCommand(fileManager, commandUsedParam);
                break;
            case "help":
                newCommand = new HelpCommand();
                break;
            case "mkdir":
                newCommand = new MkdirCommand(fileManager, commandUsedParam);
                break;
            case "paste":
                newCommand = new PasteCommand(fileManager, commandUsedParam);
                break;
            case "touch":
                newCommand = new TouchCommand(fileManager,commandUsedParam);
                break;
            case "visu":
                newCommand = new VisuCommand(fileManager, commandUsedParam);
                break;
            case "..":
                newCommand = new GetBackCommand(fileManager);
                break;
            case ".":
                newCommand = new GetToCommand(fileManager, commandUsedParam);
                break;
            case "+":
                newCommand = new AddAnnotationCommand(fileManager, commandUsedParam, annotationManager);
                break;
            case "-":
                newCommand = new DeleteAnnotationCommand(fileManager, commandUsedParam, annotationManager);
                break;
            case "note":
                newCommand = new NoteCommand(fileManager, commandUsedParam);
                break;
            case "exit":
                newCommand = new ExitCommand(fileManager, commandUsedParam);
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
        this.command = newCommand;
    }
    

    

    public void execute() {
        command.execute();
        commandHistory.push(command);
    }

    public void log() {
        for (iCommand command : commandHistory) {
            command.log();
        }
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            iCommand lastCommand = commandHistory.pop();
            lastCommand.undo();
        }else {
            System.out.println("No command to undo.");
        }
    }
}
