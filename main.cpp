#include<iostream>
#include<fstream>
using namespace std;

int minNum, maxNum, lastIndex;

bool validInt(int x){
	if(x < 0)
		return false;
	else
		return true;
}

void findMinMax(ifstream& readFile){
	int current;
	if(!readFile.eof()){
		readFile >> current;
		minNum = current;
		maxNum = current;
	}
	else{
		cout<<"Please enter integers for Evaluation.\n";
		exit(1);
	}
	while(!readFile.eof()){											
    	readFile >> current;
    	if(validInt(current)){
    		if(current < minNum){
    			minNum = current;
			}
			if(current > maxNum){
				maxNum = current;
			}
		}
		else{
			cout<<"Only positive integers.\n";
			exit(1);
		}	
	}
}

class bucketArray{
	private:
		int* dArray;
	public:
		bucketArray(int minN, int maxN){
			lastIndex = maxN - minN + 1;
			dArray = new int[lastIndex]();
		}
		bucketArray(){
			dArray = new int[0];
		}
		int getIndex(int item){
			return item - minNum;
		}
		void storesInt(ifstream& readFile){
			readFile.seekg(0, readFile.beg);
			int current;
			while(!readFile.eof()){
				readFile >> current;
				int i = getIndex(current);
				++dArray[i];
			}
		}
		void printSortData(ofstream& writeFile){
			for(int j=0; j<lastIndex; j++){
				while(dArray[j] != 0){
					writeFile << j + minNum <<endl;
					--dArray[j];
				}
			}
		}
};

int main(int argc, char** argv){
	
	if (argc != 3) {																	//error from invalid number of arguments
        cerr << "Please and only specify an input and output text file. Try again..\n"<<endl;
        exit(1);
    }
    
	ifstream read;
	ofstream write;
	read.open(argv[1]);
	write.open(argv[2]);
	
	read.seekg(0, std::ios::end);
	if (read.tellg() == 0){
		cout << "File is empty." << endl;
		exit(1);
	}
	read.seekg(0, read.beg);	
    findMinMax(read);
	bucketArray container(minNum, maxNum);
    container.storesInt(read);
    container.printSortData(write);
	read.close();
	write.close();
	return 0;
}


