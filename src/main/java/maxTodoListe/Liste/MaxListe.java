package maxTodoListe.Liste;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="maliste")
public class MaxListe implements Serializable {

	/**
	 * numero de version par default de la classe pour que les ojets generes soit reconnu
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idListe;
	private String nomListe;
	private String decritListe;
	private String evolListe;
	
	// constructeur vide
	public MaxListe() {
		
	}
	
	public Integer getIdListe() {
		return idListe;
	}

	public void setIdListe(Integer idListe) {
		this.idListe = idListe;
	}

	public String getNomListe() {
		return nomListe;
	}

	public void setNomListe(String nomListe) {
		this.nomListe = nomListe;
	}

	public String getDecritListe() {
		return decritListe;
	}

	public void setDecritListe(String decritListe) {
		this.decritListe = decritListe;
	}

	public String getEvolListe() {
		return evolListe;
	}

	public void setEvolListe(String evolListe) {
		this.evolListe = evolListe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((decritListe == null) ? 0 : decritListe.hashCode());
		result = prime * result + ((evolListe == null) ? 0 : evolListe.hashCode());
		result = prime * result + ((idListe == null) ? 0 : idListe.hashCode());
		result = prime * result + ((nomListe == null) ? 0 : nomListe.hashCode());
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
		MaxListe other = (MaxListe) obj;
		if (decritListe == null) {
			if (other.decritListe != null)
				return false;
		} else if (!decritListe.equals(other.decritListe))
			return false;
		if (evolListe == null) {
			if (other.evolListe != null)
				return false;
		} else if (!evolListe.equals(other.evolListe))
			return false;
		if (idListe == null) {
			if (other.idListe != null)
				return false;
		} else if (!idListe.equals(other.idListe))
			return false;
		if (nomListe == null) {
			if (other.nomListe != null)
				return false;
		} else if (!nomListe.equals(other.nomListe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaxListe [idListe=" + idListe + ", nomListe=" + nomListe + ", decritListe=" + decritListe
				+ ", evolListe=" + evolListe + "]";
	}
	
	
	
	
	

}
