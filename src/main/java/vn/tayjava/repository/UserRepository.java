package vn.tayjava.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vn.tayjava.controller.Request.PostUserRequest;
import vn.tayjava.model.UserEntity;
import vn.tayjava.services.UserService;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Service
    @Slf4j
    @AllArgsConstructor
    class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
        @Override
        public String CreaterUser(PostUserRequest req ) {
            UserEntity user = new UserEntity();
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());
            user.setDateOfBirth(req.getDateOfBirth());
            user.setPhone(req.getPhone());
            user.setGender(req.getGender());
            user.setEmail(req.getEmail());
            user.setUsername(req.getUsername());
            user.setPassword(req.getPassword());
            user.setStatus(req.getStatus());
            user.setType(req.getType());
            userRepository.save(user);

            return  "200";
        }
    }
}
