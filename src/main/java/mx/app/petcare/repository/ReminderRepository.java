package mx.app.petcare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

	public Page<Reminder> findByOwner(Person owner, Pageable pageable);
}
