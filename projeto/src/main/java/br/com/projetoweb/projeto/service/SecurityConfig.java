package br.com.projetoweb.projeto.service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/usuarios").permitAll().and()
        .authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/usuarios").permitAll().and()
        .authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/login").permitAll()
        .anyRequest().authenticated().and().cors();


        return http.build();
    }
    
   
         
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
