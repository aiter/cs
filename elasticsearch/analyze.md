```shell
GET /_analyze
{
  "analyzer": "standard",
  "text": "3 elasticsearch In action"
}

GET /_analyze
{
  "analyzer": "simple",
  "text": "2 elasticsearch In action"
}

GET /_analyze
{
  "analyzer": "whitespace",
  "text": "2 elasticsearch In action"
}

GET /_analyze
{
  "analyzer": "stop",
  "text": "the 2 elasticsearch In action"
}

GET /_analyze
{
  "analyzer": "keyword",
  "text": "the 2 elasticsearch In action"
}

//正则表达式
GET /_analyze
{
  "analyzer": "pattern",
  "text": "the 2 elasticsearch In action"
}

GET /_analyze
{
  "analyzer": "english",
  "text": "the 2 running elasticsearch In action"
}

GET /_analyze
{
  "analyzer": "icu_analyzer",
  "text": "我是中文的技术与实践"
}

GET /_analyze
{
  "analyzer": "standard",
  "text": "我是中文的技术与实践"
}
```
