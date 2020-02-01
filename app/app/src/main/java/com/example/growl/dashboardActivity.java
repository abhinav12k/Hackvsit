package com.example.growl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class dashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void logoutMethod(View view){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginFirebase.class));
        finish();
    }


    public void addPetsMethod(View view){

        startActivity(new Intent(getApplicationContext(),PetInfo.class));
        finish();
    }
}
