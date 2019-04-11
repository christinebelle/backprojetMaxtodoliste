package maxTodoListe.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import maxTodoListe.Entity.UsersProfil;

public interface UsersProfilRepository extends JpaRepository<UsersProfil, Integer> {
	
	/**
	 * Optional: un conteneur pouvant contenir ou non une valeur non nulle. 
	 * Si une valeur est présente, isPresent() retournera true et get() retournera la valeur.
	 * @param username
	 */
	Optional<UsersProfil> findByUsername(String username);
	
	boolean existsByUsername(String username);

    void deleteByUsername(String username);	

}
