package com.example.classactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView name,salary,age;
    EditText input;
    static RequestQueue queue ;

    private static String url = "https://dummy.restapiexample.com/api/v1/employee/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = (TextView) findViewById(R.id.name_id);
        salary = (TextView) findViewById(R.id.salary_id);
        age = (TextView) findViewById(R.id.age_id);
        input = (EditText) findViewById(R.id.edt_id);
        queue = Volley.newRequestQueue(this);



    }


    public void getData(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");

                            String employeeName = data.getString("employee_name");
                            int employeeSalary = data.getInt("employee_salary");
                            int employeeAge = data.getInt("employee_age");

                            name.setText(employeeName);
                            salary.setText(employeeSalary + "");
                            age.setText(employeeAge + "");
                            Toast.makeText(MainActivity.this, name + ", " + salary + "," + age , Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        error.printStackTrace();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);



    }

    public void prevEmployee(View view){
        int prevID = (Integer.parseInt(input.getText().toString()) - 1 );
        String tempUrl = url + prevID;
        input.setText(prevID + "");
        getData(tempUrl);
    }

    public void nextEmployee(View view){
        int nextID = (Integer.parseInt(input.getText().toString()) + 1);
        String tempUrl = url + nextID;
        input.setText(nextID +"");
        getData(tempUrl);
    }

    public void getEmployee(View view){
        String tempUrl = url + input.getText().toString();
        getData(tempUrl);
    }

}