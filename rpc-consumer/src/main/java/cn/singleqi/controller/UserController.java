package cn.singleqi.controller;

import cn.singleqi.entity.User;
import cn.singleqi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/{id:.+}", method = RequestMethod.GET)
    public User getById(@PathVariable String id) {
        log.info("根据ID查询用户信息:{}", id);
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        user = userService.insertUser(user);
        log.info("新增用户信息ID:{}", user.getId());
        return user;
    }

    @RequestMapping(path = "/{id:.+}", method = RequestMethod.PUT)
    public User update(@PathVariable String id, @RequestBody User user) {
        user = userService.updateUserById(id, user);
        log.info("修改用户信息ID:{}", user.getId());
        return user;
    }

    @RequestMapping(path = "/{id:.+}", method = RequestMethod.DELETE)
    public User delete(@PathVariable String id) {
        User user = userService.deleteUserById(id);
        log.info("删除用户信息ID:{}", user.getId());
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        log.info("获取所有用户信息");
        return userService.getAllUser();
    }
}
