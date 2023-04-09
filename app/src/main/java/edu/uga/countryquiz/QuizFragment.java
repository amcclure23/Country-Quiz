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

    /**
     * constructor
     */
    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * creates new instance of the quizfragment
     * @param page
     * @param title
     * @param questionWord
     * @param correctAnswer
     * @return
     */
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

    /**
     * initializes variables from arguments
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("someInt", 0);
            questionWord = getArguments().getString("questionWord", questionWord);
            correctAnswer= getArguments().getString("correctAnswer", correctAnswer);
        }
    }

    /**
     * view inflation
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        TextView label = (TextView) view.findViewById(R.id.label);
        String questionNum = "Question " + (page+1);
        label.setText(questionNum);
        return view;
    }

    /**
     * creates functionality of the objects in the layout
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
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

    /**
     * randomizes the answers and sets the textviews
     * @param view
     */
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
        button1 .setText("1. "+answers[int_random]);
        if(int_random == 2)
        {
            int_random = 0;
        }
        else
        {
            int_random++;
        }
        button2 .setText("2. "+answers[int_random]);
        if(int_random == 2)
        {
            int_random = 0;
        }
        else
        {
            int_random++;
        }
        button3 .setText("3. "+answers[int_random]);
    }

}
