package ua.zxc.notes.payload;

import lombok.Data;

@Data
public class NotePayload {

    private Long id;
    private String text;
    private String createdAt;
    private String updatedAt;
}
