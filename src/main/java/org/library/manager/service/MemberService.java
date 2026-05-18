package org.library.manager.service;

import org.library.manager.model.request.CreateMemberRequest;
import org.library.manager.model.request.UpdateMemberRequest;
import org.library.manager.model.response.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse createMember(CreateMemberRequest request);
    List<MemberResponse> getMember();
    MemberResponse getMemberByCode(String code);
    MemberResponse UpdateMember(String code, UpdateMemberRequest request);
    void deleteMember(String code);
}
