package com.universidadcauca.movil.postreazure;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.universidadcauca.movil.postreazure.adapters.PostreAdapter;
import com.universidadcauca.movil.postreazure.models.Postre;
import com.universidadcauca.movil.postreazure.net.PostreCon;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements PostreCon.PostreConI {

    PostreCon postreCon;

    ListView list;
    PostreAdapter adapter;
    List<Postre> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        data= new ArrayList<>();
        adapter = new PostreAdapter(this,data);
        list.setAdapter(adapter);


        postreCon = new PostreCon(this, this);
        postreCon.getAllItems();


    }

    //region OptionMenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_add) {

            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region Azure Callback
    @Override
    public void onCompleted(Postre entity, Exception exception, ServiceFilterResponse response) {

    }

    @Override
    public void onDeleteCompleted(Exception exception, ServiceFilterResponse response) {

    }

    @Override
    public void onReadCompleted(List<Postre> result, int count, Exception exception, ServiceFilterResponse response) {
        data.clear();
        for(int i=0;i<result.size();i++){
            data.add(result.get(i));
        }
        adapter.notifyDataSetChanged();
    }
    //endregion
}
