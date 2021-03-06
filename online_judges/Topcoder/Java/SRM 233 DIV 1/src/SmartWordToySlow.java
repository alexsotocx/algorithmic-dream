import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SmartWordToySlow {

  int[][][][] dist = new int[26][26][26][26];

  private Position[] states(Position stateP) {
    int[] state = new int[]{stateP.a, stateP.b, stateP.c, stateP.d};
    Position[] states = new Position[8];
    for (int i = 0, j = 0; i < 4; i++) {
      int aux = state[i];
      state[i] = (state[i] + 1) % 26;
      states[j++] = new Position(state);
      state[i] = aux;
      state[i] = (state[i] + 25) % 26;
      states[j++] = new Position(state);
      state[i] = aux;
    }
    //System.out.println(Arrays.toString(states));
    return states;
  }


  public int minPresses(String start, String finish, String[] forbid) {
    preprocess(forbid);
    Position pStart = new Position(start);
    Position pFinish = new Position(finish);
    dist[pStart.a][pStart.b][pStart.c][pStart.d] = 0;
    Queue<Position> queue = new LinkedList<Position>();
    queue.offer(pStart);
    while (!queue.isEmpty()) {
      Position state = queue.poll();
      if (state.equals(pFinish)) {
        return state.getDist();
      }
      for (Position s : states(state)) {
        if (s.getDist() == 0) {
          System.out.println(s);
          s.setDist(state.getDist() + 1);
          queue.add(s);
        }
      }

    }
    return -1;
  }


  private void preprocess(String[] forbid) {
    for (String s : forbid) {
      String[] str = s.split("\\s");
      for (char i : str[0].toCharArray())
        for (char j : str[1].toCharArray())
          for (char m : str[2].toCharArray())
            for (char n : str[3].toCharArray()){
              if(dist[i - 'a'][j - 'a'][m - 'a'][n - 'a'] == 0) {
                dist[i - 'a'][j - 'a'][m - 'a'][n - 'a'] = -10;
                System.out.println(Arrays.toString(new char[]{i,j,m,n}));
              }

            }
    }
  }

  class Position {
    int a, b, c, d;

    public Position(int[] pos) {
      this.a = pos[0];
      this.b = pos[1];
      this.c = pos[2];
      this.d = pos[3];
    }


    public Position(String pos) {
      this.a = pos.charAt(0) - 'a';
      this.b = pos.charAt(1) - 'a';
      this.c = pos.charAt(2) - 'a';
      this.d = pos.charAt(3) - 'a';
    }

    int getDist() {
      return dist[a][b][c][d];
    }

    void setDist(int value) {
      dist[a][b][c][d] = value;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Position) {
        Position instance = (Position) obj;
        return instance.a == this.a && instance.b == this.b && instance.c == this.c && instance.d == this.d;
      }
      return false;
    }

    @Override
    public String toString() {
      return Arrays.toString(new char[]{(char) (this.a + 'a'), (char) (this.b + 'a'), (char) (this.c + 'a'), (char) (this.d + 'a')});
    }
  }

  public static void main(String args[]) {
    String start = "mmma";
    String finish = "yyyy";
    System.out.println("XD");
    String[] forbid = new String[]{"qwertyuiopasdfg qwertyuiopasdfg qwertyuiopasdfg z", "qwertyuiopasdfg qwertyuiopasdfg hjklzxvcbnm z", "qwertyuiopasdfg hjklzxvcbnm qwertyuiopasdfg z", "qwertyuiopasdfg hjklzxvcbnm hjklzxvcbnm z", "hjklzxvcbnm qwertyuiopasdfg qwertyuiopasdfg z", "hjklzxvcbnm qwertyuiopasdfg hjklzxvcbnm z", "hjklzxvcbnm hjklzxvcbnm qwertyuiopasdfg z", "hjklzxvcbnm hjklzxvcbnm hjklzxvcbnm z", "qwertyuiopasdfg qwertyuiopasdfg z qwertyuiopasdfg", "qwertyuiopasdfg qwertyuiopasdfg z hjklzxvcbnm", "qwertyuiopasdfg hjklzxvcbnm z qwertyuiopasdfg", "qwertyuiopasdfg hjklzxvcbnm z hjklzxvcbnm", "hjklzxvcbnm qwertyuiopasdfg z qwertyuiopasdfg", "hjklzxvcbnm qwertyuiopasdfg z hjklzxvcbnm", "hjklzxvcbnm hjklzxvcbnm z qwertyuiopasdfg", "hjklzxvcbnm hjklzxvcbnm z hjklzxvcbnm", "qwertyuiopasdfg z qwertyuiopasdfg qwertyuiopasdfg", "qwertyuiopasdfg z qwertyuiopasdfg hjklzxvcbnm", "qwertyuiopasdfg z hjklzxvcbnm qwertyuiopasdfg", "qwertyuiopasdfg z hjklzxvcbnm hjklzxvcbnm", "hjklzxvcbnm z qwertyuiopasdfg qwertyuiopasdfg", "hjklzxvcbnm z qwertyuiopasdfg hjklzxvcbnm", "hjklzxvcbnm z hjklzxvcbnm qwertyuiopasdfg", "hjklzxvcbnm z hjklzxvcbnm hjklzxvcbnm", "z qwertyuiopasdfg qwertyuiopasdfg qwertyuiopasdfg", "z qwertyuiopasdfg qwertyuiopasdfg hjklzxvcbnm", "z qwertyuiopasdfg hjklzxvcbnm qwertyuiopasdfg", "z qwertyuiopasdfg hjklzxvcbnm hjklzxvcbnm", "z hjklzxvcbnm qwertyuiopasdfg qwertyuiopasdfg", "z hjklzxvcbnm qwertyuiopasdfg hjklzxvcbnm", "z hjklzxvcbnm hjklzxvcbnm qwertyuiopasdfg", "z hjklzxvcbnm hjklzxvcbnm hjklzxvcbnm", "n ablm ablm abcdefghijkl", "n ablm abcdefghijkl ablm", "n abcdefghijkl ablm ablm", "abcdefghijklm n abcdefghijklm abcdefghijklm", "abcdefghijklm abcdefghijklm n abcdefghijklm", "abcdefghijklm abcdefghijklm abcdefghijklm n", "bcdefghijklm bcdefghijkl ablm ablm", "abcdefghijkl ablm bcdefghijkl ablm", "ablm bcdefghijklm bcdefghijkl ablm", "abcdefghijkl ablm ablm bcdefghijkl", "ablm abcdefghijkl ablm bcdefghijkl", "ablm ablm bcdefghijklm bcdefghijkl"};
    System.out.println(new SmartWordToySlow().minPresses(start, finish, forbid));
  }


}
