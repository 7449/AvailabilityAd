# 简易封装的海外广告框架

## 仅供参考

#### 初始化广告SDK(可选)

    AvailabilityAd.init(context)

#### 创建一个广告载体对象

    AvailabilityAd.create(AdConfig)

#### 请求一个广告对象

    AvailabilityAd.load(Context,AdConfig,Callback)
    //kotlin
    AvailabilityAd.load(Context,AdConfig){ad:->
    }

    or

    AvailabilityAd.loadOrCache(Context,AdConfig,Callback)

#### Config配置

1. 自定义可参考`SimpleAdConfig`
2. 内置了`noCache`,`successful`,`all`三个配置,分别为不进缓存,请求成功进入缓存,不论结果进入缓存
3. 如果需要缓存,则实现`AdCacheConfig`即可,其中`expireTime`则为过期时间
4. 如果需要扩展内置的配置类,具体可参考`AdmobAdConfig`

       data class AdmobAdConfig @JvmOverloads constructor(
            override val id: String,
            override val type: AdType,
            val bannerSize: AdSize = AdSize.BANNER,
            override val cache: AdCacheConfig? = null,
       ) : AdConfig

#### 缓存

1. 缓存为一个单例`Map`集合
2. 提供了各种方便的`Api`,具体可见`AdCache`

#### 展示广告(插屏/native)

1. 请求成功之后拿到`Ad`对象
2. `Ad`调用`show`方法
3. 插屏或者激励广告调用时传入`Activity`,Native广告传入需要承载的`RootView`
4. `Max`平台调用插屏时不需要传入`Activity`,直接调用该方法即可
5. 获取错误信息时`Ad`调用`failure`即可
6. 获取加载时间时`Ad`调用`latencyMillis`即可
7. 一般广告如果不及时替换会造成重复点击,可根据`Ad.repeatedlyClick`判断是否为重复点击

#### 回调

1. 命名基本和`Admob`对齐
2. `Audience`的`paidEvent`固定返回`USD`,`3`
3. `激励广告`触发`onRewarded`,`Audience`默认返回`空字符串`和`0`
4. 插屏回调只返回点击,关闭,展示失败,展示成功四个回调
5. 具体可见`SimpleAdCallback`

## 示例

#### 加载

    AvailabilityAd.load(this, config) {
        it.orCache()?.show(nativeGroupView)
    }

