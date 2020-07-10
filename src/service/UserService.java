package service;
//业务层 负责处理读到的数据

import dao.UserDao;
import domain.User;
import util.MySpring;

//控制登录
public class UserService {
    private UserDao userDao =  MySpring.getBean("dao.UserDao");

    public String LoginText(String account,String password){
       User user = userDao.selectOne(account);
       if(user!=null){
           if(user.getPassword().equals(password)){
               return "登录成功";
           }
       }
       return "账号或用户名错误";
    }
}
