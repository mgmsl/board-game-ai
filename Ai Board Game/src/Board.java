
public class Board {
	private float[][] valueBoardforX;
	private float[][] valueBoardforO;
	private char[][] playerBoard;
	private float v;
	private Move move; 
	
	public Move getMove() {
		return move;
	}
	public void setMove(Move move) {
		this.move = move;
	}
	public float getV() {
		return v;
	}
	public void setV(float eval) {
		this.v = eval;
	}
	public Board(Board a){
		this.setV(a.getV());
		playerBoard = new char[6][6];
		valueBoardforO = new float[6][6];
		valueBoardforX = new float[6][6];
		for(int i = 0; i < 6; i++){
			for(int j = 0; j <6; j++){
				playerBoard[i][j] = a.getPlayerBoard()[i][j];
			}
		}
		for(int i = 0; i < 6; i++){
			for(int j = 0; j <6; j++){
				valueBoardforX[i][j] = a.getValueBoardforX()[i][j];
			}
		}
		for(int i = 0; i < 6; i++){
			for(int j = 0; j <6; j++){
				valueBoardforO[i][j] = a.getValueBoardforO()[i][j];
			}
		}
	}
	public Board(){
		v = 0;
		valueBoardforX = new float[][]{
			{ -4, -3, -0.1f,-0.1f, -3,    -4 },
			{-1.5f, -1,   0, 0,   -1,  -1.5f },
			{  0,  0.5f,  1, 1,   0.5f,    0 },
			{0.5f, 1.5f,  2, 2,   1.5f, 0.5f },
			{0.7f,   2,   6, 6,    2,   0.7f },
			{0.7f,   2,5.9f, 5.9f, 2,   0.7f }
		};
		valueBoardforO = new float[][]{
			{-0.7f, -2,-5.9f,-5.9f,-2,  -0.7f },
			{-0.7f, -2,   -6,-6,   -2,  -0.7f },
			{-0.5f,-1.5f, -2,-2,  -1.5f,-0.5f },
			{ -0,  -0.5f, -1,-1,  -0.5f, -0 },
			{1.5f,   1,    0, 0,    1,   1.5f },
			{  4,    3, 0.1f,0.1f,  3,    4 } 
		};
		playerBoard = new char[][]{
			{'E','E','X','X','E','E'},
			{'E','E','X','X','E','E'},
			{'E','E','E','E','E','E'},
			{'E','E','E','E','E','E'},
			{'E','E','O','O','E','E'},
			{'E','E','O','O','E','E'},
		};
	}
	public void setValueBoardforX(float[][] valueBoard) {
		this.valueBoardforX = valueBoard;
	}
	public void setValueBoardforO(float[][] valueBoard) {
		this.valueBoardforO = valueBoard;
	}
	public void setPlayerBoard(char[][] playerBoard) {
		this.playerBoard = playerBoard;
	}
	public float evaluateBoard(){
		float sum = 0;
		for(int k =0;k<6;k++){
			for(int j=0;j<6;j++){
				if(playerBoard[k][j] == 'X'){
					sum += valueBoardforX[k][j];
				}
				if(playerBoard[k][j] == 'O'){
					sum += valueBoardforO[k][j];
				}
			}
		}
		return sum;
	}
	public int getRow(char c){
		if(c=='a') return 1;
		else if (c=='b') return 2;
		else if (c=='c') return 3;
		else if (c=='d') return 4;
		else if (c=='e') return 5;
		else if (c=='f') return 6;
		return 0;
	}
	public void makeMove(int i, String from, String to){ //i=1 for player X, 2 for player O.
		if(i==1){
			if(doable(i,from,to)){
				int fRow = getRow(from.charAt(0)) - 1;
				int fColumn = Character.getNumericValue(from.charAt(1)) - 1;
				
				int tRow = getRow(to.charAt(0)) - 1;
				int tColumn = Character.getNumericValue(to.charAt(1)) - 1;
				playerBoard[fRow][fColumn] = 'E';
				playerBoard[tRow][tColumn] = 'X';
				this.move = new Move(fRow,fColumn,tRow,tColumn);
			}
		}
		else if (i==2){
			if(doable(i,from,to)){
				int fRow = getRow(from.charAt(0)) - 1;
				int fColumn = Character.getNumericValue(from.charAt(1)) - 1;
				
				int tRow = getRow(to.charAt(0)) - 1;
				int tColumn = Character.getNumericValue(to.charAt(1)) - 1;
				this.playerBoard[fRow][fColumn] = 'E';
				this.playerBoard[tRow][tColumn] = 'O';
				this.move = new Move(fRow,fColumn,tRow,tColumn);
			}
		}
	}
	public void makeMove(int i, int fR, int fC, int tR, int tC){ //i=1 for player X, 2 for player O.
		if(i==1){
			if(doable(i,fR,fC,tR,tC)){
				this.playerBoard[fR][fC] = 'E';
				this.playerBoard[tR][tC] = 'X';
				this.move = new Move(fR,fC,tR,tC);
			}
		}
		else if (i==2){
			if(doable(i,fR,fC,tR,tC)){
				this.playerBoard[fR][fC] = 'E';
				this.playerBoard[tR][tC] = 'O';
				this.move = new Move(fR,fC,tR,tC);
			}
		}
	}
	public void makeMove(int type,Move move){
		int fRow = getRow(move.getFrom().charAt(0)) - 1;
		int fColumn = Character.getNumericValue(move.getFrom().charAt(1)) - 1;
		
		int tRow = getRow(move.getTo().charAt(0)) - 1;
		int tColumn = Character.getNumericValue(move.getTo().charAt(1)) - 1;
		if(type==1){
				this.playerBoard[fRow][fColumn] = 'E';
				this.playerBoard[tRow][tColumn] = 'X';
				this.move = move;
		}
		else if (type==2){
				this.playerBoard[fRow][fColumn] = 'E';
				this.playerBoard[tRow][tColumn] = 'O';
				this.move = move;
		}
	}
	public void printBoard(){
		System.out.println("  1 2 3 4 5 6");
		System.out.println(" +------------+");
		for(int i=0; i<6;i++){
			if(i==0) System.out.print("a");
			else if(i==1) System.out.print("b");
			else if(i==2) System.out.print("c");
			else if(i==3) System.out.print("d");
			else if(i==4) System.out.print("e");
			else if (i==5) System.out.print("f");
			System.out.print("|");
			for(int j=0; j<6;j++){
				if(playerBoard[i][j] == 'E'){
					System.out.print("  ");
				}
				else
				    System.out.print(playerBoard[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println(" +------------+");
	}
	public boolean doable(int type,int fR, int fC, int tR, int tC){
		int fRow = fR;
		int fColumn = fC;
		
		int tRow = tR;
		int tColumn = tC;
		if (type == 1){ //X
			if(playerBoard[fRow][fColumn] != 'X') return false; //user must move the piece it possesses.
			else{
				if(!(0<=tRow && tRow<=5) || !(0<=tColumn && tColumn<=5) ){
					return false;
				}
				else if( playerBoard[tRow][tColumn] == 'X' ){
					return false;
				}
				else if (playerBoard[tRow][tColumn] == 'O'){
					return false;
				}
				else return true;
			}
		}
		else{
			if(playerBoard[fRow][fColumn] != 'O') return false; 
			else{
				if(!(0<=tRow && tRow<=5) || !(0<=tColumn && tColumn<=5) ){
					return false;
				}
				else if( playerBoard[tRow][tColumn] == 'O' ){
					return false;
				}
				else if( playerBoard[tRow][tColumn] == 'X' ){
					return false;
				}
				else return true;
			}
		}
	}
	public boolean doable(int type,String from, String to){
		
		int fRow = getRow(from.charAt(0)) - 1;
		int fColumn = Character.getNumericValue(from.charAt(1)) - 1;
		
		int tRow = getRow(to.charAt(0)) - 1;
		int tColumn = Character.getNumericValue(to.charAt(1)) - 1;
		
		if (type == 1){ //X
			if(playerBoard[fRow][fColumn] != 'X') return false;
			else{
				if(!(0<=tRow && tRow<=5) || !(0<=tColumn && tColumn<=5) ){
					return false;
				}
				else if( playerBoard[tRow][tColumn] == 'X' ){
					return false;
				}
				else if( playerBoard[tRow][tColumn] == 'O' ){
					return false;
				}
				else return true;
			}
		}
		else{
			if(playerBoard[fRow][fColumn] != 'O') return false;
			else{
				if(!(0<=tRow && tRow<=5) || !(0<=tColumn && tColumn<=5) ){
					return false;
				}
				else if( playerBoard[tRow][tColumn] == 'O' ){
					return false;
				}
				else if( playerBoard[tRow][tColumn] == 'X' ){
					return false;
				}
				else return true;
			}
		}
	}
	public char[][] getPlayerBoard(){
		return playerBoard;
	}
	public float[][] getValueBoardforX(){
		return valueBoardforX;
	}
	public float[][] getValueBoardforO(){
		return valueBoardforO;
	}
	private String convertToPosition(int r, int c){
		StringBuilder sb=new StringBuilder("");
		if(r==0) sb.append('a');
		else if (r==1) sb.append('b');
		else if (r==2) sb.append('c');
		else if (r==3) sb.append('d');
		else if (r==4) sb.append('e');
		else if (r==5) sb.append('f');
		sb.append(c);
		return sb.toString();
	}
	public boolean isDone(){
		char[][] b = getPlayerBoard();
		if(b[0][2] == 'O' && b[0][3] == 'O' && b[1][2] =='O' && b[1][3] == 'O'){
			System.out.println("Player 2 has won! (O)");
			return true; 
		}
		else if (b[4][2]=='X' && b[4][3]=='X' && b[5][2]=='X' && b[5][3] == 'X'){
			System.out.println("Player 1 has won! (X)");
			return true;
		}
		else return false;
	}
	public boolean checkIfNotOutOfBounds(int i, int j){
		if( 0 <= i && 5 >= i && 0 <= j && 5 >= j) return true;
		return false;
	}
}
