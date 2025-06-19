package com.agrofarm.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure()
    .directory("target") // путь к папке с .env
    .filename(".env")
    .load();

    String openaiKey = dotenv.get("OPENAI_API_KEY");
    String dbUser = dotenv.get("POSTGRES_USERNAME");
    String dbPass = dotenv.get("POSTGRES_PASSWORD");

    if (openaiKey != null) System.setProperty("OPENAI_API_KEY", openaiKey);
    else System.err.println("❌ OPENAI_API_KEY not set in .env");

    if (dbUser != null) System.setProperty("POSTGRES_USERNAME", dbUser);
    else System.err.println("❌ POSTGRES_USERNAME not set in .env");

    if (dbPass != null) System.setProperty("POSTGRES_PASSWORD", dbPass);
    else System.err.println("❌ POSTGRES_PASSWORD not set in .env");

    SpringApplication.run(BackendApplication.class, args);
}

  @Configuration 
  public static class Myconfiguration{
  @Bean  
  public WebMvcConfigurer corsConfigurer(){
   return new WebMvcConfigurer() {    @Override
    public void addCorsMappings(CorsRegistry registry) {     registry.addMapping("/**")
       .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");    }
   };
  } }
}



