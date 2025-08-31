package kcn333.securityDemo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager =new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id AS username, pw AS password, active AS enabled FROM users WHERE user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id AS username, role AS authority FROM roles WHERE user_id=?");


        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/").hasRole("USER")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form-> form
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied")
                );
        return httpSecurity.build();
    }




        /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails john= User.builder()
                .username("john")
                .password("{noop}test")
                .roles("USER")
                .build();
        UserDetails suzan= User.builder()
                .username("suzan")
                .password("{noop}test")
                .roles("USER","MANAGER")
                .build();
        UserDetails bob= User.builder()
                .username("bob")
                .password("{noop}test")
                .roles("USER","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, suzan, bob);

    }
    */

}
