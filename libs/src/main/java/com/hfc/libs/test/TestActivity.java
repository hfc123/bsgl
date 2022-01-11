package com.hfc.libs.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfc.libs.R;
import com.ydtx.jobmanage.library.dialog.ListViewDialog;

import java.util.Arrays;
import java.util.List;


/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/11 11:02
 **/
public class TestActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlayout);
        View test1 = findViewById(R.id.test1);
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListView();
            }
        });

    }

    private void showListView() {
        final List<String> data= Arrays.asList("item1","item2","item3","item4","item5","item6","item7","item8","item9","item10");
        final ListViewDialog dialog2 = new ListViewDialog(this,data);
        dialog2.setdefaultAnimation();
        dialog2.show();
        dialog2.setOnItemClickLisener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog2.dismiss();
                Toast.makeText(TestActivity.this,data.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

}
