package br.com.projetoweb.projeto.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/usuarios").permitAll().and()
        .authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/usuarios/login").permitAll()
        .anyRequest().authenticated().and().cors();
        http.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
