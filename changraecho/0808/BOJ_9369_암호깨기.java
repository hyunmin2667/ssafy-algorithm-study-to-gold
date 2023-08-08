
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;

//do it 교재의 문제는 아님.
public class BOJ_9369_암호깨기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 역암호화표 초기화
        HashMap<Character, Character> invCipherTable = new HashMap<Character, Character>();

        // 암호화표 초기화
        HashMap<Character, Character> cipherTable = new HashMap<Character, Character>();

        // N 초기화
        int N;

        // IMPOSSIBLE 초기화
        boolean IMPOSSIBLE;

        // encrypteds 초기화
        String[] encrypteds;

        // decrypted 초기화
        String decrypted;

        // eHash, dHash 초기화
        HashMap<Character, HashSet<Integer>> eHash;
        HashMap<Character, HashSet<Integer>> dHash;

        // toDecrypt 초기화
        String toDecrypt;

        // decryptable 초기화
        boolean decryptable;

        // T
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            // 역암호화표 정의

            // N
            N = Integer.parseInt(br.readLine());

            // N개의 암호문 중 해독될 수 있는 것이 존재하는지 확인.
            IMPOSSIBLE = true;

            // N개의 암호문을 arr encrypteds에 저장
            encrypteds = new String[N];
            for (int i = 0; i < N; i++) {
                encrypteds[i] = br.readLine();
            }

            // decrypted
            decrypted = br.readLine();
            char[] dArr = decrypted.toCharArray();

            // decrypted로부터 dHash 계산
            dHash = new HashMap<Character, HashSet<Integer>>();
            int dLen = dArr.length;
            for (int i = 0; i < dLen; i++) {
                if (!dHash.containsKey(dArr[i])) {
                    dHash.put(dArr[i], new HashSet<Integer>());

                }
                dHash.get(dArr[i]).add(i);
            }

            // dHash의 key의 갯수가 25인 경우, 남은 하나의 알파벳을 기억해야 한다.
            char remainAlphabet = ' ';

            if (dHash.keySet().size() == 25) {
                for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
                    if (!dHash.containsKey(alphabet)) {
                        remainAlphabet = alphabet;
                        break;
                    }
                }
            }

            // toDecrypt
            toDecrypt = br.readLine();
            char[] tdArr = toDecrypt.toCharArray();
            int tdLen = tdArr.length;

            // 각 암호문에 대해 그것이 해독될 수 있는 지 판단. 해독될 수 있는 것이 존재하면 IMPOSSIBLE = false, 역암호화 표 수정
            for (String encrypted : encrypteds) {

                // eArr 정의
                char[] eArr = encrypted.toCharArray();
                int eLen = eArr.length;

                // 길이가 같아야 함
                if (eLen == dLen) {
                    eHash = new HashMap<Character, HashSet<Integer>>();

                    for (int i = 0; i < eLen; i++) {

                        if (!eHash.containsKey(eArr[i])) {
                            eHash.put(eArr[i], new HashSet<Integer>());

                        }
                        eHash.get(eArr[i]).add(i);

                    }

                    // eHash와 dHash를 비교해서 해독 될 수 있는 지 판단
                    decryptable = true;

                    for (int i = 0; i < eLen; i++) {
                        if (!(eHash.get(eArr[i]).equals(dHash.get(dArr[i])))) {
                            decryptable = false;
                            break;
                        }
                    }

                    // 해독될 수 있다면, 역암호화 표 작성(수정)
                    if (decryptable) {
                        IMPOSSIBLE = false;
                        for (int i = 0; i < eLen; i++) {

                            // 역암호화표를 처음 작성할 때
                            if (!invCipherTable.containsKey(dArr[i])) {
                                invCipherTable.put(dArr[i], eArr[i]);
                            } else { // 역암호화표를 수정할 때
                                if (invCipherTable.get(dArr[i]) != eArr[i]) {
                                    invCipherTable.put(dArr[i], '?');
                                }

                            }

                        }

                        // 역암호화표의 key의 갯수가 25개일 때, 남은 알파벳은 해독될 수도 있다.
                        if (remainAlphabet != ' ') {
                            // remainAlphabet에 대해 invCipherTable 정의
                            if (!invCipherTable.containsKey(remainAlphabet)) {
                                char val = ' ';
                                for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
                                    if (!eHash.containsKey(alphabet)) {
                                        val = alphabet;
                                        break;
                                    }
                                }
                                invCipherTable.put(remainAlphabet, val);
                            } else if (invCipherTable.get(remainAlphabet) != '?') { // 필요하다면
                                                                                    // invCipherTable.get(remainAlphabet)
                                                                                    // 재정의
                                char val = ' ';
                                for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
                                    if (!eHash.containsKey(alphabet)) {
                                        val = alphabet;
                                        break;
                                    }
                                }
                                if (val != invCipherTable.get(remainAlphabet)) {
                                    invCipherTable.put(remainAlphabet, '?');
                                }

                            }
                        }

                    }

                }

            }

            // 해독될 수 있는 암호가 없는 경우
            if (IMPOSSIBLE) {
                sb.append("IMPOSSIBLE").append("\n");
            } else { // 해독될 수 있는 암호가 있는 경우

                // 역암호화표를 이용해서 암호화표 정의
                for (char key : invCipherTable.keySet()) {
                    char val = invCipherTable.get(key);
                    if (val != '?') {
                        cipherTable.put(val, key);
                    }
                }

                for (int i = 0; i < tdLen; i++) {
                    if (cipherTable.containsKey(tdArr[i])) {
                        sb.append(cipherTable.get(tdArr[i]));
                    } else {
                        sb.append('?');
                    }

                }
                sb.append("\n");

                cipherTable.clear();

            }

            invCipherTable.clear();
        }

        System.out.println(sb.toString());

    }
}
