# Miscellaneous
Small projects in various languages.

## Table of contents
* [C#](#C#)
* [Perl](#Perl)
* [Python](#Python)
* [Xml Markup Language](#Xml-Markup-Language)

## C# 

**WINDOWS FORMS APPS**

* **NamesFilter** - name suggestion program that uses the "Dictionary" data structure. 

The program consists of 4 elements:
1. time measurement,
2. loading data from a file,
3. data transformation,
4. user interface for suggesting names (two- and three-letter beginnings).

The program uses a file with names sorted from most often
to the least common surnames in the PESEL database (password: cs2020).

* **ProjectFilesCopier**  - program that creates a copy folder of project files without binaries and generates .zip.

To view the contents of the project file (.csproj) I used the xmlDocument class (since these are xml files). Based on the content of these files, I found all files included in the project (sections Compile Include, None Include and EmbeddedResource Include).

**CONSOLE APPS**
 
* **ResolutionChanger**  - program for changing resolution of a picture in a given directory

Program parameters:
1. target resolution -res = levelxpion e.g. -res = 320x240,
2. input directory (optional, if not provided, then current) -inputdir = path,
3. output directory (optional, if not specified, create output directory in the current directory) -outputdir = path,


## Perl 

* **myls.pl** - a substitute for the ls command from Linux

The program accepts three optional parameters given in any order:
1. name of the directory whose content is to be output by the program (by default - accept the current directory),
2. the -l option to run the "long" form of results output to stdout,
3. the -L option that causes the file list for each entry to also contain the name of the file owner.

The "short" list of file details contains only file names listed in one column. The "long" list includes (in this order):
1. file name (30 characters)
2. size (in bytes - 10 characters)
3. modification time in the form "yyyy-mm-dd HH: MM: SS"
4. rights (in the form of a 10-character mask compatible in form and content with the convention used by ls -l; on the first character we output 'd' if the entry represents a directory and '-' otherwise)

* **htmlParser.pl** - a script that parses html to csv

The script assumes that the html documents from the pages listed below are in the local file system:
1. [site 1](https://www.spoj.com/WIPING4/ranks/)
2. [site 2](https://www.spoj.com/WIPING5/ranks/)

I introduced an additional mechanism for loading a text file with the name passed by the script argument, containing the names of the players' accounts (one per line) to be included when outputting the statement; accounts not listed in this file are skipped.

The html file is loaded from stdin, and the obtained extract should be output to stdout in CSV format (with fields limited by quotation marks and separated by commas) 

## Python
* **DuplicateFilesFinder** - script detecting duplicate files in directories specified in the parameters. Duplicates are files of identical size and identical content (byte by byte).
* **HistogramAndDecrypter** - script to create a histogram of Latin letters and decrypt the substitution cipher
* **htmlParser** - script that parses html to csv. The same rules as in the perl script described above.

## Xml Markup Language

* **person_list.xml** - an xml tree with list of people
* **XML_validator.xsd** - XSD schema for validating XML with list of people.
* **XML_to_HTML_converter.xsl** - XML to HTML conversion (nodes to table)
* **XML_to_PDF_converter.xsl** - XML to PDF conversion (nodes to table)
* **XML_DOM_tableView.html** - HTML doc with onload event support
* **XML_DOM_parser.js** - XML DOM parser in JavaScript (nodes to HTML table)
