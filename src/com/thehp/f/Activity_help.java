package com.thehp.f;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Activity_help extends Activity {
	public int count;
	ImageView help,next;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.help);
	help=(ImageView) findViewById(R.id.imageView1);
	next=(ImageView) findViewById(R.id.iv_next);
	count=1;
}

public void trans(View v){
	if(v.getId()==R.id.iv_next)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		count++;
		if(count<4){
		String mDrawableName = "h"+String.valueOf(count);
		int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
		help.setImageResource(resID); 
		}
		if(count==3)
		{
			((ImageView) v).setImageResource(R.drawable.play); 
		}
		else if(count==4)
		{
			Intent mainmenu=new Intent(Activity_help.this,Activity_mainmenu.class);
    		
    		Activity_help.this.startActivity(mainmenu);
    		Activity_help.this.finish();
    		
    		overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
		}
	}
	if(v.getId()==R.id.iv_prev)
	{
		if(f_engine.sfx==true)
	    	Activity_Menu.sound.playShortResource(R.raw.bounce);
		count--;
		if(count>0){
			next.setImageResource(R.drawable.forward_b); 
		String mDrawableName = "h"+String.valueOf(count);
		int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
		help.setImageResource(resID); 
		}
		if(count==0)
		{
			
			Activity_help.this.finish();
			overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
		}
	}
}
}
