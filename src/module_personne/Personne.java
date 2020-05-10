package module_personne;

import java.io.IOException;
import java.util.List;
import java.io.File;


public abstract class Personne 
{
	private int Id;
	private String Nom;
	private String Adresse;
	private int Tel;
	
	public Personne(int I , String N , String A, int T)
	{
		Id = I;
		Nom = N;
		Adresse = A;
		Tel = T;
	}
	
	public abstract List<String> ConvertToList();
	
	
	

	public int getId() {
		return Id;
	}

	public String getNom() {
		return Nom;
	}

	public String getAdresse() {
		return Adresse;
	}

	public int getTel() {
		return Tel;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public void setTel(int tel) {
		Tel = tel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Adresse == null) ? 0 : Adresse.hashCode());
		result = prime * result + Id;
		result = prime * result + ((Nom == null) ? 0 : Nom.hashCode());
		result = prime * result + Tel;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (Adresse == null) {
			if (other.Adresse != null)
				return false;
		} else if (!Adresse.equals(other.Adresse))
			return false;
		if (Id != other.Id)
			return false;
		if (Nom == null) {
			if (other.Nom != null)
				return false;
		} else if (!Nom.equals(other.Nom))
			return false;
		if (Tel != other.Tel)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  Id + " , " + Nom + " , " + Adresse + " , 0" + Tel;// On rajoute un 0 car Tel le considere comme inutile un 0 au debut d'un integer
	}
}

