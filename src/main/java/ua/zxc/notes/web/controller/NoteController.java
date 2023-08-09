package ua.zxc.notes.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.zxc.notes.dto.CreateNoteDto;
import ua.zxc.notes.dto.UpdateNoteDto;
import ua.zxc.notes.payload.CreateNoteRequest;
import ua.zxc.notes.payload.NotePayload;
import ua.zxc.notes.payload.PagePayload;
import ua.zxc.notes.payload.ResponseBody;
import ua.zxc.notes.payload.UpdateNoteRequest;
import ua.zxc.notes.service.NoteFacade;

import java.security.Principal;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static ua.zxc.notes.util.Utils.getTimestampNow;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
@Slf4j
public class NoteController {

    private final NoteFacade noteFacade;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getNoteById(@PathVariable(name = "id") Long noteId) {
        log.debug("Getting note by id = [{}]", noteId);
        NotePayload noteById = noteFacade.getNoteById(noteId);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addValue("note", noteById);
        return new ResponseEntity<>(responseBody, OK);
    }

    @GetMapping
    public ResponseEntity<ResponseBody> getAllNotesByUsername(@RequestParam(name = "page") int numberOfPage,
                                                              Principal principal) {
        log.debug("Getting all notes by username = [{}]", principal.getName());
        PagePayload<NotePayload> notes = noteFacade.getAllNotesByUsername(numberOfPage, principal.getName());
        ResponseBody responseBody = new ResponseBody();
        responseBody.addValue("page", notes);
        return new ResponseEntity<>(responseBody, OK);
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody CreateNoteRequest createNoteReq,
                                        Principal principal) {
        String username = principal.getName();
        log.debug("Creating new note by user = [{}]", username);
        noteFacade.createNote(new CreateNoteDto(createNoteReq.getText(), username, getTimestampNow()));
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable(name = "id") Long noteId,
                                        @RequestBody UpdateNoteRequest updateNoteReq,
                                        Principal principal) {
        String username = principal.getName();
        log.debug("Updating note with id = [{}] by user = [{}]", noteId, username);
        noteFacade.updateNote(new UpdateNoteDto(noteId, updateNoteReq.getText(), username, getTimestampNow()));
        return new ResponseEntity<>(OK);
    }
}
