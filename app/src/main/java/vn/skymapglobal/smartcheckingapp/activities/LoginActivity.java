package vn.skymapglobal.smartcheckingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.Cleaner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.skymapglobal.smartcheckingapp.models.User;
import io.realm.Realm;
import vn.skymapglobal.smartcheckingapp.R;
import vn.skymapglobal.smartcheckingapp.repositories.UserRepository;
import vn.skymapglobal.smartcheckingapp.services.ApiService;

public class LoginActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://armonitoring.skymapglobal.vn/") // Thay thế bằng URL của máy chủ của bạn
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiService apiService = retrofit.create(ApiService.class);

    private EditText editTextUsername;
    private EditText editTextPassword;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = new UserRepository();

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        Button buttonLogin = findViewById(R.id.loginButton);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = editTextUsername.getText().toString();
                String inputPassword = editTextPassword.getText().toString();
                Call<User> call = apiService.login(inputUsername, inputPassword);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });
            }
        });

        TextView buttonSignup = findViewById(R.id.signupText);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }

    private void handleLogin() {
        String inputUsername = editTextUsername.getText().toString();
        String inputPassword = editTextPassword.getText().toString();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isLoggedIn = userRepository.loginUser(inputUsername, inputPassword);

        if (isLoggedIn) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username", inputUsername);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userRepository.closeRealm();
    }
}