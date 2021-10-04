package mx.app.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
