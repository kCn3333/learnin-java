package pl.kcn333.employeeCrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails john =User.builder().username("john").password("{noop}elo").roles("user").build();
//        UserDetails bob=User.builder().username("bob").password("{noop}elo").roles("user","admin").build();
//
//        return new InMemoryUserDetailsManager(john,bob);
//
//
//    }


    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id AS username, pw AS password, active FROM members WHERE user_id = ?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id AS username, role AS authority FROM roles WHERE user_id = ?");

        return jdbcUserDetailsManager;

        
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer->configurer
                .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH,"/api/employees/**").hasRole("ADMIN"));

        // use basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());
        // disable CRSF (Cross Site Request Forgery)
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

}
