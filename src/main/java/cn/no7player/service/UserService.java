package cn.no7player.service;

import cn.no7player.mapper.UserInfoMapper;
import cn.no7player.mapper.UserMapper;
import cn.no7player.model.User;
import cn.no7player.model.UserInfo;
import cn.no7player.model.UserInfoExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {

    private Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    public User getUser() {
        User user = userMapper.findUserInfo();
        //User user=null;
        return user;
    }

    public List<UserInfo> getUserInfo(String id) {
        UserInfoExample example = new UserInfoExample();
        if (id != null) {
            example.createCriteria().andIdEqualTo(Integer.valueOf(id));
        }
        List<UserInfo> userList = userInfoMapper.selectByExample(example);
        return userList;
    }


    public int addUserInfo(UserInfo userInfo) {
        int flag = 0;
        try {
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());
            UserInfoExample example = new UserInfoExample();
            example.createCriteria().andNameEqualTo(userInfo.getName());
            List<UserInfo> userList = userInfoMapper.selectByExample(example);
            if (userList != null && userList.size() > 0) {
                flag = 2;//存在该用户名则不能添加
                return flag;
            }
            flag = userInfoMapper.insertSelective(userInfo);
        } catch (Exception e) {
            logger.error(e);
        }
        return flag;
    }

    public int updateUserInfo(UserInfo userInfo) {
        int flag = 0;
        try {
            List<UserInfo> userList = userInfoMapper.selectUserByName(userInfo);
            if (userList != null && userList.size() > 0) {
                flag = 2;//该用户名已存在
            } else {
                flag = userInfoMapper.updateByPrimaryKeySelective(userInfo);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return flag;
    }

    public int deleteUserInfo(int id) {
        int flag = 0;
        try {
            flag = userInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e);
        }
        return flag;
    }

    public List selectUserBycreateTime() {
        List userInfoList = userInfoMapper.selectUserByCreateTime();
        return userInfoList;
    }
}
