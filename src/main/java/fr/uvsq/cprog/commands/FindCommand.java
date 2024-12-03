package fr.uvsq.cprog.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class FindCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;

    public FindCommand(FileManager fileManager, CommandUsedParam commandUsedParam) {
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
            FindCommand.class);

    @Override
    public void execute() {
        String fileName = commandUsedParam.getDirName();

        if (!fileName.isEmpty()) {
            try (Stream<Path> paths = Files.walk(fileManager
                .getCurrentDirectory())) {
                paths.filter(Files::isRegularFile)
                        .filter(path -> path.getFileName()
                                .toString().equals(fileName))
                        .forEach(System.out::println);
            } catch (IOException e) {
                LOGGER.error("Erreur lors de la "
                        + "recherche de fichiers.");
            }
        } else {
            System.out.println("Nom fichier manquant");
        }
    }
    @Override
    public void log() {
            System.out.println("find");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}