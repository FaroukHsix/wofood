package com.ama.righthajj.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ama.righthajj.controller.NavigationDrawer;
import com.ama.righthajj.controller.Tawaf;
import com.ama.righthajj.model.Gate;
import com.ama.righthajj.model.Response;
import com.ama.righthajj.model.User;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by hammo on 8/2/2018.
 */

public class Task {

    private Context context;


    public Task(Context context) {
        this.context = context;
    }

    public void getData(String id, String route, final ProgressBar bar) {
        bar.setVisibility(View.VISIBLE);
        Ion.with(context)
                .load("POST", route)
                .addHeader("accept", "application/json")
                .setBodyParameter("id", id)
                .as(new TypeToken<Response<User>>() {
                })
                .setCallback(new FutureCallback<Response<User>>() {
                    @Override
                    public void onCompleted(Exception e, Response<User> result) {
                        Log.i("object", result + "");
                        bar.setVisibility(View.GONE);
                        if (e != null) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            if (result.isError()) {
                                Toast.makeText(context, "not recognized", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Identified", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(context, NavigationDrawer.class);
                                i.putExtra("user", result.getPayload());
                                context.startActivity(i);
                            }
                        }


                    }

                });


    }

    public void getStatus(String route, final TextView text, final ProgressBar bar) {

        Ion.with(context)
                .load("POST", route)
                .addHeader("accept", "application/json")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        bar.setVisibility(View.GONE);
                        if (e != null) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            text.setVisibility(View.VISIBLE);
                            Log.i("result", result + "");
                            int houjjajNbr = Integer.parseInt(result.get("number").getAsString());
                            if (houjjajNbr < 29500)
                                text.setText(" available");
                            else
                                text.setText("not available");
                        }

                    }
                });


    }

    public void getGatesStatus(String route) {
        Ion.with(context)
                .load("GET", route)
                .addHeader("accept", "application/json")

                .as(new TypeToken<ArrayList<Gate>>() {
                })
                .setCallback(new FutureCallback<ArrayList<Gate>>() {
                    @Override
                    public void onCompleted(Exception e, ArrayList<Gate> gates) {
                        if (e != null) {
                            e.printStackTrace();
                        } else {
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("gates",gates);
                            Intent i = new Intent(context, Tawaf.class);
                            i.putExtras(bundle);
                            context.startActivity(i);
                        }

                    }
                });
    }


}
