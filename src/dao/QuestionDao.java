package dao;

import domain.Question;
import util.MySpring;
import util.QuestionFileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

//用来获取题目
public class QuestionDao {
    //获取缓存对象
    private QuestionFileReader questionFileReader = MySpring.getBean("util.QuestionFileReader");
    //将缓存的对象 存入 可以通过索引随机生成题目
    private ArrayList<Question> questionBank = new ArrayList(questionFileReader.getQuestionBox());

    //生成试卷,试卷题目需要适合遍历，所以用Arraylist
    public ArrayList<Question> getPaper(int count){
        HashSet<Question> paper = new HashSet<>();//用来存储最终的试卷题目
        while (paper.size()!=count) {
            Random random = new Random();
            int index = random.nextInt(this.questionBank.size());//随机产生的一个题目索引位置
            paper.add(questionBank.get(index));
        }
        return new ArrayList<Question>(paper);
    }

}
