package com.thehp.f;

import java.awt.font.TextAttribute;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Activity_Menu extends Activity {
	public static TextView hss;
	public static TextView p,hlevel;
	public static SoundPoolPlayer sound;
	public static int tut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		sound = new SoundPoolPlayer(this); 
		f_engine.prefs = this.getSharedPreferences("f_squared_hs", Context.MODE_PRIVATE);
		
				
				f_engine.h_name = f_engine.prefs.getString("name", "player");
				f_engine.player_name=f_engine.prefs.getString("lname","player");
				long temp=(long)f_engine.prefs.getLong("score", 0);
				f_engine.h_level=f_engine.prefs.getInt("level", 1);
				tut=f_engine.prefs.getInt("game", 0);
				f_engine.h_score_s=(long)temp/1000;
				f_engine.h_score_m=temp-f_engine.h_score_s*1000;
								
				hss=(TextView)findViewById(R.id.tv_score);
		 p=(TextView)findViewById(R.id.tv_p);
		 TextView thelp=(TextView) findViewById(R.id.tv1);
		 TextView lvl=(TextView) findViewById(R.id.textView2);
		 hlevel=(TextView) findViewById(R.id.tv_hlevel);
		hss.setText(Html.fromHtml(String.valueOf(f_engine.h_score_s)+"<sup>"
		+ String.valueOf(f_engine.h_score_m)+"</sup>"));
		p.setText(f_engine.h_name);
		hlevel.setText(String.valueOf(f_engine.h_level));
		Typeface custom_font2 = Typeface.createFromAsset(getAssets(),
			      "fonts/casper.ttf");
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
			      "fonts/code.otf");
			      p.setTypeface(custom_font);
			      hss.setTypeface(custom_font2);
			      lvl.setTypeface(custom_font);
			      hlevel.setTypeface(custom_font);
			      thelp.setTypeface(custom_font);

		
	}

	public void start(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.iv_sfx)
		{
			if(f_engine.sfx==true)
		    	Activity_Menu.sound.playShortResource(R.raw.bounce);
			Log.e("touchview","...");
			if(tut==1)
			{
			Intent mainmenu=new Intent(Activity_Menu.this,Activity_mainmenu.class);
    		
    		//Intent svc=new Intent(Activity_start.this, Bgm.class);
    		//startService(svc);
    		Activity_Menu.this.startActivity(mainmenu);
    		//Activity_Menu.this.finish();
    		overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
		}
			else
			{
				Activity_Menu.sound.playShortResource(R.raw.bounce);
				Intent help=new Intent(Activity_Menu.this,Activity_help.class);
				

				Activity_Menu.this.startActivity(help);
				//Activity_Menu.this.finish();
				overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
			}
		}
	}
	
	public void settings(View v)
	{// TODO Auto-generated method stub
		if(v.getId()==R.id.iv_gravity)
		{
			if(f_engine.sfx==true)
		    	Activity_Menu.sound.playShortResource(R.raw.bounce);
			Intent settings=new Intent(Activity_Menu.this,Activity_settings.class);
    		

    		Activity_Menu.this.startActivity(settings);
    		//Activity_Menu.this.finish();
    		overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
		}
		
	}
	public void help(View v)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		Intent help=new Intent(Activity_Menu.this,Activity_help.class);
		

		Activity_Menu.this.startActivity(help);
		//Activity_Menu.this.finish();
		overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
		
	}
@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	Log.e("imp","paused");

}

@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	Log.e("imp","stoped");

	
}

}
