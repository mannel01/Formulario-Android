package com.example.formulario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import
        androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    TextView texto;
    Button irForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        texto = findViewById(R.id.dadosRetornados);
        configurarLauncher();

        irForm = findViewById(R.id.irForm);
        irForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaFormulario.class);
                launcher.launch(intent);
            }
        });
    }


    private void configurarLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> tratarResultado(result)
        );
    }

    private void tratarResultado(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            String nome = data.getStringExtra("nome");
            Integer idade = data.getIntExtra("idade", 0);
            String email = data.getStringExtra("email");
            String cidade = data.getStringExtra("cidade");
            String genero = data.getStringExtra("genero");
            texto.setText("Nome: " + nome + "\nIdade: " + idade + "\nE-mail: " + email + "\nCidade: " + cidade + "\nGênero: " + genero);
        }
    }
}