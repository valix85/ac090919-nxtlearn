package it.nextre.academy.nxtlearn.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


// @EnableWebMvc //se attivato resetta la pre-configurazione di springboot e sarà necessario specificare nuovamente tutte le configurazioni del viewResolver e altro...
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //override dei metodi per impostare tutte le possibili configurazioni di spring WebMvc
    //configurazioni di esempio
    /*
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/");
        registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver()  {
        InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
        resolver.setPrefix ("/ui/jsp/");
        resolver.setSuffix (".jsp");
        resolver.setViewClass (JstlView.class);
        return resolver;
    }

     */



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register").setViewName("register.html");

        //registry.addViewController("/login").setViewName("login.html");
    }

    @Value("${datafile.directory}")
    String localPath;

    //la configurazione di defult di springboot con thymeleaf fa si di creare le rotte automatiche per tutti i file presenti all'interno di resources/public/
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/static/")
        .setCachePeriod(0) //scade in tempo zero, cache disattivata
        .resourceChain(false)
        ;
        // System.out.println("PATH LOCALE: "+localPath);
        if (localPath.startsWith("/")){
            // unix / linux like
            if (!localPath.endsWith("/")){
                localPath+="/";
            }
        }else{
            // other OS (windows)
            if (!localPath.endsWith("\\")){
                localPath+="\\";
            }
        }

        registry.addResourceHandler("/data/**")
                .addResourceLocations("file:"+localPath)
                .setCachePeriod(0) //scade in tempo zero, cache disattivata
                .resourceChain(false)
        ;

    }

/*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/login")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET","POST","OPTIONS","HEAD");

        registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","PATCH","HEAD");

    }

 */
}//end class
