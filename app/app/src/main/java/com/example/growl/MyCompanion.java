package com.example.growl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MyCompanion extends AppCompatActivity {

    TextView name,email,phone;
    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;
    FirebaseDatabase database;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_companion);

        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phoneEt);
        fAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//        database = FirebaseDatabase.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DatabaseReference documentReference = database.getInstance().getReference();
        documentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    UserInformation uInfo = new UserInformation();
                    name.setText(ds.child(userId).getValue(UserInformation.class).getName());
                    email.setText(ds.child(userId).getValue(UserInformation.class).getEmail());
                    phone.setText(ds.child(userId).getValue(UserInformation.class).getPhone_num());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void addpetsMethod(View view){
        startActivity(new Intent(MyCompanion.this,PetInfo.class));
    }
}
