package es.marianogl.lista_compra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class listacompra extends AppCompatActivity {
    private ListView mShoppingList;
    private EditText mItemEdit;
    private Button mAddButton;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_compra);
        //*******************************************************************************
        //------------------------DECLARACIÓN DE VARIABLES-------------------------------
        //*******************************************************************************

        mShoppingList = (ListView) findViewById(R.id.shopping_listView);
        mItemEdit = (EditText) findViewById(R.id.item_editText);
        mAddButton = (Button) findViewById(R.id.add_button1);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mShoppingList.setAdapter(mAdapter);

        //*******************************************************************************
        //-----------------------FIN DECLARACIÓN DE VARIABLES----------------------------
        //*******************************************************************************

        //-------------------------------------------------------------------------------

        //*******************************************************************************
        //------------------------------AÑADIR ITEM A LA LISTA---------------------------
        //*******************************************************************************
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEdit.getText().toString();
                mAdapter.add(item);
                mAdapter.notifyDataSetChanged();
                mItemEdit.setText ("");
            }
        });
        //*******************************************************************************
        //-------------------------------FIN DE AÑADIR-----------------------------------
        //*******************************************************************************
        //-------------------------------------------------------------------------------
        //*******************************************************************************
        //------------------------------ELIMINAR ITEM A LA LISTA---------------------------
        //*******************************************************************************






        //*******************************************************************************
        //-------------------------------FIN DE ELIMINAR-----------------------------------
        //*******************************************************************************



    }



}