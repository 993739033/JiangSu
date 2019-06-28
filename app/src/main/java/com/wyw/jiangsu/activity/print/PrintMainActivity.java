package com.wyw.jiangsu.activity.print;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qs.helper.printer.Device;
import com.qs.helper.printer.PrintService;
import com.qs.helper.printer.PrinterClass;
import com.qs.helper.printer.bt.BtService;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimBQueryListBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.interfac.Constance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PrintMainActivity extends ListActivity {

    protected static final String TAG = "PrintMainActivity";
    public static boolean checkState = true;
    private Thread tv_update;
    TextView textView_state;
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    Handler mhandler = null;
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_items);

        textView_state = (TextView) findViewById(R.id.textView_state);
        setListAdapter(new SimpleAdapter(this, getData("simple-list-item-2"),
                android.R.layout.simple_list_item_2, new String[]{"title",
                "description"}, new int[]{android.R.id.text1,
                android.R.id.text2}));

        mhandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_READ:
                        byte[] readBuf = (byte[]) msg.obj;
                        Log.e(TAG, "readBuf:" + readBuf[0]);
                        if (readBuf[0] == 0x13) {
                            PrintService.isFUll = true;
                            ShowMsg(getResources().getString(R.string.str_printer_state) + ":" + getResources().getString(R.string.str_printer_bufferfull));
                        } else if (readBuf[0] == 0x11) {
                            PrintService.isFUll = false;
                            ShowMsg(getResources().getString(R.string.str_printer_state) + ":" + getResources().getString(R.string.str_printer_buffernull));
                        } else if (readBuf[0] == 0x08) {
                            ShowMsg(getResources().getString(R.string.str_printer_state) + ":" + getResources().getString(R.string.str_printer_nopaper));
                        } else if (readBuf[0] == 0x01) {
                            //ShowMsg(getResources().getString(R.string.str_printer_state)+":"+getResources().getString(R.string.str_printer_printing));
                        } else if (readBuf[0] == 0x04) {
                            ShowMsg(getResources().getString(R.string.str_printer_state) + ":" + getResources().getString(R.string.str_printer_hightemperature));
                        } else if (readBuf[0] == 0x02) {
                            ShowMsg(getResources().getString(R.string.str_printer_state) + ":" + getResources().getString(R.string.str_printer_lowpower));
                        } else {
                            String readMessage = new String(readBuf, 0, msg.arg1);
                            if (readMessage.contains("800"))// 80mm paper
                            {
                                PrintService.imageWidth = 72;
                                Toast.makeText(PrintMainActivity.this, "80mm", Toast.LENGTH_SHORT).show();

                            } else if (readMessage.contains("580"))// 58mm paper
                            {
                                PrintService.imageWidth = 48;
                                Toast.makeText(PrintMainActivity.this, "58mm", Toast.LENGTH_SHORT).show();

                            }
                        }
                        break;
                    case MESSAGE_STATE_CHANGE:// 蓝牙连接状
                        switch (msg.arg1) {
                            case PrinterClass.STATE_CONNECTED:// 已经连接
                                break;
                            case PrinterClass.STATE_CONNECTING:// 正在连接
                                Toast.makeText(getApplicationContext(), "正在连接!", Toast.LENGTH_SHORT).show();
                                break;
                            case PrinterClass.STATE_LISTEN:
                            case PrinterClass.STATE_NONE:
                                break;
                            case PrinterClass.SUCCESS_CONNECT:
                                PrintService.pl.write(new byte[]{0x1b, 0x2b});// 检测打印机型号
                                Toast.makeText(getApplicationContext(), "连接成功!", Toast.LENGTH_SHORT).show();
                                break;
                            case PrinterClass.FAILED_CONNECT:
                                Toast.makeText(getApplicationContext(), "连接失败!", Toast.LENGTH_SHORT).show();
                                break;
                            case PrinterClass.LOSE_CONNECT:
                                Toast.makeText(getApplicationContext(), "丢失连接!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case MESSAGE_WRITE:

                        break;
                }
                super.handleMessage(msg);
            }
        };

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        break;
                    case 1:
//					Device d = (Device) msg.obj;
//					if (d != null) {
//						if (PrintSettingActivity.deviceList == null) {
//							PrintSettingActivity.deviceList = new ArrayList<Device>();
//						}
//						if (!checkData(PrintSettingActivity.deviceList, d)) {
//							PrintSettingActivity.deviceList.add(d);
//						}
//					}
                        break;
                    case 2:
                        break;
                }
            }
        };

        tv_update = new Thread() {
            public void run() {
                while (true) {
                    if (checkState) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        textView_state.post(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                if (PrintService.pl != null) {
                                    if (PrintService.pl.getState() == PrinterClass.STATE_CONNECTED) {
                                        textView_state.setText("已连接");
                                    } else if (PrintService.pl.getState() == PrinterClass.STATE_CONNECTING) {
                                        textView_state.setText("正在连接...");
                                    } else if (PrintService.pl.getState() == PrinterClass.LOSE_CONNECT
                                            || PrintService.pl.getState() == PrinterClass.FAILED_CONNECT) {
                                        checkState = false;
                                        textView_state.setText("没有连接");
                                        Intent intent = new Intent();
                                        intent.setClass(PrintMainActivity.this, PrintSettingActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                        startActivity(intent);
                                    } else {
                                        textView_state.setText("没有连接");
                                    }
                                }
                            }
                        });
                    }
                }
            }
        };
        tv_update.start();

        PrintService.pl = new BtService(this, mhandler, handler);
        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("index", 0);
        if (index == 0) {
            AnimBlistBean.DataListBean bean2 = (AnimBlistBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
            Intent intent = new Intent(this, PrintsActivity.class);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("position", 0);
            bundle2.putInt("index", 0);
            bundle2.putSerializable(Constance.START_ACTIVITY_DATA, bean2);
            intent.putExtras(bundle2);
            startActivity(intent);
        }

        if (index == 1) {
            AnimBQueryListBean.DataListEntity bean = (AnimBQueryListBean.DataListEntity) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
            Intent intent3 = new Intent(this, PrintsActivity.class);
            Bundle bundle3 = new Bundle();
            bundle3.putInt("position", 0);
            bundle3.putInt("index", 1);
            bundle3.putSerializable(Constance.START_ACTIVITY_DATA, bean);
            intent3.putExtras(bundle3);
            startActivity(intent3);
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onListItemClick(ListView listView, View v, int position, long id) {

        PrintMainActivity.checkState = true;
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.setClass(PrintMainActivity.this, PrintsActivity.class);

        switch (position) {
            case 0:
                startActivity(intent);
                break;
            case 1:
                startActivity(intent);
                break;
            case 2:

                break;
        }
    }

    private List<Map<String, String>> getData(String title) {
        List<Map<String, String>> listData = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "蓝牙打印机");
        map.put("description", "");
        listData.add(map);
        return listData;
    }

    @Override
    protected void onRestart() {
        checkState = true;
        super.onRestart();
    }

    private void ShowMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg,
                Toast.LENGTH_SHORT).show();
    }


}
