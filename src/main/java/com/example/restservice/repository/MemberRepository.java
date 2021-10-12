package com.example.restservice.repository;

import com.example.restservice.domain.Member;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {

    Map<Integer, Member> memberMap = new ConcurrentHashMap<>();

    {
        Member member1 = new Member(1, "memberA", 19);
        Member member2 = new Member(2, "memberB", 21);
        Member member3 = new Member(3, "memberC", 18);
        Member member4 = new Member(4, "memberD", 32);
        Member member5 = new Member(5, "memberE", 27);

        Arrays.asList(member1, member2, member3, member4, member5)
                .stream()
                .forEach(member -> memberMap.put(member.getMemberId(), member));
    }


    public Flux<Member> findAll() {
        return Flux.fromIterable(memberMap.values());
    }


    public Mono<Member> findById(Integer memberId) {
        return Mono.justOrEmpty(memberMap.get(memberId));
    }
}
