package edu.uga.countryquiz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import static android.content.Context.MODE_PRIVATE;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.Random;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Creates the instance of the quiz fragment
 * displays the quiz question and allows user to answer the question
 * via radiogroup
 */
public class QuizFragment extends Fragment {

    public String[] continents = new String[]{"Africa","Antarctica", "Asia", "Oceania", "Europe", "North America", "South America"};
    private int page;
    TextView textView;
    private RadioGroup radioGroup;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private int answer = 0;
    private String questionWord;
    private String correctAnswer;
    Activity context;

    public interface OnDataPass {
        public void onDataPass(String data);
    }
    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(int page, String title, String questionWord, String correctAnswer) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        args.putString("questionWord", questionWord);
        args.putString("correctAnswer", correctAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("someInt", 0);
            questionWord = getArguments().getString("questionWord", questionWord);
            correctAnswer= getArguments().getString("correctAnswer", correctAnswer);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        TextView label = (TextView) view.findViewById(R.id.label);
        String questionNum = "Question " + (page+1);
        label.setText(questionNum);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        //public void onActivityCreated(Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setText(view);
        radioGroup = view.findViewById(R.id.groupradio);
        if (radioGroup != null) {
            radioGroup.clearCheck();
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override

                // Check which radio button has been clicked
                public void onCheckedChanged(RadioGroup group,
                                             int checkedId) {
                    // Get the selected Radio Button
                    RadioButton radioButtonPicked = (RadioButton) group.findViewById(checkedId);
                    if (correctAnswer.equals(radioButtonPicked.getText().toString())) {
                        answer = 1;
                    }
                }
            });
        }
    }

    //randomizes the answers
    private void setText(View view) {
        textView = view.findViewById( R.id.textView);
        button1  = view.findViewById( R.id.radioButton);
        button2  = view.findViewById( R.id.radioButton2);
        button3  = view.findViewById( R.id.radioButton3);
        String question = "Which continent is " + questionWord + " located in?";
        textView.setText(question);
        //getting non-matching continents for the answers.
        Random rand = new Random();
        String[] answers = new String[3];
        answers[0]=correctAnswer;
        int int_random = rand.nextInt(7);

        while(correctAnswer.equals(continents[int_random])){
            int_random = rand.nextInt(7);
        }
        answers[1]=continents[int_random];
        while(correctAnswer.equals(continents[int_random])||answers[1].equals(continents[int_random])){
            int_random = rand.nextInt(7);
        }
        answers[2]=continents[int_random];

        //displaying the answers randomly
        int_random = rand.nextInt(3);
        button1 .setText(answers[int_random]);
        if(int_random == 2)
        {
            int_random = 0;
        }
        else
        {
            int_random++;
        }
        button2 .setText(answers[int_random]);
        if(int_random == 2)
        {
            int_random = 0;
        }
        else
        {
            int_random++;
        }
        button3 .setText(answers[int_random]);
    }

}
