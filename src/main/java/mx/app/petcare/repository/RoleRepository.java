package mx.app.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
