package edu.uga.countryquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

/**
 * fragment that contains a viewpager
 */
public class QuizView extends Fragment {

    private ViewPager2 pager;
    private List<String[]> countries;

    /**
     * constructor
     */
    public QuizView() {
        // Required empty public constructor
    }

    /**
     * inflates view
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_view, container, false);
        LinearLayout myLinearLayout = getActivity().findViewById(R.id.splash_content);
        myLinearLayout.setVisibility(View.GONE);
        return view;
    }

    /**
     * sets up the viewpager2 with the questionpageadapter
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
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
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            int prevPage = -1;
            @Override
            public void onPageSelected(int position) {
                // The current item position is passed to this method
                if (position == prevPage) {
                    pager.setCurrentItem(prevPage + 1);
                }
                if (position > prevPage) {
                    prevPage = position - 1;
                }
            }
        });

    }

    /**
     * destroyyyyyy!!!!!!
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

