package com.example.restservice.service;

import com.example.restservice.domain.Member;
import com.example.restservice.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MemberServiceTest {

    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void findAll() {
        Member member1 = new Member(1, "memberA", 19);
        Member member2 = new Member(2, "memberB", 21);
        Member member3 = new Member(3, "memberC", 18);
        List<Member> members = Arrays.asList(member1, member2, member3);
        given(memberRepository.findAll()).willReturn(Flux.fromIterable(members));

        StepVerifier.create(memberService.findAll())
            .expectSubscription()
            .expectNextMatches(member -> {
                assertThat(member).isInstanceOf(Member.class);
                assertThat(member).isEqualTo(member1);
                return true;
            })
            .expectNextCount(members.size() - 1)
            .expectComplete();

        verify(memberRepository, only()).findAll();
    }

    @Test
    void findById() {
        Member member = new Member(1, "memberA", 19);
        given(memberRepository.findById(member.getMemberId())).willReturn(Mono.just(member));

        StepVerifier.create(memberService.findById(member.getMemberId()))
            .expectSubscription()
            .expectNextMatches(result -> {
                assertThat(result).isInstanceOf(Member.class);
                assertThat(result).isEqualTo(member);
                return true;
            })
            .expectComplete();

        verify(memberRepository, only()).findById(member.getMemberId());
    }

    @Test
    void create() {
        Member member = new Member(1, "memberA", 19);
        given(memberRepository.save(member)).willReturn(Mono.just(member));

        StepVerifier.create(memberService.create(member))
            .expectSubscription()
            .expectNextMatches(result -> {
                assertThat(result).isInstanceOf(Member.class);
                assertThat(result).isEqualTo(member);
                return true;
            })
            .expectComplete();

        verify(memberRepository, only()).save(member);
    }
}