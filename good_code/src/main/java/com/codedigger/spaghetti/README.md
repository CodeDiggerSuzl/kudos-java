
# 理论篇｜如何避免写出面条代码？
https://mp.weixin.qq.com/s/7YQ_Wb9mLdROlm5gIC-9uw
> 本文是“面条代码系列”文章的第一篇，在这个系列里，我将从理论开始，再通过实践和大家一起来探讨如何才能写好代码。

## 面条代码系列文章目的

一直以来有一个问题困扰着我，大家好像都在不断的批判面条代码，但不知不觉中，每个人又不停的在写着面条代码，为什么？随着与越来越多的人沟通，我慢慢了解到，这其实是一个非常底层的问题，是一个对什么是好代码的认知的问题，所以要解决面条代码的问题，还是要从什么是好代码着手。
我期望通过本文，能颠覆你对好代码的认知，让你能从认知上有一个转变，“哥白尼式的转变”，什么意思呢？
你试想一下，当你现在回过头去看“地心说”时，你会有什么想法？而在之前一段很久很久的时间里，人们一直是坚信着地球才是宇宙的中心啊！当你理解了“日心说”以后，就再也回不去了，你已经没有办法再回到“地心说”了，并不是以前的人傻，而是你的认知发生了改变，一种不可逆的改变。因此，今天我试图从“什么是好代码”的认知讲起，期望能刷新你对好代码的认识，从而解决“面条代码’的问题。
什么是好代码

什么是好代码？我相信每个人心中都会有自己的答案，比如抽象层度、可扩展、可读性、是否符合设计模式等等，好代码的维度有很多，这些都没有错，今天我想从另一个维度来聊聊什么是好代码。
先说一个东西“星盘”，星盘是古代天文学家用来进行天文测量的仪器，通过转动星盘，可以模拟各天体的运动，从而预测太阳、月亮、金星、火星等天体在宇宙中的位置。星盘是天空模型的机械实现。通过星盘这个模型完整的呈现了星空中各星体的运行。在这个星盘中，沉淀了大量对于星体运行轨迹的知识，这些知识都融合进了星盘这个模型中。而我们的代码是把我们运行在线下的日常业务，通过软件模型呈现到了我们的系统中，同样线下的业务中含有大量的业务知识，这些业务知识有时候是一个行业几十年的沉淀。如果我们的代码只是实现了功能，但没有与业务知识相匹配的模型，虽然功能实现了，但无法承载海量的业务知识，随着业务不断的衍进，必然会让线下业务与线上的系统脱节，从业务到系统的翻译成本越来越大，系统对业务的表达力越来越弱，就算一个小需求，从业务到产品，再到技术和测试，整个沟通成本也会很大。这时，我们就会说这个系统的代码太烂了，已经无法维护了。
因此，一个小小的星盘，完整的阐释了“什么是好代码”，好代码一定是能够完整的表达业务，能够把现实中的业务反映在我们的系统中。
到这里，我相信我们已经有目标了，如何才能写出像星盘一样的代码？我们先从一段面条代码开始。

## 面条代码的由来

面条代码（spaghetti code）是指非结构化和难以维护的源代码，要了解面条代码，我们先从下面的一个需求开始。
人员签到的需求：
* 当签到时间>班次开始时间N分钟后，签到状态为迟到
* N根据不同的业务可以灵活配置，比如:门店为5，配送为0
  根据上面的需求，再来看一下我们的实现代码：
```java

public void 人员签到(班次ID，人员ID) {
班次 = 班次Repo.获取班次(班次ID)

    迟到可容忍时间 = 配置服务.获取可容忍时间(业务域);

    签到状态 = null;
    if( 签到时间 > (班次.开始时间 +迟到可容忍时间)) {
      签到状态 = 迟到;
    } else {
      签到状态 = 正常;
    }

    //更新数据库状态 
}
```

看完上面的实现代码，大家想一下，如果让你来写，你会怎么写呢？如果大家没有看出问题来，那很抱歉，我可以说大家平时就是在写面条代码。

