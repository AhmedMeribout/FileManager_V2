package fr.uvsq.cprog.commands;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class VisuCommand implements iCommand {
    /**
     * Constante pour la taille du cadre.
     */
    private static final int FRAME_SIZE = 400;
    /**
     * Liste pour les types d'images.
     */
    private static final List<String> IMAGE_EXTENSIONS = Arrays
    .asList(".jpg",
    ".jpeg", ".png", ".gif", ".bmp");
    /**
     * Gestionnaire de fichiers utilisé pour la manipulation des opérations
     * liées
     * aux fichiers
     * dans le contexte de la classe où cette variable est déclarée.
     */
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;
    /**
     * Instance statique du logger (journal d'événements) associée à la classe
     * Commande.
     * Utilisée pour enregistrer des messages de journalisation facilitant
     * le suivi
     * et le débogage
     * des opérations effectuées par la classe Commande.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(
            VisuCommand.class);

    public VisuCommand(FileManager fileManager, CommandUsedParam commandUsedParam) {
        this.fileManager = fileManager;
        this.commandUsedParam = commandUsedParam;
    }

    @Override
    public void execute() {
        int ner = commandUsedParam.getLastNER();
        try {
            List<String> contents = fileManager
                    .getDirectoryContents(fileManager
                            .getCurrentDirectory());
            if (ner >= 1 && ner <= contents.size()) {
                String fileName = contents.get(ner - 1);
                Path filePath = fileManager.getCurrentDirectory()
                        .resolve(fileName);

                if (fileManager.isTextFile(filePath)) {
                    Files.lines(filePath, Charset.defaultCharset())
                            .forEach(System.out::println);
                } else {
                    long size = Files.size(filePath);
                    if (Files.isDirectory(filePath)) {
                        System.out.println("Taille du dossier: "
                                + size + " bytes");
                    } else {
                        if (isImageFile(filePath)) {
                                displayImage(filePath);
                        } else {
                                System.out.println("Taille du fichier: "
                                + size + " bytes");
                        }
                    }
                }
            } else {
                System.out.println("NER invalide.");
            }
        } catch (IOException e) {
            LOGGER.error("Erreur lors de la lecture du fichier.");
        }
    }

    /**
     * Vérifie si le fichier spécifié est un fichier image.
     *
     * @param filePath Le chemin du fichier à vérifier.
     * @return true si le fichier est un fichier image, false sinon.
     */
    public static boolean isImageFile(final Path filePath) {
        String fileName = filePath.getFileName().toString().toLowerCase();
        return IMAGE_EXTENSIONS.stream().anyMatch(fileName::endsWith);
    }

    /**
     * Affiche une image à partir du chemin spécifié.
     *
     * @param imagePath Le chemin de l'image à afficher.
     */
    public static void displayImage(final Path imagePath) {
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath.toString());
            JLabel jLabel = new JLabel(imageIcon);

            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());
            // Ajustez la taille du cadre selon vos besoins
            frame.setSize(FRAME_SIZE, FRAME_SIZE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(jLabel);

            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur"
            + " lors de l'affichage de l'image.", "Erreur",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void log() {
            System.out.println("visu");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}