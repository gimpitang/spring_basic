package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

@Controller
@RequestMapping("/member/rest")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;





    //      홈화면


    //      회원 목록 조회
    @GetMapping("/list")
    public String memberList(Model model){
        List<MemberListRes> memberListResList= memberService.findAll();
        model.addAttribute("memberList",memberListResList);
        return "/member/member-list";
    }


    @GetMapping ("/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model){
        try {
            MemberDetailDto dto = memberService.findById(id);
            model.addAttribute("member",dto);
            return "/member/member-detail";
        }catch (NoSuchElementException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/member-error";
        }
    }

    //      회원가입
    //      비밀번호 8자리 미만 + email 중복
    @GetMapping("/create")
    public String memberCreate(){
        return "member/member-create";
    }

    @PostMapping("/create")
    public String memberCreatePost (MemberCreateDto memberCreateDto, Model model){
        try {
            memberService.save(memberCreateDto);
            return "redirect:/member/list";
        }catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/member-error";
        }catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/member-error";
        }
    }

}