上面的代码有两个问题:
迟到可容忍时间 = 配置服务.获取可容忍时间(业务域);

问题一：在签到方法内需要去查询配置服务，我相信这样的场景会出现在我们大量的方法中，有时候一个操作需要查询大量的其它服务来准备数据，慢慢的我们的方法会越来越长，即便我们可以把这些查询封装在一个方法中，但其实不解决实质性的问题。
```java
    if( 签到时间 > (班次.开始时间 +迟到可容忍时间)) {
      签到状态 = 迟到;
    } else {
      签到状态 = 正常;
    }
```

问题二：签到的业务逻辑揉合在签到方法中，形成了面向过程式的代码片段，随着业务的不断变化，签到方法有可能会越来越复杂。

这两个问题是所有面条代码中最典型的例子，问题一和问题二在复杂的现实业务中，可以演变成庞然大物，有时为了做某个操作，我们可能需要写上百行的代码来准备数据，有时一个复杂的业务逻辑，我们也可能会写上上百行代码。如果能解决上面的两个问题，我们就能在很大层度上解决面条代码的问题，接下来，我们来看看怎么来解决？
## 如何解决面条代码的问题

其实你并不孤单，关于上面的面条代码代码早在2004年就已经被人提出来了，要解决这个问题，我们需要引入三个模式：
值对象(Value Object)
无副作用方法(Side-Effect-Free Function)
语义化接口(Intention-Revealing Interface)
模式一：值对象(Value Object)

我们从一个灵魂拷问开始，在问题一中，“迟到可容忍时间”是可以按不同的业务配置的，那在处理迟到时，当然要从一个配置服务中去获取啊，感觉没毛病。真的是这样吗？
在上面代码中，我们有一个“班次”模型，除了入参“签到时间”外，为什么我们还需要模型之外的数据？大家有没有想过，是不是我们的模型有问题？既然系统要反应真实的业务，那我们回到现实的业务中，当班次安排下去以后，“迟到可容忍时间”还会有变化吗？今天“迟到可容忍时间”是5分钟，明天是10分钟，会这样吗？当然不是，当一个班次安排下去后，“迟到可容忍时间”必然是已经确定了的，如果真的要发生变化，业务也会在班次安排之前，提前通知到大家。因此，当班次被创建出来时，“迟到可容忍时间”就已经确定了，而不是当发生签到时，再去获取“迟到可容忍时间”。
当然，除了“迟到可容忍时间”外，对于班次，我们其实还有很多这样的配置，我们把这些配置抽象成一个对象“班次配置”，然后在班次生成的时候，把这些数据放进去，在班次持久化时，再把班次配置冗余到班次对象上。“班次配置”对象就是一个值对象。
再回过头来看问题一，因为我们对业务的理解不够深入，没有把业务完全的反映到我们的模型和代码上，导致了每次签到，都需要去查询配置服务。再来看我们的解决方案，“班次”对象上冗余了“班次配置”，我们再也不需要在签到时，去查询配置服务了，班次本身就能回答本次签到是否迟到了。
那我们是否就能把“考勤状态判定”这件事交给“班次”呢？我们进入下一个话题，无副作用方法。
模式二：无副作用方法(Side-Effect-Free Function)

“副作用”原意是指“意外的结果”，而在这里无副作用方法(Side-Effect-Free Function)是指方法被调用后，不会对系统状态产生任何影响。那很多同学就会觉得自己之前写的service全都是无副作用方法，真的是这样吗？如果从定义上来看，没错，是的。但是我今天想和大家聊的不是一个方法，而是一种架构，这有什么区别吗？回答这个问题前我先提一个问题：
你说你的方法是无副作用方法，我凭什么相信你？
很多时候，一个看似“无副作用”的方法，调用了方法A，方法A再调用方法B，再调用方法C，方法D，方法E，最后在方法E中更新了一下数据库状态。所以你有什么底气回答上面的问题？凭什么？可能有读者觉得我是在抬杠，当然不是，我是在讲一个严肃的架构问题。好的架构，是能让你的系统中能产生一个像“重力”一样的东西，就像建筑学中的“重力”你必须得去遵守它。好的软件架构，也是一样的，能够创造出一个像“重力”一样的规则，让你不得不遵守它。请看下面的定义：

