package com.codeup.testrepo.services;
<<<<<<< HEAD

=======
>>>>>>> main
import com.codeup.testrepo.models.User;
import jakarta.persistence.*;

import java.util.Date;

public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;


}


