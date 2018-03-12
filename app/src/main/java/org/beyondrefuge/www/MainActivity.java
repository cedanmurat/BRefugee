package org.beyondrefuge.www;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.beyondrefuge.www.Fragments.AboutUs;
import org.beyondrefuge.www.Fragments.ContactUs;
import org.beyondrefuge.www.Fragments.History;
import org.beyondrefuge.www.Fragments.Home;
import org.beyondrefuge.www.Fragments.Nationalism;
import org.beyondrefuge.www.Fragments.NewsFeed;
import org.beyondrefuge.www.Fragments.RacismAntiRefugee;
import org.beyondrefuge.www.Fragments.Settings;
import org.beyondrefuge.www.Fragments.Sexism;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        searchView=(SearchView)findViewById(R.id.search_bar);
        setSearchView();
        NavDrawer();
        displayMenu(1);
    }

    private void NavDrawer() {//Navigation Drawer Menu
        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("NewsFeed");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Racism/ Anti-Refugee");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Nationalism");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Sexism");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("History");

        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(7).withName("Contact Us");
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withIdentifier(8).withName("About Us");
        SecondaryDrawerItem item9 = new SecondaryDrawerItem().withIdentifier(9).withName("Settings");
        SecondaryDrawerItem item10 = new SecondaryDrawerItem().withIdentifier(10).withName("Sign Out");

//create the drawer and remember the `Drawer` result object

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1, item2, item3, item4, item5, item6,
                        new DividerDrawerItem(),
                        item7, item8, item9,item10,
                        new SecondaryDrawerItem().withName("")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position){
                            case 11:
                                finish();
                                startActivity(new Intent(MainActivity.this,Login.class));
                                LoginManager.getInstance().logOut();
                                FirebaseAuth.getInstance().signOut();
                                break;
                            default: displayMenu(position);
                        }


                        return false;
                    }
                })
                .build();
    }

    private void displayMenu(int position) {//Menu Display Items
        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = new Home();
                break;
            case 2:
                fragment = new NewsFeed();
                break;
            case 3:
                fragment = new RacismAntiRefugee();
                break;
            case 4:
                fragment = new Nationalism();
                break;
            case 5:
                fragment = new Sexism();
                break;
            case 6:
                fragment = new History();
                break;
            case 8://it is not 7,becauese of I dont know why , I think, naw drawer expect  the drawer like a item :)
                fragment = new ContactUs();
                break;
            case 9:
                fragment = new AboutUs();
                break;
            case 10:
                fragment = new Settings();
                break;

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, fragment);
            fragmentTransaction.commit();
        }
    }
    private void setSearchView(){//Search Bar
        final Fragment[] fragment = {null};
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                       @Override
            public boolean onQueryTextSubmit(String query) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("taggedWord", query);
                editor.commit();
                fragment[0] = new NewsFeed();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, fragment[0]);
                fragmentTransaction.commit();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

    }
}
