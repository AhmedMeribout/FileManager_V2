package fr.uvsq.cprog.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class MkdirCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;

    private Path temp;

    public MkdirCommand(FileManager fileManager, CommandUsedParam commandUsedParam) {
        this.fileManager = fileManager;
        this.commandUsedParam= commandUsedParam;
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
            MkdirCommand.class);

    @Override
    public void execute() {
        String dirName = commandUsedParam.getDirName();

        if (!dirName.isEmpty()) {
            Path newDirPath = fileManager
                .getCurrentDirectory().resolve(dirName);
            try {
                if (!Files.exists(newDirPath)) {
                    this.temp = Files.createDirectory(newDirPath);
                    System.out.println("Répertoire créé : " + dirName);
                } else {
                    System.out.println("Le répertoire existe déjà.");

                }
            } catch (IOException e) {
                LOGGER.error("Erreur lors de la création "
                        + "du répertoire");
            }
        } else {
            System.out.println("Nom fichier manquant");
        }
        
    }

    public void log(){
        System.out.println("Repository created at " + temp.toString());
    }

    public void undo() {
        try {
            Files.delete(temp);
            System.out.println("Répertoire supprimé");
        } catch (IOException e) {
            LOGGER.error("Erreur lors de la suppression "
                    + "du répertoire");
        }
    }
}