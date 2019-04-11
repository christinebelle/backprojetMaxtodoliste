package maxTodoListe.Security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author christine
 * structures énumération : permet de définir une liste de valeurs possibles.
 * Liste des roles possible
 */
public enum Role implements GrantedAuthority {
	
	ROLE_READER, ROLE_USER;
	
	@Override
    public String getAuthority() {
        return name();
    }

}
