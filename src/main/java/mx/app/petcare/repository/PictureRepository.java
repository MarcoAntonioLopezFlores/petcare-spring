package mx.app.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Pet;
import mx.app.petcare.entity.Picture;


@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

	public List<Picture> findByPet(Pet pet);
}
