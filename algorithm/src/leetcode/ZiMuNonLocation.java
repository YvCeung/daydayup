package leetcode;

import java.util.*;

/**
 * @Description 字母异位词分组
 * @Author zy
 * @Date 2024/7/29 23:48
 **/
public class ZiMuNonLocation {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            //转换成char数组以便排序
            char[] charArray = str.toCharArray();
            //相同的异位词排序后是一样的
            Arrays.sort(charArray);
            String key = new String(charArray);
            List<String> list = map.getOrDefault(key,new ArrayList<>());
            list.add(str);
            //之所以重新put一次，是因为返回的list可能是新建的，因为新建的不会跟key进行关联
            map.put(key,list);
        }
        return new ArrayList<>(map.values());
    }
}
