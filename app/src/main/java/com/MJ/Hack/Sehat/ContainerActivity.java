package com.MJ.Hack.Sehat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mapzen.speakerbox.Speakerbox;

import java.util.ArrayList;
import java.util.List;

import fragments.About;
import fragments.Yoga;
import fragments.homefragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContainerActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Boolean CheckEditText ;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    TextView crp_user,oparea;
    public  static int val=0;
   public static List<Article> articles = new ArrayList<>();
    public  static ProgressBar progressBar2;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        bottomNavigation = findViewById(R.id.bottomBar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        nv = (NavigationView)findViewById(R.id.nv);
        View headerView =nv.getHeaderView(0);
        crp_user=headerView.findViewById(R.id.crpuser);
        oparea=headerView.findViewById(R.id.oparea);
      //  progressBar2=findViewById(R.id.progressBar2);
        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        dl.addDrawerListener(t);
        t.syncState();
        fm = getSupportFragmentManager();

        News.GetDataService service  =  News.getRetrofitInstance().create(News.GetDataService.class);
        Call<Postlist> call=service.getPostlist();
      call.enqueue(new Callback<Postlist>() {
          @Override
          public void onResponse(Call<Postlist> call, Response<Postlist> response) {
             Postlist data = response.body();
              articles = data.getArticles();;
              List<Article> ab = data.getArticles();
              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new homefragment()).commit();
           


          }

          @Override
          public void onFailure(Call<Postlist> call, Throwable t) {

          }
      });


    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new homefragment()).commit();


  nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch(id)
                {

                case R.id.bp:
                        int p1;
                        Toast.makeText(getApplicationContext(),"Blood Pressure",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), StartVitalSigns.class);
                        p1 = 2;
                        i.putExtra("Page", p1);
                        startActivity(i);
                        break;

                    case R.id.heartrate:
                        Toast.makeText(getApplicationContext(),"heart rate",Toast.LENGTH_SHORT).show();
                        Intent i2 = new Intent(getApplicationContext(), StartVitalSigns.class);
                         int p2= 1;
                         i2.putExtra("Page", p2);
                        startActivity(i2);
                        break;
                    case R.id.rp:
                        Toast.makeText(getApplicationContext(),"respiration",Toast.LENGTH_SHORT).show();
                        Intent i3 = new Intent(getApplicationContext(), StartVitalSigns.class);
                        int p3 = 3;
                         i3.putExtra("Page", p3);
                         startActivity(i3);
                         break;
                    case  R.id.ol:
                        Toast.makeText(getApplicationContext(),"oxygen",Toast.LENGTH_SHORT).show();
                        Intent i4 = new Intent(getApplicationContext(), StartVitalSigns.class);
                         int p4 = 4;
                         i4.putExtra("Page", p4);
                         startActivity(i4);
                         break;
                      //  Toast.makeText(ContainerActivity.this, "Settings",Toast.LENGTH_SHORT).show();break;
                    case R.id.logout:

                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref" ,MODE_PRIVATE);
                        sharedPreferences.edit().clear().commit();
               
                }


                return true;

            }
        });
bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new homefragment()).commit();

                return true;
            case R.id.yoga:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Yoga()).commit();


                return true;
            case R.id.About:


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new About()).commit();
        }


        return false;
    }
});


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new homefragment()).commit();
        super.onStart();
    }
}
