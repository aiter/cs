### http请求时,使用request body 传输数据
#### raw
> 转换为curl
```shell
curl -X POST \
  'http://localhost:8080/v2/schedule?request_id=request-id-123' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: c9d572b4-bc94-45f5-865d-ab151a4a8ba3' \
  -H 'cache-control: no-cache' \
  -d '{
	"userid":1232345,
	"region":{
		"ids":["ut-id1","ut-id2"],
		"num":2
	}
}'
```

#### x-www-form-urlencoded
> 转换为curl
```shell
 curl -X POST \
  'http://localhost:8080/v2/schedule?request_id=request-id-123' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Postman-Token: df495c05-e390-490e-a2c9-4de412ac59d2' \
  -H 'cache-control: no-cache' \
  -d 'userid=123444&region=%7B%0A%20%20%20%20%22ids%22%3A%5B%0A%20%20%20%20%20%20%20%20%22ut-%E5%A4%A7%E8%BF%9E%E7%94%B5%E4%BF%A1%22%2C%20%22ut-%E6%B2%88%E9%98%B3%E7%94%B5%E4%BF%A1%22%2C%20%22ut-%E5%A4%A7%E8%BF%9E%E7%A7%BB%E5%8A%A8%22%0A%20%20%20%20%5D%0A%7D&undefined='
```

#### form-data
> -F text的像cccc=ccc-value，可以通过servlet server中的parameter获取
```shell
curl -X POST \
  'http://localhost:8080/v2/schedule?ts=1565307822&request_id=request-id-123' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Postman-Token: ad2a3eb4-d40c-4df9-97d2-558b8f74469b' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'ddd=@/Users/liuyunjian/Downloads/未命名文件.jpg' \
  -F cccc=ccc-value
```
