### class
* class 类
```python
class VideoExtractor():
    def __init__(self):
        self.logger = getLogger(self.name)
        self.url = None
        self.vid = None
```
* 继承
```python
class Iqiyi(VideoExtractor):


class Youku(VideoExtractor):
    name = u"优酷 (Youku)"
    ref_youku = 'https://v.youku.com'
    ref_tudou = 'https://video.tudou.com'
    ckey_default = "DIl58SLFxFNndSV1GFNnMQVYkx1PP5tKe1siZu/86PR1u/Wh1Ptd+WOZsHHWxysSfAOhNJpdVWsdVJNsfJ8Sxd8WKVvNfAS8aS8fAOzYARzPyPc3JvtnPHjTdKfESTdnuTW6ZPvk2pNDh4uFzotgdMEFkzQ5wZVXl2Pf1/Y6hLK0OnCNxBj3+nb0v72gZ6b0td+WOZsHHWxysSo/0y9D2K42SaB8Y/+aD2K42SaB8Y/+ahU+WOZsHcrxysooUeND"
    ckey_mobile = "7B19C0AB12633B22E7FE81271162026020570708D6CC189E4924503C49D243A0DE6CD84A766832C2C99898FC5ED31F3709BB3CDD82C96492E721BDD381735026"

    def __init__(self):
        VideoExtractor.__init__(self)
        self.params = ( 
            ('0503', self.ref_youku, self.ckey_default),
            ('0590', self.ref_youku, self.ckey_default),
            )
```

### 打包发布工具
* pip
* setuptools
> python setup.py build 直接在当前目录下，build一个包 
>
> python setup.py bdist_egg 打包一个test的包。这个测试包，可以直接复制到site-packages目录。和install效果一样
>
> python setup.py install 安装包到/usr/local/lib/python2.7/site-packages/demo-0.1-py2.7.egg

setup.py
```shell
from setuptools import setup, find_packages
setup(
    name = "demo",
    version = "0.1",
    packages = find_packages(),
)
```
demo/__init__.py
```
  1 #!/usr/bin/env python
  2 #-*- coding:utf-8 -*-
  3 
  4 def test():
  5     print "hello world!"
  6 
  7 if __name__ == '__main__':
  8     test()
  9
```
build后目录
```shell
.
├── build
│   ├── bdist.macosx-10.14-x86_64
│   └── lib
│       └── demo
│           └── __init__.py
├── demo
│   ├── __init__.py
│   └── __init__.pyc
├── demo.egg-info
│   ├── PKG-INFO
│   ├── SOURCES.txt
│   ├── dependency_links.txt
│   └── top_level.txt
├── dist
│   └── demo-0.1-py2.7.egg
└── setup.py
```


### 安装路劲
> pip3
```shell
/usr/local/lib/python3.7/site-packages

/usr/local/lib/python2.7/site-packages
/usr/lib/python2.7/

python安装：/usr/local/Cellar/python/3.7.2_1/
```
