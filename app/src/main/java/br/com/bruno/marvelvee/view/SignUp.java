package br.com.bruno.marvelvee.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.bruno.marvelvee.R;
import br.com.bruno.marvelvee.SignUpHelper;
import br.com.bruno.marvelvee.model.Usuario;

public class SignUp extends AppCompatActivity {
    private SignUpHelper helper;
    private List<Usuario> usuarios = new ArrayList<>();
    private TextView Name, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         helper = new SignUpHelper(this);
//        final UsuarioDAO dao = new UsuarioDAO(this);



        Button signUpButtonSignUp = (Button) findViewById(R.id.signUpButtonSignUp);
        signUpButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrar = new Intent(SignUp.this, MainUser.class);

                Usuario usuario = helper.getUsuario();
//////                usuarios.add(usuario);
////                dao.save(usuario);
////                dao.close();

                Toast.makeText(SignUp.this, "Cadastrado" + " Sr " + usuario.getNome() + usuario.getSobrenome(), Toast.LENGTH_SHORT).show();
                startActivity(cadastrar);

//                Name = (TextView) findViewById(R.id.nameTextViewMainUser);
//                Email = (TextView)findViewById(R.id.emailTextViewMainUser);
//                finish();
            }

        });
    }


}
