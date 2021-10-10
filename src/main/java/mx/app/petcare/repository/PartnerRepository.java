package mx.app.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Partner;
import mx.app.petcare.entity.UserAccount;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>{
	public Partner findByUser(UserAccount user);
	List<Partner> findByStatusIsTrue();
	List<Partner> findByStatusIsFalse();
}
