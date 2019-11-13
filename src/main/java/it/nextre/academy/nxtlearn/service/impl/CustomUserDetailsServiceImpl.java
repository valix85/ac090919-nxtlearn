package it.nextre.academy.nxtlearn.service.impl;



import it.nextre.academy.nxtlearn.config.CustomUserDetails;
import it.nextre.academy.nxtlearn.model.Utenza;
import it.nextre.academy.nxtlearn.repository.UtenzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {


    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UtenzaRepository utenzaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Calling loadUserByUsername method with username: " + username);

        Utenza tmp = utenzaRepository.findByEmail(username);
        log.debug("trovato utente: "+tmp);
        //System.out.println("####"+tmp);
        //System.out.println("####"+new CustomUserDetails(tmp));

        return new CustomUserDetails(tmp);
    }


}//end class