package noteforge.repository;

import noteforge.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrContentContainingIgnoreCase(
            String title, String category, String content
    );

    List<Note> findByCategoryIgnoreCase(String category);

    List<Note> findByFavoriteTrue();
}