package byk.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import byk.app.repository.*;
import byk.app.model.*;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;


@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CashbooklineRepository cashbooklineRepository;

//    @Autowired
//    private AccountCashbookJoinRepository accountCashbookJoinRepository;

    public static void main(String[] args)  {

        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {
        Account o = accountRepository.getOne(0L);
        Account p = accountRepository.getOne(1L);

        System.out.println("Printing: "+p.getName());
        Set<AccountCashbookJoin> a = p.getAccountCashbookJoins();
        for (AccountCashbookJoin b: a ){
            System.out.println(b.getCashbookline());
        }


//        cashbooklineRepository.deleteAllInBatch();
//        accountCashbookJoinRepository.deleteAllInBatch();

        System.out.println("adding stuff to db");
        Account originAccount = accountRepository.getOne(0L);
        Account targetAccount = accountRepository.getOne(1L);

        Cashbookline line = new Cashbookline("Stipendio blabal", 1000.65f);
        line.setDate(LocalDate.now());

        AccountCashbookJoin origin = new AccountCashbookJoin(false);
        origin.setAccount(originAccount);
        origin.setCashbookline(line);
        originAccount.getAccountCashbookJoins().add(origin);


        line.getAccountCashbookJoins().add(origin);

        cashbooklineRepository.save(line);
//        accountCashbookJoinRepository.save(origin);


        AccountCashbookJoin target = new AccountCashbookJoin(true);


        target.setAccount(targetAccount);
        target.setCashbookline(line);

        targetAccount.getAccountCashbookJoins().add(target);


        AccountCashbookJoin origin2 = new AccountCashbookJoin(true);
        AccountCashbookJoin target2 = new AccountCashbookJoin(false);

//        line.getAccountCashbookJoins().add(target);
//        accountCashbookJoinRepository.save(target);

        Cashbookline line2 = new Cashbookline("Fattura n.155", 5600);
        line.setDate(LocalDate.now());

        origin2.setAccount(originAccount);
        origin2.setCashbookline(line2);

        originAccount.getAccountCashbookJoins().add(origin2);

        target2.setAccount(targetAccount);
        target2.setCashbookline(line2);

        targetAccount.getAccountCashbookJoins().add(target2);

        cashbooklineRepository.save(line2);
//        line.getAccountCashbookJoins().add(origin2);
//        accountCashbookJoinRepository.save(origin2);
//        line.getAccountCashbookJoins().add(target2);
//        accountCashbookJoinRepository.save(target2);
    }
}
