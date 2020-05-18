package Link;

import java.util.HashMap;
import java.util.Stack;

/**
 * 描述:
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 栈的应用
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-04-29 15:24
 */
public class LeeCode20 {
    private static HashMap<Character, Character> map = new HashMap<>();

    static {
        // key - value
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
    }


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { // 左括号
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) return false;

                if (c != map.get(stack.pop())) return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') { // 左括号
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) return false;

                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '{' && c != '}') return false;
                if (left == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        while (s.contains("{}")
                || s.contains("[]")
                || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("()", "");
            s = s.replace("[]", "");
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid3("(()(()))"));
    }

    public static int isValid3(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(0);
            } else {
                Integer w = stack.pop();
                Integer v = stack.pop();
                stack.push(v + Math.max(w * 2, 1));
            }
        }
        return stack.pop();
    }
}
