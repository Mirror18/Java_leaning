# Apache POI 中文使用指南

参考文章[Apache POI 中文使用指南_apache poi中文文档-CSDN博客](https://blog.csdn.net/hadues/article/details/113859228)

## Apache POI 中文使用手册

### Apache POI 项目介绍

> - Apache POI项目的任务是创建和维护Java API，以基于Office Open XML标准（OOXML）和Microsoft的OLE 2复合文档格式（OLE2）处理各种文件格式。
> - 简而言之，您可以使用Java读写MS Excel文件。
> - 此外，您可以使用Java读写MS Word和MS PowerPoint文件。
> - Apache POI是您的Java Excel解决方案（适用于Excel 97-2008）
> - Apache POI项目是用于基于Microsoft的OLE 2复合文档格式开发文件格式的纯Java端口的主项目
> - Apache POI还是用于基于Office Open XML（ooxml）开发文件格式的纯Java端口的主项目

简言之,[Apache POI](https://poi.apache.org/) 是Java 领域中可以操作World,Excel,PPT文件的类库,可以用于生成报表,数据处理等.

> 值得注意的是,Apache POI 从4.0.1版本开始，需要JDK 8 或更高版本支持。

###  处理组件

#### Excel 文件处理组件

| POIFS                                                        | HSSF          | XSSF            | SXSSF             |
| ------------------------------------------------------------ | ------------- | --------------- | ----------------- |
| OIFS是POI中最古老，最稳定的部分。OLE 2复合文档格式到纯Java的移植。 | 读写*.xls文件 | 读写*.xlsx 文件 | 读写*.xlsx 大文件 |

####  Word 文件处理组件

| HWPF          | XWPF            |
| ------------- | --------------- |
| 处理*.doc文件 | 处理*.docx 文件 |

####  PPT 文件处理组件

| HSLF          | XSLF            |
| ------------- | --------------- |
| 处理*.ppt文件 | 处理*.pptx 文件 |

#### 文档属性组件

> - HPSF是OLE 2属性集格式到纯Java的移植。
> - 属性集通常用于存储文档的属性（标题，作者，最后修改日期等），但是它们也可以用于特定于应用程序的目的。
> - HPSF支持读取和写入属性。

#### Visio 文件处理组件

| HDGF           | XDGF           |
| -------------- | -------------- |
| 处理*.vsd 文件 | 处理*.vsdx文件 |

#### Microsoft Publisher 98（-2007）文件处理组件

> - HPBF是我们将Microsoft Publisher 98（-2007）文件格式移植到纯Java的端口。
> - 目前，它仅支持低水平读取大约一半的文件部分，并支持简单的文本提取

####  OutLook 文件处理组件

> - SMF是Microsoft Outlook消息文件格式到纯Java的移植。目前，它仅包含MSG文件的某些文本内容以及一些附件。进一步的支持和文档进展缓慢
> - Microsoft最近在其OSP中添加了Outlook文件格式

####  OutLook 附件文件处理组件

> - HMEF是Microsoft TNEF（传输中性编码格式）文件格式到纯Java的移植。Outlook有时会使用TNEF对消息进行编码，通常会以winmail.dat的形式出现。HMEF当前仅支持较低级别的阅读，但我们希望添加文本和附件提取。
> - 处理winmail.dat 格式文件

### Apache POI 下载

依赖引用关系图如下所示:
![在这里插入图片描述](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404280257869.png)

####  直接下载

[点击下载Apache POI](https://poi.apache.org/download.html)
![在这里插入图片描述](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404280257848.png)

> - 二进制版本发布仓库： https://archive.apache.org/dist/poi/release/bin/
> - 源码版本发布仓库：https://archive.apache.org/dist/poi/release/src/

####  Maven依赖下载

```xml
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.0.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
<dependency>
    <groupId>org.apache.poi</groupId>
	<artifactId>poi-ooxml</artifactId>
	<version>5.0.0</version>
</dependency>

```

> [点击查看最新版](https://mvnrepository.com/artifact/org.apache.poi/poi)

### 使用Apache POI

Apache POI 虽然支持很多种文档格式的读写,但是我们最常用的还是对Excel 文件的读写。

####  使用Apache POI 读写Excel 文件

正如前面所说，Apache POI 针对读写Excel 文件提供了三个组件:

| HSSF                                                  | XSSF                                                         | SXSSF                                                        |
| ----------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| HSSF是POI项目对Excel '97（-2007）文件格式的纯Java实现 | XSSF是POI项目对Excel 2007 OOXML（.xlsx）文件格式的纯Java实现。 | SXSSF是XSSF的API兼容流扩展，可用于必须生成非常大的电子表格且堆空间有限的情况 |
| 处理`*.xls` 文件                                      | 处理`*.xlsx` 文件                                            | 处理超大的`*xlsx` 文件                                       |

> - 生成电子表格的另一种方法是通过Cocoon序列化器（但是您仍将间接使用HSSF）。使用Cocoon，您可以通过简单地应用样式表并指定序列化程序来序列化任何XML数据源（例如，可能是在SQL中输出的ESQL页面）。
> - 从`3.8-beta3`开始，POI提供了基于XSSF的低内存占用的SXSSF API。
> - SXSSF是XSSF的API兼容流扩展，可用于必须生成非常大的电子表格且堆空间有限的情况。SXSSF通过限制对滑动窗口内的行的访问来实现其低内存占用，而XSSF允许对文档中的所有行进行访问。不再存在于窗口中的较旧的行由于被写入磁盘而变得不可访问.
> - 在自动刷新模式下，可以指定访问窗口的大小，以在内存中保留一定数量的行。当达到该值时，创建额外的一行会导致索引最低的行从访问窗口中删除并写入磁盘。或者，可以将窗口大小设置为动态增长。可以根据需要通过显式调用flushRows（int keepRows）定期对其进行修剪。
> - 由于实现的流性质，与XSSF相比存在以下限制：
>   - 在某个时间点只能访问有限数量的行。
>   - 不支持`Sheet.clone()`。
>   - 不支持公式评估
> - 电子表格API功能摘要
>   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210219114121447.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhZHVlcw==,size_16,color_FFFFFF,t_70)

##### 如何创建一个WorkBook?

创建一个*.xls 文件

```java
Workbook wb = new HSSFWorkbook();
try  (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
1234
```

创建一个*.xlsx 文件

```java
Workbook wb = new XSSFWorkbook();
try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
    wb.write(fileOut);
}
1234
```

#####  如何创建一个Sheet?

```java
Workbook wb = new HSSFWorkbook();  // or new XSSFWorkbook();
Sheet sheet1 = wb.createSheet("new sheet");
Sheet sheet2 = wb.createSheet("second sheet");
// Note that sheet name is Excel must not exceed 31 characters
// and must not contain any of the any of the following characters:
// 0x0000
// 0x0003
// colon (:)
// backslash (\)
// asterisk (*)
// question mark (?)
// forward slash (/)
// opening square bracket ([)
// closing square bracket (])
// You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
// for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
Sheet sheet3 = wb.createSheet(safeName);
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
```

##### 如何创建一个单元格？

```java
Workbook wb = new HSSFWorkbook();
//Workbook wb = new XSSFWorkbook();
CreationHelper createHelper = wb.getCreationHelper();
Sheet sheet = wb.createSheet("new sheet");
// Create a row and put some cells in it. Rows are 0 based.
Row row = sheet.createRow(0);
// Create a cell and put a value in it.
Cell cell = row.createCell(0);
cell.setCellValue(1);
// Or do it on one line.
row.createCell(1).setCellValue(1.2);
row.createCell(2).setCellValue(
     createHelper.createRichTextString("This is a string"));
row.createCell(3).setCellValue(true);
// Write the output to a file
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
```

##### 如何创建一个日期类型的单元格？

```java
Workbook wb = new HSSFWorkbook();
//Workbook wb = new XSSFWorkbook();
CreationHelper createHelper = wb.getCreationHelper();
Sheet sheet = wb.createSheet("new sheet");
// Create a row and put some cells in it. Rows are 0 based.
Row row = sheet.createRow(0);
// Create a cell and put a date value in it.  The first cell is not styled
// as a date.
Cell cell = row.createCell(0);
cell.setCellValue(new Date());
// we style the second cell as a date (and time).  It is important to
// create a new cell style from the workbook otherwise you can end up
// modifying the built in style and effecting not only this cell but other cells.
CellStyle cellStyle = wb.createCellStyle();
cellStyle.setDataFormat(
    createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
cell = row.createCell(1);
cell.setCellValue(new Date());
cell.setCellStyle(cellStyle);
//you can also set date as java.util.Calendar
cell = row.createCell(2);
cell.setCellValue(Calendar.getInstance());
cell.setCellStyle(cellStyle);
// Write the output to a file
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}

```

#####  创建多种格式的单元格

```bash
Workbook wb = new HSSFWorkbook();
Sheet sheet = wb.createSheet("new sheet");
Row row = sheet.createRow(2);
row.createCell(0).setCellValue(1.1);
row.createCell(1).setCellValue(new Date());
row.createCell(2).setCellValue(Calendar.getInstance());
row.createCell(3).setCellValue("a string");
row.createCell(4).setCellValue(true);
row.createCell(5).setCellType(CellType.ERROR);
// Write the output to a file
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}

```

#####  Files 和InputStream

当打开 WorkBook（.`xls` HSSFWorkbook或`.xlsx` XSSFWorkbook）时，可以从File 或InputStream加载工作簿。

使用File对象可以减少内存消耗，而InputStream需要更多内存，因为它必须缓冲整个文件。

如果使用WorkbookFactory，则使用其中一个非常容易：

```java
// Use a file
Workbook wb = WorkbookFactory.create(new File("MyExcel.xls"));
// Use an InputStream, needs more memory
Workbook wb = WorkbookFactory.create(new FileInputStream("MyExcel.xlsx"));

```

如果直接使用HSSFWorkbook或XSSFWorkbook，通常应遍历POIFSFileSystem或 OPCPackage，以完全控制生命周期（包括完成后关闭文件）：

```java
// HSSFWorkbook, File
POIFSFileSystem fs = new POIFSFileSystem(new File("file.xls"));
HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
....
fs.close();
// HSSFWorkbook, InputStream, needs more memory
POIFSFileSystem fs = new POIFSFileSystem(myInputStream);
HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
// XSSFWorkbook, File
OPCPackage pkg = OPCPackage.open(new File("file.xlsx"));
XSSFWorkbook wb = new XSSFWorkbook(pkg);
....
pkg.close();
// XSSFWorkbook, InputStream, needs more memory
OPCPackage pkg = OPCPackage.open(myInputStream);
XSSFWorkbook wb = new XSSFWorkbook(pkg);
....
pkg.close();
                
```

#####  展示各种对齐方式

```java
public static void main(String[] args) throws Exception {
    Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
    Sheet sheet = wb.createSheet();
    Row row = sheet.createRow(2);
    row.setHeightInPoints(30);
    createCell(wb, row, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
    createCell(wb, row, 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
    createCell(wb, row, 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
    createCell(wb, row, 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
    createCell(wb, row, 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
    createCell(wb, row, 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
    createCell(wb, row, 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
    // Write the output to a file
    try (OutputStream fileOut = new FileOutputStream("xssf-align.xlsx")) {
        wb.write(fileOut);
    }
    wb.close();
}
/**
 * Creates a cell and aligns it a certain way.
 *
 * @param wb     the workbook
 * @param row    the row to create the cell in
 * @param column the column number to create the cell in
 * @param halign the horizontal alignment for the cell.
 * @param valign the vertical alignment for the cell.
 */
private static void createCell(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign) {
    Cell cell = row.createCell(column);
    cell.setCellValue("Align It");
    CellStyle cellStyle = wb.createCellStyle();
    cellStyle.setAlignment(halign);
    cellStyle.setVerticalAlignment(valign);
    cell.setCellStyle(cellStyle);
}

```

#####  设置边框

```java
Workbook wb = new HSSFWorkbook();
Sheet sheet = wb.createSheet("new sheet");
// Create a row and put some cells in it. Rows are 0 based.
Row row = sheet.createRow(1);
// Create a cell and put a value in it.
Cell cell = row.createCell(1);
cell.setCellValue(4);
// Style the cell with borders all around.
CellStyle style = wb.createCellStyle();
style.setBorderBottom(BorderStyle.THIN);
style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
style.setBorderLeft(BorderStyle.THIN);
style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
style.setBorderRight(BorderStyle.THIN);
style.setRightBorderColor(IndexedColors.BLUE.getIndex());
style.setBorderTop(BorderStyle.MEDIUM_DASHED);
style.setTopBorderColor(IndexedColors.BLACK.getIndex());
cell.setCellStyle(style);
// Write the output to a file
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
wb.close()

```

#####  遍历行和单元格

有时，可能只想遍历工作簿中的所有工作表中的所有行或行中的所有单元格。这可以通过简单的for循环来实现。

通过调用workbook.sheetIterator()， sheet.rowIterator()和row.cellIterator() 或隐式使用for-each循环，可以使用这些迭代器。请注意，rowIterator和cellIterator遍历已创建的行或单元格，跳过空的行和单元格。

```java
for (Sheet sheet : wb ) {
    for (Row row : sheet) {
        for (Cell cell : row) {
            // Do something here
        }
    }
}

```

#####  遍历单元格，控制丢失/空白的单元格

在某些情况下，进行迭代时，您需要完全控制如何处理丢失或空白的行和单元格，并且需要确保访问每个单元格，而不仅仅是访问文件中定义的那些单元格。（CellIterator将仅返回文件中定义的单元格，这在很大程度上是具有值或样式的单元格，但取决于Excel）。

在这种情况下，应获取一行的第一列和最后一列信息，然后调用getCell（int，MissingCellPolicy） 来获取单元格。使用 MissingCellPolicy 控制空白或空单元格的处理方式。

```java
// Decide which rows to process
int rowStart = Math.min(15, sheet.getFirstRowNum());
int rowEnd = Math.max(1400, sheet.getLastRowNum());
for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
   Row r = sheet.getRow(rowNum);
   if (r == null) {
      // This whole row is empty
      // Handle it as needed
      continue;
   }
   int lastColumn = Math.max(r.getLastCellNum(), MY_MINIMUM_COLUMN_COUNT);
   for (int cn = 0; cn < lastColumn; cn++) {
      Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
      if (c == null) {
         // The spreadsheet is empty in this cell
      } else {
         // Do something useful with the cell's contents
      }
   }
}

```

#####  获取单元格内容

要获取单元格的内容，您首先需要知道它是哪种单元格（例如，将字符串单元格作为其数字内容将获得NumberFormatException）。因此，您将需要打开单元格的类型，然后为该单元格调用适当的getter。

在下面的代码中，我们遍历一张纸中的每个单元格，打印出该单元格的引用（例如A3），然后打印出该单元格的内容。

```java
// import org.apache.poi.ss.usermodel.*;
DataFormatter formatter = new DataFormatter();
Sheet sheet1 = wb.getSheetAt(0);
for (Row row : sheet1) {
    for (Cell cell : row) {
        CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
        System.out.print(cellRef.formatAsString());
        System.out.print(" - ");
        // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
        String text = formatter.formatCellValue(cell);
        System.out.println(text);
        // Alternatively, get the value and format it yourself
        switch (cell.getCellType()) {
            case CellType.STRING:
                System.out.println(cell.getRichStringCellValue().getString());
                break;
            case CellType.NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.println(cell.getDateCellValue());
                } else {
                    System.out.println(cell.getNumericCellValue());
                }
                break;
            case CellType.BOOLEAN:
                System.out.println(cell.getBooleanCellValue());
                break;
            case CellType.FORMULA:
                System.out.println(cell.getCellFormula());
                break;
            case CellType.BLANK:
                System.out.println();
                break;
            default:
                System.out.println();
        }
    }
}

```

#####  文字提取

对于大多数文本提取要求，标准ExcelExtractor类应提供您所需要的全部。

```java
try (InputStream inp = new FileInputStream("workbook.xls")) {
    HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
    ExcelExtractor extractor = new ExcelExtractor(wb);
    extractor.setFormulasNotResults(true);
    extractor.setIncludeSheetNames(false);
    String text = extractor.getText();
    wb.close();
}

```

> 对于非常精美的文本提取，XLS到CSV等，请查看 `/src/examples/src/org/apache/poi/examples/hssf/eventusermodel/XLS2CSVmra.java`

##### 填充和颜色

```java
Workbook wb = new XSSFWorkbook();
Sheet sheet = wb.createSheet("new sheet");
// Create a row and put some cells in it. Rows are 0 based.
Row row = sheet.createRow(1);
// Aqua background
CellStyle style = wb.createCellStyle();
style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
style.setFillPattern(FillPatternType.BIG_SPOTS);
Cell cell = row.createCell(1);
cell.setCellValue("X");
cell.setCellStyle(style);
// Orange "foreground", foreground being the fill foreground not the font color.
style = wb.createCellStyle();
style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
cell = row.createCell(2);
cell.setCellValue("X");
cell.setCellStyle(style);
// Write the output to a file
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
wb.close();

```

#####  合并单元格

```bash
Workbook wb = new HSSFWorkbook();
Sheet sheet = wb.createSheet("new sheet");
Row row = sheet.createRow(1);
Cell cell = row.createCell(1);
cell.setCellValue("This is a test of merging");
sheet.addMergedRegion(new CellRangeAddress(
        1, //first row (0-based)
        1, //last row  (0-based)
        1, //first column (0-based)
        2  //last column  (0-based)
));
// Write the output to a file
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
wb.close();

```

##### 设置字体

```java
Workbook wb = new HSSFWorkbook();
Sheet sheet = wb.createSheet("new sheet");
// Create a row and put some cells in it. Rows are 0 based.
Row row = sheet.createRow(1);
// Create a new font and alter it.
Font font = wb.createFont();
font.setFontHeightInPoints((short)24);
font.setFontName("Courier New");
font.setItalic(true);
font.setStrikeout(true);
// Fonts are set into a style so create a new one to use.
CellStyle style = wb.createCellStyle();
style.setFont(font);
// Create a cell and put a value in it.
Cell cell = row.createCell(1);
cell.setCellValue("This is a test of fonts");
cell.setCellStyle(style);
// Write the output to a file
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
wb.close();


```

> 请注意，工作簿中唯一字体的最大数量限制为32767。您应该在应用程序中重新使用字体，而不是为每个单元格创建字体。

例子：

错误的写法

```java
for (int i = 0; i < 10000; i++) {
    Row row = sheet.createRow(i);
    Cell cell = row.createCell(0);
    CellStyle style = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    style.setFont(font);
    cell.setCellStyle(style);
}

```

正确的写法

```java
CellStyle style = workbook.createCellStyle();
Font font = workbook.createFont();
font.setBoldweight(Font.BOLDWEIGHT_BOLD);
style.setFont(font);
for (int i = 0; i < 10000; i++) {
    Row row = sheet.createRow(i);
    Cell cell = row.createCell(0);
    cell.setCellStyle(style);
}

```

#####  自定义颜色

HSSF（*.xls文件）

```java
HSSFWorkbook wb = new HSSFWorkbook();
HSSFSheet sheet = wb.createSheet();
HSSFRow row = sheet.createRow(0);
HSSFCell cell = row.createCell(0);
cell.setCellValue("Default Palette");
//apply some colors from the standard palette,
// as in the previous examples.
//we'll use red text on a lime background
HSSFCellStyle style = wb.createCellStyle();
style.setFillForegroundColor(HSSFColor.LIME.index);
style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
HSSFFont font = wb.createFont();
font.setColor(HSSFColor.RED.index);
style.setFont(font);
cell.setCellStyle(style);
//save with the default palette
try (OutputStream out = new FileOutputStream("default_palette.xls")) {
    wb.write(out);
}
//now, let's replace RED and LIME in the palette
// with a more attractive combination
// (lovingly borrowed from freebsd.org)
cell.setCellValue("Modified Palette");
//creating a custom palette for the workbook
HSSFPalette palette = wb.getCustomPalette();
//replacing the standard red with freebsd.org red
palette.setColorAtIndex(HSSFColor.RED.index,
        (byte) 153,  //RGB red (0-255)
        (byte) 0,    //RGB green
        (byte) 0     //RGB blue
);
//replacing lime with freebsd.org gold
palette.setColorAtIndex(HSSFColor.LIME.index, (byte) 255, (byte) 204, (byte) 102);
//save with the modified palette
// note that wherever we have previously used RED or LIME, the
// new colors magically appear
try (out = new FileOutputStream("modified_palette.xls")) {
    wb.write(out);
}

```

XSSF(*.xlsx 文件)

```java
XSSFWorkbook wb = new XSSFWorkbook();
XSSFSheet sheet = wb.createSheet();
XSSFRow row = sheet.createRow(0);
XSSFCell cell = row.createCell( 0);
cell.setCellValue("custom XSSF colors");
XSSFCellStyle style1 = wb.createCellStyle();
style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 0, 128), new DefaultIndexedColorMap()));
style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

```

#####  读和写WorksBooks

```java
try (InputStream inp = new FileInputStream("workbook.xls")) {
//InputStream inp = new FileInputStream("workbook.xlsx");
    Workbook wb = WorkbookFactory.create(inp);
    Sheet sheet = wb.getSheetAt(0);
    Row row = sheet.getRow(2);
    Cell cell = row.getCell(3);
    if (cell == null)
        cell = row.createCell(3);
    cell.setCellType(CellType.STRING);
    cell.setCellValue("a test");
    // Write the output to a file
    try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
        wb.write(fileOut);
    }
}

```

#####  在单元格中使用换行符

```java
Workbook wb = new XSSFWorkbook();   //or new HSSFWorkbook();
Sheet sheet = wb.createSheet();
Row row = sheet.createRow(2);
Cell cell = row.createCell(2);
cell.setCellValue("Use \n with word wrap on to create a new line");
//to enable newlines you need set a cell styles with wrap=true
CellStyle cs = wb.createCellStyle();
cs.setWrapText(true);
cell.setCellStyle(cs);
//increase row height to accommodate two lines of text
row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));
//adjust column width to fit the content
sheet.autoSizeColumn(2);
try (OutputStream fileOut = new FileOutputStream("ooxml-newlines.xlsx")) {
    wb.write(fileOut);
}
wb.close();

```

##### 数据格式化

```java
Workbook wb = new HSSFWorkbook();
Sheet sheet = wb.createSheet("format sheet");
CellStyle style;
DataFormat format = wb.createDataFormat();
Row row;
Cell cell;
int rowNum = 0;
int colNum = 0;
row = sheet.createRow(rowNum++);
cell = row.createCell(colNum);
cell.setCellValue(11111.25);
style = wb.createCellStyle();
style.setDataFormat(format.getFormat("0.0"));
cell.setCellStyle(style);
row = sheet.createRow(rowNum++);
cell = row.createCell(colNum);
cell.setCellValue(11111.25);
style = wb.createCellStyle();
style.setDataFormat(format.getFormat("#,##0.0000"));
cell.setCellStyle(style);
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
wb.close();

```

##### 将工作表调整为一页

```java
Workbook wb = new HSSFWorkbook();
Sheet sheet = wb.createSheet("format sheet");
PrintSetup ps = sheet.getPrintSetup();
sheet.setAutobreaks(true);
ps.setFitHeight((short)1);
ps.setFitWidth((short)1);
// Create various cells and rows for spreadsheet.
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
wb.close();

```

##### 设置打印区域

```java
Workbook wb = new HSSFWorkbook();
Sheet sheet = wb.createSheet("Sheet1");
//sets the print area for the first sheet
wb.setPrintArea(0, "$A$1:$C$2");
//Alternatively:
wb.setPrintArea(
        0, //sheet index
        0, //start column
        1, //end column
        0, //start row
        0  //end row
);
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
wb.close();

```

#####  设置页脚页数

```java
Workbook wb = new HSSFWorkbook(); // or new XSSFWorkbook();
Sheet sheet = wb.createSheet("format sheet");
Footer footer = sheet.getFooter();
footer.setRight( "Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages() );
// Create various cells and rows for spreadsheet.
try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
    wb.write(fileOut);
}
wb.close();

```

#### Apache POI 提取Word中的文件

Apache POI 针对Word 的处理有两套API，分别如下：

| HWPF          | XWPF            |
| ------------- | --------------- |
| 处理*.doc文件 | 处理*.docx 文件 |

如果是Maven项目添加如下依赖：

```xml
        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>4.1.2</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>4.1.2</version>
        </dependency>
        
        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml-schemas</artifactId>
            <version>4.1.2</version>
        </dependency>
        
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-scratchpad</artifactId>
            <version>4.1.2</version>
        </dependency>

```

如果是Gradle 项目，添加如下依赖:

```bash
    // Apache POI
    // https://mvnrepository.com/artifact/org.apache.poi/poi
    implementation group: 'org.apache.poi', name: 'poi', version: '4.1.2'
    // https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '4.1.2'
    // https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml-schemas
    implementation group: 'org.apache.poi', name: 'poi-ooxml-schemas', version: '4.1.2'
    // doc 需要用
    implementation group: 'org.apache.poi', name: 'poi-scratchpad', version: '4.1.2'

```

#####  读取Excel 数据并写入到Word示例

[读取Excel 数据并写入到Word示例](https://xingyun.blog.csdn.net/article/details/102555142)

##### 读取Excel 数据并从Word中提取图片示例

接下来我们来演示如何从word中提取图片。

```java
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.List;

/**
 * @author qing-feng.zhao
 */
@Slf4j
public class SmartPoiWordUtils {
    /**
     * 静态工具类应该禁用构造方法
     */
    private SmartPoiWordUtils(){}
    /**
     * 写入数据到word
     * @param fileName
     * @param dataListArg
     * @throws IOException
     */
    public static void writeDataToWord(String fileName, List<String> dataListArg) throws IOException {
        try(FileOutputStream out = new FileOutputStream(fileName);){
            //创建一个文档
            XWPFDocument xwpfDocument=new XWPFDocument();
            //创建一个段落
            XWPFParagraph xwpfParagraph;
            //创建一片区域
            XWPFRun run;
            for (String lineData:dataListArg
            ) {
                xwpfParagraph= xwpfDocument.createParagraph();
                run=xwpfParagraph.createRun();
                run.setText(lineData);
            }
            xwpfDocument.write(out);
            xwpfDocument.close();
        }
    }

    public synchronized static void fetchPictureFromWord(File worldFilePath,String targetPictureFolder,String pictureFileName) throws IOException {
            String fileType=SmartFileUtils.getNormalFileType(worldFilePath.getName());
            Integer numberId=1;
            switch (fileType.toLowerCase()){
                case "doc":
                    try(HWPFDocument hwpfDocument=new HWPFDocument(new FileInputStream(worldFilePath.getAbsolutePath()))
                    ){
                        PicturesTable picturesTable=hwpfDocument.getPicturesTable();
                        List<Picture> pictures = picturesTable.getAllPictures();
                        for (Picture picture : pictures) {
                            // 输出图片到磁盘
                            String pictureFiePath=targetPictureFolder + File.separator + pictureFileName+numberId+"."+picture.suggestFileExtension();
                            try(OutputStream out = new FileOutputStream(pictureFiePath)){
                                picture.writeImageContent(out);
                            }
                            numberId++;
                        }
                    }
                    break;
                case "docx":
                    try(XWPFDocument xwpfDocument=new XWPFDocument(new FileInputStream(worldFilePath.getAbsolutePath()))){
                        //得到word的数据流
                        List<XWPFPictureData> xwpfPictureDataList=xwpfDocument.getAllPictures();
                        if(null!=xwpfPictureDataList&&xwpfPictureDataList.size()>0){
                            for (XWPFPictureData item:xwpfPictureDataList
                            ) {
                                byte[] bytes = item.getData();
                                String pictureFiePath=targetPictureFolder + File.separator + pictureFileName+numberId+"."+item.suggestFileExtension();
                                File file=new File(pictureFiePath);
                                if(file.exists()){
                                    file.delete();
                                }
                                try(FileOutputStream fos = new FileOutputStream(pictureFiePath)){
                                    fos.write(bytes);
                                }
                                numberId++;
                            }
                        }
                    } catch (IOException e) {
                        log.error("错误的文件:{}",e);
                        log.error("出错文件路径",worldFilePath.getAbsolutePath());
                    }
                    break;
                default:
                    log.error("{}无效的文件格式:{}",worldFilePath,fileType);
                    break;
            }
        }
}

```

测试调用类如下：

```java
     public static void main(String[] args) {
        File currentWordFile=new File("/Users/zhaoqingfeng/desktop/test.docx");
        String targetPicturePath=currentWordFile.getParentFile().getPath();
        String wordFileNameWithOutFileType=SmartFileUtils.getNormalFileNameWithOutFileType(currentWordFile.getName());
        try {
            SmartPoiWordUtils.fetchPictureFromWord(currentWordFile,targetPicturePath,wordFileNameWithOutFileType);
        } catch (IOException e) {
            log.error("文件I/O异常",e);
            log.error("处理文件名称:{}",currentWordFile.getName());
            log.error("处理文件路径:{}",currentWordFile.getAbsolutePath());
        }
    }
```

如果word中插入一张图片,上面代码执行完成后，在`/Users/zhaoqingfeng/Desktop/` 路径下就会生成一张图片。

本篇完～



## 苍穹外卖中的使用

开源处理office各种文件格式的开源项目。

应用场景

1. 银行网银系统导出交易明细
2. 各种业务系统导出Excel报表
3. 批量导入业务数据

在这个项目中就是导出Excel形式的报表文件，最近三十天的诶运营数据

那就证明这是写了一个功能。

调用顺序是这样来的。

![image-20240428032246665](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404280322781.png)在这个文件下的

在这里调用。

![image-20240428035717494](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404280357555.png)

调用这个服务。根据内容进行跳转。

![image-20240428035803837](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404280358925.png)

调用的是这个服务。

并且知道传入的参数是响应。

![image-20240428035905550](https://cdn.jsdelivr.net/gh/Mirror18/imgage@img/note/202404280359668.png)这里是完整代码。

进入分析

```java
@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    
     /**
     * 导出近30天的运营数据报表
     * @param response
     */
    @Override
    public void exportBusinessData(HttpServletResponse response) {
        //开始日期和结束日期
        LocalDate begin = LocalDate.now().minusDays(30);
        LocalDate end = LocalDate.now().minusDays(1);
        //查询概览运营数据，提供给Excel模板文件
        BusinessDataVO businessData = workspaceService.getBusinessData(LocalDateTime.of(begin,LocalTime.MIN), LocalDateTime.of(end, LocalTime.MAX));
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表模板.xlsx");
        try {
            //基于提供好的模板文件创建一个新的Excel表格对象
            XSSFWorkbook excel = new XSSFWorkbook(inputStream);
            //获得Excel文件中的一个Sheet页
            XSSFSheet sheet = excel.getSheet("Sheet1");

            sheet.getRow(1).getCell(1).setCellValue(begin + "至" + end);
            //获得第4行
            XSSFRow row = sheet.getRow(3);
            //获取单元格
            row.getCell(2).setCellValue(businessData.getTurnover());
            row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
            row.getCell(6).setCellValue(businessData.getNewUsers());
            row = sheet.getRow(4);
            row.getCell(2).setCellValue(businessData.getValidOrderCount());
            row.getCell(4).setCellValue(businessData.getUnitPrice());
            for (int i = 0; i < 30; i++) {
                LocalDate date = begin.plusDays(i);
                //准备明细数据
                businessData = workspaceService.getBusinessData(LocalDateTime.of(date,LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
                row = sheet.getRow(7 + i);
                row.getCell(1).setCellValue(date.toString());
                row.getCell(2).setCellValue(businessData.getTurnover());
                row.getCell(3).setCellValue(businessData.getValidOrderCount());
                row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
                row.getCell(5).setCellValue(businessData.getUnitPrice());
                row.getCell(6).setCellValue(businessData.getNewUsers());
            }
            //通过输出流将文件下载到客户端浏览器中
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);
            //关闭资源
            out.flush();
            out.close();
            excel.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
```

不过这玩意儿确实跟上面的没啥联系。因为这玩意儿是读取和写入，上面的教程是创建和写入，有联系，联系不大。看个热闹就好。

ok，开始下一个篇章

