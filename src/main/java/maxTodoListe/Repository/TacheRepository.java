package maxTodoListe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import maxTodoListe.Entity.TacheListe;

@Repository
public interface TacheRepository extends CrudRepository<TacheListe, Integer> {
	
	@Query(value="SELECT * FROM bd_maxliste.drag_drop where position = 'à faire'",
			nativeQuery=true)
	public List<TacheListe> findByPositionAFaire();
	
	@Query(value="SELECT * FROM bd_maxliste.drag_drop where position = 'en cour'",
			nativeQuery=true)
	public List<TacheListe> findByPositionEnCour();
	
	@Query(value="SELECT * FROM bd_maxliste.drag_drop where position = 'terminer'",
			nativeQuery=true)
	public List<TacheListe> findByPositionTerminer();
	 	

}
