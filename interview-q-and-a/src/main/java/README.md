# 常见的手写面试题和面试真实遇到的问题

## 单例模式

## 死锁

## 冒泡排序

## 交替打印奇偶数(线程通信)

## 生产者消费者模式(线程通信)

线程间通信的方式

## 共享内存的方式(通过 volatile 来保证内存的可见性)

### 消息传递的方法

使用 `wait()` && `notify()` && `notifyAll()`

或者使用 Condition 接口 ,

```java
private final Lock lock=new ReentrantLock();
private final Condition cond1=lock.newCondition();
```

## 写一个方法求一个数的阶乘

## 判断一个数是不是一个树的子树


