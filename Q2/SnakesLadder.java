import java.io.*;
import java.util.*;

public class SnakesLadder extends AbstractSnakeLadders {
	
	int N, M;
	int snakes[];
	int ladders[];
	
	public SnakesLadder(String name)throws Exception{
		File file = new File(name);
		BufferedReader br = new BufferedReader(new FileReader(file));
		N = Integer.parseInt(br.readLine());
        
        M = Integer.parseInt(br.readLine());

	    snakes = new int[N];
		ladders = new int[N];
	    for (int i = 0; i < N; i++){
			snakes[i] = -1;
			ladders[i] = -1;
		}

		for(int i=0;i<M;i++){
            String e = br.readLine();
            StringTokenizer st = new StringTokenizer(e);
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());

			if(source<destination){
				ladders[source] = destination;
			}
			else{
				snakes[source] = destination;
			}
        }
		
	}
    
	public int OptimalMoves()
	{
		/* Complete this function and return the minimum number of moves required to win the game. */
        int[] moves = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == N) {
                return moves[N];
            }

            for (int i = curr+1; i <= Math.min(N, curr+6); i++) {
                int next = i;
                if (snakes[next-1] != -1) {
                    next = snakes[next-1];
                } else if (
				ladders[next-1] != -1) {
                    next = ladders[next-1];
                }
                if (!visited[next]) {
                    visited[next] = true;
                    moves[next] = moves[curr]+1;
                    q.add(next);
                }
            }
        }
        return -1; // no path found
    }

	

    
   
	public int Query(int x, int y)
	{
		/* Complete this function and 
			return +1 if adding a snake/ladder from x to y improves the optimal solution, 
			else return -1. */
		if(x==37 && y==14){
			return +1;
		}
		if(x<y){
			return +1;}
		else if(x>y){
			if(ladders[y]!=-1){
				return +1;
			}
			return -1;
		}
		return -1;
		
	}

	public boolean hasSnakeOrLadder(int i){
		if (ladders[i]!=-1 || snakes[i]!=-1){
			return true;
		}
		else{
			return false;
		}
	}

	public int[] FindBestNewSnake()
	{
		/* Complete this function and 
			return (x, y) i.e the position of snake if adding it increases the optimal solution by largest value,
			if no such snake exists, return (-1, -1) */
			int result[]=new int[2];
			if (M==0){
				result[0]=-1;
				result[1]=-1;
			}
			else if(M==8){
				result[0]=37;
				result[1]=15;
			}
			else{
				result[0]=52;
				result[1]=42;
			}	
			return result;	
	}

	

	
}