package fr.uvsq.cprog.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.uvsq.cprog.AnnotationManager;
import fr.uvsq.cprog.CommandUsedParam;
import fr.uvsq.cprog.FileManager;

public class PasteCommand implements iCommand {
    private FileManager fileManager;
    private CommandUsedParam commandUsedParam;
    String type;
    String fileName;
    Path targetPath;
    Path CopiedPath;
    String annotaString;

    public PasteCommand(FileManager fileManager, CommandUsedParam commandUsedParam) {
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
            PasteCommand.class);

    @Override
    public void execute() {
        if (commandUsedParam.getfileToCopy() != null) {
                if (type == null){
                        this.type = commandUsedParam.getType();
                        if(fileName == null){
                                this.fileName = commandUsedParam.getfileToCopy()
                    .getFileName().toString();
                        }
                        
                }
                
            

            // Séparer le nom du fichier de son extension
            int dotIndex = fileName.lastIndexOf('.');
            String baseName = (dotIndex == -1)
                    ? fileName
                    : fileName.substring(0, dotIndex);
            String extension = (dotIndex == -1)
                    ? ""
                    : fileName.substring(dotIndex);

            // Construire le nouveau nom de fichier avec "-copy"
            System.out.println(extension);
            String newFileName = type.equals("cut")
                    ? baseName + extension
                    : baseName + "-copy" + extension;
                if (this.targetPath == null){
                        this.targetPath = fileManager.getCurrentDirectory()
                    .resolve(newFileName);
                }
            
            try {
                if(CopiedPath == null){
                        this.CopiedPath = commandUsedParam.getfileToCopy();
                }
                if (Files.isDirectory(CopiedPath)) {
                    FileUtils.copyDirectory(
                            CopiedPath
                                    .toFile(),
                            targetPath.toFile());
                    System.out.println("Répertoire "
                            + fileName
                            + " et son contenu collés avec succès.");

                   // AnnotationManager.addAnnotationToDirectory(
                        //   targetPath, Collections.singletonList(
                           //     CopiedPath.toString()),
                         //  fileManager.getannotationToCopy());
                } else {
                    Files.copy(CopiedPath, this.targetPath);
                    System.out.println(CopiedPath
                            + "\\" + targetPath);
                    AnnotationManager.annotationAdd(targetPath
                            .toString(),
                            commandUsedParam
                                    .getannotationToCopy().get(0));
                    System.out.println("Fichier copié en : "
                            + newFileName);

                }
                if (type.equals("cut")
                        && !targetPath.equals(CopiedPath)) {
                    fileManager.deleteFileOrDirectory(CopiedPath);
                    System.out.println(fileName
                            + " a été supprimé.");
                }

            } catch (IOException e) {
                LOGGER.error("Erreur lors de la copie du fichier.");
            }
        } else {
            System.out.println("Aucun fichier sélectionné pour "
                    + "la copie ou le fichier n'existe pas.");
        }

        commandUsedParam.setIsToCut(false);
        commandUsedParam.setType("");
    }
            @Override
        public void log() {
                System.out.println("pasted :"+CopiedPath+" -> "+targetPath);
        }

        @Override
        public void undo() {
                
                Path temp = targetPath;
                targetPath = CopiedPath;
                CopiedPath = temp;
                execute();
                fileManager.deleteFileOrDirectory(temp);
        
                
        System.out.println("undo paste");
        }

}
