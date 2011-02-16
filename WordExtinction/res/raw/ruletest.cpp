#include <iostream>
#include <string>
#include <set>

#define DELPTS 1
#define BONUS 1

using namespace std;

set<string> words;


char chardist[26] = {'e','a','i','o','n','r','t','l','s','u','d','g','b','c','m','p','f','h','v','w','y','k','j','x','q','z'};
int charcount[26] = {12,9,9,8,6,6,6,4,4,4,4,3,2,2,2,2,2,2,2,2,2,1,1,1,1,1};
int charpts[26] =    {1,  3,  3,  2,  1,  4,  2,  4,  1,  8,  5,  1,  3,  1,  1,  3, 10,  1,  1,  1,  1,  4,  4,  8,  4, 10};

int scoreLetter(char c){
  for(int i=0; i<26; i++){
    if(chardist[i] == c){
      return BONUS+charpts[i];
    }
  }

  return -5000;
}

int scoreWord(string s){
  int total = 0;
  for(int i=0; i<s.length(); i++){
    total += scoreLetter(s[i]);
  }

  return total;
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
      int sb = scoreBreakdown(t,numShears-1,numSickles,origlen)+DELPTS;
      if(sb > max){
	max = sb;
	maxS = t;
      }
    }   
  }

  if(numSickles > 0 && s.length() >= 2){
    for(int i=0; i<s.length()-1; i++){
      string t = s.substr(0,i) + s.substr(i+2,s.length()-i-1);
      int sb = scoreBreakdown(t,numShears,numSickles-1,origlen)+2*DELPTS;
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

int main(){
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

  set<string>::iterator it = words.begin();
  for(;it != words.end(); it++){
    int nSick = 1;
    int nSh = (*it).length()-2;
    if((*it).length() <= 2){
      nSick = 0;
      nSh = (*it).length();
    }
    int sb = scoreWord(*it) - scoreBreakdown((*it),nSh,nSick,(*it).length());
    //cout << (*it) << " " << (canDo((*it),nSh,nSick) ? "true" : "false") << endl;
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

  return 0;
}
