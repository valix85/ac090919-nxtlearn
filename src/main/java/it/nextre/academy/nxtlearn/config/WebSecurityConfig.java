package it.nextre.academy.nxtlearn.config;

import it.nextre.academy.nxtlearn.exception.NotFoundException;
import it.nextre.academy.nxtlearn.filter.JwtRequestFilter;
import it.nextre.academy.nxtlearn.service.impl.CustomUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import static org.springframework.security.config.Customizer.withDefaults;

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

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;




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
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                // Le regole sono senza contesto di deploy
                .antMatchers("/","/home","/index", "/api/**","/api/register","/register","/error","/404","/403","/authenticate").permitAll()
                //.antMatchers("/assets/**").permitAll()
                // aggiungere le options per il jwt
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("utente")
                .passwordParameter("pwd")
                .successHandler(loginSuccessHandler)
                //.successForwardUrl("/")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
                        System.out.println("UTENTE RESPINTO");
                        // System.out.println(request);
                        response.sendError(403,exception.getMessage());

                    }
                })
                //.failureForwardUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                //.sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        ;


        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration = configuration.applyPermitDefaultValues();
        //configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","PATCH","OPTIONS","HEAD"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}//end class
