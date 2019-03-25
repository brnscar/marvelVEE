package br.com.bruno.marvelvee.webService;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.bruno.marvelvee.model.Character;


public class GetMarvelTask extends AsyncTask<Object, Object, String>{
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

        List<Character> characters = new ArrayList<>();
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

        Toast.makeText(context, "Web Service acessada", Toast.LENGTH_LONG).show();

    }
}
