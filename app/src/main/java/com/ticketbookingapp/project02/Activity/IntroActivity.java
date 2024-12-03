package com.ticketbookingapp.project02.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ticketbookingapp.project02.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,MainActivity.class));
            }
        });


    }
}