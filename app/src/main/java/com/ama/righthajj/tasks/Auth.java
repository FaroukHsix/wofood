package com.ama.righthajj.tasks;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.ama.righthajj.controller.NavigationDrawer;
import com.ama.righthajj.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by hammo on 8/2/2018.
 */

public class Auth {

    private Context context;


    public Auth(Context context) {
        this.context = context;
    }

    public void getData (String id,String route ){

        Ion.with(context)
                .load(route)
                .addHeader("Accept","application/json")
                .setBodyParameter("id",id)
                .asJsonObject().
                setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e != null )
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();


                else {
                    boolean error = result.get("error").getAsBoolean();
                    if (error){
                        Toast.makeText(context, "not recognized", Toast.LENGTH_SHORT).show();
                    }else{
                        JsonObject userData = result.get("payload").getAsJsonObject();
                        User data = new Gson().fromJson(userData, User.class);

                        Toast.makeText(context, "Identified", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, NavigationDrawer.class);
                        i.putExtra("user", data);
                        context.startActivity(i);


                    }

                }



            }
        });

    }

}
