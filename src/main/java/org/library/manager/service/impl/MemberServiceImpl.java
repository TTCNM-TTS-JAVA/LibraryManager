package org.library.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.manager.entity.Member;
import org.library.manager.enums.Status;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.AuthorDto;
import org.library.manager.model.request.CreateMemberRequest;
import org.library.manager.model.request.UpdateMemberRequest;
import org.library.manager.model.response.MemberResponse;
import org.library.manager.repository.MemberRepository;
import org.library.manager.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<MemberResponse> filterMember(int size, int page, AuthorDto authorDto) {

        Pageable pageFormat = PageRequest.of(page -1,size);

        Page<Member> members = memberRepository.findByFilterMember
                        (authorDto.getSearch(),
                        authorDto.getStatus(),
                        pageFormat);

        return members.stream()
                .map(x -> MemberResponse.builder()
                        .id(x.getId())
                        .memberCode(x.getMemberCode())
                        .fullName(x.getFullName())
                        .email(x.getEmail())
                        .phoneNumber(x.getPhoneNumber())
                        .dob(x.getDob())
                        .address(x.getAddress())
                        .status(x.getStatus())
                        .createdAt(x.getCreatedAt())
                        .updatedAt(x.getUpdatedAt())
                        .build())
                .toList();
    }

    @Override
    public MemberResponse getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_EXISTED));
        return MemberResponse.builder()
                .id(member.getId())
                .memberCode(member.getMemberCode())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .dob(member.getDob())
                .address(member.getAddress())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    @Override
    public MemberResponse createMember(CreateMemberRequest request) {

        if (memberRepository.existsByMemberCode(request.getMemberCode())){
            throw new CustomException(ErrorCode.MEMBER_EXISTED);
        }

        Member member = Member.builder()
                .memberCode(request.getMemberCode())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .dob(request.getDob())
                .address(request.getAddress())
                .status(request.getStatus())
                .build();

        memberRepository.save(member);

        return MemberResponse.builder()
                .id(member.getId())
                .memberCode(member.getMemberCode())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .dob(member.getDob())
                .address(member.getAddress())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    @Override
    public MemberResponse updateMember(Long memberId, UpdateMemberRequest request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_EXISTED));

        if(memberRepository.existsByMemberCode(request.getMemberCode())
                && !member.getMemberCode().equals(request.getMemberCode())){
            throw new CustomException(ErrorCode.MEMBER_CODE_EXISTED);
        }

        member.setMemberCode(request.getMemberCode());
        member.setFullName(request.getFullName());
        member.setEmail(request.getEmail());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setAddress(request.getAddress());
        member.setDob(request.getDob());
        member.setStatus(request.getStatus());

        memberRepository.save(member);

        return MemberResponse.builder()
                .id(member.getId())
                .memberCode(member.getMemberCode())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .dob(member.getDob())
                .address(member.getAddress())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_EXISTED));

        member.setStatus(Status.INACTIVE);
        memberRepository.save(member);
    }
}
