### 索引创建
> 直接使用POST一个文档
```shell
POST users/_doc
{
  "user" : "Mike",
  "post_date" : "2019-08-10T05:46:12",
  "message" : "trying out kibana"
}
// 批量操作
POST _bulk
{ "index" : { "_index" : "test", "_id" : "1" } }
{ "field1" : "value1" }
{ "delete" : { "_index" : "test", "_id" : "2" } }
// 批量查询
GET /_mget
{
    "docs" : [
        {
            "_index" : "test",
            "_type" : "_doc",
            "_id" : "1"
        },
        {
            "_index" : "test",
            "_type" : "_doc",
            "_id" : "2"
        }
    ]
}

// 指定id查询
GET users/_doc/1

POST users/_doc
{
  "user" : "Mike",
  "post_date" : "2019-08-10T05:46:12",
  "message" : "trying out kibana"
}

GET users/_doc/bKBheGwBLTAv2XOOYtU1

GET users/_doc/baBieGwBLTAv2XOORtUN

PUT users/_doc/1?op_type=create
{
  "user" : "leya",
  "post_date" : "2019-08-10T05:46:12",
  "message" : "trying out kibana"
}

PUT users/_doc/1
{
  "user" : "Mike"
}
// 更新
POST users/_update/1/
{
  "doc": {
    "post_date" : "2019-08-10T05:57:12",
    "message" : "trying out kibana123"
  }
}
```
