package ua.zxc.notes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.zxc.notes.dto.CreateNoteDto;
import ua.zxc.notes.dto.NoteDto;
import ua.zxc.notes.dto.PageDto;
import ua.zxc.notes.dto.UpdateNoteDto;
import ua.zxc.notes.mapper.impl.NoteMapper;
import ua.zxc.notes.payload.NotePayload;
import ua.zxc.notes.payload.PagePayload;
import ua.zxc.notes.service.NoteFacade;
import ua.zxc.notes.service.NoteService;

@Service
@RequiredArgsConstructor
public class NoteFacadeImpl implements NoteFacade {

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @Override
    public NotePayload getNoteById(Long noteId) {
        return noteMapper.dtoToPayload(noteService.getNoteById(noteId));
    }

    @Override
    public PagePayload<NotePayload> getAllNotesByUsername(int numberOfPage, String username) {
        PageDto<NoteDto> notesByUsername = noteService.getAllNotesByUsername(numberOfPage, username);
        return new PagePayload<>(noteMapper.dtosToPayloads(notesByUsername.getContent()),
                notesByUsername.getCurrentPageNumber(),
                notesByUsername.getTotalPages());
    }

    @Override
    public void createNote(CreateNoteDto createNoteDto) {
        noteService.createNote(createNoteDto);
    }

    @Override
    public void updateNote(UpdateNoteDto updateNoteDto) {
        noteService.updateNote(updateNoteDto);
    }
}
