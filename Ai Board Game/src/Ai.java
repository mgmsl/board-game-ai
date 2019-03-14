import java.util.ArrayList;

public class Ai {
	private int type;
	private Board board;
	private final int depth=8;
	private Move lastMove;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Move getLastMove() {
		return lastMove;
	}
	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}
	public int getDepth() {
		return depth;
	}
	public Ai(int ty, Board br){ //if t == 1, ai will be X(max), otherwise will be O(min).
		this.type = ty;
		this.board = br;
		lastMove = new Move("a1","f5");
	}
	public boolean checkIfNotOutOfBounds(int i, int j){
		if( 0 <= i && 5 >= i && 0 <= j && 5 >= j) return true;
		return false;
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
	private ArrayList<Move> generateMoves(Board br,int type){ //user type.	
		Board boardCopy = new Board(br);
		ArrayList<Move> moveList = new ArrayList<>();
		
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if(type==1){ //X (max)
					if ( convertToPosition(i,j).equals(lastMove.getTo())  ){
						if( boardCopy.getPlayerBoard()[i][j]=='X' ){
							if( boardCopy.doable(type,i, j, i, j-1) ){  
								if ( !convertToPosition(i,j-1).equals(lastMove.getFrom())  )
									moveList.add( new Move(i, j, i, j-1));
							}
							if( boardCopy.doable(type,i, j, i-1, j) ){
								if ( !convertToPosition(i-1,j).equals(lastMove.getFrom())  )
									moveList.add( new Move(i, j, i-1, j));
							}
							if( boardCopy.doable(type,i, j, i, j+1) ){
								if ( !convertToPosition(i,j+1).equals(lastMove.getFrom())  )
									moveList.add( new Move(i, j, i, j+1));
							}
							if( boardCopy.doable(type,i, j, i+1, j) ){
								if ( !convertToPosition(i+1,j).equals(lastMove.getFrom())  )
									moveList.add( new Move(i, j, i+1, j));
							}
							if( checkIfNotOutOfBounds(i,j-1) ){  
								if(boardCopy.getPlayerBoard()[i][j-1] == 'O' || boardCopy.getPlayerBoard()[i][j-1] == 'X'){
									if( boardCopy.doable(type,i, j, i, j-2) ){
										if( checkIfNotOutOfBounds(i,j-3) ){
											if(boardCopy.getPlayerBoard()[i][j-3] == 'O' || boardCopy.getPlayerBoard()[i][j-3] == 'X' ){
												if( boardCopy.doable(type,i, j, i, j-4) ){
													if ( !convertToPosition(i,j-4).equals(lastMove.getFrom())  )
														moveList.add( new Move(i, j, i, j-4));
												}
												else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom())  )
													moveList.add( new Move(i, j, i, j-2));
											}
											if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j-2));
										}
										else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j-2));	
									}
								}
							}
							if( checkIfNotOutOfBounds(i-1,j) ){
								if(boardCopy.getPlayerBoard()[i-1][j] == 'O' || boardCopy.getPlayerBoard()[i-1][j] == 'X'){
									if( boardCopy.doable(type,i, j, i-2, j) ){
										if( checkIfNotOutOfBounds(i-3,j) ){
											if(boardCopy.getPlayerBoard()[i-3][j] == 'O' || boardCopy.getPlayerBoard()[i-3][j] == 'X'){
												if( boardCopy.doable(type,i, j, i-4, j) )
													if ( !convertToPosition(i,j-4).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i-4, j));
												else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i-2, j));
											}
											else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
													moveList.add( new Move(i, j, i-2, j));	
										}
										else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom())) 
												moveList.add( new Move(i, j, i-2, j));	
									}
								}
								
							}
							if( checkIfNotOutOfBounds(i,j+1) ){
								if(boardCopy.getPlayerBoard()[i][j+1] == 'O' || boardCopy.getPlayerBoard()[i][j+1] == 'X'){
									if( boardCopy.doable(type,i, j, i, j+2) ){
										if( checkIfNotOutOfBounds(i,j+3) ){
											if(boardCopy.getPlayerBoard()[i][j+3] == 'O' || boardCopy.getPlayerBoard()[i][j+3] == 'O'){
												if( boardCopy.doable(type,i, j, i, j+4) ) 
													if ( !convertToPosition(i,j+4).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i, j+4));
												else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i, j+2));
											}
											else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j+2));	
										}
										else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
											moveList.add( new Move(i, j, i, j+2));	
									}
								}
							}
							if( checkIfNotOutOfBounds(i+1,j) ){
								if( boardCopy.getPlayerBoard()[i+1][j] == 'O' || boardCopy.getPlayerBoard()[i+1][j] == 'X'){
									if( boardCopy.doable(type,i, j, i+2, j) ){
										if( checkIfNotOutOfBounds(i+3,j) ){
											if(boardCopy.getPlayerBoard()[i+3][j] == 'O' || boardCopy.getPlayerBoard()[i+3][j] == 'X'){
												if( boardCopy.doable(type,i, j,i+4, j) ) 
													if ( !convertToPosition(i+4,j).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i+4, j));	
												else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom()))
													moveList.add( new Move(i, j, i+2, j));	
											}
											else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i+2, j));	
										}
										else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom())) 
											moveList.add( new Move(i, j, i+2, j));	
									}
								}
								
							}
							
						}
					}
					else{ 
						if( boardCopy.getPlayerBoard()[i][j]=='X' ){
							if( boardCopy.doable(type,i, j, i, j-1) ){
								moveList.add( new Move(i, j, i, j-1));
							}
							if( boardCopy.doable(type,i, j, i-1, j) ){
								moveList.add( new Move(i, j, i-1, j));
							}
							if( boardCopy.doable(type,i, j, i, j+1) ){
								moveList.add( new Move(i, j, i, j+1));
							}
							if( boardCopy.doable(type,i, j, i+1, j) ){
								moveList.add( new Move(i, j, i+1, j));
							}
							if( checkIfNotOutOfBounds(i,j-1) ){
								if(boardCopy.getPlayerBoard()[i][j-1] == 'O' || boardCopy.getPlayerBoard()[i][j-1] == 'X'){
									if( boardCopy.doable(type,i, j, i, j-2) ){
										if( checkIfNotOutOfBounds(i,j-3) ){
											if(boardCopy.getPlayerBoard()[i][j-3] == 'O' || boardCopy.getPlayerBoard()[i][j-3] == 'X'){
												if( boardCopy.doable(type,i, j, i, j-4) ) 
													moveList.add( new Move(i, j, i, j-4));
												else
													moveList.add( new Move(i, j, i, j-2));
											}
											moveList.add( new Move(i, j, i, j-2));
										}
										else moveList.add( new Move(i, j, i, j-2));	
									}
								}
							}
							if( checkIfNotOutOfBounds(i-1,j) ){
								if(boardCopy.getPlayerBoard()[i-1][j] == 'O' || boardCopy.getPlayerBoard()[i-1][j] == 'X'){
									if( boardCopy.doable(type,i, j, i-2, j) ){
										if( checkIfNotOutOfBounds(i-3,j) ){
											if(boardCopy.getPlayerBoard()[i-3][j] == 'O' || boardCopy.getPlayerBoard()[i-3][j] == 'X'){
												if( boardCopy.doable(type,i, j, i-4, j) ) 
													moveList.add( new Move(i, j, i-4, j));
												else
													moveList.add( new Move(i, j, i-2, j));
											}
											else 
												moveList.add( new Move(i, j, i-2, j));	
										}
										else moveList.add( new Move(i, j, i-2, j));	
									}
								}
								
							}
							if( checkIfNotOutOfBounds(i,j+1) ){
								if(boardCopy.getPlayerBoard()[i][j+1] == 'O' || boardCopy.getPlayerBoard()[i][j+1] == 'X'){
									if( boardCopy.doable(type,i, j, i, j+2) ){
										if( checkIfNotOutOfBounds(i,j+3) ){
											if(boardCopy.getPlayerBoard()[i][j+3] == 'O' || boardCopy.getPlayerBoard()[i][j+3] == 'X'){
												if( boardCopy.doable(type,i, j, i, j+4) ) 
													moveList.add( new Move(i, j, i, j+4));
												else
													moveList.add( new Move(i, j, i, j+2));
											}
											else
												moveList.add( new Move(i, j, i, j+2));	
										}
										else moveList.add( new Move(i, j, i, j+2));	
									}
								}
							}
							if( checkIfNotOutOfBounds(i+1,j) ){
								if( boardCopy.getPlayerBoard()[i+1][j] == 'O' || boardCopy.getPlayerBoard()[i+1][j] == 'X'){
									if( boardCopy.doable(type,i, j, i+2, j) ){
										if( checkIfNotOutOfBounds(i+3,j) ){
											if(boardCopy.getPlayerBoard()[i+3][j] == 'O' || boardCopy.getPlayerBoard()[i+3][j] == 'X'){
												if( boardCopy.doable(type,i, j,i+4, j) ) 
													moveList.add( new Move(i, j, i+4, j));	
												else
													moveList.add( new Move(i, j, i+2, j));	
											}
											else
												moveList.add( new Move(i, j, i+2, j));	
										}
										else moveList.add( new Move(i, j, i+2, j));	
									}
								}
								
							}
							
						}
					}
					
				}
				else{ // O (min)
					if(  convertToPosition(i,j).equals(lastMove.getTo()) ){
						if( boardCopy.getPlayerBoard()[i][j]=='O' ){
							if( boardCopy.doable(type,i, j, i, j-1) ){ 
								if ( !convertToPosition(i,j-1).equals(lastMove.getFrom()) )
									moveList.add( new Move(i, j, i, j-1));
							}
							if( boardCopy.doable(type,i, j, i-1, j) ){
								if ( !convertToPosition(i-1,j).equals(lastMove.getFrom()) )
									moveList.add( new Move(i, j, i-1, j));
							}
							if( boardCopy.doable(type,i, j, i, j+1) ){
								if ( !convertToPosition(i,j+1).equals(lastMove.getFrom()) )
									moveList.add( new Move(i, j, i, j+1));
							}
							if( boardCopy.doable(type,i, j, i+1, j) ){
								if ( !convertToPosition(i+1,j).equals(lastMove.getFrom()) )
									moveList.add( new Move(i, j, i+1, j));
							}
							if( checkIfNotOutOfBounds(i,j-1) ){  
								if(boardCopy.getPlayerBoard()[i][j-1] == 'O' || boardCopy.getPlayerBoard()[i][j-1] == 'X'){
									if( boardCopy.doable(type,i, j, i, j-2) ){
										if( checkIfNotOutOfBounds(i,j-3) ){
											if(boardCopy.getPlayerBoard()[i][j-3] == 'O' || boardCopy.getPlayerBoard()[i][j-3] == 'X'){
												if( boardCopy.doable(type,i, j, i, j-4) ){
													if ( !convertToPosition(i,j-4).equals(lastMove.getFrom())  )
														moveList.add( new Move(i, j, i, j-4));
												}
												else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom())  )
													moveList.add( new Move(i, j, i, j-2));
											}
											if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j-2));
										}
										else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j-2));	
									}
								}
							}
							if( checkIfNotOutOfBounds(i-1,j) ){
								if(boardCopy.getPlayerBoard()[i-1][j] == 'O' || boardCopy.getPlayerBoard()[i-1][j] == 'X'){
									if( boardCopy.doable(type,i, j, i-2, j) ){
										if( checkIfNotOutOfBounds(i-3,j) ){
											if(boardCopy.getPlayerBoard()[i-3][j] == 'O' || boardCopy.getPlayerBoard()[i-3][j] == 'X'){
												if( boardCopy.doable(type,i, j, i-4, j) )
													if ( !convertToPosition(i,j-4).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i-4, j));
												else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i-2, j));
											}
											else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
													moveList.add( new Move(i, j, i-2, j));	
										}
										else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom())) 
												moveList.add( new Move(i, j, i-2, j));	
									}
								}
								
							}
							if( checkIfNotOutOfBounds(i,j+1) ){
								if(boardCopy.getPlayerBoard()[i][j+1] == 'O' || boardCopy.getPlayerBoard()[i][j+1] == 'X'){
									if( boardCopy.doable(type,i, j, i, j+2) ){
										if( checkIfNotOutOfBounds(i,j+3) ){
											if(boardCopy.getPlayerBoard()[i][j+3] == 'O' || boardCopy.getPlayerBoard()[i][j+3] == 'X'){
												if( boardCopy.doable(type,i, j, i, j+4) ) 
													if ( !convertToPosition(i,j+4).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i, j+4));
												else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i, j+2));
											}
											else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j+2));	
										}
										else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
											moveList.add( new Move(i, j, i, j+2));	
									}
								}
							}
							if( checkIfNotOutOfBounds(i+1,j) ){
								if( boardCopy.getPlayerBoard()[i+1][j] == 'O' || boardCopy.getPlayerBoard()[i+1][j] == 'X'){
									if( boardCopy.doable(type,i, j, i+2, j) ){
										if( checkIfNotOutOfBounds(i+3,j) ){
											if(boardCopy.getPlayerBoard()[i+3][j] == 'O' || boardCopy.getPlayerBoard()[i+3][j] == 'X'){
												if( boardCopy.doable(type,i, j,i+4, j) ) 
													if ( !convertToPosition(i+4,j).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i+4, j));	
												else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom()))
													moveList.add( new Move(i, j, i+2, j));	
											}
											else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i+2, j));	
										}
										else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom())) 
											moveList.add( new Move(i, j, i+2, j));	
									}
								}
								
							}
						}
					}
					else{
						if( boardCopy.getPlayerBoard()[i][j]=='O' ){
							if( boardCopy.doable(type,i, j, i, j-1) ){
								moveList.add( new Move(i, j, i, j-1));
							}
							if( boardCopy.doable(type,i, j, i-1, j) ){
								moveList.add( new Move(i, j, i-1, j));
							}
							if( boardCopy.doable(type,i, j, i, j+1) ){
								moveList.add( new Move(i, j, i, j+1));
							}
							if( boardCopy.doable(type,i, j, i+1, j) ){
								moveList.add( new Move(i, j, i+1, j));
							}
							if( checkIfNotOutOfBounds(i,j-1) ){
								if(boardCopy.getPlayerBoard()[i][j-1] == 'O' || boardCopy.getPlayerBoard()[i][j-1] == 'X'){
									if( boardCopy.doable(type,i, j, i, j-2) ){
										if( checkIfNotOutOfBounds(i,j-3) ){
											if(boardCopy.getPlayerBoard()[i][j-3] == 'O' || boardCopy.getPlayerBoard()[i][j-3] == 'X'){
												if( boardCopy.doable(type,i, j, i, j-4) ){
													if ( !convertToPosition(i,j-4).equals(lastMove.getFrom())  )
														moveList.add( new Move(i, j, i, j-4));
												}
												else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom())  )
													moveList.add( new Move(i, j, i, j-2));
											}
											if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j-2));
										}
										else if ( !convertToPosition(i,j-2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j-2));	
									}
								}
							}
							if( checkIfNotOutOfBounds(i-1,j) ){
								if(boardCopy.getPlayerBoard()[i-1][j] == 'O' || boardCopy.getPlayerBoard()[i-1][j] == 'X'){
									if( boardCopy.doable(type,i, j, i-2, j) ){
										if( checkIfNotOutOfBounds(i-3,j) ){
											if(boardCopy.getPlayerBoard()[i-3][j] == 'O' || boardCopy.getPlayerBoard()[i-3][j] == 'X'){
												if( boardCopy.doable(type,i, j, i-4, j) )
													if ( !convertToPosition(i-4,j).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i-4, j));
												else if ( !convertToPosition(i-2,j).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i-2, j));
											}
											else if ( !convertToPosition(i-2,j).equals(lastMove.getFrom()))
													moveList.add( new Move(i, j, i-2, j));	
										}
										else if ( !convertToPosition(i-2,j).equals(lastMove.getFrom())) 
												moveList.add( new Move(i, j, i-2, j));	
									}
								}
								
							}
							if( checkIfNotOutOfBounds(i,j+1) ){
								if(boardCopy.getPlayerBoard()[i][j+1] == 'O' || boardCopy.getPlayerBoard()[i][j+1] == 'X'){
									if( boardCopy.doable(type,i, j, i, j+2) ){
										if( checkIfNotOutOfBounds(i,j+3) ){
											if(boardCopy.getPlayerBoard()[i][j+3] == 'O' || boardCopy.getPlayerBoard()[i][j+3] == 'X'){
												if( boardCopy.doable(type,i, j, i, j+4) ) 
													if ( !convertToPosition(i,j+4).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i, j+4));
												else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i, j+2));
											}
											else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i, j+2));	
										}
										else if ( !convertToPosition(i,j+2).equals(lastMove.getFrom()))
											moveList.add( new Move(i, j, i, j+2));	
									}
								}
							}
							if( checkIfNotOutOfBounds(i+1,j) ){
								if( boardCopy.getPlayerBoard()[i+1][j] == 'O' || boardCopy.getPlayerBoard()[i+1][j] == 'X'){
									if( boardCopy.doable(type,i, j, i+2, j) ){
										if( checkIfNotOutOfBounds(i+3,j) ){
											if(boardCopy.getPlayerBoard()[i+3][j] == 'O' || boardCopy.getPlayerBoard()[i+3][j] == 'X'){
												if( boardCopy.doable(type,i, j,i+4, j) ) 
													if ( !convertToPosition(i+4,j).equals(lastMove.getFrom()))
														moveList.add( new Move(i, j, i+4, j));	
												else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom()))
													moveList.add( new Move(i, j, i+2, j));	
											}
											else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom()))
												moveList.add( new Move(i, j, i+2, j));	
										}
										else if ( !convertToPosition(i+2,j).equals(lastMove.getFrom())) 
											moveList.add( new Move(i, j, i+2, j));	
									}
								}
								
							}
						}
					}
				}
			}
		}
		//possible moves are created. They are moves that are doable on current root of tree.
		return moveList;
	}
	
	private Move alphaBetaSearch(int type){
		Board node = this.board;
		int depth = 0;
		int v;
		Result result;
		if(type == 1){ //AI is player 1. (MAX)
			result = maxValue(new Move(0,0,0,0),type,node,depth,-1000,+1000);
		}
		else{
			result = minValue(new Move(0,0,0,0),type,node, depth,-1000,+1000);
		}
		
		ArrayList<Move> moveList = generateMoves(node,type);
		
		for(Move move : moveList){
			
			Board newBoard = new Board(this.board);
			newBoard.makeMove(type, move);
			if (newBoard.getMove().getFrom().equals(result.getMove().getFrom()) && newBoard.getMove().getTo().equals(result.getMove().getTo())){
				System.out.println("The chosen move:" + move.getFrom() + move.getTo());
				return move;
			}
		}
		System.out.println("No move found. Returning the last move.");
		return lastMove;
		
	}
	private Result maxValue(Move mv,int type,Board node,int depth, float alpha, float beta){
		if (depth == this.depth){
			float eval = 0;
			eval = node.evaluateBoard();
			
			node.setV(eval);
			Result result = new Result(mv,eval);
			return result;
		}
		
		ArrayList<Move> moveList = generateMoves(node,type);
		int v = -1000;
		
		Result result = new Result(new Move(0,0,0,0),v);
		
		for(Move move : moveList){
			Board newBoard = new Board(node);
			newBoard.makeMove(type, move);
			if(type == 1){
				Result tempR = minValue(move,2,newBoard,depth+1,alpha,beta);
				if (result.getV() < tempR.getV()){
					result.setV( tempR.getV() );
					result.setMove(move);
				}
			}
			if (result.getV() >= beta){
				return result;
			}
			alpha = Math.max(alpha, result.getV());
		}
		return result;
	}
	private Result minValue(Move mv,int type,Board node,int depth, float alpha, float beta){
		if(depth==this.depth){
			float eval = 0;
			eval = node.evaluateBoard();
			node.setV(eval);
			Result result = new Result(mv,eval);
			return result;
		}
		
		ArrayList<Move> moveList = generateMoves(node,type);
		
		int v = +1000;
		//char[][] pl = node.getPlayerBoard();
		Result result = new Result(new Move(0,0,0,0),v);
		for(Move move : moveList){
			Board newBoard = new Board(node);
			newBoard.makeMove(type, move);  //burda 			
			if(type == 2){
				Result tempR = maxValue(move,1,newBoard,depth+1,alpha,beta);
				if (result.getV() > tempR.getV()){
					result.setV( tempR.getV() );
					result.setMove(move);
				}
			}
			if (result.getV() <= alpha){
				return result;
			}
			beta = Math.min(alpha, result.getV());
		}
		return result;
	}

	public void makeMove(Board br,int type){
		Move theChosenOne = alphaBetaSearch(type);
		System.out.println("The move chosen by ai is: " + theChosenOne.getFrom() + " " + theChosenOne.getTo());
		br.makeMove(type, theChosenOne);
		setLastMove(theChosenOne);
		System.out.println("The move has been made by AI.");
	}
}
