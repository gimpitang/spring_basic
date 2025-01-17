package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.PostCreateDto;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListRes;
import com.beyond.basic.b2_board.repository.MemberRepository;
import com.beyond.basic.b2_board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    @Autowired
    private final PostRepository postRepository ;
    @Autowired
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public void save(PostCreateDto postCreateDto) {
        Member member = memberRepository.findById(postCreateDto.getMemberId()).orElseThrow(()->new EntityNotFoundException("member is not found"));
        Post post = postCreateDto.toEntity(member);
        postRepository.save(post);


    }
    public List<PostListRes> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(p->p.toListDto()).collect(Collectors.toList());

    }

    public PostDetailDto findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 글이다"));
        // post에 member이 담겨있음
//        Member member = memberRepository.findById(post.getMemberId()).orElseThrow(()->new EntityNotFoundException("member is not found"));
//        return post.toDetailDto(member.getEmail());
        return post.toDetailDto(post.getMember().getEmail());
    }
}
