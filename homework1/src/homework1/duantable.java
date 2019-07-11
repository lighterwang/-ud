package homework1;

/**
 * 段表寄存器
 *
 */
public class duantable {
	private int start_address=0 ; //段表始址
	public  int length; //段表长度
	public duantable(int length) {
		super();
		this.length = length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getStart_address() {
		return start_address;
	}

	public void setStart_address(int start_address) {
		this.start_address = start_address;
	}

	public int getLength() {
		return length;
	}
	
	
	
	
}
