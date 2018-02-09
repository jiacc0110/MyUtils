package jiacc.myutils.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import jiacc.myutils.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_net_utils:
            startActivity(new Intent(this,Top250MovieActivity.class));
            break;
            case R.id.bt_net_recyclerview:
            startActivity(new Intent(this,RecyclerViewTestActivity.class));
            break;
        }
    }

}
