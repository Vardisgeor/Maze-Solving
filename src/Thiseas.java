
import java.util.StringTokenizer;

import org.w3c.dom.css.Counter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Thiseas {
	
    public static int counter = 1;

	public static boolean solveMaze(String maze[][], int x, int y, int n, int m, StringStack<String> list) {

        list.push("("+x+", "+y+")");

        while(!list.isEmpty()){

            if(x == n-1 || y == m-1 || y == 0 ) {
                
                list.push("("+x+", "+y+")");
                return true;
            }

            if(x == 0 && maze[x][y].equals("*")){   // if the solution is in the first line
               
                list.push("("+x+", "+y+")");
                return true;
            }

            if(visit(x,y,n,m,maze)){

                boolean back = true;

                if(x>=1 && maze[x-1][y].equals("0")){
                    if(!maze[x-1][y].equals("*")){
                        list.push("("+(x-1)+", "+y+")");
                        maze[x-1][y] = "*";
                        back = false;
                        x = x-1;
                    }
                }

                if(y>0 && maze[x][y-1].equals("0")){
                    if(!maze[x][y-1].equals("*")){
                        list.push("("+x+", "+(y-1)+")");
                        maze[x][y-1] = "*";
                        back = false;
                        y = y-1;
                    }
                }

                if(maze[x][y+1].equals("0")){
                    if(!maze[x][y+1].equals("*")){
                        list.push("("+x+", "+(y+1)+")");
                        maze[x][y+1] = "*";
                        back = false;
                        y = y+1;
                    }
                }

                if(maze[x+1][y].equals("0")){
                    if(!maze[x+1][y].equals("*")){
                        list.push("("+(x+1)+", "+y+")");
                        maze[x+1][y] = "*";
                        back = false;
                        x = x+1;
                    }
                }

                if(back){
                    maze[x][y]="*";
                    String temp = list.pop();
                    if(!list.isEmpty()){
                        String[] r = temp.split(", ");
                        String r2 = r[0].replace("(","");
                        String r3 = r[1].replace(")","");
                        x = Integer.parseInt(r2);
                        y = Integer.parseInt(r3);   
                    } 
                        
                }
            }
           
        }
        return false;

    }
	
	public static boolean visit(int x, int y, int n, int m,String maze[][]) {
		
		if(x<n-1 && y<m-1) {
            return true;
		}else {
			return false;
		}
		
	}
	
    public static void main(String[] args) {
       
        BufferedReader reader = null;
        String line;
        
        int n,m;
        int cor_n,cor_m;

        try {
        	
        	FileReader file = new FileReader(args[0]);
        
            reader = new BufferedReader(file);
            line = reader.readLine();

            StringTokenizer stn = new StringTokenizer(line);
            n = Integer.parseInt(stn.nextToken());
            m = Integer.parseInt(stn.nextToken());
            line = reader.readLine();

            stn = new StringTokenizer(line);
            cor_n = Integer.parseInt(stn.nextToken());
            cor_m = Integer.parseInt(stn.nextToken());
            line = reader.readLine();

            String[][] stable = new String[n][m];
            int k=0;
            
            while(line!=null){
            	
                stn = new StringTokenizer(line);
                
                for(int i=0; i<m; i++){
                    stable[k][i] = stn.nextToken();
                }
				
                k++;
                line = reader.readLine();
            }

            reader.close();
            
			StringStack<String> list =new StringStackImpl<>();
			
            if(stable[cor_n][cor_m].equals("Ε")){

                list.push(stable[cor_n][cor_m]);
                solveMaze(stable,cor_n,cor_m,n,m,list);

                if(list.isEmpty()){
                    System.out.println("There is no solution!");
                }else{
                    list.pop();
                    System.out.println("The exit of the maze is: "+list.peek());
                }

                
            }else {
            	System.out.println("There is no entrance!!!");
            }
        
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
}
