package com.codeup.testrepo.repositories;

<<<<<<< HEAD

=======
>>>>>>> main
import com.codeup.testrepo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
