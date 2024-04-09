package modesto.gabriele.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        //Definicao da acao do click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto );
                String assunto = etAssunto.getText().toString();
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                //Criando a intencao para navegar para outra app
                Intent i = new Intent(Intent.ACTION_SENDTO);
                //Definindo o tipo de app que o android deverá abrir (no caso email)
                i.setData(Uri.parse("mailto:"));
                //Preenchendo os dados que iremos enviar para outra tela
                String[] emails = new String[] {email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);
                //Caso possua mais de uma opção de app, o usuario podera escolher
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP: "));

                } // caso nao possua nenhum app insta, o programa ira mostrar uma mensagem de erro
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa operação", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}