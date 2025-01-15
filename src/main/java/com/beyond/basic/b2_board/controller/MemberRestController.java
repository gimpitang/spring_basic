package com.beyond.basic.b2_board.controller;
//      restController를 사용하는 경우는 프론트와 백엔드가 따로 있는 경우
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberUpdateDto;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// 모든 메서드에 ResponseBody가 생김
@RestController
@RequestMapping("/member/rest")

public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //      회원목록조회
    @GetMapping("/list")
    public List<MemberListRes> memberList(){
        List<MemberListRes> memberListResList= memberService.findAll();
        return memberListResList;
    }

    //      상세조회
    @GetMapping ("/detail/{id}")
    public MemberDetailDto memberDetail(@PathVariable Long id) {

        MemberDetailDto dto = memberService.findById(id);
        return dto;
    }


    //      회원가입
    //      비밀번호 8자리 미만 + email 중복

    @PostMapping("/create")
    public String memberCreatePost (@RequestBody MemberCreateDto memberCreateDto){
            memberService.save(memberCreateDto);
            return "OK";


    }

//      비밀번호 변경
//      1.Controller : /member/rest/update/pw
//      2.Dto : email, pw 로 이루어진 json data
//      3. Service : email로 Member 찾기 -> Member member = repository.findByEmail(email);
//      member.updatePw(newPw);
//      repository.save(memebr); => 업데이트가 된다


    //      get: 조회, post: 등록, patch: 부분수정, put:대체, delete: 삭제
    //      axios.patch가 있어서 프론트와 따로 설정해야함.
    @PatchMapping("/update/pw")
    public String updatePw(@RequestBody MemberUpdateDto memberUpdateDto){
        memberService.updatePw(memberUpdateDto);
        return "change success";
    }

}
