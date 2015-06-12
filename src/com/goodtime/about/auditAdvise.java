package com.goodtime.about;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.MicroERP_1_0_0.MainActivity;
import com.example.MicroERP_1_0_0.R;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongcy on 2015/6/11.
 */
public class auditAdvise  extends Activity {
    // Progress Dialog
    private ProgressDialog pDialog;
    private TextView tv_head;
    JSONParser jsonParser = new JSONParser();
    EditText inputName;
    EditText inputEmail;
    EditText inputDesc;
    Button upback;

    // url to create new product
    private static String url_up = "http://192.168.53.27/kode/data.php";
    private static final String TAG_MESSAGE = "message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advise);
        tv_head = (TextView)findViewById(R.id.tv_head);
        tv_head.setText("建议");
        // Edit Text
        inputName = (EditText) findViewById(R.id.inputName);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputDesc = (EditText) findViewById(R.id.inputDesc);
        upback = (Button) findViewById(R.id.upback);
        upback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent back = new Intent(auditAdvise.this, MainActivity.class);
                back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                auditAdvise.this.finish();
            }
        });

        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);
        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // creating new product in background thread
                if(validate()){
                    new Up().execute();
                }
            }
        });
    }
    private boolean validate()
    {
        String description = inputDesc.getText().toString().trim();
        if (description.equals(""))
        {
            DialogUtil.showDialog(this, "没有填写建议", false);
            return false;
        }

        return true;
    }
    /**
     * Background Async Task to Create new product
     * */
    class Up extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(auditAdvise.this);
            pDialog.setMessage("正在提交");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String name = inputName.getText().toString();
            String email = inputEmail.getText().toString();
            String description = inputDesc.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("description", description));

            // getting JSON Object
            // Note that create product url accepts POST method
            try{
                JSONObject json = jsonParser.makeHttpRequest(url_up,
                        "POST", params);
                String message = json.getString(TAG_MESSAGE);
                return message;
            }catch(Exception e){
                e.printStackTrace();
                return "";
            }
            // check for success tag


        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String message) {
            pDialog.dismiss();
            //message Ϊ����doInbackground�ķ���ֵ
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();


        }
    }
}
