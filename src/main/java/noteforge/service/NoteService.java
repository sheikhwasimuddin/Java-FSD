package noteforge.service;

import noteforge.entity.Note;
import org.springframework.stereotype.Service;
import noteforge.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> searchNotes(String keyword) {
        return noteRepository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrContentContainingIgnoreCase(
                keyword, keyword, keyword
        );
    }

    public List<Note> getNotesByCategory(String category) {
        return noteRepository.findByCategoryIgnoreCase(category);
    }

    public List<Note> getFavoriteNotes() {
        return noteRepository.findByFavoriteTrue();
    }
}