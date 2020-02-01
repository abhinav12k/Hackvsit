package com.example.growl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterFirebase extends AppCompatActivity {

    EditText fullname,username,email,password,phone;
    Button registerBtn;
    FirebaseAuth fAuth;
    TextView textView;
    String userId;
    CheckBox havePet; //for checkbox
    Boolean pets;
//    FirebaseFirestore fStore;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firebase);

        havePet = findViewById(R.id.checkBox);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.email);
        email = findViewById(R.id.email);
        password=findViewById(R.id.password);
        phone = findViewById(R.id.phoneEt);
//        fStore = FirebaseFirestore.getInstance();
        registerBtn = findViewById(R.id.btnLogin);
        textView = findViewById(R.id.textView2);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(RegisterFirebase.this,dashboardActivity.class));
            finish();
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String account_email = email.getText().toString();
                String account_pass = password.getText().toString();
                final String name = fullname.getText().toString();
                final String user_phone = phone.getText().toString();

                havePet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(havePet.isChecked()){
                            pets=true;
                        }else{
                            pets=false;
                        }
                    }
                });

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

                //register the user in the firebase
                fAuth.createUserWithEmailAndPassword(account_email,account_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterFirebase.this,"Registration successful!",Toast.LENGTH_SHORT).show();
                            userId = fAuth.getCurrentUser().getUid();
//                            DocumentReference documentReference = fStore.collection("users").document(userId);
                            DatabaseReference documentReference = database.getInstance().getReference();
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",name);
                            user.put("email",account_email);
                            user.put("phone",user_phone);
                            user.put("Pets?",pets);
                            documentReference.child("users").child(userId).setValue(user);

                            startActivity(new Intent(getApplicationContext(),dashboardActivity.class));
                        }else{
                            Toast.makeText(RegisterFirebase.this,"Error ! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

    public void loginMethod(View view) {
        startActivity(new Intent(RegisterFirebase.this,LoginFirebase.class));
        finish();
    }
}
