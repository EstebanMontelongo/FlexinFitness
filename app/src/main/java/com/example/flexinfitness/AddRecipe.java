package com.example.flexinfitness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AddRecipe extends AppCompatActivity implements View.OnClickListener{


    private static final int REQUEST_CODE = 1;

    // Declare View & ViewGroup variables
    Button addRecipeButton;
    Button homeButton;
    LinearLayout linearLayout;
    ConstraintLayout constraintLayout;
    Recipe Recipe1;
    String currRecipe = "Recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipts);

        // Connect View & ViewGroup variables to their XML id's
        constraintLayout = findViewById(R.id.constraintLayout);
        linearLayout = findViewById(R.id.linearLayout);
        addRecipeButton = findViewById(R.id.addRecipeButton);
        homeButton = findViewById(R.id.homeButton);

        // setting the onClick's for the buttons
        addRecipeButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // create a log entry
            case R.id.addRecipeButton:
                Intent createRecipeEntry = new Intent(getApplicationContext(), CreateRecipe.class); // intent is to switch to 'createLogEntry'
                createRecipeEntry.putExtra(currRecipe, Recipe1);
                startActivityForResult(createRecipeEntry, REQUEST_CODE);
                break;
            // go back to dashboard
            case R.id.homeButton:
                Intent goBackToDashboard = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(goBackToDashboard);
                break;
        } // end switch(view.getId()
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //addLog();
            }

        }
    }
}

