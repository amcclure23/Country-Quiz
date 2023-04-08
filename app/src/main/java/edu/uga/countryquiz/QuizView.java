package edu.uga.countryquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class QuizView extends Fragment {

    private ViewPager2 pager;
    private List<String[]> countries;

    public QuizView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_view, container, false);
        LinearLayout myLinearLayout = getActivity().findViewById(R.id.splash_content);
        myLinearLayout.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countries = (List<String[]>) getArguments().getSerializable("countriesList");
        pager = getActivity().findViewById( R.id.viewpager);
        QuestionPagerAdapter qpAdapter = new
                QuestionPagerAdapter(
                getChildFragmentManager(), getLifecycle(), countries );
        pager.setOrientation(
                ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( qpAdapter );
        // Prevent swiping left on all pages
        pager.registerOnPageChangeCallback(new SwipeLeftDisabledOnPageChangeCallback(pager));
    }

    private static class SwipeLeftDisabledOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {

        private final ViewPager2 viewPager;

        public SwipeLeftDisabledOnPageChangeCallback(ViewPager2 viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void onPageSelected(int position) {
            // Disable swiping left on all pages except the first page
            if (position > 0) {
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(0, false);
                    }
                });
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

