package com.dabai.Repairbug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.support.design.widget.*;
import android.widget.*;
import android.support.v4.*;
import android.os.*;
import java.io.*;
import android.support.v7.app.*;
import android.content.*;
import android.*;
import android.net.*;
import android.provider.*;
import android.support.v4.content.*;
import android.content.pm.*;
import android.support.v4.app.*;
import android.support.annotation.*;

public class MainActivity extends AppCompatActivity {

	TextView Phone_info,low_momery,delmiuiapp;
	
	int appnum;
	String miuiapp[]={"com.duokan.reader"
	,"com.achievo.vipshop"
		,"com.xiaomi.jr"
		,"com.dianping.v1"
		,"com.suning.mobile.ebuy"
		,"com.zhihu.android"
		,"ctrip.android.view"
		,"com.xiaomi.mimobile.noti"
		,"com.mfashiongallery.emag"
		,"com.google.android.marvin.talkback"
		,"com.qiyi.video"
		,"com.baidu.BaiduMap"
		,"com.yidian.xiaomi"
		,"com.sankuai.meituan"
		,"com.baidu.searchbox"
		,"com.eg.android.AlipayGphone"
		,"com.xiaomi.smarthome"
		,"com.wuba"
		,"com.xiaomi.shop"
		,"com.xiaomi.channel"
		,"com.wali.live"
		,"com.sina.weibo"
		,"com.miui.miuibbs"
		,"com.netease.newsreader.activity"
		,"com.mi.liveassistant"
		,"com.xiangkan.android"
		,"com.xiaomi.o2o"};
	
	
	
