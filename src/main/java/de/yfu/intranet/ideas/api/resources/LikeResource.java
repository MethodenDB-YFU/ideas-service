package de.yfu.intranet.ideas.api.resources;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class LikeResource {

    private UUID id;

    @NotNull
    private UUID likeBy;

    private LocalDateTime likeAt;

    public UUID getLikeBy() {
        return likeBy;
    }

    public void setLikeBy(UUID likeBy) {
        this.likeBy = likeBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getLikeAt() {
        return likeAt;
    }

    public void setLikeAt(LocalDateTime likeAt) {
        this.likeAt = likeAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeResource that = (LikeResource) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(likeBy, that.likeBy) &&
                Objects.equal(likeAt, that.likeAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, likeBy, likeAt);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("likeBy", likeBy)
                .add("likeOn", likeAt)
                .toString();
    }
}
