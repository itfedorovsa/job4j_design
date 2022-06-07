package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> curr = new HashMap<>();
        int added;
        int changed = 0;
        int deleted = 0;
        for (User user : current) {
            curr.put(user.getId(), user.getName());
        }
        for (User user : previous) {
            if (!curr.containsKey(user.getId())) {
                deleted++;
            } else if (!curr.get(user.getId()).equals(user.getName())) {
                changed++;
            }
        }
        added = curr.size() + deleted - previous.size();
        return new Info(added, changed, deleted);
    }

}
