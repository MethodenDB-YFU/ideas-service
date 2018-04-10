package de.yfu.intranet.ideas.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static de.yfu.intranet.ideas.data.domain.Comment.COMMENT_TABLE;

@Entity
@Table(name = COMMENT_TABLE)
public class Comment implements Serializable {

    public static final String COMMENT_TABLE = "comments";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "c_idea_id", nullable = false)
    @JsonBackReference
    private Idea idea;

    @Column(name = "c_comment")
    private String comment;

    @Column(name = "c_comment_by")
    private UUID commentBy;

    @Column(name = "c_comment_at")
    private LocalDateTime commentAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UUID getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(UUID commentBy) {
        this.commentBy = commentBy;
    }

    public LocalDateTime getCommentAt() {
        return commentAt;
    }

    public void setCommentAt(LocalDateTime commentOn) {
        this.commentAt = commentOn;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    @PrePersist
    protected void onCreate() {
        commentAt = LocalDateTime.now();
    }
}
