package de.yfu.intranet.ideas.service;

import de.yfu.intranet.ideas.data.domain.Commit;
import de.yfu.intranet.ideas.data.domain.Idea;
import de.yfu.intranet.ideas.data.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 * @Author Alex Senger <alexander.senger@yfu-deustchland.de>
 *
 */
@Service
public class CommitService {

    private final IdeaService ideaService;
    private final CommitRepository commitRepository;

    @Autowired
    public CommitService(
            final IdeaService ideaService,
            final CommitRepository commitRepository) {
        this.ideaService = ideaService;
        this.commitRepository = commitRepository;
    }

    public void commit(UUID ideaId, Commit commit) throws Exception {
        Idea idea = ideaService.getIdea(ideaId);
        if (idea == null) {
            throw new Exception("No idea was found for the given ID");
        }

        Commit existing = commitRepository.findOneByIdeaAndCommitBy(idea, commit.getCommitBy());

        if (existing == null) {
            idea.addCommit(commit);
            ideaService.updateIdea(idea);
        }
        else {
            throw new Exception("A commit from this user already exists for this Idea.");
        }
    }

    public void uncommit(UUID ideaId, UUID commitId, Commit commit) throws Exception {
        Idea idea = ideaService.getIdea(ideaId);
        if (idea == null) {
            throw new Exception("No idea was found for the given ID");
        }

        Commit existing = commitRepository.findOne(commitId);
        if (existing == null) {
            throw new Exception("No commit was found with the given ID");
        }

        if (existing.getCommitBy().equals(commit.getCommitBy())) {
            commitRepository.delete(existing);
        }
        else {
            throw new Exception("Commit wasn't given by current user.");
        }
    }
}
