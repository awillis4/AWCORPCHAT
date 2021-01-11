package com.example.awchatproj2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//author citations: google developer training, tutorials point, simplified coding, codelabs android fundamentals, mateusz

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;private EditText editTextEmail;private EditText editTextPhone;
    private EditText editTextPassword;
    private Button Register;private Button Login;
    private BroadcastReceiver broadcastReceiver;
    private static final String URL_STORE_TOKEN="https://192.168.43.207/FcmExample/storeFcmToken.php";
    private EditText InputUserEmail;private EditText InputUserPhone;private EditText InputUserPassword;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);
       // textView=(TextView) findViewById(R.id.textViewToken);
        InputUserEmail=(EditText)findViewById(R.id.editTextEmail);
        InputUserPhone=(EditText)findViewById(R.id.editTextPhone);
        InputUserPassword=(EditText)findViewById(R.id.editTextPassword);
        Register=(Button)findViewById(R.id.Register);
        Login=(Button)findViewById(R.id.Login);
        

        View v = findViewById(R.id.Register);v.setOnClickListener(this);}
    @Override public void onClick(View arg0){if(arg0.getId()==R.id.Register){
        Intent intent=new Intent(this,SecondActivity.class);this.startActivity(intent);}

        Register.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View view){sendTokenToServer();}});
        broadcastReceiver=new BroadcastReceiver(){@Override
            public void onReceive(Context context, Intent intent){}};
                //textView.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());}};

       // if(SharedPrefManager.getInstance(this).getToken()!=null){
           // textView.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
          //  Log.d("myfcmtokenshared",SharedPrefManager.getInstance(this).getToken());}
        registerReceiver(broadcastReceiver,new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));}

    private void sendTokenToServer(){
        final String email=editTextEmail.getText().toString().trim();final String phone=editTextPhone.getText().toString().trim();
    if(TextUtils.isEmpty(email)||TextUtils.isEmpty(phone)){
        Toast.makeText(this,"Input email and phone, please",Toast.LENGTH_LONG).show();}
    else if(SharedPrefManager.getInstance(this).getToken()!=null){//String URL_STORE_TOKEN=null;
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_STORE_TOKEN,
                new Response.Listener<String>(){@Override public void onResponse(String response){try{
                JSONObject obj=new JSONObject(response);Toast.makeText(getApplicationContext(),
                            obj.getString("message"),Toast.LENGTH_LONG).show();}catch(JSONException e){e.printStackTrace();}}},
                new Response.ErrorListener(){@Override public void onErrorResponse(VolleyError error){
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();}}){

        @Override protected Map<String,String> getParams() throws AuthFailureError{
            Map<String,String>params=new HashMap<>();
            params.put("token",SharedPrefManager.getInstance(getApplicationContext()).getToken());
        params.put("email",email);return params;}};

                //new Response.Listener<String>(){@Override public void onResponse(String response){}},
        //new Response.ErrorListener(){@Override public void onErrorResponse(VolleyError error){}});
        RequestQueue requestQueue= Volley.newRequestQueue(this);requestQueue.add(stringRequest);}
        else{Toast.makeText(this,"Token not generated",Toast.LENGTH_LONG).show();}}


    public void SecondActivity(View view) { }
}