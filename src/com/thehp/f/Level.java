package com.thehp.f;

import java.sql.Struct;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Level {

	public Paint bg=new Paint();
	public boolean completed=false;
	public int c_r=20,temp=20;
	public int c_hit[],n;
	public int bounce=0;
	public class collect  
	{		
		int x;
		int y;
		int c_r;
		public collect(int i)
		{
			//temp=(int)f_engine.screenX/24;
			Random r =new Random();
			int k;
			do
			{c_r=r.nextInt((int)f_engine.screenY/38);}while(c_r<(int)f_engine.screenY/66);
			if(i>0){
			do{x=r.nextInt(temp+100);}while(x<(c[i-1].x+c[i-1].c_r+20));}
			else {do{x=r.nextInt(temp+f_engine.screenX/5);}while(x<temp);}
			do{y=r.nextInt(f_engine.screenY-3*c_r);}while(y<2*c_r);
			temp+=f_engine.screenX/6;
			c_hit[i]=0;
			
		}
	}
	public collect c[];
	private void create_collect()
	{
		Random r =new Random();
		do {n=r.nextInt(6);}while(n<4);
		
		c_hit=new int[n];
		c=new collect[n];
		for(int i=0;i<n;i++)
		{
			c[i]=new collect(i);	
			
		}
	}
	public Level()
	{
		f_engine.i_vel*=1.067f;
		f_engine.g*=1.0235;
		Random rand = new Random();	
			bg.setARGB(150,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
			
			create_collect();
			}
	public Level(boolean c)
	{
		
		f_engine.i_vel=f_engine.screenY/50;
			bg.setColor(Color.BLACK);
			ball.b_paint.setColor(Color.WHITE);
			
			
			}
	
	
}
