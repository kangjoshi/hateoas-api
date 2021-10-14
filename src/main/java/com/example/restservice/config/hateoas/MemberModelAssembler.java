package com.example.restservice.config.hateoas;

import com.example.restservice.controller.MemberController;
import com.example.restservice.domain.Member;
import com.example.restservice.dto.MemberModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MemberModelAssembler extends RepresentationModelAssemblerSupport<Member, MemberModel> {

    public MemberModelAssembler() {
        super(MemberController.class, MemberModel.class);
    }

    @Override
    public MemberModel toModel(Member member) {
        MemberModel model = createModelWithId(member.getMemberId(), member);

        model.setMemberId(member.getMemberId());
        model.setName(member.getName());
        model.setAge(member.getAge());

        return model;
    }


}
