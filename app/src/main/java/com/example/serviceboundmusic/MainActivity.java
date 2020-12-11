package com.example.serviceboundmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.example.serviceboundmusic.MyService.MyBinder;

public class MainActivity extends AppCompatActivity {

    private MyService myService;
    private boolean isBound = false;
    private ServiceConnection connection;
    String[] mobileArray = {"Bai hat 1","Bai hat 2","Bai hat 3","Bai hat 4",
            "Bai hat 5","Bai hat 6","Bai hat 7","Bai hat 8"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);







//        final Button btOn = (Button) findViewById(R.id.btOn);
//        final Button btOff = (Button) findViewById(R.id.btOff);
//        final Button btFast = (Button) findViewById(R.id.btTua);

        final ImageButton lui=findViewById(R.id.lui);
        final ImageButton dung=findViewById(R.id.tt);
        final ImageButton toi=findViewById(R.id.toi);
        final ImageButton tt=findViewById(R.id.tt);

        // Khởi tạo ServiceConnection
        connection = new ServiceConnection() {

            // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
            @Override
            public void onServiceDisconnected(ComponentName name) {

                isBound = false;
            }

            // Phương thức này được hệ thống gọi khi kết nối tới service thành công
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyBinder binder = (MyBinder) service;
                myService = binder.getService(); // lấy đối tượng MyService
                isBound = true;
            }
        };

        // Khởi tạo intent
        final Intent intent =
                new Intent(MainActivity.this,
                MyService.class);

        //////

        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, connection,
                        Context.BIND_AUTO_CREATE);

            }
        });

        ///

//        btOn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Bắt đầu một service sủ dụng bind
//                bindService(intent, connection,
//                        Context.BIND_AUTO_CREATE);
//                // Đối thứ ba báo rằng Service sẽ tự động khởi tạo
//            }
//        });


        ///
        dung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound){
                    unbindService(connection);
                    isBound = false;
                }
            }
        });
        ///
//        btOff.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // Nếu Service đang hoạt động
//                if(isBound){
//                    // Tắt Service
//                    unbindService(connection);
//                    isBound = false;
//                }
//            }
//        });

        toi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        btFast.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // nếu service đang hoạt động
//                if(isBound){
//                    // tua bài hát
//                    myService.fastForward();
//                }else{
//                    Toast.makeText(MainActivity.this,
//                            "Service chưa hoạt động", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        findViewById(R.id.btStart).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(isBound){
//                    // tua bài hát
//                    myService.fastStart();
//                }else{
//                    Toast.makeText(MainActivity.this,
//                            "Service chưa hoạt động", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });s

        findViewById(R.id.toi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound){
                    // tua bài hát
                    myService.fastStart();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Service chưa hoạt động", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}