package agence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class Main {

	private static Map<String, Epoque> epoques = new HashMap<>();
    private static List<Document> documents = new ArrayList<>();

    public static void main(String[] args) {
        try {
            chargerEpoques("epoques.txt");
            chargerDocuments("documents.txt");

            String[] epoquesArray = epoques.values().stream().map(Epoque::toString).toArray(String[]::new);
            String choixEpoque = (String) JOptionPane.showInputDialog(null, "Sélectionnez une époque :", "Époques", JOptionPane.QUESTION_MESSAGE, null, epoquesArray, epoquesArray[0]);

            if (choixEpoque != null) {
                String codeEpoque = choixEpoque.split(" - ")[0];
                List<Document> documentsEpoque = new ArrayList<>();
                for (Document doc : documents) {
                    if (doc.getEpoqueCode().equals(codeEpoque)) {
                        documentsEpoque.add(doc);
                    }
                }

                StringBuilder infoEpoque = new StringBuilder();
                int totalLivres = 0, totalMagazines = 0, totalVideos = 0, totalAudios = 0;
                double valeurTotale = 0;
                int totalPages = 0, totalDuree = 0;

                for (Document doc : documentsEpoque) {
                    valeurTotale += doc.getValeur();

                    if (doc instanceof Livre) {
                        totalLivres++;
                        totalPages += ((Livre) doc).nbPages;
                    } else if (doc instanceof Magazine) {
                        totalMagazines++;
                        totalPages += ((Magazine) doc).nbPages;
                    } else if (doc instanceof Video) {
                        totalVideos++;
                        totalDuree += ((Video) doc).duree;
                    } else if (doc instanceof Audio) {
                        totalAudios++;
                        totalDuree += ((Audio) doc).duree;
                    }
                }

                infoEpoque.append("Résumé de l'époque :\n");
                infoEpoque.append("Livres : ").append(totalLivres).append("\n");
                infoEpoque.append("Magazines : ").append(totalMagazines).append("\n");
                infoEpoque.append("Vidéos : ").append(totalVideos).append("\n");
                infoEpoque.append("Audios : ").append(totalAudios).append("\n");
                infoEpoque.append("Valeur totale : ").append(valeurTotale).append(" €\n");
                infoEpoque.append("Pages cumulées : ").append(totalPages).append("\n");
                infoEpoque.append("Durée totale : ").append(totalDuree).append(" min\n");

                JOptionPane.showMessageDialog(null, infoEpoque.toString(), "Résumé", JOptionPane.INFORMATION_MESSAGE);

                String[] documentsArray = documentsEpoque.stream().map(Document::getTitre).toArray(String[]::new);
                String choixDocument = (String) JOptionPane.showInputDialog(null, "Sélectionnez un document :", "Documents",
                        JOptionPane.QUESTION_MESSAGE, null, documentsArray, documentsArray[0]);

                if (choixDocument != null) {
                    for (Document doc : documentsEpoque) {
                        if (doc.getTitre().equals(choixDocument)) {
                            JOptionPane.showMessageDialog(null, doc.getDetails(), "Détails du document", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void chargerEpoques(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    epoques.put(parts[0], new Epoque(parts[0], parts[1]));
                } else {
                    System.err.println("Erreur de format dans epoques.txt : " + line);
                }
            }
        }
    }

    private static void chargerDocuments(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                try {
                    String type = parts[0];
                    String epoqueCode = parts[1];
                    String titre = parts[2];
                    int anneePublication = Integer.parseInt(parts[3]);
                    double valeur = Double.parseDouble(parts[4]);

                    if (!epoques.containsKey(epoqueCode)) {
                        throw new IllegalArgumentException("Époque invalide : " + epoqueCode);
                    }

                    switch (type) {
                        case "L":
                            int nbPages = Integer.parseInt(parts[5]);
                            double largeur = Double.parseDouble(parts[6]);
                            double hauteur = Double.parseDouble(parts[7]);
                            String auteur = parts[8];
                            String collection = parts.length > 9 ? parts[9] : "";
                            documents.add(new Livre(epoqueCode, titre, anneePublication, valeur, nbPages, largeur, hauteur, auteur, collection));
                            break;
                        case "M":
                            int nbPagesMag = Integer.parseInt(parts[5]);
                            double largeurMag = Double.parseDouble(parts[6]);
                            double hauteurMag = Double.parseDouble(parts[7]);
                            int numero = Integer.parseInt(parts[8]);
                            String theme = parts[9];
                            documents.add(new Magazine(epoqueCode, titre, anneePublication, valeur, nbPagesMag, largeurMag, hauteurMag, numero, theme));
                            break;
                        case "V":
                            String formatVideo = parts[5].toUpperCase();
                            int dureeVideo = Integer.parseInt(parts[6]);
                            String realisateur = parts[7];
                            String acteurPrincipal = parts[8];
                            if (!formatVideo.equals("DVD") && !formatVideo.equals("BLU-RAY")) {
                                throw new IllegalArgumentException("Format vidéo invalide : " + formatVideo);
                            }
                            documents.add(new Video(epoqueCode, titre, anneePublication, valeur, formatVideo, dureeVideo, realisateur, acteurPrincipal));
                            break;
                        case "A":
                            String formatAudio = parts[5].toUpperCase();
                            int dureeAudio = Integer.parseInt(parts[6]);
                            String auteurAudio = parts[7];
                            String interpretePrincipal = parts[8];
                            if (!formatAudio.equals("CD") && !formatAudio.equals("MP3")) {
                                throw new IllegalArgumentException("Format audio invalide : " + formatAudio);
                            }
                            documents.add(new Audio(epoqueCode, titre, anneePublication, valeur, formatAudio, dureeAudio, auteurAudio, interpretePrincipal));
                            break;
                        default:
                            throw new IllegalArgumentException("Type de document inconnu : " + type);
                    }
                } catch (Exception e) {
                    System.err.println("Erreur dans documents.txt : " + e.getMessage() + " (ligne : " + line + ")");
                }
            }
        }
    }

}
