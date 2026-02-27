package badcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BadRacer {

    public boolean doM(int r) {
        return r >= 4;
    }

    public boolean chk(String n) {
        if (n == null) {
            return false;
        }
        if (n.length() > 5) {
            return false;
        }
        if (n.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<String> doW(Map<String, Integer> c) {
        int m = 0;
        for (Map.Entry<String, Integer> e : c.entrySet()) {
            if (e.getValue() > m) {
                m = e.getValue();
            }
        }
        List<String> w = new ArrayList<>();
        for (Map.Entry<String, Integer> e : c.entrySet()) {
            if (e.getValue() == m) {
                w.add(e.getKey());
            }
        }
        return w;
    }
}
