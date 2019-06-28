package com.wyw.jiangsu.activity.print;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jq.printer.JQPrinter;
import com.jq.printer.Port.PORT_STATE;
import com.jq.printer.esc.ESC;
import com.jq.ui.BtConfigActivity;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.BaseActivity;
import com.wyw.jiangsu.bean.AnimBQueryListBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.utils.ChangeLock;

import java.text.SimpleDateFormat;

public class JQPrintActivity extends BaseActivity {
	
	private final static int REQUEST_BT_ENABLE = 0;
	private final static int REQUEST_BT_ADDR = 1;	
	
	private boolean mBtOpenSilent = true;
	private BluetoothAdapter btAdapter = null;
	private JQPrinter printer = null;
	
	private Button mButtonLawEnforcementBill = null;
	private Button mButtonBtScan = null;

    private AnimBlistBean.DataListBean bean2;
    private int index;
    private AnimBQueryListBean.DataListEntity bean3;
    private ChangeLock changeLock = new ChangeLock();


    private long mLastTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jqprint);
        setTitle("JQTEK打印机打印");
        
//        mApp = (DemoApplication)getApplication();  //tip:DemoApplication需要在AndroidManifest.xml的application中注册  android:name="com.jq.ui.DemoApplication"
        mButtonBtScan = (Button)findViewById(R.id.BTButton);
        mButtonBtScan.setText("选择打印机");
        mButtonLawEnforcementBill = (Button)findViewById(R.id.ButtonLawEnforcementBill);
        mButtonLawEnforcementBill.setVisibility(Button.INVISIBLE);

        Bundle bundle = getIntent().getExtras();
         index = bundle.getInt("index", 0);
        if (index == 0) {
            bean2 = (AnimBlistBean.DataListBean) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
        } else if (index == 1) {
            bean3 = (AnimBQueryListBean.DataListEntity) bundle.getSerializable(Constance.START_ACTIVITY_DATA);
        }

        int position = bundle.getInt("position", 0);


        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) { 
            Toast.makeText(this, "本机没有找到蓝牙硬件或驱动！", Toast.LENGTH_SHORT).show(); 
            finish(); 
            return;
        }
          
     // 如果本地蓝牙没有开启，则开启
        //以下操作需要在AndroidManifest.xml中注册权限android.permission.BLUETOOTH ；android.permission.BLUETOOTH_ADMIN
        if (!btAdapter.isEnabled()) { 
        	Toast.makeText(this, "正在开启蓝牙", Toast.LENGTH_SHORT).show(); 
        	if (!mBtOpenSilent)
        	{
        		// 我们通过startActivityForResult()方法发起的Intent将会在onActivityResult()回调方法中获取用户的选择，比如用户单击了Yes开启，  
        		// 那么将会收到RESULT_OK的结果，  
        		// 如果RESULT_CANCELED则代表用户不愿意开启蓝牙  
        		Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); 
        		startActivityForResult(mIntent,REQUEST_BT_ENABLE ); 
        	}
        	else { // 用enable()方法来开启，无需询问用户(无声息的开启蓝牙设备),这时就需要用到android.permission.BLUETOOTH_ADMIN权限。  
        		btAdapter.enable();
        		Toast.makeText(this, "本地蓝牙已打开", Toast.LENGTH_SHORT).show(); 
        	}        	
        }
        else
        {
        	Toast.makeText(this, "本地蓝牙已打开", Toast.LENGTH_SHORT).show(); 
        } 
    }


    private void exit() {
    	if (printer != null)
    	{
    		if(!printer.close())
				Toast.makeText(this, "打印机关闭失败", Toast.LENGTH_SHORT).show(); 
			else
				Toast.makeText(this, "打印机关闭成功", Toast.LENGTH_SHORT).show(); 
			printer = null;
    	}
    	if(btAdapter != null )	{
    		if (btAdapter.isEnabled())	{
    			btAdapter.disable();
    			Toast.makeText(this, "关闭蓝牙成功", Toast.LENGTH_LONG).show();
    		}
    	}
		finish(); 
		System.exit(0); //凡是非零都表示异常退出!0表示正常退出!
	}
    

	private final BroadcastReceiver mReceiver = new BroadcastReceiver()
	{
		@SuppressLint("WrongConstant")
		@Override
		public void onReceive(android.content.Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action))
			{
				if (printer != null)
				{
					if (printer.isOpen)
						printer.close();
				}
				Toast.makeText(context,"蓝牙连接已断开",Toast.LENGTH_SHORT).show();
			}	
		};
	};
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    { 
        super.onActivityResult(requestCode, resultCode, data); 
        if (requestCode == REQUEST_BT_ENABLE) 
        { 
            if (resultCode == RESULT_OK) 
            { 
                Toast.makeText(this, "蓝牙已打开", Toast.LENGTH_SHORT).show();
            } 
            else if (resultCode == RESULT_CANCELED) 
            { 
                Toast.makeText(this, "不允许蓝牙开启", Toast.LENGTH_SHORT).show(); 
                exit();
                return;
            }         
        }
        else if (requestCode == REQUEST_BT_ADDR)
        {
        	if(resultCode == Activity.RESULT_OK)
			{
        		String btDeviceString = data.getStringExtra(BtConfigActivity.EXTRA_BLUETOOTH_DEVICE_ADDRESS);
				if (btDeviceString != null)
				{
					mButtonBtScan.setText("名称:"+data.getStringExtra(BtConfigActivity.EXTRA_BLUETOOTH_DEVICE_NAME) + "\r\n地址:" + btDeviceString);
					
					if(btAdapter.isDiscovering())
						btAdapter.cancelDiscovery();							
					
					if (printer != null)
					{
						printer.close();
					}
				
					printer = new JQPrinter(btAdapter,btDeviceString);
					
					if (!printer.open(com.jq.printer.JQPrinter.PRINTER_TYPE.ULT113x))
					{
						Toast.makeText(this, "打印机Open失败", Toast.LENGTH_SHORT).show(); 
						return;
					}
					
					if (!printer.wakeUp())
						return;
					
					Log.e("JQ", "printer open ok");
				
					mButtonLawEnforcementBill.setVisibility(Button.VISIBLE);
					IntentFilter filter = new IntentFilter();
					filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);//蓝牙断开
					registerReceiver(mReceiver, filter);
				}
			}
        	else
        	{
        		mButtonBtScan.setText("选择打印机");
        	}
        } 
    }
    
    //蓝牙按键点击 ,此函数需要在 activity_main.xml文件中注册
    public void bt_button_click(View view)
    {
    	if (btAdapter == null)
    		return;
    	mButtonBtScan.setText("请等待");
    	mButtonLawEnforcementBill.setVisibility(Button.INVISIBLE);
		Intent myIntent = new Intent();
		myIntent.setClass(this, BtConfigActivity.class  );
		startActivityForResult(myIntent, REQUEST_BT_ADDR);
    }
    
    public void ButtonEnforcement_click(View view)
	{
		if(btAdapter.isDiscovering())
			btAdapter.cancelDiscovery();
		
		if (printer.getPortState() != PORT_STATE.PORT_OPEND)
		{
			mButtonBtScan.setText("选择打印机");
			mButtonLawEnforcementBill.setVisibility(Button.INVISIBLE);
			Toast.makeText(this, "Fail:" +printer.getPortState(), Toast.LENGTH_SHORT).show();
    		return; 
		}
        if (index == 0) {
            PrintImage();
            PrintImage2();
        }
        if (index == 1) {
//            printer.feedLeftMark();
            PrintImage3();
            PrintImage4();
        }
	}

    private void PrintImage() {

    }

    private void PrintImage2() {

    }

    private void PrintImage3() {
        if (!printer.wakeUp()) {
            Toast.makeText(this, "打印失败!请检查打印机是否开启", Toast.LENGTH_SHORT).show();
            return;
        }
        printer.esc.feedDots(160);
        printer.esc.barcode.barcode2D_QRCode(54,0, ESC.BAR_UNIT.x4,0,2,
                Constance.PRINT_URL_ANIB + bean3.getGuid());
        printer.esc.feedEnter();
        printer.esc.feedDots(46);
        printer.esc.text.printOut(220, 0, ESC.FONT_HEIGHT.x16, true,
                ESC.TEXT_ENLARGE.HEIGHT_WIDTH_DOUBLE, bean3.getAQCargoOwner());
        printer.esc.feedEnter();
        printer.esc.feedDots(32);
        printer.esc.text.printOut(220, 0, ESC.FONT_HEIGHT.x16, true,
                ESC.TEXT_ENLARGE.HEIGHT_WIDTH_DOUBLE, bean3.getAQPhone());
        printer.esc.feedEnter();
        printer.esc.feedDots(32);
        printer.esc.text.printOut(220, 0, ESC.FONT_HEIGHT.x16, true,
                ESC.TEXT_ENLARGE.HEIGHT_WIDTH_DOUBLE, bean3.getAQXuZhong());
        printer.esc.feedEnter();
        printer.esc.feedDots(32);
        printer.esc.text.printOut(220, 0, ESC.FONT_HEIGHT.x16, true,
                ESC.TEXT_ENLARGE.HEIGHT_WIDTH_DOUBLE,
                changeLock.changeToQuantity_unit(String.valueOf(bean3.getAQQuantity())) + bean3.getAQDanWei());
        printer.esc.feedEnter();
        printer.esc.feedDots(32);
        printer.esc.text.printOut(220, 0, ESC.FONT_HEIGHT.x16, true,
                ESC.TEXT_ENLARGE.HEIGHT_WIDTH_DOUBLE,
                bean3.getAQYongTu());
        printer.esc.feedEnter();
        printer.esc.feedDots(32);
        printer.esc.text.printOut(220, 0, ESC.FONT_HEIGHT.x16, true,
                ESC.TEXT_ENLARGE.HEIGHT_WIDTH_DOUBLE,
                bean3.getAQShiQy() + bean3.getAQXianQy() + bean3.getAQXiangQy() + bean3.getAQCunQy());
        printer.esc.feedEnter();
        printer.esc.feedDots(32);
        printer.esc.text.printOut(220, 0, ESC.FONT_HEIGHT.x16, true,
                ESC.TEXT_ENLARGE.HEIGHT_WIDTH_DOUBLE,
                bean3.getAQShiDd() + bean3.getAQXianDd() + bean3.getAQXiangDd() + bean3.getAQCunDd());
        printer.esc.feedEnter();
        printer.esc.feedDots(160);
        printer.esc.feedEnter();
//        printer.esc.text.printOut(bean3.getAQPhone());
//        printer.esc.text.drawOut(280, 32, bean3.getAQXuZhong());
//        printer.esc.text.printOut(JQPrinter.ALIGN.RIGHT, ESC.FONT_HEIGHT.x16, false,
//                ESC.TEXT_ENLARGE.NORMAL, changeLock.changeToQuantity_unit(String.valueOf(bean3.getAQQuantity())) + bean3.getAQDanWei());
//        printer.esc.text.drawOut(280, 32, bean3.getAQYongTu());
//        printer.esc.text.drawOut(280, 32, bean3.getAQShiQy() + bean3.getAQXianQy() + bean3.getAQXiangQy() + bean3.getAQCunQy());
//        printer.esc.text.drawOut(280, 32, bean3.getAQShiDd() + bean3.getAQXianDd() + bean3.getAQXiangDd() + bean3.getAQCunDd());
//        printer.esc.text.drawOut(280, 32, bean3.getAQEarTag());
//        printer.esc.text.drawOut(280, 32, bean3.getAQVeterinary());
//
//        String[] nians = bean3.getDateQF().trim().split(" ");
//        String[] splits = nians[0].split("-|/");
//        String Years = splits[0];
//        String Months = splits[1];
//        String Dates = splits[2];
//
//        printer.esc.text.drawOut(280, 32, Years);
//        printer.esc.text.drawOut(280, 32, Months);
//        printer.esc.text.drawOut(280, 32, Dates);

    }

    private void PrintImage4() {
        if (!printer.wakeUp()) {
            Toast.makeText(this, "打印失败!请检查打印机是否开启", Toast.LENGTH_SHORT).show();
            return;
        }

    }


    /*
     * 以下排版是按3英寸打印机排版，请选用ULT113x系列，JLP351系列
     */
	private boolean printView() {
		if (!printer.wakeUp())
			return false;
		printer.esc.text.printOut(150, 29, ESC.FONT_HEIGHT.x24, true,
				ESC.TEXT_ENLARGE.HEIGHT_WIDTH_DOUBLE, "卫生行政执法文书");
		ESC.LINE_POINT[] lines = new ESC.LINE_POINT[1];
		lines[0] = new ESC.LINE_POINT(0, 575);

		for (int i = 0; i < 4; i++) {
			printer.esc.graphic.linedrawOut(lines);
		}
		printer.esc.feedDots(4);
		printer.esc.text.printOut(JQPrinter.ALIGN.CENTER, ESC.FONT_HEIGHT.x24, true,
				ESC.TEXT_ENLARGE.NORMAL, "现场检测笔录");
		printer.esc.barcode.code128_auto_printOut(JQPrinter.ALIGN.CENTER,
				ESC.BAR_UNIT.x3, 56, ESC.BAR_TEXT_POS.BOTTOM,
				ESC.BAR_TEXT_SIZE.ASCII_8x16, "A02161645760");

		printer.esc.text.printOut("被检查人：上海济强电子科技有限公司");
		printer.esc.text.printOut("联系电话：13371967607" + "   邮政编码：201206");
		printer.esc.text.printOut("法定代表人(或责任人)：张三" + "   职务：总经理");
		printer.esc.text.printOut("检查机关：浦东1队");
		SimpleDateFormat formatter   =   new   SimpleDateFormat("yyyy年MM月dd日HH时");
		printer.esc.text.printOut("检查时间：" + formatter.format(System.currentTimeMillis()));
		printer.esc.text.printOut("检查地点：浦东金藏路258号2号楼2层\n");
		printer.esc.text.drawOut("检查人员示证检查，检查记录：\n");
		printer.esc.text.printOut("    卫生监督员" + "李四" + "和" + "王二" + "在" + "赵大"
				+ "的陪同下,对" + "上海济强电子科技有限公司" + "进行了检查，经检查发现：");
		printer.esc.text.printOut("1.门外有垃圾。\n2.门口有小广告。\n3.厕所未及时清洁。\r\n");
        printer.esc.feedDots(200);
        printer.esc.feedDots(200);
		printer.esc.barcode.barcode2D_QRCode(50,0, ESC.BAR_UNIT.x4,0,2,"QR二维条码");
		printer.esc.feedEnter();
		printer.esc.feedDots(200);
		printer.esc.text.printOut("被检查人阅后签名：\n");
		printer.esc.text.drawOut(0, 16, "日期    年  月  日");
        printer.esc.text.drawOut(0, 0, "卫生行政机关盖章");
        printer.esc.text.drawOut(280, 32, "卫生行政机关盖章");
		printer.esc.text.drawOut(280, 35, "卫生行政机关盖章");
		formatter   =   new   SimpleDateFormat("yyyy年MM月dd日");
		printer.esc.text.drawOut(280, 0, formatter.format(System.currentTimeMillis()));
		printer.esc.feedEnter();
		printer.esc.text.printOut("卫生监督员签名：\n");
		printer.esc.text.printOut("日期    年  月  日");
		for (int i = 0; i < 4; i++) {
			printer.esc.graphic.linedrawOut(lines);
		}
		printer.esc.feedDots(4);
		printer.esc.text.printOut(JQPrinter.ALIGN.RIGHT, ESC.FONT_HEIGHT.x16, false,
				ESC.TEXT_ENLARGE.NORMAL, "中华人民共和国卫生部制定");
		printer.esc.feedLines(3);
		return true;
	}

	@Override
	public void bindData() {

	}

	@Override
	public void bindListener() {

	}
}
