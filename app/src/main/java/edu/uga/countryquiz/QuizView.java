package edu.uga.countryquiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class QuizView extends Fragment {

    public QuizView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 pager = getActivity().findViewById( R.id.viewpager);
        QuestionPagerAdapter qpAdapter = new
                QuestionPagerAdapter(
                getChildFragmentManager(), getLifecycle(), getContext() );
        pager.setOrientation(
                ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( qpAdapter );
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}