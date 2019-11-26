package it.nextre.academy.nxtlearn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication!=null)
        logger.info("utente sloggato: " + ((CustomUserDetails) authentication.getPrincipal()).getUsername());
        logger.debug("LOGOUT called");
        response.setStatus(200);
    }

}//end class
