### datetime & timestamp
* 都包含date和time
| date | datetime | timestamp |
| --- | --- | ---|
|2019-11-25 |2019-11-25 03:48:55|2019-11-25 03:48:55|
|'1000-01-01' to '9999-12-31' | '1000-01-01 00:00:00' to '9999-12-31 23:59:59' | '1970-01-01 00:00:01' UTC to '2038-01-19 03:14:07' UTC|
* UTC(Coordinated Universal Time )
* 有一个变量，表示当地时间(time zone)
```
<pre>
> SELECT @@GLOBAL.time_zone, @@SESSION.time_zone;
+--------------------+---------------------+
| @@GLOBAL.time_zone | @@SESSION.time_zone |
+--------------------+---------------------+
| SYSTEM             | SYSTEM              |
+--------------------+---------------------+
</pre>
```

* 可以设置不同的time_zone。
> mysql.time_zone_name 表里，有支持的所有time_zon 
> Asia/Tokyo
> Asia/Shanghai
> ...
> SET time_zone = timezone; //设置当前session，不是全局global设置
> set time_zone="Asia/Shanghai";

```
<pre>
mysql> update datetime_test set d_timestamp = '2019-11-11 11:11:11',d_datetime='2019-11-11 11:11:11' where id=1;

mysql> select * from datetime_test limit 2;
+----+------------+---------------------+---------------------+
| id | d_int      | d_timestamp         | d_datetime          |
+----+------------+---------------------+---------------------+
|  1 | 1574653735 | 2019-11-11 11:11:11 | 2019-11-11 11:11:11 |
|  2 | 1574653735 | 2019-11-25 11:48:55 | 2019-11-25 03:48:55 |
+----+------------+---------------------+---------------------+

mysql> set time_zone="Asia/Tokyo";  //修改time_zone.东京时间比北京快一个小时

mysql> select * from datetime_test limit 2;
+----+------------+---------------------+---------------------+
| id | d_int      | d_timestamp         | d_datetime          |
+----+------------+---------------------+---------------------+
|  1 | 1574653735 | 2019-11-11 12:11:11 | 2019-11-11 11:11:11 |
|  2 | 1574653735 | 2019-11-25 12:48:55 | 2019-11-25 03:48:55 |
+----+------------+---------------------+---------------------+

// timestamp由于存储的时候是根据字段value和设置的时区(time_zone)转化为UTC存储的。展示时，也就根据UTC存储时间+time_zone转化为time_zone时间展示。datetime存储的是字段的value值
</pre>
```


[1](https://www.eversql.com/mysql-datetime-vs-timestamp-column-types-which-one-i-should-use/)
[2](https://dev.mysql.com/doc/refman/5.7/en/datetime.html)
