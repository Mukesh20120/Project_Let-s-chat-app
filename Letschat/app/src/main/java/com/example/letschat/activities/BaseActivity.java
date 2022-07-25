package com.example.letschat.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letschat.utilities.Constants;
import com.example.letschat.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class BaseActivity extends AppCompatActivity {

    private DocumentReference documentReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager preferenceManager=new PreferenceManager(getApplicationContext());
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        documentReference = database.collection(Constants.KEY_COLLECTION_USERS)
        .document(preferenceManager.getString(Constants.KEY_USER_ID));
    }
    protected  void onPause(){
        super.onPause();
        documentReference.update(Constants.KEY_AVAILABILITY,0);
    }
    protected void onResume(){
        super.onResume();
        documentReference.update(Constants.KEY_AVAILABILITY,1);
    }
}
