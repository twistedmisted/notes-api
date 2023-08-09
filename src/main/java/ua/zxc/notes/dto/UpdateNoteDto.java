package ua.zxc.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class UpdateNoteDto {

    private Long id;
    private String text;
    private String byUsername;
    private Timestamp updatedAt;
}
