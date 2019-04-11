package maxTodoListe.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="maliste")
public class ProjetListe implements Serializable {

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
	private Date dateListe;
	
	
	// constructeur vide
	public ProjetListe() {
		
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
	
	public Date getDateListe() {
		return dateListe;
	}

	public void setDateListe(Date dateListe) {
		this.dateListe = dateListe;
	}
		

}
