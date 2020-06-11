#第一周学习笔记

## 作业

### Queue源码分析

Queue是一个FIFO（先进先出）规则的数据结构，类似生活中的排队。

Java中Queue是一个接口，定义了以下几个方法：

- add
  - 向队尾添加元素，若队列满了，则抛出IllegalStateException异常
- offer
  - 向队尾添加元素，若队列满了，则返回false
- remove
  - 删除并返回队首元素，若队列为空，则抛出NoSuchElementException异常
- poll
  - 删除并返回队首元素，若队列为空，则返回null
- element
  - 返回队首元素，若队列为空，则抛出NoSuchElementException异常
- peek
  - 返回队首元素，若队列为空，则返回null



### Priority Queue源码分析

Priority Queue优先队列，虽然是队列，但是其出队顺序是按照优先级大小排序，其添加的对象需要实现Comparable接口，用于比较优先级。

Java中的PriorityQueue继承了AbstractQueue（实现了Queue的接口方法），其主要方法：

- add
  - 与offer方法等价，调用的offer方法添加元素
- offer
  - 添加元素，若队列满了，则进行扩容（容量小于64，双倍扩容；否则每次增加50%）
  - 添加新元素后，对数据进行排序
- peek
  - 返回队首元素（优先级最大的）
- remove
  - 查询并删除指定元素，剩余元素重新排序（堆）
  - 不带参数的remove方法，调用的是poll
- poll
  - 删除并返回队首元素，并进行重新排序（堆）

