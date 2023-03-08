package com.test.lichday.database;

import com.test.lichday.model.Lhp;

import com.test.lichday.repository.LhpRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Database {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(LhpRepository lhpRepository){


        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Lhp LhpA = new Lhp("HDV", "A2", "304", 1, 2);
                Lhp LhpB = new Lhp("Tester", "A2", "302", 2, 2);
                logger.info("insert data:"+lhpRepository.save(LhpA));
                logger.info("insert data:"+lhpRepository.save(LhpB));
            }
        };
    }

}
