package es.proyecto_final.lista_compra;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static ListView listView;
    EditText input;
    ImageView enter;
    static ListViewAdapter adapter;
    static ArrayList<String> items;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.add);
        context = getApplicationContext();


        // add hardcoded items to grocery list
        items = new ArrayList<>();
        /*
        items.add("Manzana");
        items.add("Platano");
        items.add("Naranja");
        items.add("Fresa");
        items.add("Kiwi");
        */

        listView.setLongClickable(true);
        adapter = new ListViewAdapter(this, items);
        listView.setAdapter(adapter);

        // Muestra el nombre del elento cuando le clicas
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, clickedItem, Toast.LENGTH_SHORT).show();
            }
        });
        // Cuando lo mantienes pulsado borra el elemento
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeItem(i);
                return false;
            }
        });

        // Funcion para añadir un elemento a la lista
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (text == null || text.length() == 0) { //Si esta vacio el campo cuando le das a añadir genera el texto
                    makeToast("Introduce un artículo");
                } else {
                    addItem(text);
                    input.setText("");
                    makeToast(text + " añadido a la lista");
                }
            }
        });
        loadContent();
    }


    //Lee la lista del fichero y lo muestra en LisView
    public void loadContent() {
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, "list.txt");
        byte[] content = new byte[(int) readFrom.length()];

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(readFrom);
            stream.read(content);

            String s = new String(content);
            // [Apple, Banana, Kiwi, Strawberry]
            s = s.substring(1, s.length() - 1);
            String split[] = s.split(", ");

            // Por si no hay nada en la lista
            if (split.length == 1 && split[0].isEmpty())
                items = new ArrayList<>();
            else items = new ArrayList<>(Arrays.asList(split));

            adapter = new ListViewAdapter(this, items);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //El override se utiliza para que no borre el contenido de la lista cuando se cierra la aplicacion
    @Override
    protected void onDestroy() {
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, "list.txt"));
            writer.write(items.toString().getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    //Funcion para borrar un item
    public static void removeItem(int i) {
        makeToast( items.get(i) + " Eliminado");
        items.remove(i);
        listView.setAdapter(adapter);
    }

    // Funcion para añadir un item por el nombre introducido
    public static void addItem(String item) {
        items.add(item);
        listView.setAdapter(adapter);
    }

    // Esta funcion genra los mensajes que salen abajo
    static Toast t;

    private static void makeToast(String s) {
        if (t != null) t.cancel();
        t = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        t.show();
    }
}