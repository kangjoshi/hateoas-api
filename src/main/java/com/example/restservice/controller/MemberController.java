package com.example.restservice.controller;

import com.example.restservice.config.hateoas.MemberModelAssembler;
import com.example.restservice.domain.Member;
import com.example.restservice.dto.MemberModel;
import com.example.restservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/members", produces = APPLICATION_JSON_VALUE)
public class MemberController {

    private final MemberService memberService;
    private final MemberModelAssembler memberModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<MemberModel>> members() {

        CollectionModel<EntityModel<MemberModel>> members = CollectionModel.wrap(memberService.findAll()
                .map(member -> memberModelAssembler.toModel(member))
                .toIterable());

        // todo : webflux 링크 사용법 알아보기
        members.add(
            linkTo(methodOn(MemberController.class).members()).withRel("members")
        );

        return members;
    }

    @GetMapping(path = "/{memberId}")
    public Mono<Member> member(@PathVariable Integer memberId) {
        return memberService.findById(memberId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus
    public Mono<Member> create(Member member) {
        return memberService.create(member);
    }


}
