package br.sofex.com.drawimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.sofex.com.Draw.Draw;

public class MainActivity extends AppCompatActivity {

    //Button Btn_Clear;
    //Button Btn_DelPoint;
    FloatingActionButton fab_Del;
    FloatingActionButton fab_delAll;
    FloatingActionButton fab_delPoint;

    FloatingActionButton fab_verde;
    FloatingActionButton fab_vermelho;
    FloatingActionButton fab_azul;
    FloatingActionButton fab_preto;
    FloatingActionButton fab_amarelo;

    ImageButton img_btn_del;
    Draw desenhar;
    Spinner Spin_Points;
    RelativeLayout rl_Spin;
    RelativeLayout rl_del1;
    RelativeLayout rl_delbuttons;
    boolean isFABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_verde = findViewById(R.id.fab_verde);
        fab_vermelho = findViewById(R.id.fab_vermelho);
        fab_azul = findViewById(R.id.fab_azul);
        fab_preto = findViewById(R.id.fab_preto);
        fab_amarelo = findViewById(R.id.fab_amarelo);

        img_btn_del  = findViewById(R.id.img_btn_del);
        rl_delbuttons  = findViewById(R.id.rl_delbuttons);
        //Btn_DelPoint = findViewById(R.id.DelPoint);
        Spin_Points  = findViewById(R.id.Spin_Points);
        rl_Spin  = findViewById(R.id.rl_Spin);
        rl_del1   = findViewById(R.id.rl_del1);
        rl_del1.setVisibility(View.GONE);
        rl_delbuttons.setVisibility(View.GONE);
        desenhar = findViewById(R.id.drawimg);

        if(rl_delbuttons.getVisibility() == View.VISIBLE ) {isFABOpen = true;}
        else { isFABOpen = false;}

        fab_verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desenhar.setColorPoint("#19DF0B");
            }
        });
        fab_vermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desenhar.setColorPoint("#F40606");
            }
        });
        fab_azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desenhar.setColorPoint("#0022FF");
            }
        });
        fab_preto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desenhar.setColorPoint("#000000");
            }
        });
        fab_amarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desenhar.setColorPoint("#FFEB3B");
            }
        });

        fab_delAll = findViewById(R.id.fab_delAll);
        fab_delPoint = findViewById(R.id.fab_delPoint);
        fab_Del = findViewById(R.id.fab_Del);
        fab_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    rl_delbuttons.setVisibility(View.VISIBLE);
                }else{
                    rl_delbuttons.setVisibility(View.GONE);
                }
            }
        });

        //Btn_Clear = findViewById(R.id.Btn_Clear);
        fab_delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desenhar.DeleteAllCanvas();
            }
        });

        fab_delPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desenhar.ListaPontosMarcados.size() >= 1)
                {
                    rl_del1.setVisibility(View.VISIBLE);
                    List<String> ListAux;
                    ListAux = ComboBoxPonto(desenhar.ListaPontosMarcados,Spin_Points,1);
                    ComboBox(ListAux,Spin_Points,1);
                }
            }
        });

        img_btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Spin_Points.getSelectedItemPosition() != 0)
                {
                    List<String> list1 = new ArrayList<>();
                    //Log.e("App1","Spin_Points: "+Spin_Points.getSelectedItem().toString());
                    desenhar.DeletePonto(Spin_Points.getSelectedItem().toString());
                    for(String str : desenhar.ListaPontosMarcados){list1.add(str);}
                    list1 = ComboBoxPonto(desenhar.ListaPontosMarcados,Spin_Points,1);
                    //Log.e("App1","List1: "+list1);
                    ComboBox(list1,Spin_Points,1);
                }
                else {Toast.makeText(MainActivity.this, "Opção Inválida ", Toast.LENGTH_SHORT).show();}

            }
        });

    }

    public Spinner ComboBox(List<String> Lista_de_Itens, Spinner Nome_Spinner, Integer Gerar_Linha_Selecione_Intog){
        //TODO: Combobox de ações principais
        // Creating adapter for spinner

        //TODO: Gera o primeiro item com um texto já definido
        List<String> ListAux = new ArrayList<>();
        if(Gerar_Linha_Selecione_Intog == 1){
            ListAux.add("Selecione uma opção");
            for(String x : Lista_de_Itens){ListAux.add(x);}
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.spin_center_item,R.id.tv_center_item, ListAux);
            Log.e("App1","Teste : "+ListAux);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.spin_center_item);


            // attaching data adapter to spinner
            Nome_Spinner.setAdapter(dataAdapter);
            Nome_Spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            Nome_Spinner.setGravity(View.TEXT_ALIGNMENT_CENTER);//Gravity setting for positioning the currently selected item.
            //Nome_Spinner.getBackground().setColorFilter(this.mContext.getResources().getColor(Cor_Seta), PorterDuff.Mode.SRC_ATOP);
        }
        else{
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.spin_center_item,R.id.tv_center_item, Lista_de_Itens);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.spin_center_item);

            // attaching data adapter to spinner
            Nome_Spinner.setAdapter(dataAdapter);
            Nome_Spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            Nome_Spinner.setGravity(View.TEXT_ALIGNMENT_CENTER);//Gravity setting for positioning the currently selected item.
            //Nome_Spinner.getBackground().setColorFilter(this.mContext.getResources().getColor(Cor_Seta), PorterDuff.Mode.SRC_ATOP);
        }

        return Nome_Spinner;
    }
    public List<String> ComboBoxPonto(List<String> Lista_de_Itens, Spinner Nome_Spinner, Integer Gerar_Linha_Selecione_Intog){
        //TODO: Combobox de ações principais
        // Creating adapter for spinner

        List<String> List1 = new ArrayList<>();
        //TODO: Gera o primeiro item com um texto já definido
        List<String> ListAux = new ArrayList<>();
        if(Gerar_Linha_Selecione_Intog == 1){
            ListAux.add("Selecione uma opção");
            for(String x : Lista_de_Itens)
            {ListAux.add(x);}
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.spin_center_item,R.id.tv_center_item, ListAux);
            Log.e("App1","Teste : "+ListAux);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.spin_center_item);


            // attaching data adapter to spinner
            Nome_Spinner.setAdapter(dataAdapter);
            Nome_Spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            Nome_Spinner.setGravity(View.TEXT_ALIGNMENT_CENTER);//Gravity setting for positioning the currently selected item.
            //Nome_Spinner.getBackground().setColorFilter(this.mContext.getResources().getColor(Cor_Seta), PorterDuff.Mode.SRC_ATOP);
        }
        else{
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.spin_center_item,R.id.tv_center_item, Lista_de_Itens);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.spin_center_item);

            // attaching data adapter to spinner
            Nome_Spinner.setAdapter(dataAdapter);
            Nome_Spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            Nome_Spinner.setGravity(View.TEXT_ALIGNMENT_CENTER);//Gravity setting for positioning the currently selected item.
            //Nome_Spinner.getBackground().setColorFilter(this.mContext.getResources().getColor(Cor_Seta), PorterDuff.Mode.SRC_ATOP);
        }

        for(String str : ListAux)
        {
          if(!str.equals("Selecione uma opção"))
          {
              List1.add(NomePonto(str));
              Log.e("App1","Teste : "+str);
          }

        }
        return List1;
    }
    public String NomePonto(String ListaRow){
        return ListaRow.substring(0, ListaRow.indexOf("X"));
    }

}
