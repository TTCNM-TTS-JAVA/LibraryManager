package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.model.dto.AuthorDto;
import org.library.manager.model.request.CreateMemberRequest;
import org.library.manager.model.request.UpdateMemberRequest;
import org.library.manager.model.response.MemberResponse;
import org.library.manager.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/filterMember")
    public ResponseEntity<List<MemberResponse>> filterMember(@RequestParam int size , @RequestParam int page, @RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(memberService.filterMember(size,page, authorDto));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long memberId){
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    @PostMapping("/create")
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Valid CreateMemberRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberService.createMember(request));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponse> UpdateMember(@PathVariable Long memberId, @RequestBody @Valid UpdateMemberRequest request){
        return ResponseEntity.ok(memberService.updateMember(memberId,request));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
