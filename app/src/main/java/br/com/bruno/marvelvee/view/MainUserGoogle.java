package br.com.bruno.marvelvee.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import br.com.bruno.marvelvee.webService.GetMarvelTask;
import br.com.bruno.marvelvee.R;

public class MainUserGoogle extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener  {

    private LinearLayout linearLayout2;
    private LinearLayout linearLayout5;
    private LinearLayout linearLayout4;
    private Button logOut;
    private SignInButton googleButton;
    private Button register;
    private TextView Name, Email;
    private ImageView fotoPerfil;
//    private Menu menuSair, menuListar;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_google);

//        Button register = (Button) findViewById(R.id.registerButtonMainUserGoogle);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainUserGoogle.this, "Preencha os dados", Toast.LENGTH_SHORT).show();
//                Intent cadastrar = new Intent(MainUserGoogle.this, SignUp.class);
//                startActivity(cadastrar);
//            }
//        });

        linearLayout2 = (LinearLayout)findViewById(R.id.linearLayoutMainUserGoogle2);
        linearLayout4 = (LinearLayout)findViewById(R.id.linearLayoutMainUserGoogle4);
        linearLayout5 = (LinearLayout)findViewById(R.id.linearLayoutMainUserGoogle5);
        logOut = (Button)findViewById(R.id.logOutButtonMainUserGoogle);
        googleButton = (SignInButton)findViewById(R.id.googleButtonMainUserGoogle);
        register = (Button)findViewById(R.id.joinUsButtonMainUserGoogle);
//        menuListar = (MenuItem) findViewById(R.id.menu_listarMainUser);
////        menuSair = (MenuItem) findViewById(R.id.logOutButtonMainUserGoogle);
        Name = (TextView) findViewById(R.id.nameTextViewMaiUserGoogle);
        Email = (TextView)findViewById(R.id.emailTextViewMainUserGoogle);
        fotoPerfil = (ImageView)findViewById(R.id.fotoPerfilMainUserGoogle);

        googleButton.setOnClickListener(this);
        logOut.setOnClickListener(this);
        register.setOnClickListener(this);
        linearLayout2.setVisibility(View.GONE);
//        menuListar.visible(false);
        register.setVisibility(View.GONE);


        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.googleButtonMainUserGoogle:
                googleButton();
                break;
            case R.id.logOutButtonMainUserGoogle:
                logOut();
               break;
            case R.id.joinUsButtonMainUserGoogle:
                register();
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }
        private void googleButton(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
       startActivityForResult(intent,REQ_CODE);
    }
    private void logOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }
    private void register() {
        Toast.makeText(MainUserGoogle.this, "Preencha os Campos", Toast.LENGTH_SHORT).show();
        Intent logar = new Intent(MainUserGoogle.this, SignUp.class);
        startActivity(logar);

    }
    private void handleResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String img_url = account.getPhotoUrl().toString();
            Name.setText(name);
            Email.setText(email);
            Glide.with(this).load(img_url).into(fotoPerfil);
            updateUI(true);

        }
        else{
            updateUI(false);
        }

    }
    private void updateUI(boolean isLogin){
        if(isLogin){
            linearLayout2.setVisibility(View.VISIBLE);
            googleButton.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.GONE);
            linearLayout5.setVisibility(View.GONE);
//            menuListar.setVisible(true);
            register.setVisibility(View.GONE);

        } else {
            linearLayout2.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout5.setVisibility(View.VISIBLE);
//            menuListar.setVisible(false);
//            logOut.setVisibility(View.GONE);
            googleButton.setVisibility(View.VISIBLE);
            register.setVisibility(View.GONE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_signup, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOutButtonMainUserGoogle:
                logOut();
                break;
            case R.id.menu_listarMainUser:
                Intent charList = new Intent(MainUserGoogle.this, CharListActivity.class);
//                new GetMarvelTask(this).execute();
                startActivity(charList);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
