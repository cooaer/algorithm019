# Queue及PriorityQueue的源码分析

## Queue

Queue是一种常见的集合结构，也就是我们通常说的队列。在java中，Queue是一个接口，继承自Collection，在日常的使用中则需要使用它的实现类。
Queue接口提供了以下待实现的方法：

* boolean add(E e)：插入元素，如果空间不足，则抛出异常；
* boolean offer(E e)：插入元素，如果空间不足，返回错误；
* remove()：删除队列顶端的元素并返回，如果队列为空，则抛出异常；
* poll()：删除队列顶端的元素并返回，如果队列为空，则返回空；
* element()：获取队列顶端的元素，如果队列为空，则抛出异常；
* peek()：获取队列顶端的元素，如果队列为空，则返回空；

## PriorityQueue

PriorityQueue是基于堆实现的一种数据结构，每次从队列中抛出的都是优先级最高的元素。PriorityQueue的元素需要实现Comparable接口或者在构造方法中体统Compartor参数。不允许存在空元素。
PriorityQueue内部是使用了一棵树来存储数据的，而且是完全二叉树；这棵树满足一个条件：对于任何一个节点，它的优先级都大于它的左右子节点；；这个树按照层级的先后顺序存储在一个数组上，通过一下公式可以在父节点和子节点之间快速转换：
leftNo = parentNo * 2 + 1;
rightNo = parentNo * 2 + 2;
parentNo = (childNo - 1) / 2;
通过树形结构实现PriorityQueue，使得获取元素的时间复杂度为O(1)，插入和删除元素的时间复杂度也仅为O(logN)；

主要关注一下add(E e)方法，内部实际调用的是offer(E e):
* 如果数组的容量不够用话，则扩容；扩容的规则：如果当前容量小于64，则加2；否则增加当前容量的一半；
* 否则在树的最后面插入元素，同时向上调整元素的位置，使其满足父节点优先级大于左右子节点的条件；具体的做法：如果子节点优先级大于父节点，则交换位置；

```
    private static <T> void siftUpComparable(int k, T x, Object[] es) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = es[parent];
            if (key.compareTo((T) e) >= 0)
                break;
            es[k] = e;
            k = parent;
        }
        es[k] = key;
    }

```

remove()方法内部调用poll()，内部逻辑分析如下：
* 如果当前树为空，则返回
* 移除树的根元素，将树的最后一个元素转移到根元素的位置，同时下移调整使这棵树重新满足父节点大于左右节点的条件；具体的做法：如果子节点有优先级大于父节点的情况，则将优先级较大的子节点和父节点交换位置；
```
    private static <T> void siftDownComparable(int k, T x, Object[] es, int n) {
        // assert n > 0;
        Comparable<? super T> key = (Comparable<? super T>)x;
        int half = n >>> 1;           // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = es[child];
            int right = child + 1;
            if (right < n &&
                ((Comparable<? super T>) c).compareTo((T) es[right]) > 0)
                c = es[child = right];
            if (key.compareTo((T) c) <= 0)
                break;
            es[k] = c;
            k = child;
        }
        es[k] = key;
    }
```