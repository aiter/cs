### 使用Transfer-Encoding:chunked 保持长连接
> client 一直等到数据，server有数据就发送

```go
package main

import (
    "fmt"
    "net/http"
    "time"
)

func main() {
    msgs := make(chan string, 5)

    http.HandleFunc("/pub", func(w http.ResponseWriter, r *http.Request) {
        msgs <- "msg1"
        fmt.Fprintf(w, "ok!")
    })  
    http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
        w.Header().Set("Transfer-Encoding", "chunked")
        data := []byte("Welcome to my website!")
        w.Write(data)
        if f, ok := w.(http.Flusher); ok {
            f.Flush()
        }
        for {
            msg := <-msgs
            w.Write([]byte(msg))
            if f, ok := w.(http.Flusher); ok {
                f.Flush()
            }
        }
        time.Sleep(2000 * time.Millisecond)
        fmt.Fprintf(w, "Welcome to my website!2222222")
    })  

    http.ListenAndServe(":8090", nil)
}
```
