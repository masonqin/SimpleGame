package game.plantform;

public class GameJudge {
	
	int[][] board = new int[7][7];
	
	public boolean isOver(){
		
		boolean ret = true;
		
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				if(board[i][j]!=1){
					if(i != 6){
						if(board[i+1][j] != 1){
							System.out.println("over"+i+j);
							ret = false;
							return ret;
						}
					}
					if(j != 6){
						if(board[i][j+1] != 1){
							System.out.println("over"+i+j);
							ret = false;
							return ret;
						}
					}
				}
			}
		}
		return ret;
	}
	
	void setStep(String step){
		System.out.println(step);
		char[] arr = step.toCharArray();
		int x1 =Integer.parseInt(Character.toString(arr[0]))-1;
		int y1 =Integer.parseInt(Character.toString(arr[1]))-1;
		int x2 =Integer.parseInt(Character.toString(arr[2]))-1;
		int y2 =Integer.parseInt(Character.toString(arr[3]))-1;
		
		//if(x1<7&&y1<7&&x2<7&&y2<7){
			board[x1][y1] = 1;
			board[x2][y2] = 1;
		//}
		System.out.println(" "+arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]);
		System.out.println(board[x1][y1]+" "+board[x2][y2]);
		
		
	}
	
	void cleanBoard(){
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				board[i][j]=0;
			}
		}
	}
		
}
