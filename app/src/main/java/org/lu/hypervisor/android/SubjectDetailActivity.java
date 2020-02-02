package org.lu.hypervisor.android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import org.lu.hypervisor.android.api.ApiClient;
import org.lu.hypervisor.android.api.model.Misbehavior;
import org.lu.hypervisor.android.api.model.Photo;
import org.lu.hypervisor.android.persist.AppDatabase;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubjectDetailActivity extends AppCompatActivity {
    public static Misbehavior misbehavior;
    private CircleImageView avatar;
    private MaterialTextView courseName,courseInfo,behaviorName,behaviorInfo;
    private ExecutorService backgroundThread = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = this.getIntent();
        this.avatar =  findViewById(R.id.subject_detail_avatar);
        this.courseName = findViewById(R.id.subject_detail_course_name);
        this.courseInfo = findViewById(R.id.subject_detail_course_info);
        this.behaviorName = findViewById(R.id.subject_detail_behavior_name);
        this.behaviorInfo = findViewById(R.id.subject_detail_behavior_info);
        backgroundThread.submit(()->{
            AppDatabase database = AppDatabase.getDb(this.getApplicationContext());
            String token =database.userDao().getALl().get(0).token;
            database.close();
            Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.getDecoder().decode(ApiClient.Subject.getPhotoBySujectId(token,misbehavior.getSubject().getId()).getPhotoBase64())));
            runOnUiThread(()->{
                avatar.setImageBitmap(bitmap);
                this.courseName.setText(misbehavior.getCourse().getName());
                this.courseInfo.setText(misbehavior.getCourse().getClassroom().getIdentifier());
                this.behaviorName.setText(misbehavior.getType().toString());
                this.behaviorInfo.setText(misbehavior.getWhen().toString());
            });
        });
    }
}
