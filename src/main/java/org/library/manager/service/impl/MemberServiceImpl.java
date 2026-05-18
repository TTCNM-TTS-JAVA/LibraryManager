package org.library.manager.service.impl;

import org.library.manager.entity.Member;
import org.library.manager.enums.ErrorCode;
import org.library.manager.enums.Status;
import org.library.manager.exception.CustomException;
import org.library.manager.model.request.CreateMemberRequest;
import org.library.manager.model.request.UpdateMemberRequest;
import org.library.manager.model.response.MemberResponse;
import org.library.manager.repository.MemberRepository;
import org.library.manager.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberResponse createMember(CreateMemberRequest request) {
        if(memberRepository.existsByCode(request.getCode())){
            throw new CustomException(ErrorCode.MEMBER_EXISTED);
        }
        Member member = Member.builder()
                .code(request.getCode())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .dateOfBirth(request.getDateOfBirth())
                .address(request.getAddress())
                .status(request.getStatus())
                .inactiveReason(request.getInactiveReason())
                .build();

        memberRepository.save(member);

        return MemberResponse.builder()
                .id(member.getId())
                .code(member.getCode())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .dateOfBirth(member.getDateOfBirth())
                .address(member.getAddress())
                .status(member.getStatus())
                .inactiveReason(member.getInactiveReason())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();

    }

    @Override
    public List<MemberResponse> getMember() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(member -> MemberResponse.builder()
                                .id(member.getId())
                                .code(member.getCode())
                                .fullName(member.getFullName())
                                .email(member.getEmail())
                                .phone(member.getPhone())
                                .dateOfBirth(member.getDateOfBirth())
                                .address(member.getAddress())
                                .status(member.getStatus())
                                .inactiveReason(member.getInactiveReason())
                                .createdAt(member.getCreatedAt())
                                .updatedAt(member.getUpdatedAt())
                                .build()
                        )
                .toList();
    }

    @Override
    public MemberResponse getMemberByCode(String code) {
        Member member = memberRepository.findByCode(code)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXISTED));

        return MemberResponse.builder()
                .id(member.getId())
                .code(member.getCode())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .dateOfBirth(member.getDateOfBirth())
                .address(member.getAddress())
                .status(member.getStatus())
                .inactiveReason(member.getInactiveReason())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    @Override
    public MemberResponse UpdateMember(String code, UpdateMemberRequest request) {
        Member member = memberRepository.findByCode(code)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_EXISTED));

        member.setCode(request.getCode());
        member.setFullName(request.getFullName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setDateOfBirth(request.getDateOfBirth());
        member.setAddress(request.getAddress());
        member.setStatus(request.getStatus());
        member.setInactiveReason(request.getInactiveReason());

        memberRepository.save(member);

        return MemberResponse.builder()
                .id(member.getId())
                .code(member.getCode())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .dateOfBirth(member.getDateOfBirth())
                .address(member.getAddress())
                .status(member.getStatus())
                .inactiveReason(member.getInactiveReason())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteMember(String code) {
        Member authors = memberRepository.findByCode(code).orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_EXISTED));
        authors.setStatus(Status.INACTIVE);
        memberRepository.save(authors);
    }
}
