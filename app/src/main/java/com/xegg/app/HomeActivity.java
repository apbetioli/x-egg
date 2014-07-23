package com.xegg.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.xegg.app.fragments.BaseFragment;
import com.xegg.app.fragments.ListViewAnimationsFragment;
import com.xegg.app.util.MessageUtil;

public class HomeActivity extends BaseActivity {

    private boolean backPressed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        refresh();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void openFragment(BaseFragment baseFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main_extras, baseFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void refresh() {
        openFragment(new ListViewAnimationsFragment());
    }

    @Override
    public void onBackPressed() {
        if(backPressed) {
            super.onBackPressed();
            return;
        }

        MessageUtil.handle(this, "Press back again to leave");

        backPressed = true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        backPressed = false;
    }
}