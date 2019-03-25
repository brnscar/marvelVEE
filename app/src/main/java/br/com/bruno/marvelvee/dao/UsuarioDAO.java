package br.com.bruno.marvelvee.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.bruno.marvelvee.model.Usuario;

public class UsuarioDAO extends SQLiteOpenHelper {

    public UsuarioDAO(Context context) {
        super(context, "MarvelVEE", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      String sql = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY, nome TEXT NOT NULL," +
                " sobrenome TEXT, email EMAIL, password VARCHAR);";
       db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS Usuarios";
            db.execSQL(sql);
            onCreate(db);
    }

    public void save (Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", usuario.getNome());
        dados.put("sobrenome", usuario.getSobrenome());
        dados.put("email", usuario.getEmail());
        dados.put("password", usuario.getPassword());

        db.insert("Usuarios", null, dados );
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario user = new Usuario();
        user.setNome("nome");
        user.setEmail("email");

        usuarios.add(user);

        return usuarios;
    }
}
