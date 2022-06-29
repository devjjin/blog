package com.dev.blog.controller.test;

import com.dev.blog.domain.Role;
import com.dev.blog.domain.User;
import com.dev.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiTestController {

    @Autowired
    UserRepository userRepository;

    // 회원목록 조회 api
    @GetMapping("/user/list")
    public List<User> userList() {
        return userRepository.findAll();
    }

    // 회원목록 페이징 조회 api
    @GetMapping("/user/list/page")
    public List<User> pageUserList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    // 회원 단건 조회 api
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });
        return user;
    }

    // 회원 등록 api
    @PostMapping("/user/join")
    public String join( User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        user.setRole(Role.USER);

        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

    // 회원 수정 api
    @Transactional
    @PutMapping("/user/{id}")
    public User update(@PathVariable Long id, @RequestBody User requestUser) {
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setUsername(requestUser.getUsername());
        user.setPassword(requestUser.getPassword());
        userRepository.save(user);

        return user;
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            return "회원 삭제에 실패하였습니다. 해당 id는 존재하지 않습니다.";
        }
        return "회원이 삭제되었습니다 id= " + id;
    }
}
