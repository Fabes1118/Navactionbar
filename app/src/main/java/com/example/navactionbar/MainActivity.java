package com.example.navactionbar;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private String [] titles;
    private ListView drawerlist;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ActionBar actionBar;
    private int currentPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        titles = getResources().getStringArray(R.array.Titles);

        drawerlist = (ListView)findViewById(R.id.drawer);

        ArrayAdapter<String> adapter = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1, titles);

        drawerlist.setAdapter(adapter);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);

        drawerlist.setOnItemClickListener(new DrawerItemOnClickListerner());

        if (savedInstanceState != null){
            currentPosition = savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        }
        else
        {selectItem(0);
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open_drawer,R.string.close_drawer);
        //*{
        //* public void onDrawerClosed(View view)
        //*{
        //*super.onDrawerClosed(view);
        //*invalidateOptionsMenu();
        //*}

        //*public void onDrawerOpened(View view)
        //*{
        //*super.onDrawerClosed(view);

        //*}
        //*};
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                FragmentManager fragmentManager = getSupportFragmentManager();

                Fragment fragment = fragmentManager.findFragmentByTag("visible_fragment");

                if (fragment instanceof TopLevelFragment){
                    currentPosition = 0;
                }

                if (fragment instanceof PageOneFragment){
                    currentPosition = 1;
                }

                if (fragment instanceof PageTwoFragment){
                    currentPosition = 2;
                }

                if (fragment instanceof PageThreeFragment){
                    currentPosition = 3;
                }

                setActionBarTitle(currentPosition);

                drawerlist.setItemChecked(currentPosition,true);


            }
        });
    }


    private class DrawerItemOnClickListerner implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void selectItem(int position){
        currentPosition = position;

        Fragment fragment;

        switch(position){
            case 1: fragment = new PageOneFragment();
            break;
            case 2: fragment = new PageTwoFragment();
            break;
            case 3: fragment = new PageThreeFragment();
            break;
            default: fragment = new TopLevelFragment();
            break;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,fragment,"visible_fragment");

        ft.addToBackStack(null);

        ft.commit();

        setActionBarTitle(position);


        drawerLayout.closeDrawer(drawerlist);


    }

    private void  setActionBarTitle(int position){

        String title;

        if (position == 0){
                title = getResources().getString(R.string.app_name);}
        else
        {
            title = titles[position];
        }
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate (Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged (Configuration config){
        super.onConfigurationChanged(config);
        drawerToggle.onConfigurationChanged(config);
    }



    @Override
    public void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.getInt("position",currentPosition);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch (item.getItemId()){
            case R.id.settings:
                //do something
                return true;

            case R.id.example:
                 //do something
                 return true;

              default:
                  return super.onOptionsItemSelected(item);
             }
        }

    }



