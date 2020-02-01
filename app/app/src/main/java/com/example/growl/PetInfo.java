package com.example.growl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PetInfo extends AppCompatActivity {

    EditText petType,petAge,lastVaccination;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        petType = findViewById(R.id.type);
        petAge = findViewById(R.id.age);
        lastVaccination = findViewById(R.id.vaccination);
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser()!=null){

            String Type = petType.getText().toString();
            String Age = petAge.getText().toString();
            String vaccination = lastVaccination.getText().toString();
            userID = fAuth.getCurrentUser().getUid();

            if(TextUtils.isEmpty(Type)){
                petType.setError("Email is required!");
                return;
            }

            if(TextUtils.isEmpty(Age)){
                petAge.setError("Password is required!");
                return;
            }

            DocumentReference documentReference = fStore.collection("Pets Info").document(userID);
            Map<String,Object> user = new HashMap<>();
            user.put("pType",Type);
            user.put("pAge",Age);
            user.put("lastVaccination",vaccination);
            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("Success stored pet data","Data stored on firestore");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Failure","Error in storage");
                }
            });

        }

    }

    public void petInfo(View view){
        startActivity(new Intent(PetInfo.this,PetListView.class));
        finish();
    }

}
