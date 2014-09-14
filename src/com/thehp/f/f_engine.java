package com.thehp.f;

import java.util.Locale;



import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.util.Log;
import android.media.MediaPlayer;
import android.os.Vibrator;

public class f_engine {

	public static final int TIME_DELAY=4000;
	public static SharedPreferences prefs;
	public static long s_time,g_time,h_score_s=0,h_score_m=0;
	public static String title="bounce.",player_name="player";
	public static String endtitle="Well bounced.",h_name;
	public static int level=1,h_level=1;;
	public static boolean gravity=false,sfx=true;
	public static Paint bg=new Paint();
	public static Paint txt=new Paint();
	public static float g= 0.7f,i_vel=20;
	public static boolean game=false,tut=true,gameover=false;
	public static Level levels[]; 
	public static int unlocked[];
	public static int numlevels=100,l_index,maxlevel=0;
	public static int screenX=400,screenY=800;
	public  static int MAX_VOLUME = 100,soundvolume;


	public static void init()
	{		
		
		//g=(float)screenY/1142;
		g=(float)screenY/1480;
		i_vel=screenY*0.014f;
		levels=new Level[numlevels];
		l_index=0;
		levels[l_index]=new Level();
		Log.e("logged","fengine init");
		unlocked=new int[numlevels];
		for(int i=0;i<numlevels;i++)unlocked[i]=0;
		unlocked[0]=1;
		maxlevel=0;
		ball.ballinit();
		
		txt_init();

		game=false;
		gameover=false;
		}
	
	public static void txt_init()
	{
		txt.setStyle(Style.FILL_AND_STROKE);
		txt.setStrokeWidth((float)screenY/150);
		txt.setColor(Color.BLACK);
		txt.setTextAlign(Align.CENTER);
		txt.setTextSize(screenY/16);
		txt.setTextSkewX((float) -0.25);
	}
	public static void update(Canvas canvas)
	{
		
		int temp=0;
	
		
		for(int i=0;i<levels[f_engine.l_index].n;i++)
		{
			double cdist=Math.sqrt(Math.pow(levels[f_engine.l_index].c[i].x-ball.x, 2.0)+
					Math.pow(levels[f_engine.l_index].c[i].y-ball.y, 2.0));
			if(cdist<=ball.r+levels[f_engine.l_index].c[i].c_r)
				{if(levels[l_index].c[i].c_r>0)levels[l_index].c_hit[i]=1;
				
				}
			if(levels[l_index].c_hit[i]==1)temp++;
			
		}
		if(temp==levels[l_index].n){
			levels[l_index].completed=true;
		}
		ball.x=ball.x+ball.vel_x;
		ball.vel_y=ball.vel_y+f_engine.g;
		ball.y=ball.y+ball.vel_y;
		if((ball.y>(canvas.getHeight()-ball.r))||(ball.y<ball.r))
		{if(ball.y>canvas.getHeight()-ball.r){ball.y=canvas.getHeight()-ball.r;
		if(game)
		{ Log.e("ball",String.valueOf(ball.r));
			
		if((int)ball.r>0)
		{
			if(g>0)
			ball.r*=0.9;
			else if(gravity==true)
		{g*=-1;ball.vel_y*=-2.5f;}
		}
			else ball.r=0f;}
			}
		if(ball.y<ball.r){
			
			ball.y=ball.r;
			if(game)
			{ Log.e("ball",String.valueOf(ball.r));
				
			if((int)ball.r>0)
			{
				if(g<0)
				ball.r*=0.9;
				else if(gravity==true)
			{g*=-1;ball.vel_y*=-2.5f;}
			}
				else ball.r=0f;}
		}
		
		ball.vel_y*=-0.88f;
		ball.vel_x*=0.95f;
		//ball.r-=10;
		}
		if(f_engine.l_index==0)
		{
		if((ball.x>(canvas.getWidth()-ball.r))||(ball.x<ball.r))
		{
			
			if(f_engine.levels[l_index].completed)
			{
				if(ball.x>canvas.getWidth()&&ball.vel_x>0){
				ball.x=0;
			++l_index;if(unlocked[l_index]==0){f_engine.levels[l_index]=new Level();
			unlocked[l_index]=1;++maxlevel;
			}
				}
				
				else if (ball.x<ball.r&&ball.vel_x<0){ball.x=ball.r;ball.vel_x*=-0.88f;}
			}
		else 
			{
			if(ball.vel_x>0)
				{ball.x=canvas.getWidth()-ball.r;ball.vel_x*=-0.88f;}
			else if (ball.x<ball.r&&ball.vel_x<0){ball.x=ball.r;ball.vel_x*=-0.88f;}
			}

		
		}
		}
		else if(f_engine.l_index<=maxlevel)
		{
			if(maxlevel<numlevels-2){
				
			if(levels[l_index].completed){
				Log.e("tag","loop1");
				if(ball.x>canvas.getWidth()){
					ball.x=0;++l_index;if(unlocked[l_index]==0){f_engine.levels[l_index]=new Level();
					unlocked[l_index]=1;++maxlevel;
					}
				}
					else if(ball.x<0&&ball.vel_x<0){ball.x=canvas.getWidth();
					--l_index;}
			}
			else
			{	
				Log.e("tag","loop2");
				if(ball.x>canvas.getWidth()-ball.r&&ball.vel_x>0){
					ball.x=canvas.getWidth()-ball.r;ball.vel_x*=-0.88f;
				}
					
					
					else if(ball.x<0&&ball.vel_x<0){ball.x=canvas.getWidth();
					--l_index;}
			}
						
		}
			else
				{
				
				if(levels[l_index].completed){
					gameover=true;
					g_time=System.currentTimeMillis()-s_time;
					Log.e("tag","loop3");
					
					if(ball.x>canvas.getWidth()){
						ball.x=0;++l_index;if(unlocked[l_index]==0){f_engine.levels[l_index]=new Level(false);
						//unlocked[l_index]=1;++maxlevel;
						f_engine.txt.setStrokeWidth(f_engine.screenY/400);
						txt.setTextSize(f_engine.screenY/28); 
						f_engine.txt.setColor(ball.b_paint.getColor());
						g_time=System.currentTimeMillis()-s_time;
						//sharedPreferences.
						}
					}
						else if(ball.x<0&&ball.vel_x<0){ball.x=canvas.getWidth(); --l_index;}
						//else if (ball.x<ball.r&&ball.vel_x<0){ball.x=ball.r;ball.vel_x*=-0.88f;}
				}
				else
				{	
					Log.e("tag","loop4");
					if(ball.x>canvas.getWidth()-ball.r&&ball.vel_x>0){
						ball.x=canvas.getWidth()-ball.r;ball.vel_x*=-0.88f;
					}
						
						
						else if(ball.x<0&&ball.vel_x<0){ball.x=canvas.getWidth();
						--l_index;}
					
					
					
				}
				}
				}
		
		else
		{
			if((ball.x>(canvas.getWidth()-ball.r))||(ball.x<ball.r))
			{
				
				if(ball.x>canvas.getWidth()-ball.r&&ball.vel_x>0)
				{ball.x=canvas.getWidth()-ball.r;ball.vel_x*=-0.88f;}
			else if (ball.x<ball.r&&ball.vel_x<0){ball.x=ball.r;ball.vel_x*=-0.88f;}
				
		}	
		}
}
	
