package net.cascaes.tictactoe.game;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GameApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }
}