package com.example.growl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class queryRegister extends AppCompatActivity {

    EditText fullName,typeGrowler,injuryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_register);

        fullName = findViewById(R.id.fullname);
        typeGrowler = findViewById(R.id.growlerType);
        injuryType = findViewById(R.id.injury);
    }

    public void reportMethod(View view){
        Toast.makeText(this,"Reporting!!",Toast.LENGTH_SHORT).show();

        String name = fullName.getText().toString();
        String growler = typeGrowler.getText().toString();
        String injury = injuryType.getText().toString();

        if(!name.equals(null) && !growler.equals(null) && !injury.equals(null)) {
            startActivity(new Intent(queryRegister.this, helpMeMap.class));
        }
        finish();
    }

}
