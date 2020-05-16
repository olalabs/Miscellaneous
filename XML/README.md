# XML MARKUP LANGUAGE

## Setup 

### XML_to_PDF_converter.xsl 

#### Prerequisites and getting the tools
Apache™ FOP 2.4 or later from [Apache™ FOP site](https://xmlgraphics.apache.org/fop/download.html)

#### Generate PDF file
1. Open the console (e.g. powershell)
2. Go to the directory where the downloaded fop.bat file is located
3. You can skip fo file generation and immediately generate a pdf file using the instructions below:

```
.\fop.bat -xml PATH\person_list.xml -xsl PATH\XML_to_PDF_converter.xsl -pdf output_pdf_name.pdf
```

* PATH - is your path where .xml and .xsl files are located
* You can change the names of files above whatever You like 

