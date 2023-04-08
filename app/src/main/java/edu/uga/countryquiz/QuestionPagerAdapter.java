package edu.uga.countryquiz;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class QuestionPagerAdapter extends FragmentStateAdapter  {
    private static int NUM_ITEMS = 8;
    String[] question1, question2, question3, question4, question5, question6;
    List<String[]> countries;
    public QuestionPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle, List<String[]> countries) {
        super( fragmentManager, lifecycle );
        question1 = countries.get(0);
        question2 = countries.get(1);
        question3 = countries.get(2);
        question4 = countries.get(3);
        question5 = countries.get(4);
        question6 = countries.get(5);
        this. countries= countries;
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
                return ScoreFragment.newInstance(9);
            case 7 :
                return HistoryFragment.newInstance();
            default:
                return null;
        }
    }

}
