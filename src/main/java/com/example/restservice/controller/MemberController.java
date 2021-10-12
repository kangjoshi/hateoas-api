package com.example.restservice.controller;

import com.example.restservice.domain.Member;
import com.example.restservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/member", produces = APPLICATION_JSON_VALUE)
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public Flux<Member> members() {
        return memberService.findAll();
    }

    @GetMapping(path = "/{memberId}")
    public Mono<Member> member(@PathVariable Integer memberId) {
        return memberService.findById(memberId);
    }



}
