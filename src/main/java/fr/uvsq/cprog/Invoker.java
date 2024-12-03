package fr.uvsq.cprog;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cette classe est le gestionnaire principal des commandes utilisateur
 * dans un environnement de ligne de commande. Elle interagit avec
 * les classes FileManager, Commande, et AnnotationManager pour
 * traiter les commandes entrées par l'utilisateur.
 */
public class Invoker {
    /**
     * Gestionnaire des commandes utilisateur.
     * Ce gestionnaire prend en charge la manipulation des commandes entrées par
     * l'utilisateur en utilisant un FileManager et une instance de Commande.
     */
    private final FileManager fileManager;
    /**
     * Gestionnaire d'annotations utilisé pour la manipulation des annotations
     * associées aux éléments.
     */
    private final CommandUsedParam commandUsedParam;
    /**
     * Gestionnaire d'annotations utilisé pour la manipulation des annotations
     * associées aux éléments.
     */
    CommandRevoker commandRevoker;
    /**
     * Gestionnaire d'annotations utilisé pour la manipulation des annotations
     * associées aux éléments.
     */
    private final AnnotationManager annotationManager;
    /**
     * Scanner utilisé pour la saisie des commandes utilisateur.
     */
    private final Scanner scanner;
    /**
     * Instance statique du logger (journal d'événements) associée à la classe
     * Commande.
     * Utilisée pour enregistrer des messages de journalisation facilitant
     * le suivi
     * et le débogage
     * des opérations effectuées par la classe Commande.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(
            Invoker.class);

    /**
     * Instance de la classe Commande utilisée pour exécuter les commandes
     * utilisateur.
     */
    //private Map<String, iCommand> commandMap = new HashMap<>();

    /**
     * Constructeur de la classe CommandHandler.
     *
     * @param filemanager       Le gestionnaire de fichiers associé.
     * @param command           L'instance de la classe Commande.
     * @param annotationmanager Le gestionnaire d'annotations.
     */
    public Invoker(final FileManager filemanager,
            final CommandUsedParam commandUsedParam,
            final AnnotationManager annotationmanager) {
        this.commandUsedParam = commandUsedParam;
        this.fileManager = filemanager;
        this.annotationManager = annotationmanager;
        this.scanner = new Scanner(System.in);
        this.commandRevoker = new CommandRevoker(fileManager, commandUsedParam, annotationManager);
    }


    /**
     * Gère l'exécution des commandes utilisateur de manière interactive.
     */
    public void handleCommands() {
        try {
            while (true) {
                fileManager.displayPathContentsNER();
                if (commandUsedParam.getLastNER() == -1) {
                    System.out.println("Aucune note pour "
                            + "l'élément courant.");
                } else {
                    System.out.println("Elément courant: "
                            + commandUsedParam.getLastNER());
                }

                if (annotationManager.getLastAnnotation() == "") {
                    System.out.println("Aucune annotation pour "
                            + "l'élément courant.\n");
                } else {
                    System.out.println("Annotation courante: "
                            + annotationManager.getLastAnnotation()
                            + "\n");
                }

                System.out.print("Entrez votre commande : ");
                String inputLine = scanner.nextLine();
                System.out.print("\n");

                if (!processCommand(inputLine)) {
                    break;
                }
                System.out.println("\n----------------------------"
                        + "--------------------------------------------- \n");
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            // Nettoyer ou réinitialiser le scanner si nécessaire
            // Ignore le reste pour éviter les problèmes.
            scanner.nextLine();
        } catch (Exception e) {
            LOGGER.error("Error : " + e.getMessage());
            // Décider de continuer ou non
        }
    }



    

    public boolean processCommand(String inputLine) {

        final String[] commandParts = inputLine.split("\\s+", 3);
        commandUsedParam.setCommandParts(commandParts);
        int ner = -1;
        String commandName = "default"; // Commande par défaut
        String name = "";
        if (commandParts.length >= 1) {
            try {
                ner = Integer.parseInt(commandParts[0]);
                commandUsedParam.setLastNER(ner);
                String eRPath = fileManager.getPathByNER(ner).toString();
                annotationManager.setLastAnnotation(AnnotationManager
                        .findAnnotation(ner, eRPath, ""));
                commandName = commandParts.length > 1
                        ? commandParts[1]
                        : commandName;
                name = commandParts.length > 2
                        && commandParts[2] instanceof String
                                ? (String) commandParts[2]
                                : name;
            } catch (NumberFormatException e) {
                commandName = commandParts[0];
                ner = commandUsedParam.getLastNER();
                name = commandParts.length > 1
                        && commandParts[1] instanceof String
                                ? (String) commandParts[1]
                                : name;
            }
            commandUsedParam.setDirName(name);
        }
        if (commandName.equals("undo")) {
             commandRevoker.undo();
        }else if (commandName.equals("log")) {
            commandRevoker.log();
        }
        else{
            commandRevoker.setCommand(commandName);
            if (commandRevoker.command != null) {
                commandRevoker.execute();
            } else {
                System.out.println("Commande non reconnue.");
            }}
        

        if (commandName.equals("exit")) return false;
        else return true;
    }
}
