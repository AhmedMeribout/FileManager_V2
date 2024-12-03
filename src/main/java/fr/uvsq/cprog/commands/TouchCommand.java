package fr.uvsq.cprog.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class TouchCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;

    public TouchCommand(FileManager fileManager, CommandUsedParam commandUsedParam) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
    }

    /**
     * Instance statique du logger (journal d'événements) associée à la classe
     * Commande.
     * Utilisée pour enregistrer des messages de journalisation facilitant
     * le suivi
     * et le débogage
     * des opérations effectuées par la classe Commande.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(
            TouchCommand.class);

    @Override
    public void execute() {
        String fileName = commandUsedParam.getDirName();

        if (!fileName.isEmpty()) {
                Path newFilePath = fileManager.getCurrentDirectory()
                .resolve(fileName + ".txt");
                try {
                        if (!Files.exists(newFilePath)) {
                                Files.createFile(newFilePath);
                                System.out.println("Fichier créé : "
                                 + fileName + ".txt");
                        } else {
                                System.out.println("Le fichier existe "
                                + "déjà.");
                        }
                } catch (IOException e) {
                        LOGGER.error("Erreur lors de la création du "
                        + "fichier");
                }
        } else {
                System.out.println("Nom fichier manquant");
        }
    }
    @Override
    public void log() {
            System.out.println("touch");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}