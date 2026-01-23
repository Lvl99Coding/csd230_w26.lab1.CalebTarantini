package csd230.lab1.repositories;

import csd230.lab1.entities.MagazineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MagazineEntityRepository extends JpaRepository<MagazineEntity, Long> {
    List<MagazineEntity> findByCurrentIssue(LocalDateTime currentIssue);
    List<MagazineEntity> findByTitle(String title);
}