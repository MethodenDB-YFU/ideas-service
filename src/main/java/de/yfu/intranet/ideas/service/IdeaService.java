package de.yfu.intranet.ideas.service;

import de.yfu.intranet.ideas.data.domain.Idea;
import de.yfu.intranet.ideas.data.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(final IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public Set<Idea> getAll() {
        Set<Idea> ideas = ideaRepository.findAll();
        if (ideas == null) {
            return Collections.emptySet();
        }
        else {
            return ideas;
        }
    }

    public Idea getIdea(UUID ideaId) {
        return ideaRepository.findOne(ideaId);
    }

    public Idea updateIdea(Idea idea) {
        return ideaRepository.save(idea);

    }

    public void deleteIdea(UUID ideaId) throws Exception {
        Idea idea = ideaRepository.findOne(ideaId);
        if (idea == null) {
            throw new Exception("No idea with given ID found");
        }
        ideaRepository.delete(idea);
    }

    public Idea createIdea(Idea idea) {
        return ideaRepository.save(idea);
    }
}
