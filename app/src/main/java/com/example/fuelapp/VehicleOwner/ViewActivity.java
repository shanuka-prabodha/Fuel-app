package com.example.fuelapp.VehicleOwner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Login.LoginActivity;
import com.example.fuelapp.Login.StorageManager;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.Queue;
import com.example.fuelapp.Model.QueueAdd;
import com.example.fuelapp.Model.User;
import com.example.fuelapp.R;
import com.example.fuelapp.Station.Station;
import com.example.fuelapp.Station.StationResponse;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {

    private TextView txtPetrolAvailable, txtDisealAvailable, txtPetrolLength, txtDisealLength ;
    Button btnMoreInfo, btnDMoreInfo , refreshPage;
    ImageButton btnPetrolView, btnDisealView;
    TextView  locationName, fuelTypeName;
    Button ptJoin , dsJoin , ptExitBefore, ptExitAfter , dsExitBefore , dsExitAfter;

    int pbike_api = 0;

    Integer pqueue ;
    Integer dqueue ;

    Station headStation =new Station();
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        StorageManager storeManager = new StorageManager(getApplicationContext());

        storeManager.getToken();

        getStationDataByID(id);
        getUserDetails(storeManager.getToken());

        txtPetrolAvailable = findViewById(R.id.pAvailable);
        txtDisealAvailable = findViewById(R.id.dAvailable);
        txtPetrolLength = findViewById(R.id.pLength);
        txtDisealLength = findViewById(R.id.dLength);
        btnMoreInfo = findViewById(R.id.btnPMore);
        btnDMoreInfo = findViewById(R.id.btnDMore);
        btnPetrolView = findViewById(R.id.pViewBtn);
        btnDisealView = findViewById(R.id.dView);
        locationName = findViewById(R.id.locationName);
        refreshPage = findViewById(R.id.refresh);
        fuelTypeName = findViewById(R.id.textView16);

        ptJoin = findViewById(R.id.pJoin);
        dsJoin = findViewById(R.id.dJoin);
        ptExitBefore = findViewById(R.id.pExitBefore);
        ptExitAfter = findViewById(R.id.pExitAfter);
        dsExitBefore = findViewById(R.id.dExitBefore);
        dsExitAfter = findViewById(R.id.dExitAfter);

        // Join button invisible and visible
        ptExitBefore.setVisibility(View.INVISIBLE);
        ptExitAfter.setVisibility(View.INVISIBLE);
        dsExitBefore.setVisibility(View.INVISIBLE);
        dsExitAfter.setVisibility(View.INVISIBLE);

        if(storeManager.getJoinStation() != "" && storeManager.getJoinStation().equals(id)){

            if(storeManager.getFuelType().equals("Petrol")){
                ptJoin.setVisibility(View.INVISIBLE);
                ptExitBefore.setVisibility(View.VISIBLE);
                ptExitAfter.setVisibility(View.VISIBLE);
            }else{
                dsJoin.setVisibility(View.INVISIBLE);
                dsExitBefore.setVisibility(View.VISIBLE);
                dsExitAfter.setVisibility(View.VISIBLE);
            }

        }


        ptJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageManager storeManager = new StorageManager(getApplicationContext());

                if(user.getFueltype().equals("Petrol")){
                    if(storeManager.getQueJoin()){
                        Toast.makeText(ViewActivity.this, "You are already in a queue", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        storeManager.setQueJoin(true, headStation.getId());
                    }

                    ptExitBefore.setVisibility(View.VISIBLE);
                    ptExitAfter.setVisibility(View.VISIBLE);
                    ptJoin.setVisibility(View.INVISIBLE);
                    UpdateStaionQue(1);

                    //get loacal time;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                dtf.format(LocalTime.now());
                System.out.println(dtf.format(LocalTime.now()).toString());

                storeManager.setStartTime(dtf.format(LocalTime.now()).substring(0,8));

                }else{
                    Toast.makeText(ViewActivity.this, "You can't join this queue, you have a Diesel vehicle", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dsJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StorageManager storeManager = new StorageManager(getApplicationContext());


                if (user.getFueltype().equals("Diesel")) {
                    if(storeManager.getQueJoin()){
                        Toast.makeText(ViewActivity.this, "You are already in a queue", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        storeManager.setQueJoin(true, headStation.getId());
                    }

                    dsExitBefore.setVisibility(View.VISIBLE);
                    dsExitAfter.setVisibility(View.VISIBLE);
                    dsJoin.setVisibility(View.INVISIBLE);
                    UpdateStaionQue(1);

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    dtf.format(LocalTime.now());
                    System.out.println(dtf.format(LocalTime.now()));
                    storeManager.setStartTime(dtf.format(LocalTime.now()).substring(0,8));

                }else{
                    Toast.makeText(ViewActivity.this, "You can't join this queue, you have a Petrol vehicle", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //page refresh
        refreshPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getStationDataByID(id);
            }
        });

        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, TimeTrackActivity.class);
                intent.putExtra("station", headStation.getId());
                intent.putExtra("fuelType", "Petrol");
                startActivity(intent);

            }
        });

        btnDMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, TimeTrackActivity.class);
                intent.putExtra("station", headStation.getId());
                intent.putExtra("fuelType", "Diesel");
                startActivity(intent);

            }
        });

        btnPetrolView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Petrol View");
                System.out.println(headStation.getName());
                showPetrolView(headStation.getId(), headStation.getPbike().toString(), headStation.getPcar().toString(), headStation.getPother().toString() , headStation.getPnextarival());

            }
        });

        btnDisealView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDisealView(headStation.getId(), headStation.getDbus().toString(), headStation.getDvan().toString(), headStation.getDother().toString() , headStation.getDnextarival());

            }
        });

        ptExitBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ptExitBefore.setVisibility(View.INVISIBLE);
                ptExitAfter.setVisibility(View.INVISIBLE);
                ptJoin.setVisibility(View.VISIBLE);
                UpdateStaionQue(-1);
                storeManager.setQueJoin(false, "");
            }
        });

        ptExitAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ptExitBefore.setVisibility(View.INVISIBLE);
                ptExitAfter.setVisibility(View.INVISIBLE);
                ptJoin.setVisibility(View.VISIBLE);
                UpdateStaionQue(-1);
                updatQueue("Petrol");
                storeManager.setQueJoin(false, "");



            }
        });

        dsExitBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsExitBefore.setVisibility(View.INVISIBLE);
                dsExitAfter.setVisibility(View.INVISIBLE);
                dsJoin.setVisibility(View.VISIBLE);
                UpdateStaionQue(-1);
                updatQueue("Diesel");
                storeManager.setQueJoin(false, "");
            }
        });

        dsExitAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsExitBefore.setVisibility(View.INVISIBLE);
                dsExitAfter.setVisibility(View.INVISIBLE);
                dsJoin.setVisibility(View.VISIBLE);
                UpdateStaionQue(-1);
                storeManager.setQueJoin(false, "");
            }
        });

    }

    private void getStationDataByID(String id){

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<StationResponse> call = iUserAPI.getStations(id);

        call.enqueue(new Callback<StationResponse>() {
            @Override
            public void onResponse(Call<StationResponse> call, Response<StationResponse> response) {
                Log.e("StationActivity", "getStationDataByID Response code " + response.code());

                if (response.code() == 200) {
                    Log.e("StationActivity", "StationCode " + response.body().getData().getId());
                    SetStationData(response.body().getData());

                } else {
                    Log.e("StationActivity", "Error  " + response.message());
                }
            }

            @Override
            public void onFailure(Call<StationResponse> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });
    }


    private void showPetrolView(final String Id, final String carLength, final String bikeLength, final String otherLength, final String next) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.queue, null);
        builder.setView(view);

        TextView carL = view.findViewById(R.id.carPL);
        carL.setText(carLength);


        TextView bikeL = view.findViewById(R.id.bikePL);
        bikeL.setText(bikeLength);

        TextView otherL = view.findViewById(R.id.otherPL);
        otherL.setText(otherLength);

        TextView nextArrival = view.findViewById(R.id.pTime);

        String date = next.substring(0,10);
        String time = next.substring(11,16);

        nextArrival.setText(date + " " + time);

        final Button button = (Button) view.findViewById(R.id.queueOkBtn);


        builder.setTitle("");

        final AlertDialog alert = builder.create();
        alert.show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


    }

    private void showDisealView(final String Id, final String busLength, final String vanLength,  final String otherLength, final String next) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.diseal_queue, null);
        builder.setView(view);

        TextView carL = view.findViewById(R.id.carPL);
        carL.setText(busLength);

        TextView vanL = view.findViewById(R.id.vanPL);
        vanL.setText(vanLength);

        TextView otherL = view.findViewById(R.id.otherPL);
        otherL.setText(otherLength);


        TextView nextArrival = view.findViewById(R.id.dTime);

        String date = next.substring(0,10);
        String time = next.substring(11,16);

        nextArrival.setText(date + " " + time);

        final Button button = (Button) view.findViewById(R.id.queueOkBtn);


        builder.setTitle("");

        final AlertDialog alert = builder.create();
        alert.show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });
    }

    private void SetStationData(Station station){

        headStation = station;

        if (station.getIspetrol()) {
            txtPetrolAvailable.setText("Available");
        } else {
            txtPetrolAvailable.setText("Finished");
        }

        if (station.getIsdiesel()) {
            txtDisealAvailable.setText("Available");
        } else {
            txtDisealAvailable.setText("Finished");
        }


        int petrolQueue = station.getPcar()+ station.getPbike() + station.getPother();
        int disealQueue = station.getDvan() + station.getDbus() + station.getDother();

        pqueue = new Integer(petrolQueue);
        dqueue = new Integer(disealQueue);

        txtPetrolLength.setText(pqueue.toString());
        txtDisealLength.setText(dqueue.toString());
        locationName.setText(station.getName());

        Log.e("StationActivity", "Station Lenght " + pqueue);

    }

    private void getUserDetails(String id){

            IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
            Call<User> call = iUserAPI.getOneUser(id);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.e("StationActivity", "Response code " + response.code());
                    if (response.code() == 200) {
                       user = response.body();
                       // fuelTypeName.setText(user.getFueltype());
                    } else {
                        Log.e("StationActivity", "Exit ");
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("RegisterActivity", String.valueOf(t));
                }
            });
    }

    private void UpdateStaionQue(int type){

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<StationResponse> call = iUserAPI.updateStationQue(headStation.getId(), type , user);

        call.enqueue(new Callback<StationResponse>() {
            @Override
            public void onResponse(Call<StationResponse> call, Response<StationResponse> response) {
                Log.e("StationActivity", "UpdateStaionQue Response code " + response.code());
                if (response.code() == 200) {

                    getStationDataByID(headStation.getId());
                } else {
                    Log.e("StationActivity", "UpdateStaionQue Error  " + response.body().getMsg());
                }
            }
            @Override
            public void onFailure(Call<StationResponse> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        Intent in = new Intent(ViewActivity.this, LoginActivity.class);
        startActivity(in);
        StorageManager storeManager = new StorageManager(getApplicationContext());
        storeManager.setToken("");
        storeManager.setFuelType("");
        storeManager.setQueJoin(false ,"");

        Toast.makeText(this, "You logged out", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    public void updatQueue(String fuelType){
        StorageManager storeManager = new StorageManager(getApplicationContext());
        Queue queue = new Queue(headStation.getId(),user.getId(),user.getVehicleno(),fuelType,storeManager.getStartTime(),LocalTime.now().toString().substring(0,8));

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<QueueAdd> call = iUserAPI.addQueue(queue);

        call.enqueue(new Callback<QueueAdd>() {
            @Override
            public void onResponse(Call<QueueAdd> call, Response<QueueAdd> response) {
                Log.e("StationActivity", "updatQueue Response code " + response.code());
                if (response.code() == 200) {
                    Log.e("StationActivity", "updatQueue Success  " + response.body().getMsg());
                } else {
                    Log.e("StationActivity", "updatQueue Error  " + response.body().getMsg());
                }
            }
            @Override
            public void onFailure(Call<QueueAdd> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });


    }


}