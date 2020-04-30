package byk.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import byk.app.repository.*;
import byk.app.model.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;


@SpringBootApplication
public class AppApplication implements CommandLineRunner {

//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private TransactionRepository transactionRepository;

    public static void main(String[] args)  {

        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) { }
}
