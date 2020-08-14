package com.nps.tacocloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.activation.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by peishen.nie on 2020/7/22.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Qualifier("userRepositoryUserDetailService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        /*auth.inMemoryAuthentication().withUser("buzz").password("infinity").authorities("ROLE_USER")
                .and().withUser("woody").password("bullseye").authorities("ROLE_USER");

        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username, password, enabled from Users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from Users where username = ?").passwordEncoder(new StandardPasswordEncoder("53cr3t"));
*/
        /*auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("uid={0}")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .passwordCompare().passwordEncoder(new BCryptPasswordEncoder()).passwordAttribute("passcode");*/
//        auth.ldapAuthentication().contextSource().root("dc=tacocloud,dc=com").ldif("classpath:users.ldif");

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests().antMatchers("/design", "/orders").access("hasRole('ROLE_USER')").antMatchers("/", "/**").access("permitAll")
        .and().formLogin().loginPage("/login").loginProcessingUrl("authenticate").usernameParameter("user").passwordParameter("pwd").defaultSuccessUrl("/design", true)
        .and().logout().logoutSuccessUrl("/");
    }


    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }


}
