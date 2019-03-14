
public class Result {
	Move move;
	float v;
	public Result(Move m, float eval){
		this.move = m;
		this.v = eval;
	}
	public Move getMove() {
		return move;
	}
	public void setMove(Move move) {
		this.move = move;
	}
	public float getV() {
		return v;
	}
	public void setV(float v) {
		this.v = v;
	}
}
