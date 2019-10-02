package com.example.pcdashboard.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pcdashboard.Fragment.ClassFragment;
import com.example.pcdashboard.Fragment.InfoFragment;
import com.example.pcdashboard.Fragment.PasswordFragment;
import com.example.pcdashboard.Fragment.PostFragment;
import com.example.pcdashboard.R;

public class FeatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_feature,new PostFragment()).commit();
    }
}
