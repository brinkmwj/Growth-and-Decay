/*
 * This giant and ugly hack was written by Bo.
 * 
 * Flowers, graphics, design and playtesting by: Pratima
 * Videos, animation, and design by: Jake
 * Sound, design, and playtesing by: Mike
 * 
 * Bo, Pratima, Jake and Mike are The Miami Word Machine,
 * Miami University Global Game Jam 2011
 * 
 * This code is creative common attribution licensed. Enjoy it, but give credit!
 * 
 * 
 * 2011-01-29, 10:03pm, EST - Bo
 * 
 * 
 * TODO LIST
 * 1) Add menu, "new game" option
 * 		DONE 2) Expand bounds where user can tap to place letters
 * 3) Add sound effects
 * 		DONE 4) Change effects colors
 * 5) Redesign interface for decay phase
 * 6) High scores screen?
 * 		DONE 7) Add bitmap filter to all drawBitmap calls
 * 		DONE 8) Create a unified drawTile method
 * 		NO - #5 will fix this 9) Detect when no possible moves left in decay phase
 * 10) Allow dragging using touch up/down
 * 		DONE 11) Show bonus points for extinct words
 * 		DONE 12) Effects for left-over letters
 * 13) Is it possible to animate the background and still get good performance?
 */

package muggj.wordextinction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;


