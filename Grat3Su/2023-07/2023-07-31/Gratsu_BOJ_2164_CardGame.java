package doit;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CardGame {

	public static void main(String[] args) {
		//제일 위의 카드 버리고
		//N은 맨 위로
		Scanner sc = new Scanner(System.in);
		Queue<Integer> myQueue = new LinkedList<>();
		
		int n = sc.nextInt();
		
		for(int i = 1; i<n+1; i++)
		{
			myQueue.add(i);//큐에 넣기
		}
		
		while(myQueue.size()>1)//카드가 한 장 남을 때까지
		{
			myQueue.poll();//맨 위 카드 버리고
			myQueue.add(myQueue.poll());//맨 위로 넣고			
		}
		System.out.println(myQueue.poll());//남은 하나
		
	}

}
