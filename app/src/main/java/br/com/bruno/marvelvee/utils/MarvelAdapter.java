package br.com.bruno.marvelvee.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.bruno.marvelvee.R;
import br.com.bruno.marvelvee.model.Character;

public class MarvelAdapter extends BaseAdapter {

    private final List<Character> characters;
    private final Activity act;

    public MarvelAdapter(List<Character> characters, Activity act) {
        this.characters = characters;
        this.act = act;

    }

    @Override
    public int getCount() {
        return characters.size();
    }

    @Override
    public Object getItem(int position) {
        return characters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.list_fragment, parent, false);
        Character c = characters.get(position);

        TextView nome = (TextView)view.findViewById(R.id.HeroNameTextCharList);
        ImageView imagem = (ImageView)view.findViewById(R.id.thumbNailCharList);

        nome.setText(c.getName());
        imagem.setImageResource(R.drawable.defaultperfil);

        return view;

    }
}
