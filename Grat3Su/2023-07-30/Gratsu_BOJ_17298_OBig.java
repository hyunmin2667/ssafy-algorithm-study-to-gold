package doit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class OBig {

	public static void main(String[] args) throws IOException
	{
//        Scanner sc = new Scanner(System.in);
//        
//        int n = sc.nextInt();
//        
//        //Stack<Integer> num = new Stack<>();        
//        int[] result = new int[n];
//        int number = sc.nextInt();
//        
//        for(int i = 1; i<n-1; i++)
//        {
//        	int compareNum = sc.nextInt();
//        	if(number < compareNum)
//        	{
//        		for(int j = i-1; j>=0; j--)
//        		{
//        			if(result[j]!=0)
//        				break;
//        			else
//        				result[j] = compareNum;
//        		}
//        		number = compareNum;
//        	}
//        }
//        result[n-1] = -1;
//        
//        System.out.println(Arrays.toString(result));
        
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));        
        int n = Integer.parseInt(buf.readLine());
        
        int[]A = new int[n];//수열 배열
        int[] ans = new int[n];//정답 배열
        String[] str = buf.readLine().split(" ");
        for(int i = 0; i<n; i++)
        {
        	A[i] = Integer.parseInt(str[i]);
        }
        Stack<Integer> myStack = new Stack<>();        
        myStack.push(0);
        
        for(int i = 0; i<n; i++)
        {
        	while(!myStack.isEmpty()&&A[myStack.peek()]<A[i])
        	{
        		ans[myStack.pop()] = A[i];        		
        	}
        	myStack.push(i);
        }
        while(!myStack.empty()) {
        	ans[myStack.pop()] = -1;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i<n; i++) {
        	bw.write(ans[i] + " ");
        }
        
        bw.write("\n");
        bw.flush();
	}
}