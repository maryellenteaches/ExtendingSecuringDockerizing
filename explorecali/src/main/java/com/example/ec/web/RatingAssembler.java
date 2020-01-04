package com.example.ec.web;

import com.example.ec.domain.TourRating;
import com.example.ec.repo.TourRepository;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Rating Assembler, convert TourRating to a Hateoas Supported Rating class
 *
 * Created by maryellenbowman.
 */
@Component
public class RatingAssembler extends ResourceAssemblerSupport<TourRating,RatingDto> {

    //Helper to fetch Spring Data Rest Repository links.
    private RepositoryEntityLinks entityLinks;

    public RatingAssembler( RepositoryEntityLinks entityLinks) {
        super(RatingController.class, RatingDto.class);
        this.entityLinks = entityLinks;
    }

    /**
     *  Generates "self", "rating" and tour links
     *
     * @param tourRating Tour Rating Entity
     * @return
     */
    @Override
    public RatingDto toResource(TourRating tourRating) {
        RatingDto rating = new RatingDto(tourRating.getScore(), tourRating.getComment(), tourRating.getCustomerId());

        // "self" : ".../ratings/{ratingId}"
        ControllerLinkBuilder ratingLink = linkTo(methodOn(RatingController.class).getRating(tourRating.getId()));
        rating.add(ratingLink.withSelfRel());

        //"tour" : ".../tours/{tourId}"
       Link tourLink = entityLinks.linkToSingleResource(TourRepository.class, tourRating.getTour().getId());
       rating.add(tourLink.withRel("tour"));
       return rating;
    }

}
