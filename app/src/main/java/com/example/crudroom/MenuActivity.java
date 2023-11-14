package com.example.crudroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Récupérez les boutons du layout par leurs identifiants
        Button btnActivity1 = findViewById(R.id.btnActivity1);
        Button btnActivity2 = findViewById(R.id.btnActivity2);
        Button btnActivity3 = findViewById(R.id.btnActivity3);
        Button btnActivity4 = findViewById(R.id.btnActivity4);

        // Définissez des écouteurs de clic pour les boutons
        btnActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque le bouton 1 est cliqué, lancez l'Activity correspondante
                Intent intent = new Intent(MenuActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque le bouton 2 est cliqué, lancez l'Activity correspondante
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque le bouton 3 est cliqué, lancez l'Activity correspondante
                Intent intent = new Intent(MenuActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        btnActivity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque le bouton 4 est cliqué, lancez l'Activity correspondante
                Intent intent = new Intent(MenuActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}
