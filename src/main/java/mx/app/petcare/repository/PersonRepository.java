package mx.app.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.UserAccount;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	public Person findByUser(UserAccount user);
}
