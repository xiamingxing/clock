package com.hackthon.util;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;


import android.R.bool;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class MediaRecorderUtils {
	private boolean isStopRecord = false;
	private String path = null;
	private SystemDayTime date = null;
	private String mFilepath = null;
	private MediaRecorder mRecorder = null;
	private File mFilename;
	private String filename= null;
	
	public MediaRecorderUtils(Context context)
	{
		isStopRecord = false;
	//	path = getSDPath()+"/"+"Media";
		date = new SystemDayTime();
//		String tmp = date.gettoday();
//		String tmp2 = date.gettime(context);
//		mFileName = date.gettoday()+date.gettime(context);
//		mFilepath = path+"/"+date.gettodaydir()+"_"+date.gettimedir(context);
//		mFilename = new File(mFilepath+"/"+"sound.3gp");
		//mFilepath = path+"/"+date.gettodaydir()+"_"+date.gettimedir(context);
		mFilepath = date.gettodaydir()+"_"+date.gettimedir(context);
		mRecorder = new MediaRecorder();
	}
  //  private FileDescriptor  mFileName = new FileDescriptor();
	
	public void startRecording() {
		isStopRecord = true;
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
        
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        
        filename = getSDPath() + "/" + mFilepath + ".amr";
        File f = new File(filename);
        if(!f.exists()){
        	try {
				f.createNewFile();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        mRecorder.setOutputFile(filename);
        
		//mFilename = new File(mFilepath+"/"+"sound.3gp");
		//mFilename = new File(mFilepath + ".3gp");
       // mRecorder.setOutputFile("");
        try
        {
            mRecorder.prepare();
            
        }
        catch (IOException e) 
        {
            Log.e("msg", "prepare() failed");
            isStopRecord = false;
            mRecorder.reset();
            mRecorder.release();
            mRecorder = null;
            return;
        }
        try
        {
        	Thread.sleep(1000);
        	mRecorder.start();
        }
        catch(InterruptedException e)
        {
        	e.printStackTrace();
        }
}
//	@Override
//	public void run()
//	{
//		// TODO Auto-generated method stub
//		super.run();
//		startRecording();
//		while(true)
//		{
//			
//		}
//	}

	public String getSDPath(){ 
	       File sdDir = null; 	                                 
	       sdDir = Environment.getExternalStorageDirectory();//获取跟目录 	         
	       return sdDir.toString(); 
	       
	}
	
	public void stopRecording() {
		isStopRecord = true;
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        return;
		
	}
    public String getRecordingname() {
    	return filename;
		
	}
}
