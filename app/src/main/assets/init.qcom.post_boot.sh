#BUG Repair
echo '5120,7680,8192,10240,12800,19200' > /sys/module/lowmemorykiller/parameters/minfree
echo '8192' > /sys/module/lowmemorykiller/parameters/vmpressure_file_min
echo '1' > /sys/module/zcache/parameters/clear_percent
echo '0' > /sys/module/lowmemorykiller/parameters/enable_adaptive_lmk
echo '0,64,128,196,764,1280' > /sys/module/lowmemorykiller/parameters/adj
#BUG Repair