	// 要申请的权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INSTALL_PACKAGES};
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                startRequestPermission();
            }
		
	
		Phone_info = (TextView)findViewById(R.id.main_info);
		low_momery = (TextView)findViewById(R.id.main_lowmomery);
		delmiuiapp = (TextView)findViewById(R.id.delmiuiapp);
		init();
		
		}
		}
	
	//启动项
	public void init(){
		
		String minfree= "\n"+new shell().su("cat /sys/module/lowmemorykiller/parameters/minfree");
		String support = ("(" + (supportL() ?"支持": "不支持") + ")");
		
		low_momery.setText("低内存优化"+support+minfree);
		Phone_info.setText("型号:"+Build.MODEL+"\n代号:"+Build.PRODUCT+"\nAndroid版本:"+Build.VERSION.RELEASE+"\nbusybox:"+ifbusybox());
		
	}
	
	
	public void delMIUIApp(View v){
		new Thread() {
			@Override
			public void run() {
				super.run();
	
				for(int i = miuiapp.length-1;i>=0;i--){
					appnum=i;
					String cmd[]={"pm uninstall "+miuiapp[i]};
					new shell().execCommand(cmd,true);
					runOnUiThread(new Runnable(){
							@Override
							public void run() {
								delmiuiapp.setText("正在删除("+miuiapp[appnum]+")");		
							}
						});
				}

				runOnUiThread(new Runnable(){
						@Override
						public void run() {
							delmiuiapp.setText("MIUI全家桶清理");	
							snackbar("清理完成");
						}
					});
				
			}
		}.start();	
		
	}
	
	//精简QQ
	public void SimplifyQQ(View v){
	//无效
		snackbar("正在精简QQ组件...");
		new Thread() {
			@Override
			public void run() {
				super.run();
				//新线程操作

				String qqsim[]={"rm -rf /storage/emulated/0/tencent/MobileQQ/.font_info"
					,"echo '' > /storage/emulated/0/tencent/MobileQQ/.font_info"
					,"rm -rf /storage/emulated/0/tencent/MobileQQ/font_info"
					,"echo '' > /storage/emulated/0/tencent/MobileQQ/font_info"
					,"rm -rf /storage/emulated/0/tencent/MobileQQ/.font_info"
					,"echo '' > /storage/emulated/0/tencent/MobileQQ/.font_info"
					,"rm -rf /storage/emulated/0/tencent/MobileQQ/font_info"
					,"echo '' > /storage/emulated/0/tencent/MobileQQ/font_info"
					,"rm -rf /data/data/com.tencent.mobileqq/files/bubble_info"
					,"echo '' > /data/data/com.tencent.mobileqq/files/bubble_info"
					,"rm -rf /data/data/com.tencent.mobileqq/files/pendant_info"
					,"echo '' > /data/data/com.tencent.mobileqq/files/pendant_info"
					,"rm -rf /storage/emulated/0/tencent/MobileQQ/.pendant"
					,"echo '' > /storage/emulated/0/tencent/MobileQQ/.pendant"
					,"rm -rf /storage/emulated/0/tencent/MobileQQ/.pendant"
					,"echo '' > /storage/emulated/0/tencent/MobileQQ/.pendant"
					,"am force-stop com.tencent.mobileqq"};

					new shell().execCommand(qqsim,true);
						
				try
				{
					Thread.sleep(1000);
				}

				catch (InterruptedException e)
				{}
				runOnUiThread(new Runnable(){
						@Override
						public void run() {
							//更新UI操作
							
							Snackbar.make(getWindow().getDecorView(),"启动QQ查看效果", Snackbar.LENGTH_LONG).setAction("启动", new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.tencent.mobileqq");
										startActivity(LaunchIntent);						
									}
								}).show();
						}
					});
			}
		}.start();	
			
		
		
	}
	//沉浸
	public void Immerse(View v){
		
		new AlertDialog.Builder(this)
			.setTitle("")
			.setMessage("沉浸模式选择")
			.setPositiveButton("全屏沉浸",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
				String cmd[]={"settings put global policy_control immersive.full=apps,-android,-com.android.systemui,-com.tencent.mobileqq,-com.tencent.tim,-com.tencent.mm,-com.tencent.tim,-com.tencent.tim"};
				new shell().execCommand(cmd,true);
				}
			}) 
			.setNeutralButton("取消沉浸", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					String cmd[]={"settings put global policy_control null"};
					new shell().execCommand(cmd,true);
				}
			})
			.show();
		
		
	}
	
	
	
	public String ifbusybox(){
		boolean a = Exists("/system/xbin/busybox");
		if(a){
		return "已安装";
		}else{
		return "未安装";
		}
	}
	
	
	public void delSearch(View v){
	
		new Thread() {
			@Override
			public void run() {
				super.run();
				//新线程操作

				try
				{
					c("com.android.systemui", "/sdcard/com.android.systemui");
				}
				catch (IOException e)
				{}

				try
				{
					Thread.sleep(1000);
				}

				catch (InterruptedException e)
				{}
				runOnUiThread(new Runnable(){
						@Override
						public void run() {
							//更新UI操作
							String cmd[]={"mount -o rw,remount /system","cp /sdcard/com.android.systemui /system/media/theme/default/com.android.systemui","chmod 0644 /system/media/theme/default/com.android.systemui ","rm -f /sdcard/com.android.systemui"};
							new shell().execCommand(cmd,true);
							init();

							Snackbar.make(getWindow().getDecorView(),"重启查看是否成功？", Snackbar.LENGTH_LONG).setAction("重启", new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										String cmd[]={"reboot"};
										new shell().execCommand(cmd,true);
									}
								}).show();

						}
					});
			}
		}.start();	
		
				
	}
	
	
	

