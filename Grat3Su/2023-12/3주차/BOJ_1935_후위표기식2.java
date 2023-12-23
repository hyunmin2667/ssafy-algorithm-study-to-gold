import java.io.*;
import java.text.Format;
import java.util.*;
public class BOJ_1935_후위표기식2 {
    static int N;
    static double[] num;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        num = new double[N];
        for(int i = 0; i<N; i++){
            num[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        double num1=0,num2=0;
        for(int i = 0; i<s.length(); i++){
            switch (s.charAt(i)){
                case '+':
                    num1 = stack.pop();
                    num2 = stack.pop();

                    stack.push(num2+num1);
                    break;
                case '-':
                    num1 = stack.pop();
                    num2 = stack.pop();

                    stack.push(num2-num1);
                    break;
                case '*':
                    num1 = stack.pop();
                    num2 = stack.pop();

                    stack.push(num2*num1);
                    break;
                case '/':
                    num1 = stack.pop();
                    num2 = stack.pop();

                    stack.push(num2/num1);
                    break;
                default:
                    stack.push(num[s.charAt(i)-'A']);
                    break;
            }

        }
        System.out.printf("%.2f", stack.pop());
    }
}
