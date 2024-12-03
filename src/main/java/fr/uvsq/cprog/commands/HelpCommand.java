package fr.uvsq.cprog.commands;

import org.fusesource.jansi.Ansi;

public class HelpCommand implements iCommand {
    
    @Override
    public void execute() {
        String[] helpLines = {
        "[<NER>] copy : " + Ansi.ansi().fg(Ansi.Color.BLACK).a(
                        "Copie l'élément désigné par <NER>."
                        + " Si l'élément existe, le nom du nouvel "
                        + "élément sera concaténé avec \"-copy\".")
                        .reset(),
        "[<NER>] cut : " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Coupe l'élément désigné par <NER>.")
                        .reset(),
        "past : " + Ansi.ansi().fg(Ansi.Color.BLACK).a(
                        "Colle l'élément précédemment copié "
                        + "depuis le presse-papiers à l'emplacement "
                        + "spécifié par `<NER>`.")
                        .reset(),
        ".. : " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Remonte d'un cran dans le système "
                        + "de fichiers.").reset(),
        "[<NER>] . : " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Entre dans un répertoire, à condition "
                        + "que <NER> désigne un répertoire.")
                        .reset(),
        "mkdir <nom> : "
                        + Ansi.ansi().fg(Ansi.Color.BLACK)
                                        .a("Crée un répertoire avec"
                                        + " le nom spécifié.").reset(),
        "touch <nom> : " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Crée un fichier avec le nom spécifié.")
                        .reset(),
        "[<NER>] visu : " + Ansi.ansi().fg(Ansi.Color.BLACK).a(
                        "Permet de voir le contenu d'un fichier texte."
                        + " Si le fichier n'est pas de type texte, affiche "
                        + "sa taille.")
                        .reset(),
        "find <nom fichier> : " + Ansi.ansi().fg(Ansi.Color.BLACK).a(
                        "Recherche dans toutes les sous-répertoires "
                        + "du répertoire courant le(s) fichier(s) et les "
                        + "affiche.")
                        .reset(),
        "rename <NER> <nouveau_nom> : " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Renomme l'élément désigné par `<NER>` "
                        + "avec le nouveau nom spécifié.")
                        .reset(),
        "delete <nom fichier>: " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Supprime le fichier spécifié.").reset(),
        "exit : " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Termine l'interaction et clôture le "
                        + "programme en affichant un message de départ.")
                        .reset(),
        "<NER> + <texte> : " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Ajoute ou concatène le texte spécifié "
                        + "à l'élément désigné par `<NER>`.")
                        .reset(),
        "<NER> - : " + Ansi.ansi().fg(Ansi.Color.BLACK)
                        .a("Retire tout le texte associé à l'élément "
                        + "désigné par `<NER>`.")
                        .reset()
        };
        System.out.println("\n");
        for (String line : helpLines) {
                System.out.println(line);
        }
    }
    @Override
    public void log() {
            System.out.println("help");
    }

    @Override
    public void undo() {
            
            System.out.println("undo");
    }

}
