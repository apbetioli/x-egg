package com.xegg.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.xegg.app.fragments.BaseFragment;
import com.xegg.app.fragments.ListViewAnimationsFragment;

public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null)
            openFragment(new ListViewAnimationsFragment());

    }

    private void openFragment(BaseFragment baseFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main_extras, baseFragment);
        fragmentTransaction.commit();
    }

}