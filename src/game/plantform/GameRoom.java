package game.plantform;

import java.util.ArrayList;
import java.util.List;

public class GameRoom {
	
	public static int id=0;
	public int roomId=0;
	public int roomState=0;
	
	public List<GamePlayer> players = new ArrayList<GamePlayer>();
	public GameJudge gameJudge = new GameJudge();
	
	public GameRoom(){
		roomId = id++;
	}
	
	public int addPlayer(GamePlayer player){
		if(players.size()<2) {
			players.add(player);
			return players.size();
		}
		return -1;
	}
	
	public GamePlayer getPlayer(int playerId){
		for(GamePlayer item : players){
			if(item.playerId == playerId){
				return item;
			}
		}
		return null;
	}
	
	public GamePlayer getAnotherPlayer(int playerId){
		for(GamePlayer item : players){
			if(item.playerId != playerId){
				return item;
			}
		}
		return null;
	}
	
	public int removePlayer(int playerId){
		if(roomState>0){
			for(GamePlayer item : players){
				if(item.playerId == playerId){
					players.remove(item);
					roomState--;
					return roomState;
				}
			}
		}
		return -1;
		
	}
	
	
	public void sendMessage(GamePlayer player,String msg){
		player.sendMessage(msg);
	}
	
	public void sendMessage(int playerId,String msg){
		GamePlayer player = getPlayer(playerId);
		player.sendMessage(msg);
	}
	
	

}
