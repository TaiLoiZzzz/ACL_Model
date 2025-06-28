package vn.tayjava.services.lmpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.tayjava.controller.Request.PostUserRequest;
import vn.tayjava.model.User;
import vn.tayjava.repository.UserRepository;
import vn.tayjava.services.UserService;

@Service
@Slf4j
@AllArgsConstructor
public class UserServicelmpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String CreaterUser(PostUserRequest request) {
        return "";
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findUserById(long id, Pageable pageable) {
        return userRepository.findAllById(id, pageable);
    }
}
