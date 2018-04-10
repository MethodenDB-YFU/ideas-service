package de.yfu.intranet.ideas.data.domain;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static de.yfu.intranet.ideas.data.domain.Idea.IDEA_TABLE;

@Entity
@Table(name = IDEA_TABLE)
public class Idea {

    public static final String IDEA_TABLE = "ideas";

    public enum Status {
        DRAFT, PUBLISHED, INPROGRESS, DONE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "i_id")
    private UUID id;

    @Column(name = "i_name")
    private String name;

    @Column(name = "i_description")
    private String description;

    @ColumnTransformer(read = "i_status::varchar", write = "?::yfu_ideas.status")
    @Enumerated(EnumType.STRING)
    @Column(name="i_status")
    private Status status;

    @Column(name = "i_created_by")
    private UUID createdBy;

    @Column(name = "i_created_at")
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "l_idea_id", referencedColumnName = "i_id")
    private Set<Like> likes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "c_idea_id", referencedColumnName = "i_id")
    private Set<Commit> commits;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "c_idea_id", referencedColumnName = "i_id")
    private List<Comment> comments;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public void addLike(Like like) {
        if (likes == null) {
            likes = new HashSet<>();
        }
        likes.add(like);
    }

    public Set<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Set<Commit> commits) {
        this.commits = commits;
    }

    public void addCommit(Commit commit) {
        if (commits == null) {
            commits = new HashSet<>();
        }
        commits.add(commit);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
