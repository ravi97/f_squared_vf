package com.thehp.f;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Activity_start extends Activity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        DisplayMetrics dm = new DisplayMetrics();
		((WindowManager) this.getSystemService(Context.WINDOW_SERVICE))
		  .getDefaultDisplay().getMetrics(dm);
		f_engine.screenY = dm.heightPixels;
		f_engine.screenX = dm.widthPixels;
        final ImageView f = (ImageView) findViewById(R.id.iv_f);
        final ImageView shadow = (ImageView) findViewById(R.id.iv_shadow);
        final ImageView text = (ImageView) findViewById(R.id.iv_text);
        // load the animation
        final Animation anim_bounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        Animation anim_fall = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        final Animation anim_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_up);
        f.startAnimation(anim_fall);
        
       
        anim_fall.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
				shadow.startAnimation(anim_bounce);		
				
				
			}
			
		
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				text.setVisibility(ImageView.VISIBLE);
				text.startAnimation(anim_up);
				
			}
		});
        
        
        
        
        new Handler().postDelayed(new Thread()
        {
        	
        	public void run() {
        		Intent mainmenu=new Intent(Activity_start.this,Activity_Menu.class);
        		
        		//Intent svc=new Intent(Activity_start.this, Bgm.class);
        		//startService(svc);
        		Activity_start.this.startActivity(mainmenu);
        		Activity_start.this.finish();
        		overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
        	};
        
        }
        , f_engine.TIME_DELAY);
    }
}
