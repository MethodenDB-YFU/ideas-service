package de.yfu.intranet.ideas.api.resources;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import de.yfu.intranet.ideas.data.domain.Comment;
import de.yfu.intranet.ideas.data.domain.Commit;
import de.yfu.intranet.ideas.data.domain.Idea;
import de.yfu.intranet.ideas.data.domain.Like;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class IdeaResource {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Idea.Status status;

    @NotBlank
    private UUID createdBy;

    private LocalDateTime createdAt;

    private Set<Like> likes;

    private List<Comment> comments;

    private Set<Commit> commits;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Idea.Status getStatus() {
        return status;
    }

    public void setStatus(Idea.Status status) {
        this.status = status;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Set<Commit> commits) {
        this.commits = commits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdeaResource that = (IdeaResource) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(name, that.name) &&
                Objects.equal(description, that.description) &&
                status == that.status &&
                Objects.equal(createdBy, that.createdBy) &&
                Objects.equal(createdAt, that.createdAt) &&
                Objects.equal(likes, that.likes) &&
                Objects.equal(comments, that.comments) &&
                Objects.equal(commits, that.commits);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, description, status, createdBy, createdAt, likes, comments, commits);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("status", status)
                .add("createdBy", createdBy)
                .add("createdAt", createdAt)
                .add("likes", likes)
                .add("comments", comments)
                .add("commits", commits)
                .toString();
    }
}