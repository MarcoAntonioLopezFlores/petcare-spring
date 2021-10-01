package mx.app.petcare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByEmail(String email);

}
