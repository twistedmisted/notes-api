package ua.zxc.notes.mapper.impl;

import org.springframework.stereotype.Component;
import ua.zxc.notes.dto.NoteDto;
import ua.zxc.notes.entity.NoteEntity;
import ua.zxc.notes.mapper.Mapper;
import ua.zxc.notes.payload.NotePayload;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static ua.zxc.notes.util.Utils.parseTimestampToStringDate;

@Component
public class NoteMapper implements Mapper<NoteEntity, NoteDto> {

    @Override
    public NoteEntity dtoToEntity(NoteDto dto) {
        if (isNull(dto)) {
            return null;
        }
        NoteEntity entity = new NoteEntity();
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    @Override
    public NoteDto entityToDto(NoteEntity entity) {
        if (isNull(entity)) {
            return null;
        }
        NoteDto dto = new NoteDto();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public NotePayload dtoToPayload(NoteDto dto) {
        if (isNull(dto)) {
            return null;
        }
        NotePayload payload = new NotePayload();
        payload.setId(dto.getId());
        payload.setText(dto.getText());
        payload.setCreatedAt(parseTimestampToStringDate(dto.getCreatedAt()));
        payload.setUpdatedAt(parseTimestampToStringDate(dto.getUpdatedAt()));
        return payload;
    }

    @Override
    public List<NoteEntity> dtosToEntities(List<NoteDto> dtos) {
        return null;
    }

    @Override
    public List<NoteDto> entitiesToDtos(List<NoteEntity> entities) {
        if (isNull(entities) || entities.isEmpty()) {
            return new ArrayList<>();
        }
        List<NoteDto> dtos = new ArrayList<>();
        for (NoteEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }

    public List<NotePayload> dtosToPayloads(List<NoteDto> dtos) {
        if (isNull(dtos) || dtos.isEmpty()) {
            return new ArrayList<>();
        }
        List<NotePayload> payloads = new ArrayList<>();
        for (NoteDto dto : dtos) {
            payloads.add(dtoToPayload(dto));
        }
        return payloads;
    }
}
