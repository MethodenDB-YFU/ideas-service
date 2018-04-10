package de.yfu.intranet.ideas.api.resources.mapper;

import de.yfu.intranet.ideas.api.resources.LikeResource;
import de.yfu.intranet.ideas.data.domain.Like;
import org.mapstruct.Mapper;

@Mapper
public interface LikeMapper {

    Like mapToLike(LikeResource likeResource);
    LikeResource mapToResoucre(Like like);
}
