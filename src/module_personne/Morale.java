package module_personne;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Morale extends Personne {
	
	private String Activite;

	public Morale(int I, String N, String A, int T, String Act) {
		super(I, N, A, T);
		Activite = Act;
	}

	public List<String> ConvertToList()
	{
		
		List<String> morale = new ArrayList<>();
		morale.add(String.valueOf(this.getId()));
		morale.add(this.getNom());
		morale.add(this.getAdresse());
		morale.add(String.valueOf(this.getTel()));
		morale.add(this.Activite);
	
		return morale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Activite == null) ? 0 : Activite.hashCode());
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
		Morale other = (Morale) obj;
		if (Activite == null) {
			if (other.Activite != null)
				return false;
		} else if (!Activite.equals(other.Activite))
			return false;
		return true;
	}

	public String getActivite() {
		return Activite;
	}

	public void setActivite(String activite) {
		Activite = activite;
	}

	@Override
	public String toString() {
		return "Morale " + super.toString() + " , " + Activite;
	}
}