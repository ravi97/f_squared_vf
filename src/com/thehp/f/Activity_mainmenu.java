package com.thehp.f;

import java.util.Random;



























import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_mainmenu extends Activity  {
	//boolean touched=false,game=true;
	Paint temp=new Paint();
	long t=10;
	Paint temp2=new Paint();

	public static SoundPoolPlayer sound;
	
	
	class RenderView extends View {
			
		public RenderView(Context context) {
			
		super(context);
		}
		protected void onDraw(final Canvas canvas) {
			//draw level background
			
			
			canvas.drawPaint(f_engine.levels[f_engine.l_index].bg);
			
			//draw collectible
			if(ball.r==0.0f)
			{
				temp.setColor(Color.BLACK);
				temp.setStrokeWidth(f_engine.screenY/100);
				temp.setTextSize(f_engine.screenY/3); 
				temp.setTextAlign(Align.CENTER);
				//temp.setTextSkewX((float) -0.25);
				temp.setStyle(Style.FILL_AND_STROKE);

				if(f_engine.gameover==false)
				{ Log.e("ball", "entered");
					f_engine.txt.setStrokeWidth(f_engine.screenY/600);
					f_engine.txt.setTextSize(f_engine.screenY/28); 
					canvas.drawText(String.valueOf(f_engine.maxlevel+1),f_engine.screenX/2, f_engine.screenY/1.7f, temp);
					
					
				}
				new Handler().postDelayed(new Thread()
		        {
		        	
		        	public void run() {
		        		Activity_mainmenu.this.finish();
		        		overridePendingTransition(R.anim.splash_in,R.anim.splash_out);
		        	};
		        
		        }
		        , f_engine.TIME_DELAY-1000);
		        		
		        	
				
			}
			temp.setTextAlign(Align.CENTER);
			temp=f_engine.levels[f_engine.l_index].bg;
			temp.setAlpha(150);
			if(f_engine.l_index==f_engine.maxlevel+1)
			temp.setColor(Color.BLACK);
			
			
			for(int i=0;i<f_engine.levels[f_engine.l_index].n;i++)
			{
				if(f_engine.levels[f_engine.l_index].c_hit[i]==0)
				{
					
					canvas.drawCircle(f_engine.levels[f_engine.l_index].c[i].x, 
							f_engine.levels[f_engine.l_index].c[i].y,
							f_engine.levels[f_engine.l_index].c[i].c_r, ball.b_paint);
					canvas.drawCircle(f_engine.levels[f_engine.l_index].c[i].x, 
							f_engine.levels[f_engine.l_index].c[i].y,
							f_engine.levels[f_engine.l_index].c[i].c_r*0.80f, temp);

				}
				else
				{
					f_engine.levels[f_engine.l_index].c[i].c_r*=0.95;
					canvas.drawCircle(f_engine.levels[f_engine.l_index].c[i].x, 
							f_engine.levels[f_engine.l_index].c[i].y,
							f_engine.levels[f_engine.l_index].c[i].c_r, ball.b_paint);
		
					if(f_engine.levels[f_engine.l_index].c[i].c_r>0)
						{
						if(ball.r<f_engine.screenY/7)
						ball.r*=1.0037f;
						canvas.drawCircle(ball.x, ball.y,ball.r, ball.b_paint);
						canvas.drawCircle(ball.x, ball.y,ball.r*0.92f, temp);
					canvas.drawCircle(ball.x, ball.y,ball.r*0.70f, ball.b_paint);
				}
				}
			}
			//Draw_border(canvas,ball.b_paint);
			//draw ball
			canvas.drawCircle(ball.x, ball.y,ball.r, ball.b_paint);
			canvas.drawCircle(ball.x, ball.y,ball.r*0.92f, temp);
		canvas.drawCircle(ball.x, ball.y,ball.r*0.85f, ball.b_paint);
		
		if(f_engine.l_index>f_engine.numlevels-2)
		{
			
			if(t<f_engine.g_time)
				t=(long) (t*1.1);
			else t=f_engine.g_time;
			
			temp2.setTextAlign(Align.CENTER);
			temp2.setTextSkewX(-0.25f);
			temp2.setStrokeWidth(f_engine.screenY/800);
			temp2.setTextSize(f_engine.screenY/33); 
			temp2.setColor(Color.WHITE);
			canvas.drawText("[ "+String.valueOf(t)+" ]", f_engine.screenX/2, f_engine.screenY/2+f_engine.screenY/26, temp2);
			
		}
		
		
			if(f_engine.game==false)
			{
			
			canvas.drawText(f_engine.title, f_engine.screenX/2, f_engine.screenY/2, f_engine.txt);
			
			}
			 
			else if(f_engine.gameover==false)
			{
				
				if(f_engine.txt.getAlpha()<=0)f_engine.txt.setAlpha(0);
				else
				{
					f_engine.txt.setAlpha((int) (f_engine.txt.getAlpha()*0.95));
					canvas.drawText(f_engine.title,f_engine.screenX/2, f_engine.screenY/2, f_engine.txt);
					
					
				}
				
			} 
			else
			{
				
				canvas.drawText(f_engine.endtitle,f_engine.screenX/2, f_engine.screenY/2, f_engine.txt);
				
				
				
			}
			
			
			
		f_engine.update(canvas);

		Log.e("logged",String.valueOf(f_engine.l_index)+"-"+String.valueOf(f_engine.gameover));
		invalidate();
		}
		public void Draw_border(Canvas canvas,Paint paint)
		{
			canvas.drawRect(0, 0, 10, canvas.getHeight(), paint);
			canvas.drawRect(0, 0, canvas.getWidth(), 10, paint);
			canvas.drawRect(canvas.getWidth()-10, 0, canvas.getWidth(), canvas.getHeight()-10, paint);
			canvas.drawRect(0, canvas.getHeight()-10, canvas.getWidth(), canvas.getHeight(), paint);
		}
		@Override
		  public boolean onTouchEvent(MotionEvent event) {
		    
			final float X = event.getX();
		    final float Y = event.getY();
		    
		    switch (event.getAction()) {
		    case MotionEvent.ACTION_DOWN:
		    	
						f_engine.touched(X,Y);
		    	
		      return true;
		    default:
		      return false;
		    }
		}
	
		

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
				f_engine.init();
				ball.ballinit();
				f_engine.prefs = this.getSharedPreferences("f_squared_hs", Context.MODE_PRIVATE);
				
				//Activity_mainmenu.player.setLooping(false);
				sound = new SoundPoolPlayer(this); 
								//Activity_Menu.player.start();
				Log.e("logged","init init");
		setContentView(new RenderView(this));
		}

	@Override
	protected void onPause() {
		
		//bgm.cancel(false);
		Log.e("imp","paused");
		
	

		super.onPause();
		
	}
	@Override
	protected void onStop() {
		if(f_engine.l_index>f_engine.numlevels-2)
			{
			if(f_engine.h_score_s==0||f_engine.g_time<(f_engine.h_score_s*1000+f_engine.h_score_m))
			{
				f_engine.h_score_s=f_engine.g_time/1000;
				f_engine.h_score_m=f_engine.g_time-f_engine.h_score_s*1000;
				f_engine.scored(f_engine.g_time, f_engine.player_name,f_engine.maxlevel+1);
			 f_engine.h_name=f_engine.player_name;
			}
			
			}
		SharedPreferences.Editor editor = f_engine.prefs.edit();
		if(f_engine.maxlevel+1>f_engine.h_level)
		{
		editor.putInt("level", f_engine.maxlevel+1);
		f_engine.h_level=f_engine.maxlevel;
		}
		editor.putInt("game",1);

		editor.commit();
		Activity_Menu.tut=1;
		
		Activity_Menu.hss.setText(Html.fromHtml(String.valueOf(f_engine.h_score_s)+"<sup>"
				+ String.valueOf(f_engine.h_score_m)+"</sup>"));
		Activity_Menu.p.setText(f_engine.h_name);
		Activity_Menu.hlevel.setText(String.valueOf(f_engine.h_level));
		f_engine.init();
		ball.ballinit();
		
		//bgm.cancel(false);
		Log.e("imp","stoped");
		

		super.onStop();
	}
}

