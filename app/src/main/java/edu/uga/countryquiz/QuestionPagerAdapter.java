package edu.uga.countryquiz;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuestionPagerAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS = 6;
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
        return QuizFragment.newInstance(position, "quiz");

    }


}
