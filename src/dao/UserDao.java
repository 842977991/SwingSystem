package dao;

import domain.User;
import util.UserFileReader;

//持久层
//数据的持久化
//只负责数据的读 写  不负责处理逻辑
public class UserDao {
        /*优化 可以加一个缓存
        * 因为每次读取  都需要读一遍文件，所以可以一次性将文件的数据读到缓存中
        * 放在util包中了
        * */
        public User selectOne(String account){
            return UserFileReader.getUser(account);
        }

   /*
  public User selectOne(String account){
      User user = null;
      BufferedReader bufferedReader = null;
      try {
          bufferedReader = new BufferedReader(new FileReader("src//dbfile//User.txt"));
          String meassage = bufferedReader.readLine();
          while (meassage!=null){
              String [] values = meassage.split("-");
              if(values[0].equals(account)){
                  user = new User(values[0],values[1]);
                  break;
              }
              meassage = bufferedReader.readLine();
          }
      } catch (Exception e) {
          e.printStackTrace();
      }finally {
              try {
                  if(bufferedReader!=null) {
                      bufferedReader.close();
                  }
              } catch (IOException e) {
                  e.printStackTrace();
              }
      }
      return user;
  }*/
}
