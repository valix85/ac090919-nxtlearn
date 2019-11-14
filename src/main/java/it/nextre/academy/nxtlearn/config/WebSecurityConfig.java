package it.nextre.academy.nxtlearn.config;

import it.nextre.academy.nxtlearn.service.impl.CustomUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
// serve a poter utilizzare il controllo raffinato sui metodi nei controller
// https://www.baeldung.com/spring-security-method-security
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.
                        withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
*/

    /* utenti di prova*/
    /*
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        //collegare MySQL per rendere gli utenti del db
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                //.authorities("CAN_DELETE") //preferire i ruoli
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER");
    }
     */



    //ignorare la security per path/estensione file
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                //.antMatchers("/css/**")
                //.antMatchers("/js/**")
                //.antMatchers("/images/**")
                //.antMatchers("/fonts/**")
                .antMatchers("/assets/**");
        //web.ignoring().antMatchers("/*.js");
        //web.ignoring().antMatchers("/assets/**").antMatchers("/static/**");
    }



    @Autowired
    CustomUserDetailsServiceImpl customUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(customUserDetailsService);
        return auth;
    }

    //comunico a spring Security di usare il MIO provider che usa il MIO service
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }







    // https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // Le regole sono senza contesto di deploy
                .antMatchers("/", "/api/guida/**","/api/register","/register").permitAll()
                //.antMatchers("/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("utente")
                .passwordParameter("pwd")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                       logger.info("UTENTE LOGGATO: "+authentication.getPrincipal());

                        authentication.getAuthorities().forEach(System.out::println);
                        System.out.println(authentication.getDetails());

                    }
                })
                //.successForwardUrl("/")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("UTENTE RESPINTO");
                    }
                })
                .failureForwardUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }




}//end class
