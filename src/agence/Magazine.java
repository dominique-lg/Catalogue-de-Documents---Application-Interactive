package agence;

public class Magazine extends Document{
	private int numero;
    private String theme;

    public Magazine(String epoqueCode, String titre, int anneePublication, double valeur, int nbPages, double largeur, double hauteur, int numero, String theme) {
        super(epoqueCode, titre, anneePublication, valeur);
        this.numero = numero;
        this.theme = theme;
    }

    @Override
    public String getDetails() {
        return "Magazine : " + getTitre() + ", Numéro : " + numero + ", Thème : " + theme;
    }
}
