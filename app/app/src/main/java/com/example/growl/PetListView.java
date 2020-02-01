package com.example.growl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import javax.annotation.Nullable;

public class PetListView extends AppCompatActivity {

    TextView username,petName,petAge,vaccinationDate;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list_view);

        username = findViewById(R.id.username);
        petName=findViewById(R.id.petNameTv);
        petAge = findViewById(R.id.ageTv);
        vaccinationDate = findViewById(R.id.lastVaccinationEt);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Pets Info").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                petName.setText(documentSnapshot.getString("pType"));
//                petAge.setText(documentSnapshot.getString("pAge"));
//                vaccinationDate.setText(documentSnapshot.getString("lastVaccination"));
                  petName.setText("Shero");
                  petAge.setText("Age - 2");
                  vaccinationDate.setText("Vaccination Date - 21/1/2020");
            }
        });

    }

    public void updateVaccinationDate(View view){



    }

}
