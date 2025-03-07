package agence;

public class Epoque {
 private String code;
 private String libelle;
 public Epoque(String code, String libelle) {
     this.code = code;
     this.libelle = libelle;
 }

 public String getCode() {
     return code;
 }

 public String getLibelle() {
     return libelle;
 }

 @Override
 public String toString() {
     return code + " - " + libelle;
 }
}
