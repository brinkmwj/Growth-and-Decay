#include <iostream>
#include <string>
#include <set>
#include <vector>
#include <cstdlib>

#define DELPTS 1
#define BONUS 1

using namespace std;

set<string> words;


char chardist[26] = {'e','a','i','o','n','r','t','l','s','u','d','g','b','c','m','p','f','h','v','w','y','k','j','x','q','z'};
int charcount[26] = {12,9,9,8,6,6,6,4,4,4,4,3,2,2,2,2,2,2,2,2,2,1,1,1,1,1};
int charpts[26] =    {1,  3,  3,  2,  1,  4,  2,  4,  1,  8,  5,  1,  3,  1,  1,  3, 10,  1,  1,  1,  1,  4,  4,  8,  4, 10};

int scoreLetter(char c){
  //for(int i=0; i<26; i++){
    //if(chardist[i] == c){
      return BONUS+charpts[c-'a'];
      //}
      //}

      // return -5000;
}

int scoreWord(string s){
  int total = 0;
  for(int i=0; i<s.length(); i++){
    total += scoreLetter(s[i]);
  }

  return total;
}

double letterProb(char c){
  //for(int i=0; i<26; i++){
  //if(chardist[i] == c){
      return ((double)charcount[c-'a'])/98.0;
      //}
      //}

      //return -1.0;
}

double nchoosek(double n, double k){
  if(k > n-k){
    k = n-k;
  }
  double c = 1;
  for(int i=0; i<k; i++){
    c = c*(n-i);
    c = c/(i+1);
  }

  return c;
}

//This does not account for the fact that you actually have 8
// letters to choose from, so it favors the big words a bit
// unfairly
double wordProb(string s){
  double total = 1.0;
  for(int i=0; i<s.length(); i++){
    total *= letterProb(s[i]);
  }

  return total*nchoosek(8,8-s.length());
}


int scoreBreakdown(string s, int numShears, int numSickles, int origlen){
  //If word fully broken down, give bonus
  if(s.length() == 0) return 2*origlen;
  //This ensures that if a word is not broken down, we don't pick this
  if(words.count(s) < 1) return 0; 

  int max = 0;
  string maxS = "";

  if(numShears > 0){
    for(int i=0; i<s.length(); i++){
      string t = s.substr(0,i) + s.substr(i+1,s.length()-i-1);
      int sb = 0;
      if(t.length() == 0 || words.count(t) > 0){
	sb = scoreBreakdown(t,numShears-1,numSickles,origlen)+DELPTS;
      }
      /*if(sb > 0){
	sb = sb+DELPTS;
	}*/
      if(sb > max){
	max = sb;
	maxS = t;
      }
    }   
  }

  if(numSickles > 0 && s.length() >= 2){
    for(int i=0; i<s.length()-1; i++){
      string t = s.substr(0,i) + s.substr(i+2,s.length()-i-1);
      int sb = 0;
      if(t.length() == 0 || words.count(t) > 0){
	sb = scoreBreakdown(t,numShears,numSickles-1,origlen)+2*DELPTS;
      }
      /*      if(sb > 0){
	sb = sb+2*DELPTS;
	}*/
      if(sb > max){
	max = sb;
	maxS = t;
      }
    }
  }

  return max;
}

bool canDo(string s, int numShears, int numSickles){
  if(s.length() == 0) return true;
  if(words.count(s) < 1) return false;
  if(numShears > 0){
    for(int i=0; i<s.length(); i++){
      string t = s.substr(0,i) + s.substr(i+1,s.length()-i-1);
      if(canDo(t,numShears-1,numSickles)){
	return true;
      }
    }
  }
  if(numSickles > 0 && s.length() >= 2){
    for(int i=0; i<s.length()-1; i++){
      string t = s.substr(0,i) + s.substr(i+2,s.length()-i-1);
      if(canDo(t,numShears,numSickles-1)){
	return true;
      }
    }
  }

  return false;
}