只有把无副作用方法，放到值对象上以后，它才是可被相信的“无副作用”方法。
值对象本身从严格意义上来说是一个不可变对象，我们只能替换它，而不能改变它。同时在值对象当中，我们是无法访问其它service的。基于这两个前提，放在值对象上的“无副作用”方法，就是完完全全可被信任的方法，这样的方法是最好的资产，可以被其它同学放心的复用。

接下来让我们继续回到上面的代码，我们要把考勤状态判定抽成一个“无副作用”方法，同时还要把班次变成一个值对象，看下面班次的示例代码：
```public class 班次 {
班次配置;
开始时间;
结束时间;

    签到状态判定(签到时间) {
        if( 签到时间 > (开始时间 +班次配置.迟到可容忍时间)) {
    return 迟到;
        } else {
    return 正常;
        }
    }
}
```
上面代码中的方法就是长在“值对象”上的一个“无副作用”方法，这样的方法是明确抽象了业务逻辑的方法，是最好的可复用资产。

当然，在我们抽象方法时，需要为抽象出来的方法取一个名字，而取名通常会难倒一大批“高手”，其实我们完全错了，不是你不会取名字，而是你的架构有问题，接下来我们来聊最后一个问题。


模式三：语义化接口(Intention-Revealing Interface)

一个容易被重用的方法或类至少要满足下面这个条件：
看到类或方法，别人就知道这个类或方法是做什么的
如何给方法取名字？如果你觉得是因为你英文不好导致的取不出好名字，那你又错了。取名字和英文好坏没关系，因为我可以很负责任的和你说，就算你用中文给方法取名字，你还是无法通过名称来表达出方法真正做的事情。因为这是人与人之间的共识问题，你不可能通过一个名称来和别人达成共识，达成共识是一个很复杂的过程，通常要经过沟通、确认，再沟通再确认，多次沟通后，人与人之间才能达成某种共识。在写代码时，我们不可能针对每一个我需要重用的方法，找写这个方法的人一遍又一遍的沟通和确认，那还不如我自己看一下方法里面的逻辑，或者自己重写一个，这才是我们今天面临的真正问题，因为无法达成共识，导致代码很难被重用。

要解决这个问题，我们要引入第三个模式，语义化接口(Intention-Revealing Interface)，语义化接口是指用业务正在使用的语言来为我为接口或方法命名，而业务正在使用的语言，我们在MRD、PRD阶段已经为此达成过共识了，这是业务、产品、开发和测试都能理解的，你也可以把它理解成DDD中的“通用语言”。如果领域层的模型和模型上的方法都是“语义化”的，那我相信，这样的代码是可被理解，并且没有歧义的，使用方可以去放心使用的。

最后放上修改前后的示意代码：

总结

本文的目的是期望能刷新你对好代码的认识，从而解决“面条代码’的问题。
我们先通过定义什么是“好代码”，让大家先从认知上理解，不是“面条代码”有问题，而是我们自己的认知有问题。
当我们认可了“好代码”的标准后，通过一个例子，借助三个模式，完成了代码的重构，这三个模式分别是：
模式一：值对象(Value Object)
模式二：无副作用方法(Side-Effect-Free Function)
模式三：语义化接口(Intention-Revealing Interface)
当然，这是一个非常简单的例子，我在这里只是通过这个例子表达我的观点，实际项目中的需求比这复杂的多的多的多，这是这个系列的第一篇，理论篇，后面我将会通过实际的例子，更加详细的说明我们是如何把这个理论运用到我们日常的代码中的。

扩展阅读：
领域驱动设计：https://book.douban.com/subject/26819666

