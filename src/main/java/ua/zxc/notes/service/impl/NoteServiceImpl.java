package ua.zxc.notes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.zxc.notes.dto.CreateNoteDto;
import ua.zxc.notes.dto.NoteDto;
import ua.zxc.notes.dto.PageDto;
import ua.zxc.notes.dto.UpdateNoteDto;
import ua.zxc.notes.entity.NoteEntity;
import ua.zxc.notes.mapper.impl.NoteMapper;
import ua.zxc.notes.repository.NoteRepository;
import ua.zxc.notes.service.NoteService;

import static org.springframework.http.HttpStatus.FORBIDDEN;
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
        NoteEntity noteEntity = getById(noteId);
        return noteMapper.entityToDto(noteEntity);
    }

    private NoteEntity getById(Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "The note with id = [" + noteId +
                        "] does not exist."));
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

    @Override
    public void createNote(CreateNoteDto createNoteDto) {
        log.debug("Creating note by user = [{}]", createNoteDto.getByUsername());
        noteRepository.save(noteMapper.dtoToEntity(createNoteDto));
    }

    @Override
    public void updateNote(UpdateNoteDto updateNoteDto) {
        Long noteId = updateNoteDto.getId();
        log.debug("Updating note with id = [{}] by user = [{}]", noteId, updateNoteDto.getByUsername());
        NoteEntity noteToUpdate = getById(noteId);
        if (userHasNotRights(updateNoteDto.getByUsername(), noteToUpdate.getUser().getUsername())) {
            throw new ResponseStatusException(FORBIDDEN, "You does not have rights to update this note.");
        }
        updateInformation(updateNoteDto, noteToUpdate);
        noteRepository.save(noteToUpdate);
    }

    private boolean userHasNotRights(String byUsername, String noteOwner) {
        return !byUsername.equals(noteOwner);
    }

    private void updateInformation(UpdateNoteDto updateNoteDto, NoteEntity noteToUpdate) {
        noteToUpdate.setText(updateNoteDto.getText());
        noteToUpdate.setUpdatedAt(updateNoteDto.getUpdatedAt());
    }
}
