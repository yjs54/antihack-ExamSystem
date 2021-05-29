package cn.upc.model;
/**
 * 进程实体
 * @author green
 *
 */

public class Process {

	private String processName;
	
	public Process() {
		
	}
	
	public Process(String processName) {
		super();
		this.processName = processName;
	}


	public String getprocessName() {
		return processName;
	}
	public void setprocessName(String i) {
		this.processName = i;
	}

}
