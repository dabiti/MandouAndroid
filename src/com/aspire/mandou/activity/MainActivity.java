package com.aspire.mandou.activity;


import com.aspire.mandou.fragment.ShowIntegralFragment;
import com.aspire.mandou.fragment.TopUpFragment;
import com.aspire.mandou.framework.widget.MyToast;
import com.aspire.mandou.util.IntentUtil;
import com.aspire.mandou.view.MabDrawerToggle;
import com.example.mandou.R;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;


public class MainActivity extends ActionBarActivity implements OnItemClickListener {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private TopUpFragment topUpFragment;// 充值积分页面
    private ShowIntegralFragment showIntergralFragment;// 积分展示界面（小油桶）
    private CharSequence mTitle;
    private ListView mDrawerList;// 左侧抽屉listview
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private MabDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }
    
    private void init(){
    	mTitle = getTitle();
        mPlanetTitles = new String[]{
                getString(R.string.title_section1),
                getString(R.string.title_section2),
                getString(R.string.title_section3),
        }; 

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); 
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START); 
        View inflater = LayoutInflater.from(this).inflate(R.layout.left_drawer_header, null);
        mDrawerList.addHeaderView(inflater);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1, mPlanetTitles)); 
        mDrawerList.setOnItemClickListener(this);
        LayoutParams lp = new LayoutParams( 
                ActionBar.LayoutParams.MATCH_PARENT, 
                ActionBar.LayoutParams.MATCH_PARENT, 
                Gravity.CENTER); 
        View viewTitleBar = getLayoutInflater().inflate(R.layout.action_bar_title, null); 
        getActionBar().setCustomView(viewTitleBar, lp);
        getActionBar().setDisplayShowHomeEnabled(false); 
        getActionBar().setDisplayShowTitleEnabled(false); 
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
        getActionBar().setDisplayShowCustomEnabled(true); 
        TextView tvTitle = (TextView) getActionBar().getCustomView().findViewById(android.R.id.title); 
        ImageButton ibtnNav = (ImageButton) getActionBar().getCustomView().findViewById(R.id.left_btn); 
        ibtnNav.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View v) { 
                mDrawerToggle.switchDrawer(); 
            } 
        }); 
        // MabDrawerToggle ties together the the proper interactions 
        // between the sliding drawer and the action bar app icon 
        mDrawerToggle = new MabDrawerToggle( 
                this,                  /* host Activity */ 
                mDrawerLayout,         /* DrawerLayout object */ 
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */ 
                R.string.title_section1,  /* "open drawer" description for accessibility */ 
                R.string.title_section2  /* "close drawer" description for accessibility */
                ); 
        mDrawerLayout.setDrawerListener(mDrawerToggle); 
        FragmentManager fragmentManagerOnCreate = getSupportFragmentManager();
        FragmentTransaction ftOnCreate = fragmentManagerOnCreate.beginTransaction();
			showIntergralFragment = new ShowIntegralFragment();
        ftOnCreate.replace(R.id.container, showIntergralFragment);
        ftOnCreate.commit();
    }

    @Override
  	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
          FragmentManager fragmentManager = getSupportFragmentManager();
          FragmentTransaction ft = fragmentManager.beginTransaction();
  		switch(arg2){
      	case 0:
      		Log.d("xxx","p:"+arg2);
      		break;
      	case 1:
      		if(showIntergralFragment == null){
      			showIntergralFragment = new ShowIntegralFragment();
      		}
      		ft.replace(R.id.container, showIntergralFragment);
      		ft.commit();
      		Log.d("xxx","p:"+arg2);
      		break;
      	case 2:
      		if(topUpFragment == null){
      			topUpFragment = new TopUpFragment();
      		}
      		ft.replace(R.id.container, topUpFragment);
      		ft.commit();	
      		Log.d("xxx","p:"+arg2);
      		break;
      	}
  		mDrawerLayout.closeDrawer(mDrawerList);
  	}

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
//            restoreActionBar();
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * 输入搜索条件
     * @author yanbin_a
     */
    @SuppressWarnings("unused")
	private void onClickAvatar() {
    	
	}
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        	
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
        

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
