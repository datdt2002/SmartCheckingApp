package vn.skymapglobal.smartcheckingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import vn.skymapglobal.smartcheckingapp.models.User;
import io.realm.Realm;
import vn.skymapglobal.smartcheckingapp.R;
import vn.skymapglobal.smartcheckingapp.repositories.UserRepository;

public class SignupActivity extends AppCompatActivity {

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userRepository = new UserRepository();

        EditText editTextFullname = findViewById(R.id.fullname);
        EditText editTextUsername = findViewById(R.id.username);
        EditText editTextPassword = findViewById(R.id.password);

        Button buttonSignUp = findViewById(R.id.signupButton);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputFullname = editTextFullname.getText().toString();
                String inputUsername = editTextUsername.getText().toString();
                String inputPassword = editTextPassword.getText().toString();

                if (inputFullname.isEmpty() || inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                userRepository.registerUser(inputFullname, inputUsername, inputPassword);
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();

            }
        });

        TextView button = findViewById(R.id.loginText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userRepository.closeRealm();
    }

}