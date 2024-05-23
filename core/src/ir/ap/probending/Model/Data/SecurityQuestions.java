package ir.ap.probending.Model.Data;

public enum SecurityQuestions {
    QUESTION_1("What is you body count?"),
    QUESTION_2("What is your mom's name?"),
    QUESTION_3("When did you have your first kiss?"),
    QUESTION_4("What happened on June 4, 1989?"),
    QUESTION_5("What is your honest opinion on AP?");

    private final String Question;
    private SecurityQuestions(String question){
        this.Question = question;
    }

    public String getQuestion() {
        return Question;
    }
}
