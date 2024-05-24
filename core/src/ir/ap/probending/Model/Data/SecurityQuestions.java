package ir.ap.probending.Model.Data;

import java.util.ArrayList;

public enum SecurityQuestions {
    QUESTION_0(""),
    QUESTION_1("What is you body count?"),
    QUESTION_2("What is your mom's name?"),
    QUESTION_3("When did you have your first kiss?"),
    QUESTION_4("What happened on June 4, 1989?"),
    QUESTION_5("What is your honest opinion on AP?");

    private final String Question;
    private static final ArrayList<String> questions = new ArrayList<String>(){
        {
            add(QUESTION_0.getQuestion());
            add(QUESTION_1.getQuestion());
            add(QUESTION_2.getQuestion());
            add(QUESTION_3.getQuestion());
            add(QUESTION_4.getQuestion());
            add(QUESTION_5.getQuestion());
        }
    };
    SecurityQuestions(String question){
        this.Question = question;
    }

    public String getQuestion() {
        return Question;
    }

    public static ArrayList<String> getQuestions() {
        return questions;
    }
}
