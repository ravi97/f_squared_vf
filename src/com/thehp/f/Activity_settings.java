package com.thehp.f;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_settings extends Activity implements OnClickListener {

private TextView player;
private ImageView sfx,change,infinite,sprint;
private ImageView gravity;
private EditText eplayer;

@Override
protected void onCreate(Bundle savedInstanceState) {
	
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.settings);
	TextView tsfx = (TextView) findViewById(R.id.tv_hsm),
			tgravity=(TextView) findViewById(R.id.textView3),
			tplayer=(TextView) findViewById(R.id.tv_hss)
			,tsprint=(TextView) findViewById(R.id.tv_sprint),
					tinfinite=(TextView) findViewById(R.id.tv_infinite),
							thelp=(TextView) findViewById(R.id.tv1)
					;
	ImageView back=(ImageView)findViewById(R.id.iv_back);
	f_engine.prefs = this.getSharedPreferences("f_squared_hs", Context.MODE_PRIVATE);
	back.setOnClickListener(this);
	eplayer=(EditText) findViewById(R.id.et_player);
	player=(TextView) findViewById(R.id.tv_player);
	player.setOnClickListener(this);
	player.setText(f_engine.player_name);
	gravity=(ImageView) findViewById(R.id.iv_gravity);
	gravity.setOnClickListener(this);
	sfx=(ImageView) findViewById(R.id.iv_sfx);
	sfx.setOnClickListener(this);
	sprint=(ImageView) findViewById(R.id.iv_sprint);
	sprint.setOnClickListener(this);
	
	infinite=(ImageView) findViewById(R.id.iv_infinite);
	infinite.setOnClickListener(this);
	change=(ImageView) findViewById(R.id.iv_change);
	change.setOnClickListener(this);
	eplayer.setVisibility(View.INVISIBLE);
	change.setVisibility(View.INVISIBLE);
	
	Typeface custom_font = Typeface.createFromAsset(getAssets(),
		      "fonts/code.otf");
		      tsfx.setTypeface(custom_font);
		      tgravity.setTypeface(custom_font);
		      tplayer.setTypeface(custom_font);
		      tsprint.setTypeface(custom_font);
		      tinfinite.setTypeface(custom_font);
		      thelp.setTypeface(custom_font);
		      player.setTypeface(custom_font);
		      eplayer.setTypeface(custom_font);
	
	if(f_engine.gravity==false)
		gravity.setImageResource(R.drawable.off); 
	else
     	gravity.setImageResource(R.drawable.on);
	
	if(f_engine.sfx==false)
		sfx.setImageResource(R.drawable.off); 
	else
     	sfx.setImageResource(R.drawable.on); 
	
	if(f_engine.numlevels<20)sprint.setImageResource(R.drawable.on);
	else infinite.setImageResource(R.drawable.on);

	}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if(v.getId()==R.id.iv_gravity)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		
		if(f_engine.gravity==false)
		{
			f_engine.gravity=true;
			((ImageView) v).setImageResource(R.drawable.on); 
		}
		else
		{
			f_engine.gravity=false;
			((ImageView) v).setImageResource(R.drawable.off); 
		}
		
	}
	
	else if(v.getId()==R.id.iv_sfx)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		Log.e("gra","clicked");
		if(f_engine.sfx==false)
		{
			f_engine.sfx=true;
			((ImageView) v).setImageResource(R.drawable.on);
		}
		else
		{
			f_engine.sfx=false;
			((ImageView) v).setImageResource(R.drawable.off);
		}
	}
	
	else if(v.getId()==R.id.tv_player)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		v.setVisibility(View.INVISIBLE);
		player.setVisibility(View.INVISIBLE);
		eplayer.setText(player.getText().toString());
		eplayer.setVisibility(View.VISIBLE);
		change.setVisibility(View.VISIBLE);
		
	}
	else if(v.getId()==R.id.iv_change)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		Log.e("set","bounce");
		if(eplayer.getText().toString().length()>7)
		{
		eplayer.setText(eplayer.getText().toString().substring(0,7));
		}
		f_engine.player_name=eplayer.getText().toString();
		player.setText(eplayer.getText().toString());
		eplayer.setVisibility(View.INVISIBLE);
		player.setVisibility(View.VISIBLE);
		change.setVisibility(View.INVISIBLE);
		SharedPreferences.Editor editor = f_engine.prefs.edit();

		editor.putString("lname",
				f_engine.player_name);
		editor.commit();
	}
	else if(v.getId()==R.id.iv_back)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		Activity_settings.this.finish();
		overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
	}
	else if(v.getId()==R.id.iv_sprint)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		((ImageView) v).setImageResource(R.drawable.on);
		infinite.setImageResource(R.drawable.off);
		
		f_engine.numlevels=10;
		
		
	}
	else if(v.getId()==R.id.iv_infinite)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		((ImageView) v).setImageResource(R.drawable.on);
		sprint.setImageResource(R.drawable.off);
		
		f_engine.numlevels=100;
	}
	
}
}
