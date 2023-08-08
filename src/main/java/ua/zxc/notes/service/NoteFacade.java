package ua.zxc.notes.service;

import ua.zxc.notes.dto.NoteDto;
import ua.zxc.notes.payload.CreateNotePayload;
import ua.zxc.notes.payload.NotePayload;
import ua.zxc.notes.payload.PagePayload;

public interface NoteFacade {

    NotePayload getNoteById(Long noteId);

    NoteDto createNote(CreateNotePayload createNote, String username);

    PagePayload<NotePayload> getAllNotesByUsername(int numberOfPage, String username);
}
