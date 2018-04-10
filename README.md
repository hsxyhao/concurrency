# 高并发
慕课网高并发实战[课程](https://coding.imooc.com/class/195.html?mc_marking=b587280c0c1c0e76c1092aa21406565a&mc_channel=syb6)，平时学习的时候很少会接触到高并发这一模块，即使
接触到也只是一些概念上的东西，和企业实际开发中的使用可能会有很大的出入通过这门课程的学习来和企业开发中的并发做对接

## 线程安全性
原子性：提供了互斥访问，同一时刻只能有一个线程来对他进行访问
 ``` java
    //var1操作对象，var2值在内存中的偏移地址，var4加上的值
    public final int getAndAddInt(Object var1, long var2, int var4) {
        //主存中的值
        int var5;
        //循环判断，一直等到其他线程同步到主存中
        do {
            //获取主存中最新的值
            var5 = this.getIntVolatile(var1, var2);
            //compareAndSwapInt native方法，判断当前的值和主存的值是否相等，是的话就进行var5 + var4
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
        return var5;
    }
 ```
可见性：一个线程对主内存的修改可以及时的被其他线程观察到
有序性：一个线程观察其他线程中的指令执行顺序，由于指令重排序的存在，该观察结果一般杂乱无序

## 安全发布对象
发布对象：使一个对象能够被当前范围之外的代码使用
对象逃逸：一种错误的发布对象。当一个对象还没有构造完成时，就使它被其他线程所见。

正确发布对象
1. 在静态初始化函数中初始化一个对象引用
 ```java
 //推荐使用，线程安全而且还不会造成资源的浪费
public class SingletonEnum {

    public static SingletonEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonEnum singleton;

        Singleton() {
            singleton = new SingletonEnum();
        }

        public SingletonEnum getInstance() {
            return singleton;
        }
    }
}
 ```
2. 将对象的引用保存到volatile类型域或者AtomicReference对象中
3. 将对象的引用保存到某个正确构造对象的final类型域中
4. 将对象的引用保存到一个由锁保护的域中

## 线程池

线程池优点：
1. 重用存在的线程，减少对象的创建，消亡的开销，性能佳
2. 可有效控制最大并发线程数，提高系统资源利用率，同时可以避免过多资源竞争，避免阻塞
3. 提供定时执行，定期执行，单线程，并发控制等功能

Thread弊端：
1. 每次new Thread新建对象，性能差
2. 线程缺乏统一管理，可能无限制的新建线程，相互竞争，有可能占用过多的系统资源
3. 缺少更多的功能，如多执行，定期执行，线程中断

ThreadPoolExecutor
+ corePoolSize：核心线程数量
+ maximumPoolSize：线程最大线程数
+ workQueue：阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程产生重大影响
+ keepAliveTime：线程池没有任务执行时最多保持多长时间
+ unit：keepAliveTime的时间单位
+ threadFactory：线程工厂，用来创建线程
+ rejectHandler：拒绝处理任务时的策略

当使用submit或execute方法时的逻辑判断
1. 首先如果运行的线程数小于corePoolSize，则会创建新的线程，即使线程池中的其他线程是空闲的也会创建，
2. 如果线程池中的线程数量大于等corePoolSize&&小于等于maximumPoolSize的时候，只有当workQueue满的
时候才会创建新的线程处理任务
3. 如果线程数大于maximumPoolSize并且workQueue满了则会采用拒绝策略

线程池实例状态
1. RUNNING：可以接受任务或处理队列中的任务
2. SHUTDOWN：
3. STOP：
4. TIDYING：
5. TERMINATED：

线程池常用方法
execute()：提交任务，交给线程池执行
submit()：提交任务，能够返回执行结果execute + Future
shutdown()：关闭线程池，等待任务都执行完成
shutdownNow()：关闭线程池，不等待任务执行完成

getTaskCount()：线程池**已经执行和未执行**的总数
getCompletedTaskCount()：已经完成的任务数量
getPoolSize()：线程池当前的线程数量
getActiveCount()：当前线程池中正在执行任务的线程数量

Executor框架接口
+ Executors.newCachedThreadPool：创建一个可缓存的线程池，如果处理任务超过当前线程数则创建线程，如果没有则回收空闲线程
> new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
+ Executors.newFixedThreadPool：创建一个定长的线程池(coorPoolSize=maximumPoolSize)，可以控制线程最大并发数，如果任务超出线程池数量则会在队列中等待
> new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
+ Executors.newScheduledThreadPool：创建一个定长，定时，周期性的线程池
> new ScheduledThreadPoolExecutor(corePoolSize) => super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new DelayedWorkQueue())
+ Executors.newSingleThreadExecutor：创建一个单线程化的线程池，可以保证任务按照一定的顺序执行（先进先出，优先级），如果当前线程异常则会创建一个新的线程完成任务
> new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())

线程池参数配置
+ CPU密集型任务，需要尽量压榨CPU，参数可设置为NCPU+1
+ IO密集型任务，参考值可以设置为2*NCPU

## 死锁
1. 互斥条件
2. 请求和保持条件：线程保持一个资源，有发出另一个资源的请求
3. 不剥夺条件
4. 循环等待条件

解决方案
+ 加锁顺序一致：在案例中可以使flag=0和flag=1两种情况下的加锁顺序保持一致
+ 使用lock进行强制释放锁
+ 死锁检测（难实现）
