package com.efarrar.loginandregisterwithsql;

/**
 * URL 1: https://www.youtube.com/watch?v=mPhqDzO7PUU
 * URL 2: https://www.youtube.com/watch?v=35nseBz0CKY
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //variables, constants, global
    EditText et_Username, et_Password;
    Button btn_Login;
    TextView tv_Register;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //instantiating objects
        et_Username = (EditText) findViewById(R.id.et_username);
        et_Password = (EditText)findViewById(R.id.et_password);
        btn_Login = (Button)findViewById(R.id.button);
        tv_Register = (TextView)findViewById(R.id.tv_register);

        db = new DatabaseHelper(this);

        //listener to go back to LoginActivity
        tv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = et_Username.getText().toString().trim();
                String pwd = et_Password.getText().toString().trim();
                Boolean dbChecker = db.checkUser(user, pwd);
                if (dbChecker == true){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
