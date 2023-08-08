package ua.zxc.notes.mapper;

import java.util.List;

public interface Mapper<E, D> {

    E dtoToEntity(D dto);

    D entityToDto(E entity);

    List<E> dtosToEntities(List<D> dtos);

    List<D> entitiesToDtos(List<E> entities);
}
