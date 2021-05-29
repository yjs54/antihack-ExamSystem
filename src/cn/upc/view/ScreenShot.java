package cn.upc.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;

import javax.imageio.ImageIO;

import cn.upc.model.PacketBean;




/**
 *
 * @author yjs
 */
public class ScreenShot extends Thread {
	private int flag = 0;
    private String lastMD5 = "";
    static boolean uploadFlag=false;
    
    String ip="127.0.0.1";
    int port=8888;
    
    public boolean isNew(String md5) {//判断画面是否重复
        if (!lastMD5.equals(md5)) {
          //  System.out.println(lastMD5+"   "+md5);
            lastMD5 = md5;
            return true;
        } else {
            return false;
        }
    }

    public void run() {
       // String fb=GetInfoFromWeb.feedbackValue("/java/getServerInfo.php");
       // String ip=fb.split(",")[0];
       // int port=Integer.parseInt(fb.split(",")[1]);
        try {
            Socket client = new Socket(ip, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            MessageDigest digest = MessageDigest.getInstance("MD5");
            int count=0;//重复相同帧的次数
            while (uploadFlag) {
                byte[] datas = snapShotStream(2).toByteArray();
                digest.update(datas);
                BigInteger bigInt = new BigInteger(1, digest.digest());
                String md5 = bigInt.toString(16);
                if (isNew(md5)) {
                    PacketBean data = new PacketBean();
                    data.setPacketType("sendshots");
                    data.setStudentID(LoginFrame.username);//还需设置studentID
                    data.setData(datas);
                    objectOutputStream.writeObject(data);
                    objectOutputStream.flush();
                    count=0;
                }else{
                    count++;
                }
                if(count==5*60*10){// 五分钟的帧都相同，说明五分钟页面没有动，断开连接
                    System.exit(0); //要不要退出系统？？？？
                    break;
                    
                }
                sleep(75);///录屏频率，需要与服务器端保持一致
            }
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public BufferedImage zipImage(BufferedImage src,int w,int h,int rate){
        int width=w/rate;
        int height=h/rate;
        BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        tag.getGraphics().drawImage(image, 0, 0, width, height, null);
        return tag;
    }
    public ByteArrayOutputStream snapShotStream(int rate) {    //将截屏写入数据流       
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            // 拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0, 0,(int) d.getWidth(), (int) d.getHeight()));
            // 将screenshot对象写入输出流
            ImageIO.write(zipImage(screenshot,(int) d.getWidth(),(int) d.getHeight(),rate), "jpg", out);
            System.out.println("截图了");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return out;
    }
    
}
