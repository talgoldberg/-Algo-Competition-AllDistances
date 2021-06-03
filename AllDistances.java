
package my_algo2;
import java.util.Arrays;

public class AllDistances {

	static int inf = 1000000;
	int mat[][];
	int arr[];
	int ans[][];
	String[][] path;
	
	public AllDistances(int[] vertices_weights, int[][] edges_weights)
	{
		 mat=new int[edges_weights.length][edges_weights[0].length];
		 arr=new int[vertices_weights.length];
		 mat=edges_weights;
		 arr=vertices_weights;
		 matrix_distance_help();
	}
	private void matrix_distance_help()
	{
		ans=new int[mat.length][mat[0].length];
		path=new String[mat.length][mat[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				
				if(mat[i][j]!=inf)
				  path[i][j]= (i+1) + "";
				
				if(mat[i][j]==inf)
					ans[i][j]=inf;
				
				if(mat[i][j]==0 && i==j)
					ans[i][j]=arr[i];
				
				if(i!=j && mat[i][j]!= inf && mat[i][j]==mat[j][i])
					ans[i][j]=arr[i]+arr[j]+mat[i][j];
				
			}
		}
		
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {
				for (int k = 0; k < ans.length; k++) {
					if(mat[i][k]!=inf && mat[k][j]!=inf)
						if(i!=j && (ans[i][j] > ans[i][k]+ans[k][j]-arr[k]))
						{
							ans[i][j]=ans[i][k]+ans[k][j]-arr[k];
							path[i][j]=path[i][k] + "-" + path[k][j];
						}
						  
				}
			}
		}
		
		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path[0].length; j++) {
				if(path[i][j]!= null)	
					path[i][j]+="-"+(j+1);
			}
		}
		
	}
	public int[][] matrix_distance()
	{
		return ans;
	}
	
	public String path(int u, int v) 
	{
		return path[u-1][v-1];
	}
	
	public int distance(int u, int v)
	{
		return ans[u-1][v-1];
	}
	
	public static void main(String[] args) {
		
		int[][] mat= {{0,18,5,inf},
					  {18,0,2,3},
					  {5,2,0,inf},
					  {inf,3,inf,0}};
		
		int[] arr= {2,4,3,6};
		
		
		AllDistances ad=new AllDistances(arr, mat);
		int[][] myMat=ad.matrix_distance(); 
		System.out.println("The distance matrix:");
		System.out.println();
		for (int[] is : myMat) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println();
		System.out.println("path: "+ ad.path(1, 4));
		System.out.println("distance: "+ ad.distance(1, 4));
	}
	

}
