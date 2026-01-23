package csd230.lab1.repositories;

import csd230.lab1.entities.ElectricGuitarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectricGuitarEntityRepository extends JpaRepository<ElectricGuitarEntity, Long> {
    ElectricGuitarEntity findById(long id);
    List<ElectricGuitarEntity> findByNumberOfPickups(int numberOfPickups);
    List<ElectricGuitarEntity> findByNumberOfStrings(int numberOfStrings);
}