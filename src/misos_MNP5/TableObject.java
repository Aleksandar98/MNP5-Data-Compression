package misos_MNP5;

public class TableObject implements Comparable<TableObject>{
	int frequency;
	String token;
	char ascii;
	
	public TableObject(int frequency, String token, char ascii) {
		super();
		this.frequency = frequency;
		this.token = token;
		this.ascii = ascii;
	}
	public TableObject(TableObject obj) {

		this.frequency = obj.frequency;
		this.token = obj.token;
		this.ascii = obj.ascii;
	}
	
	public void incFrequency() {
		frequency++;
	}
	
	public void incFrequency(int num) {
		frequency+=num;
	}
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public char getAscii() {
		return ascii;
	}

	public void setAscii(char ascii) {
		this.ascii = ascii;
	}

	@Override
	public String toString() {
		return "TableObject [frequency=" + frequency + ", token=" + token + ", ascii=" + ascii + "]";
	}

	@Override
	public int compareTo(TableObject o) {
		// TODO Auto-generated method stub
		return Integer.compare(frequency, o.getFrequency());
	}

	
}
