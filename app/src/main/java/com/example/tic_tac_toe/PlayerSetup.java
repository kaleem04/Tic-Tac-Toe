package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerSetup extends AppCompatActivity {

  private EditText player1;
  private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_playersetup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        player1 = findViewById(R.id.editText1);
        player2 = findViewById(R.id.editText2);
    }


    public void submitButtonClicked(View view){
     String player1name = player1.getText().toString();
     String player2name = player2.getText().toString();

        Intent intent = new Intent(this,gamedisplay.class);
        intent.putExtra("Player Names",new String[]{player1name,player2name});
        startActivity(intent);

    }
}