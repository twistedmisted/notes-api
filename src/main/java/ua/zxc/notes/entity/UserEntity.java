package ua.zxc.notes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "users")
@Setter
@Getter
public class UserEntity {

    @Id
    private Long id;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = ALL)
    private List<NoteEntity> notes = new ArrayList<>();
}
