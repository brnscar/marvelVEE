package br.com.bruno.marvelvee.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.bruno.marvelvee.utils.MarvelAdapter;
import br.com.bruno.marvelvee.R;
import br.com.bruno.marvelvee.webService.WebCliente;
import br.com.bruno.marvelvee.model.Character;

public class CharListActivity extends AppCompatActivity {


    private List<Character> characters = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fragment);

        new GetMarvelTask(this).execute();


        ListView listView = (ListView) findViewById(R.id.list_view_fragment);

        //chamada da nossa implementação
        MarvelAdapter adapter =
                new MarvelAdapter(characters, this);

        listView.setAdapter(adapter);

    }

    private class GetMarvelTask extends AsyncTask<Object, Object, String> {
        private ProgressDialog dialog;
        private Context context;

        public GetMarvelTask(Context context) {
            this.context = context;

        }
        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(context,"Aguarde", "Acessando a WEB SERVICE...", true, true);
        }

        @Override
        protected String doInBackground(Object... params) {

            WebCliente client = new WebCliente();
            String resposta = client.get();
            return resposta;
        }

        @Override
        protected void onPostExecute(String resposta) {
            dialog.dismiss();


            try {
                JSONObject myJson = new JSONObject(resposta);
                JSONObject jsonData = myJson.getJSONObject("data");
                JSONArray jsonResult = jsonData.getJSONArray("results");

                for (int i=0; i < jsonResult.length(); i++) {
                    JSONObject obj = jsonResult.getJSONObject(i);
                    Character character = new Character();

                    character.setId(obj.getInt("id"));
                    character.setName(obj.getString("name"));
                    character.setDescription(obj.getString("description"));
                    character.setThumbnail(obj.getJSONObject("thumbnail").getString("path") + "." + obj.getJSONObject("thumbnail").getString("extension"));

                    characters.add(character);
                }

                System.out.println(characters);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(context, "Web Service foi acessada", Toast.LENGTH_LONG).show();
//            ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(this,R.layout.list_fragment, characters);



        }
    }

}

