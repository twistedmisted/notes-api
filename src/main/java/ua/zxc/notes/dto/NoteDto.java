package ua.zxc.notes.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class NoteDto {

    private Long id;
    private String text;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
