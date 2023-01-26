package com.alexvanbeekum.theBucketOfHikes.controllers;

import com.alexvanbeekum.theBucketOfHikes.exceptions.DuplicateException;
import com.alexvanbeekum.theBucketOfHikes.models.Link;
import com.alexvanbeekum.theBucketOfHikes.payload.requests.AddLinkRequest;
import com.alexvanbeekum.theBucketOfHikes.payload.requests.LinkRequest;
import com.alexvanbeekum.theBucketOfHikes.payload.responses.LinkResponse;
import com.alexvanbeekum.theBucketOfHikes.payload.responses.ListResponse;
import com.alexvanbeekum.theBucketOfHikes.payload.responses.MessageResponse;
import com.alexvanbeekum.theBucketOfHikes.services.LinkService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
@WebFilter("/filter-response-header/*")
public class LinkController implements Filter {
    final LinkService linkService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        chain.doFilter(request, response);
    }

    @PostMapping(path="/link")
    public ResponseEntity<?> addLink(@RequestBody AddLinkRequest addLinkRequest){
        Link link = new Link(addLinkRequest);

        try{
            linkService.addLink(link);
        } catch (DuplicateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(HttpStatus.CONFLICT, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new LinkResponse(link));
    }

    @GetMapping(path="/location/{locationId}/links")
    public ResponseEntity<ListResponse> getLinksByLocationId(@PathVariable Long locationId){
        List<Link> links = linkService.getLinksByLocationId(locationId);
        System.out.println("location Id: " + locationId);
        System.out.println(links);
        return ResponseEntity.ok(new ListResponse(links));
    }

    @PutMapping(path="/link")
    public ResponseEntity<MessageResponse> editLink(@RequestBody LinkRequest linkRequest){
        Link link = new Link(linkRequest);
        linkService.updateLink(link);

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "Link " + link.getLinkName() + " has been successfully updated"));
    }

    @DeleteMapping(path="/link/{linkId}")
    public ResponseEntity<MessageResponse> removeLink(@PathVariable Long linkId){
        System.out.println("link with linkID " + linkId + " is being requested for removal");
        linkService.removeLink(linkId);

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "link with id " + linkId + " has been successfully removed"));
    }
}
