package maxTodoListe.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import maxTodoListe.Security.Role;

@Entity
@Table(name="users")
public class UsersProfil {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roleList;

	public UsersProfil(@NotNull String username, @NotNull String password) {
		this.username = username;
		this.password = password;
	}

	public UsersProfil(@NotNull String username, @NotNull String password, List<maxTodoListe.Security.Role> roleList) {
		this.username = username;
		this.password = password;
		this.roleList = roleList;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}
       

}
