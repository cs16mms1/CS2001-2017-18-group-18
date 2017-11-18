package brunel.csgroup18.squadlink;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionPlansFragment extends Fragment {

    private FragmentTabHost mTabHost;

    public NutritionPlansFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nutrition_plans, container, false);


        mTabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Coach Plans").setIndicator("Coach Plans"),
                CoachNutritionFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("My Plans").setIndicator("My Plans"),
                MyNutritionFragment.class, null);

        return rootView;
    }

}
