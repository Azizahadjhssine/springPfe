package com.app.Config;

import java.util.Arrays;
import java.util.Collections;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.app.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig  {
	 private final UserRepository userRepository;
	    private final JwtAuthFilter jwtAuthFilter;

	    @Bean
	    //s
	    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	        http.headers().frameOptions().disable();
	        //http.formLogin();
	        http.authorizeRequests().antMatchers(
	        		"/**/auth/**",
	        		"/**/role/**",
	        		"/**/v1/**",
	        		"/api/v1/general",
	        		"/**/v1/Souscription/**",
	        		"/send-email",
	                // swagger
	                "/v3/api-docs",
	                "/v3/api-docs/**",
	                "/swagger-resources",
	                "/swagger-resources/**",
	                "/configuration/ui",
	                "/configuration/security",
	                "/swagger-ui/**",
	                "/webjars/**",
	                "/swagger-ui.html",
	                "/**/h2-console/**").permitAll();
	        //les droits d'accés pour chaque user
	      //  http.authorizeRequests().antMatchers(HttpMethod.DELETE  ,"**").hasRole("ADMIN");
	        http.authorizeRequests().anyRequest().authenticated();
	        http
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.authenticationProvider(authenticationProvider());
	        http.addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	                .cors()
	        ;
	        return http.build();




	    }
	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

	    @Bean
	    public UserDetailsService userDetailsService() {
	        return email -> userRepository.findByEmail(email)
	                .orElseThrow(() -> new EntityNotFoundException("User does not exist in the database, please register"));
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        // return NoOpPasswordEncoder.getInstance();
	        return new BCryptPasswordEncoder();
	    }
	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	    @Bean
	    //accéder à toute les méthodes en angular
	    public CorsFilter corsFilter() {
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        final CorsConfiguration config = new CorsConfiguration();

	        config.setAllowCredentials(true);//Autorise l'envoi de cookies et d'informations d'authentification dans les requêtes CORS.
	        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	        //Définit l'origine (domaine) qui est autorisée à accéder aux ressources de votre application. Dans cet exemple, 
	        //il s'agit d'Angular exécuté sur http://localhost:4200
	        
	        config.setAllowedHeaders(Arrays.asList("*"));
	        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PATCH", "OPTIONS"));

	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }

}
