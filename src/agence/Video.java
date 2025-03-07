package agence;

public class Video extends Document {
	private String format;
    int duree;
    private String realisateur;
    private String acteurPrincipal;

    public Video(String epoqueCode, String titre, int anneePublication, double valeur, String format, int duree, String realisateur, String acteurPrincipal) {
        super(epoqueCode, titre, anneePublication, valeur);
        this.format = format;
        this.duree = duree;
        this.realisateur = realisateur;
        this.acteurPrincipal = acteurPrincipal;
    }
    

    public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public String getRealisateur() {
		return realisateur;
	}


	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}


	public String getActeurPrincipal() {
		return acteurPrincipal;
	}


	public void setActeurPrincipal(String acteurPrincipal) {
		this.acteurPrincipal = acteurPrincipal;
	}


	@Override
    public String getDetails() {
        return "Vidéo : " + getTitre() + ", Format : " + format + ", Durée : " + duree + " min, Réalisateur : " + realisateur +
                ", Acteur principal : " + acteurPrincipal;
    }
}
