package edu.uga.countryquiz;

public class questions {
    int questionID;
    String questionWord;
    String correctAnswer;
    String wrongAnswer1;
    String wrongAnswer2;

    /* this creates a question object
    * param int
    * param string
    * param string
    * param string
    * param string
    */
    questions(int questionID, String questionWord, String correctAnswer, String wrongAnswer1, String wrongAnswer2)
    {
        this.questionID    = questionID;
        this.correctAnswer = correctAnswer;
        this.questionWord  = questionWord;
        this.wrongAnswer1  = wrongAnswer1;
        this.wrongAnswer2  = wrongAnswer2;
    }
}
