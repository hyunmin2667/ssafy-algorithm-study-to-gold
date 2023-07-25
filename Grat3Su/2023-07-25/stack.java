package doit;

import java.util.Scanner;
import java.util.Stack;

public class stack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[]A = new int [N];
		StringBuffer bf = new StringBuffer();
		
		for(int i = 0; i<N; i++)
		{//받기
			A[i] = sc.nextInt();
		}
		
		Stack<Integer> stack = new Stack<>();//스택
		int num = 1;
		boolean result = true;
		for(int i = 0; i<A.length; i++)
		{
			int su = A[i];//현재 수열의 수
			if(su>= num)
			{
				while(su>=num) {
					stack.push(num++);
					bf.append("+\n");
				}
				stack.pop();
				bf.append("-\n");				
			}
			else
			{
				int n = stack.pop();
				if(n>su)
				{
					System.out.println("NO");
					result = false;
					break;
				}
				else
					bf.append("-\n");
			}
		}
		if(result) System.out.println(bf.toString());
	}
}
