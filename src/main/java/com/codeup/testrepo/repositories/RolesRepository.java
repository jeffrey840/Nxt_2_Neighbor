package com.codeup.testrepo.repositories;
        import com.codeup.testrepo.models.Roles;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    static Roles findByName() {
        return null;
    }

}
