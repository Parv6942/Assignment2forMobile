package com.example.connect3game;  // Change to your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private EditText nameInput;  // Stores the user's name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomepage);
        nameInput = findViewById(R.id.Name_Id);
        Button startButton = findViewById(R.id.Start_Button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameInput.getText().toString().trim();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("USERNAME", userName);  // Send name to MainActivity
                startActivity(intent);
                finish();  // Close WelcomeActivity
            }
        });
    }
}
