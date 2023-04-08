package edu.uga.countryquiz;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuestionPagerAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS = 8;
    private DatabaseHelper db;
    String[] question1, question2, question3, question4, question5, question6;

    public QuestionPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle, Context context ) {
        super( fragmentManager, lifecycle );
        db = new DatabaseHelper(context);
        fillQuestions();
        db.close();
    }

    private void fillQuestions() {
        question1 = db.getCountry();
        checkQuestion(question1);
        question2 = db.getCountry();
        checkQuestion(question2);
        question3 = db.getCountry();
        checkQuestion(question3);
        question4 = db.getCountry();
        checkQuestion(question4);
        question5 = db.getCountry();
        checkQuestion(question5);
        question6 = db.getCountry();
        checkQuestion(question6);
    }

    private void checkQuestion(String[] question) {
        int count = 0;
        if (question == question1) {
            count++;
        } else if (question == question2) {
            count++;
        } else if (question == question3) {
            count++;
        } else if (question == question4) {
            count++;
        } else if (question == question5) {
            count++;
        } else if (question == question6) {
            count++;
        }

        if (count > 1) {
            fillQuestions();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return QuizFragment.newInstance(position, "quiz", question1[0], question1[1]);
            case 1:
                return QuizFragment.newInstance(position, "quiz", question2[0], question2[1]);
            case 2:
                return QuizFragment.newInstance(position, "quiz", question3[0], question3[1]);
            case 3:
                return QuizFragment.newInstance(position, "quiz", question4[0], question4[1]);
            case 4:
                return QuizFragment.newInstance(position, "quiz", question5[0], question5[1]);
            case 5:
                return QuizFragment.newInstance(position, "quiz", question6[0], question6[1]);
            case 6:
            case 7:
                return ScoreFragment.newInstance(position, "results");
            default:
                return null;
        }
    }

}
