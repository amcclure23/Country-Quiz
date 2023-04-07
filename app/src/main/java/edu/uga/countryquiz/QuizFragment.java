package edu.uga.countryquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    private int questionNum;
    private String title;
    private int page;
    TextView textView;
    private RadioGroup radioGroup;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private String answer;
    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(int page, String title) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("someInt", 0);
            title = getArguments().getString("someTitle");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        TextView label = (TextView) view.findViewById(R.id.label);
        label.setText(page + " -- " + title);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        //public void onActivityCreated(Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        setText(view);
        radioGroup = view.findViewById(R.id.groupradio);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                                              {
                                                  @Override

                                                  // Check which radio button has been clicked
                                                  public void onCheckedChanged(RadioGroup group,
                                                                               int checkedId)
                                                  {
                                                      // Get the selected Radio Button
                                                      RadioButton radioButtonPicked = (RadioButton)group.findViewById(checkedId);
                                                      answer =  radioButtonPicked.getText().toString();
                                                  }
                                              });
        //TextView highlightsView = view.findViewById( R.id.highlightsView );

       // titleView.setText( "hey bitch" );
       // highlightsView.setText( androidVersionsInfo[ versionNum ] );
    }

    private void setText(View view) {
        textView = view.findViewById( R.id.textView);
        button1  = view.findViewById( R.id.radioButton);
        button2  = view.findViewById( R.id.radioButton2);
        button3  = view.findViewById( R.id.radioButton3);
       // textView.setText(newQuiz.quest[page-1].questionWord);
       //button1 .setText(newQuiz.quest[page-1].correctAnswer);
       // button1 .setText(newQuiz.quest[page-1].wrongAnswer1);
      //  button1 .setText(newQuiz.quest[page-1].wrongAnswer2);
    }

    public static int getNumberOfVersions() {
        return 2;
    }
}
