package module_personne;

import java.io.BufferedReader;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Adherent extends Personne
{
	private String Fonction;
	private String Prenom;
	
	public Adherent (int I , String N , String A , int T, String P, String F)
	{
		super(I , N , A , T);
		Prenom = P;
		Fonction = F;
	}
	
	public List<String> ConvertToList()
	{
		
		List<String> adherent = new ArrayList<>();
		adherent.add(String.valueOf(this.getId()));
		adherent.add(this.getNom());
		adherent.add(this.getAdresse());
		adherent.add(String.valueOf(this.getTel()));
		adherent.add(this.Prenom);
		adherent.add(this.Fonction);
		return adherent;
	}
	
	public String getFonction() {
		return Fonction;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setFonction(String fonction) {
		Fonction = fonction;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Fonction == null) ? 0 : Fonction.hashCode());
		result = prime * result + ((Prenom == null) ? 0 : Prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adherent other = (Adherent) obj;
		if (Fonction == null) {
			if (other.Fonction != null)
				return false;
		} else if (!Fonction.equals(other.Fonction))
			return false;
		if (Prenom == null) {
			if (other.Prenom != null)
				return false;
		} else if (!Prenom.equals(other.Prenom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adherent " + super.toString() + " , " + Prenom + " , " + Fonction ;
	}
}