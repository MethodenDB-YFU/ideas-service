package de.yfu.intranet.ideas.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static de.yfu.intranet.ideas.data.domain.Commit.COMMIT_TABLE;

@Entity
@Table(name = COMMIT_TABLE)
public class Commit {

    public static final String COMMIT_TABLE = "commits";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "c_idea_id", nullable = false)
    @JsonBackReference
    private Idea idea;

    @Column(name = "c_commit_by")
    private UUID commitBy;

    @Column(name = "c_commit_at")
    private LocalDateTime commitAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCommitBy() {
        return commitBy;
    }

    public void setCommitBy(UUID commitBy) {
        this.commitBy = commitBy;
    }

    public LocalDateTime getCommitAt() {
        return commitAt;
    }

    public void setCommitAt(LocalDateTime commitOn) {
        this.commitAt = commitOn;
    }

    @PrePersist
    protected void onCreate() {
        commitAt = LocalDateTime.now();
    }
}
