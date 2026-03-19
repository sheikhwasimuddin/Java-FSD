package noteforge.controller;

import noteforge.entity.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import noteforge.service.NoteService;

import java.util.List;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) String category,
                       @RequestParam(required = false) String filter,
                       Model model) {

        List<Note> notes;

        if (keyword != null && !keyword.trim().isEmpty()) {
            notes = noteService.searchNotes(keyword);
        } else if (category != null && !category.trim().isEmpty()) {
            notes = noteService.getNotesByCategory(category);
        } else if ("favorites".equalsIgnoreCase(filter)) {
            notes = noteService.getFavoriteNotes();
        } else {
            notes = noteService.getAllNotes();
        }

        model.addAttribute("notes", notes);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/notes/add")
    public String showAddForm(Model model) {
        model.addAttribute("note", new Note());
        return "add-note";
    }

    @PostMapping("/notes/save")
    public String saveNote(@ModelAttribute Note note) {
        noteService.saveNote(note);
        return "redirect:/";
    }

    @GetMapping("/notes/view/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        Note note = noteService.getNoteById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note ID"));
        model.addAttribute("note", note);
        return "view-note";
    }

    @GetMapping("/notes/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Note note = noteService.getNoteById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note ID"));
        model.addAttribute("note", note);
        return "edit-note";
    }

    @PostMapping("/notes/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute Note updatedNote) {
        Note note = noteService.getNoteById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note ID"));

        note.setTitle(updatedNote.getTitle());
        note.setCategory(updatedNote.getCategory());
        note.setContent(updatedNote.getContent());
        note.setFavorite(updatedNote.isFavorite());

        noteService.saveNote(note);
        return "redirect:/";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/";
    }
}