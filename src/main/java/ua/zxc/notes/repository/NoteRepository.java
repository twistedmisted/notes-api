package ua.zxc.notes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.zxc.notes.entity.NoteEntity;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, Long> {

    Page<NoteEntity> findByUserUsername(Pageable pageable, String username);
}
