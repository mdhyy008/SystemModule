package com.dabai.SystemModule;

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
import java.util.*;
import org.apache.http.util.*;
import android.view.View.*;
import android.widget.PopupMenu.*;
import android.net.wifi.*;

public class MainActivity extends AppCompatActivity implements OnClickListener,OnMenuItemClickListener
{


	

	TextView Phone_info,low_momery,delmiuiapp,lowLook,dellog,changeModel;

	PopupMenu ModelMenu;
	
	String a,b,c,d,e,f;
	String prop;
	String oldProp,newProp;
	String a1,b1,c1,d1,e1,f1;

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
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_WIFI_STATE};
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
		{
            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED)
			{
                // 如果没有授予该权限，就去提示用户请求
                startRequestPermission();
            }


			lowLook = (TextView)findViewById(R.id.lowLook);
			Phone_info = (TextView)findViewById(R.id.main_info);
			low_momery = (TextView)findViewById(R.id.main_lowmomery);
			delmiuiapp = (TextView)findViewById(R.id.delmiuiapp);
			dellog = (TextView)findViewById(R.id.dellog);
			changeModel = (TextView)findViewById(R.id.changeModel);
			
	
			CheckLow();

		}
	}

	
	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
	}

	@Override
	public boolean onMenuItemClick(MenuItem p1)
	{
		// TODO: Implement this method
		switch (p1.getItemId())
		{
			case R.id.vivoX20:
				changeModel("vivo","vivo X20","vivo","X20","X20");
				break;
			case R.id.oppoR11:
				changeModel("oppo","oppo R11","oppo","R11","R11");
				break;
			case R.id.iPhoneX:
				changeModel("iPhone","X","iPhone","hydrogen","X");
				break;		
			case R.id.samsungS9:
				changeModel("samsung","SM-G9650","samsung","star2qltechn","star2qltezc");
				break;		
			case R.id.Titanium8848:
				changeModel("ERENEBEN","EBEN M2","ERENEBEN","EBEN M2","EBEN M2");
				break;	
				
			case R.id.GooglePixel:
				changeModel("google","Pixel 2 XL","Google","taimen","taimen");
				break;		
			case R.id.OnePlus5:
				changeModel("OnePlus","ONEPLUS A5000","OnePlus","OnePlus5","OnePlus5");
				break;		
			case R.id.Mix2s:
				changeModel("Xiaomi","MIX 2S","Xiaomi","SDM660","Xiaomi");
				break;		
				
        }
		/*
		 String brand
		 ,String model
		 ,String manufacturer
		 ,String device
		 ,String product
		 */
		return false;
	}
	
	
	
	
	//启动项
	public void CheckLow()
	{

		String minfree= "\n" + new shell().su("cat /sys/module/lowmemorykiller/parameters/minfree");
		String support = ("(" + (supportL() ?"支持": "不支持") + ")");

		low_momery.setText("低内存优化" + support + minfree);
		Phone_info.setText("型号:" + Build.MODEL + "\n代号:" + Build.PRODUCT + "\nAndroid版本:" + Build.VERSION.RELEASE + "\nbusybox:" + ifbusybox());

	}


	
	//look wifi
	public void lookWifi(View v){
		if(Build.VERSION.SDK_INT>=26){
			Toast.makeText(getApplicationContext(),"系统版本检测:"+Build.VERSION.RELEASE,1).show();
			AndroidOWifi();
		}
		else{
			AndroidNwifi();
		}
		
		
		
	}
	
	public void AndroidNwifi(){
		String a[]={"cp /data/misc/wifi/wpa_supplicant.conf /sdcard/wifi.txt"};
		new shell().execCommand(a,true);
		try
		{
			String b =readSDFile("/sdcard/wifi.txt");
			String c =	b.substring(b.indexOf("network"),b.length());
			String d =c.replace("network={","");	
			String e =d.replace("}","");
			String j = e.replace("bssid=","");
			String f = j.replace("ssid=","名称:");
			String g = f.replace("psk=","密码:");
			new AlertDialog.Builder(this).setMessage(g).show();
		}
		catch (IOException e)
		{}
		
	}
	
	public void AndroidOWifi(){
		snackbar("没适配");
	}
	
	
	
	
	public void toast(String a){
		Toast.makeText(getApplicationContext(),a,1).show();
	}
	
	
	public void delMIUIApp(View v)
	{
		
		Snackbar.make(getWindow().getDecorView(), "MIUI9会预装一些广告应用,点击确定开始删除它们", Snackbar.LENGTH_LONG).setAction("重启", new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					new Thread() {
						@Override
						public void run()
						{
							super.run();

							for (int i = miuiapp.length - 1;i >= 0;i--)
							{
								appnum = i;
								String cmd[]={"pm uninstall " + miuiapp[i]};
								new shell().execCommand(cmd, true);
								runOnUiThread(new Runnable(){
										@Override
										public void run()
										{
											delmiuiapp.setText("正在删除(" + miuiapp[appnum] + ")");		
										}
									});
							}

							runOnUiThread(new Runnable(){
									@Override
									public void run()
									{
										delmiuiapp.setText("MIUI全家桶清理");	
										snackbar("清理完成");
									}
								});

						}
					}.start();	
				}
			}).show();	

	}


	//del log
	public void delLog(View v)
	{

		dellog.setText("清理中");
		new Thread() {
			@Override
			public void run()
			{
				super.run();
				//新线程操作
				String a[]={"echo 3 > /proc/sys/vm/drop_caches"
					,"logcat -c"};
				new shell().execCommand(a, true);	
				runOnUiThread(new Runnable(){
						@Override
						public void run()
						{
							//更新UI操作
							dellog.setText("清理");
							snackbar("清理完毕");
							
						}

					});


			}
		}.start();
	}

	
	public void delBatteryLog(View v){
		
		new AlertDialog.Builder(this)
			.setTitle("提示")
			.setMessage("通过删除\n/data/system/batterystats-checkin.bin\n/data/system/batterystats-daily.xml\n/data/system/batterystats.bin\n来清空系统的电池使用记录")
			.setPositiveButton("清除",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{


					new Thread() {
						@Override
						public void run() {
							super.run();
							//新线程操作
							String cmd[]={"mount -o remount,rw /system"
							,"rm -f /data/system/batterystats-checkin.bin"
							,"rm -f /data/system/batterystats-daily.xml"
							,"rm -f /data/system/batterystats.bin"};
							new shell().execCommand(cmd, true);
							runOnUiThread(new Runnable(){
									@Override
									public void run() {
										//更新UI操作
										Snackbar.make(getWindow().getDecorView(), "重启才会生效!", Snackbar.LENGTH_LONG).setAction("重启", new View.OnClickListener() {
												@Override
												public void onClick(View v)
												{
													reboot();
												}
											}).show();
									}

								});

						}
					}.start();			
				}
			}) 	
			.show();
	}
	
	
	

	public void changeMODEL(View v)
	{
		
	
		new AlertDialog.Builder(this)
			.setTitle("警告")
			.setMessage("当你点击确认的时候代表你已经明白此操作的作用\n和带来的好处和后果\n本软件不承担任何责任")
			.setPositiveButton("确认更改",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					
					PopupMenu popup = new PopupMenu(MainActivity.this,changeModel);//第二个参数是绑定的那个view
					//获取菜单填充器
					MenuInflater inflater = popup.getMenuInflater();
					//填充菜单
					inflater.inflate(R.menu.changemodel, popup.getMenu());
					//绑定菜单项的点击事件
					popup.setOnMenuItemClickListener(MainActivity.this);
					//显示(这一行代码不要忘记了)
					popup.show(); 
				}
			}) 
			.setNeutralButton("恢复", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					recover();			
				}
			})
			.show();

	}


	//重启
	public void reboot()
	{
		String a[]={"reboot"};
		new shell().execCommand(a, true);
	}


	public void recover()
	{

		new AlertDialog.Builder(this)
			.setTitle("恢复机型")
			.setMessage("如果你上一次修改机型是用的本软件,那么你可以用本功能恢复\n如果你是用其他APP改的机型,可能本操作会无效")
			.setPositiveButton("确认恢复",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					
					
					new Thread() {
						@Override
						public void run() {
							super.run();
							//新线程操作
							String cmd[]={"mount -o remount,rw /system","chmod 777 /system/build.prop","chmod 777 /system/build.prop.bak","cp -f /system/build.prop.bak /system/build.prop","chmod 644 /system/build.prop"};
							new shell().execCommand(cmd, true);
							runOnUiThread(new Runnable(){
									@Override
									public void run() {
										//更新UI操作
										Snackbar.make(getWindow().getDecorView(), "更改完成需要重启嘛?", Snackbar.LENGTH_LONG).setAction("重启", new View.OnClickListener() {
												@Override
												public void onClick(View v)
												{
													reboot();
												}
											}).show();
									}

								});

						}
					}.start();			
				}
			}) 	
			.show();
	}

	//删密码
	public void delLock(View v){
		new AlertDialog.Builder(this)
			.setTitle("")
			.setMessage("删除锁屏密码会使手机重启,继续吗？")
			.setPositiveButton("重启",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					String cmd[]={"rm -f /data/system/gatekeeper.pattern.key"
						,"rm -f /data/system/gatekeeper.password.key"
						,"rm -f /data/system/locksettings.db"
						,"rm -f /data/system/locksettings.db-shm"
						,"reboot"};
						new shell().execCommand(cmd,true);
				}
			}) 
			.setNeutralButton("取消", null)
			.show();
		
	}

	

	//改机型
	public void changeModel(final String brand,final String model,final String manufacturer,final String device,final String product)
	{
		
		new Thread() {
			@Override
			public void run()
			{
				super.run();
				//新线程操作

				
				String mode[]={"mount -o rw,remount /system"
					,"cp /system/build.prop /system/build.prop.bak"
					,"cp /system/build.prop /data/build.prop"
					,"chmod 0644 /data/build.prop"
					,"sed -i 's/^ro.product.brand=.*/ro.product.brand="+brand+"/' /data/build.prop"
			
					,"sed -i 's/^ro.product.model=.*/ro.product.model="+model+"/' /data/build.prop"
					,"sed -i 's/^ro.product.manufacturer=.*/ro.product.manufacturer="+manufacturer+"/' /data/build.prop"
					,"sed -i 's/^ro.product.device=.*/ro.product.device="+device+"/' /data/build.prop"
					,"sed -i 's/^ro.build.product=.*/ro.build.product="+product+"/' /data/build.prop"

					,"cp /data/build.prop /system/build.prop"};
					
			new shell().execCommand(mode,true);
			
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							//更新UI操作
							Snackbar.make(getWindow().getDecorView(), "更改完成需要重启嘛?", Snackbar.LENGTH_LONG).setAction("重启", new View.OnClickListener() {
									@Override
									public void onClick(View v)
									{
										reboot();
									}
								}).show();
						}

					});
			}
		}.start(); 

	}
	
	
	
	


	public String readSDFile(String fileName) throws IOException
	{    

        File file = new File(fileName);    
        FileInputStream fis = new FileInputStream(file);    
        int length = fis.available();   
		byte [] buffer = new byte[length];   
		fis.read(buffer); 
		String res = EncodingUtils.getString(buffer, "UTF-8");
		fis.close();       
		return res;    
	} 

	//filename   a.txt  不加路径保存到sdcard
	public void saveSdFile(String filename, String text)
	{
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{	
			try
			{
				File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
				File saveFile = new File(sdCardDir, filename);
				FileOutputStream outStream = new FileOutputStream(saveFile);
				outStream.write(text.getBytes());
				outStream.close();
			}
			catch (IOException ioe)
			{
			}
		}
	}

	



	//网络adb
	public void NETADB(View v)
	{


		new AlertDialog.Builder(this)
			.setTitle("提示")
			.setMessage("开启成功后\n使用电脑adb程序执行adb connect IP:5555来连接到手机")
			.setPositiveButton("开启",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					String a[]={"setprop service.adb.tcp.port 5555","stop adbd","sleep 1","start adbd"};
					new shell().execCommand(a, true);
					snackbar("开启成功(IP:5555)");
				}
			}) 
			.setNeutralButton("关闭", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					String a[]={"stop adbd"};
					new shell().execCommand(a, true);	
					snackbar("关闭成功");
				}
			})

			.show();
	}



	//沉浸
	public void Immerse(View v)
	{

		new AlertDialog.Builder(this)
			.setTitle("")
			.setMessage("沉浸模式选择")
			.setPositiveButton("全屏沉浸",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					String cmd[]={"settings put global policy_control immersive.full=apps,-android,-com.android.systemui,-com.tencent.mobileqq,-com.tencent.tim,-com.tencent.mm,-com.tencent.tim,-com.tencent.tim"};
					new shell().execCommand(cmd, true);
				}
			}) 
			.setNeutralButton("取消沉浸", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					String cmd[]={"settings put global policy_control null"};
					new shell().execCommand(cmd, true);
				}
			})
			.show();


	}



	public String ifbusybox()
	{
		boolean a = Exists("/system/xbin/busybox");
		if (a)
		{
			return "已安装";
		}
		else
		{
			return "未安装";
		}
	}


	public void delSearch(View v)
	{

		new Thread() {
			@Override
			public void run()
			{
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
						public void run()
						{
							//更新UI操作
							String cmd[]={"mount -o rw,remount /system","cp /sdcard/com.android.systemui /system/media/theme/default/com.android.systemui","chmod 0644 /system/media/theme/default/com.android.systemui ","rm -f /sdcard/com.android.systemui"};
							new shell().execCommand(cmd, true);
							CheckLow();

							Snackbar.make(getWindow().getDecorView(), "重启查看是否成功？", Snackbar.LENGTH_LONG).setAction("重启", new View.OnClickListener() {
									@Override
									public void onClick(View v)
									{
										String cmd[]={"reboot"};
										new shell().execCommand(cmd, true);
									}
								}).show();

						}
					});
			}
		}.start();	


	}




