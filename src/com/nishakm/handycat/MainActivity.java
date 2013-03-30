package com.nishakm.handycat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private static final int WAIT_TIME = 3000;
	private int sysTime1 = 0;
	private int sysTime2 = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //reset system times
        sysTime1 = 0;
        sysTime2 = 0;
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //response to touching the cat
    public void catResponse(View view){
    	//record time of touch
    	sysTime2 = (int) System.currentTimeMillis();
    	int timeDiff = Math.abs(sysTime2-sysTime1);
    	System.out.println("sysTime1: " + sysTime1 + "; sysTime2: "+ sysTime2 + "; diff: "+ timeDiff);
    	if(sysTime1 == 0 || timeDiff>WAIT_TIME){ //no continuous touching for some time now
    		//meow
    		MediaPlayer playMeow = MediaPlayer.create(this, R.raw.cat_meow);
        	
        	playMeow.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mp.release();
				}
			});
        	playMeow.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					mp.stop();
					mp.release();
					return false;
				}
			});
        	
        	playMeow.start();
        	
    	}else{
    		//purr
    		MediaPlayer playPurr = MediaPlayer.create(this, R.raw.cat_purr);
    		playPurr.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mp.release();
				}
			});
        	playPurr.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					mp.stop();
					mp.release();
					return false;
				}
			});
    		playPurr.start();
    	}
    	//sysTime2 is now old
    	sysTime1 = sysTime2;
    }
    
}
