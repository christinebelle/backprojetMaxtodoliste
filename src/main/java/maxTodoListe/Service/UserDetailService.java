package maxTodoListe.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import maxTodoListe.Entity.UsersProfil;
import maxTodoListe.Repository.UsersProfilRepository;

/**
 * Class permettant de vérifier si l'utilisateur existe
 * @author christine
 *
 */
@Service
public class UserDetailService {
	
	@Autowired
    private UsersProfilRepository userProRepo;
	
	/**
	 * Fonction qui recherche l'utilisateur dans la BDD et renvoi les données de l'utilisateur
	 * si l'utilisateur n'hexiste pas renvois un message d'erreur
	 * @param username
	 * @return User
	 * @throws UsernameNotFoundException
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

		final Optional<UsersProfil> user = userProRepo.findByUsername(username);
		
		if (!user.isPresent()) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
		
		return User
                .withUsername(username)
                .password(user.get().getPassword())
                .authorities(user.get().getRoleList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();		
	}

}
