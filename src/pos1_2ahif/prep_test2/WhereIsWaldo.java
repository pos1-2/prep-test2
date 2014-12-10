package pos1_2ahif.prep_test2;

import java.util.*;

/**
 * Created by Florian on 09.12.2014.
 */
public final class WhereIsWaldo implements Map<Integer, WhereIsWaldo.HidingPlace> {
    // you _MUST NOT_ modify this class!

    public interface Exercises {
        int findWaldo(WhereIsWaldo whereIsWaldo);
    }

    public interface HidingPlace {
        Object search();
    }

    public interface Waldo {
    }

    public interface Hint {
    }

    public interface HintLeft extends Hint {
    }

    public interface HintRight extends Hint {
    }

    // no need to read any further

    private Map<Integer, HidingPlace> map;

    public WhereIsWaldo(int difficulty) {
        // do not "optimize" the hiding place by reusing refs
        // because then the search delay can be tricked by
        // sneaky pupils
        final HintLeft left = new HintLeft() {
        };
        final HintRight right = new HintRight() {
        };
        final Object nothing = new Object() {
        };

        map = new HashMap<Integer, HidingPlace>(difficulty);

        Random random = new Random();

        final int waldo = random.nextInt();

        map.put(waldo, new HidingPlaceWithDelay(new Waldo() {
        }));

        for (int i = 0; i < difficulty; ++i) {
            final int position = random.nextInt();

            if (map.containsKey(position)) {
                continue;
            }

            if (random.nextDouble() < 0.9) {
                map.put(position, new HidingPlaceWithDelay(nothing));
            } else {
                map.put(position,
                        (position > waldo)
                                ? new HidingPlaceWithDelay(left)
                                : new HidingPlaceWithDelay(right));
            }
        }

        map = Collections.unmodifiableMap(map);
    }

    private static class HidingPlaceWithDelay implements HidingPlace {
        private Object object;

        public HidingPlaceWithDelay(Object object) {
            this.object = object;
        }

        public Object search() {
            while (true) {
                try {
                    Thread.sleep(100);
                    break;
                } catch (InterruptedException e) {
                    System.err.println("No cheating!");
                }
            }
            return object;
        }
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public HidingPlace get(Object key) {
        return map.get(key);
    }

    public HidingPlace put(Integer key, HidingPlace value) {
        return map.put(key, value);
    }

    public HidingPlace remove(Object key) {
        return map.remove(key);
    }

    public void putAll(Map<? extends Integer, ? extends HidingPlace> m) {
        map.putAll(m);
    }

    public void clear() {
        map.clear();
    }

    public Set<Integer> keySet() {
        return map.keySet();
    }

    public Collection<HidingPlace> values() {
        return map.values();
    }

    public Set<Entry<Integer, HidingPlace>> entrySet() {
        return map.entrySet();
    }

    public boolean equals(Object o) {
        return map.equals(o);
    }

    public int hashCode() {
        return map.hashCode();
    }
}
