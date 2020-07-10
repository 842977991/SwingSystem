package util;

import domain.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

//缓存题目
public class QuestionFileReader {
    //无重复的读取题目
    private HashSet<Question> questionBox = new HashSet();
    {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("src//dbfile//Question.txt"));
            String message = bufferedReader.readLine();
            while (message!= null){
                String [] values = message.split("#");
                if(values.length==2) {
                    questionBox.add(new Question(values[0], values[1]));
                }else if(values.length==3){
                    questionBox.add(new Question(values[0],values[1],values[2]));
                }
                message = bufferedReader.readLine();
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

    //获取缓存
    public HashSet<Question> getQuestionBox(){
        return questionBox;
    }
}
