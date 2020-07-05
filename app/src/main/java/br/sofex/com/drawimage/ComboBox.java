package br.sofex.com.drawimage;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ComboBox {

    Context mContext;
    Spinner spin;

    public ComboBox(Context context) {
        this.mContext = context;
    }
    public Spinner ComboBox( List<String> Lista_de_Itens, Spinner Nome_Spinner,Integer Gerar_Linha_Selecione_Intog){
        //TODO: Combobox de ações principais
        // Creating adapter for spinner

        //TODO: Gera o primeiro item com um texto já definido
        List<String> ListAux = new ArrayList<>();
        if(Gerar_Linha_Selecione_Intog == 1){
            ListAux.add("Selecione uma opção");
            for(String x : Lista_de_Itens)
            {ListAux.add(x);}
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext,R.layout.spin_center_item,R.id.tv_center_item, ListAux);
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
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext,R.layout.spin_center_item,R.id.tv_center_item, Lista_de_Itens);
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


}
