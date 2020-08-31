package byk.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.transaction.Transactional;



@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    public static void main(String[] args)  {

        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) { }
}
