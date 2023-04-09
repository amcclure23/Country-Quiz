package edu.uga.countryquiz;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class QuestionPagerAdapter extends FragmentStateAdapter  {
    private static int NUM_ITEMS = 8;
    String[] question1, question2, question3, question4, question5, question6;
    private FragmentManager fragmentManager;
    private int score;
    private ScoreFragment finalScore = ScoreFragment.newInstance(score);
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager2 viewPager;




    public QuestionPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle, List<String[]> countries, ViewPager2 viewPager) {
        super( fragmentManager, lifecycle );
        this.fragmentManager = fragmentManager;
        question1 = countries.get(0);
        question2 = countries.get(1);
        question3 = countries.get(2);
        question4 = countries.get(3);
        question5 = countries.get(4);
        question6 = countries.get(5);
        fragments.add(QuizFragment.newInstance(0, "quiz", question1[0], question1[1]));
        fragments.add(QuizFragment.newInstance(1, "quiz", question2[0], question2[1]));
        fragments.add(QuizFragment.newInstance(2, "quiz", question3[0], question3[1]));
        fragments.add(QuizFragment.newInstance(3, "quiz", question4[0], question4[1]));
        fragments.add(QuizFragment.newInstance(4, "quiz", question5[0], question5[1]));
        fragments.add(QuizFragment.newInstance(5, "quiz", question6[0], question6[1]));
        fragments.add(finalScore);
        this.viewPager = viewPager;
        fragments.add(HistoryFragment.newInstance());
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    public QuizFragment getQuizFragment(int position) {
        return (QuizFragment) fragmentManager.findFragmentByTag("f" + position);
    }

    public void setScoreFragment(int score) {
        finalScore = ScoreFragment.newInstance(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void replaceFragment(Fragment fragment, int position) {
        fragments.set(position, fragment);

    }

}
