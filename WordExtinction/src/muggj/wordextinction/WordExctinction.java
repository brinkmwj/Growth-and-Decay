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
 * See below for the license terms for this code.
 * 
 * 
 * 2011-01-29, 10:03pm, EST - Bo
 * 
 * 
 * TODO LIST
 * 		DONE 1) Add menu, "new game" option
 * 		DONE 2) Expand bounds where user can tap to place letters
 * 3) Add sound effects
 * 		DONE 4) Change effects colors
 * 		DONE 5) Redesign interface for decay phase
 * 		NO - This makes no sense. I was sleepy when I wrote it.
 * 			6) High scores screen?
 * 		DONE 7) Add bitmap filter to all drawBitmap calls
 * 		DONE 8) Create a unified drawTile method
 * 		NO - #5 will fix this 9) Detect when no possible moves left in decay phase
 * 		PARTIAL - It is not possible to use multi-touch dragging until
 * 			API level 5 (Android 2.0), so I have elected not to 
 * 			implement it 10) Allow dragging using touch up/down
 * 		DONE 11) Show bonus points for extinct words
 * 		DONE 12) Effects for left-over letters
 * 13) Is it possible to animate the background and still get good performance?
 */

/*
 * This game, including 1) code 2) art and 3) ancillary files, is licensed under the Attribution-Noncommercial-Share Alike 3.0 version of the Creative Commons License. For reference the license is given below and can also be found at http://creativecommons.org/licenses/by-nc-sa/3.0/
 * 
License

THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR COPYRIGHT LAW IS PROHIBITED.

BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.

1. Definitions

"Adaptation" means a work based upon the Work, or upon the Work and other pre-existing works, such as a translation, adaptation, derivative work, arrangement of music or other alterations of a literary or artistic work, or phonogram or performance and includes cinematographic adaptations or any other form in which the Work may be recast, transformed, or adapted including in any form recognizably derived from the original, except that a work that constitutes a Collection will not be considered an Adaptation for the purpose of this License. For the avoidance of doubt, where the Work is a musical work, performance or phonogram, the synchronization of the Work in timed-relation with a moving image ("synching") will be considered an Adaptation for the purpose of this License.
"Collection" means a collection of literary or artistic works, such as encyclopedias and anthologies, or performances, phonograms or broadcasts, or other works or subject matter other than works listed in Section 1(g) below, which, by reason of the selection and arrangement of their contents, constitute intellectual creations, in which the Work is included in its entirety in unmodified form along with one or more other contributions, each constituting separate and independent works in themselves, which together are assembled into a collective whole. A work that constitutes a Collection will not be considered an Adaptation (as defined above) for the purposes of this License.
"Distribute" means to make available to the public the original and copies of the Work or Adaptation, as appropriate, through sale or other transfer of ownership.
"License Elements" means the following high-level license attributes as selected by Licensor and indicated in the title of this License: Attribution, Noncommercial, ShareAlike.
"Licensor" means the individual, individuals, entity or entities that offer(s) the Work under the terms of this License.
"Original Author" means, in the case of a literary or artistic work, the individual, individuals, entity or entities who created the Work or if no individual or entity can be identified, the publisher; and in addition (i) in the case of a performance the actors, singers, musicians, dancers, and other persons who act, sing, deliver, declaim, play in, interpret or otherwise perform literary or artistic works or expressions of folklore; (ii) in the case of a phonogram the producer being the person or legal entity who first fixes the sounds of a performance or other sounds; and, (iii) in the case of broadcasts, the organization that transmits the broadcast.
"Work" means the literary and/or artistic work offered under the terms of this License including without limitation any production in the literary, scientific and artistic domain, whatever may be the mode or form of its expression including digital form, such as a book, pamphlet and other writing; a lecture, address, sermon or other work of the same nature; a dramatic or dramatico-musical work; a choreographic work or entertainment in dumb show; a musical composition with or without words; a cinematographic work to which are assimilated works expressed by a process analogous to cinematography; a work of drawing, painting, architecture, sculpture, engraving or lithography; a photographic work to which are assimilated works expressed by a process analogous to photography; a work of applied art; an illustration, map, plan, sketch or three-dimensional work relative to geography, topography, architecture or science; a performance; a broadcast; a phonogram; a compilation of data to the extent it is protected as a copyrightable work; or a work performed by a variety or circus performer to the extent it is not otherwise considered a literary or artistic work.
"You" means an individual or entity exercising rights under this License who has not previously violated the terms of this License with respect to the Work, or who has received express permission from the Licensor to exercise rights under this License despite a previous violation.
"Publicly Perform" means to perform public recitations of the Work and to communicate to the public those public recitations, by any means or process, including by wire or wireless means or public digital performances; to make available to the public Works in such a way that members of the public may access these Works from a place and at a place individually chosen by them; to perform the Work to the public by any means or process and the communication to the public of the performances of the Work, including by public digital performance; to broadcast and rebroadcast the Work by any means including signs, sounds or images.
"Reproduce" means to make copies of the Work by any means including without limitation by sound or visual recordings and the right of fixation and reproducing fixations of the Work, including storage of a protected performance or phonogram in digital form or other electronic medium.
2. Fair Dealing Rights. Nothing in this License is intended to reduce, limit, or restrict any uses free from copyright or rights arising from limitations or exceptions that are provided for in connection with the copyright protection under copyright law or other applicable laws.

3. License Grant. Subject to the terms and conditions of this License, Licensor hereby grants You a worldwide, royalty-free, non-exclusive, perpetual (for the duration of the applicable copyright) license to exercise the rights in the Work as stated below:

to Reproduce the Work, to incorporate the Work into one or more Collections, and to Reproduce the Work as incorporated in the Collections;
to create and Reproduce Adaptations provided that any such Adaptation, including any translation in any medium, takes reasonable steps to clearly label, demarcate or otherwise identify that changes were made to the original Work. For example, a translation could be marked "The original work was translated from English to Spanish," or a modification could indicate "The original work has been modified.";
to Distribute and Publicly Perform the Work including as incorporated in Collections; and,
to Distribute and Publicly Perform Adaptations.
The above rights may be exercised in all media and formats whether now known or hereafter devised. The above rights include the right to make such modifications as are technically necessary to exercise the rights in other media and formats. Subject to Section 8(f), all rights not expressly granted by Licensor are hereby reserved, including but not limited to the rights described in Section 4(e).

4. Restrictions. The license granted in Section 3 above is expressly made subject to and limited by the following restrictions:

You may Distribute or Publicly Perform the Work only under the terms of this License. You must include a copy of, or the Uniform Resource Identifier (URI) for, this License with every copy of the Work You Distribute or Publicly Perform. You may not offer or impose any terms on the Work that restrict the terms of this License or the ability of the recipient of the Work to exercise the rights granted to that recipient under the terms of the License. You may not sublicense the Work. You must keep intact all notices that refer to this License and to the disclaimer of warranties with every copy of the Work You Distribute or Publicly Perform. When You Distribute or Publicly Perform the Work, You may not impose any effective technological measures on the Work that restrict the ability of a recipient of the Work from You to exercise the rights granted to that recipient under the terms of the License. This Section 4(a) applies to the Work as incorporated in a Collection, but this does not require the Collection apart from the Work itself to be made subject to the terms of this License. If You create a Collection, upon notice from any Licensor You must, to the extent practicable, remove from the Collection any credit as required by Section 4(d), as requested. If You create an Adaptation, upon notice from any Licensor You must, to the extent practicable, remove from the Adaptation any credit as required by Section 4(d), as requested.
You may Distribute or Publicly Perform an Adaptation only under: (i) the terms of this License; (ii) a later version of this License with the same License Elements as this License; (iii) a Creative Commons jurisdiction license (either this or a later license version) that contains the same License Elements as this License (e.g., Attribution-NonCommercial-ShareAlike 3.0 US) ("Applicable License"). You must include a copy of, or the URI, for Applicable License with every copy of each Adaptation You Distribute or Publicly Perform. You may not offer or impose any terms on the Adaptation that restrict the terms of the Applicable License or the ability of the recipient of the Adaptation to exercise the rights granted to that recipient under the terms of the Applicable License. You must keep intact all notices that refer to the Applicable License and to the disclaimer of warranties with every copy of the Work as included in the Adaptation You Distribute or Publicly Perform. When You Distribute or Publicly Perform the Adaptation, You may not impose any effective technological measures on the Adaptation that restrict the ability of a recipient of the Adaptation from You to exercise the rights granted to that recipient under the terms of the Applicable License. This Section 4(b) applies to the Adaptation as incorporated in a Collection, but this does not require the Collection apart from the Adaptation itself to be made subject to the terms of the Applicable License.
You may not exercise any of the rights granted to You in Section 3 above in any manner that is primarily intended for or directed toward commercial advantage or private monetary compensation. The exchange of the Work for other copyrighted works by means of digital file-sharing or otherwise shall not be considered to be intended for or directed toward commercial advantage or private monetary compensation, provided there is no payment of any monetary compensation in con-nection with the exchange of copyrighted works.
If You Distribute, or Publicly Perform the Work or any Adaptations or Collections, You must, unless a request has been made pursuant to Section 4(a), keep intact all copyright notices for the Work and provide, reasonable to the medium or means You are utilizing: (i) the name of the Original Author (or pseudonym, if applicable) if supplied, and/or if the Original Author and/or Licensor designate another party or parties (e.g., a sponsor institute, publishing entity, journal) for attribution ("Attribution Parties") in Licensor's copyright notice, terms of service or by other reasonable means, the name of such party or parties; (ii) the title of the Work if supplied; (iii) to the extent reasonably practicable, the URI, if any, that Licensor specifies to be associated with the Work, unless such URI does not refer to the copyright notice or licensing information for the Work; and, (iv) consistent with Section 3(b), in the case of an Adaptation, a credit identifying the use of the Work in the Adaptation (e.g., "French translation of the Work by Original Author," or "Screenplay based on original Work by Original Author"). The credit required by this Section 4(d) may be implemented in any reasonable manner; provided, however, that in the case of a Adaptation or Collection, at a minimum such credit will appear, if a credit for all contributing authors of the Adaptation or Collection appears, then as part of these credits and in a manner at least as prominent as the credits for the other contributing authors. For the avoidance of doubt, You may only use the credit required by this Section for the purpose of attribution in the manner set out above and, by exercising Your rights under this License, You may not implicitly or explicitly assert or imply any connection with, sponsorship or endorsement by the Original Author, Licensor and/or Attribution Parties, as appropriate, of You or Your use of the Work, without the separate, express prior written permission of the Original Author, Licensor and/or Attribution Parties.
For the avoidance of doubt:

Non-waivable Compulsory License Schemes. In those jurisdictions in which the right to collect royalties through any statutory or compulsory licensing scheme cannot be waived, the Licensor reserves the exclusive right to collect such royalties for any exercise by You of the rights granted under this License;
Waivable Compulsory License Schemes. In those jurisdictions in which the right to collect royalties through any statutory or compulsory licensing scheme can be waived, the Licensor reserves the exclusive right to collect such royalties for any exercise by You of the rights granted under this License if Your exercise of such rights is for a purpose or use which is otherwise than noncommercial as permitted under Section 4(c) and otherwise waives the right to collect royalties through any statutory or compulsory licensing scheme; and,
Voluntary License Schemes. The Licensor reserves the right to collect royalties, whether individually or, in the event that the Licensor is a member of a collecting society that administers voluntary licensing schemes, via that society, from any exercise by You of the rights granted under this License that is for a purpose or use which is otherwise than noncommercial as permitted under Section 4(c).
Except as otherwise agreed in writing by the Licensor or as may be otherwise permitted by applicable law, if You Reproduce, Distribute or Publicly Perform the Work either by itself or as part of any Adaptations or Collections, You must not distort, mutilate, modify or take other derogatory action in relation to the Work which would be prejudicial to the Original Author's honor or reputation. Licensor agrees that in those jurisdictions (e.g. Japan), in which any exercise of the right granted in Section 3(b) of this License (the right to make Adaptations) would be deemed to be a distortion, mutilation, modification or other derogatory action prejudicial to the Original Author's honor and reputation, the Licensor will waive or not assert, as appropriate, this Section, to the fullest extent permitted by the applicable national law, to enable You to reasonably exercise Your right under Section 3(b) of this License (right to make Adaptations) but not otherwise.
5. Representations, Warranties and Disclaimer

UNLESS OTHERWISE MUTUALLY AGREED TO BY THE PARTIES IN WRITING AND TO THE FULLEST EXTENT PERMITTED BY APPLICABLE LAW, LICENSOR OFFERS THE WORK AS-IS AND MAKES NO REPRESENTATIONS OR WARRANTIES OF ANY KIND CONCERNING THE WORK, EXPRESS, IMPLIED, STATUTORY OR OTHERWISE, INCLUDING, WITHOUT LIMITATION, WARRANTIES OF TITLE, MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NONINFRINGEMENT, OR THE ABSENCE OF LATENT OR OTHER DEFECTS, ACCURACY, OR THE PRESENCE OF ABSENCE OF ERRORS, WHETHER OR NOT DISCOVERABLE. SOME JURISDICTIONS DO NOT ALLOW THE EXCLUSION OF IMPLIED WARRANTIES, SO THIS EXCLUSION MAY NOT APPLY TO YOU.

6. Limitation on Liability. EXCEPT TO THE EXTENT REQUIRED BY APPLICABLE LAW, IN NO EVENT WILL LICENSOR BE LIABLE TO YOU ON ANY LEGAL THEORY FOR ANY SPECIAL, INCIDENTAL, CONSEQUENTIAL, PUNITIVE OR EXEMPLARY DAMAGES ARISING OUT OF THIS LICENSE OR THE USE OF THE WORK, EVEN IF LICENSOR HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.

7. Termination

This License and the rights granted hereunder will terminate automatically upon any breach by You of the terms of this License. Individuals or entities who have received Adaptations or Collections from You under this License, however, will not have their licenses terminated provided such individuals or entities remain in full compliance with those licenses. Sections 1, 2, 5, 6, 7, and 8 will survive any termination of this License.
Subject to the above terms and conditions, the license granted here is perpetual (for the duration of the applicable copyright in the Work). Notwithstanding the above, Licensor reserves the right to release the Work under different license terms or to stop distributing the Work at any time; provided, however that any such election will not serve to withdraw this License (or any other license that has been, or is required to be, granted under the terms of this License), and this License will continue in full force and effect unless terminated as stated above.
8. Miscellaneous

Each time You Distribute or Publicly Perform the Work or a Collection, the Licensor offers to the recipient a license to the Work on the same terms and conditions as the license granted to You under this License.
Each time You Distribute or Publicly Perform an Adaptation, Licensor offers to the recipient a license to the original Work on the same terms and conditions as the license granted to You under this License.
If any provision of this License is invalid or unenforceable under applicable law, it shall not affect the validity or enforceability of the remainder of the terms of this License, and without further action by the parties to this agreement, such provision shall be reformed to the minimum extent necessary to make such provision valid and enforceable.
No term or provision of this License shall be deemed waived and no breach consented to unless such waiver or consent shall be in writing and signed by the party to be charged with such waiver or consent.
This License constitutes the entire agreement between the parties with respect to the Work licensed here. There are no understandings, agreements or representations with respect to the Work not specified here. Licensor shall not be bound by any additional provisions that may appear in any communication from You. This License may not be modified without the mutual written agreement of the Licensor and You.
The rights granted under, and the subject matter referenced, in this License were drafted utilizing the terminology of the Berne Convention for the Protection of Literary and Artistic Works (as amended on September 28, 1979), the Rome Convention of 1961, the WIPO Copyright Treaty of 1996, the WIPO Performances and Phonograms Treaty of 1996 and the Universal Copyright Convention (as revised on July 24, 1971). These rights and subject matter take effect in the relevant jurisdiction in which the License terms are sought to be enforced according to the corresponding provisions of the implementation of those treaty provisions in the applicable national law. If the standard suite of rights granted under applicable copyright law includes additional rights not granted under this License, such additional rights are deemed to be included in the License; this License is not intended to restrict the license of any rights under applicable law.
Creative Commons Notice

Creative Commons is not a party to this License, and makes no warranty whatsoever in connection with the Work. Creative Commons will not be liable to You or any party on any legal theory for any damages whatsoever, including without limitation any general, special, incidental or consequential damages arising in connection to this license. Notwithstanding the foregoing two (2) sentences, if Creative Commons has expressly identified itself as the Licensor hereunder, it shall have all rights and obligations of Licensor.

Except for the limited purpose of indicating to the public that the Work is licensed under the CCPL, Creative Commons does not authorize the use by either party of the trademark "Creative Commons" or any related trademark or logo of Creative Commons without the prior written consent of Creative Commons. Any permitted use will be in compliance with Creative Commons' then-current trademark usage guidelines, as may be published on its website or otherwise made available upon request from time to time. For the avoidance of doubt, this trademark restriction does not form part of this License.

Creative Commons may be contacted at http://creativecommons.org/.
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

import muggj.wordextinction.WordExctinction.WEView.DrawEffect;


import android.app.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Align;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class WordExctinction extends Activity  {
	//CONSTANTS
	static int MSPERFRAME = 1000/24;
	static int p1color = 0xfff17720;
	static int p2color = 0xfff420e8;
	static int textColor = 0xff000000;
	static int textBorderColor = 0xffffffff;
	static int textHighliteColor = 0xffffff00;
	
	//DATA LOADED IN onCreate
	public HashSet<String> wordSet;
	Activity mActivity;
	WEView mView;
	Random rnd;
	
	//DATA LOADED IN onStart, FREED in onStop
	Bitmap fl1TileImage;
	Bitmap fl2TileImage;
	Bitmap fl3TileImage;
	Bitmap leaf_bronze;
	Bitmap leaf_silver;
	Bitmap leaf_gold;
	Bitmap leaf_ruby;
	Bitmap leaf_diamond;
	Bitmap mirror_leaf;
	Bitmap cat_leaf;

	Bitmap gbg;
	Bitmap dbg;
	Bitmap splash;

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


	//TODO: When/where should these be initialized
	boolean inDraw;
	//2 is high, 1 is medium, 0 is low
	int mGraphicsLevel;
	Paint bmP;


	//this is only needed for taking screenshots
	/*public void onBackPressed() {

	   return;
	}*/

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}



	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.new_game:
			mView.newGame();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void loadBitmaps(){
		Log.d("GaD","loadBitmaps called at level: "+mGraphicsLevel);
		if(mGraphicsLevel == 3){
			//First, try loading hi res images
			try{
				fl1TileImage = BitmapFactory.decodeResource(res, R.drawable.flower1_h);
				fl2TileImage = BitmapFactory.decodeResource(res, R.drawable.flower2_h);
				fl3TileImage = BitmapFactory.decodeResource(res, R.drawable.flower3_h);
				
				leaf_bronze = BitmapFactory.decodeResource(res, R.drawable.leaf_bronze_h);
				leaf_silver = BitmapFactory.decodeResource(res, R.drawable.leaf_silver_h);
				leaf_gold = BitmapFactory.decodeResource(res, R.drawable.leaf_gold_h);
				leaf_ruby = BitmapFactory.decodeResource(res, R.drawable.leaf_ruby_h);
				leaf_diamond = BitmapFactory.decodeResource(res, R.drawable.leaf_diamond_h);
				mirror_leaf = BitmapFactory.decodeResource(res, R.drawable.mirror_leaf_h);
				cat_leaf = BitmapFactory.decodeResource(res, R.drawable.cat_leaf_h);
				
				gbg = BitmapFactory.decodeResource(res, R.drawable.bg0030_h);
				dbg = BitmapFactory.decodeResource(res, R.drawable.bg0060_h);
				splash = BitmapFactory.decodeResource(res, R.drawable.splash0068_m);
				
				greyButton = BitmapFactory.decodeResource(res, R.drawable.grbut_h);
				activeButton = BitmapFactory.decodeResource(res, R.drawable.actbut_h);
				checkedButton = BitmapFactory.decodeResource(res, R.drawable.checkbutton_h);

				shearsImage = BitmapFactory.decodeResource(res, R.drawable.gardenshears_h);
				sickleImage = BitmapFactory.decodeResource(res, R.drawable.sickle_h);

				logoImage = BitmapFactory.decodeResource(res, R.drawable.icon_h);

				petal1 = BitmapFactory.decodeResource(res, R.drawable.petal1_h);
				petal2 = BitmapFactory.decodeResource(res, R.drawable.petal2_h);
				petal3 = BitmapFactory.decodeResource(res, R.drawable.petal3_h);

				Log.d("GaD","loadBitmaps succeeded at level: "+mGraphicsLevel);

				//If it worked, set the ids for the other BG and Splash images
			} catch (OutOfMemoryError e){
				Log.d("GaD","Hi-res image load failed");
				mGraphicsLevel = 2;
			}
		}

		if(mGraphicsLevel == 2){
			//hi-res icons, med-res bgs

			fl1TileImage = null;
			fl2TileImage = null;
			fl3TileImage = null;
			leaf_bronze = null;
			leaf_silver = null;
			leaf_gold = null;
			leaf_ruby = null;
			leaf_diamond = null;
			mirror_leaf = null;
			cat_leaf = null;
			
			gbg = null;
			dbg = null;
			splash = null;

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
				fl1TileImage = BitmapFactory.decodeResource(res, R.drawable.flower1_h);
				fl2TileImage = BitmapFactory.decodeResource(res, R.drawable.flower2_h);
				fl3TileImage = BitmapFactory.decodeResource(res, R.drawable.flower3_h);

				leaf_bronze = BitmapFactory.decodeResource(res, R.drawable.leaf_bronze_h);
				leaf_silver = BitmapFactory.decodeResource(res, R.drawable.leaf_silver_h);
				leaf_gold = BitmapFactory.decodeResource(res, R.drawable.leaf_gold_h);
				leaf_ruby = BitmapFactory.decodeResource(res, R.drawable.leaf_ruby_h);
				leaf_diamond = BitmapFactory.decodeResource(res, R.drawable.leaf_diamond_h);
				mirror_leaf = BitmapFactory.decodeResource(res, R.drawable.mirror_leaf_h);
				cat_leaf = BitmapFactory.decodeResource(res, R.drawable.cat_leaf_h);
				
				gbg = BitmapFactory.decodeResource(res, R.drawable.bg0030_m);
				dbg = BitmapFactory.decodeResource(res, R.drawable.bg0060_m);
				splash = BitmapFactory.decodeResource(res, R.drawable.splash0068_m);

				greyButton = BitmapFactory.decodeResource(res, R.drawable.grbut_h);
				activeButton = BitmapFactory.decodeResource(res, R.drawable.actbut_h);
				checkedButton = BitmapFactory.decodeResource(res, R.drawable.checkbutton_h);

				shearsImage = BitmapFactory.decodeResource(res, R.drawable.gardenshears_h);
				sickleImage = BitmapFactory.decodeResource(res, R.drawable.sickle_h);

				logoImage = BitmapFactory.decodeResource(res, R.drawable.icon_h);

				petal1 = BitmapFactory.decodeResource(res, R.drawable.petal1_h);
				petal2 = BitmapFactory.decodeResource(res, R.drawable.petal2_h);
				petal3 = BitmapFactory.decodeResource(res, R.drawable.petal3_h);

				Log.d("GaD","loadBitmaps succeeded at level: "+mGraphicsLevel);
				//If it worked, set the ids for the other BG and Splash images
			} catch (OutOfMemoryError e){
				Log.d("GaD","Mid-res image load failed");
				mGraphicsLevel = 1;
			}
		}

		if(mGraphicsLevel == 1){
			//mid-res icons, low-res bgs

			fl1TileImage = null;
			fl2TileImage = null;
			fl3TileImage = null;

			leaf_bronze = null;
			leaf_silver = null;
			leaf_gold = null;
			leaf_ruby = null;
			leaf_diamond = null;
			mirror_leaf = null;
			cat_leaf = null;
			
			gbg = null;
			dbg = null;
			splash = null;

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

				//TODO: Add medium versions
				leaf_bronze = BitmapFactory.decodeResource(res, R.drawable.leaf_bronze_h);
				leaf_silver = BitmapFactory.decodeResource(res, R.drawable.leaf_silver_h);
				leaf_gold = BitmapFactory.decodeResource(res, R.drawable.leaf_gold_h);
				leaf_ruby = BitmapFactory.decodeResource(res, R.drawable.leaf_ruby_h);
				leaf_diamond = BitmapFactory.decodeResource(res, R.drawable.leaf_diamond_h);
				mirror_leaf = BitmapFactory.decodeResource(res, R.drawable.mirror_leaf_h);
				cat_leaf = BitmapFactory.decodeResource(res, R.drawable.cat_leaf_h);
				
				gbg = BitmapFactory.decodeResource(res, R.drawable.bg0030_s);
				dbg = BitmapFactory.decodeResource(res, R.drawable.bg0060_s);
				splash = BitmapFactory.decodeResource(res, R.drawable.splash0068_s);

				greyButton = BitmapFactory.decodeResource(res, R.drawable.grbut_m);
				activeButton = BitmapFactory.decodeResource(res, R.drawable.actbut_m);
				checkedButton = BitmapFactory.decodeResource(res, R.drawable.checkbutton_m);

				shearsImage = BitmapFactory.decodeResource(res, R.drawable.gardenshears_m);
				sickleImage = BitmapFactory.decodeResource(res, R.drawable.sickle_m);

				logoImage = BitmapFactory.decodeResource(res, R.drawable.icon_m);

				petal1 = BitmapFactory.decodeResource(res, R.drawable.petal1_m);
				petal2 = BitmapFactory.decodeResource(res, R.drawable.petal2_m);
				petal3 = BitmapFactory.decodeResource(res, R.drawable.petal3_m);

				Log.d("GaD","loadBitmaps succeeded at level: "+mGraphicsLevel);

				//If it worked, set the ids for the other BG and Splash images
			} catch (OutOfMemoryError e){
				//TODO: Give the user a meaningful error message
				Log.d("GaD","Mid-res image load failed");
				mGraphicsLevel = 0;
			}
		}

		
		if(mGraphicsLevel == 0){
			//low-res everything

			fl1TileImage = null;
			fl2TileImage = null;
			fl3TileImage = null;

			leaf_bronze = null;
			leaf_silver = null;
			leaf_gold = null;
			leaf_ruby = null;
			leaf_diamond = null;
			mirror_leaf = null;
			cat_leaf = null;
			
			gbg = null;
			dbg = null;
			splash = null;

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

				//TODO add small versions
				leaf_bronze = BitmapFactory.decodeResource(res, R.drawable.leaf_bronze_h);
				leaf_silver = BitmapFactory.decodeResource(res, R.drawable.leaf_silver_h);
				leaf_gold = BitmapFactory.decodeResource(res, R.drawable.leaf_gold_h);
				leaf_ruby = BitmapFactory.decodeResource(res, R.drawable.leaf_ruby_h);
				leaf_diamond = BitmapFactory.decodeResource(res, R.drawable.leaf_diamond_h);
				mirror_leaf = BitmapFactory.decodeResource(res, R.drawable.mirror_leaf_h);
				cat_leaf = BitmapFactory.decodeResource(res, R.drawable.cat_leaf_h);
				
				gbg = BitmapFactory.decodeResource(res, R.drawable.bg0030_s);
				dbg = BitmapFactory.decodeResource(res, R.drawable.bg0060_s);
				splash = BitmapFactory.decodeResource(res, R.drawable.splash0068_s);

				greyButton = BitmapFactory.decodeResource(res, R.drawable.grbut_s);
				activeButton = BitmapFactory.decodeResource(res, R.drawable.actbut_s);
				checkedButton = BitmapFactory.decodeResource(res, R.drawable.checkbutton_s);

				shearsImage = BitmapFactory.decodeResource(res, R.drawable.gardenshears_s);
				sickleImage = BitmapFactory.decodeResource(res, R.drawable.sickle_s);

				logoImage = BitmapFactory.decodeResource(res, R.drawable.icon_s);

				petal1 = BitmapFactory.decodeResource(res, R.drawable.petal1_s);
				petal2 = BitmapFactory.decodeResource(res, R.drawable.petal2_s);
				petal3 = BitmapFactory.decodeResource(res, R.drawable.petal3_s);

				Log.d("GaD","loadBitmaps succeeded at level: "+mGraphicsLevel);

				//If it worked, set the ids for the other BG and Splash images
			} catch (OutOfMemoryError e){
				//TODO: Give the user a meaningful error message
				Log.e("GaD","Image load failed: You do not have enough memory for 320x240 graphics.");
			}
		}

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

		mGraphicsLevel = 3;
		switch(metrics.densityDpi){
		case DisplayMetrics.DENSITY_HIGH:
		default:
			mGraphicsLevel = 3;
			break;

		case DisplayMetrics.DENSITY_MEDIUM:
			mGraphicsLevel = 2;
			break;

		case DisplayMetrics.DENSITY_LOW:
			mGraphicsLevel = 1;
			break;
		}

		bmP = new Paint();
		bmP.setFilterBitmap(true);

		mActivity = this;
	}
	
	//TODO: Add an onDestroy to save game state, and then add functionality
	//      to load it during game init.

	protected void onStart(){	
		Log.d("GaD","Resuming");
		inDraw = false;
		loadWords();
		loadBitmaps();
		super.onStart();
		//mView.setVisibility(View.VISIBLE);
	}

	protected void onStop(){
		//This stops onDraw from being called?
		//mView.setVisibility(View.INVISIBLE);
		super.onStop();
		
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

		gbg = null;
		dbg = null;
		splash = null;

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
		Log.d("GaD","Paused");
	}

	//This is based on the SCRABBLE (TM) letter distribution. Scrabble has 100 tiles,
	// but we only have 98 because we don't have blanks
	//CONSTANTS -- Don't need to be saved/loaded
	char[] chardist = {'e','a','i','o','n','r','t','l','s','u','d','g','b','c','m','p','f','h','v','w','y','k','j','x','q','z'};
	int[] charcount = {12,9,9,8,6,6,6,4,4,4,4,3,2,2,2,2,2,2,2,2,2,1,1,1,1,1};
	int[] charpts = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	int[] charbgs = {0,1,2,0,1,2,0,1,2,0,1,2,0,1,2,0,1,2,0,1,2,0,1,2,0,1};
	int delpts = 1;
	
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
		//TODO Think about when is the right time to set this
		int sqSize;
		
		//CONSTANTS -- 
		public int scoreGoal = 250;
		
		//GAME STATE VARIABLES -- Need to be saved/loaded
		// in onDestroy/onCreate
		public int mode; //0 - growth, 1 - extinction, 2 - Title movie
		//public boolean newLettersNeeded;
		public char[] p1letters;
		public char[] p2letters;
		
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

		public int p1SmallMallets;
		public int p2SmallMallets;
		public int p1BigMallets;
		public int p2BigMallets;
		public int p1len;
		public int p2len;
		public boolean gameover;
		//public Timer timer;
		public boolean openingdone;
		public int p1DecayDone;
		public int p2DecayDone;	
		//public int whichBG;
		public long lastDrawTime;
		public boolean newLettersNeeded;
		public ArrayList<DrawEffect> deList;
		public int p1downX;
		public int p1downY;
		public int p2downX;
		public int p2downY;

		//Achievement related variables
		ArrayList<String> p1words;
		ArrayList<String> p2words;
		int p1achX;
		int p2achX;

		
		
		public class DrawEffect {
			int x;
			int y;

			int type;/*
			 * Type 0: Scoring effect
			 * Type 1: Extinction effect
			 * Type 2: Dead flower
			 * Type 3: Star
			 * Type 4: Achievement message
			 */
			boolean done;
			int whichPlayer;
			int alpha;
			int amount;
			int whichLetter;
			float scale;
			public String award;
			public String reason;

			public DrawEffect(){
				x = getWidth()/2;
				y = getHeight();
				type = 0;
				done = false;
				whichPlayer = 0;
				alpha = 255;
				scale = 1.0f;
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
					
					p.setStyle(Paint.Style.STROKE);
					p.setStrokeWidth(((float)sqSize)/60.0f);
					p.setColor(textBorderColor);
					p.setAlpha(alpha);
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
					p.setStyle(Paint.Style.STROKE);
					p.setStrokeWidth(((float)sqSize)/60.0f);
					p.setColor(textBorderColor);
					p.setAlpha(alpha);
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
					
				case 3:
					p = new Paint();
					p.setAntiAlias(true);
					Bitmap lbmp= null;
					if(amount == 0){
						lbmp = leaf_bronze;//p.setColor(0xff8C7853); //bronze
					} else if(amount == 1){
						lbmp = leaf_silver;//p.setColor(0xffE6E8FA); //silver
					} else if(amount == 2){
						lbmp = leaf_gold;//p.setColor(0xffD9D919); //gold
					} else if(amount == 3){
						lbmp = leaf_ruby;//p.setColor(0xffbb0000); //ruby
					} else if(amount == 4){
						lbmp = leaf_diamond;//p.setColor(0xffffffff); //diamond
					} else if(amount == 5){
						lbmp = mirror_leaf;
					} else if(amount == 6){
						lbmp = cat_leaf;
					}
						
					if(whichPlayer == 1){
						c.save();
						c.rotate(180,getWidth()/2,getHeight()/2);
					}
					p.setFilterBitmap(true);
					//(12,38) to (89,115)
					
					c.drawBitmap(lbmp,null, new Rect(x, y, x+(sqSize/2), y+(sqSize/2)), p);
					if(whichPlayer == 1){
						c.restore();
					}
					break;
					
				case 4:
					p = new Paint();
					p.setAntiAlias(true);

					if(whichPlayer == 0){
						p.setColor(p1color);
					} else {
						p.setColor(p2color);
					}
					p.setAlpha(alpha);
					
					p.setTextAlign(Align.CENTER);
					if(whichPlayer == 1){
						c.save();
						c.rotate(180,getWidth()/2,getHeight()/2);
					}
					String s1 ="";
					String s2 = "awarded for";
					String s3 ="";
					if(amount == 0){
						s1 = "Bronze Leaf"; //bronze
						s3 = "20-point Word";
					} else if(amount == 1){
						s1 = "Silver Leaf"; //bronze
						s3 = "25-point Word";
					} else if(amount == 2){
						s1 = "Gold Leaf"; //bronze
						s3 = "27-point Word";
					} else if(amount == 3){
						s1 = "Ruby Leaf"; //bronze
						s3 = "29-point Word";
					} else if(amount == 4){
						s1 = "Diamond Leaf"; //bronze
						s3 = "31-point Word";
					}
					
					if(scale > 0.0f){
						p.setTextSize(scale*sqSize);
						c.drawText(s1, x, y-scale*(sqSize/2), p);
						p.setTextSize(scale*(sqSize/2));
						c.drawText(s2, x, y+scale*(sqSize/8), p);
						p.setTextSize(scale*sqSize);
						c.drawText(s3, x, y+scale*sqSize, p);
						
						p.setStyle(Paint.Style.STROKE);
						p.setStrokeWidth(scale*((float)sqSize)/60.0f);
						p.setColor(textBorderColor);
						p.setTextSize(scale*sqSize);
						c.drawText(s1, x, y-scale*(sqSize/2), p);
						p.setTextSize(scale*(sqSize/2));
						c.drawText(s2, x, y+scale*(sqSize/8), p);
						p.setTextSize(scale*sqSize);
						c.drawText(s3, x, y+scale*sqSize, p);
					}
					
					scale -= 0.035f;
					if(scale <= 0.0f){
						done = true;
					}
					if(whichPlayer == 1){
						c.restore();
					}
					break;
					
				case 5:
					p = new Paint();
					p.setAntiAlias(true);

					if(whichPlayer == 0){
						p.setColor(p1color);
					} else {
						p.setColor(p2color);
					}
					p.setAlpha(alpha);
					
					p.setTextAlign(Align.CENTER);
					if(whichPlayer == 1){
						c.save();
						c.rotate(180,getWidth()/2,getHeight()/2);
					}
					
					
					String s11 = award;
					String s21 = "awarded for";
					String s31 = reason;
					
					if(scale > 0.0f){
						p.setTextSize(scale*sqSize);
						c.drawText(s11, x, y-scale*(sqSize/2), p);
						p.setTextSize(scale*(sqSize/2));
						c.drawText(s21, x, y+scale*(sqSize/8), p);
						p.setTextSize(scale*sqSize);
						c.drawText(s31, x, y+scale*sqSize, p);
						
						p.setStyle(Paint.Style.STROKE);
						p.setStrokeWidth(scale*((float)sqSize)/60.0f);
						p.setColor(textBorderColor);
						p.setTextSize(scale*sqSize);
						c.drawText(s11, x, y-scale*(sqSize/2), p);
						p.setTextSize(scale*(sqSize/2));
						c.drawText(s21, x, y+scale*(sqSize/8), p);
						p.setTextSize(scale*sqSize);
						c.drawText(s31, x, y+scale*sqSize, p);
					}
					
					
					scale -= 0.035f;
					if(scale <= 0.0f){
						done = true;
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
			p1downX = -1;
			p1downY = -1;
			p2downX = -1;
			p2downY = -1;
			//whichBG = 0;

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
			
			//TODO REMOVE THIS
			/*p1letters[0] = 'j';
			p1letters[1] = 'u';
			p1letters[2] = 'm';
			p1letters[3] = 'b';
			p1letters[4] = 'o';
			p1letters[5] = 'o';
			p1letters[6] = 'b';
			p1letters[7] = 's';
			
			p2letters[0] = 'j';
			p2letters[1] = 'u';
			p2letters[2] = 'm';
			p2letters[3] = 'b';
			p2letters[4] = 'o';
			p2letters[5] = 'o';
			p2letters[6] = 'b';
			p2letters[7] = 's';*/
		}

		protected int scoreWord(String s){
			int score = 0;
			for(int i=0; i<s.length(); i++){
				score += (1+charpts[s.charAt(i) - 'a']);
			}

			return score;
		}

		//Utility function to load background bitmaps, and handle
		// out of memory errors
		/*protected void loadBG(int number){
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
		}*/


		public void newGame(){
			//openingdone = false;
			mode = 0;
			//whichBG = 0;

			p1word = "";
			p2word = "";
			p1score = 0;
			p2score = 0;
			p1done = false;
			p2done = false;
			p1DecayDone = 0;
			p2DecayDone = 0;
			p1selected = -1;
			p2selected = -1;
			p1selected2 = -1;
			p2selected2 = -1;
			pickNewLetters();

			p1words = new ArrayList<String>();
			p2words = new ArrayList<String>();
			p1achX = 0;
			p2achX = 0;
			
			deList.clear();
			
			//loadBG(gbgs[14]);	
			this.invalidate();
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

			//whichBG = whichBG + (drawSteps/2);


			if(mode == 0){			
				c.drawBitmap(gbg, null, new Rect(0,0,getWidth(),getHeight()), bmP);
			} else if(mode == 1){
				c.drawBitmap(dbg, null, new Rect(0,0,getWidth(),getHeight()), bmP);
			} else {
				c.drawBitmap(splash, null, new Rect(0,0,getWidth(),getHeight()), bmP);
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
				
				if(p1score > p2score){
					c.drawText("Winner - "+p1score+" points", getWidth()/2, getHeight()-sqSize, p);
					c.save();
					c.rotate(180,getWidth()/2,getHeight()/2);
					c.drawText("" + p2score + " points", getWidth()/2, getHeight()-sqSize, p);
					c.restore();
				}else if(p2score > p1score){
					c.drawText("" + p1score + " points", getWidth()/2, getHeight()-sqSize, p);
					c.save();
					c.rotate(180,getWidth()/2,getHeight()/2);
					c.drawText("Winner - "+p2score+" points", getWidth()/2, getHeight()-sqSize, p);
					c.restore();
				}else{
					c.drawText("Tie - "+p1score+" points", getWidth()/2, getHeight()-sqSize, p);
					c.save();
					c.rotate(180,getWidth()/2,getHeight()/2);
					c.drawText("Tie - "+p2score+" points", getWidth()/2, getHeight()-sqSize, p);
					c.restore();
				}
				inDraw = false;
				
				this.postInvalidateDelayed(MSPERFRAME/2);
				return;
			}


			if(mode == 0 && p1done && p2done){
				mode = 1;
				//If both players are done, total points and reset
				//whichBG = 0;

				p1done = false;
				p2done = false;
				p1DecayDone = 0;
				p2DecayDone = 0;

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

				doGrowthAchievements(p1word, p2word);
				
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


			} else if(mode == 1 && (p1done || p1DecayDone ==2) && 
					(p2done || p2DecayDone == 2)){
				mode = 0;
				//whichBG = 0;

				p1done = false;
				p2done = false;
				p1DecayDone = 0;
				p2DecayDone = 0;
				//Score leftover letters
				/*if(p1word.length() == 0)
					p1score += 2*(p1len);*/ //p1 gets bonus only if clearing
				p2score += delpts*(p1word.length()); //p2 get bonus based on leftovers
				if(p1word.length() > 0){
					DrawEffect bonusE = new DrawEffect();
					bonusE.amount = delpts*(p1word.length());
					bonusE.whichPlayer = 1;
					bonusE.x = 5*sqSize;
					bonusE.y = (int)(1.5*sqSize);
					bonusE.alpha = 255;
					bonusE.type = 0;
					deList.add(bonusE);
				}
				/*if(p2word.length() == 0)
					p2score += 2*(p2len); *///p2 gets bonus only if clearing
				p1score += delpts*(p2word.length()); //p1 get bonus based on leftovers
				if(p2word.length() > 0){
					DrawEffect bonusE = new DrawEffect();
					bonusE.amount = delpts*(p2word.length());
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
						drawTile(c,p1letters[i],i*sqSize,getHeight(),1+charpts[p1letters[i]-'a'],(i == p1selected || i == p1selected2));
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
						drawTile(c,p1word.charAt(i),offset+i*sqSize,getHeight()-sqSize,1+charpts[p1word.charAt(i)-'a'],false);
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
						drawTile(c,p2letters[i],i*sqSize,getHeight(),1+charpts[p2letters[i]-'a'],(i == p2selected || i == p2selected2));
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
						drawTile(c,p2word.charAt(i),offset+i*sqSize,getHeight()-sqSize,1+charpts[p2word.charAt(i)-'a'],false);
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
					drawTile(c,p1word.charAt(i),offset+i*sqSize,getHeight()-sqSize,delpts,(p1selected == i || p1selected2 == i));

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
					if(p1selected != -1 && p1selected2 == -1){
						p.setAlpha(255);
					} else {
						p.setAlpha(160);
					}
					c.drawText("Select a letter, then tap", 3*sqSize, getHeight()-2*sqSize/3, p);
					c.drawText("here to activate shears", 3*sqSize, getHeight()-sqSize/3, p);
					p.setAlpha(255);
				} 

				p.setAlpha(255);
				if(p1BigMallets > 0 && p1SmallMallets > 0){
					mDrawable.setBounds(6*sqSize,getHeight()-7*sqSize/8,6*sqSize+1,getHeight()-sqSize/8);
					mDrawable.draw(c);
				}

				if(p1BigMallets > 0){
					c.drawBitmap(sickleImage, null, new Rect((int)(6.5*sqSize),getHeight()-sqSize,(int)(7.5*sqSize),getHeight()),bmP);
					p.setColor(textBorderColor);
					if(p1selected != -1 && p1selected2 != -1){
						p.setAlpha(255);
					} else {
						p.setAlpha(160);
					}
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/3);
					c.drawText("Select two" , 7*sqSize, getHeight()-5*sqSize/6, p);	
					c.drawText("neighbors,",7*sqSize, getHeight()-3*sqSize/6, p);
					c.drawText("then tap",7*sqSize, getHeight()-1*sqSize/6, p);
					p.setAlpha(255);
				}
				
				//End of turn button
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
				if(p1DecayDone >= 1){
					c.drawBitmap(checkedButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				} else {
					c.drawBitmap(activeButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				}  	
				p.setColor(textBorderColor);
				p.setTextSize(sqSize/3);
				p.setTextAlign(Align.CENTER);
				if(p1DecayDone == 0){
					p.setAlpha(192);
					c.drawText("Tap here", 9*sqSize, getHeight()-sqSize-sqSize, p);
					c.drawText("if done", 9*sqSize, getHeight()-sqSize-2*sqSize/3, p);
				} else if(p1DecayDone == 1){
					p.setAlpha(192);
					c.drawText("Tap again", 9*sqSize, getHeight()-sqSize-sqSize, p);
					c.drawText("to confirm", 9*sqSize, getHeight()-sqSize-2*sqSize/3, p);			
				} else {
					p.setAlpha(192);
					//c.drawText("C", 9*sqSize, getHeight()-sqSize-sqSize, p);
					c.drawText("Confirmed", 9*sqSize, getHeight()-sqSize-2*sqSize/3, p);			
				}
				p.setAlpha(255);

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
					drawTile(c,p2word.charAt(i),offset+i*sqSize,getHeight()-sqSize,delpts,(p2selected == i || p2selected2 == i));

					
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
					if(p2selected != -1 && p2selected2 == -1){
						p.setAlpha(255);
					} else {
						p.setAlpha(160);
					}
					c.drawText("Select a letter, then tap", 3*sqSize, getHeight()-2*sqSize/3, p);
					c.drawText("here to activate shears", 3*sqSize, getHeight()-sqSize/3, p);
					p.setAlpha(255);
				}
				
				p.setAlpha(255);
				if(p2BigMallets > 0 && p2SmallMallets > 0){
					mDrawable.setBounds(6*sqSize,getHeight()-7*sqSize/8,6*sqSize+1,getHeight()-sqSize/8);
					mDrawable.draw(c);
				}

				if(p2BigMallets > 0){
					c.drawBitmap(sickleImage, null, new Rect((int)(6.5*sqSize),getHeight()-sqSize,(int)(7.5*sqSize),getHeight()),bmP);
					p.setColor(textBorderColor);
					if(p2selected != -1 && p2selected2 != -1){
						p.setAlpha(255);
					} else {
						p.setAlpha(160);
					}
					p.setTextAlign(Align.CENTER);
					p.setTextSize(sqSize/3);
					c.drawText("Select two" , 7*sqSize, getHeight()-5*sqSize/6, p);	
					c.drawText("neighbors,",7*sqSize, getHeight()-3*sqSize/6, p);
					c.drawText("then tap",7*sqSize, getHeight()-1*sqSize/6, p);
					p.setAlpha(255);
				}

				//End of turn button
				mDrawable.getPaint().setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
				if(p2DecayDone >= 1){
					c.drawBitmap(checkedButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				} else {
					c.drawBitmap(activeButton,null,new Rect((int)(8.5*sqSize),(int)(getHeight()-1.5*sqSize),(int)(9.5*sqSize),(int)(getHeight()-0.5*sqSize)),bmP);
				}  	
				p.setColor(textBorderColor);
				p.setTextSize(sqSize/3);
				p.setTextAlign(Align.CENTER);
				if(p2DecayDone == 0){
					p.setAlpha(192);
					c.drawText("Tap here", 9*sqSize, getHeight()-sqSize-sqSize, p);
					c.drawText("if done", 9*sqSize, getHeight()-sqSize-2*sqSize/3, p);
				} else if(p2DecayDone == 1){
					p.setAlpha(192);
					c.drawText("Tap again", 9*sqSize, getHeight()-sqSize-sqSize, p);
					c.drawText("to confirm", 9*sqSize, getHeight()-sqSize-2*sqSize/3, p);			
				} else {
					p.setAlpha(192);
					//c.drawText("C", 9*sqSize, getHeight()-sqSize-sqSize, p);
					c.drawText("Confirmed", 9*sqSize, getHeight()-sqSize-2*sqSize/3, p);			
				}
				p.setAlpha(255);
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

			float x = arg1.getX();
			float y = arg1.getY();
			boolean goodup = false;
			
			if(arg1.getAction() == MotionEvent.ACTION_UP && mode == 0){	
				if(y < getHeight()/2){
					//player 2's side
					p2downX = (int)x;
					p2downY = (int)y;
					if(getWidth()-x < 8*sqSize && y > sqSize && p2selected != -1){
						goodup = true;
					}
				} else {
					p1downX = (int)x;
					p1downY = (int)y;
					if(x < 8*sqSize && y < getHeight()-sqSize && p1selected != -1){
						goodup = true;
					}
				}
				
			} 

			if(arg1.getAction() == MotionEvent.ACTION_DOWN || goodup){
				this.invalidate();

				if(y < getHeight()/2){
					p2downX = (int)x;
					p2downY = (int)y;
				} else {
					p1downX = (int)x;
					p1downY = (int)y;
				}

				if(mode == 2){
					openingdone = false;
					mode = 0;
					if(deList != null){
						deList.clear();
					}
					//whichBG = 0;

					return true;
				}

				if(gameover){
					gameover = false;
					mode = 2;
					//whichBG = 0;

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
						p1DecayDone = 0;
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
						p2DecayDone = 0;
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
					} else if(x < sqSize*6 && y > getHeight()-sqSize){
						p1DecayDone = 0;
						//player 1 is activating small mallet
						if(p1selected != -1 && p1selected2 == -1 && p1SmallMallets > 0){
							//Only one letter selected
							p1SmallMallets -= 1;
							if(p1SmallMallets < 0) {
								p1BigMallets -= 1;
								p1SmallMallets = 0;
							}
							String t = p1word.substring(0,p1selected) + p1word.substring(p1selected+1);
							if(t.length() == 0 || wordSet.contains(t)){
								DrawEffect Se = new DrawEffect();
								Se.amount = delpts;
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

								p1score += delpts;
								p1word = t;
							}
							if(t.length() == 0){
								DrawEffect ExtinctE = new DrawEffect();
								ExtinctE.whichPlayer = 0;
								ExtinctE.x = 4*sqSize;
								ExtinctE.y = sqSize;
								ExtinctE.type = 1;
								deList.add(ExtinctE);


								p1score += delpts*(p1len);

								ExtinctE = new DrawEffect();
								ExtinctE.whichPlayer = 0;
								ExtinctE.x = 4*sqSize;
								ExtinctE.y = getHeight()-sqSize;
								ExtinctE.type = 0;
								ExtinctE.amount = delpts*p1len;
								deList.add(ExtinctE);
							}
							p1selected = p1selected2 = -1;

						} 
					} else if(x > sqSize*6 && x < sqSize*8 && y > getHeight() - sqSize){
						//player 1 is activating big mallet
						p1DecayDone = 0;
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
								Se.amount = delpts*2;
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

								Se = new DrawEffect();
								Se.type = 2;
								Se.whichPlayer = 0;
								Se.whichLetter = p1word.charAt(p1selected2);
								letOff = p1selected2 - p1word.length()/2.0f;
								Se.x = (int) letOff*sqSize + 4*sqSize;
								Se.y = (int) getHeight()-3*sqSize/2;
								deList.add(Se);
								
								p1score += delpts*2;
								p1word = t;
							}
							if(t.length() == 0){
								DrawEffect ExtinctE = new DrawEffect();
								ExtinctE.whichPlayer = 0;
								ExtinctE.x = 4*sqSize;
								ExtinctE.y = sqSize;
								ExtinctE.type = 1;
								deList.add(ExtinctE);

								p1score += delpts*(p1len);

								ExtinctE = new DrawEffect();
								ExtinctE.whichPlayer = 0;
								ExtinctE.x = 4*sqSize;
								ExtinctE.y = getHeight()-sqSize;
								ExtinctE.type = 0;
								ExtinctE.amount = delpts*p1len;
								deList.add(ExtinctE);
							}
							p1selected = p1selected2 = -1;

						}
					} else if(getWidth() - x < sqSize*6 && y < sqSize){
						p2DecayDone = 0;
						// player 2 is activating small mallet
						if(p2selected != -1 && p2selected2 == -1 && p2SmallMallets > 0 ){
							//Only one letter selected
							p2SmallMallets -= 1;
							if(p2SmallMallets < 0){
								p2BigMallets -= 1;
								p2SmallMallets = 0;
							}
							String t = p2word.substring(0,p2selected) + p2word.substring(p2selected+1);
							if(t.length() == 0 || wordSet.contains(t)){
								DrawEffect Se = new DrawEffect();
								Se.amount = delpts;
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

								p2score += delpts;
								p2word = t;
							}
							if(t.length() == 0){
								DrawEffect ExtinctE = new DrawEffect();
								ExtinctE.whichPlayer = 1;
								ExtinctE.x = 4*sqSize;
								ExtinctE.y = sqSize;
								ExtinctE.type = 1;
								deList.add(ExtinctE);

								p2score += delpts*(p2len);

								ExtinctE = new DrawEffect();
								ExtinctE.whichPlayer = 1;
								ExtinctE.x = 4*sqSize;
								ExtinctE.y = getHeight()-sqSize;
								ExtinctE.type = 0;
								ExtinctE.amount = delpts*p2len;
								deList.add(ExtinctE);
							}
							p2selected = p2selected2 = -1;

						}
					}else if(getWidth()-x > sqSize*6 && getWidth()-x < sqSize*8 && y < sqSize){
						// player 2 is activating big mallet
						p2DecayDone = 0;
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
								Se.amount = delpts*2;
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
								
								Se = new DrawEffect();
								Se.type = 2;
								Se.whichPlayer = 1;
								Se.whichLetter = p2word.charAt(p2selected2);
								letOff = p2selected2 - p2word.length()/2.0f;
								Se.x = (int) letOff*sqSize + 4*sqSize;
								Se.y = (int) getHeight()-3*sqSize/2;
								deList.add(Se);

								p2score += delpts*2;
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

								p2score += delpts*(p2len);

								ExtinctE = new DrawEffect();
								ExtinctE.whichPlayer = 1;
								ExtinctE.x = 4*sqSize;
								ExtinctE.y = getHeight()-sqSize;
								ExtinctE.type = 0;
								ExtinctE.amount = delpts*p2len;
								deList.add(ExtinctE);
							}
						}
					} else if(x > sqSize*8 && y > getHeight() - 2*sqSize){
						//p1 done button during decay phase
						if(p1DecayDone == 0){
							p1DecayDone = 1;
						} else if(p1DecayDone == 1) {
							p1DecayDone = 2;
						}
					} else if(getWidth()-x > sqSize*8 &&  y < 2*sqSize){
						//p2 done button during decay phase
						if(p2DecayDone == 0){
							p2DecayDone = 1;
						} else if(p2DecayDone == 1) {
							p2DecayDone = 2;
						}
					}
				}


			}



			return true;


		}



		
		
		/*
		 * ACHIEVEMENTS
		 */
		
		/* YES -- DONE
		 * 
		 * Diamond/Platinum/Gold/Silver/Bronze star for building words.
		 * Diamond star - 45 pt word. Only 418 of these.
		 * Ruby star - 40 pt word. Only 1673 of these.
		 * Gold star - 33pt word. In best 10% of words, and only ~0.1% chance
		 *             of happening on a particular draw
		 * Silver star - 30pt word. In best 20% of words, about 3.5% chance
		 * Bronze star - 28pt word. About 10% chance of happening.
		 */
		
		/* MAYBE -- seems to duplicate the star achievements
		 * 
		 * 7-letter club - build 7-letter word
		 * 8-letter club - build 8-letter word
		 */
		
		/*
		 * Play-history-based achievements
		 * DONE -- Copycat - Play a word previously played by the other player
		 * DONE -- Palindrome - Play a palindrome of at least 4 letters
		 */
		
		void doGrowthAchievements(String p1word, String p2word){
			
			//First do star awards for big-scoring words
			int p1wscore = scoreWord(p1word);
			int p2wscore = scoreWord(p2word);
			if(p1wscore >= 20){
				//Star achieved
				DrawEffect de = new DrawEffect();
				de.whichPlayer = 0;
				de.type = 3;
				de.amount = 0; //bronze
				if(p1wscore >= 25) de.amount = 1; //silver
				if(p1wscore >= 27) de.amount = 2; //gold
				if(p1wscore >= 29) de.amount = 3; //ruby
				if(p1wscore >= 31) de.amount = 4; //diamond
				de.x = p1achX-sqSize/8; //p1stars*(sqSize/2);
				de.y = getHeight()/2+(sqSize/2);
				p1achX = p1achX + sqSize/2 - sqSize/4;
				deList.add(de);
				
				de = new DrawEffect();
				de.whichPlayer = 0;
				de.type = 4;
				de.amount = 0; //bronze
				if(p1wscore >= 25) de.amount = 1; //silver
				if(p1wscore >= 27) de.amount = 2; //gold
				if(p1wscore >= 29) de.amount = 3; //ruby
				if(p1wscore >= 31) de.amount = 4; //diamond
				de.x = getWidth()/2;
				de.y = 3*getHeight()/4;
				deList.add(de);
			}
			if(p2wscore >= 20){
				//Star achieved
				DrawEffect de = new DrawEffect();
				de.whichPlayer = 1;
				de.type = 3;
				de.amount = 0; //bronze
				if(p2wscore >= 25) de.amount = 1; //silver
				if(p2wscore >= 27) de.amount = 2; //gold
				if(p2wscore >= 29) de.amount = 3; //ruby
				if(p2wscore >= 31) de.amount = 4; //diamond
				de.x = p2achX -sqSize/8; //p2stars*(sqSize/2);
				de.y = getHeight()/2+(sqSize/2);
				p2achX = p2achX + sqSize/2 -sqSize/4;
				deList.add(de);
				
				de = new DrawEffect();
				de.whichPlayer = 1;
				de.type = 4;
				de.amount = 0; //bronze
				if(p2wscore >= 25) de.amount = 1; //silver
				if(p2wscore >= 27) de.amount = 2; //gold
				if(p2wscore >= 29) de.amount = 3; //ruby
				if(p2wscore >= 31) de.amount = 4; //diamond
				de.x = getWidth()/2;
				de.y = 3*getHeight()/4;
				deList.add(de);
			}
			
			
			
			//Palindrome
			boolean isPali = true;
			if(p1word.length() < 4) isPali = false;
			for(int i=0; i<p1word.length();i++){
				if(p1word.charAt(i) != p1word.charAt(p1word.length()-i-1)){
					isPali = false;
				}
			}
			if(isPali){
				DrawEffect de = new DrawEffect();
				de.whichPlayer = 0;
				de.type = 5;
				de.award = "Mirror Leaf";
				de.reason = "Palindrome, 4+ letters";
				de.x = getWidth()/2;
				de.y = 3*getHeight()/4;
				deList.add(de);
				
				p1score += p1word.length();
				
				de = new DrawEffect();
				de.amount = p1word.length();
				de.type = 0;
				de.whichPlayer = 0;			
				de.x = (int) 4*sqSize;
				de.y = (int) getHeight()-3*sqSize/2;
				deList.add(de);
				
				de = new DrawEffect();
				de.whichPlayer = 0;
				de.type = 3;
				de.amount = 5; //bronze
				de.x = p1achX; //p1stars*(sqSize/2);
				de.y = getHeight()/2+(sqSize/2);
				p1achX = p1achX + sqSize/2;
				deList.add(de);
			}
			
			isPali = true;
			if(p2word.length() < 4) isPali = false;
			for(int i=0; i<p2word.length();i++){
				if(p2word.charAt(i) != p2word.charAt(p2word.length()-i-1)){
					isPali = false;
				}
			}
			if(isPali){
				DrawEffect de = new DrawEffect();
				de.whichPlayer = 1;
				de.type = 5;
				de.award = "Mirror Leaf";
				de.reason = "Palindrome, 4+ letters";
				de.x = getWidth()/2;
				de.y = 3*getHeight()/4;
				deList.add(de);
				
				p2score += p2word.length();
				
				de = new DrawEffect();
				de.amount = p2word.length();
				de.type = 0;
				de.whichPlayer = 1;				
				de.x = (int) 4*sqSize;
				de.y = (int) getHeight()-3*sqSize/2;
				deList.add(de);
				
				de = new DrawEffect();
				de.whichPlayer = 1;
				de.type = 3;
				de.amount = 5;
				de.x = p2achX; //p1stars*(sqSize/2);
				de.y = getHeight()/2+(sqSize/2);
				p2achX = p2achX + sqSize/2;
				deList.add(de);
			}
			
			
			
			//Copycat
			boolean iscopy = false;
			for(int i=0; p2words!=null && i<p2words.size(); i++){
				if(p2words.get(i).equalsIgnoreCase(p1word)){
					iscopy = true;
				}
			}
			if(iscopy && p1word.length() >= 4){
				DrawEffect de = new DrawEffect();
				de.whichPlayer = 0;
				de.type = 5;
				de.award = "Copycat, 4+ letters";
				de.reason = "Repeat opponent's word";
				de.x = getWidth()/2;
				de.y = 3*getHeight()/4;
				deList.add(de);
				
				p1score += p1word.length();
				
				de = new DrawEffect();
				de.amount = p1word.length();
				de.type = 0;
				de.whichPlayer = 0;			
				de.x = (int) 4*sqSize;
				de.y = (int) getHeight()-3*sqSize/2;
				deList.add(de);
				
				de = new DrawEffect();
				de.whichPlayer = 0;
				de.type = 3;
				de.amount = 6; //copycat
				de.x = p1achX; //p1stars*(sqSize/2);
				de.y = getHeight()/2+(sqSize/2);
				p1achX = p1achX + sqSize/2;
				deList.add(de);
			}
			iscopy = false;
			for(int i=0; p1words != null && i<p1words.size(); i++){
				if(p1words.get(i).equalsIgnoreCase(p2word)){
					iscopy = true;
				}
			}
			if(iscopy && p2word.length() >= 4){
				DrawEffect de = new DrawEffect();
				de.whichPlayer = 1;
				de.type = 5;
				de.award = "Copycat, 4+ letters";
				de.reason = "Repeat opponent's word";
				de.x = getWidth()/2;
				de.y = 3*getHeight()/4;
				deList.add(de);
				
				p2score += p2word.length();
				
				de = new DrawEffect();
				de.amount = p2word.length();
				de.type = 0;
				de.whichPlayer = 1;			
				de.x = (int) 4*sqSize;
				de.y = (int) getHeight()-3*sqSize/2;
				deList.add(de);
				
				de = new DrawEffect();
				de.whichPlayer = 1;
				de.type = 3;
				de.amount = 6; //copycat
				de.x = p2achX; //p1stars*(sqSize/2);
				de.y = getHeight()/2+(sqSize/2);
				p2achX = p2achX + sqSize/2;
				deList.add(de);
			}
			
			
			
			if(p1words == null){
				p1words = new ArrayList<String>();
			}
			if(p2words == null){
				p2words = new ArrayList<String>();
			}
			//Do this last
			p1words.add(p1word);
			p2words.add(p2word);
		}

	}
}