package fr.harmonicate.essence;

public class Plein {
	

	private String nomPersonne;
	private String datePlein;
	private String prixLitre;
	
	public Plein() {
		super();
		nomPersonne="";
		datePlein="";
		prixLitre="";
	}
	public Plein(String nomPersonne, String datePlein, String prixLitre) {
		super();
		this.nomPersonne = nomPersonne;
		this.datePlein = datePlein;
		this.prixLitre = prixLitre;
	}

	public String getNomPersonne() {
		return nomPersonne;
	}
	
	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}
	
	public String getDatePlein() {
		return datePlein;
	}
	
	public void setDatePlein(String datePlein) {
		this.datePlein = datePlein;
	}
	
	public String getPrixLitre() {
		return prixLitre;
	}
	
	public void setPrixLitre(String prixLitre) {
		this.prixLitre = prixLitre;
	}
	
	
	

}
