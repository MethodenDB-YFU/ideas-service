package de.yfu.intranet.ideas.api.resources;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class CommitResource {

    private UUID id;

    @NotNull
    private UUID commitBy;

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

    public void setCommitAt(LocalDateTime commitAt) {
        this.commitAt = commitAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitResource that = (CommitResource) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(commitBy, that.commitBy) &&
                Objects.equal(commitAt, that.commitAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, commitBy, commitAt);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("commitBy", commitBy)
                .add("commitAt", commitAt)
                .toString();
    }
}
