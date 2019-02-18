package maxTodoListe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import maxTodoListe.Liste.DragDrop;

@Repository
public interface DragDropRepository extends CrudRepository<DragDrop, Integer> {
	
	@Query(value="SELECT * FROM bd_maxliste.drag_drop",
			nativeQuery=true)
	public List<DragDrop> findByLastPosition();
	 	

}
