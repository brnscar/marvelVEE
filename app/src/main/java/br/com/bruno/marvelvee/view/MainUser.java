package br.com.bruno.marvelvee.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.bruno.marvelvee.webService.GetMarvelTask;
import br.com.bruno.marvelvee.R;
import br.com.bruno.marvelvee.SignUpHelper;
import br.com.bruno.marvelvee.dao.UsuarioDAO;
import br.com.bruno.marvelvee.model.Character;
import br.com.bruno.marvelvee.model.Usuario;

public class MainUser extends AppCompatActivity {
    private SignUpHelper helper;
    private TextView Name, Description;
    private ImageView ThumbNail;
//    private List<Usuario> usuarios = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();
    private TextView NameUser, EmailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        UsuarioDAO dao = new UsuarioDAO(this);
        List<Usuario> usuarios = dao.getUsuarios();
//        dao.close();

//        TextView nameUser = (TextView) findViewById(R.id.nameTextViewMainUser);
//        TextView emailUser = (TextView)findViewById(R.id.emailTextViewMainUser);

//        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this,  android.R.layout.simple_list_item_1, usuarios);
//        emailUser.setText("email");
////        nameUser.setText("name");

//        Usuario user = new Usuario();

//        helper = new SignUpHelper(helper);
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
                Intent logoff = new Intent(MainUser.this, SignUp.class);
                startActivity(logoff);
                finish();
                break;
            case R.id.menu_listarMainUser:
                Intent charList = new Intent(MainUser.this, CharListActivity.class);
//               new GetMarvelTask(this).execute();
               startActivity(charList);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//
//    private class GetMarvelTask extends AsyncTask<Object, Object, String> {
//        private ProgressDialog dialog;
//        private Context context;
//
//        public GetMarvelTask(Context context) {
//            this.context = context;
//
//        }
//        @Override
//        protected void onPreExecute() {
//            dialog = ProgressDialog.show(context,"Aguarde", "Acessando a WEB SERVICE...", true, true);
//        }

//        @Override
//        protected String doInBackground(Object... params) {
//
//            WebCliente client = new WebCliente();
//            String resposta = client.get();
//            return resposta;
//        }

//        @Override
//        protected void onPostExecute(String resposta) {
//            dialog.dismiss();
//
//            Name = (TextView) findViewById(R.id.nomeListFragment);
//            ThumbNail = (ImageView)findViewById(R.id.thumbnailListFragment);
//
//            try {
//                JSONObject myJson = new JSONObject(resposta);
//                JSONObject jsonData = myJson.getJSONObject("data");
//                JSONArray jsonResult = jsonData.getJSONArray("results");
//
//                for (int i=0; i < jsonResult.length(); i++) {
//                    JSONObject obj = jsonResult.getJSONObject(i);
//                    Character character = new Character();
//
//                    character.setId(obj.getInt("id"));
//                    character.setName(obj.getString("name"));
//                    character.setDescription(obj.getString("description"));
//                    character.setThumbnail(obj.getJSONObject("thumbnail").getString("path") + "." + obj.getJSONObject("thumbnail").getString("extension"));
//
//                    Name.setText(character.getName());
//                    Description.setText(character.getDescription());
//
//
//                    characters.add(character);
//                }
//
//                System.out.println(characters);
//
//
//
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            Toast.makeText(context, "Lista de personagens", Toast.LENGTH_LONG).show();
//        }


}

