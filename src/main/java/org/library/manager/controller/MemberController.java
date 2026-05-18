package org.library.manager.controller;

import jakarta.validation.Valid;
import org.library.manager.model.request.CreateMemberRequest;
import org.library.manager.model.request.UpdateMemberRequest;
import org.library.manager.model.response.ApiResponse;
import org.library.manager.model.response.MemberResponse;
import org.library.manager.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    ApiResponse<MemberResponse> createMember(@RequestBody @Valid CreateMemberRequest request){
        return ApiResponse.<MemberResponse>builder()
                .result(memberService.createMember(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<MemberResponse>> getMember(){
        return ApiResponse.<List<MemberResponse>>builder()
                .result(memberService.getMember())
                .build();
    }

    @GetMapping("/{memberCode}")
    ApiResponse<MemberResponse> getMemberByMemberCode(@PathVariable String memberCode){
        return ApiResponse.<MemberResponse>builder()
                .result(memberService.getMemberByCode(memberCode))
                .build();
    }

    @PutMapping("/{memberCode}")
    ApiResponse<MemberResponse> UpdateMember(@PathVariable String memberCode,@RequestBody UpdateMemberRequest request){
        return ApiResponse.<MemberResponse>builder()
                .result(memberService.UpdateMember(memberCode,request))
                .build();
    }

    @DeleteMapping("/{memberCode}")
    ApiResponse<String> deleteMember(@PathVariable String memberCode){
        memberService.deleteMember(memberCode);
        return ApiResponse.<String>builder()
                .result( "member has been delete")
                .build();
    }
}
