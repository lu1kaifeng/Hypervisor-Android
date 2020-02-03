package org.lu.hypervisor.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.lu.hypervisor.android.api.ApiClient;
import org.lu.hypervisor.android.persist.AppDatabase;
import org.lu.hypervisor.android.persist.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    MaterialButton btn;
    TextInputEditText userNameInput;
    TextInputEditText passwordInput;
    TextInputLayout userNameLayout;
    TextInputLayout passwordLayout;
    ProgressBar progressBar;
    ExecutorService backgroundThread = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            backgroundThread.submit(()->{
                AppDatabase appDatabase = AppDatabase.getDb(getApplicationContext());
                if(appDatabase.userDao().entryCount() != 0){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                appDatabase.close();
            });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.btn_login);
        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);
        userNameLayout = findViewById(R.id.userNameLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        progressBar = findViewById(R.id.login_progressBar);
        userNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count != 0)userNameLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count != 0 )passwordLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn.setOnClickListener((view)->{
            if(passwordInput.getText().length() == 0) {
                passwordLayout.setError(getString(R.string.password_hint)+getString(R.string.empty_credentials));
            }else{
                passwordLayout.setErrorEnabled(false);
            }
            if(userNameInput.getText().length() == 0){
                userNameLayout.setError(getString(R.string.username_hint)+getString(R.string.empty_credentials));
            }
            else{
                userNameLayout.setErrorEnabled(false);
            }
            if(passwordInput.getText().length() == 0 || userNameInput.getText().length() == 0) return;
            backgroundThread.submit(()->{
                runOnUiThread(()->{
                    progressBar.setVisibility(View.VISIBLE);
                });
                try{
                    AppDatabase database = AppDatabase.getDb(LoginActivity.this.getApplicationContext());
                    String token = ApiClient.Subject.login(LoginActivity.this.passwordInput.getText().toString(),LoginActivity.this.userNameInput.getText().toString());
                    database.close();
                    User user = new User();
                    user.userName = userNameInput.getText().toString();
                    user.password = passwordInput.getText().toString();
                    user.token = token;
                    database.userDao().delAll();
                    database.userDao().insert(user);
                    database.close();
                    runOnUiThread(()->{
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    });
                } catch (RuntimeException rte){
                    rte.printStackTrace();
                    runOnUiThread(()->{
                        passwordLayout.setError(getString(R.string.password_hint)+getString(R.string.wrong_credentials));
                        userNameLayout.setError(getString(R.string.username_hint)+getString(R.string.wrong_credentials));
                        progressBar.setVisibility(View.GONE);
                    });
                }
            });
        });
    }
}
