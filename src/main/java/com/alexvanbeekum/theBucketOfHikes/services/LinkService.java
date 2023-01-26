package com.alexvanbeekum.theBucketOfHikes.services;

import com.alexvanbeekum.theBucketOfHikes.exceptions.DuplicateException;
import com.alexvanbeekum.theBucketOfHikes.exceptions.EntityNotFoundException;
import com.alexvanbeekum.theBucketOfHikes.exceptions.ValidationException;
import com.alexvanbeekum.theBucketOfHikes.models.Link;
import com.alexvanbeekum.theBucketOfHikes.repositories.LinkRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LinkService {
    final LinkRepository linkRepository;

    private static final Logger logger = LoggerFactory.getLogger(LinkService.class);

    @Transactional
    public boolean addLink(Link link){
        if(linkRepository.existsByLink(link.getLink()))
            throw new DuplicateException("Link already exists","Link of {" + link.getLink() +"} already exits in database");

        if(link.getLinkName() == null || link.getLink() == null || link.getType() == null || link.getLocationId() == null)
            throw new ValidationException("Missing link details");

        linkRepository.save(link);

        logger.info("Link " + link.getLinkName() + " has been added");

        return true;
    }

    public List<Link> getLinksByLocationId(Long locationId) {
        return linkRepository.getAllByLocationId(locationId);
    }
    public List<Link> getAllLinks(){
        return linkRepository.findAll();
    }

    @Transactional
    public void updateLink(Link link){
        Link update = linkRepository.findById(link.getLinkId()).orElse(null);

        if(update == null){
            throw new EntityNotFoundException("Link not found");
        }
        update.update(link);
    }

    @Transactional
    public boolean removeLink(Long linkId){
        if(!linkRepository.existsById(linkId)){
            throw new EntityNotFoundException("Link with Id \"" + linkId +"\" not found");
        }
        Link  link = linkRepository.getReferenceById(linkId);

        return removeLink(link);
    }

    @Transactional
    public boolean removeLink( Link link){
        linkRepository.delete(link);
        logger.info("Location " + link + " has been removed");

        return true;
    }
}
