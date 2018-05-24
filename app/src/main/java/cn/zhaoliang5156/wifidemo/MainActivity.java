package cn.zhaoliang5156.wifidemo;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 程序启动页面
 * 需求：判断网络类型
 *
 * @author zhaoliang
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    // 声明控件
    private Button btnCheckNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * 初始化操作
     */
    private void init() {
        initView();
        initListener();
    }

    /**
     * 初始化点击事件
     */
    private void initListener() {
        btnCheckNet.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btnCheckNet = findViewById(R.id.btn_check_net);
    }


    @Override
    public void onClick(View v) {
        // 获取网络连接类型
        int type = getNetType();
        // 显示对话框提示用户
        showNetDialog(type);
    }

    /**
     * 显示网络类型判断对话框
     *
     * @param type
     */
    private void showNetDialog(int type) {
        String message;
        switch (type) {
            case -1:
                message = "没有网络";
                break;
            case 0:
                message = "手机网络";
                break;
            case 1:
                message = "Wifi网络";
                break;
            default:
                message = "没有获取到网络类型";
        }
        // 创建AlertDialog的builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置对话框要显示的消息
        builder.setMessage("当前网络连接方式是" + message);
        // 设置确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击确定，继续播放
                Log.i(TAG, "继续播放");
            }
        });
        // 设置取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击取消，不做操作
                Log.i(TAG, "不做操作");
            }
        });
        // 显示对话框
        builder.show();
    }

    /**
     * 获取网络类型
     *
     * @return -1 没有网络，0，手机网络,1 WiFi连接
     */
    private int getNetType() {
        // 1. 获取ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        // 2. 获取网络信息对象
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // 3. 判断网络类型
        return networkInfo.getType();
    }
}
