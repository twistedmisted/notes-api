package ua.zxc.notes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.zxc.notes.dto.NoteDto;
import ua.zxc.notes.dto.PageDto;
import ua.zxc.notes.entity.NoteEntity;
import ua.zxc.notes.mapper.impl.NoteMapper;
import ua.zxc.notes.repository.NoteRepository;
import ua.zxc.notes.service.NoteService;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private static final int PAGE_SIZE = 10;

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public NoteDto getNoteById(Long noteId) {
        log.debug("Getting note by id = [{}]", noteId);
        NoteEntity noteEntity = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "The note with such id does not exist."));
        return noteMapper.entityToDto(noteEntity);
    }

    @Override
    public PageDto<NoteDto> getAllNotesByUsername(int numberOfPage, String username) {
        log.debug("Getting all notes by username = [{}]", username);
        Page<NoteEntity> notesByUsername = noteRepository.findByUserUsername(PageRequest.of(numberOfPage - 1, PAGE_SIZE),
                username);
        if (!notesByUsername.hasContent()) {
            return new PageDto<>(numberOfPage, notesByUsername.getTotalPages());
        }
        return new PageDto<>(noteMapper.entitiesToDtos(notesByUsername.getContent()),
                numberOfPage, notesByUsername.getTotalPages());
    }
}
