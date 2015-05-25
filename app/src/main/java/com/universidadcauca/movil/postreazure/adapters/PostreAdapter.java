package com.universidadcauca.movil.postreazure.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.universidadcauca.movil.postreazure.R;
import com.universidadcauca.movil.postreazure.models.Postre;

import java.util.List;

/**
 * Created by DarioFernando on 19/05/2015.
 */
public class PostreAdapter extends BaseAdapter {


    Context context;
    List<Postre> data;

    int widthImg, heightImg;

    public PostreAdapter(Context context, List<Postre> data) {
        this.context = context;
        this.data = data;

        WindowManager wM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wM.getDefaultDisplay();

        DisplayMetrics metrics =  new DisplayMetrics();

        display.getMetrics(metrics);

        widthImg = display.getWidth();
        heightImg = context.getResources().getDimensionPixelSize(R.dimen.template_img_height);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView != null)
            v = convertView;
        else
            v =  View.inflate(context, R.layout.template_postre,null);

        Postre p = (Postre) getItem(position);

        TextView txt = (TextView) v.findViewById(R.id.title);
        txt.setText(p.getNombre());

        txt = (TextView) v.findViewById(R.id.description);
        txt.setText(p.getDescripcion());

        txt = (TextView) v.findViewById(R.id.price);
        txt.setText(""+p.getPrecio());

        txt = (TextView) v.findViewById(R.id.time);
        txt.setText(p.getDuracion());

        ImageView img = (ImageView) v.findViewById(R.id.img);

        Log.i("picasso","url="+p.getImgurl());
        Picasso.with(context).load(p.getImgurl()).resize(widthImg,heightImg).into(img);

        return v;
    }
}
