package util;

import domain.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//缓存
public class UserFileReader {
    //一次性将数据读入
    private static HashMap<String, User> userBox = new HashMap<String,User>();
    static{
        //在当前类加载时就执行
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("src//dbfile//User.txt"));
            String user = bufferedReader.readLine();
            while (user!=null){
                String [] values = user.split("-");
                userBox.put(values[0],new User(values[0],values[1]));
                user = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //使用static 可以 直接类+方法名调用
    public static User getUser(String account){
       return userBox.get(account);
    }

}
