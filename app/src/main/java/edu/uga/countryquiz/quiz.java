package edu.uga.countryquiz;
import java.text.SimpleDateFormat;
import java.util.Date;

public class quiz {
    int quizID;
    String date;
    int currentScore = 0;
    int currentQuestion = 0;
    questions quest[] = new questions[6];

    quiz(int idNum){
        //creates the quiz date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.date = formatter.format(date);

        //initializes quizID
        this.quizID =idNum;

    }



}
