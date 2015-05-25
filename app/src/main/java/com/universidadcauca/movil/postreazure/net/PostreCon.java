package com.universidadcauca.movil.postreazure.net;

import android.content.Context;

import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;
import com.universidadcauca.movil.postreazure.models.Postre;

import java.util.List;

/**
 * Created by DarioFernando on 19/05/2015.
 */
public class PostreCon extends AzureClient<Postre> implements TableQueryCallback<Postre> {


    public interface PostreConI{
        void onCompleted(Postre entity, Exception exception, ServiceFilterResponse response);
        void onDeleteCompleted(Exception exception, ServiceFilterResponse response);
        void onReadCompleted(List<Postre> result, int count, Exception exception, ServiceFilterResponse response);
    }

    PostreConI postreConI;

    public PostreCon(Context context, PostreConI postreConI) {
        super(context);
        this.postreConI=postreConI;
    }

    @Override
    public Class<Postre> getClassModel() {
        return Postre.class;
    }

    @Override
    public void onCompleted(Exception exception, ServiceFilterResponse response) {
        postreConI.onDeleteCompleted(exception,response);
    }

    @Override
    public void onCompleted(Postre entity, Exception exception, ServiceFilterResponse response) {
        postreConI.onCompleted(entity,exception, response);
    }

    @Override
    public void onCompleted(List<Postre> result, int count, Exception exception, ServiceFilterResponse response) {
        postreConI.onReadCompleted(result,count,exception,response);
    }
}
