package cn.upc.model;

import java.io.Serializable;
 
public class PacketBean implements Serializable {

     private String packetType;
     private Object data;
     private String studentID;
     
     public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getPacketType() {
         return packetType;
     }
     public void setPacketType(String packetType) {
         this.packetType = packetType;
     }
     public Object getData() {
         return data;
     }
     public void setData(Object data) {
         this.data = data;
     }
     
 }
