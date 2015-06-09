package com.aspire.mandou.view;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

public class MabDrawerToggle extends ActionBarDrawerToggle{

	DrawerLayout mDrawerLayout;
	public MabDrawerToggle(Activity activity, DrawerLayout drawerLayout,
			int drawerImageRes, int openDrawerContentDescRes,
			int closeDrawerContentDescRes) {
		super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes,
				closeDrawerContentDescRes);
		mDrawerLayout = drawerLayout;
	}
	
	public void switchDrawer() 
	{ 
	    if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) 
	    { 
	        mDrawerLayout.closeDrawer(GravityCompat.START); 
	    } 
	    else
	    { 
	        mDrawerLayout.openDrawer(GravityCompat.START); 
	    } 
	}

}
