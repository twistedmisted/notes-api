package ua.zxc.notes.service;

import ua.zxc.notes.dto.CreateNoteDto;
import ua.zxc.notes.dto.NoteDto;
import ua.zxc.notes.dto.PageDto;
import ua.zxc.notes.dto.UpdateNoteDto;

public interface NoteService {

    NoteDto getNoteById(Long noteId);

    PageDto<NoteDto> getAllNotesByUsername(int numberOfPage, String username);

    void createNote(CreateNoteDto createNoteDto);

    void updateNote(UpdateNoteDto updateNoteDto);
}
