package it.nextre.academy.nxtlearn.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());




    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("UTENTE LOGGATO: "+authentication.getPrincipal());
        authentication.getAuthorities().forEach(System.out::println);
        System.out.println(authentication.getDetails());


        @Data
        class LoginOkDao{
            String username;
            String role;
            String expireSession;
        }

        LoginOkDao resp = new LoginOkDao();
        resp.username = ""+((CustomUserDetails) authentication.getPrincipal()).getUsername();
        resp.role = authentication.getAuthorities().stream()
                .map(el -> ((GrantedAuthority) el).getAuthority())
                .collect(Collectors.joining( "," ));
        resp.role = resp.role.replaceAll("ROLE_","");

        resp.expireSession = ""+(Instant.now().toEpochMilli()+30*1000);

        response.getWriter().write(
            new ObjectMapper().writeValueAsString(resp)
        );

        // System.out.println(request);
    }
}//end class
