package com.tabian.firebasegooglesignin;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Add extends AppCompatActivity {
    private static final String tag_json_obj = "jobj_req";
    private String TAG = Add.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);



        findViewById(R.id.add_id).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                final String myEmail = prefs.getString("myEmail","ajaykedia1992@gmail.com");
                final String myPhone = prefs.getString("myPhone", "1234567890");


                EditText Amt= (EditText) findViewById(R.id.amount_id1);
                String amot = String.valueOf(Amt.getText());
                // Double amount = Double.valueOf(amot);
                add(amot,myEmail,myPhone);
            }
        });
    }

    void add(String  amount, String myemail, String myphone){

        String url = Const.URL_ADD;
        JSONObject params = new JSONObject();

        /*
        {
          "moneyInformation": {
            "phoneNo": "",
            "emailId": "",
            "receiverEmailId": "",
            "receiverPhoneNo": "",
            "transactionMoney": 0
          },
          "responseProtocol": "",
          "requestProtocol": ""
        }
        Click to set as parameter value
        Response Messages
         */

        doJsonObjReq(Const.REQUEST_TOKEN,
                url + "?"+"email_id="+myemail+"&phone_no="+myphone+"&transaction_amount="+amount,
                Request.Method.PUT);
    }
    public void doJsonResponseHandling(int myrequest, JSONObject response) {
        JSONObject a = response;
    }

    private void handleErrorResponse(VolleyError error){
        // TO DO: Add error response
        String text = "Error! Error! Error!";

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
                            // JSONObject a = response;
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
                // handleErrorResponse(1, error);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