//文件是否存在
	public boolean Exists(String fi)
	{
		try
		{
			File f=new File(fi);
			if (!f.exists())
			{
				return false;
			}

		}
		catch (Exception e)
		{

			return false;
		}
		return true;
	}

	public void repairWifi(View v)
	{
		Snackbar.make(getWindow().getDecorView(), "正在调整服务器地址...", Snackbar.LENGTH_LONG).show();

		new Thread() {
			@Override
			public void run()
			{
				super.run();
				//新线程操作
				String cmd[]={"settings delete global captive_portal_server",
					"settings delete global captive_portal_https_url",
					"settings delete global captive_portal_http_url",
					"settings delete global captive_portal_use_https",
					"settings put global captive_portal_use_https 1",
					"settings put global captive_portal_https_url https://www.qualcomm.cn/generate_204"
				};
				new shell().execCommand(cmd, true);
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{}
				runOnUiThread(new Runnable(){
						@Override
						public void run()
						{
							//更新UI操作
							snackbar("重启下WiFi看看吧");					
						}
					});
			}
		}.start();


	}

	public void repairTime(View v)
	{

		Snackbar.make(getWindow().getDecorView(), "正在调整服务器地址...", Snackbar.LENGTH_LONG).show();

		new Thread() {
			@Override
			public void run()
			{
				super.run();
				//新线程操作
				String cmd[]={"settings put global ntp_server ntp1.aliyun.com"
				};
				new shell().execCommand(cmd, true);
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{}
				runOnUiThread(new Runnable(){
						@Override
						public void run()
						{
							//更新UI操作
							snackbar("重启手机试试看吧");					
						}
					});
			}
		}.start();	
	}
	//低内存杀手

	public void lowMemory(View v)
	{
		if (isInstalled(getApplicationContext(), "com.xiaoxin.lowmemorykillerEdit"))
		{
			Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.xiaoxin.lowmemorykillerEdit");
			startActivity(LaunchIntent);
		}
		else
		{
			lowLook.setText("正在安装插件...");
			new Thread() {
				@Override
				public void run()
				{
					super.run();
					//新线程操作
					try
					{
						c("Lmo.apk", "/sdcard/Lmo.apk");	
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
							public void run()
							{
								//更新UI操作
								String install[]={"pm install -r \"/storage/emulated/0/Lmo.apk\""};
								new shell().execCommand(install, true);
								Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.xiaoxin.lowmemorykillerEdit");
								startActivity(LaunchIntent);
								lowLook.setText("优化");
							}
						});
				}
			}.start();
		}
	}

	public static boolean isInstalled(Context context, String packageName)
	{  
        final PackageManager packageManager = context.getPackageManager();//获取packagemanager  
        List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息  
        List<String> pName = new ArrayList<>();//用于存储所有已安装程序的包名  
        //从pinfo中将包名字逐一取出，压入pName list中  
        if (pInfo != null)
		{  
            for (int i = 0; i < pInfo.size(); i++)
			{  
                String pn = pInfo.get(i).packageName;  
                pName.add(pn);  
            }  
        }  
        return pName.contains(packageName);//判断pName中是否有目标程序的包名，有TRUE，没有FALSE  
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


	public void snackbar(String a)
	{
		Snackbar.make(getWindow().getDecorView(), a, Snackbar.LENGTH_SHORT).show();
	}

	// 开始提交请求权限
    private void startRequestPermission()
	{
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 321)
		{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			{
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
				{
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b)
					{
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        showDialogTipUserGoToAppSettting();
                    }
					else
                        finish();
                }
				else
				{
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    // 提示用户去应用设置界面手动开启权限

    private void showDialogTipUserGoToAppSettting()
	{

        dialog = new AlertDialog.Builder(this)
			.setTitle("存储权限不可用")
			.setMessage("请在-应用设置-权限-中，允许使用存储权限来保存数据")
			.setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// 跳转到应用设置界面
					goToAppSetting();
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			}).setCancelable(false).show();
    }



    // 跳转到当前应用的设置界面
    private void goToAppSetting()
	{
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 123);
    }

    //回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123)
		{

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			{
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED)
				{
                    // 提示用户应该去应用设置界面手动开启权限
                    showDialogTipUserGoToAppSettting();
                }
				else
				{

                    if (dialog != null && dialog.isShowing())
					{

                        dialog.dismiss();
                    }
                   	Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();



				}
            }
        }
    }

}

