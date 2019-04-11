package maxTodoListe.Security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import maxTodoListe.Service.UserDetailService;

/**
 * 
 * @author christine
 * Class JWT en charge de la gestion du token : encodage, création, vérification ...
 */
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
	
	@Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h
	
	@Autowired
	private UserDetailService usDetailService;
	
	
	/**
	 * Méthode qui code notre secret au format base64 pour activer la signature HMAC 
	 * (l'en-tête et les données utiles sont également en base64)
	 */
	@PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
	
	
	/**
	 * Méthode qui crée un token avec le nom d'utilisateur en tant que champ "sub", 
	 * les rôles d'utilisateur en tant que champ "auth", "iat" en tant que date,
     * "exp" comme date actuelle + durée de validité.
	 * @param username
	 * @param roles
	 * @return
	 */
	public String createToken(String username, List<Role> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }
	
	
	/**
	 * Méthode qui récupère le nom d'utilisateur du JWT token
	 * @param token
	 * @return le nom de l'utilisateur en String
	 */
	public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
	
	
	/**
	 * Méthode qui renvoie l'authentification de l'utilisateur basée sur un JWT token.
	 * @param token
	 * @return l'objet "authentification" si le nom d'utilisateur est trouvé.
	 */
	public Authentication getAuthentication(String token) {
        UserDetails userDetails = usDetailService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
	
	
	/**
	 * Méthode qui résout un JWT token à partir d'une requête HTTP.
	 * L'en-tête doit contenir un champ Authorization où "Bearer" doit être ajouté avant le JWT token.
	 * @param request
	 * @return le JWT token provenant du HTTP Header ou return NULL
	 */
	public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
	
	
	/**
	 * Méthode qui vérifie qu'un JWT token est valide.
	 * La signature doit être correcte et le temps de "exp" doit être après "now"
	 * @param token
	 * @return true si le token est valide ou le message d'exception
	 * @throws Exception : "le mot de passe est invalide"
	 */
	public boolean validateToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("le mot de passe est invalide");
        }
    }

}
