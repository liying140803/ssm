package cn.no7player.controller;

import cn.no7player.model.UserInfo;
import cn.no7player.service.RedisService;
import cn.no7player.service.UserService;
import cn.no7player.util.CommonResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ly on 2019/8/27.
 */
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    //获取全部用户信息
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UserInfo>> getUserInfo(String id) {
        List<UserInfo> userList = userService.getUserInfo(id);
        if (userList == null || userList.isEmpty()) {
            logger.info("查询结果是空");
        }
        redisService.set("userList", userList);
        logger.info("插入redis成功");
        return CommonResult.success(userList);
    }

    //添加用户
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addUserInfo(@RequestBody UserInfo userInfo) {
        int flag = userService.addUserInfo(userInfo);
        if (flag == 1) {
            return CommonResult.success("添加成功");
        } else if (flag == 2) {
            logger.info("该用户已存在，用户名是：" + userInfo.getName());
            return CommonResult.failed("该用户已存在");

        } else {
            logger.info("添加失败");
            return CommonResult.failed("添加失败");
        }

    }

    //编辑用户
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUserInfo(@RequestBody UserInfo userInfo) {
        if (userInfo.getId() == null) {
            return CommonResult.failed("用户id不能为空");
        }
        int flag = userService.updateUserInfo(userInfo);
        if (flag == 1) {
            return CommonResult.success("编辑成功");
        } else if (flag == 2) {
            logger.info("该用户已存在，用户名是：" + userInfo.getName());
            return CommonResult.failed("该用户已存在");

        } else {
            logger.info("编辑失败");
            return CommonResult.failed("编辑失败");
        }

    }


    //删除用户
    @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteUserInfo(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
        Integer userId = (Integer) jsonParam.get("userId");
        if (userId == null) {
            return CommonResult.failed("用户id不能为空");
        }
        int flag = userService.deleteUserInfo(userId);
        if (flag > 0) {
            return CommonResult.success("删除成功");
        } else {
            logger.info("删除失败");
            return CommonResult.failed("删除失败");
        }
    }

    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List> selectUser() {
        List userList = userService.selectUserBycreateTime();
        if (userList != null) {
            logger.info("user:" + userList);
        }
        return CommonResult.success(userList);
    }

}
