package de.yfu.intranet.ideas.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static de.yfu.intranet.ideas.data.domain.Like.LIKE_TABLE;

@Entity
@Table(name = LIKE_TABLE)
public class Like {

    public static final String LIKE_TABLE = "likes";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "l_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "l_idea_id", nullable = false)
    @JsonBackReference
    private Idea idea;

    @Column(name = "l_like_by")
    private UUID likeBy;

    @Column(name = "l_like_at")
    private LocalDateTime likeAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLikeBy() {
        return likeBy;
    }

    public void setLikeBy(UUID likeBy) {
        this.likeBy = likeBy;
    }

    public LocalDateTime getLikeAt() {
        return likeAt;
    }

    public void setLikeAt(LocalDateTime likeAt) {
        this.likeAt = likeAt;
    }

    @PrePersist
    protected void onCreate() {
        likeAt = LocalDateTime.now();
    }
}
