
public class Move {
	private String from;
	private String to;
	
	public Move(String f, String t){
		this.from = f;
		this.to = t;
	}
	public Move(int i,int j, int k, int l){
		StringBuilder sb=new StringBuilder("");
		if(i==0) sb.append("a");
		else if (i==1) sb.append("b");
		else if (i==2) sb.append("c");
		else if (i==3) sb.append("d");
		else if (i==4) sb.append("e");
		else if (i==5) sb.append("f");
		sb.append(j+1);
		this.from = sb.toString();
		
		StringBuilder cc=new StringBuilder("");
		if(k==0) cc.append("a");
		else if (k==1) cc.append("b");
		else if (k==2) cc.append("c");
		else if (k==3) cc.append("d");
		else if (k==4) cc.append("e");
		else if (k==5) cc.append("f");
		cc.append(l+1);
		this.to = cc.toString();
		
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
