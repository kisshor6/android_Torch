package com.example.torchlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Boolean isTorchON = false;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
    }
    public void torch_off(View view){
        Button button = (Button) view;
        if (button.getText().equals("Flash On")){
            button.setText(R.string.flashon);
            button.setBackgroundResource(R.drawable.button);
            imageView.setImageResource(R.drawable.flashoff);
            torchToggle("On");
        }else {
            button.setText(R.string.flashoff);
            button.setBackgroundResource(R.drawable.btn);
            imageView.setImageResource(R.drawable.flashon);
            torchToggle("Off");
        }
    }

    private void torchToggle(String command) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1){
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String CameraID = null;
            try {
                if (cameraManager != null){
                    CameraID = cameraManager.getCameraIdList()[0];
                }
                if (cameraManager != null){
                    if (command.equals("On")){
                        cameraManager.setTorchMode(CameraID, true);
                        isTorchON = true;
                    }
                    else {
                        cameraManager.setTorchMode(CameraID, false);
                        isTorchON = false;
                    }
                }
            }catch (CameraAccessException e){
                e.getMessage();
            }
        }
    }
}