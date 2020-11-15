# HashMap总结

HashMap是一种存储键值对的数据结构，在日常的编程中经常用到。

一些基本操作的时间复杂度：
添加：O(1)
删除：O(1)
查询：O(1)

* 底层是基于数组+链表/二叉树组成的，数组用来存储Key，链表或者二叉树用来存储value；
* 默认初始化大小为16，默认负载因子为0.75；当桶大小达到最大值的0.75时，将桶的容量翻倍；
* 扩充容量会进行rehash、复制数据，非常耗能，所以尽可能的设置一个合适的容量；

### 存储数据：put(K key, V value)

* 计算hash
* 如果数组为空，则初始化
* 根据hash计算出在桶的位置，如果当前为空，则表示没有冲突，直接创建一个新桶
* 如果当前桶为二叉树，则按照二叉树的方式写入键值对
* 如果是链表，则遍历链表，如果找到相同的key，覆盖之前的数据，否则在链表最后插入数据，如果链表大小超过预设的阀值，则转为红黑树
* 如果当大小达到最大容量的0.75，则进行扩容

```
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
            boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}

```

### 获取数据 get(K key)

* 计算hash值
* 根据hash值计算出桶在数组中的位置，找到对应位置的桶，如果桶为空，返回空
* 如果桶的节点为为树，则按照红黑树的方式查找返回
* 如果为链表，则遍历链表，找到key，返回对应的value


```
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(key)) == null ? null : e.value;
}

final Node<K,V> getNode(Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n, hash; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & (hash = hash(key))]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```