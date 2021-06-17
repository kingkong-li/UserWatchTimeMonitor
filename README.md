# UserWatchTimeMonitor
__用户看多久、用户兴趣发掘？列表项观测时间记录__
## 功能概述
### 1 RecyclerView代码埋点检测用户观看每个Item时间，并实时显示。
### 2 RecyclerView无痕埋点检测用户观看每个Item时间，并实时显示。
## 效果展示
http://player.youku.com/embed/XMzk5MzQ2MDYyNA
## 应用场景
### 1 用户兴趣采集发掘
### 2 广告推荐效果监控
## 原理解析 
__当一个Item开始显示记录时间,结束显示再记录一次时间__
### 代码中埋点
当RecyclerView addOnScrollListener之后便需要重写方法onScrolled，在这个方法里面我们可以获取到当前第一个显示的item和最后显示的item，那么我们选定item如果在这个之间并且屏幕亮便是在显示、否则没有显示。当外界条件变化、比如列表滚动、屏幕变暗就计算一次。我把计算部分专门抽成一个类，代码中出现变化比如滚动时候调用就ok，具体看代码。
### 非侵入式埋点
  用AspectJ去织入onScrolled的代码，具体做法见源码；

## 做法步骤
## 难点介绍
### 1 RecyclerView的区分
### 2 RecyclerView item 序列号与内容的映射
## 未来研究
### 1 如果RecyclerView没有重写方法onScrolled该如何采集
### 2 RecyclerView 观看内容采集


**博客地址：https://www.jianshu.com/p/f4cd59330ceb**
