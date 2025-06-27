package vn.tayjava.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.tayjava.controller.Request.PostUserRequest;
import vn.tayjava.services.UserService;

import javax.swing.text.html.parser.Entity;

@RestController // hoặc @Controller nếu trả về view (Thymeleaf)
@RequestMapping("/api/users") // prefix đường dẫn cho tất cả các endpoint
@RequiredArgsConstructor // dùng Lombok để inject service
public class UserController {
    private final UserService userService;
    @PostMapping("")
    public ResponseEntity<String> getUsers(@RequestBody PostUserRequest request) {
        userService.CreaterUser(request);
        return ResponseEntity.ok("Create");
    }
}
