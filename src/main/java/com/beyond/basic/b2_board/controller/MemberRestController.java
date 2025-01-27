package com.beyond.basic.b2_board.controller;
//      restController를 사용하는 경우는 프론트와 백엔드가 따로 있는 경우
import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.*;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

// 모든 메서드에 ResponseBody가 생김
//  Controller + ResponseBody가 모든 메서드에 생김
@RestController
@RequestMapping("/member/rest")

public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //      회원목록조회
    @GetMapping("/list")
    public ResponseEntity<?> memberList(){
        List<MemberListRes> memberListResList= memberService.findAll();
        return new ResponseEntity<>
                (new CommonDto(HttpStatus.OK.value(), "memberList is found", memberListResList), HttpStatus.OK);
    }

    //      상세조회
    @GetMapping ("/detail/{id}")
    public ResponseEntity<?> memberDetail(@PathVariable Long id) {
//        try {
            MemberDetailDto dto = memberService.findById(id);
            return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "memberDetail is successfully found", dto), HttpStatus.OK);
//        }catch (EntityNotFoundException e){
//            return new ResponseEntity<>(new CommonErrorDto(HttpStatus.NOT_FOUND.value(),e.getMessage()),HttpStatus.NOT_FOUND);
//
//        }
    }


    //      회원가입
    //      비밀번호 8자리 미만 + email 중복

    @PostMapping("/create")
    public ResponseEntity<?> memberCreatePost (@RequestBody MemberCreateDto memberCreateDto){
//        try {
            Member member = memberService.save2(memberCreateDto);
            return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(), "register success", member.getId()), HttpStatus.CREATED);
//        }catch (IllegalArgumentException e){
//            return new ResponseEntity<>(new CommonErrorDto(HttpStatus.BAD_REQUEST.value(),e.getMessage()),HttpStatus.BAD_REQUEST);
//        }
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
    public ResponseEntity<?> updatePw(@RequestBody MemberUpdateDto memberUpdateDto) {
//        try {
            memberService.updatePw(memberUpdateDto);
            return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "password is successfully changed", memberUpdateDto), HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(new CommonErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
//        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember (@PathVariable Long id){
//        try{
             memberService.delete(id);
            return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "password is successfully changed", id), HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//        return new ResponseEntity<>(new CommonErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
//        }
    }

}
