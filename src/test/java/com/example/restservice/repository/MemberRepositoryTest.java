package com.example.restservice.repository;

import com.example.restservice.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemberRepository();
    }

    @Test
    void findAll() {

        StepVerifier.create(memberRepository.findAll())
                .expectSubscription()
                .expectNextMatches(member -> {

                    assertNotNull(member);
                    assertTrue(member instanceof Member);

                    return true;
                })
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void findById() {
        Integer memberId = 1;

        StepVerifier.create(memberRepository.findById(memberId))
                .expectSubscription()
                .expectNextMatches(member -> {

                    assertNotNull(member);
                    assertTrue(member instanceof Member);

                    assertEquals(memberId, member.getMemberId());

                    return true;
                })
                .verifyComplete();


    }

    @Test
    void findById2() {
        Integer memberId = 99;

        StepVerifier.create(memberRepository.findById(memberId))
                .expectSubscription()
                .verifyComplete();


    }
}