package edu.uga.countryquiz;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuestionPagerAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS = 8;
    public QuestionPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle ) {
        super( fragmentManager, lifecycle );
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
                return StartPageFragment.newInstance(position, "start");
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return QuizFragment.newInstance(position, "quiz");
            case 7:
                return ScoreFragment.newInstance(position, "results");
            default:
                return null;
        }
    }


}
