package vn.tayjava.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.tayjava.controller.Request.PostUserRequest;
import vn.tayjava.model.User;

public interface UserService {
     public String CreaterUser(PostUserRequest request);

     //get All by limit offset
     Page<User> findAllUsers(Pageable pageable);
     //get search by limit offset
     Page<User> findUserById (long id, Pageable pageable);
}
