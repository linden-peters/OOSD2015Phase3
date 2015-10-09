package com.foo.oosd2015p3c;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private String getWsPath()
    {
        return "http://" + prefs.getString("pref_wsHost","") + ":" + prefs.getString("pref_wsPort","") + "/" + prefs.getString("pref_wsName","") + "/";
    }
    TextView tvAgency;
    TextView tvAgent;
    EditText etAgentId;
    Button button;
    String agentId;
    //StringBuilder builder = new StringBuilder();
    ListView lvAgency;
    ListView lvAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_main);
        tvAgency = (TextView)findViewById(R.id.tvAgency);
        //new GetAgency().execute();
        tvAgent = (TextView)findViewById(R.id.tvAgent);
        //etAgentId = (EditText)findViewById(R.id.etAgentId);
        //button = (Button)findViewById(R.id.button);
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agentId = etAgentId.getText().toString();
                new GetAgency().execute();
                new GetAgent().execute();
            }
        });
        */
        lvAgency = (ListView)findViewById(R.id.lvAgency);
        lvAgent = (ListView)findViewById(R.id.lvAgent);
        new GetAgency().execute();
        new GetAgent().execute();
    }

    class GetAgency extends AsyncTask<Void,Void,Void>
    {
        StringBuilder builder = new StringBuilder();
        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(getWsPath() + "GetAgencyJSON?id=0");
                Log.d("GetAgency", "URL=" + url.toString());
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
                JSONObject objOuter = new JSONObject(builder.toString());
                //tvAgency.setText(objOuter.toString());
                StringBuffer listAgency = new StringBuffer();
                for (int i = 0; i < objOuter.length(); i++)
                {
                    if (i > 0) { listAgency.append("\n- - - - - - - - - - - - - - - -\n"); }
                    JSONObject objInner = objOuter.getJSONObject(""+i);
                    //listAgency.append(objInner.toString());

                    listAgency.append("[#" + objInner.getString("AgencyId") + "]\n\t"
                            + objInner.getString("AgncyAddress") + "\n\t"
                            + objInner.getString("AgncyCity") + ", "
                            + objInner.getString("AgncyProv") + "\n\t"
                            + objInner.getString("AgncyCountry") + " "
                            + objInner.getString("AgncyPostal") + "\n\t"
                            + PhoneNumberUtils.formatNumber(objInner.getString("AgncyPhone")));// + "\tF: "
                            //+ objInner.getString("AgncyFax"));
                }
                //Log.d("OOSD",listAgency.toString());
                tvAgency.setText(listAgency.toString());
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
                URL url = new URL(getWsPath() + "GetAgentJSON?id=0");// + agentId);
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
                //JSONObject obj = new JSONObject(builder.toString());
                //tvAgent.setText(obj.toString());
                JSONObject objOuter = new JSONObject(builder.toString());
                //tvAgent.setText(objOuter.toString());
                StringBuffer listAgent = new StringBuffer();
                for (int i = 0; i < objOuter.length(); i++)
                {
                    if (i > 0) { listAgent.append("\n- - - - - - - - - - - - - - - -\n"); }
                    JSONObject objInner = objOuter.getJSONObject(""+i);
                    //listAgency.append(objInner.toString());

                    listAgent.append("[#" + objInner.getString("AgentId") + "] (Agency #"
                            + objInner.getString("AgencyId") + ")\n\t"
                            + objInner.getString("AgtFirstName") + " "
                            + objInner.getString("AgtMiddleInitial") + " "
                            + objInner.getString("AgtLastName") + "\n\t"
                            + objInner.getString("AgtBusPhone") + "\n\t"
                            + objInner.getString("AgtEmail") + "\n\t"
                            + objInner.getString("AgtPosition"));// + "\tF: "
                    //+ objInner.getString("AgncyFax"));

                }
                //Log.d("OOSD",listAgency.toString());
                tvAgent.setText(listAgent.toString());
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
        if (id == R.id.action_refresh) {
            new GetAgency().execute();
            new GetAgent().execute();
            Toast.makeText(this,"Refresh Complete",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        else if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
