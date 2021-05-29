package cn.upc.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class MainFrame extends JFrame {
	
    public static void main(String[] args){	
			}
    
    public MainFrame(){
    	
    	addWindowFocusListener(new WindowFocusListener(){
			@Override
			public void windowLostFocus(WindowEvent e){
				//System.out.println("����ʧȥ�˽���");
				
				new ScreenShot().start();
				ScreenShot.uploadFlag = true;
			}
			@Override
			public void windowGainedFocus(WindowEvent e){
				//System.out.println("���ڻ���˽���");
				ScreenShot.uploadFlag = false;
			
			}
			
		});
    }
    public void enterWebPage(String url){
    	Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        MainFrame frame = new MainFrame();
        frame.setTitle("����ϵͳ");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(browserView, BorderLayout.CENTER);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        browser.loadURL(url);
        
    }
}
