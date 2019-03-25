package br.com.bruno.marvelvee;

import android.widget.EditText;

import br.com.bruno.marvelvee.model.Usuario;
import br.com.bruno.marvelvee.view.SignUp;

public class SignUpHelper {
    private final EditText campoEmail;
    private final EditText campoNome;
    private final EditText campoSobrenome;
    private final EditText campoPassword;


    public SignUpHelper(SignUp signUp) {

         campoNome = (EditText) signUp.findViewById(R.id.namePlainTextSignUp);
         campoSobrenome = (EditText) signUp.findViewById(R.id.lastNamePlainTextSignUp);
         campoEmail = (EditText) signUp.findViewById(R.id.emailPlainTextSignUp);
         campoPassword= (EditText) signUp.findViewById(R.id.passwordPlainTextSignUp);


    }

    public Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(campoNome.getText().toString());
        usuario.setSobrenome(campoSobrenome.getText().toString());
        usuario.setEmail(campoEmail.getText().toString());
        usuario.setPassword(campoPassword.getText().toString());

        return usuario;
    }
}
