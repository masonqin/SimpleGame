package game.plantform;

import java.io.IOException;

import javax.websocket.Session;

public class GamePlayer {
	
	public int playerId;
	public Session websocketSession;
	
	public GamePlayer(int playerId,Session websocketSession) {
		this.playerId = playerId;
		this.websocketSession = websocketSession;
	}
	
	public void sendMessage(String msg) {
		try{
			this.websocketSession.getBasicRemote().sendText(msg);
			
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
