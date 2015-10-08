package com.foo.oosd2015p3c;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String wsHost = "192.168.1.28"; //"10.163.101.212";
    int    wsPort = 8080;
    String wsName = "OOSD2015P3B";
    String wsPath = wsPathInit();
    public String getWsHost() { return wsHost; }
    public void setWsHost(String wsHost) {
        this.wsHost = wsHost;
        setWsPath(wsPathInit());
    }
    public int getWsPort() { return wsPort; }
    public void setWsPort(int wsPort) {
        this.wsPort = wsPort;
        setWsPath(wsPathInit());
    }
    public String getWsName() { return wsName; }
    public void setWsName(String wsName) {
        this.wsName = wsName;
        setWsPath(wsPathInit());
    }
    public String getWsPath() { return wsPath; }
    public void setWsPath(String wsPath) { this.wsPath = wsPath; }
    private String wsPathInit()
    {
        return "http://" + wsHost + (wsPort > 0 ? ":" + wsPort : "") + "/" + wsName + "/";
    }
    TextView tvAgency;
    TextView tvAgent;
    EditText etAgentId;
    Button button;
    String agentId;
    //StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAgency = (TextView)findViewById(R.id.tvAgency);
        new GetAgency().execute();
        tvAgent = (TextView)findViewById(R.id.tvAgent);
        etAgentId = (EditText)findViewById(R.id.etAgentId);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agentId = etAgentId.getText().toString();
                new GetAgent().execute();
            }
        });
    }

    class GetAgency extends AsyncTask<Void,Void,Void>
    {
        StringBuilder builder = new StringBuilder();
        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(wsPath + "GetAgencyJSON?id=0");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                JSONObject obj = new JSONObject(builder.toString());
                tvAgency.setText(obj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(aVoid);
        }
    }

    class GetAgent extends AsyncTask<Void,Void,Void>
    {
        StringBuilder builder = new StringBuilder();
        @Override
        protected Void doInBackground(Void... params) {
            try {
                //URL url = new URL("http://" + hostname + ":8080/OOSD2015P3B/GetCustomerJSON?id=" + custid);
                //URL url = new URL("http://" + hostname + ":8080/OOSD2015P3B/rest/getrest/" + custid);
                URL url = new URL(getWsPath() + "GetAgentJSON?id=" + agentId);
                Log.d("GetAgent", "URL=" + url.toString());
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Accept","application/json");
                conn.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = br.readLine()) != null)
                {
                    builder.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                JSONObject obj = new JSONObject(builder.toString());
                tvAgent.setText(obj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
