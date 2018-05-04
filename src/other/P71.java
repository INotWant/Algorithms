package other;

import java.util.LinkedList;

public class P71 {

    public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList<>();
        StringBuilder backFlag = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c != '/') {
                boolean isFileName = false;
                StringBuilder tempSb = new StringBuilder();
                while (i < path.length() && path.charAt(i) != '/') {
                    if (Character.isLetter(path.charAt(i)))
                        isFileName = true;
                    tempSb.append(path.charAt(i));
                    ++i;
                }
                --i;
                if (!isFileName && tempSb.length() >= 3)
                    isFileName = true;
                if (isFileName) {
                    backFlag.delete(0, backFlag.length());
                    stack.addLast(tempSb.toString());
                } else
                    backFlag.append(tempSb.toString());
            } else {
                if (backFlag.length() > 0 && backFlag.toString().equals("..")) {
                    if (stack.size() > 0)
                        stack.removeLast();
                }
                backFlag.delete(0, backFlag.length());
            }
        }
        if (backFlag.length() == 2) {
            if (stack.size() > 0)
                stack.removeLast();
        } else if (backFlag.length() > 2)
            stack.addLast(backFlag.toString());
        StringBuilder result = new StringBuilder("/");
        for (int i = 0; i < stack.size(); i++) {
            if (i != stack.size() - 1)
                result.append(stack.get(i)).append("/");
        }
        if (stack.size() > 0)
            result.append(stack.get(stack.size() - 1));
        return result.toString();
    }

    public static void main(String[] args) {
        P71 p71 = new P71();
        System.out.println(p71.simplifyPath("/a/./b/../../c/"));
        System.out.println(p71.simplifyPath("/home/foo/./.././bar"));
        System.out.println(p71.simplifyPath("/a/..."));
        System.out.println(p71.simplifyPath("///eHx/.."));
    }

}
