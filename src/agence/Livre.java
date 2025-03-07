package agence;

public class Livre extends Document {
	protected int nbPages;
    private double largeur;
    private double hauteur;
    private String auteur;
    private String collection;

    public Livre(String epoqueCode, String titre, int anneePublication, double valeur, int nbPages, double largeur, double hauteur, String auteur, String collection) {
        super(epoqueCode, titre, anneePublication, valeur);
        this.nbPages = nbPages;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.auteur = auteur;
        this.collection = collection;
    }

    @Override
    public String getDetails() {
        return "Livre : " + getTitre() + ", Auteur : " + auteur + ", Collection : " + (collection.isEmpty() ? "N/A" : collection) +
                ", Pages : " + nbPages + ", Dimensions : " + largeur + "x" + hauteur + " cm";
    }
}
