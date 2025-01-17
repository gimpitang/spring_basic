package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.*;
import com.beyond.basic.b2_board.service.MemberService;
import com.beyond.basic.b2_board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post/rest")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    //      Valid(컨트롤러)와 NotEmpty(DTO) 등 검증 어노테이션이 한쌍.
    public ResponseEntity<?> postCreate (@Valid @RequestBody PostCreateDto dto){
        postService.save(dto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(), "register success", "OK"), HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<?> postList() {
        List<PostListRes> postListResList= postService.findAll();

        return new ResponseEntity<>
                (new CommonDto(HttpStatus.OK.value(),"This is postList",postListResList),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetail(@PathVariable Long id) {
        PostDetailDto postDetailDto = postService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "memberDetail is successfully found", postDetailDto), HttpStatus.OK);

    }


}