	public static void scored(long score,String name,int level)
	{
		SharedPreferences.Editor editor = prefs.edit();

	editor.putString("name",
			name);

	if(gravity==false)
	editor.putLong("score",
			score);
	else
		editor.putLong("score",
				score);
	
	if(numlevels>20)
	editor.putInt("level",
			level);
	
	editor.putInt("game",1);

	
	//editor.putInt("locationCount", locationCount);

	editor.commit();
	}
	public static void touched(float eventX,float eventY)
	{
		
		double dist=Math.sqrt(Math.pow(eventX-ball.x, 2.0)+Math.pow(eventY-ball.y, 2.0));
	    if(eventX>450&&eventY>770&&eventX<470&&eventY<790)
	    {
	    	if(game==false)
		    	s_time=System.currentTimeMillis();
		    	game=true;
	    }
		
		if(dist<ball.r*2){
	    	if(game==false)
	    	s_time=System.currentTimeMillis();
	    	game=true;
	        //ballsound.run();
	    	if(sfx==true)
	    	Activity_mainmenu.sound.playShortResource(R.raw.bounce);
	
	     float tan =Math.abs((eventY-ball.y)/(eventX-ball.x));
	    if(eventY>ball.y&&eventX<ball.x)
	     {ball.vel_y= (float) -(Math.sin(Math.atan(tan))*i_vel);
	     ball.vel_x= (float) (Math.cos(Math.atan(tan))*i_vel);
	     }
	    else if(eventY>ball.y&&eventX>ball.x)
	     {ball.vel_y= (float) -(Math.sin(Math.atan(tan))*i_vel);
	     ball.vel_x= (float) -(Math.cos(Math.atan(tan))*i_vel);
	     }
	    else if(eventY<ball.y&&eventX<ball.x)
	     {ball.vel_y= (float) (Math.sin(Math.atan(tan))*i_vel);
	     ball.vel_x= (float) (Math.cos(Math.atan(tan))*i_vel);
	     }
	    else if(eventY<ball.y&&eventX>ball.x)
	     {ball.vel_y= (float) (Math.sin(Math.atan(tan))*i_vel);
	     ball.vel_x= (float) -(Math.cos(Math.atan(tan))*i_vel);
	     }
	    }
	}
}
