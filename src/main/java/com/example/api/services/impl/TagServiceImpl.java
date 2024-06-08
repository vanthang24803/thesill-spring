package com.example.api.services.impl;

import com.example.api.common.exceptions.BadRequestException;
import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.helpers.Response;
import com.example.api.common.messages.NotFoundMessage;
import com.example.api.domain.dtos.tag.TagRequest;
import com.example.api.domain.entities.TagEntity;
import com.example.api.repositories.TagRepository;
import com.example.api.repositories.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public void seeds() {
        Set<String> seedData = this.data();

        for (String seed : seedData) {
            if (tagRepository.findByName(seed).isEmpty()) {
                var tag = TagEntity
                        .builder()
                        .id(UUID.randomUUID())
                        .name(seed)
                        .createdAt(LocalDateTime.now())
                        .build();
                tagRepository.save(tag);
            }
        }
    }

    @Override
    public Response<TagEntity> save(TagRequest request) {
        if (tagRepository.existsByName(request.getName())) {
            throw new BadRequestException("Tag name existed!");
        }

        var tag = TagEntity
                .builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .createdAt(LocalDateTime.now())
                .build();

        tagRepository.save(tag);

        return new Response<>(HttpStatus.CREATED.value(), tag);
    }

    @Override
    public Response<List<TagEntity>> findAll() {
        var tags = tagRepository.findAll();
        return new Response<>(HttpStatus.OK.value(), tags);
    }

    @Override
    public Response<TagEntity> findOne(UUID id) {
        var tagName = this.findByIdThrowException(id);
        return new Response<>(HttpStatus.OK.value(), tagName);
    }

    @Override
    public Response<TagEntity> update(UUID id, TagRequest request) {
        var tag = this.findByIdThrowException(id);

        tag.setName(request.getName());

        tagRepository.save(tag);

        return new Response<>(HttpStatus.OK.value(), tag);
    }

    @Override
    public void remove(UUID id) {
        var tag = this.findByIdThrowException(id);
        tagRepository.delete(tag);
    }


    private TagEntity findByIdThrowException(UUID id) {
        return tagRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.TAG_NOT_FOUND)
        );
    }

    private Set<String> data() {
        return Set.copyOf(Set.of(
                "Pet-Friendly",
                "Best Seller",
                "Favorite Gift",
                "New Arrival",
                "Garden & Patio",
                "New Color"
        ));
    }
}
