package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByIsbn(String isbn);
    BookEntity findById(long id);
    List<BookEntity> findByTitleLike(String contents);
    @Query
    List<BookEntity> findByCopiesBetween(int min, int max);
}