import android.app.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class WordExctinction extends Activity  {
	public HashSet<String> wordSet;

	Activity mActivity;
	WEView mView;
	Random rnd;
	Bitmap fl1TileImage;
	Bitmap fl2TileImage;
	Bitmap fl3TileImage;
	//Bitmap shearTile;
	
	Bitmap bg;
	
	static int MSPERFRAME = 1000/24;
	static int p1color = 0xfff17720;
	static int p2color = 0xfff420e8;
	static int textColor = 0xff000000;
	static int textBorderColor = 0xffffffff;
	static int textHighliteColor = 0xffffff00;
	
	Bitmap greyButton;
	Bitmap activeButton;
	Bitmap checkedButton;
	Bitmap shearsImage;
	Bitmap sickleImage;
	Bitmap logoImage;
	Bitmap petal1;
	Bitmap petal2;
	Bitmap petal3;
	Resources res;

	int[] gbgs;
	int[] dbgs;
	int[] splashes;
	
	boolean inDraw;
	
	//2 is high, 1 is medium, 0 is low
	int mGraphicsLevel;
	
	Paint bmP;

	
	//this is only needed for taking screenshots
	/*public void onBackPressed() {

	   return;
	}*/
	
	public void loadBitmaps(){
		Log.e("GaD","loadBitmaps called at level: "+mGraphicsLevel);
		if(mGraphicsLevel == 2){
			//First, try loading hi res images
			try{
				fl1TileImage = BitmapFactory.decodeResource(res, R.drawable.flower1_h);
				fl2TileImage = BitmapFactory.decodeResource(res, R.drawable.flower2_h);
				fl3TileImage = BitmapFactory.decodeResource(res, R.drawable.flower3_h);
				
				bg = BitmapFactory.decodeResource(res, R.drawable.bg0030_h);//This is to test memory availability
				
				greyButton = BitmapFactory.decodeResource(res, R.drawable.grbut_h);
				activeButton = BitmapFactory.decodeResource(res, R.drawable.actbut_h);
				checkedButton = BitmapFactory.decodeResource(res, R.drawable.checkbutton_h);
		
				shearsImage = BitmapFactory.decodeResource(res, R.drawable.gardenshears_h);
				sickleImage = BitmapFactory.decodeResource(res, R.drawable.sickle_h);
		
				logoImage = BitmapFactory.decodeResource(res, R.drawable.icon_h);
		
				petal1 = BitmapFactory.decodeResource(res, R.drawable.petal1_h);
				petal2 = BitmapFactory.decodeResource(res, R.drawable.petal2_h);
				petal3 = BitmapFactory.decodeResource(res, R.drawable.petal3_h);
				
				Log.e("GaD","loadBitmaps succeeded at level: "+mGraphicsLevel);
				
				
				/*splashes[ 0] = R.drawable.splash0002_m;
				splashes[ 1] = R.drawable.splash0004_m;
				splashes[ 2] = R.drawable.splash0006_m;
				splashes[3 ] = R.drawable.splash0008_m;
				splashes[ 4] = R.drawable.splash0010_m;
				splashes[5] = R.drawable.splash0012_m;
				splashes[6] = R.drawable.splash0014_m;
				splashes[7] = R.drawable.splash0016_m;
				splashes[8] = R.drawable.splash0018_m;
				splashes[9] = R.drawable.splash0020_m;
				splashes[10] = R.drawable.splash0022_m;
				splashes[11] = R.drawable.splash0024_m;
				splashes[12] = R.drawable.splash0026_m;
				splashes[13] = R.drawable.splash0028_m;
				splashes[14] = R.drawable.splash0030_m;
				splashes[15] = R.drawable.splash0032_m;
				splashes[16] = R.drawable.splash0034_m;
				splashes[17] = R.drawable.splash0036_m;
				splashes[18] = R.drawable.splash0038_m;
				splashes[19] = R.drawable.splash0040_m;
				splashes[20] = R.drawable.splash0042_m;
				splashes[21] = R.drawable.splash0044_m;
				splashes[22] = R.drawable.splash0046_m;
				splashes[23] = R.drawable.splash0048_m;
				splashes[24] = R.drawable.splash0050_m;
				splashes[25] = R.drawable.splash0052_m;
				splashes[26] = R.drawable.splash0054_m;
				splashes[27] = R.drawable.splash0056_m;
				splashes[28] = R.drawable.splash0058_m;
				splashes[29] = R.drawable.splash0060_m;
				splashes[30] = R.drawable.splash0062_m;
				splashes[31] = R.drawable.splash0064_m;
				splashes[32] = R.drawable.splash0066_m;*/
				splashes[33] = R.drawable.splash0068_m;
				
				/*
				gbgs[0] = R.drawable.bg0002_m;
				gbgs[1] = R.drawable.bg0004_m;
				gbgs[2] = R.drawable.bg0006_m;
				gbgs[3] = R.drawable.bg0008_m;
				gbgs[4] = R.drawable.bg0010_m;
				gbgs[5] = R.drawable.bg0012_m;
				gbgs[6] = R.drawable.bg0014_m;
				gbgs[7] = R.drawable.bg0016_m;
				gbgs[8] = R.drawable.bg0018_m;
				gbgs[9] = R.drawable.bg0020_m;
				gbgs[10] = R.drawable.bg0022_m;
				gbgs[11] = R.drawable.bg0024_m;
				gbgs[12] = R.drawable.bg0026_m;
				gbgs[13] = R.drawable.bg0028_m;
				gbgs[14] = R.drawable.bg0030_m;
				
				dbgs[0] = R.drawable.bg0032_m;
				dbgs[1] = R.drawable.bg0034_m;
				dbgs[2] = R.drawable.bg0036_m;
				dbgs[3] = R.drawable.bg0038_m;
				dbgs[4] = R.drawable.bg0040_m;
				dbgs[5] = R.drawable.bg0042_m;
				dbgs[6] = R.drawable.bg0044_m;
				dbgs[7] = R.drawable.bg0046_m;
				dbgs[8] = R.drawable.bg0048_m;
				dbgs[9] = R.drawable.bg0050_m;
				dbgs[10] = R.drawable.bg0052_m;
				dbgs[11] = R.drawable.bg0054_m;
				dbgs[12] = R.drawable.bg0056_m;
				dbgs[13] = R.drawable.bg0058_m;
				dbgs[14] = R.drawable.bg0060_m;
				*/
				
				/*gbgs[0] = R.drawable.bg0002_h;
				gbgs[1] = R.drawable.bg0004_h;
				gbgs[2] = R.drawable.bg0006_h;
				gbgs[3] = R.drawable.bg0008_h;
				gbgs[4] = R.drawable.bg0010_h;
				gbgs[5] = R.drawable.bg0012_h;
				gbgs[6] = R.drawable.bg0014_h;
				gbgs[7] = R.drawable.bg0016_h;
				gbgs[8] = R.drawable.bg0018_h;
				gbgs[9] = R.drawable.bg0020_h;
				gbgs[10] = R.drawable.bg0022_h;
				gbgs[11] = R.drawable.bg0024_h;
				gbgs[12] = R.drawable.bg0026_h;
				gbgs[13] = R.drawable.bg0028_h;*/
				gbgs[14] = R.drawable.bg0030_h;
				
				/*dbgs[0] = R.drawable.bg0032_h;
				dbgs[1] = R.drawable.bg0034_h;
				dbgs[2] = R.drawable.bg0036_h;
				dbgs[3] = R.drawable.bg0038_h;
				dbgs[4] = R.drawable.bg0040_h;
				dbgs[5] = R.drawable.bg0042_h;
				dbgs[6] = R.drawable.bg0044_h;
				dbgs[7] = R.drawable.bg0046_h;
				dbgs[8] = R.drawable.bg0048_h;
				dbgs[9] = R.drawable.bg0050_h;
				dbgs[10] = R.drawable.bg0052_h;
				dbgs[11] = R.drawable.bg0054_h;
				dbgs[12] = R.drawable.bg0056_h;
				dbgs[13] = R.drawable.bg0058_h;*/
				dbgs[14] = R.drawable.bg0060_h;
				
				
				//If it worked, set the ids for the other BG and Splash images
			} catch (OutOfMemoryError e){
				Log.e("GaD","Hi-res image load failed");
				mGraphicsLevel = 1;
			}
		}
		
		if(mGraphicsLevel == 1){
			//If loading hi-res failed, try medium res
			
			fl1TileImage = null;
			fl2TileImage = null;
			fl3TileImage = null;
			
			bg = null;
			
			greyButton = null;
			activeButton = null;
			checkedButton = null;
	
			shearsImage = null;
			sickleImage = null;
	
			logoImage = null;
	
			petal1 = null;
			petal2 = null;
			petal3 = null;
			System.gc();
			
			try{
				fl1TileImage = BitmapFactory.decodeResource(res, R.drawable.flower1_m);
				fl2TileImage = BitmapFactory.decodeResource(res, R.drawable.flower2_m);
				fl3TileImage = BitmapFactory.decodeResource(res, R.drawable.flower3_m);
				
				bg = BitmapFactory.decodeResource(res, R.drawable.bg0030_m);
				
				greyButton = BitmapFactory.decodeResource(res, R.drawable.grbut_m);
				activeButton = BitmapFactory.decodeResource(res, R.drawable.actbut_m);
				checkedButton = BitmapFactory.decodeResource(res, R.drawable.checkbutton_m);
		
				shearsImage = BitmapFactory.decodeResource(res, R.drawable.gardenshears_m);
				sickleImage = BitmapFactory.decodeResource(res, R.drawable.sickle_h);
		
				logoImage = BitmapFactory.decodeResource(res, R.drawable.icon_m);
		
				petal1 = BitmapFactory.decodeResource(res, R.drawable.petal1_m);
				petal2 = BitmapFactory.decodeResource(res, R.drawable.petal2_m);
				petal3 = BitmapFactory.decodeResource(res, R.drawable.petal3_m);
				
				Log.e("GaD","loadBitmaps succeeded at level: "+mGraphicsLevel);
				
				
				
				/*gbgs[0] = R.drawable.bg0002_m;
				gbgs[1] = R.drawable.bg0004_m;
				gbgs[2] = R.drawable.bg0006_m;
				gbgs[3] = R.drawable.bg0008_m;
				gbgs[4] = R.drawable.bg0010_m;
				gbgs[5] = R.drawable.bg0012_m;
				gbgs[6] = R.drawable.bg0014_m;
				gbgs[7] = R.drawable.bg0016_m;
				gbgs[8] = R.drawable.bg0018_m;
				gbgs[9] = R.drawable.bg0020_m;
				gbgs[10] = R.drawable.bg0022_m;
				gbgs[11] = R.drawable.bg0024_m;
				gbgs[12] = R.drawable.bg0026_m;
				gbgs[13] = R.drawable.bg0028_m;*/
				gbgs[14] = R.drawable.bg0030_m;
				
				/*dbgs[0] = R.drawable.bg0032_m;
				dbgs[1] = R.drawable.bg0034_m;
				dbgs[2] = R.drawable.bg0036_m;
				dbgs[3] = R.drawable.bg0038_m;
				dbgs[4] = R.drawable.bg0040_m;
				dbgs[5] = R.drawable.bg0042_m;
				dbgs[6] = R.drawable.bg0044_m;
				dbgs[7] = R.drawable.bg0046_m;
				dbgs[8] = R.drawable.bg0048_m;
				dbgs[9] = R.drawable.bg0050_m;
				dbgs[10] = R.drawable.bg0052_m;
				dbgs[11] = R.drawable.bg0054_m;
				dbgs[12] = R.drawable.bg0056_m;
				dbgs[13] = R.drawable.bg0058_m;*/
				dbgs[14] = R.drawable.bg0060_m;
				
				/*splashes[ 0] = R.drawable.splash0002_m;
				splashes[ 1] = R.drawable.splash0004_m;
				splashes[ 2] = R.drawable.splash0006_m;
				splashes[3 ] = R.drawable.splash0008_m;
				splashes[ 4] = R.drawable.splash0010_m;
				splashes[5] = R.drawable.splash0012_m;
				splashes[6] = R.drawable.splash0014_m;
				splashes[7] = R.drawable.splash0016_m;
				splashes[8] = R.drawable.splash0018_m;
				splashes[9] = R.drawable.splash0020_m;
				splashes[10] = R.drawable.splash0022_m;
				splashes[11] = R.drawable.splash0024_m;
				splashes[12] = R.drawable.splash0026_m;
				splashes[13] = R.drawable.splash0028_m;
				splashes[14] = R.drawable.splash0030_m;
				splashes[15] = R.drawable.splash0032_m;
				splashes[16] = R.drawable.splash0034_m;
				splashes[17] = R.drawable.splash0036_m;
				splashes[18] = R.drawable.splash0038_m;
				splashes[19] = R.drawable.splash0040_m;
				splashes[20] = R.drawable.splash0042_m;
				splashes[21] = R.drawable.splash0044_m;
				splashes[22] = R.drawable.splash0046_m;
				splashes[23] = R.drawable.splash0048_m;
				splashes[24] = R.drawable.splash0050_m;
				splashes[25] = R.drawable.splash0052_m;
				splashes[26] = R.drawable.splash0054_m;
				splashes[27] = R.drawable.splash0056_m;
				splashes[28] = R.drawable.splash0058_m;
				splashes[29] = R.drawable.splash0060_m;
				splashes[30] = R.drawable.splash0062_m;
				splashes[31] = R.drawable.splash0064_m;
				splashes[32] = R.drawable.splash0066_m;*/
				splashes[33] = R.drawable.splash0068_m;
				//If it worked, set the ids for the other BG and Splash images
			} catch (OutOfMemoryError e){
				Log.e("GaD","Mid-res image load failed");
				mGraphicsLevel = 0;
			}
		}
		
		if(mGraphicsLevel == 0){
			//If loading medium-res failed, try low-res
			
			fl1TileImage = null;
			fl2TileImage = null;
			fl3TileImage = null;
			
			bg = null;
			
			greyButton = null;
			activeButton = null;
			checkedButton = null;
	
			shearsImage = null;
			sickleImage = null;
	
			logoImage = null;
	
			petal1 = null;
			petal2 = null;
			petal3 = null;
			System.gc();
			
			try{
				fl1TileImage = BitmapFactory.decodeResource(res, R.drawable.flower1_s);
				fl2TileImage = BitmapFactory.decodeResource(res, R.drawable.flower2_s);
				fl3TileImage = BitmapFactory.decodeResource(res, R.drawable.flower3_s);
				
				bg = BitmapFactory.decodeResource(res, R.drawable.bg0030_s);
				
				greyButton = BitmapFactory.decodeResource(res, R.drawable.grbut_s);
				activeButton = BitmapFactory.decodeResource(res, R.drawable.actbut_s);
				checkedButton = BitmapFactory.decodeResource(res, R.drawable.checkbutton_s);
		
				shearsImage = BitmapFactory.decodeResource(res, R.drawable.gardenshears_s);
				sickleImage = BitmapFactory.decodeResource(res, R.drawable.sickle_m);
		
				logoImage = BitmapFactory.decodeResource(res, R.drawable.icon_s);
		
				petal1 = BitmapFactory.decodeResource(res, R.drawable.petal1_s);
				petal2 = BitmapFactory.decodeResource(res, R.drawable.petal2_s);
				petal3 = BitmapFactory.decodeResource(res, R.drawable.petal3_s);
				
				Log.e("GaD","loadBitmaps succeeded at level: "+mGraphicsLevel);
				/*gbgs[0] = R.drawable.bg0002_s;
				gbgs[1] = R.drawable.bg0004_s;
				gbgs[2] = R.drawable.bg0006_s;
				gbgs[3] = R.drawable.bg0008_s;
				gbgs[4] = R.drawable.bg0010_s;
				gbgs[5] = R.drawable.bg0012_s;
				gbgs[6] = R.drawable.bg0014_s;
				gbgs[7] = R.drawable.bg0016_s;
				gbgs[8] = R.drawable.bg0018_s;
				gbgs[9] = R.drawable.bg0020_s;
				gbgs[10] = R.drawable.bg0022_s;
				gbgs[11] = R.drawable.bg0024_s;
				gbgs[12] = R.drawable.bg0026_s;
				gbgs[13] = R.drawable.bg0028_s;*/
				gbgs[14] = R.drawable.bg0030_s;
				
				/*dbgs[0] = R.drawable.bg0032_s;
				dbgs[1] = R.drawable.bg0034_s;
				dbgs[2] = R.drawable.bg0036_s;
				dbgs[3] = R.drawable.bg0038_s;
				dbgs[4] = R.drawable.bg0040_s;
				dbgs[5] = R.drawable.bg0042_s;
				dbgs[6] = R.drawable.bg0044_s;
				dbgs[7] = R.drawable.bg0046_s;
				dbgs[8] = R.drawable.bg0048_s;
				dbgs[9] = R.drawable.bg0050_s;
				dbgs[10] = R.drawable.bg0052_s;
				dbgs[11] = R.drawable.bg0054_s;
				dbgs[12] = R.drawable.bg0056_s;
				dbgs[13] = R.drawable.bg0058_s;*/
				dbgs[14] = R.drawable.bg0060_s;
				
				/*splashes[ 0] = R.drawable.splash0002_s;
				splashes[ 1] = R.drawable.splash0004_s;
				splashes[ 2] = R.drawable.splash0006_s;
				splashes[3 ] = R.drawable.splash0008_s;
				splashes[ 4] = R.drawable.splash0010_s;
				splashes[5] = R.drawable.splash0012_s;
				splashes[6] = R.drawable.splash0014_s;
				splashes[7] = R.drawable.splash0016_s;
				splashes[8] = R.drawable.splash0018_s;
				splashes[9] = R.drawable.splash0020_s;
				splashes[10] = R.drawable.splash0022_s;
				splashes[11] = R.drawable.splash0024_s;
				splashes[12] = R.drawable.splash0026_s;
				splashes[13] = R.drawable.splash0028_s;
				splashes[14] = R.drawable.splash0030_s;
				splashes[15] = R.drawable.splash0032_s;
				splashes[16] = R.drawable.splash0034_s;
				splashes[17] = R.drawable.splash0036_s;
				splashes[18] = R.drawable.splash0038_s;
				splashes[19] = R.drawable.splash0040_s;
				splashes[20] = R.drawable.splash0042_s;
				splashes[21] = R.drawable.splash0044_s;
				splashes[22] = R.drawable.splash0046_s;
				splashes[23] = R.drawable.splash0048_s;
				splashes[24] = R.drawable.splash0050_s;
				splashes[25] = R.drawable.splash0052_s;
				splashes[26] = R.drawable.splash0054_s;
				splashes[27] = R.drawable.splash0056_s;
				splashes[28] = R.drawable.splash0058_s;
				splashes[29] = R.drawable.splash0060_s;
				splashes[30] = R.drawable.splash0062_s;
				splashes[31] = R.drawable.splash0064_s;
				splashes[32] = R.drawable.splash0066_s;*/
				splashes[33] = R.drawable.splash0068_s;
				//If it worked, set the ids for the other BG and Splash images
			} catch (OutOfMemoryError e){
				//TODO: Give the user a meaningful error message
				Log.e("GaD","Low-res image load failed");
			}
		}
		
		bg = null;
	}
	
	public void loadWords(){
		wordSet = new HashSet<String>();
		res = getResources();
		InputStream wordIS = res.openRawResource(R.raw.wordlistoneperline);
		BufferedReader in = new BufferedReader(new InputStreamReader(wordIS));

		String aLine;
		try {
			while((aLine = in.readLine()) != null){
				wordSet.add(aLine);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rnd = new Random();
		
		mView = new WEView(this);
		setContentView(mView);
		mView.setOnTouchListener(mView);

		//Calculate graphics level based on screen density
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		mGraphicsLevel = 2;
		switch(metrics.densityDpi){
		case DisplayMetrics.DENSITY_HIGH:
		default:
			mGraphicsLevel = 2;
			break;
			
		case DisplayMetrics.DENSITY_MEDIUM:
			mGraphicsLevel = 1;
			break;
			
		case DisplayMetrics.DENSITY_LOW:
			mGraphicsLevel = 0;
			break;
		}
			
		
		dbgs = new int[15];
		gbgs = new int[15];
		splashes = new int[34];
		
		bmP = new Paint();
		bmP.setFilterBitmap(true);
		
		mActivity = this;
	}

	protected void onResume(){
		
		Log.e("GaD","Resuming");
		inDraw = false;
		loadWords();
		loadBitmaps();
		
		super.onResume();
		mView.setVisibility(View.VISIBLE);
	}
	
	protected void onPause(){
		//This stops onDraw from being called?
		mView.setVisibility(View.INVISIBLE);
		super.onPause();
		
		/*
		 * TODO: This is almost certainly not the right way to
		 * synchronize onPause with onDraw. Figure it out later.
		 * 
		 * Goal: Wait for any current onDraw to finish before clearing
		 * bitmaps
		 */
		try {
			while(inDraw){
				Thread.sleep(MSPERFRAME);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Free up some memory
		wordSet = null;
		
		fl1TileImage = null;
		fl2TileImage = null;
		fl3TileImage = null;
		
		bg = null;
		
		greyButton = null;
		activeButton = null;
		checkedButton = null;

		shearsImage = null;
		sickleImage = null;

		logoImage = null;

		petal1 = null;
		petal2 = null;
		petal3 = null;
		System.gc();
		Log.e("GaD","Paused");
		
	}

	//This is based on the SCRABBLE (TM) letter distribution. Scrabble has 100 tiles,
	// but we only have 98 because we don't have blanks
	char[] chardist = {'e','a','i','o','n','r','t','l','s','u','d','g','b','c','m','p','f','h','v','w','y','k','j','x','q','z'};
	int[] charcount = {12,9,9,8,6,6,6,4,4,4,4,3,2,2,2,2,2,2,2,2,2,1,1,1,1,1};
	int[] charpts = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	int[] charbgs = {0,1,2,0,1,2,0,1,2,0,1,2,0,1,2,0,1,2,0,1,2,0,1,2,0,1};

	public char randLetter(){
		int r = rnd.nextInt(98); //HARD CODED CONSTANT - sum of charcount
		//This is based on the scrabble distribution
		for(int i=0; i<26; i++){ //HARD CODED CONSTANT - length of charcount/chardist
			if(charcount[i] > r){
				return chardist[i];
			}

			r -= charcount[i];
		}

		return 'z';
	}


	public class WEView extends View implements OnTouchListener {
		public int mode; //0 - growth, 1 - extinction, 2 - Title movie
		public boolean newLettersNeeded;
		public char[] p1letters;
		public char[] p2letters;
		int sqSize;

		public int p1selected;
		public int p2selected;
		public int p1selected2;
		public int p2selected2;
		public String p1word;
		public String p2word;
		public boolean p1done;
		public boolean p2done;
		public int p1score;
		public int p2score;
		public int scoreGoal = 250;

		public int p1SmallMallets;
		public int p2SmallMallets;
		public int p1BigMallets;
		public int p2BigMallets;
		public int p1len;
		public int p2len;
		public boolean gameover;
		public Timer timer;
		public boolean openingdone;

		public int whichBG;
		public long lastDrawTime;

		public class DrawEffect {
			int x;
			int y;

			int type;/*
			 * Type 0: Scoring effect
			 * Type 1: Extinction effect
			 * Type 2: Dead flower
			 */
			boolean done;
			int whichPlayer;
			int alpha;
			int amount;
			int whichLetter;

			public DrawEffect(){
				x = getWidth()/2;
				y = getHeight();
				type = 0;
				done = false;
				whichPlayer = 0;
				alpha = 255;
			}

			public void draw(Canvas c, int numSteps){
				Paint p;
				switch(type){
				case 0:
					p = new Paint();
					p.setAntiAlias(true);
					if(whichPlayer == 0){
						p.setColor(p1color);
					} else {
						p.setColor(p2color);
					}
					p.setTextSize(sqSize);
					p.setTextAlign(Align.CENTER);

					int ydir = getHeight()/2 - y;
					int xdir = 0 - x;
					
					for(int i=0;i<numSteps;i++){
						y = y + (ydir)/24;
						x = x + (xdir)/24;
						alpha = alpha - 5;
						if(alpha < 0){
							alpha = 0;
						}
					}
					
					p.setAlpha(alpha);

					if(whichPlayer == 1){
						c.save();
						c.rotate(180,getWidth()/2,getHeight()/2);
					}
					c.drawText("+"+String.valueOf(amount), x, y, p);
					if(whichPlayer == 1){
						c.restore();
					}

					if(alpha <= 0) done = true;
					if(x < -sqSize || x > getWidth() + sqSize) done = true;
					if(y < -sqSize || y > getHeight() + sqSize) done = true;
					/*if(y == getHeight()/2) done = true;
					if(x == getWidth()/2) done = true;
					 */

					break;
					
				case 1:
					
					//First update
					for(int i=0;i<numSteps;i++){
						alpha = alpha - 10;
						if(alpha <= 0) {
							done = true;
							alpha = 0;
						}
					}
					
					//Then draw
					p = new Paint();
					p.setAntiAlias(true);
					
					if(whichPlayer == 0){
						p.setColor(p1color);
					} else {
						p.setColor(p2color);
					}
					p.setAlpha(alpha);
					p.setTextSize(5*sqSize/8);
					p.setTextAlign(Align.CENTER);
					if(whichPlayer == 1){
						c.save();
						c.rotate(180,getWidth()/2,getHeight()/2);
					}
					c.drawText("Opponent's plants uprooted!", 5*sqSize, getHeight()-y, p);
					if(whichPlayer == 1){
						c.restore();
					}
					break;
					
				case 2:
					for(int i=0; i<numSteps; i++){
						y += 7;
						if(y >= getHeight()+sqSize) done = true;
					}
					
					if(whichPlayer == 1){
						c.save();
						c.rotate(180,getWidth()/2,getHeight()/2);
					}
					if(charbgs[whichLetter-'a'] == 0){
						c.drawBitmap(petal1, null, new Rect(x,y-sqSize/2,x+sqSize,y+sqSize/2),bmP);
					} else if(charbgs[whichLetter-'a'] == 1){
						c.drawBitmap(petal2, null, new Rect(x,y-sqSize/2,x+sqSize,y+sqSize/2),bmP);
					} else {
						c.drawBitmap(petal3, null, new Rect(x,y-sqSize/2,x+sqSize,y+sqSize/2),bmP);
					}
					if(whichPlayer == 1){
						c.restore();
					}
					break;

				default:
					//Do nothing but die
					done = true;
				}
			}

			public boolean isDone(){
				return done;
			}

		}

		public ArrayList<DrawEffect> deList;

		public WEView(Context context) {
			super(context);
			mode = 2; //Start in title mode
			openingdone = false;
			newLettersNeeded = true;
			p1letters = new char[8];
			p2letters = new char[8];

			p1selected = -1;
			p2selected = -1;
			p1selected2 = -1;
			p2selected2 = -1;
			p1word = "";
			p2word = "";
			p1done = false;
			p2done = false;
			p1score = 0;
			p2score = 0;
			p1SmallMallets = 0;
			p2SmallMallets = 0;
			p1BigMallets = 0;
			p2BigMallets = 0;
			gameover = false;

			whichBG = 0;
			
			lastDrawTime = -1; //(new Date()).getTime();

			deList = new ArrayList<DrawEffect>();
		}

		//
		public void drawTile(Canvas c, char which, int x, int y, int pts, boolean selected){
			if(which == ' ') return;

			Paint p = new Paint();
			p.setAntiAlias(true);
			p.setFilterBitmap(true);
			p.setAlpha(192);
			if(charbgs[which-'a']==0){
				c.drawBitmap(fl1TileImage,null, new Rect(x,y-sqSize,x+sqSize,y), p);//rawBitmap(leafTileImage,x,)
			} else if(charbgs[which-'a']==1){
				c.drawBitmap(fl2TileImage,null, new Rect(x,y-sqSize,x+sqSize,y), p);
			}else {
				c.drawBitmap(fl3TileImage,null, new Rect(x,y-sqSize,x+sqSize,y), p);
			}
			
			p.setTextSize(3*sqSize/4);
			p.setTextAlign(Align.CENTER);
			if(selected){
				p.setColor(textHighliteColor);
			} else {
				p.setColor(textColor);
			}
			c.drawText(String.valueOf(which),x+sqSize/2,y-sqSize/4,p);

			p.setTextSize(3*sqSize/4);
			p.setStyle(Paint.Style.STROKE);
			p.setStrokeWidth(((float)sqSize)/40.0f);
			p.setColor(textBorderColor);
			c.drawText(String.valueOf(which),x+sqSize/2,y-sqSize/4,p);
			p.setStyle(Paint.Style.FILL);

			p.setTextSize(3*sqSize/16);
			
			ShapeDrawable oDraw = new ShapeDrawable(new OvalShape());
			oDraw.getPaint().setColor(textBorderColor);
			oDraw.setBounds(x+(3*sqSize/4)-sqSize/8,y-5*sqSize/16-sqSize/8,x+(3*sqSize/4)+sqSize/8,y-5*sqSize/16+sqSize/8);
			oDraw.draw(c);
			p.setColor(textColor);
			c.drawText(String.valueOf(pts),x+(3*sqSize/4),y-sqSize/4,p);
			

		}


		protected boolean isvowel(char c){
			if(c == 'a' || c == 'e' || c=='i' || c=='o' || c=='u') return true;

			return false;
		}


		protected void pickNewLetters(){
			int p1vowelcount = 0;
			int p2vowelcount = 0;

			while(p1vowelcount < 1 || p2vowelcount < 1 ||
					p1vowelcount > 4 || p2vowelcount > 4){

				p1vowelcount = p2vowelcount = 0;
				for(int i=0; i<8; i++){
					p1letters[i] = randLetter();
					if(isvowel(p1letters[i])) p1vowelcount++;
					p2letters[i] = randLetter();
					if(isvowel(p2letters[i])) p2vowelcount++;
				}
			}
		}

		protected int scoreWord(String s){
			int score = 0;
			for(int i=0; i<s.length(); i++){
				score += charpts[s.charAt(i) - 'a'];
			}

			return score;
		}

		//Utility function to load background bitmaps, and handle
		// out of memory errors
		protected void loadBG(int number){
			boolean done = false;
			
			while(!done){
				bg = null;
				System.gc();
				try{
					bg = BitmapFactory.decodeResource(res, number); //eventually, change this
					done = true;
				} catch(OutOfMemoryError e){
					mGraphicsLevel -= 1;
					loadBitmaps();
				}
			}
		}

		protected void onDraw(Canvas c){
			inDraw = true;
			
			sqSize = getWidth()/10; //This is a hack. Really should set this in the ctor,
			// but getWidth does not seem to work there, and I'm lazy

			//First, check to see if we need new sets of letters
			if(newLettersNeeded){
				newLettersNeeded = false;
				pickNewLetters();
				p1word = "";
				p2word = "";
			}		

			//Fill background with black
			ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
			mDrawable.getPaint().setColor(0xff77ffff);
			mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL);
			mDrawable.setBounds(0,0,getWidth(),getHeight());
			//mDrawable.draw(c);

			int drawSteps = 0;
			long now = (new Date()).getTime();
			if(lastDrawTime < 0){
				lastDrawTime = (new Date()).getTime();
			}
			while(now - lastDrawTime > MSPERFRAME){
				drawSteps ++;
				lastDrawTime += MSPERFRAME;
			}
			/*if(drawSteps > 0){
				Log.e("GaD","Skipped " + (drawSteps-1) + " steps of animation");
			}*/
				
			whichBG = whichBG + (drawSteps/2);
			
			
			if(mode == 0){
				if(whichBG > 14)
					whichBG = 14;
				//loadBG(gbgs[whichBG]);
				if(bg == null){
					loadBG(gbgs[14]);
				}
				c.drawBitmap(bg, null, new Rect(0,0,getWidth(),getHeight()), bmP);
			} else if(mode == 1){
				if(whichBG > 14)
					whichBG = 14;
				//loadBG(dbgs[whichBG]);
				if(bg == null){
					loadBG(dbgs[14]);
				}
				c.drawBitmap(bg, null, new Rect(0,0,getWidth(),getHeight()), bmP);
			} else {
				if(whichBG > 33)
					whichBG = 33;
				if(bg == null){
					loadBG(splashes[33]);
				}
				//loadBG(splashes[whichBG]);
				c.drawBitmap(bg, null, new Rect(0,0,getWidth(),getHeight()), bmP);
			}

			if(mode == 2){
			
				openingdone = true;
				Paint p = new Paint();
				p.setAntiAlias(true);
				p.setTextAlign(Align.CENTER);
				p.setTextSize(sqSize/2);
				p.setColor(textBorderColor);
				c.drawText("Tap to play", 5*sqSize, getHeight()/2+sqSize/2, p);
			
				this.postInvalidateDelayed(MSPERFRAME/2);
				inDraw = false;
				return;
			}

			Paint p = new Paint();
			p.setTextAlign(Align.CENTER);
			p.setColor(textColor);
			//p.setStrokeWidth(2.0f);
			p.setStyle(android.graphics.Paint.Style.FILL);
			p.setTextSize(sqSize);
			p.setAntiAlias(true);

			//Next, draw the score bars
			//mDrawable.getPaint().setColor(0xffff0000);
			//mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
			float pctToGoal = ((float)p1score) / (float)(1.25f*scoreGoal);
			int barw = (int)(getWidth()*(pctToGoal));
			{
				int i=0;
				while(barw > sqSize/2){
					c.drawBitmap(logoImage,null,new Rect(i*sqSize/2,getHeight()/2,(i+1)*sqSize/2,getHeight()/2+sqSize/2),bmP);
					i++;
					barw -= (sqSize/2);
				}
				c.drawBitmap(logoImage,new Rect(0,0,barw*logoImage.getWidth()/(sqSize/2),logoImage.getHeight()),new Rect(i*sqSize/2,getHeight()/2,barw+i*sqSize/2,getHeight()/2+sqSize/2),bmP);
			}


			//logoDrawable.setBounds(0,getHeight()/2,(int)(getWidth()*(pctToGoal)),getHeight()/2+sqSize/2);
			//logoDrawable.draw(c);
			p.setColor(textBorderColor);
			p.setTextSize(sqSize/2);
			p.setTextAlign(Align.LEFT);
			c.drawText(String.valueOf(p1score), 0, (getHeight()+3*sqSize/4)/2, p);	

			c.save();
			c.rotate(180,getWidth()/2,getHeight()/2);
			//mDrawable.getPaint().setColor(p2color);
			//mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
			pctToGoal = ((float)p2score) / (float)(1.25f*scoreGoal);
			//trim = (int)((/*1.0-*/pctToGoal)*getWidth() / 2);
			//mDrawable.setBounds(0,getHeight()/2,(int)(getWidth()*(pctToGoal)),getHeight()/2+sqSize/2);
			//mDrawable.draw(c);
			barw = (int)(getWidth()*(pctToGoal));
			{
				int i=0;
				while(barw > sqSize/2){
					c.drawBitmap(logoImage,null,new Rect(i*sqSize/2,getHeight()/2,(i+1)*sqSize/2,getHeight()/2+sqSize/2),bmP);
					i++;
					barw -= (sqSize/2);
				}
				c.drawBitmap(logoImage,new Rect(0,0,barw*logoImage.getWidth()/(sqSize/2),logoImage.getHeight()),new Rect(i*sqSize/2,getHeight()/2,barw+i*sqSize/2,getHeight()/2+sqSize/2),bmP);
			}
			p.setTextSize(sqSize/2);

			p.setColor(textBorderColor);
			p.setTextAlign(Align.LEFT);
			c.drawText(String.valueOf(p2score), 0, (getHeight()+3*sqSize/4)/2, p);
			p.setTextAlign(Align.CENTER);
			c.restore();

			mDrawable.getPaint().setColor(textBorderColor);
			mDrawable.getPaint().setAlpha(128);
			mDrawable.setBounds(0,getHeight()/2-1,getWidth(),getHeight()/2);
			mDrawable.draw(c);
			mDrawable.getPaint().setAlpha(255);

			if(p1score >= scoreGoal || p2score >= scoreGoal){
				gameover = true;
				deList.clear();
				if(p1score > p2score){
					c.drawText("Winner!", getWidth()/2, getHeight()-sqSize, p);
					c.save();
					c.rotate(180,getWidth()/2,getHeight()/2);
					c.drawText("Loser!", getWidth()/2, getHeight()-sqSize, p);
					c.restore();
				}else if(p2score > p1score){
					c.drawText("Loser!", getWidth()/2, getHeight()-sqSize, p);
					c.save();
					c.rotate(180,getWidth()/2,getHeight()/2);
					c.drawText("Winner!", getWidth()/2, getHeight()-sqSize, p);
					c.restore();
				}else{
					c.drawText("Tie!", getWidth()/2, getHeight()-sqSize, p);
					c.save();
					c.rotate(180,getWidth()/2,getHeight()/2);
					c.drawText("Tie!", getWidth()/2, getHeight()-sqSize, p);
					c.restore();
				}
				inDraw = false;
				return;
			}


			if(mode == 0 && p1done && p2done){
				mode = 1;
				//If both players are done, total points and reset
				whichBG = 0;
				loadBG(dbgs[14]);
				
				p1done = false;
				p2done = false;

				p1score += scoreWord(p1word);
				DrawEffect p1SE = new DrawEffect();
				p1SE.amount = scoreWord(p1word);
				p1SE.x=getWidth()/2;
				p1SE.y=getHeight();
				p1SE.whichPlayer = 0;
				deList.add(p1SE);

				p2score += scoreWord(p2word);
				DrawEffect p2SE = new DrawEffect();
				p2SE.amount = scoreWord(p2word);
				p2SE.x=getWidth()/2;
				p2SE.y=getHeight();
				p2SE.whichPlayer = 1;
				deList.add(p2SE);

				String t = p1word;
				p1word = p2word;
				p2word = t;
				p1len = p1word.length();
				p2len = p2word.length();

				p1selected = -1;
				p2selected = -1;
				p1selected2 = -1;
				p2selected2 = -1;

				if(p1word.length() >= 3){
					p1BigMallets = 1;
				}
				p1SmallMallets = p1word.length()-2*p1BigMallets;

				if(p2word.length() >= 3){
					p2BigMallets = 1;
				}
				p2SmallMallets = p2word.length()-2*p2BigMallets;


			} else if(mode == 1 && p1done && p2done){
				mode = 0;
				whichBG = 0;
				loadBG(gbgs[14]);
				p1done = false;
				p2done = false;

				//Score leftover letters
				/*if(p1word.length() == 0)
					p1score += 2*(p1len);*/ //p1 gets bonus only if clearing
				p2score += 2*(p1word.length()); //p2 get bonus based on leftovers
				if(p1word.length() > 0){
					DrawEffect bonusE = new DrawEffect();
					bonusE.amount = 2*(p1word.length());
					bonusE.whichPlayer = 1;
					bonusE.x = 5*sqSize;
					bonusE.y = (int)(1.5*sqSize);
					bonusE.alpha = 255;
					bonusE.type = 0;
					deList.add(bonusE);
				}
				/*if(p2word.length() == 0)
					p2score += 2*(p2len); *///p2 gets bonus only if clearing
				p1score += 2*(p2word.length()); //p1 get bonus based on leftovers
				if(p2word.length() > 0){
					DrawEffect bonusE = new DrawEffect();
					bonusE.amount = 2*(p2word.length());
					bonusE.whichPlayer = 0;
					bonusE.x = 5*sqSize;
					bonusE.y = (int)(1.5*sqSize);
					bonusE.alpha = 255;
					bonusE.type = 0;
					deList.add(bonusE);
				}

				p1word = "";
				p2word = "";

				p1selected = -1;
				p2selected = -1;
				p1selected2 = -1;
				p2selected2 = -1;
				pickNewLetters();
			}


			if(mode == 0){
				p.setColor(textColor);
				p.setTextSize(sqSize);

				//Draw the letter supply
				for(int i=0;i<8;i++){
					if(p1letters[i] != ' '){
						drawTile(c,p1letters[i],i*sqSize,getHeight(),charpts[p1letters[i]-'a'],(i == p1selected || i == p1selected2));
					}
					p.setTextSize(sqSize);
				}


				//Draw player 1's word in the word box
				int offset = (8*sqSize - p1word.length()*sqSize)/2;
				p.setColor(textBorderColor);
				p.setAlpha(192);
				p.setTextSize(sqSize/3);
				c.drawText("Tap letter, then tap here to place",4*sqSize,getHeight()-11*sqSize/8,p);
				p.setAlpha(255);
				for(int i=0;i<p1word.length();i++){
					if(p1word.charAt(i) != ' '){
						drawTile(c,p1word.charAt(i),offset+i*sqSize,getHeight()-sqSize,charpts[p1word.charAt(i)-'a'],false);
					}

					p.setTextSize(sqSize);
				}
				//Draw the current word, with empty boxes

				//Boundary for word building area
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.STROKE);
				mDrawable.getPaint().setColor(textBorderColor);
				mDrawable.setBounds(0,getHeight()-2*sqSize,8*sqSize,getHeight()-sqSize);
				//mDrawable.draw(c);

				//End of turn button
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
				if(p1done){
					c.drawBitmap(checkedButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				} else if(p1word.length() == 0 || wordSet.contains(p1word)){
					c.drawBitmap(activeButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				} else {
					c.drawBitmap(greyButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				}      	
				p.setColor(textBorderColor);
				if(!p1done){
					p.setAlpha(192);
				} else {
					p.setAlpha(96);
				}
				p.setTextSize(sqSize/3);
				p.setTextAlign(Align.CENTER);
				c.drawText("Tap here", 9*sqSize, getHeight()-sqSize-sqSize, p);
				c.drawText("when done", 9*sqSize, getHeight()-sqSize-2*sqSize/3, p);
				p.setAlpha(255);

				p.setTextSize(sqSize);

				c.save();
				c.rotate(180,getWidth()/2,getHeight()/2);
				//Draw player 2's letter supply
				for(int i=0;i<8;i++){
					if(p2letters[i] != ' '){
						drawTile(c,p2letters[i],i*sqSize,getHeight(),charpts[p2letters[i]-'a'],(i == p2selected || i == p2selected2));
					}

					p.setTextSize(sqSize);
				}

				//Draw player 2's word box
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.STROKE);
				mDrawable.getPaint().setColor(textBorderColor);
				mDrawable.setBounds(0,getHeight()-2*sqSize,8*sqSize,getHeight()-sqSize);
				//mDrawable.draw(c);
				p.setTextSize(sqSize/3);
				p.setColor(textBorderColor);
				p.setAlpha(192);
				c.drawText("Tap letter, then tap here to place",4*sqSize,getHeight()-11*sqSize/8,p);
				p.setAlpha(255);
				//Draw player 2's word
				offset = (8*sqSize - p2word.length()*sqSize)/2;
				for(int i=0;i<p2word.length();i++){
					if(p2word.charAt(i) != ' '){
						drawTile(c,p2word.charAt(i),offset+i*sqSize,getHeight()-sqSize,charpts[p2word.charAt(i)-'a'],false);
					}

					p.setTextSize(sqSize);
				}

				//End of turn button
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
				if(p2done){
					c.drawBitmap(checkedButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				} else if(p2word.length() == 0 || wordSet.contains(p2word)){
					c.drawBitmap(activeButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				} else {
					c.drawBitmap(greyButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				}    	
				p.setColor(textBorderColor);
				if(!p2done){
					p.setAlpha(192);
				} else {
					p.setAlpha(96);
				}
				p.setTextSize(sqSize/3);
				p.setTextAlign(Align.CENTER);
				c.drawText("Tap here", 9*sqSize, getHeight()-sqSize-sqSize, p);
				c.drawText("when done", 9*sqSize, getHeight()-sqSize-2*sqSize/3, p);
				p.setAlpha(255);
				c.restore();
			} else if(mode == 1){
				p.setColor(textColor);
				p.setTextSize(sqSize);

				if((p1BigMallets <= 0 && p1SmallMallets <= 0) || p1word.length() <= 0){
					p1done = true;
				}
				if((p2BigMallets <= 0 && p2SmallMallets <= 0) || p2word.length() <= 0){
					p2done = true;
				}

				//Draw player 1's word in the word box
				int offset = (8*sqSize - p1word.length()*sqSize)/2;
				for(int i=0;i<p1word.length();i++){
					drawTile(c,p1word.charAt(i),offset+i*sqSize,getHeight()-sqSize,2,(p1selected == i || p1selected2 == i));

					p.setTextSize(sqSize);
				}
				//Boundary for word building area
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.STROKE);
				mDrawable.getPaint().setColor(textBorderColor);
				mDrawable.setBounds(0,getHeight()-2*sqSize,8*sqSize,getHeight()-sqSize);

				if(p1SmallMallets > 0){

					for(int i=0; i<p1SmallMallets; i++){
						c.drawBitmap(shearsImage, null, new Rect(i*sqSize,getHeight()-sqSize,(i+1)*sqSize,getHeight()), bmP);
					}

					p.setColor(textBorderColor);
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/3);
					p.setAlpha(192);
					c.drawText("Select a letter, then tap here to activate shears", 4*sqSize, getHeight()-sqSize/2, p);
					p.setAlpha(255);
				} 


				if(p1BigMallets > 0){
					c.drawBitmap(sickleImage, null, new Rect(8*sqSize,getHeight()-2*sqSize,10*sqSize,getHeight()),bmP);
					p.setColor(textBorderColor);
					p.setAlpha(192);
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/3);
					c.drawText("Select two" , 9*sqSize, getHeight()-4*sqSize/3, p);
					c.drawText("neighboring",9*sqSize, getHeight()-3*sqSize/3, p);
					c.drawText("letters, then",9*sqSize, getHeight()-2*sqSize/3, p);
					c.drawText("tap here",9*sqSize, getHeight()-sqSize/3, p);
					p.setAlpha(255);
				}

				c.save();
				c.rotate(180,getWidth()/2,getHeight()/2);
				//Draw player 2's word box
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.STROKE);
				mDrawable.getPaint().setColor(textBorderColor);
				mDrawable.setBounds(0,getHeight()-2*sqSize,8*sqSize,getHeight()-sqSize);
				//mDrawable.draw(c);

				//Draw player 2's word
				offset = (8*sqSize - p2word.length()*sqSize)/2;
				for(int i=0;i<p2word.length();i++){
					drawTile(c,p2word.charAt(i),offset+i*sqSize,getHeight()-sqSize,2,(p2selected == i || p2selected2 == i));

					p.setTextSize(sqSize);
				}
				//Draw player 2's mallet

				if(p2SmallMallets > 0){
					for(int i=0; i<p2SmallMallets; i++){
						c.drawBitmap(shearsImage, null, new Rect(i*sqSize,getHeight()-sqSize,(i+1)*sqSize,getHeight()), bmP);
					}

					p.setColor(textBorderColor);
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/3);
					p.setAlpha(192);
					c.drawText("Select a letter, then tap here to activate shears", 4*sqSize, getHeight()-sqSize/2, p);
					p.setAlpha(255);
				}

				if(p2BigMallets > 0){
					c.drawBitmap(sickleImage, null, new Rect(8*sqSize,getHeight()-2*sqSize,10*sqSize,getHeight()),bmP);
					p.setColor(textBorderColor);
					p.setAlpha(192);
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/3);
					c.drawText("Select two" , 9*sqSize, getHeight()-4*sqSize/3, p);
					c.drawText("neighboring",9*sqSize, getHeight()-3*sqSize/3, p);
					c.drawText("letters, then",9*sqSize, getHeight()-2*sqSize/3, p);
					c.drawText("tap here",9*sqSize, getHeight()-sqSize/3, p);
					p.setAlpha(255);
				}

				c.restore();

			}



			//Do effect
			Iterator<DrawEffect> it = deList.iterator();
			DrawEffect ef;
			while(it.hasNext()){
				ef=it.next();
				if(ef.isDone()){
					it.remove();
				} else {
					ef.draw(c,drawSteps);
				}
			}
		
			this.postInvalidateDelayed(MSPERFRAME/2);
			
			inDraw = false;
		}

		public boolean onTouch(View arg0, MotionEvent arg1) {	
			if(arg1.getAction() != MotionEvent.ACTION_DOWN) return false;
			this.invalidate();

			float x = arg1.getX();
			float y = arg1.getY();

			if(mode == 2){
				openingdone = false;
				mode = 0;
				whichBG = 0;
				loadBG(gbgs[14]);
				return true;
			}

			if(gameover){
				gameover = false;
				mode = 2;
				whichBG = 0;
				loadBG(splashes[33]);
				p1done = false;
				p2done = false;

				p1score = 0;
				p2score = 0;	

				p1word = "";
				p2word = "";

				p1selected = -1;
				p2selected = -1;
				p1selected2 = -1;
				p2selected2 = -1;
				pickNewLetters();
				return true;
			}

			if(mode == 0){
				if(x < sqSize*8 && y > getHeight() - sqSize){
					//player 1 has touched one of her letters
					p1done = false;

					if(p1selected == (int)(x/sqSize) || p1letters[(int)(x/sqSize) ] == ' '){
						p1selected = -1;
					} else {
						p1selected = (int)(x/sqSize);
					}

				} else if (getWidth()-x < sqSize*8 && y < sqSize) {
					p2done = false;
					//player 2 has touched one of her letters
					x = getWidth()-x;

					if(p2selected == (int)(x/sqSize) || p2letters[(int)(x/sqSize) ] == ' '){
						p2selected = -1;
					} else {
						p2selected = (int)(x/sqSize);
					}

				} else if (x < sqSize*8 && y > getHeight()/2){
					p1done = false;
					//player 1 has touched her word row
					if(p1selected == -1){
						//player is trying to remove a character
						int offset = (8*sqSize - p1word.length()*sqSize)/2;
						int whichLetter = (int)((x-offset)/sqSize);
						if(whichLetter >= 0 && whichLetter < p1word.length()){
							int blankspot = 0;
							while(p1letters[blankspot] != ' '){
								blankspot++;
							}
							p1letters[blankspot] = p1word.charAt(whichLetter);
							p1word = p1word.substring(0, whichLetter) + p1word.substring(whichLetter+1);
						}
					} else {
						//player is trying to add a character
						int offset = (8*sqSize - p1word.length()*sqSize)/2;
						int whereInWord = (int)((x-offset + (sqSize/2))/sqSize);
						if(whereInWord > p1word.length()){
							whereInWord = p1word.length();
						}
						if(whereInWord < 0){
							whereInWord = 0;
						}
						//make new word
						p1word = p1word.substring(0, whereInWord) + p1letters[p1selected] + p1word.substring(whereInWord);

						//clear out selected letter
						p1letters[p1selected] = ' ';
						p1selected = -1;

					}
				} else if (getWidth()-x < sqSize*8 && y < getHeight()/2){
					p2done = false;
					//player 2 has touched her word row
					x = getWidth() - x;
					if(p2selected == -1){
						//player is trying to remove a character
						int offset = (8*sqSize - p2word.length()*sqSize)/2;
						int whichLetter = (int)((x-offset)/sqSize);
						if(whichLetter >= 0 && whichLetter < p2word.length()){
							int blankspot = 0;
							while(p2letters[blankspot] != ' '){
								blankspot++;
							}
							p2letters[blankspot] = p2word.charAt(whichLetter);
							p2word = p2word.substring(0, whichLetter) + p2word.substring(whichLetter+1);
						}
					} else {
						//player is trying to add a character
						int offset = (8*sqSize - p2word.length()*sqSize)/2;
						int whereInWord = (int)((x-offset + (sqSize/2))/sqSize);
						if(whereInWord > p2word.length()){
							whereInWord = p2word.length();
						}
						if(whereInWord < 0){
							whereInWord = 0;
						}
						//make new word
						p2word = p2word.substring(0, whereInWord) + p2letters[p2selected] + p2word.substring(whereInWord);

						//clear out selected letter
						p2letters[p2selected] = ' ';
						p2selected = -1;

					}
				} else if(x > 8*sqSize &&  y > getHeight() - 2*sqSize){
					//player 1 indicated they are done
					p1selected = -1;
					if(p1word.length() == 0 || wordSet.contains(p1word)){
						p1done = !p1done;
					}
				}  else if(getWidth()-x > 8*sqSize &&  y < 2*sqSize){
					//player 2 indicated they are done
					p2selected = -1;
					if(p2word.length() == 0 || wordSet.contains(p2word)){
						p2done = !p2done;
					}
				}
			} else if(mode == 1){
				if (x < sqSize*8 && y > getHeight() - 2*sqSize && y < getHeight()-sqSize){
					//player 1 has touched her word row
					if(p1selected == -1){
						//player is trying to select 1 character
						int offset = (8*sqSize - p1word.length()*sqSize)/2;
						int whichLetter = (int)((x-offset)/sqSize);
						if(whichLetter >= p1word.length()) whichLetter = p1word.length()-1;
						if(whichLetter < 0) whichLetter=0;

						p1selected = whichLetter;
					} else {
						//player is either switching selections, or picking a second letter
						int offset = (8*sqSize - p1word.length()*sqSize)/2;
						int whichLetter = (int)((x-offset)/sqSize);
						if(whichLetter >= p1word.length()) whichLetter = p1word.length()-1;
						if(whichLetter < 0) whichLetter=0;

						if(p1selected == whichLetter){
							p1selected = p1selected2;
							p1selected2 = -1;
						} else if (p1selected2 == whichLetter){
							p1selected2 = -1;
						}else if(p1selected != -1 && p1selected2 == -1 && (p1selected-1 == whichLetter || p1selected+1 == whichLetter)){
							p1selected2 = whichLetter;
						}
					}
				} else if (getWidth() - x < sqSize*8 && y < 2*sqSize && y > sqSize){
					//player 2 has touched her word row
					if(p2selected == -1){
						x = getWidth() - x;
						//player is trying to select 1 character
						int offset = (8*sqSize - p2word.length()*sqSize)/2;
						int whichLetter = (int)((x-offset)/sqSize);
						if(whichLetter >= p2word.length()) whichLetter = p2word.length()-1;
						if(whichLetter < 0) whichLetter=0;

						p2selected = whichLetter;
					} else {
						//player is either switching selections, or picking a second letter
						x = getWidth() - x;
						int offset = (8*sqSize - p2word.length()*sqSize)/2;
						int whichLetter = (int)((x-offset)/sqSize);
						if(whichLetter >= p2word.length()) whichLetter = p2word.length()-1;
						if(whichLetter < 0) whichLetter=0;

						if(p2selected == whichLetter){
							p2selected = p2selected2;
							p2selected2 = -1;
						} else if (p2selected2 == whichLetter){
							p2selected2 = -1;
						}else if(p2selected != -1 && p2selected2 == -1 && (p2selected-1 == whichLetter || p2selected+1 == whichLetter)){
							p2selected2 = whichLetter;
						}
					}
				} else if(x < sqSize*8 && y > getHeight()-sqSize){
					//player 1 is activating small mallet
					if(p1selected != -1 && p1selected2 == -1 && (p1SmallMallets > 0 || p1BigMallets>0)){
						//Only one letter selected
						p1SmallMallets -= 1;
						if(p1SmallMallets < 0) {
							p1BigMallets -= 1;
							p1SmallMallets = 0;
						}
						String t = p1word.substring(0,p1selected) + p1word.substring(p1selected+1);
						if(t.length() == 0 || wordSet.contains(t)){
							DrawEffect Se = new DrawEffect();
							Se.amount = 2;
							Se.type = 0;
							Se.whichPlayer = 0;
							float letOff = p1selected - p1word.length()/2.0f;
							Se.x = (int) letOff*sqSize + 4*sqSize;
							Se.y = (int) getHeight()-3*sqSize/2;
							deList.add(Se);
							
							Se = new DrawEffect();
							Se.type = 2;
							Se.whichPlayer = 0;
							Se.whichLetter = p1word.charAt(p1selected);
							Se.x = (int) letOff*sqSize + 4*sqSize;
							Se.y = (int) getHeight()-3*sqSize/2;
							deList.add(Se);
							
							p1score += 2;
							p1word = t;
						}
						if(t.length() == 0){
							DrawEffect ExtinctE = new DrawEffect();
							ExtinctE.whichPlayer = 0;
							ExtinctE.x = 4*sqSize;
							ExtinctE.y = sqSize;
							ExtinctE.type = 1;
							deList.add(ExtinctE);
							
							
							p1score += 2*(p1len);
								
							ExtinctE = new DrawEffect();
							ExtinctE.whichPlayer = 0;
							ExtinctE.x = 4*sqSize;
							ExtinctE.y = getHeight()-sqSize;
							ExtinctE.type = 0;
							ExtinctE.amount = 2*p1len;
							deList.add(ExtinctE);
						}
						p1selected = p1selected2 = -1;

					} 
				} else if(x > sqSize*8 && y > getHeight() - 2*sqSize){
					//player 1 is activating big mallet
					if(p1selected != -1 && p1selected2 != -1 && p1BigMallets > 0){
						int small;
						int big;
						if(p1selected < p1selected2){
							small = p1selected;
							big = p1selected2;
						} else {
							small = p1selected2;
							big = p1selected;
						}
						p1BigMallets -= 1;
						String t = p1word.substring(0,small) + p1word.substring(big+1);
						if(t.length() == 0 || wordSet.contains(t)){
							DrawEffect Se = new DrawEffect();
							Se.amount = 4;
							Se.type = 0;
							Se.whichPlayer = 0;
							float letOff = p1selected - p1word.length()/2.0f;
							Se.x = (int) letOff*sqSize + 4*sqSize;
							Se.y = (int) getHeight()-3*sqSize/2;
							deList.add(Se);
							
							Se = new DrawEffect();
							Se.type = 2;
							Se.whichPlayer = 0;
							Se.whichLetter = p1word.charAt(p1selected);
							Se.x = (int) letOff*sqSize + 4*sqSize;
							Se.y = (int) getHeight()-3*sqSize/2;
							deList.add(Se);
							
							p1score += 4;
							p1word = t;
						}
						if(t.length() == 0){
							DrawEffect ExtinctE = new DrawEffect();
							ExtinctE.whichPlayer = 0;
							ExtinctE.x = 4*sqSize;
							ExtinctE.y = sqSize;
							ExtinctE.type = 1;
							deList.add(ExtinctE);
							
							p1score += 2*(p1len);
							
							ExtinctE = new DrawEffect();
							ExtinctE.whichPlayer = 0;
							ExtinctE.x = 4*sqSize;
							ExtinctE.y = getHeight()-sqSize;
							ExtinctE.type = 0;
							ExtinctE.amount = 2*p1len;
							deList.add(ExtinctE);
						}
						p1selected = p1selected2 = -1;

					}
				} else if(getWidth() - x < sqSize*8 && y < sqSize){
					// player 2 is activating small mallet
					if(p2selected != -1 && p2selected2 == -1 && (p2SmallMallets > 0 || p2BigMallets > 0)){
						//Only one letter selected
						p2SmallMallets -= 1;
						if(p2SmallMallets < 0){
							p2BigMallets -= 1;
							p2SmallMallets = 0;
						}
						String t = p2word.substring(0,p2selected) + p2word.substring(p2selected+1);
						if(t.length() == 0 || wordSet.contains(t)){
							DrawEffect Se = new DrawEffect();
							Se.amount = 2;
							Se.type = 0;
							Se.whichPlayer = 1;
							float letOff = p2selected - p2word.length()/2.0f;
							Se.x = (int) letOff*sqSize + 4*sqSize;
							Se.y = (int) getHeight()-3*sqSize/2;
							deList.add(Se);
							
							Se = new DrawEffect();
							Se.type = 2;
							Se.whichPlayer = 1;
							Se.whichLetter = p2word.charAt(p2selected);
							Se.x = (int) letOff*sqSize + 4*sqSize;
							Se.y = (int) getHeight()-3*sqSize/2;
							deList.add(Se);
							
							p2score += 2;
							p2word = t;
						}
						if(t.length() == 0){
							DrawEffect ExtinctE = new DrawEffect();
							ExtinctE.whichPlayer = 1;
							ExtinctE.x = 4*sqSize;
							ExtinctE.y = sqSize;
							ExtinctE.type = 1;
							deList.add(ExtinctE);
							
							p2score += 2*(p2len);
							
							ExtinctE = new DrawEffect();
							ExtinctE.whichPlayer = 1;
							ExtinctE.x = 4*sqSize;
							ExtinctE.y = getHeight()-sqSize;
							ExtinctE.type = 0;
							ExtinctE.amount = 2*p2len;
							deList.add(ExtinctE);
						}
						p2selected = p2selected2 = -1;

					}
				}else if(getWidth()-x > sqSize*8 && y < 2*sqSize){
					// player 2 is activating big mallet
					if(p2selected != -1 && p2selected2 != -1 && p2BigMallets > 0){
						int small;
						int big;
						if(p2selected < p2selected2){
							small = p2selected;
							big = p2selected2;
						} else {
							small = p2selected2;
							big = p2selected;
						}

						p2BigMallets -= 1;
						String t = p2word.substring(0,small) + p2word.substring(big+1);
						if(t.length() == 0 || wordSet.contains(t)){
							DrawEffect Se = new DrawEffect();
							Se.amount = 4;
							Se.type = 0;
							Se.whichPlayer = 1;
							float letOff = p2selected - p2word.length()/2.0f;
							Se.x = (int) letOff*sqSize + 4*sqSize;
							Se.y = (int) getHeight()-3*sqSize/2;
							deList.add(Se);
							
							Se = new DrawEffect();
							Se.type = 2;
							Se.whichPlayer = 1;
							Se.whichLetter = p2word.charAt(p2selected);
							Se.x = (int) letOff*sqSize + 4*sqSize;
							Se.y = (int) getHeight()-3*sqSize/2;
							deList.add(Se);
							
							p2score += 4;
							p2word = t;
						}
						p2selected = p2selected2 = -1;
						if(t.length() == 0){
							DrawEffect ExtinctE = new DrawEffect();
							ExtinctE.whichPlayer = 1;
							ExtinctE.x = 4*sqSize;
							ExtinctE.y = sqSize;
							ExtinctE.type = 1;
							deList.add(ExtinctE);
							
							p2score += 2*(p2len);
							
							ExtinctE = new DrawEffect();
							ExtinctE.whichPlayer = 1;
							ExtinctE.x = 4*sqSize;
							ExtinctE.y = getHeight()-sqSize;
							ExtinctE.type = 0;
							ExtinctE.amount = 2*p2len;
							deList.add(ExtinctE);
						}
					}
				} 
			}






			return true;

		}
	}





}