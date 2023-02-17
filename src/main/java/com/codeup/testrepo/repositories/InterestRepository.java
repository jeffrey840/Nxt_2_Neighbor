package com.codeup.testrepo.repositories;
import com.codeup.testrepo.models.Interests;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterestRepository extends JpaRepository<Interests, Long> {

}
