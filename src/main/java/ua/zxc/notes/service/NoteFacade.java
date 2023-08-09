package ua.zxc.notes.service;

import ua.zxc.notes.dto.CreateNoteDto;
import ua.zxc.notes.dto.UpdateNoteDto;
import ua.zxc.notes.payload.NotePayload;
import ua.zxc.notes.payload.PagePayload;

public interface NoteFacade {

    NotePayload getNoteById(Long noteId);

    PagePayload<NotePayload> getAllNotesByUsername(int numberOfPage, String username);

    void createNote(CreateNoteDto createNoteDto);

    void updateNote(UpdateNoteDto updateNoteDto);
}
