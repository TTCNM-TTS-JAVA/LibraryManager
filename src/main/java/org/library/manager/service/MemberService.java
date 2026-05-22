package org.library.manager.service;

import org.library.manager.model.dto.AuthorDto;
import org.library.manager.model.request.CreateMemberRequest;
import org.library.manager.model.request.UpdateMemberRequest;
import org.library.manager.model.response.MemberResponse;

import java.util.List;

public interface MemberService {
    List<MemberResponse> filterMember(int size, int page, AuthorDto authorDto);
    MemberResponse getMemberById(Long memberId);
    MemberResponse createMember(CreateMemberRequest request);
    MemberResponse updateMember(Long memberId, UpdateMemberRequest request);
    void deleteMember(Long memberId);
}
