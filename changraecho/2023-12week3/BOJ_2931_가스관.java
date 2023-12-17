package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2931_가스관 {
	static int R, C;
	static char[][] grid;
	//static boolean[][] visited;
	static int curX, curY;
	static int inDir;
	static int cutX, cutY;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	//static int startX, startY, startDir;
	static int missingX, missingY;
	static char missingBlock;
	static Map<Character, Set<Integer>> blocksDirHash;
	
	
	static void initBlocksDirHash() {
		blocksDirHash = new HashMap<>();
		
		blocksDirHash.put('|', new HashSet<>(List.of(1, 3)));
		blocksDirHash.put('-', new HashSet<>(List.of(0, 2)));
		blocksDirHash.put('+', new HashSet<>(List.of(0,1,2,3)));
		blocksDirHash.put('1', new HashSet<>(List.of(1,2)));
		blocksDirHash.put('2', new HashSet<>(List.of(2,3)));
		blocksDirHash.put('3', new HashSet<>(List.of(0, 3)));
		blocksDirHash.put('4', new HashSet<>(List.of(0, 1)));
	}
	
	static boolean outGrid(int x, int y) {
		if (x < 0 || x >= R || y < 0 || y >= C) return true;
		return false;
	}
	
	static void findStart() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] != 'M') continue;
				
				int ni, nj;
				for (int d = 0; d < 4; d++) {
					ni = i + dx[d];
					nj = j + dy[d];
					
					if (outGrid(ni, nj)) continue;
					if (grid[ni][nj] == 'Z' || grid[ni][nj] == '.') continue;
					
					if (hasInDir(grid[ni][nj], d)) {
						curX = ni;
						curY = nj;
						inDir =d;
						
						break;
						
					}
				}
			}
		}
	}
	
	static boolean hasInDir(char block, int dir) {
//		System.out.println(block);
		return blocksDirHash.get(block).contains(dir);
	}
	
	static Character getBlock(int inDir, int outDir) { // '+'는 찾지 않음.
		
		//System.out.println(inDir + " " + outDir);
		
		
		if (inDir == outDir && (inDir == 0 || outDir == 2)) {
			return '-';
		}
		
		else if (inDir == outDir && (inDir == 1 || outDir == 3)) {
			return '|';
		}
		
		for (char block: blocksDirHash.keySet()) {
			if (block == '+') continue;
			if (getOutDir(block, inDir) == outDir) {
				return block;
			}
		}
		
		
		return 'a'; // to avoid error
		
	}
	
	static int getOutDir(char c, int inDir) {
		if (c == '+' || c == '-' || c == '|') {
			return inDir;
		}
		
		if (c == '1') {
			if (inDir == 1) {
				return 0;
			}
			else if (inDir == 2) {
				return 3;
			}
			
		}
		
		else if (c == '2') {
			if (inDir == 2) {
				return 1;
			} else if (inDir == 3) {
				return 0;
			}
			
		}
		
		else if (c == '3') {
			if (inDir == 0) return 1;
			else if (inDir ==3) {
			return 2;	
			}
			
		}
		
		else if (c == '4') {
			if (inDir == 0) return 3;
			else if (inDir == 1) {
				return 2;
			}

		}
	
		return -1; // to avoid error
				
	}
	
	static void move() {
		int outDir = getOutDir(grid[curX][curY], inDir);
		int nx = curX + dx[outDir];
		int ny = curY + dy[outDir];
		
		if (grid[nx][ny] == '.') {
			cutX = curX;
			cutY =  curY;
		}
		
		curX = nx;
		curY = ny;
		inDir = outDir;
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new char[R][C];
		for (int i = 0; i < R; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				grid[i][j] = row[j];
			}
		}
		
		initBlocksDirHash();
		findStart();
		
				
		while (grid[curX][curY] != '.') {
			move();
		}
		
		missingX = curX;
		missingY = curY;
		
		int nx, ny;
		int numCandidates = 0;
		int outDir = -1;
		for (int d = 0; d < 4; d++) {
			nx = curX + dx[d];
			ny = curY + dy[d];
			
			if (outGrid(nx, ny)) continue;
			if (grid[nx][ny] == 'M' || grid[nx][ny] == 'Z' || grid[nx][ny] == '.') continue;
			if (nx == cutX && ny == cutY) continue;
			
			if (hasInDir(grid[nx][ny], d)) {
				//System.out.println(nx + " " + ny);
				numCandidates++;
				outDir = d;
			}
		}
		
		if (numCandidates == 3 ) {
			missingBlock = '+';
		} else if (numCandidates == 1) {
			missingBlock = getBlock(inDir, outDir);
		}
		
		sb.append(missingX + 1 + " ");
		sb.append(missingY + 1 + " ");
		sb.append(missingBlock);
		
		
		System.out.println(sb);

	}

}
