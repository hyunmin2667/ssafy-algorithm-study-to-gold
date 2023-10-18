package doit;
import java.util.Scanner;

public class RemainAdd {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		long[] s = new long[N];
		long[] c = new long[M];
		
		long answer = 0;
		
		s[0] = sc.nextInt();
		
		for(int i =1; i<N; i++)
		{
			s[i] = s[i-1]+sc.nextInt();
		}
		
		for(int i = 0; i<N; i++)
		{
			int remainder = (int)(s[i]%M);//나누기
			if(remainder == 0)//0-i까지의 구간합 자체가 0일 때 정답
				answer++;
			c[remainder]++;//나머지가 같은 인덱스의 갯수 카운트
		}
		for(int i = 0; i<M; i++)
		{
			if(c[i]>1)//나머지가 같은 인덱스 총 2개를 뽑는 경우의 수 더하기
			answer = answer +(c[i] * (c[i]-1)/2);
		}
		System.out.println(answer);
		sc.close();
	}
}
