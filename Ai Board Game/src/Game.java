import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Game {

	public static void main(String args[]) throws InterruptedException, FileNotFoundException{
		
		boolean done = false;
		Board newBoard = new Board();
		Move move = new Move("","");
		User user = new User(newBoard);
		
		
		if(user.getInput() == 1){ //keyboard
			if(user.getType()==1){ //User is player 1 (X)
				int k=0;
				newBoard.printBoard();
				while(k==0){
					k = user.userMove(newBoard);
				}
				newBoard.printBoard();
				Thread.sleep(user.getSec()*1000);
			}
			//User is player 2. AI will make the first move.
			int aiType = (user.getType()==1) ?  2 : 1;
			Ai aiPlayer = new Ai(aiType,newBoard);
			
			int l;
			int temp = 0;
			while(!done){
				aiPlayer.makeMove( newBoard, aiType );
				newBoard.printBoard();
				done = newBoard.isDone();
				if (done) break;
				l = 0;
				while( l== 0 ){
					l = user.userMove(newBoard);
				};
				newBoard.printBoard();
				done = newBoard.isDone();
				if (done) break;
				
				Thread.sleep(user.getSec()*1000);
				temp++;

			}
		}
	}
}
