package ru.mihkopylov.myspb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String login;
    @Nullable
    private String token;
}
