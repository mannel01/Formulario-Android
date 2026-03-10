package com.example.formulario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaFormulario extends AppCompatActivity {

    Button btnEnviar;
    EditText nome, idade, email, cidade;
    RadioButton escolha;
    RadioGroup genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_formulario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnEnviar = findViewById(R.id.btnEnviar);
        nome = findViewById(R.id.txtNome);
        idade = findViewById(R.id.txtIdade);
        email = findViewById(R.id.txtEmail);
        cidade = findViewById(R.id.txtCidade);
        genero = findViewById(R.id.radioGroup);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idGroup = genero.getCheckedRadioButtonId();
                escolha = findViewById(idGroup);

                Intent intent = new Intent();

                intent.putExtra("nome", nome.getText().toString());
                intent.putExtra("idade", Integer.parseInt(idade.getText().toString()));
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("cidade", cidade.getText().toString());
                intent.putExtra("genero", escolha.getText());

                setResult(RESULT_OK, intent);
                finish();
            }
        });



    }
}