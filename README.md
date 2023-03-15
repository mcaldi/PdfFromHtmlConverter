# PdfFromHtmlConverter

Java (JDK 11) utility for try 2 opensource libs to generate a PDF file from HTML (or FreeMarker Template .ftl). 
This software use:

- openhthmltopdf: https://github.com/danfickle/openhtmltopdf
- flying-saucer (flying-saucer-pdf-openpdf) : https://github.com/flyingsaucerproject/flyingsaucer


### How to compile

Produce a JAR:
```
mvn package
```

### How to use

Compiled JAR is in target folder ( target/PdfFromHtmlConverter-0.0.1-SNAPSHOT-jar-with-dependencies.jar)
To start the application run:

```
java -jar target/PdfFromHtmlConverter-0.0.1-SNAPSHOT-jar-with-dependencies.jar [arguments]
```
```
Arguments:
 -a,--assets <arg>    assets folder absolute path
 -e,--external        if present external path used, false otherwise.
                      Default value false
 -h,--help            help
 -i,--in <arg>        input file absolute path
 -l,--lib <arg>       conversion library - 0 (openhthmltopdf) or 1 (flying-saucer)
 -o,--out <arg>       output file absolute path
 -u,--accessibility   if present, an accessible pdf  is required, false
                      otherwise. Valid only for lib = 0. Default value
                      false
 -x,--pdfa            if present, pdfa generation is required, false
                      otherwise. Valid only for lib = 0. Default value
                      false
```

Example:
```
java -jar target/PdfFromHtmlConverter-0.0.1-SNAPSHOT-jar-with-dependencies.jar  -l 0 -i /home/mcdev/index.html -o /home/mcdev/out.pdf -a  /home/mcdev/static/ -e -u
```

### License

Please see libraries licences:
- https://github.com/danfickle/openhtmltopdf#license   (added into openhtmltopdf-LICENSE folder)
- https://github.com/flyingsaucerproject/flyingsaucer/blob/master/LICENSE (added into flying-saucer-LICENSE folder)

