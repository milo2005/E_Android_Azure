package com.universidadcauca.movil.postreazure.net;

import android.content.Context;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableDeleteCallback;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by DarioFernando on 19/05/2015.
 */
public abstract class AzureClient<T> implements TableOperationCallback<T>, TableDeleteCallback, TableQueryCallback<T> {

    MobileServiceClient client;

    public AzureClient(Context context){

        try {
            client = new MobileServiceClient("https://test-dar.azure-mobile.net/",
                    "xcgYdwnyOYUYPkmZRlZDskxNdxqcHe41",
                    context);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void insert(T item){
        getTable().insert(item, this);
    }

    public void update(T item){
        getTable().update(item, this);
    }

    public void delete(T item){
        getTable().delete(item, this);
    }

    public void getAllItems(){
        getTable().execute(this);
    }

    public void getLastItems(int size){
        getTable().skip(size).execute(this);
    }

    public void getItemsByColumnFragment(String columnName, String valueFragment){
        getTable().where().subStringOf(valueFragment, columnName);
    }

    public abstract Class<T> getClassModel();


    public MobileServiceTable<T> getTable(){
        return client.getTable(getClassModel());
    }

    @Override
    public abstract void onCompleted(T entity, Exception exception, ServiceFilterResponse response);

    @Override
    public abstract void onCompleted(Exception exception, ServiceFilterResponse response);

    @Override
    public abstract void onCompleted(List<T> result, int count, Exception exception, ServiceFilterResponse response);
}
