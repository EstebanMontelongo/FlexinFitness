package com.example.flexinfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {

    // declaring the View & ViewGroups
    Button logOut;
    Button diary;
    Button settings;
    Button workoutPlannerButton;
    Button tutorialButton;

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        // connecting Views & ViewGroups to their XMLs
        logOut = findViewById(R.id.logOutButton);
        // diary == logButton lol I need to go back and change this
        diary = findViewById(R.id.diaryButton);
        name = findViewById(R.id.nameTextView);
        settings = findViewById(R.id.settingsButton);
        workoutPlannerButton = findViewById(R.id.workoutPlannerButton);
        tutorialButton = findViewById(R.id.tutorialButton);

        final GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            // Retrieves user info to display.
            name.setText(signInAccount.getDisplayName());

            // Listens for a click on the sign out button.
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                            .requestIdToken(getString(R.string.default_web_client_id))
                            .requestEmail()
                            .build();

                    // Creates the current google sign in client instance
                    final GoogleSignInClient signInClient = GoogleSignIn.getClient(DashBoard.this, signInOptions);

                    // Signs out if firebase client
                    mAuth.signOut();
                    // Attempts to sign out of google client and listens for a success or failure
                    signInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(DashBoard.this, signInAccount.getEmail() + " Logged out Successfull!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DashBoard.this, signInAccount.getEmail()+ " Failed to log out!", Toast.LENGTH_SHORT).show();

                        }

                    });
                }
            });
        }
        // If they're in the application without beng logged into, send them to the login screen.
        else{
            mAuth.signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        // goes to the log activity ================================================================
        diary.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent gotoLOGDIARYHOMEPAGE = new Intent(getApplicationContext(), logHomepage.class);
                startActivity(gotoLOGDIARYHOMEPAGE);
            }
        }); // end LOG/DIARY button onClick ========================================================

        // goes to the workoutPlanner activity =====================================================
        workoutPlannerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent workoutPlanner = new Intent(DashBoard.this, workoutPlanner.class);
                startActivity(workoutPlanner);
            }
        }); // end workoutPlanner ==================================================================

        // goes to the TUTORIAL activity ===========================================================
        tutorialButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent tutorial = new Intent(DashBoard.this, tutorial.class);
                startActivity(tutorial);
            }
        }); // end TUTORIAL activity ===============================================================

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(getApplicationContext(), settingOptions.class);
                startActivity(goToSettings);
            }
        });
    }
}
