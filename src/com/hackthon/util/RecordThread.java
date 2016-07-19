package com.hackthon.util;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;



public class RecordThread extends Thread 
{ 
	private AudioRecord ar; 
	private int bs; 
	private static int SAMPLE_RATE_IN_HZ = 8000; 
	private boolean isRun = false; 
	 private static int BLOW_ACTIVI=2425;
	 private int number = 1;
     private int tal = 1;
     private long currenttime;
     private long endtime;
     private long time = 1;
	private Handler handler = null;
	public RecordThread(Handler handle, AudioRecord au, int bb) 
	{ 
		super(); 
		handler = handle;
		
		bs = bb;//AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT); 
		ar = au;//new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, bs); 
	} 
	public void run() 
	{ 
		super.run(); 
		
		ar.startRecording(); 
		// 用于读取的 
	    byte[] buffer = new byte[bs]; 
	    isRun = true;
//		while (isRun) 
//		{ 
//			int r = ar.read(buffer, 0, bs); 
//			int v = 0; 
//			// 将 buffer 内容取出，进行平方和运算 
//			for (int i = 0; i < buffer.length; i++) 
//			{ 
//				// 这里没有做运算的优化，为了更加清晰的展示代码 
//				v += buffer[i] * buffer[i];			
//			} 
//			// 平方和除以数据总长度，得到音量大小。可以获取白噪声值，然后对实际采样进行标准化。 
//			// 如果想利用这个数值进行操作，建议用 sendMessage 将其抛出，在 Handler 里进行处理。 
//			//Log.d("spl", String.valueOf(v / (float) r)); 
//			Message msg = new Message();
//			msg.arg1 = ((int)v) / r;
//			handler.sendMessage(msg);
//		} 
//		ar.stop(); 
	    
	    
	    try 
	    {
            ar.startRecording();
  
            // 用于读取的 buffer
            while (true) 
            {
                    number++;
                    sleep(8);
                    currenttime = System.currentTimeMillis();
                    int r = ar.read(buffer, 0, bs) + 1;
                    int v = 0;
                    for (int i = 0; i < buffer.length; i++) {
                            v += buffer[i] * buffer[i];
                    }
                    int value = Integer.valueOf(v / (int) r);
                    tal = tal + value;
                    endtime = System.currentTimeMillis();
                    time = time + (endtime - currenttime);
    
                    if (time >= 500 || number > 5) 
                    {

                        int total = tal / number;
                        if (total > BLOW_ACTIVI) 
                        {
                                //发送消息通知到界面 触发动画
                               
                                //利用传入的handler 给界面发送通知
                        	Message msg = new Message();
                			msg.arg1 = total;
                			handler.sendMessage(msg);
                			break;
                		}
                        number = 1;
                        tal = 1;
                        time = 1;
                    }

            }
            
      } 
	 catch (Exception e) {
            e.printStackTrace();
     }

	} 
	public void pause() 
	{ 
		// 在调用本线程的 Activity 的 onPause 里调用，以便 Activity 暂停时释放麦克风 
		isRun = false; 
	} 
	public void start()
	{ 
		// 在调用本线程的 Activity 的 onResume 里调用，以便 Activity 恢复后继续获取麦克风输入音量 
		if (!isRun) { 
		super.start(); 
	} 
	}

}