bool myComp(pair<int, string> i, pair<int, string> j){
  return i.first > j.first; //Want reverse order
}

char randLetter(){
  int r = rand()%98; //HARD CODED CONSTANT - sum of charcount
  //This is based on the scrabble distribution
  for(int i=0; i<26; i++){ //HARD CODED CONSTANT - length of charcount/chardist
    if(charcount[i] > r){
      return chardist[i];
    }
    
    r -= charcount[i];
  }
  
  return 'z';
}

bool isvowel(char c){
  if(c == 'a' || c == 'e' || c=='i' || c=='o' || c=='u') return true;
  
  return false;
}


int pickNewLetters(){
  int points=0;
  int vc = 0;
  while(vc < 1 || vc > 4){
    points = 0;
    vc = 0;
    for(int i=0; i<8; i++){
      char c = randLetter();
      if(isvowel(c)) vc++;
      points += scoreLetter(c);
    }
  }

  return points;
}


int main(){
  cout << scoreWord("riffraff") << endl;

  int scorecount[11*8];
  for(int i=0; i<11*8; i++){
    scorecount[i] = 0;
  }
  for(int i=0; i<10000; i++){
    int score = pickNewLetters();
    scorecount[score]++;
  }
  int sum = 0;
  for(int i=0; i<11*8; i++){
    sum += scorecount[i];
    cout << i << " " << sum << endl;
  }



  cout << endl << endl;

  string s;
  while(!getline(cin,s).eof()){
    words.insert(s);
  }

  long numWords[9][200];
  //long numSolved[9];
  for(int j=0; j<9; j++){
  for(int i=0; i<200; i++){
    numWords[j][i] = 0;
  }}


  vector<pair<int, string> > wordsByScore;
  double totalWP = 0.0;

  set<string>::iterator it = words.begin();
  for(;it != words.end(); it++){
    int nSick = 1;
    int nSh = (*it).length()-2;
    if((*it).length() <= 2){
      nSick = 0;
      nSh = (*it).length();
    }
    int score = scoreWord(*it);
    int sb1 = scoreBreakdown((*it),nSh,nSick,(*it).length());
    if(sb1 <= 0){
      //cout << "Unassailable: " << sb1 << " " << (*it) << endl;
    }
    int sb = score - sb1;
    //cout << (*it) << " " << (canDo((*it),nSh,nSick) ? "true" : "false") << endl;
    wordsByScore.push_back(make_pair(score,*it));

    double wp = wordProb(*it);
    totalWP += wp;

    numWords[(*it).length()][sb+100]++;
    //cout << (*it) << " " << sb << endl;
  }

  for(int j=1; j<9; j++){
    cout << j << endl;
    int total = 0;
    int count = 0;
  for(int i=0; i<200; i++){
    //cout << i-100 << ": " << numWords[j][i] << endl;
    total = total + (i-100)*numWords[j][i];
    count = count + numWords[j][i];
  }
  cout << (100*total/count) << endl;
  }

  sort(wordsByScore.begin(),wordsByScore.end(),myComp);
  vector<pair<int, string> >::iterator it2 = wordsByScore.begin();

  for(double i=0.0; i<0.05; i += 0.001){

    cout << 100.0*i << " " << wordsByScore[wordsByScore.size()*i].first << " " << wordsByScore[wordsByScore.size()*i].second << endl;
  }
  //for(;it2 != wordsByScore.end(); it2++){
  //  cout << (*it2).first << " " << (*it2).second << endl;
  //}

  cout << endl << endl;

  double goal = 0.00000;
  double sofar = 0.0;
  cout << totalWP << endl;
  for(;it2 != wordsByScore.end(); it2++){
    double wp = wordProb((*it2).second);
    if(sofar <= goal && (sofar+wp) > goal){
      cout << sofar << " " << (*it2).first << " " << (*it2).second << endl;
      goal += 0.00025;
    }
    
    sofar += wp;
    if(goal > 0.05) break;
    }

  return 0;
}
