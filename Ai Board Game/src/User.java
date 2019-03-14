import java.util.Scanner;

public class User {
	private int type;
	private Board br;
	private int input;
	private int sec;
	private Move move;
		
	public User(Board br){
		int in,in2,in3;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter which player you will be (1 or 2)");
		in = sc.nextInt();
		this.type = in;
		System.out.println("Select input provider. Enter 1 for keyboard, 2 for file.(Enter 1)"); //1 for playing first
		in2= sc.nextInt();
		this.input = in2;
		System.out.println("Enter the time that the current configuration will stay on the screen(in seconds)");
		in3 = sc.nextInt();
		this.sec = in3;
		this.br = br;
	}
	
	public int userMove(Board game){
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose a piece to move:");
		String sIn= sc.next();
		int t = 0;
		while ( t == 0 ){
			if(sIn.length()==2){
				t = 1;
				break;
			}
			System.out.println("Try again.Choose a piece to move:");
			sIn= sc.next();
		}
		System.out.println("Choose the new position for " + sIn + ":");
		String sIn2= sc.next();
		int y = 0;
		while ( y == 0 ){
			if(sIn2.length()==2){ 
				y = 1; 
				break;
				}
			System.out.println("Try again.Choose the new position for " + sIn + ":");
			sIn2= sc.next();
		}
		if ( game.doable(type,sIn, sIn2)) {
			move = new Move(sIn,sIn2);
			game.makeMove(type, sIn, sIn2);
			return 1;
		}
		else System.out.println("Move is not valid. Try again.");return 0;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Board getBoard() {
		return br;
	}

	public void setBoard(Board br) {
		this.br = br;
	}

	public int getInput() {
		return input;
	}

	public void setInput(int input) {
		this.input = input;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}
}
