package seguros.api.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import seguros.api.spring.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/resources/**").permitAll().antMatchers("/index")
		.authenticated()
		.anyRequest().hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.antMatchers("/charts", "/navbar").authenticated().anyRequest().hasAnyRole("USER")
		.and().formLogin()
		.loginPage("/login").usernameParameter("userName").passwordParameter("password")
		.successHandler((req,res,auth)->{
			res.sendRedirect("/index");
	      })
		.failureHandler((req,res,exp)->{
			String errMsg="";
	         if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
	            errMsg="Invalid e-mail or password.";
	         }else{
	            errMsg="Unknown error - "+exp.getMessage();
	         }
	         req.getSession().setAttribute("message", errMsg);
	         res.sendRedirect("/login");
		})
		.permitAll(); //.httpBasic() para utilizar autenticação do browser
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
	}

}