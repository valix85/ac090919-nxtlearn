package it.nextre.academy.nxtlearn.api;


import it.nextre.academy.nxtlearn.config.CustomUserDetails;
import it.nextre.academy.nxtlearn.dto.JwtRequestDto;
import it.nextre.academy.nxtlearn.dto.JwtResponseDto;
import it.nextre.academy.nxtlearn.myutils.JwtTokenUtil;
import it.nextre.academy.nxtlearn.service.impl.CustomUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class JwtAuthenticationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    //private AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    //private JwtUserDetailsService userDetailsService;
    private CustomUserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) throws Exception {
        logger.debug("Tentativo di autenticazione da: " + authenticationRequest.getUtente());
        authenticate(authenticationRequest.getUtente(), authenticationRequest.getPwd());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUtente());
        final String token = jwtTokenUtil.generateToken(userDetails);
        // hook here to add custom properties
        // todo spostare in un service, i controller devon essere leggeri
        Map<String, Object> risp = new HashMap<>();
        risp.put("token", token);
        risp.put("username",userDetails.getUsername());
        String role = "";
        role = userDetails.getAuthorities().stream()
                .map(el -> ((GrantedAuthority) el).getAuthority())
                .collect(Collectors.joining( "," ));
        role = role.replaceAll("ROLE_","");
        risp.put("role",role);

        risp.put("expireSession", jwtTokenUtil.getExpirationDateFromToken(token));
        risp.put("nome", ((CustomUserDetails)userDetails).getPersona().getNome());
        risp.put("cognome",((CustomUserDetails)userDetails).getPersona().getCognome());

        return ResponseEntity.ok(risp);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            logger.debug("username: "+ username+" , password: " +
                    ((password!=null)?"*".repeat(password.length()):"null") );
            //authenticationManager
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}//end class
