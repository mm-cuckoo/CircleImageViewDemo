
# ImageView图片圆形显示
### 效果
![这里写图片描述](http://img.blog.csdn.net/20160606225021310)
### 功能介绍
以下功能都可通过代码动态进行设置
> 1. 使用默认样式，图片未进行圆形处理
> 2. 设置图片为填充模式
> 3. 设置图片圆形边框大小
> 4. 设置图片圆形边框的颜色

### 用法
1. 将工程中的CircleImageViewBase.java 类复制到你项目中。
2. 创建一个类继承CircleImageViewBase.java 类。
3. 实现initConfig()方法。
4. 在initConfig() 方法中就该自定义View 进行统一设置。

### 自定义设置
1. #### 默认样式
> 调用setUseDefaultStyle(boolean useDefaultStyle) 方法即可设置使用默认样。
>> 1. 如果使用默认样式，该自定义控件将不会对图片进行圆形处理，同样，设置的填充样式、边框大小、边框颜色也将不会起作用。
>> 2. false 不启用默认样式； true 启用默认样式，默认false


2. #### 图片填充模式设置
> 调用public void setIsFill(boolean isFill) 方法即可设置图片圆处理填充模式。
>> 1. 图片进行填充处理，当图片大小小于设置的大小时，会自动拉伸图片使图片填充满整个圆。如果未进行设置图片不会进行填充处理。
>> 2. false 不启用； true 启用，默认false

3. #### 图片圆形边框大小设置
> 调用setBorderWidth(int borderWidth) 方法即可设置图片圆形边框大小。
>> 边框大小默认为 10， 如果设置为0 ，边框颜色将不起作用。

4. #### 图片圆形边框的颜色设置
> 调用setBorderWidth(int borderWidth) 方法即可设置图片圆形边框颜色。
>> 边框颜色默认为白色（#FFFFFF）。
