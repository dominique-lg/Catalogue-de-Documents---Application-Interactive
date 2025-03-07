package agence;

public class Audio extends Document{
	private String format;
    public int duree;
    private String auteur;
    private String interpretePrincipal;

    public Audio(String epoqueCode, String titre, int anneePublication, double valeur, String format, int duree, String auteur, String interpretePrincipal) {
        super(epoqueCode, titre, anneePublication, valeur);
        this.format = format;
        this.duree = duree;
        this.auteur = auteur;
        this.interpretePrincipal = interpretePrincipal;
    }

    @Override
    public String getDetails() {
        return "Audio : " + getTitre() + ", Format : " + format + ", Durée : " + duree + " min, Auteur : " + auteur +
                ", Interprète principal : " + interpretePrincipal;
    }
}
