# 常见的手写面试题和面试真实遇到的问题

## 单例模式

## 死锁

## 冒泡排序

## 交替打印奇偶数(线程通信)

## 生产者消费者模式(线程通信)

使用 `wait()` && `notify()` && `notifyAll()`

或者使用 Condition 接口 ,

```java
private final Lock lock=new ReentrantLock();
private final Condition cond1=lock.newCondition();
```
