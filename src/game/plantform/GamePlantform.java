package game.plantform;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GamePlantform {
	
	public static List<GamePlayer> players = new ArrayList<GamePlayer>();
	public static Map<Integer ,GamePlayer> playerMap = new HashMap<Integer ,GamePlayer>();
	public static List<GameRoom> gameRooms = new LinkedList<GameRoom>();
	public static Map<Integer,Integer> playerRoom = new HashMap<Integer,Integer>();
	//public static int ret = initGameRooms();
	
	public static int initGameRooms(){
		for(int i=0;i<10;i++){
			gameRooms.add(new GameRoom());
		}
		return 1;
	}
	
	public static void receivePlayer(int playerId,Session session){
		GamePlayer player;
		if(playerMap.get(playerId) != null){
			player = playerMap.get(playerId);
			player.websocketSession = session;
		} else {
			player = new GamePlayer(playerId,session);
			players.add(player);
			playerMap.put(playerId, player);
		}
	}
	
	public static int assignPlayerToRoom(int playerId ,int roomId){
		
		if(playerRoom.get(playerId)!=null){
			return -2;
		}
		
		int ret = getRoomById(roomId).addPlayer(playerMap.get(playerId));	
		if(ret>0){
			playerRoom.put(playerId, roomId);
			playerMap.get(playerId).sendMessage("get room success");
		}
		return ret;
	}
	
	public static void createRoom(){
		gameRooms.add(new GameRoom());
	}
	
	public static int getPlayerRoom(int playerId){	
		return playerRoom.get(playerId);
	}
	
	public static void removePlayer(int playerId) {
		playerMap.remove(playerId);
		getRoomById(getPlayerRoom(playerId)).removePlayer(playerId);
	}
	
	public static void dispatchMsg(String message,Session session){
		int playerid;
		int roomid;
		Map<String,String> msgmap = parseData(message);
		Map<String,String> remap = new HashMap<String,String>();
		String msg;
		
		switch(msgmap.get("action")){
		
			case "getplant":
				playerid = Integer.parseInt(msgmap.get("playerid"));
				receivePlayer(playerid,session);
				System.out.println("Get in Plant: " + "PlayerID: " + playerid);
				break;
				
			case "getroom":
				playerid = Integer.parseInt(msgmap.get("playerid"));
				roomid = Integer.parseInt(msgmap.get("roomid"));
				System.out.println("Get in Room: " + "PlayerID: " + playerid +" in " + "RoomID: " + roomid);
				int index = assignPlayerToRoom(playerid,roomid);
	
				remap.clear();
				remap.put("action","setroom");
				remap.put("roomid",Integer.toString(roomid));
				remap.put("playerid",Integer.toString(playerid));
				remap.put("index",Integer.toString(index));
				msg = genMsg(remap);
				if(index<0){
					playerMap.get(playerid).sendMessage(msg);
				} else {
					Broadcast(msg);
				}
	
				break;
				
			case "start":
				playerid = Integer.parseInt(msgmap.get("playerid"));
				roomid = playerRoom.get(playerid);
				getRoomById(roomid).gameJudge.cleanBoard();
				remap.clear();
				remap.put("action","start");
				msg = genMsg(remap);
				getRoomById(roomid).getAnotherPlayer(playerid).sendMessage(msg);
				System.out.println("PlayerID: " + playerid +" in " + "RoomID: " + roomid + " start");
				break;
				
			case "step":
				playerid = Integer.parseInt(msgmap.get("playerid"));
				roomid =  playerRoom.get(playerid);
				String step = msgmap.get("step");

				getRoomById(roomid).gameJudge.setStep(step);
				if(getRoomById(roomid).gameJudge.isOver()){
					remap.clear();
					remap.put("action", "win");
					msg = genMsg(remap);
					playerMap.get(playerid).sendMessage(msg);
					
					remap.clear();
					remap.put("action", "over");
					msg = genMsg(remap);
					getRoomById(roomid).getAnotherPlayer(playerid).sendMessage(msg);
				}else{
					remap.clear();
					remap.put("action", "step");
					remap.put("step",step);
					msg = genMsg(remap);
					getRoomById(roomid).getAnotherPlayer(playerid).sendMessage(msg);
				}
				
				System.out.println("PlayerID: " + playerid + " in " + "RoomID: " + roomid + " step: " + step);
				break;
				
			case "create":
				playerid = Integer.parseInt(msgmap.get("playerid"));
				gameRooms.add(new GameRoom());
				roomid = gameRooms.get(gameRooms.size()-1).roomId;
				remap.clear();
				remap.put("action", "create");
				remap.put("roomid",Integer.toString(roomid));
				msg = genMsg(remap);
	
				Broadcast(msg);
				System.out.println("PlayerID: " + playerid + " Create " + "RoomID: " + roomid );
				break;
			
			case "removeroom":
				roomid = Integer.parseInt(msgmap.get("roomid"));
				for(int i=getRoomById(roomid).players.size();i>0;i--){
					playerRoom.remove(getRoomById(roomid).players.get(i-1).playerId);
				}
				gameRooms.remove(getRoomById(roomid));
				
				remap.clear();
				remap.put("action", "removeroom");
				remap.put("roomid",Integer.toString(roomid));
				msg = genMsg(remap);
				
				Broadcast(msg);
				System.out.println("Room Numbers: " + gameRooms.size());
				break;
			
			case "quitroom":
				playerid = Integer.parseInt(msgmap.get("playerid"));
				roomid = Integer.parseInt(msgmap.get("roomid"));
				getRoomById(roomid).players.remove(playerMap.get(playerid));
				playerRoom.remove(playerid);
				System.out.println("Quit Room: " + "PlayerID: " + playerid +" in " + "RoomID: " + roomid);
				
				remap.clear();
				remap.put("action","quitroom");
				remap.put("roomid",Integer.toString(roomid));
				remap.put("playerid",Integer.toString(playerid));
				msg = genMsg(remap);
				Broadcast(msg);
				break;
			
			case "loadroom":
				playerid = Integer.parseInt(msgmap.get("playerid"));
				remap.clear();
				remap.put("action", "loadroom");
				String roomsstr = "";
				String players = "";
				remap.put("length", Integer.toString(gameRooms.size()));
				for(int i=0;i<gameRooms.size();i++){
					GameRoom gameroom = gameRooms.get(i);
					roomsstr +=  gameroom.roomId + ";"; 
					switch(gameroom.players.size()){
						case 0:
							players += gameroom.roomId + " null" + " null" + ";";
							break;
						case 1:
							players += gameroom.roomId+" "+gameroom.players.get(0).playerId+" null"+";";
							break;
						case 2:
							players += gameroom.roomId+ " " +gameroom.players.get(0).playerId
										+" " +gameroom.players.get(1).playerId+";";
							break;
						default:
							break;
					}
				}
				remap.put("rooms",roomsstr);
				remap.put("players", players);
				msg = genMsg(remap);
				playerMap.get(playerid).sendMessage(msg); 
				break;
				
			default:
				break;
		}
	}
	
    private static Map<String, String> parseData(String data){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
        return map;
    }
    
    private static String genMsg(Map<String,String> remap){
    	return (new Gson()).toJson(remap);
    }
    
    private static GameRoom getRoomById(int roomId){
    	for(GameRoom item : gameRooms){
    		if(item.roomId == roomId){
    			return item;
    		}
    	}
    	return null;
    }
    
    public static void Broadcast(String msg){
    	for(GamePlayer player:players){
    		player.sendMessage(msg);
    	}
    	
    }
    
	
}
