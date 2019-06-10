#include "TicTacToeApp.h"

using namespace boost::algorithm;

JNIEXPORT jstring JNICALL Java_TicTacToeApp_Random(JNIEnv *env, jobject thisObject, jstring jtab)
{
	srand(time(NULL));
	const char *cstr = env->GetStringUTFChars(jtab, NULL);
	std::string tab = std::string(cstr);
	std::vector<std::string> tokens;

	split(tokens, tab, is_any_of(" "));
	
	std::string minimax[4][4];
	int liczba=0;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			minimax[i][j] = tokens[liczba];
			liczba++;
		
		}
	}
	
	
	jint x = (rand() % 3) ;
	jint y = (rand() % 3);

	while (minimax[x][y] == "X" || minimax[x][y] == "O") {
	
		x = (rand() % 3);
		y = (rand() % 3);
	}
	std::string out_x;
	std::stringstream ss1;
	ss1 << x;
	out_x = ss1.str();

	std::string out_y;
	std::stringstream ss2;
	ss2 << y;
	out_y = ss2.str();

	std::string s = out_x + " " + out_y;
	//printf("Wartosc: \n",cstr);
	
	return env->NewStringUTF(s.c_str());
}

JNIEXPORT jstring JNICALL Java_TicTacToeApp_MyAlg(JNIEnv *env, jobject, jstring jtab)
{
	srand(time(NULL));

	const char *cstr = env->GetStringUTFChars(jtab, NULL);
	std::string tab = std::string(cstr);
	std::vector<std::string> tokens;

	split(tokens, tab, is_any_of(" "));

	std::string minimax[4][4];
	int liczba = 0;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			minimax[i][j] = tokens[liczba];
			liczba++;

		}
	}
	
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (minimax[j][i] == "")
			{
				minimax[j][i] = "X";
				if (wygrana(minimax, "X")) {
					
					std::string out_j;
					std::stringstream ss1;
					ss1 << j;
					out_j = ss1.str();

					std::string out_i;
					std::stringstream ss2;
					ss2 << i;
					out_i = ss2.str();

					std::string s = out_i + " " + out_j;
					
					return env->NewStringUTF(s.c_str());
				}
					
				minimax[j][i] = "";
			}
		}
	}


	int x = (rand() % 3);
	int y = (rand() % 3);

	while (minimax[x][y] == "X" || minimax[x][y] == "O") {

		x = (rand() % 3);
		y = (rand() % 3);
	}
	std::string out_x;
	std::stringstream ss1;
	ss1 << x;
	out_x = ss1.str();

	std::string out_y;
	std::stringstream ss2;
	ss2 << y;
	out_y = ss2.str();

	std::string s = out_x + " " + out_y;
	
	
	return env->NewStringUTF(s.c_str());
}
bool wygrana(string t[4][4], string  g) {
	bool test;

	test = false;
	
	for (int y = 0; y < 4; y++) {
		test |= ((t[0][y] == g) && (t[1][y] == g) && (t[2][y] == g));
		test |= ((t[1][y] == g) && (t[2][y] == g) && (t[3][y] == g));
	}

	for (int x = 0; x < 4; x++) {
		test |= ((t[x][0] == g) && (t[x][1] == g) && (t[x][2] == g));
		test |= ((t[x][1] == g) && (t[x][2] == g) && (t[x][3] == g));
	}

	test |= ((t[0][0] == g) && (t[1][1] == g) && (t[2][2] == g));
	test |= ((t[2][0] == g) && (t[1][1] == g) && (t[0][2] == g));
	test |= ((t[1][0] == g) && (t[2][1] == g) && (t[3][2] == g));
	test |= ((t[3][0] == g) && (t[2][1] == g) && (t[1][2] == g));
	test |= ((t[0][1] == g) && (t[1][2] == g) && (t[2][3] == g));
	test |= ((t[2][1] == g) && (t[1][2] == g) && (t[0][3] == g));
	test |= ((t[1][1] == g) && (t[2][2] == g) && (t[3][3] == g));
	test |= ((t[3][1] == g) && (t[2][2] == g) && (t[1][3] == g));

	if (test)
	{
		return true;
	}
	return false;
}