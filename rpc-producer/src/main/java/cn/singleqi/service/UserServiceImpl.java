package cn.singleqi.service;

import cn.singleqi.annotation.RpcService;
import cn.singleqi.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RpcService
public class UserServiceImpl implements UserService {

    Map<String, User> userMap = new HashMap<>();

    @Override
    public User insertUser(User user) {
        log.info("insert user info:{}", user);
        if (user != null) {
            userMap.put(user.getId(), user);
            return userMap.get(user.getId());
        }
        return null;
    }

    @Override
    public User getUserById(String id) {
        log.info("get user id:{}", id);
        return userMap.get(id);
    }

    @Override
    public User deleteUserById(String id) {
        log.info("delete user id:{}", id);
        return userMap.remove(id);
    }

    @Override
    public User updateUserById(String id, User user) {
        log.info("update user id:{},info:{}", id, user);
        userMap.put(id, user);
        return userMap.get(id);
    }

    @Override
    public List<User> getAllUser() {
        log.info("get all user info size:{}", userMap.size());
        return new ArrayList<>(userMap.values());
    }
}
