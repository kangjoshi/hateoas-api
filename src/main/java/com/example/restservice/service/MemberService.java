package com.example.restservice.service;

import com.example.restservice.domain.Member;
import com.example.restservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Flux<Member> findAll() {
        return memberRepository.findAll();
    }

    public Mono<Member> findById(Integer memberId) {
        return memberRepository.findById(memberId);
    }
}
