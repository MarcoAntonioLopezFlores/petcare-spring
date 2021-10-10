package mx.app.petcare.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

	public List<Reminder> findByOwner(Person owner);
}
