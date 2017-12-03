package com.tabian.firebasegooglesignin;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
df

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pay extends AppCompatActivity {

    static final Object lock=new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

// SharedPreference
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);



        findViewById(R.id.transfer_id).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                final String myEmail = prefs.getString("myEmail","");
                final String myPhone = prefs.getString("myPhone", "1234567890");

                EditText othemail = (EditText) findViewById(R.id.email_id);
                EditText othphone= (EditText) findViewById(R.id.phone_id);
                EditText Amt= (EditText) findViewById(R.id.amount_id);
                String otheremail = String.valueOf(othemail.getText());
                String otherphone = String.valueOf(othphone.getText());
                String amot = String.valueOf(Amt.getText());
                Double amount = Double.valueOf(amot);
                pay(amount,myEmail,otheremail,myPhone,otherphone);
            }
        });





    }

    void pay(Double  amount, String myemail,String otheremail,String myphone,String otherphone){



    }

    private void doJsonResponseHandling(int myRequestType, JSONObject response) {


            try {
                synchronized (lock) {
                    String activityId = response.getString("id");
                    Long calories = response.getLong("calories");
                    String startDateStandard = response.getString("start_date");
                    String startDateLocal = response.getString("start_date_local");

                }


            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

    public void doJsonObjReq(final int myRequestType, String url, int method) {
        // finishedReqJSONObj = false;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Log.d(TAG, response.toString());

                        try {
                            doJsonResponseHandling(myRequestType, response);
                        }
                        catch (Exception exp) {
                            //TODO: write good error handler
                            exp.getStackTrace();
                        }
                        finally {
                            // finishedReqJSONObj = true;
                        }
                        // hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // throw;
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                NetworkResponse response = error.networkResponse;
                handleErrorResponse(myRequestType, error);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


}
