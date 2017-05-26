package xiaomi.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "jason-" + MainActivity.class.getSimpleName();

    private AlertDialog mAlertDialog;
    private TextView mTv;
    private Button mbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        update();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        update();
        Log.i(TAG, "onConfigurationChanged." + newConfig.toString());
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mbtn = (Button) findViewById(R.id.btn);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceDialog();
            }
        });
    }

    private void update() {
        Context context = LocaleUtil.getResourcesLegacy(getApplicationContext());
        mTv.setText(context.getResources().getString(R.string.test_string));
    }

    private void showSingleChoiceDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("title");

        /**
         * 设置内容区域为单选列表项
         */



        final String[] items = LocaleUtil.getSupportLocaleList();

        builder.setSingleChoiceItems(items, LocaleUtil.getType().ordinal(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LocaleUtil.setType(LocaleUtil.getTypeByOrdinal(i));
                Toast.makeText(getApplicationContext(), "You clicked "+items[i], Toast.LENGTH_SHORT).show();
                mAlertDialog.hide();
            }
        });

        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(true);
        mAlertDialog.show();
    }

}
