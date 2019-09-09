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
* 
