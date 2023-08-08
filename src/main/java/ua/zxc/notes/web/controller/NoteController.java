package ua.zxc.notes.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.zxc.notes.payload.NotePayload;
import ua.zxc.notes.payload.PagePayload;
import ua.zxc.notes.payload.ResponseBody;
import ua.zxc.notes.service.NoteFacade;

import java.security.Principal;

import static org.springframework.http.HttpStatus.OK;

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
}
