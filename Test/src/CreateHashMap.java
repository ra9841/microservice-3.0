public class CreateHashMap {


    static class Entry {
        Object key;
        Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] entry;
    private static final int initialCapacity = 16;

    public CreateHashMap() {
        this.entry = new Entry[initialCapacity];
    }

    public Object get(Object key) {
        int index = getIndex(key);
        for(int i = index; i < entry.length; i++ ) {
            if(null != entry[i] && entry[i].key.equals(key)) {
                return entry[i].value;
            }
        }

        return null;
    }


    public void put(Object key, Object value) {
        int index = getIndex(key);
        for(int i = index; i < entry.length; i++) {
            if(null == entry[i] || entry[i].key.equals(key)) {
                entry[i] = new Entry(key, value);
                return;
            }
        }
    }

    private static int getIndex(Object key) {
        return Math.abs(key.hashCode()) % initialCapacity;
    }






}
