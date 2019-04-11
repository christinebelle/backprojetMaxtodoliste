package maxTodoListe.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="dragDrop")
public class TacheListe implements Serializable {
	
	/**
	 * serialVersionUID = 1L
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String position;
	private String tache;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="idListe")
	private ProjetListe projetListe;
	
	
	public TacheListe() {
	
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getTache() {
		return tache;
	}


	public void setTache(String tache) {
		this.tache = tache;
	}


	public void setMaxListe(ProjetListe projetListe) {
		this.projetListe = projetListe;
	}


	public ProjetListe getMaxListe() {
		return projetListe;
	}

	
		
}
