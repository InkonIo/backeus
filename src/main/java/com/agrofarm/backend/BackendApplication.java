package com.agrofarm.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {

    Dotenv dotenv = Dotenv.configure().load();
    System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));
    System.setProperty("POSTGRES_USERNAME", dotenv.get("POSTGRES_USERNAME"));
    System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD"));
    SpringApplication.run(BackendApplication.class, args);
  }
}