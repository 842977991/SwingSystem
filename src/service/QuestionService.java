package service;

import dao.QuestionDao;
import domain.Question;
import util.MySpring;

import java.util.ArrayList;

public class QuestionService {
    private QuestionDao questionDao = MySpring.getBean("dao.QuestionDao");
    public ArrayList<Question> getPaper(int count){
        return questionDao.getPaper(count);
    }
}
