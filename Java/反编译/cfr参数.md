# cfr参数

输入下列语句可以查看cfr支持的所有参数：

```
java -jar .\cfr-0.149.jar --help
```

CFR 0.149

   --aexagg                         (boolean)
   --aggressivesizethreshold        (int >= 0)  default: 15000
   --allowcorrecting                (boolean)  default: true
   --analyseas                      (One of [DETECT, JAR, WAR, CLASS])
   --arrayiter                      (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --caseinsensitivefs              (boolean)  default: true  不区分大小写
   --clobber                        (boolean)
   --collectioniter                 (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --commentmonitors                (boolean)  default: false
   --comments                       (boolean)  default: true
   --decodeenumswitch               (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --decodefinally                  (boolean)  default: true
   --decodelambdas                  (boolean)  default: true if class file from version 52.0 (Java 8) or greater
   --decodestringswitch             (boolean)  default: true if class file from version 51.0 (Java 7) or greater
   --dumpclasspath                  (boolean)  default: false
   --eclipse                        (boolean)  default: true
   --elidescala                     (boolean)  default: false
   --extraclasspath                 (string)
   --forcecondpropagate             (boolean)
   --forceexceptionprune            (boolean)
   --forcereturningifs              (boolean)
   --forcetopsort                   (boolean)
   --forcetopsortaggress            (boolean)
   --forloopaggcapture              (boolean)
   --hidebridgemethods              (boolean)  default: true
   --hidelangimports                (boolean)  default: true
   --hidelongstrings                (boolean)  default: false
   --hideutf                        (boolean)  default: true
   --ignoreexceptions               (boolean)  default: false
   --ignoreexceptionsalways         (boolean)  default: false
   --importfilter                   (string)
   --innerclasses                   (boolean)  default: true
   --instanceofpattern              (boolean)  default: true if class file from version 58.0 (Java 14) or greater, or experimental in 58.0 (Java 14)
   --j14classobj                    (boolean)  default: false if class file from version 49.0 (Java 5) or greater
   --jarfilter                      (string)
   --labelledblocks                 (boolean)  default: true
   --lenient                        (boolean)  default: false
   --liftconstructorinit            (boolean)  default: true
   --methodname                     (string)
   --obfuscationpath                (string)
   --outputdir                      (string)
   --outputpath                     (string)
   --override                       (boolean)  default: true if class file from version 50.0 (Java 6) or greater
   --previewfeatures                (boolean)  default: true
   --pullcodecase                   (boolean)  default: false
   --recordtypes                    (boolean)  default: true if class file from version 58.0 (Java 14) or greater, or experimental in 58.0 (Java 14)
   --recover                        (boolean)  default: true
   --recovertypeclash               (boolean)
   --recovertypehints               (boolean)
   --relinkconststring              (boolean)  default: true
   --removebadgenerics              (boolean)  default: true
   --removeboilerplate              (boolean)  default: true
   --removedeadmethods              (boolean)  default: true
   --removeinnerclasssynthetics     (boolean)  default: true
   --rename                         (boolean)  default: false
   --renamedupmembers               (boolean)  default: Value of option 'rename'
   --renameenumidents               (boolean)  default: Value of option 'rename'
   --renameillegalidents            (boolean)  default: Value of option 'rename'
   --renamesmallmembers             (int >= 0)  default: 0
   --showinferrable                 (boolean)  default: false if class file from version 51.0 (Java 7) or greater
   --showversion                    (boolean)  default: true
   --silent                         (boolean)  default: false
   --skipbatchinnerclasses          (boolean)  default: true

<font color = orange>--stringbuffer</font>                   (boolean)  default: false if class file from version 49.0 (Java 5) or greater
<font color = orange>--stringbuilder</font>                   (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --stringconcat                   (boolean)  default: true if class file from version 53.0 (Java 9) or greater
   --sugarasserts                   (boolean)  default: true
   <font color = orange>--sugarboxing</font>                    (boolean)  default: true
   --sugarenums                     (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --switchexpression               (boolean)  default: true if class file from version 57.0 (Java 13) or greater, or experimental in 56.0 (Java 12)
   --tidymonitors                   (boolean)  default: true
   --tryresources                   (boolean)  default: true if class file from version 51.0 (Java 7) or greater
   --usenametable                   (boolean)  default: true
   --help                           (string)

Please specify '--help optionname' for specifics, eg
   --help pullcodecase

**使用实例**

```
java -jar cfr-0.149.jar Main.class --stringbuilder false
```

