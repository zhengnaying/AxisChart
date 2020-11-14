package com.example.axischart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   ImageView up,left,right,down;
   Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonPlot=findViewById(R.id.buttonPlot);
        final TextView txtFunction=findViewById(R.id.txtFunction);
        final CustomView customView=findViewById(R.id.plotview);
        buttonPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.NoZoom();
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
        initView();
        EventView();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // 这条表示加载菜单文件，第一个参数表示通过那个资源文件来创建菜单
        // 第二个表示将菜单传入那个对象中。这里我们用Menu传入menu
        // 这条语句一般系统帮我们创建好
        getMenuInflater().inflate(R.menu.zoom, menu);
        return true;
    }
    // 菜单的监听方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final CustomView customView=findViewById(R.id.plotview);
        switch (item.getItemId()) {
            case R.id.action_zoomin:
                customView.ZoomIn();
                customView.invalidate();
                break;
            case R.id.action_zoomout:
                customView.ZoomOut();
                customView.invalidate();
                break;
            default:
                break;
        }
        return true;

    }
    public void initView(){
        up = (ImageView)findViewById(R.id.action_up);
        left = (ImageView)findViewById(R.id.action_left);
        right = (ImageView)findViewById(R.id.action_right);
        down = (ImageView)findViewById(R.id.action_down);
        back = (Button)findViewById(R.id.back);
    }
    public void EventView(){
        final CustomView customView=findViewById(R.id.plotview);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.up();
                customView.invalidate();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.left();
                customView.invalidate();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.right();
                customView.invalidate();
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.down();
                customView.invalidate();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.NoZoom();
                customView.back();
                customView.invalidate();
            }
        });
    }
}