package org.lu.hypervisor.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.SystemClock;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import org.lu.hypervisor.android.api.ApiClient;
import org.lu.hypervisor.android.api.model.Notification;
import org.lu.hypervisor.android.api.model.Photo;
import org.lu.hypervisor.android.api.model.Subject;
import org.lu.hypervisor.android.persist.AppDatabase;
import org.lu.hypervisor.android.persist.User;
import org.lu.hypervisor.android.receivers.ResponseBroadcastReceiver;
import org.lu.hypervisor.android.receivers.ToastBroadcastReceiver;
import org.lu.hypervisor.android.services.BackgroundService;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Subject currentUser;
    private ResponseBroadcastReceiver broadcastReceiver;
    private ExecutorService backgroundThread = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver= new ResponseBroadcastReceiver();
        IntentFilter intentFilter= new IntentFilter();
        intentFilter.addAction(BackgroundService.ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
        scheduleAlarm();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        backgroundThread.submit(()->{
            AppDatabase database = AppDatabase.getDb(MainActivity.this.getApplicationContext());
            String token =database.userDao().getALl().get(0).token;
            database.close();
            this.currentUser = ApiClient.Subject.getInfo(token);
            Photo photo = ApiClient.Subject.getPhoto(token);
            runOnUiThread(()->{
                CircleImageView avatar = drawer.findViewById(R.id.drawerAvatar);
                MaterialTextView userName = drawer.findViewById(R.id.draw_userName),userRole = drawer.findViewById(R.id.drawer_userRole);
                Bitmap avatarBmp = BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.getDecoder().decode(photo.getPhotoBase64())));
                avatar.setImageBitmap(avatarBmp);
                userName.setText(currentUser.getName());
                userRole.setText(currentUser.getRole());
            });
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private  void scheduleAlarm() {
        Intent toastIntent= new Intent(getApplicationContext(), ToastBroadcastReceiver.class);
        PendingIntent toastAlarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, toastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        long startTime=System.currentTimeMillis(); //alarm starts immediately
        AlarmManager backupAlarmMgr=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        backupAlarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP,startTime,1000,toastAlarmIntent); // alarm will repeat after every 15 minutes
    }
}
