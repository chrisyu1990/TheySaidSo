package theysaidso.com.theysaidso.HttpController;

import android.app.Activity;
import android.content.Context;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import theysaidso.com.theysaidso.Json.Parser;

/**
 * Created by chrisyu on 15/12/10.
 */
public class CustomCallBack<T> implements Callback {
    public static boolean httpRequestOnGoing;
    private Context context;
    private CustomRunnable runnable;
    final Class<T> typeParameterClass;
    Parser parser;

    public CustomCallBack(Class<T> typeParameterClass, CustomRunnable runnable, Context context) {
        this.context = context;
        this.runnable = runnable;
        this.typeParameterClass = typeParameterClass;
        parser = new Parser<T>(typeParameterClass);
    }

    @Override
    public void onResponse(Response response) throws IOException {
        runnable.url = response.request().urlString();
        String dataString = response.body().string();
        runnable.data = parser.getObject(dataString);

        //very limited offline caching
        //if(OfflineData.get(runnable.url, context) == null)
        //    OfflineData.save(runnable.url, dataString, context);

        runOnUiThread();
        CustomCallBack.httpRequestOnGoing = false;
    }

    @Override
    public void onFailure(Request request, IOException e) {
        runnable.url = request.urlString();

        //if(OfflineData.get(runnable.url, context) != null) {
        //    runnable.data = parser.getObject(OfflineData.get(runnable.url, context));
        //    runOnUiThread();
        //}

        CustomCallBack.httpRequestOnGoing = false;
        //prompt error
    }

    public void runOnUiThread() {
        ((Activity)context).runOnUiThread(runnable);
    }
}
