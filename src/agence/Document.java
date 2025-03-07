package agence;

public class Document {
 protected String epoqueCode;
 protected String titre;
 protected double valeur;
 protected int anneePublication;
 
 
 public Document() {
	super();
}

public Document(String epoqueCode, String titre, int anneePublication, double valeur) {
     this.epoqueCode = epoqueCode;
     this.titre = titre;
     this.anneePublication = anneePublication;
     this.valeur = valeur;
 }

 public String getEpoqueCode() {
     return epoqueCode;
 }

 public String getTitre() {
     return titre;
 }

 public int getAnneePublication() {
     return anneePublication;
 }

 public double getValeur() {
     return valeur;
 }

 public abstract String getDetails();
}
