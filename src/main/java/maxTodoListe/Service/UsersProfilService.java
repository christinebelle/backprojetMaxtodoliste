package maxTodoListe.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import maxTodoListe.Repository.UsersProfilRepository;
import maxTodoListe.Security.JwtTokenProvider;

@Service
public class UsersProfilService {

	private UsersProfilRepository uProfilRepository;
	private BCryptPasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    
    
    /**
     * Métode qui génère un token en fonction des informations de l'utilisateur
     * @param username
     * @param password
     * @return retourne un token ou une AuthenticationException erreur
     * @throws Exception : "les informations ne sont pas les bonnes"
     */
    public String signin(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, uProfilRepository.findByUsername(username).get().getRoleList());
        } catch (AuthenticationException e) {
            throw new Exception("les informations ne sont pas les bonnes");
        }
    }
    
    
}
