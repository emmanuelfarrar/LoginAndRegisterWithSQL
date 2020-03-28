package com.efarrar.loginandregisterwithsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    //variables, constants, global
    EditText et_Username, et_Password, et_CnfPassword;
    Button btn_Register;
    TextView tv_Login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //instantiating objects
        et_Username = (EditText) findViewById(R.id.et_username);
        et_Password = (EditText)findViewById(R.id.et_password);
        et_CnfPassword = (EditText)findViewById(R.id.et_cnf_password);
        btn_Register = (Button)findViewById(R.id.button);
        tv_Login = (TextView)findViewById(R.id.tv_login);

        db = new DatabaseHelper(this);

        //listener to go back to LoginActivity
        tv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = et_Username.getText().toString().trim();
                String pwd = et_Password.getText().toString().trim();
                String cnf_pwd = et_CnfPassword.getText().toString().trim();

                if (pwd.equals(cnf_pwd)) {
                    db.addUser(user, pwd);
                    Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
