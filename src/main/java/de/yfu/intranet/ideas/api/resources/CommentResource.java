package de.yfu.intranet.ideas.api.resources;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class CommentResource {

    private UUID id;

    @NotBlank
    private String comment;

    @NotNull
    private UUID commentBy;

    private LocalDateTime commentAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setCommentAt(LocalDateTime commentAt) {
        this.commentAt = commentAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentResource that = (CommentResource) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(commentBy, that.commentBy) &&
                Objects.equal(commentAt, that.commentAt) &&
                Objects.equal(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, commentBy, commentAt, comment);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("commentBy", commentBy)
                .add("commentAt", commentAt)
                .add("comment", comment)
                .toString();
    }
}
