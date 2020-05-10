package module_personne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Beneficiaire extends Personne {
	
	private String Prenom;
	private String Naissance;

	public Beneficiaire(int I, String N, String A, int T, String P, String D) {
		super(I, N, A, T);
		Prenom = P;
		Naissance = D;
	}

	public List<String> ConvertToList()
	{
		
		List<String> beneficiaire = new ArrayList<>();
		beneficiaire.add(String.valueOf(this.getId()));
		beneficiaire.add(this.getNom());
		beneficiaire.add(this.getAdresse());
		beneficiaire.add(String.valueOf(this.getTel()));
		beneficiaire.add(this.Prenom);
		beneficiaire.add(this.Naissance);
		return beneficiaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Naissance == null) ? 0 : Naissance.hashCode());
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
		Beneficiaire other = (Beneficiaire) obj;
		if (Naissance == null) {
			if (other.Naissance != null)
				return false;
		} else if (!Naissance.equals(other.Naissance))
			return false;
		if (Prenom == null) {
			if (other.Prenom != null)
				return false;
		} else if (!Prenom.equals(other.Prenom))
			return false;
		return true;
	}

	public String getPrenom() {
		return Prenom;
	}

	public String getNaissance() {
		return Naissance;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public void setNaissance(String naissance) {
		Naissance = naissance;
	}

	@Override
	public String toString() {
		return "Beneficiaire " + super.toString() + " , " + Prenom + " , " + Naissance;
	}
}