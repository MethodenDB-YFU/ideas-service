package de.yfu.intranet.ideas.api.resources.mapper;

import de.yfu.intranet.ideas.api.resources.IdeaResource;
import de.yfu.intranet.ideas.data.domain.Idea;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface IdeaMapper {

    Set<Idea> mapToIdea(Set<IdeaResource> ideaResources);
    Set<IdeaResource> mapToResource(Set<Idea> idea);
    Idea mapToIdea(IdeaResource ideaResource);
    IdeaResource mapToResource(Idea idea);
}
