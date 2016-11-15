package com.example.moon7.address_to_path_exam;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String get;
    Scanner scan;
    EditText input;
    TextView result;
    Geocoder coder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan=new Scanner(System.in);
        input=(EditText)findViewById(R.id.Input);
        result=(TextView)findViewById(R.id.result);
        coder=new Geocoder(this, Locale.KOREA);
//        coder=new Geocoder(null);
    }


    public void onClick(View v){
        List<Address> list=null;
        switch (v.getId()){
            case R.id.Ex:
                get=input.getText().toString();
                try{
                    list=coder.getFromLocationName(get,10);
//                    list=coder.getFromLocation(더블,더블,10); 이걸로하면 좌표값을 주소로 변환가능
                }catch (IOException e) {
                    e.printStackTrace();
            }
                if (list==null)
                    result.setText("해당되는 정보가 없습니다.");
                else{
                    result.setText(list.get(0).toString());
                    result.append(list.get(0).getCountryName());
                    result.append(""+list.get(0).getLatitude());
                    result.append(""+list.get(0).getLongitude());
                }
                break;
            case R.id.Ex2://위도 경도만 받아옴
                get=input.getText().toString();
                try{
                    list=coder.getFromLocationName(get,10);
                }catch (IOException e) {
                    e.printStackTrace();
                }
                result.setText("위도:"+list.get(0).getLatitude());
                result.append("경도:"+list.get(0).getLongitude());
                break;
        }
    }
}
