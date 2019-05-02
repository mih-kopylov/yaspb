package ru.mihkopylov.myspb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "reason_groups")
@Getter
@Setter
public class ReasonGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Nullable
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ReasonGroup parent;
    @NonNull
    private String name;
    @Nullable
    @Column(name = "reason_id")
    private Long reasonId;
    @Nullable
    private String body;
}