//文件是否存在
	public boolean Exists(String fi){
		try{
			File f=new File(fi);
			if(!f.exists()){
				return false;
			}

		}catch (Exception e) {

			return false;
		}
		return true;
	}
	
	public void repairWifi(View v){
		Snackbar.make(getWindow().getDecorView(),"正在调整服务器地址...",Snackbar.LENGTH_LONG).show();
		
		new Thread() {
			@Override
			public void run() {
				super.run();
				//新线程操作
				String cmd[]={"settings delete global captive_portal_server",
					"settings delete global captive_portal_https_url",
					"settings delete global captive_portal_http_url",
					"settings delete global captive_portal_use_https",
					"settings put global captive_portal_use_https 1",
					"settings put global captive_portal_https_url https://www.qualcomm.cn/generate_204"
				};
				new shell().execCommand(cmd,true);
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{}
				runOnUiThread(new Runnable(){
						@Override
						public void run() {
							//更新UI操作
							snackbar("重启下WiFi看看吧");					
						}
					});
			}
		}.start();
		
		
	}
	
	public void repairTime(View v){
		
		Snackbar.make(getWindow().getDecorView(),"正在调整服务器地址...",Snackbar.LENGTH_LONG).show();

		new Thread() {
			@Override
			public void run() {
				super.run();
				//新线程操作
				String cmd[]={"settings put global ntp_server ntp1.aliyun.com"
				};
				new shell().execCommand(cmd,true);
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{}
				runOnUiThread(new Runnable(){
						@Override
						public void run() {
							//更新UI操作
							snackbar("重启手机试试看吧");					
						}
					});
			}
		}.start();	
	}
	//低内存杀手
	
	public void lowMemory(View v){
		
		new Thread() {
			@Override
			public void run() {
				super.run();
				//新线程操作

				try
				{
					c("init.qcom.post_boot.sh", "/sdcard/init.qcom.post_boot.sh");
				}
				catch (IOException e)
				{}
				
				try
				{
					Thread.sleep(1000);
				}

				catch (InterruptedException e)
				{}
				runOnUiThread(new Runnable(){
						@Override
						public void run() {
							//更新UI操作
							String cmd[]={"mount -o rw,remount /system","cp /sdcard/init.qcom.post_boot.sh /system/etc/init.qcom.post_boot.sh","rm -f /sdcard/init.qcom.post_boot.sh"};
							new shell().execCommand(cmd,true);
							init();
							
							Snackbar.make(getWindow().getDecorView(),"重启查看是否成功？", Snackbar.LENGTH_LONG).setAction("重启", new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										String cmd[]={"reboot"};
									new shell().execCommand(cmd,true);
									}
								}).show();
							
						}
					});
			}
		}.start();	
		
	}
	
	
	
	public void c(String assetsFileName, String OutFileName) throws IOException 
	{
        File f = new File(OutFileName);
        if (f.exists())
            f.delete();
        f = new File(OutFileName);
        f.createNewFile();
        InputStream I = getAssets().open(assetsFileName);
        OutputStream O = new FileOutputStream(OutFileName);
        byte[] b = new byte[1024];
        int l = I.read(b);
        while (l > 0) 
		{
            O.write(b, 0, l);
            l = I.read(b);
        }
        O.flush();
        I.close();
        O.close();
    }

	

	
	private static boolean supportL()
	{
		boolean Boolean = new File(post_bootFile).exists();
		return Boolean;
	}

	private static String post_bootFile = "/system/etc/init.qcom.post_boot.sh";
	
	
	public void snackbar(String a){
		Snackbar.make(getWindow().getDecorView(),a,Snackbar.LENGTH_SHORT).show();
	}
	
	// 开始提交请求权限
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        showDialogTipUserGoToAppSettting();
                    } else
                        finish();
                } else {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    // 提示用户去应用设置界面手动开启权限

    private void showDialogTipUserGoToAppSettting() {

        dialog = new AlertDialog.Builder(this)
			.setTitle("存储权限不可用")
			.setMessage("请在-应用设置-权限-中，允许使用存储权限来保存数据")
			.setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// 跳转到应用设置界面
					goToAppSetting();
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			}).setCancelable(false).show();
    }



    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 123);
    }

    //回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // 提示用户应该去应用设置界面手动开启权限
                    showDialogTipUserGoToAppSettting();
                } else {

                    if (dialog != null && dialog.isShowing()) {

                        dialog.dismiss();
                    }
                   	Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();



				}
            }
        }
    }
	
}
