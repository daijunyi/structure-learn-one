package com.daijunyi.structure.hash;

class HashTabMain{
    public static void main(String[] args) {
        HashTab<String, String> tab = new HashTab<>();
        tab.put("123","值2");
        tab.put("12","值4");
        System.out.println("获取值:"+tab.get("123"));
        System.out.println("替换值:"+tab.put("123","值3"));
        System.out.println("获取值:"+tab.get("123"));
        System.out.println("删除值:"+tab.remove("123"));
        System.out.println("再次获取值:"+tab.get("123"));
        System.out.println("获取值:"+tab.get("12"));
    }
}

/**
 * @author djy
 * @createTime 2022/3/1 下午5:11
 * @description
 */
public class HashTab<K,V> {
    private Entry<K,V>[] table;

    private int size;

    public HashTab() {
        super();
        this.size = 10;
        table = new Entry[10];
    }

    public HashTab(int size) {
        this.size = size;
        table = new Entry[size];
    }

    public V put(K key,V value){
        if (value == null){
            throw new NullPointerException();
        }
        int hash = key.hashCode();
        final Entry<K,V>[] tab = table;
        int index = getIndex(hash);
        Entry<K, V> kvEntry = tab[index];
        //替换逻辑
        while (kvEntry != null){
            if (kvEntry.hash == hash && kvEntry.key.equals(key)){
                //说明是同一个
                V old = kvEntry.value;
                kvEntry.value = value;
                return old;
            }
            kvEntry = kvEntry.next;
        }
        //把新的值加到第一个
        tab[index] = new Entry(key,value,hash,tab[index]);
        return null;
    }

    public V get(K key){
        if (key == null){
            throw new NullPointerException();
        }
        int hash = key.hashCode();
        int index = getIndex(hash);
        Entry<K, V> kvEntry = table[index];
        while (kvEntry != null){
            if (kvEntry.hash == hash && kvEntry.key.equals(key)){
                return kvEntry.value;
            }
            kvEntry = kvEntry.next;
        }
        return null;
    }

    public V remove(K key){
        if (key == null){
            throw new NullPointerException();
        }
        int hash = key.hashCode();
        int index = getIndex(hash);
        Entry<K, V> kvEntry = table[index];
        if (kvEntry!=null && kvEntry.hash == hash && kvEntry.key.equals(key)){
            table[index] = kvEntry.next;
            return kvEntry.value;
        }
        Entry<K,V> tmp = kvEntry;
        kvEntry = kvEntry.next;
        while (kvEntry != null){
            if (kvEntry.hash == hash && kvEntry.key.equals(key)){
                tmp.next = kvEntry.next;
                return kvEntry.value;
            }
            kvEntry = kvEntry.next;
            tmp = tmp.next;
        }
        return null;
    }

    private int getIndex(int hash) {
        return (hash & 0x7FFFFFFF) % size;
    }

    private static class Entry<K,V>{
        V value;
        final K key;
        final int hash;
        Entry<K,V> next;

        public Entry(K key, V value,int hash, Entry<K, V> next) {
            this.value = value;
            this.key = key;
            this.hash = hash;
            this.next = next;
        }
    }
}
