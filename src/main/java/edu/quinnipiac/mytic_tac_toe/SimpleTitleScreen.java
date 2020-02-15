package edu.quinnipiac.mytic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SimpleTitleScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.title_screen);

        Button playButton = findViewById(R.id.start_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.enter_name);

                if (editText.getText().toString().equals("") || editText.getText().toString().equals(" ")){
                    editText.setHint("Please try again.");
                }
                else{

                    String name = getResources().getString(R.string.player_name);
                    Intent startGameIntent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(startGameIntent);
                }
            }
        });
    }
}