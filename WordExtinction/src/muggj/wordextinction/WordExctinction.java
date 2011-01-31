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
 */

package muggj.wordextinction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Paint.Align;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
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
	Bitmap[] bgs;
	Bitmap greyButton;
	Bitmap activeButton;
	Bitmap checkedButton;
	Bitmap shearsImage;
	Bitmap sickleImage;
	Bitmap logoImage;
	BitmapDrawable logoDrawable;
	Bitmap petal1;
	Bitmap petal2;
	Bitmap petal3;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rnd = new Random();
		
		mView = new WEView(this);
		setContentView(mView);
		mView.setOnTouchListener(mView);


		wordSet = new HashSet<String>();
		Resources res = getResources();
		InputStream wordIS = res.openRawResource(R.raw.wordlistoneperline);
		BufferedReader in = new BufferedReader(new InputStreamReader(wordIS));

		String aLine;
		try {
			while((aLine = in.readLine()) != null){
				wordSet.add(aLine);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		fl1TileImage = BitmapFactory.decodeResource(res, R.drawable.flower1);
		fl2TileImage = BitmapFactory.decodeResource(res, R.drawable.flower2);
		fl3TileImage = BitmapFactory.decodeResource(res, R.drawable.flower3);
		//shearTile = BitmapFactory.decodeResource(res, R.drawable.gardensheers);
		bgs = new Bitmap[127];

		System.gc();
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inSampleSize = 1;
		//bgs[0] = BitmapFactory.decodeResource(res, R.drawable.bg0001);
		//bgs[1] = BitmapFactory.decodeResource(res, R.drawable.bg0002);
		/*bgs[2] = BitmapFactory.decodeResource(res, R.drawable.bg0003);
		//bgs[3] = BitmapFactory.decodeResource(res, R.drawable.bg0004);
		bgs[4] = BitmapFactory.decodeResource(res, R.drawable.bg0005);
		//bgs[5] = BitmapFactory.decodeResource(res, R.drawable.bg0006);
		bgs[6] = BitmapFactory.decodeResource(res, R.drawable.bg0007);
		//bgs[7] = BitmapFactory.decodeResource(res, R.drawable.bg0008);
		bgs[8] = BitmapFactory.decodeResource(res, R.drawable.bg0009);
		//bgs[9] = BitmapFactory.decodeResource(res, R.drawable.bg0010);
		bgs[10] = BitmapFactory.decodeResource(res, R.drawable.bg0011);
		//bgs[11] = BitmapFactory.decodeResource(res, R.drawable.bg0012);
		bgs[12] = BitmapFactory.decodeResource(res, R.drawable.bg0013);
		//bgs[13] = BitmapFactory.decodeResource(res, R.drawable.bg0014);
		bgs[14] = BitmapFactory.decodeResource(res, R.drawable.bg0015);
		//bgs[15] = BitmapFactory.decodeResource(res, R.drawable.bg0016);
		bgs[16] = BitmapFactory.decodeResource(res, R.drawable.bg0017);
		//bgs[17] = BitmapFactory.decodeResource(res, R.drawable.bg0018);
		bgs[18] = BitmapFactory.decodeResource(res, R.drawable.bg0019);
		//bgs[19] = BitmapFactory.decodeResource(res, R.drawable.bg0020);
		bgs[20] = BitmapFactory.decodeResource(res, R.drawable.bg0021);
		//bgs[21] = BitmapFactory.decodeResource(res, R.drawable.bg0022);
		bgs[22] = BitmapFactory.decodeResource(res, R.drawable.bg0023);
		//bgs[23] = BitmapFactory.decodeResource(res, R.drawable.bg0024);
		bgs[24] = BitmapFactory.decodeResource(res, R.drawable.bg0025);
		//bgs[25] = BitmapFactory.decodeResource(res, R.drawable.bg0026);
		bgs[26] = BitmapFactory.decodeResource(res, R.drawable.bg0027);
		//bgs[27] = BitmapFactory.decodeResource(res, R.drawable.bg0028);
		bgs[28] = BitmapFactory.decodeResource(res, R.drawable.bg0029);*/
		bgs[29] = BitmapFactory.decodeResource(res, R.drawable.bg0030,options);
		/*bgs[30] = BitmapFactory.decodeResource(res, R.drawable.bg0031);
		//bgs[31] = BitmapFactory.decodeResource(res, R.drawable.bg0032);
		bgs[32] = BitmapFactory.decodeResource(res, R.drawable.bg0033);
		//bgs[33] = BitmapFactory.decodeResource(res, R.drawable.bg0034);
		bgs[34] = BitmapFactory.decodeResource(res, R.drawable.bg0035);
		//bgs[35] = BitmapFactory.decodeResource(res, R.drawable.bg0036);
		bgs[36] = BitmapFactory.decodeResource(res, R.drawable.bg0037);
		//bgs[37] = BitmapFactory.decodeResource(res, R.drawable.bg0038);
		bgs[38] = BitmapFactory.decodeResource(res, R.drawable.bg0039);
		//bgs[39] = BitmapFactory.decodeResource(res, R.drawable.bg0040);
		bgs[40] = BitmapFactory.decodeResource(res, R.drawable.bg0041);
		//bgs[41] = BitmapFactory.decodeResource(res, R.drawable.bg0042);
		bgs[42] = BitmapFactory.decodeResource(res, R.drawable.bg0043);
		//bgs[43] = BitmapFactory.decodeResource(res, R.drawable.bg0044);
		bgs[44] = BitmapFactory.decodeResource(res, R.drawable.bg0045);
		//bgs[45] = BitmapFactory.decodeResource(res, R.drawable.bg0046);
		bgs[46] = BitmapFactory.decodeResource(res, R.drawable.bg0047);
		//bgs[47] = BitmapFactory.decodeResource(res, R.drawable.bg0048);
		bgs[48] = BitmapFactory.decodeResource(res, R.drawable.bg0049);
		//bgs[49] = BitmapFactory.decodeResource(res, R.drawable.bg0050);
		bgs[50] = BitmapFactory.decodeResource(res, R.drawable.bg0051);
		//bgs[51] = BitmapFactory.decodeResource(res, R.drawable.bg0052);
		bgs[52] = BitmapFactory.decodeResource(res, R.drawable.bg0053);
		//bgs[53] = BitmapFactory.decodeResource(res, R.drawable.bg0054);
		bgs[54] = BitmapFactory.decodeResource(res, R.drawable.bg0056);
		//bgs[55] = BitmapFactory.decodeResource(res, R.drawable.bg0057);
		bgs[56] = BitmapFactory.decodeResource(res, R.drawable.bg0058);
		//bgs[57] = BitmapFactory.decodeResource(res, R.drawable.bg0059);*/
		bgs[58] = BitmapFactory.decodeResource(res, R.drawable.bg0060,options);
		//bgs[59] = BitmapFactory.decodeResource(res, R.drawable.splash0001);
		/*bgs[60] = BitmapFactory.decodeResource(res, R.drawable.splash0002);
		//bgs[61] = BitmapFactory.decodeResource(res, R.drawable.splash0003);
		bgs[62] = BitmapFactory.decodeResource(res, R.drawable.splash0004);
		//bgs[63] = BitmapFactory.decodeResource(res, R.drawable.splash0005);
		bgs[64] = BitmapFactory.decodeResource(res, R.drawable.splash0006);
		//bgs[65] = BitmapFactory.decodeResource(res, R.drawable.splash0007);
		bgs[66] = BitmapFactory.decodeResource(res, R.drawable.splash0008);
		//bgs[67] = BitmapFactory.decodeResource(res, R.drawable.splash0009);
		bgs[68] = BitmapFactory.decodeResource(res, R.drawable.splash0010);
		//bgs[69] = BitmapFactory.decodeResource(res, R.drawable.splash0011);
		bgs[70] = BitmapFactory.decodeResource(res, R.drawable.splash0012);
		//bgs[71] = BitmapFactory.decodeResource(res, R.drawable.splash0013);
		bgs[72] = BitmapFactory.decodeResource(res, R.drawable.splash0014);
		//bgs[73] = BitmapFactory.decodeResource(res, R.drawable.splash0015);
		bgs[74] = BitmapFactory.decodeResource(res, R.drawable.splash0016);
		//bgs[75] = BitmapFactory.decodeResource(res, R.drawable.splash0017);
		bgs[76] = BitmapFactory.decodeResource(res, R.drawable.splash0018);
		//bgs[77] = BitmapFactory.decodeResource(res, R.drawable.splash0019);
		bgs[78] = BitmapFactory.decodeResource(res, R.drawable.splash0020);
		//bgs[79] = BitmapFactory.decodeResource(res, R.drawable.splash0021);
		bgs[80] = BitmapFactory.decodeResource(res, R.drawable.splash0022);
		//bgs[81] = BitmapFactory.decodeResource(res, R.drawable.splash0023);
		bgs[82] = BitmapFactory.decodeResource(res, R.drawable.splash0024);
		//bgs[83] = BitmapFactory.decodeResource(res, R.drawable.splash0025);
		bgs[84] = BitmapFactory.decodeResource(res, R.drawable.splash0026);
		//bgs[85] = BitmapFactory.decodeResource(res, R.drawable.splash0027);
		bgs[86] = BitmapFactory.decodeResource(res, R.drawable.splash0028);
		//bgs[87] = BitmapFactory.decodeResource(res, R.drawable.splash0029);
		bgs[88] = BitmapFactory.decodeResource(res, R.drawable.splash0030);
		//bgs[89] = BitmapFactory.decodeResource(res, R.drawable.splash0031);
		bgs[90] = BitmapFactory.decodeResource(res, R.drawable.splash0032);
		//bgs[91] = BitmapFactory.decodeResource(res, R.drawable.splash0033);
		bgs[92] = BitmapFactory.decodeResource(res, R.drawable.splash0034);
		//bgs[93] = BitmapFactory.decodeResource(res, R.drawable.splash0035);
		bgs[94] = BitmapFactory.decodeResource(res, R.drawable.splash0036);
		//bgs[95] = BitmapFactory.decodeResource(res, R.drawable.splash0037);
		bgs[96] = BitmapFactory.decodeResource(res, R.drawable.splash0038);
		//bgs[97] = BitmapFactory.decodeResource(res, R.drawable.splash0039);
		bgs[98] = BitmapFactory.decodeResource(res, R.drawable.splash0040);
		//bgs[99] = BitmapFactory.decodeResource(res, R.drawable.splash0041);
		bgs[100] = BitmapFactory.decodeResource(res, R.drawable.splash0042);
		//bgs[101] = BitmapFactory.decodeResource(res, R.drawable.splash0043);
		bgs[102] = BitmapFactory.decodeResource(res, R.drawable.splash0044);
		//bgs[103] = BitmapFactory.decodeResource(res, R.drawable.splash0045);
		bgs[104] = BitmapFactory.decodeResource(res, R.drawable.splash0046);
		//bgs[105] = BitmapFactory.decodeResource(res, R.drawable.splash0047);
		bgs[106] = BitmapFactory.decodeResource(res, R.drawable.splash0048);
		//bgs[107] = BitmapFactory.decodeResource(res, R.drawable.splash0049);
		bgs[108] = BitmapFactory.decodeResource(res, R.drawable.splash0050);
		//bgs[109] = BitmapFactory.decodeResource(res, R.drawable.splash0051);
		bgs[110] = BitmapFactory.decodeResource(res, R.drawable.splash0052);
		//bgs[111] = BitmapFactory.decodeResource(res, R.drawable.splash0053);
		bgs[112] = BitmapFactory.decodeResource(res, R.drawable.splash0054);
		//bgs[113] = BitmapFactory.decodeResource(res, R.drawable.splash0055);
		bgs[114] = BitmapFactory.decodeResource(res, R.drawable.splash0056);
		//bgs[115] = BitmapFactory.decodeResource(res, R.drawable.splash0057);
		bgs[116] = BitmapFactory.decodeResource(res, R.drawable.splash0058);
		//bgs[117] = BitmapFactory.decodeResource(res, R.drawable.splash0059);
		bgs[118] = BitmapFactory.decodeResource(res, R.drawable.splash0060);
		//bgs[119] = BitmapFactory.decodeResource(res, R.drawable.splash0061);
		bgs[120] = BitmapFactory.decodeResource(res, R.drawable.splash0062);
		//bgs[121] = BitmapFactory.decodeResource(res, R.drawable.splash0063);
		bgs[122] = BitmapFactory.decodeResource(res, R.drawable.splash0064);
		//bgs[123] = BitmapFactory.decodeResource(res, R.drawable.splash0065);
		bgs[124] = BitmapFactory.decodeResource(res, R.drawable.splash0066);
		//bgs[125] = BitmapFactory.decodeResource(res, R.drawable.splash0067);*/
		bgs[126] = BitmapFactory.decodeResource(res, R.drawable.splash0068,options);
		
		System.gc();
		greyButton = BitmapFactory.decodeResource(res, R.drawable.grbut);
		activeButton = BitmapFactory.decodeResource(res, R.drawable.actbut);
		checkedButton = BitmapFactory.decodeResource(res, R.drawable.checkbutton);

		shearsImage = BitmapFactory.decodeResource(res, R.drawable.gardensheers);
		sickleImage = BitmapFactory.decodeResource(res, R.drawable.sickle);

		logoImage = BitmapFactory.decodeResource(res, R.drawable.icon);

		petal1 = BitmapFactory.decodeResource(res, R.drawable.petal1);
		petal2 = BitmapFactory.decodeResource(res, R.drawable.petal2);
		petal3 = BitmapFactory.decodeResource(res, R.drawable.petal3);
		
		mActivity = this;
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
		public int scoreGoal = 250; //TODO 250

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

			public void draw(Canvas c){
				Paint p;
				switch(type){
				case 0:
					p = new Paint();
					p.setAntiAlias(true);
					if(whichPlayer == 0){
						p.setColor(0xffff0000);
					} else {
						p.setColor(0xff0000ff);
					}
					p.setTextSize(sqSize);
					p.setTextAlign(Align.CENTER);

					int ydir = getHeight()/2 - y;
					int xdir = 0 - x;
					y = y + (ydir)/24;
					x = x + (xdir)/24;

					alpha = alpha - 5;
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
					alpha = alpha - 10;
					if(alpha <= 9) done = true;
					p = new Paint();
					p.setAntiAlias(true);
					
					if(whichPlayer == 0){
						p.setColor(0xffff0000);
					} else {
						p.setColor(0xff0000ff);
					}
					p.setAlpha(alpha);
					p.setTextSize(3*sqSize/4);
					p.setTextAlign(Align.CENTER);
					if(whichPlayer == 1){
						c.save();
						c.rotate(180,getWidth()/2,getHeight()/2);
					}
					c.drawText("Opponent's plants extinct!", 5*sqSize, getHeight()-y, p);
					if(whichPlayer == 1){
						c.restore();
					}
					break;
					
				case 2:
					y += 7;
					if(y >= getHeight()+sqSize) done = true;
					
					if(whichPlayer == 1){
						c.save();
						c.rotate(180,getWidth()/2,getHeight()/2);
					}
					if(charbgs[whichLetter-'a'] == 0){
						c.drawBitmap(petal1, null, new Rect(x,y-sqSize/2,x+sqSize,y+sqSize/2),null);
					} else if(charbgs[whichLetter-'a'] == 1){
						c.drawBitmap(petal2, null, new Rect(x,y-sqSize/2,x+sqSize,y+sqSize/2),null);
					} else {
						c.drawBitmap(petal3, null, new Rect(x,y-sqSize/2,x+sqSize,y+sqSize/2),null);
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

			whichBG = 60;






			deList = new ArrayList<DrawEffect>();


		}

		public void drawTile(Canvas c, char which, int x, int y){
			if(which == ' ') return;

			Paint p = new Paint();
			p.setAlpha(192);
			if(charbgs[which-'a']==0){
				c.drawBitmap(fl1TileImage,null, new Rect(x,y-sqSize,x+sqSize,y), p);//rawBitmap(leafTileImage,x,)
			} else if(charbgs[which-'a']==1){
				c.drawBitmap(fl2TileImage,null, new Rect(x,y-sqSize,x+sqSize,y), p);
			}else {
				c.drawBitmap(fl3TileImage,null, new Rect(x,y-sqSize,x+sqSize,y), p);
			}

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


		protected void onDraw(Canvas c){

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

			whichBG = 0;
			if(mode == 0){
				c.drawBitmap(bgs[29], null, new Rect(0,getHeight()/2,getWidth(),getHeight()), null);
				c.save();
				c.rotate(180,getWidth()/2,getHeight()/2);
				c.drawBitmap(bgs[29], null, new Rect(0,getHeight()/2,getWidth(),getHeight()), null);
				c.restore();
			} else if(mode == 1){
				c.drawBitmap(bgs[58], null, new Rect(0,getHeight()/2,getWidth(),getHeight()), null);
				c.save();
				c.rotate(180,getWidth()/2,getHeight()/2);
				c.drawBitmap(bgs[58], null, new Rect(0,getHeight()/2,getWidth(),getHeight()), null);
				c.restore();
			} else {
				c.drawBitmap(bgs[126], null, new Rect(0,0,getWidth(),getHeight()), null);
			}

			whichBG+=2;
			if(mode == 0){
				if(whichBG > 28)
					whichBG = 28;
			}else if(mode == 1){
				if(whichBG > 58)
					whichBG = 58;
			}else if(mode == 2){
				//if(whichBG > 58+66){
					//whichBG = 58+66;
					openingdone = true;
					Paint p = new Paint();
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/2);
					p.setColor(0xffffffff);
					c.drawText("Tap to play", 5*sqSize, getHeight()/2+sqSize/2, p);
				//}
				
				//TODO make this only fire 24 times per second!
				try {
					Thread.currentThread().sleep(1000/24);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.invalidate();
				return;
			}

			Paint p = new Paint();
			p.setTextAlign(Align.CENTER);
			p.setColor(0xff000000);
			p.setStrokeWidth(2.0f);
			p.setStyle(android.graphics.Paint.Style.FILL);
			p.setTextSize(sqSize);
			p.setAntiAlias(true);

			//Next, draw the score bars
			mDrawable.getPaint().setColor(0xffff0000);
			mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
			float pctToGoal = ((float)p1score) / (float)(1.25f*scoreGoal);
			int barw = (int)(getWidth()*(pctToGoal));
			{
				int i=0;
				while(barw > sqSize/2){
					c.drawBitmap(logoImage,null,new Rect(i*sqSize/2,getHeight()/2,(i+1)*sqSize/2,getHeight()/2+sqSize/2),null);
					i++;
					barw -= (sqSize/2);
				}
				c.drawBitmap(logoImage,new Rect(0,0,barw*logoImage.getWidth()/(sqSize/2),logoImage.getHeight()),new Rect(i*sqSize/2,getHeight()/2,barw+i*sqSize/2,getHeight()/2+sqSize/2),null);
			}


			//logoDrawable.setBounds(0,getHeight()/2,(int)(getWidth()*(pctToGoal)),getHeight()/2+sqSize/2);
			//logoDrawable.draw(c);
			p.setColor(0xffffffff);
			p.setTextSize(sqSize/2);
			p.setTextAlign(Align.LEFT);
			c.drawText(String.valueOf(p1score), 0, (getHeight()+3*sqSize/4)/2, p);	

			c.save();
			c.rotate(180,getWidth()/2,getHeight()/2);
			mDrawable.getPaint().setColor(0xff0000ff);
			mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
			pctToGoal = ((float)p2score) / (float)(1.25f*scoreGoal);
			//trim = (int)((/*1.0-*/pctToGoal)*getWidth() / 2);
			//mDrawable.setBounds(0,getHeight()/2,(int)(getWidth()*(pctToGoal)),getHeight()/2+sqSize/2);
			//mDrawable.draw(c);
			barw = (int)(getWidth()*(pctToGoal));
			{
				int i=0;
				while(barw > sqSize/2){
					c.drawBitmap(logoImage,null,new Rect(i*sqSize/2,getHeight()/2,(i+1)*sqSize/2,getHeight()/2+sqSize/2),null);
					i++;
					barw -= (sqSize/2);
				}
				c.drawBitmap(logoImage,new Rect(0,0,barw*logoImage.getWidth()/(sqSize/2),logoImage.getHeight()),new Rect(i*sqSize/2,getHeight()/2,barw+i*sqSize/2,getHeight()/2+sqSize/2),null);
			}
			p.setTextSize(sqSize/2);

			p.setColor(0xffffffff);
			p.setTextAlign(Align.LEFT);
			c.drawText(String.valueOf(p2score), 0, (getHeight()+3*sqSize/4)/2, p);
			p.setTextAlign(Align.CENTER);
			c.restore();

			mDrawable.getPaint().setColor(0xffffffff);
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
				return;
			}


			if(mode == 0 && p1done && p2done){
				mode = 1;
				//If both players are done, total points and reset
				whichBG = 30;

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
				p1done = false;
				p2done = false;

				//Score leftover letters
				if(p1word.length() == 0)
					p1score += 2*(p1len); //p1 gets bonus only if clearing
				p2score += 2*(p1word.length()); //p2 get bonus based on leftovers
				if(p2word.length() == 0)
					p2score += 2*(p2len); //p2 gets bonus only if clearing
				p1score += 2*(p2word.length()); //p1 get bonus based on leftovers


				p1word = "";
				p2word = "";

				p1selected = -1;
				p2selected = -1;
				p1selected2 = -1;
				p2selected2 = -1;
				pickNewLetters();
			}


			if(mode == 0){
				p.setColor(0xff000000);
				p.setTextSize(sqSize);





				//Draw the letter supply
				for(int i=0;i<8;i++){
					if(i == p1selected || i == p1selected2){
						p.setColor(0xffffff00);
					} else {
						p.setColor(0xff000000);
					}

					drawTile(c,p1letters[i],i*sqSize,getHeight());
					p.setTextSize(3*sqSize/4);
					p.setTextAlign(Align.CENTER);
					c.drawText(String.valueOf(p1letters[i]),i*sqSize+sqSize/2,getHeight()-sqSize/4,p);

					p.setTextSize(3*sqSize/4);
					p.setStyle(Paint.Style.STROKE);
					p.setColor(0xffffffff);
					c.drawText(String.valueOf(p1letters[i]),i*sqSize+sqSize/2,getHeight()-sqSize/4,p);
					p.setStyle(Paint.Style.FILL);

					p.setTextSize(sqSize/4);
					if(p1letters[i] != ' '){
						ShapeDrawable oDraw = new ShapeDrawable(new OvalShape());
						oDraw.getPaint().setColor(0xffffffff);
						oDraw.setBounds(i*sqSize+(3*sqSize/4)-sqSize/8,getHeight()-5*sqSize/16-sqSize/8,i*sqSize+(3*sqSize/4)+sqSize/8,getHeight()-5*sqSize/16+sqSize/8);
						oDraw.draw(c);
						p.setColor(0xff000000);
						c.drawText(String.valueOf(charpts[p1letters[i]-'a']),i*sqSize+(3*sqSize/4),getHeight()-sqSize/4,p);
					}



					p.setTextSize(sqSize);
				}


				//Draw player 1's word in the word box
				int offset = (8*sqSize - p1word.length()*sqSize)/2;
				p.setColor(0xffffffff);
				p.setAlpha(192);
				p.setTextSize(sqSize/3);
				c.drawText("Tap letter, then tap here to place",4*sqSize,getHeight()-11*sqSize/8,p);
				p.setAlpha(255);
				for(int i=0;i<p1word.length();i++){
					drawTile(c,p1word.charAt(i),offset+i*sqSize,getHeight()-sqSize);
					p.setColor(0xff000000);
					p.setTextSize(3*sqSize/4);
					c.drawText(String.valueOf(p1word.charAt(i)),offset+i*sqSize+sqSize/2,getHeight()-5*sqSize/4,p);

					p.setTextSize(3*sqSize/4);
					p.setStyle(Paint.Style.STROKE);
					p.setColor(0xffffffff);
					c.drawText(String.valueOf(p1word.charAt(i)),offset+i*sqSize+sqSize/2,getHeight()-5*sqSize/4,p);
					p.setStyle(Paint.Style.FILL);


					ShapeDrawable oDraw = new ShapeDrawable(new OvalShape());
					oDraw.getPaint().setColor(0xffffffff);
					oDraw.setBounds(offset+i*sqSize+(5*sqSize/8),getHeight()-23*sqSize/16,offset+i*sqSize+(7*sqSize/8),getHeight()-19*sqSize/16);
					oDraw.draw(c);
					p.setColor(0xff000000);
					p.setTextSize(sqSize/4);
					c.drawText(String.valueOf(charpts[p1word.charAt(i)-'a']),offset+i*sqSize+(3*sqSize/4),getHeight()-5*sqSize/4,p);

					p.setTextSize(sqSize);
				}
				//Draw the current word, with empty boxes

				//Boundary for word building area
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.STROKE);
				mDrawable.getPaint().setColor(0xffffffff);
				mDrawable.setBounds(0,getHeight()-2*sqSize,8*sqSize,getHeight()-sqSize);
				//mDrawable.draw(c);

				//End of turn button
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
				if(p1done){
					c.drawBitmap(checkedButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),null);
				} else if(p1word.length() == 0 || wordSet.contains(p1word)){
					c.drawBitmap(activeButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),null);
				} else {
					c.drawBitmap(greyButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),null);
				}      	
				p.setColor(0xffffffff);
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
					drawTile(c,p2letters[i],i*sqSize,getHeight());
					if(i == p2selected || i == p2selected2){
						p.setColor(0xffffff00);
					} else {
						p.setColor(0xff000000);
					}
					p.setTextSize(3*sqSize/4);
					c.drawText(String.valueOf(p2letters[i]),i*sqSize+sqSize/2,getHeight()-sqSize/4,p);


					p.setTextSize(3*sqSize/4);
					p.setStyle(Paint.Style.STROKE);
					p.setColor(0xffffffff);
					c.drawText(String.valueOf(p2letters[i]),i*sqSize+sqSize/2,getHeight()-sqSize/4,p);
					p.setStyle(Paint.Style.FILL);

					p.setTextSize(sqSize/4);
					if(p2letters[i] != ' '){
						ShapeDrawable oDraw = new ShapeDrawable(new OvalShape());
						oDraw.getPaint().setColor(0xffffffff);
						oDraw.setBounds(i*sqSize+(3*sqSize/4)-sqSize/8,getHeight()-5*sqSize/16-sqSize/8,i*sqSize+(3*sqSize/4)+sqSize/8,getHeight()-5*sqSize/16+sqSize/8);
						oDraw.draw(c);
						p.setColor(0xff000000);
						c.drawText(String.valueOf(charpts[p2letters[i]-'a']),i*sqSize+(3*sqSize/4),getHeight()-sqSize/4,p);
					}

					p.setTextSize(sqSize);
				}

				//Draw player 2's word box
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.STROKE);
				mDrawable.getPaint().setColor(0xffffffff);
				mDrawable.setBounds(0,getHeight()-2*sqSize,8*sqSize,getHeight()-sqSize);
				//mDrawable.draw(c);
				p.setTextSize(sqSize/3);
				p.setColor(0xffffffff);
				p.setAlpha(192);
				c.drawText("Tap letter, then tap here to place",4*sqSize,getHeight()-11*sqSize/8,p);
				p.setAlpha(255);
				//Draw player 2's word
				offset = (8*sqSize - p2word.length()*sqSize)/2;
				for(int i=0;i<p2word.length();i++){
					drawTile(c,p2word.charAt(i),offset+i*sqSize,getHeight()-sqSize);
					p.setColor(0xff000000);
					p.setTextSize(3*sqSize/4);
					c.drawText(String.valueOf(p2word.charAt(i)),offset+i*sqSize+sqSize/2,getHeight()-5*sqSize/4,p);

					p.setTextSize(3*sqSize/4);
					p.setStyle(Paint.Style.STROKE);
					p.setColor(0xffffffff);
					c.drawText(String.valueOf(p2word.charAt(i)),offset+i*sqSize+sqSize/2,getHeight()-5*sqSize/4,p);
					p.setStyle(Paint.Style.FILL);

					ShapeDrawable oDraw = new ShapeDrawable(new OvalShape());
					oDraw.getPaint().setColor(0xffffffff);
					oDraw.setBounds(offset+i*sqSize+(5*sqSize/8),getHeight()-23*sqSize/16,offset+i*sqSize+(7*sqSize/8),getHeight()-19*sqSize/16);
					oDraw.draw(c);

					p.setColor(0xff000000);
					p.setTextSize(sqSize/4);
					c.drawText(String.valueOf(charpts[p2word.charAt(i)-'a']),offset+i*sqSize+(3*sqSize/4),getHeight()-5*sqSize/4,p);

					p.setTextSize(sqSize);
				}

				//End of turn button
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
				if(p2done){
					c.drawBitmap(checkedButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),null);
				} else if(p2word.length() == 0 || wordSet.contains(p2word)){
					c.drawBitmap(activeButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),null);
				} else {
					c.drawBitmap(greyButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),null);
				}    	
				p.setColor(0xffffffff);
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
				p.setColor(0xff000000);
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
					if(p1selected == i || p1selected2 == i){
						p.setColor(0xffffff00);
					}else {	
						p.setColor(0xff000000);
					}
					p.setTextSize(3*sqSize/4);
					drawTile(c,p1word.charAt(i),offset+i*sqSize,getHeight()-sqSize);
					c.drawText(String.valueOf(p1word.charAt(i)),offset+i*sqSize+sqSize/2,getHeight()-5*sqSize/4,p);
					p.setTextSize(sqSize/4);
					c.drawText(String.valueOf(2),offset+i*sqSize+(3*sqSize/4),getHeight()-5*sqSize/4,p);

					p.setTextSize(3*sqSize/4);
					p.setStyle(Paint.Style.STROKE);
					p.setColor(0xffffffff);
					c.drawText(String.valueOf(p1word.charAt(i)),offset+i*sqSize+sqSize/2,getHeight()-5*sqSize/4,p);
					p.setStyle(Paint.Style.FILL);

					p.setTextSize(sqSize);
				}
				//Boundary for word building area
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.STROKE);
				mDrawable.getPaint().setColor(0xffffffff);
				mDrawable.setBounds(0,getHeight()-2*sqSize,8*sqSize,getHeight()-sqSize);

				if(p1SmallMallets > 0){

					for(int i=0; i<p1SmallMallets; i++){
						c.drawBitmap(shearsImage, null, new Rect(i*sqSize,getHeight()-sqSize,(i+1)*sqSize,getHeight()), null);
					}

					p.setColor(0xffffffff);
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/3);
					p.setAlpha(192);
					c.drawText("Select a letter, then tap here to activate shears", 4*sqSize, getHeight()-sqSize/2, p);
					p.setAlpha(255);
				} 


				if(p1BigMallets > 0){
					c.drawBitmap(sickleImage, null, new Rect(8*sqSize,getHeight()-2*sqSize,10*sqSize,getHeight()),null);
					p.setColor(0xffffffff);
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
				mDrawable.getPaint().setColor(0xffffffff);
				mDrawable.setBounds(0,getHeight()-2*sqSize,8*sqSize,getHeight()-sqSize);
				//mDrawable.draw(c);

				//Draw player 2's word
				offset = (8*sqSize - p2word.length()*sqSize)/2;
				for(int i=0;i<p2word.length();i++){
					if(p2selected == i || p2selected2 == i){
						p.setColor(0xffffff00);
					}else {	
						p.setColor(0xff000000);
					}
					p.setTextSize(3*sqSize/4);
					drawTile(c,p2word.charAt(i),offset+i*sqSize,getHeight()-sqSize);
					c.drawText(String.valueOf(p2word.charAt(i)),offset+i*sqSize+sqSize/2,getHeight()-5*sqSize/4,p);

					p.setTextSize(sqSize/4);
					c.drawText(String.valueOf(2),offset+i*sqSize+(3*sqSize/4),getHeight()-5*sqSize/4,p);


					p.setTextSize(3*sqSize/4);
					p.setStyle(Paint.Style.STROKE);
					p.setColor(0xffffffff);
					c.drawText(String.valueOf(p2word.charAt(i)),offset+i*sqSize+sqSize/2,getHeight()-5*sqSize/4,p);
					p.setStyle(Paint.Style.FILL);

					p.setTextSize(sqSize);
				}
				//Draw player 2's mallet

				if(p2SmallMallets > 0){
					for(int i=0; i<p2SmallMallets; i++){
						c.drawBitmap(shearsImage, null, new Rect(i*sqSize,getHeight()-sqSize,(i+1)*sqSize,getHeight()), null);
					}

					p.setColor(0xffffffff);
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/3);
					p.setAlpha(192);
					c.drawText("Select a letter, then tap here to activate shears", 4*sqSize, getHeight()-sqSize/2, p);
					p.setAlpha(255);
				}

				if(p2BigMallets > 0){
					c.drawBitmap(sickleImage, null, new Rect(8*sqSize,getHeight()-2*sqSize,10*sqSize,getHeight()),null);
					p.setColor(0xffffffff);
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
					ef.draw(c);
				}
			}



			//TODO make this only fire 24 times per second!
			try {
				Thread.currentThread().sleep(1000/24);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.invalidate();
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
				return true;
			}

			if(gameover){
				gameover = false;
				mode = 2;
				whichBG = 60;
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

				} else if (x < sqSize*8 && y > getHeight() - 2*sqSize){
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
				} else if (getWidth()-x < sqSize*8 && y < 2*sqSize){
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
						}
					}
				} 
			}






			return true;

		}
	}





}