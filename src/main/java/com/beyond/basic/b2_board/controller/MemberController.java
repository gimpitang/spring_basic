//package com.beyond.basic.b2_board.controller;
//
//
//import com.beyond.basic.b2_board.dtos.MemberCreateDto;
//import com.beyond.basic.b2_board.dtos.MemberListRes;
//import com.beyond.basic.b2_board.dtos.MemberDetailDto;
//import com.beyond.basic.b2_board.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import java.util.*;
//
//@Controller
//@RequestMapping("/member")
//@RequiredArgsConstructor
//public class MemberController {
////    //      의존성 주입(DI) 방법 1. Autowired 어노테이션 사용 : 필드 주입.
////    @Autowired
////    private MemberService memberService;
//
//    //      의존성 주입(DI) 방법 2. 생성자 주입 방식(가장 많이 사용하는 방식).
//    //      장점1. final을 통해 상수로 사용 가능.(안정성 향상)
//    //      장점2. 다형성 구현 가능(인터페이스 사용가능)
//    //      장점3. 순환참조를 컴파일 시점에서 걸러낼 수 있다.
////    private final MemberService memberService;
////    //      싱글톤 객체로 만들어지는 시점에 아래 생성자가 호출된다. 생성자가 하나밖에 없을 때에는 Autowired 생략가능.
//////    @Autowired
////
////    public MemberController(MemberService memberService){//     Autowired가 주입 받는 부분
////        this.memberService = memberService;
////    }
//
//
//    //      의존성 주입(DI) 방법 3. RequiredArgs 어노테이션 사용방법
//    //      RequiredArgs : 반드시 초기화 되어야하는 필드(final 키워드 붙어있는 것, @Nonnull 어노테이션)를 대상으로 생성자를 자동으로 만들어주는 어노테이션.
//    //  이건 맨 윗줄쯤에 있음
//    private final MemberService memberService;
//
//
//
//
//
//    //      홈화면
//    @GetMapping("")
//    public String memberHome(){
//        return "/member/home";
//    }
//
//
//    //      회원 목록 조회
//    @GetMapping("/list")
//    public String memberList(Model model){ //여기에 있는 memberList이거가 +2 줄의 "memberList"이게 된거임
//        List<MemberListRes> memberListResList= memberService.findAll(); //여기에 있는 memberListResList가 밑의 memberListResList
//        model.addAttribute("memberList",memberListResList);
//        return "/member/member-list";
//    }
//
//    //      회원 상세조회
//    //      pathvariable로 id값 받아온다
//
//    //1/14수업 시작. detail+create 수정함.
//    @GetMapping ("/detail/{id}")
//    public String memberDetail(@PathVariable Long id, Model model){
//        //      name, email, password
//        try {
//            MemberDetailDto dto = memberService.findById(id);
//            model.addAttribute("member",dto);
//            return "/member/member-detail";
//        }catch (NoSuchElementException e){
//            model.addAttribute("errorMessage", e.getMessage());
//            return "member/member-error";
//        }
//    }
//
//    //      회원가입
//    //      비밀번호 8자리 미만 + email 중복
//    @GetMapping("/create")
//    public String memberCreate(){
//        return "member/member-create";
//    }
//
//    @PostMapping("/create")
//    public String memberCreatePost (MemberCreateDto memberCreateDto, Model model){
//        try {
//            //      서비스에게 객체를 넘기게 한다.
//            memberService.save(memberCreateDto);
////        화면 리턴이 아닌 url 재호출을 통해 redirect
//            return "redirect:/member/list";
//        }catch (IllegalArgumentException e){
//            model.addAttribute("errorMessage", e.getMessage());
//            return "/member/member-error";
//        }catch (RuntimeException e){
//            model.addAttribute("errorMessage", e.getMessage());
//            return "/member/member-error";
//        }
//    }
//
//}
