package com.example.firebaseloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button logoutButton;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    private void init() {
        auth = FirebaseAuth.getInstance();

        welcomeTextView = findViewById(R.id.welcome_textview);
        logoutButton = findViewById(R.id.logout_button);

        String currentUserEmail = auth.getCurrentUser().getEmail();

        SpannableStringBuilder welcomeString = new SpannableStringBuilder("Welcome, \n" + currentUserEmail);
        welcomeString.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 9, currentUserEmail.length() + 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        welcomeTextView.setText(welcomeString);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(WelcomeActivity.this, "User logged out", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}