package com.example.growl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFirebase extends AppCompatActivity {

    EditText email,password;
    Button loginBtn;
    FirebaseAuth fAuth;
    TextView registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etEmail);
        loginBtn = findViewById(R.id.btnLogin);
        registerbtn = findViewById(R.id.textView2);
        fAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account_email = email.getText().toString();
                String account_pass = password.getText().toString();

                if(TextUtils.isEmpty(account_email)){
                    email.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(account_pass)){
                    password.setError("Password is required!");
                    return;
                }

                if(account_pass.length()<6){
                    password.setError("Password length must be >= 6");
                    return;
                }

                //sign in the user in the firebase
                fAuth.signInWithEmailAndPassword(account_email,account_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginFirebase.this,"Logged In successfully!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),dashboardActivity.class));
                        }else{
                            Toast.makeText(LoginFirebase.this,"Error ! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void registerMethod(View view) {
        startActivity(new Intent(LoginFirebase.this,RegisterFirebase.class));
        finish();
    }
}
