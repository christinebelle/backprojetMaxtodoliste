package maxTodoListe;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class JDBCsecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource sourceData() {
		final DriverManagerDataSource dataSource1 = new DriverManagerDataSource();
		// recuperer les donnees dans application.properties
		dataSource1.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource1.setUrl(env.getProperty("spring.datasource.url"));
		dataSource1.setUsername(env.getProperty("spring.datasource.username"));
		dataSource1.setPassword(env.getProperty("spring.datasource.password"));
		
		return dataSource1;
		
	}
	
	@Autowired
	public void configAuthentification(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(sourceData());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(
                "/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.json",
                "/**/*.js"
        ).permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/api/liste").permitAll()
		.antMatchers("/kanban").permitAll()
		.antMatchers("/max").hasAnyAuthority("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/max")
		.and()
		.csrf().disable();
	}
}
