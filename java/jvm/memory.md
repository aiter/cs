### 堆内存
* PS Old Gen
* PS Eden Space
* PS Survivor Space

### 非堆对内
* Metaspace
* Code Cache 
* Compressed Class Space

### jstat -gccapacity 
 NGCMN    NGCMX     NGC     S0C   S1C       EC      OGCMN      OGCMX       OGC         OC       MCMN     MCMX      MC     CCSMN    CCSMX     CCSC    YGC    FGC
340736.0 340736.0 340736.0 34048.0 34048.0 272640.0   707840.0   707840.0   707840.0   707840.0      0.0 1116160.0  77100.0      0.0 1048576.0   9580.0     25     4
> NGC = Eden + Survivor
> S0/S1 = Servivor
> E = Eden
> OC = Old
> MC = mataspace 
> CCS = Compressed Class Space
