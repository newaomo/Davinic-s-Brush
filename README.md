Davinic's Brush
===================


这是一款 Android App, 需要Android5.0+, GPL v3 License的环境支持。

> **现在Davinic's Brush已经面对人群部分开放了，欢迎扫码下载 !**


<a href="https://github.com/newaomo/Davinic-s-Brush">
  <img alt="Google Play"  width="200" height="200"
       src="https://qr.api.cli.im/qr?data=https%253A%252F%252Fgithub.com%252Fnewaomo%252FDavinic-s-Brush&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=3c47eee500a82e04a7ffef516c039d15" />
</a>

## Introduction
Davinci’s brush是一款基于深度学习技术的海报生产及图像设计协作平台。

截图
--------------
<img width="25%" height="25%" src="https://note.youdao.com/yws/public/resource/a3bcf3f0737f6e53baf0e81ec7a4d793/xmlnote/73151585623D4354ADC04BD42348CCA4/208"/>
<img width="35%" eight="30%"
src="https://note.youdao.com/yws/public/resource/a3bcf3f0737f6e53baf0e81ec7a4d793/xmlnote/22152A302AAC4AB1A7F4A5FC1EA7E28E/210"/>
<img width="35%" height="50%" src="https://note.youdao.com/yws/public/resource/a3bcf3f0737f6e53baf0e81ec7a4d793/xmlnote/A716D76535EE46C68923F8B1A96A6FCC/212"/>

* * *

项目功能介绍
--------------
- 人工智能生成海报
    - 支持选择不同的海报模式
    - 用户可选择海报的尺寸、风格、文本
    - 用户可上传不定量图片、文件作为素材
    - 用户可选择简约模式一键生成
    - 用户可以浏览新闻并一键生成海报分享
- 海报编辑
    - 用户可修改得到的图层形式的海报
    - 可以向海报中添加图片、文字贴纸、标签等
    - 可以为海报添加滤镜，剪裁、旋转海报等
    - 可以修改海报的亮度、对比度、透明度等
- 个人图库
    - 用户可以上传图片到服务器进行智能分类
    - 通过风格、颜色对图片进行筛选，快速得到需要的类型素材
    - 用户可自定义上传图片到个人图库
- 登录注册
    - 用户输入电话、密码信息进行登录操作
    - 用户输入电话获取验证码
    - 用户输入电话、验证码、密码等信息进行注册
- 其他
    - 用户可以编辑个人资料来获取智能推荐服务
    - 日//夜间模式的切换
    - 选择语音交互方式
    - 对生成的海报通过评分进行反馈
    - 查看历史记录、个人收藏
    - 根据人群推荐优秀海报及素材
    - 查看应用使用统计情况



* * *

项目架构介绍
--------------

<img width="25%" height="25%" src="https://note.youdao.com/yws/public/resource/a3bcf3f0737f6e53baf0e81ec7a4d793/xmlnote/73151585623D4354ADC04BD42348CCA4/208"/>
<img width="35%" eight="30%"
src="https://note.youdao.com/yws/public/resource/a3bcf3f0737f6e53baf0e81ec7a4d793/xmlnote/22152A302AAC4AB1A7F4A5FC1EA7E28E/210"/>
<img width="35%" height="50%" src="https://note.youdao.com/yws/public/resource/a3bcf3f0737f6e53baf0e81ec7a4d793/xmlnote/A716D76535EE46C68923F8B1A96A6FCC/212"/>


- activity
    - 各个活动
    - 部分功能的完整模块
- adapter
    - 适配器
- beans
    - 实体类
- colorUi
    - 夜间模式
- file_select
    - 不同模式的文件选择
- fragment
    - 碎片实体类
- mydb
    - 数据库功能实现的代码
- view
    - 界面中使用的过渡动画、个性化控件等
- widget
    - 定制化后的系统控件
- colorfulnews
    - 新闻端
* * *



Gradle 构建
--------------
- 版本
    - Android SDK：2.3.3
    - Gradle
- 环境变量
    - ANDROID_HOME
    - GRADLE_HOME，同时把bin放入path变量
- Android SDK 安装，版本保持一致
    - Android SDK Build-tools 
    - Google Repository
    - Android Support Repository
    - Android Support Library
- 编译
    - `./gradlew assembleDebug`，编译好的apk在build/outputs/apk下面，默认用的是 debug.keystore 签名

## Report a bug or request a feature
Before creating a new issue please make sure that same or similar issue is not already created by checking 
[open issues][5] and [closed issues][6] *(please note that there might be multiple pages)*. If your issue is already 
there, don't create a new one, but leave a comment under already existing one.

Checklist for creating issues:

- Keep titles short but descriptive.
- For feature requests leave a clear description about the feature with examples where appropriate.
- For bug reports leave as much information as possible about your device, android version, etc.
- For bug reports also write steps to reproduce the issue.

[Create new issue][4]

## Contact me

 If you have a better idea or way on this project, please let me know, thanks :

[Email](mailto:578552861@qq.com)


### License
```
Copyright 2015 newaomo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


[4]: https://github.com/newaomo/Davinic-s-Brush/issues
