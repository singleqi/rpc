package cn.singleqi.service;

import cn.singleqi.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User insertUser(User user);

    User getUserById(String id);

    User deleteUserById(String id);

    User updateUserById(String id, User user);

    List<User> getAllUser();
}
