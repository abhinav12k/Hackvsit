package com.example.growl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();
    }

    public void myAccountMethod(View view){
        Intent FirebaseIntent = new Intent(this, RegisterFirebase.class);
        startActivity(FirebaseIntent);
    }

    public void careCenterMethod(View view) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent careCenterIntent = new Intent(this, careCenterMapActivity.class);
            startActivity(careCenterIntent);
        } else {
            Dexter.withActivity(MainActivity.this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Intent careCenterIntent = new Intent(MainActivity.this, careCenterMapActivity.class);
                            startActivity(careCenterIntent);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            if (response.isPermanentlyDenied()) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Permission Denied")
                                        .setMessage("Permission to location is permanently denied go to setting to enable Location services")
                                        .setNegativeButton("Cancel", null)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent();
                                                intent.setAction(Settings.ACTION_APPLICATION_SETTINGS);
                                                intent.setData(Uri.fromParts("package", getPackageName(), null));
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .check();
        }
    }

    public void newFeedMethod(View view) {

        Intent newsFeedIntent = new Intent(this, dosWeb.class);
        startActivity(newsFeedIntent);
    }

    public void getHelpMapMethod(View view) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent queryRegisterIntent = new Intent(this, queryRegister.class);
            startActivity(queryRegisterIntent);
        } else {
            Dexter.withActivity(MainActivity.this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Intent queryRegisterIntent = new Intent(MainActivity.this, queryRegister.class);
                            startActivity(queryRegisterIntent);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            if (response.isPermanentlyDenied()) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Permission Denied")
                                        .setMessage("Permission to location is permanently denied go to setting to enable Location services")
                                        .setNegativeButton("Cancel", null)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent();
                                                intent.setAction(Settings.ACTION_APPLICATION_SETTINGS);
                                                intent.setData(Uri.fromParts("package", getPackageName(), null));
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .check();

        }

    }

    public void myCompanionMethod(View view) {
        if (fAuth.getCurrentUser().getUid() == null) {
            startActivity(new Intent(this, MyCompanion.class));
        }else{
            startActivity(new Intent(this,PetListView.class));
        }
    }
}