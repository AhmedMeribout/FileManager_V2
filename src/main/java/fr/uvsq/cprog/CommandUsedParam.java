package fr.uvsq.cprog;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommandUsedParam {

    private static CommandUsedParam INSTANCE;
    /**
     * Les parties de la commande saisie.
     */

    public static CommandUsedParam getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandUsedParam();
        }
        return INSTANCE;
    }


    private String[] commandParts;
    /**
     * Nom du repertoire à créer.
     */
    private String dirName;
    /**
     * Type de l'opération en cours (cut, copy) pour gérer les actions de
     * découpe/copie de fichiers.
     */
    private String type = "";
    /**
     * Référence au fichier ou répertoire à copier.
     */
    private Path toCopy;
    /**
     * Liste des chemins associés au fichier ou répertoire en cours
     * de copie.
     */
    private List<String> pathToCopy = new ArrayList<>();
    /**
     * Dernier NER (Numéro d'Élément de Référence) utilisé dans
     * ce gestionnaire de
     * fichiers.
     */
    private int lastNER = -1;
    /**
     * Indique si l'opération de copie doit être
     *                traitée comme une
     *                découpe (cut). 
     */
    private boolean isToCut = false;
    
    /**
     * Définit la liste des chemins à copier dans ce FileManager.
     *
     * @param commandParts La liste des parties de la saisie.
     */
    public final void setCommandParts(final String[] commandParts) {
        this.commandParts = commandParts;
    }

    /**
     * Liste des annotations associées au fichier ou répertoire
     * en cours de copie.
     */
    private List<String> annotationsToCopy = new ArrayList<>();

    /**
     * Obtient la liste des chemins à copier dans ce
     * FileManager.
     *
     * @return La liste des chemins à copier.
     */
    public final String[] getCommandParts() {
        return this.commandParts;
    }

    /**
     * Définit le nom du repertoire à créer
     *
     * @param dirName Le nom du repertoire à créer.
     */
    public final void setDirName(final String dirName) {
        this.dirName = dirName;
    }

    /**
     * Obtient le nom du repertoire à créer
     *
     * @return Le nom du repertoire à créer.
     */
    public final String getDirName() {
        return this.dirName;
    }

    /**
     * Définit le type du fichier ou répertoire à copier dans
     * ce FileManager.
     *
     * @param type Le type du fichier ou répertoire à copier.
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * Obtient le type du fichier ou répertoire à copier
     * dans ce FileManager.
     *
     * @return Le type du fichier ou répertoire à copier.
     */
    public final String getType() {
        return type;
    }

    /**
     * Définit le fichier ou répertoire à copier dans
     * ce FileManager.
     *
     * @param file Le fichier ou répertoire à copier.
     */
    public final void setfileToCopy(final Path file) {
        this.toCopy = file;
    }

    /**
     * Obtient le fichier ou répertoire à copier
     * dans ce FileManager.
     *
     * @return Le fichier ou répertoire à copier.
     */
    public final Path getfileToCopy() {
        return toCopy;
    }

    /**
     * Définit la liste des chemins à copier dans ce FileManager.
     *
     * @param pathtoCopy La liste des chemins à copier.
     */
    public final void setPathToCopy(final List<String> pathtoCopy) {
        this.pathToCopy = pathtoCopy;
    }

    /**
     * Obtient la liste des chemins à copier dans ce
     * FileManager.
     *
     * @return La liste des chemins à copier.
     */
    public final List<String> getPathToCopy() {
        return pathToCopy;
    }

    /**
     * Définit le dernier NER (Numéro d'Élément de Référence)
     * utilisé dans ce
     * FileManager.
     *
     * @param ner Le dernier NER à définir.
     */
    public final void setLastNER(final int ner) {
        this.lastNER = ner;
    }

    /**
     * Obtient le dernier NER (Numéro d'Élément de Référence)
     * utilisé dans ce
     * FileManager.
     *
     * @return Le dernier NER utilisé.
     */
    public final int getLastNER() {
        return lastNER;
    }

    /**
     * Définit le dernier NER (Numéro d'Élément de Référence)
     * utilisé dans ce
     * FileManager.
     *
     * @param ner Le dernier NER à définir.
     */
    public final void setIsToCut(final boolean isToCut) {
        this.isToCut = isToCut;
    }

    /**
     * Obtient le dernier NER (Numéro d'Élément de Référence)
     * utilisé dans ce
     * FileManager.
     *
     * @return Le dernier NER utilisé.
     */
    public final boolean getIsToCut() {
        return this.isToCut;
    }

    /**
     * Définit la liste des annotations à copier dans
     * ce FileManager.
     *
     * @param annotation La liste des annotations à copier.
     */
    public final void setannotationToCopy(final List<String> annotation) {
        this.annotationsToCopy = annotation;
    }

    /**
     * Obtient la liste des annotations à copier
     * dans ce FileManager.
     *
     * @return La liste des annotations à copier.
     */
    public final List<String> getannotationToCopy() {
        return annotationsToCopy;
    }

    public void resetCopy() {
        this.toCopy = null;
        this.annotationsToCopy = new ArrayList<>();
        this.pathToCopy = new ArrayList<>();
        this.type = ""; 
    }

    /**
     * Obtient le chemin relatif d'un chemin complet par
     * rapport à un chemin de
     * base.
     *
     * @param fullPath Le chemin complet.
     * @param basePath Le chemin de base.
     * @return Le chemin relatif par rapport au chemin de base.
     */
    public static String getRelativePath(final String fullPath,
            final String basePath) {
        Path fullPathObj = Paths.get(fullPath);
        Path basePathObj = Paths.get(basePath);

        Path relativePathObj = basePathObj
                .relativize(fullPathObj);

        // Convertir le chemin relatif en une chaîne
        String relativePath = relativePathObj
                .toString();

        return relativePath;
    }
}
