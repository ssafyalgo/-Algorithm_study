import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        LinkedList<Character> list = new LinkedList<>();
        ListIterator<Character> iter = list.listIterator();
        for(int i=0; i<str.length(); i++){
            iter.add(str.charAt(i));
        }
        int M = Integer.parseInt(br.readLine());
        while(M--!=0){
            String input = br.readLine();
            char cmd = input.charAt(0);
            if(cmd=='L'){
                if(iter.hasPrevious())iter.previous();
            }else if(cmd=='D'){
                if(iter.hasNext())iter.next();
            }else if(cmd=='B'){
                if(iter.hasPrevious()){
                    iter.previous();
                    iter.remove();
                }
            }else if(cmd=='P'){
                iter.add(input.charAt(2));
            }
        }
        //toString
        StringBuilder sb = new StringBuilder();
        for(Character temp : list){
            sb.append(temp);
        }
        System.out.println(sb);
    }
}

