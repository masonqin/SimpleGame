package game.websocket;


import game.plantform.GamePlantform;

import java.io.IOException;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.Session;




@ServerEndpoint("/actions")
public class WebsocketServer {
	
	//private static int onlineCount = 0;
	private Session session;
	private int playerId;
	//private static List<WebsocketServer> webSockets = new ArrayList<WebsocketServer>();
	
	@OnOpen
	public void onOpen(Session session){
		this.session = session;

		//webSockets.add(this);
		//addOnlineCount();
		//System.out.println("New player get in,total player:"+getOnlineCount());
	}
	
	@OnMessage
	public void onMessage(String message,Session session){
		
		GamePlantform.dispatchMsg(message,session);
		
	}
	
	@OnError
	public void onError(Session session,Throwable error){
		System.out.println("Errors in websocket");
		error.printStackTrace();
	}

	@OnClose
	public void onClose(){
		//webSockets.remove(this);
		//subOnlineCount();
		//System.out.println("One player get out,total player:"+getOnlineCount());
	}
	
	
//	public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
// 
//    public static synchronized void addOnlineCount() {
//        WebsocketServer.onlineCount++;
//    }
//     
//    public static synchronized void subOnlineCount() {
//    	WebsocketServer.onlineCount--;
//    }
    
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    

	

}
