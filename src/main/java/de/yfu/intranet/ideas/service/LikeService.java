package de.yfu.intranet.ideas.service;

import de.yfu.intranet.ideas.data.domain.Idea;
import de.yfu.intranet.ideas.data.domain.Like;
import de.yfu.intranet.ideas.data.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 *
 * @Author Alex Senger <alexander.senger@yfu-deustchland.de>
 *
 */
@Service
public class LikeService {

    private final IdeaService ideaService;
    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(
            final LikeRepository likeRepository,
            final IdeaService ideaService) {
        this.ideaService = ideaService;
        this.likeRepository = likeRepository;
    }

    public void like(UUID ideaId, Like like) throws Exception {
        Idea idea = ideaService.getIdea(ideaId);
        if (idea == null) {
            throw new Exception("No idea was found for the given ID");
        }

        Like existing = likeRepository.findOneByIdeaAndLikeBy(idea, like.getLikeBy());

        if (existing == null) {
            idea.addLike(like);
            ideaService.updateIdea(idea);
        }
        else {
            throw new Exception("A like from this user already exists for this Idea.");
        }
    }

    public void unlike(UUID ideaId, UUID likeId, Like like) throws Exception {
        Idea idea = ideaService.getIdea(ideaId);
        if (idea == null) {
            throw new Exception("No idea was found for the given ID");
        }

        Like existing = likeRepository.findOne(likeId);
        if (existing == null) {
            throw new Exception("No like was found with the given ID");
        }

        if (existing.getLikeBy().equals(like.getLikeBy())) {
            likeRepository.delete(existing);
        }
        else {
            throw new Exception("Like wasn't given by current user.");
        }
    }
}
