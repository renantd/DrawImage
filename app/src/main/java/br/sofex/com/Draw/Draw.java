package br.sofex.com.Draw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Draw extends View {

    Context mContext;
    Activity activity;
    private Paint paint;
    public List<String> ListaPontosMarcados = new ArrayList<>();
    public List<String> list2 = new ArrayList<>();
    private List<Point> points = new ArrayList<>();
    public int count = 0;


    public Draw(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public Draw(Context context, @Nullable AttributeSet attrs)
    {
        super(context,attrs);
        this.mContext = context;
        initView();
    }

    private void initView()
    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#0AFD00"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(5f);
        paint.setTextSize(80);
    }

    @Override
    public boolean onTouchEvent( MotionEvent m) {
        int pointerCount = m.getPointerCount();
        for (int i = 0; i < pointerCount; i++)
        {
            int action = m.getActionMasked();
            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    count++;
                    Point p = new Point();
                    p.x = (int) m.getX(i);
                    p.y = (int) m.getY(i);

                    ListaPontosMarcados.add("P"+count+"X"+(int) m.getX(i)+" P"+count+"Y"+(int) m.getY(i));
                    points.add(p);
                    break;
            }
        }
        invalidate();
        return true;
    }
    public void DeleteAllCanvas(){
        points.clear();
        ListaPontosMarcados = null;
        invalidate();
    }
    public void DeletePonto(String StringLista){
        List<String> list1 = new ArrayList<>();
        List<String> listFinal = new ArrayList<>();
        int count = 0;
        for(String str :  ListaPontosMarcados)
        {
            if(!str.contains(StringLista))
            {list1.add(str);}
        }
        points.clear();
        for(String k : list1){
          redrawPoints(RefactorCoordX(k),RefactorCoordY(k));
        }
        for(String str : list1)
        {
          count++;
          listFinal.add("P"+count+"X"+RefactorCoordX(str)+" P"+count+"Y"+RefactorCoordY(str));
        }
        //Log.e("App1","listFinal: "+listFinal);
        ListaPontosMarcados = listFinal;
        //Log.e("App1","ListaPontosMarcados: "+list1);

    }
    public List<String> UpdatelistPontos(){return list2 = ListaPontosMarcados; }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        count = 0;
        for (Point point : points) {
            //i++;
            count ++;
            canvas.drawCircle(point.x, point.y, 20, paint);
            paint.setTextSize(60);
            canvas.drawText("P"+count,point.x-25, point.y-25,paint);
            invalidate();
        }

    }
    public String NomePonto(String ListaRow){
        return ListaRow.substring(0, ListaRow.indexOf("X"));
    }
    public Integer RefactorCoordX(String str){
        String str4 = "";
        try{
            String Pont_Cont = str.substring(0, str.indexOf("X")+1);
            String requiredString = str.replaceAll(Pont_Cont, "");
            str4 = requiredString.substring(0,  requiredString.indexOf(" "));
        }
        catch(NullPointerException e){
            Log.e("Error :","Error :"+e);
        }
        return Integer.parseInt(str4);
    }
    public Integer RefactorCoordY(String str){
        Integer x = Integer.parseInt(str.substring(str.lastIndexOf("Y") + 1));
        //Log.e("App123"," Y "+str);
        return x;
    }
    public void redrawPoints(int x , int y){
        Point p = new Point();
        p.x = x;
        p.y = y;
        points.add(p);
        invalidate();
    }
    public void setColorPoint(String parserColorCode) {
        paint.setColor(Color.parseColor(parserColorCode));
    }

}
