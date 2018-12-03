package dev.codecathode.LaunchTrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.jaredrummler.cyanea.app.CyaneaAppCompatActivity;
import com.jaredrummler.cyanea.prefs.CyaneaThemePickerActivity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class LaunchListActivity extends CyaneaAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Using CyaneaAppCompatActivity in order to use the Cyanea theme library


    public Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public AppBarLayout appbar;
    public TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_list);


        toolbar = findViewById(R.id.toolbar);
        appbar = findViewById(R.id.appbarLayout);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        setSupportActionBar(toolbar);

        getSystemFontScale();
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(getResources().getColor(R.color.drawerShadowWhite));
        NavigationView navigationView = findViewById(R.id.navigationDrawer);

        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        navigationView.setNavigationItemSelectedListener(this);


        //Remove default title, Since I'm using textview as title on the toolbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);


        if (savedInstanceState != null) {
            appbar.setExpanded(false, true);
        }


        Fragment fragmentList = new LaunchListFragment();
        String listFragmentTag = LaunchListFragment.class.getName();


        if (savedInstanceState == null && getSupportFragmentManager().findFragmentByTag(listFragmentTag) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.launch_container, fragmentList, listFragmentTag)
                    .commit();
        }


    }

    //Trying to maintain toolBar textView title size, since it becomes too big if the system font size is large
    private void getSystemFontScale() {
        float scale = getResources().getConfiguration().fontScale;
        if (scale > 0.9) {
            toolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch_list, menu);
        return true;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = new SettingsFragment();
        switch (menuItem.getItemId()) {

            case R.id.settings:
                String settingFragmentTag = SettingsFragment.class.getName();

                boolean fragmentPop = getSupportFragmentManager().popBackStackImmediate(settingFragmentTag, 0);
                FragmentManager fragmentManager = getSupportFragmentManager();

                if (!fragmentPop && fragmentManager.findFragmentByTag(settingFragmentTag) == null) {

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.launch_container, fragment, settingFragmentTag)
                            .addToBackStack(settingFragmentTag)
                            .commit();
                }

                return true;

            case R.id.theming:

                //Comes from Cyanea theming library
                startActivity(new Intent(this, CyaneaThemePickerActivity.class));

            default:
                return false;
        }

    }
}
