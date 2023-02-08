package com.codeup.testrepo.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.UserRepository;

@Component
public class seedUsersTable implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            User neighbor1 = new User( 1,"theloudhouse", "loud@mail.com", 1);
            User neighbor2 = new User(2, "thequiethouse", "quiet@mail.com", 2);
            User neighbor3 = new User(3, "theoutsiders", "outside@mail.com", 3);
            User seller1 = new User(4, "ProRealtor", "realtor@mail.com", 4);
            User seller2 = new User(5, "Homes4U", "home4u@mail.com", 5);
            User seller3 = new User(6, "UbuyHome", "buyhome@mail.com", 6);
            User buyer1 = new User(7, "iwanthomes", "want@mail.com", 7);
            User buyer2 = new User(8, "givemehome", "give@mail.com", 8);
            User buyer3 = new User(9, "ibuyhome", "buy@mail.com", 9);


        }
        System.out.println(userRepository.count());
    }
}
