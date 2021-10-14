package com.example.restservice.dto;

import com.example.restservice.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@Relation(value = "member", collectionRelation = "members")
public class MemberModel extends RepresentationModel<MemberModel> {

    private Integer memberId;
    private String name;
    private Integer age;

    public MemberModel(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.age = member.getAge();
    }
}
