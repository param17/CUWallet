package com.tabian.firebasegooglesignin;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Pay extends AppCompatActivity {
    private static final String tag_json_obj = "jobj_req";
    static final Object lock=new Object();
    private String TAG = Pay.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

// SharedPreference
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);



        findViewById(R.id.transfer_id).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                final String myEmail = prefs.getString("myEmail","ajaykedia1992@gmail.com");
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

        String url = Const.URL_SEND;
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

        JSONObject moneyInfoMap = new JSONObject();
        try {
            moneyInfoMap.put("phoneNo", myphone);
            moneyInfoMap.put("emailId", myemail);
            moneyInfoMap.put("receiverEmailId", otheremail);
            moneyInfoMap.put("receiverPhoneNo", otherphone);
            moneyInfoMap.put("transactionMoney", amount);
            params.put("moneyInformation", moneyInfoMap);
            params.put("responseProtocol", Const.PROTOCOL);
            params.put("requestProtocol", Const.PROTOCOL);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        doJsonObjPost(url, params);
        // doJsonObjReq(Const.REQUEST_TOKEN, Const.URL_GET + "?"+"email_id="+otheremail+"&phone_no=424234", Request.Method.GET);
    }


    private HashMap<String, Object> mParams;
    public void doJsonObjPost(String url, JSONObject params) {
        // finishedReqJSONObj = false;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        // msgResponse.setText(response.toString());
                        // hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
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
