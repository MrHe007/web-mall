package com.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneTest01 {

	@Test
	public void test01() throws IOException {
		// 创建 IndexWriter对
		
		Directory directory = FSDirectory.open(new File("E:\\demo\\index")); 		// 创建索引库的位置 
		Analyzer analyzer = new StandardAnalyzer(); 		// 创建分词器
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		
		// 拿到文件所在的位置
		// 通过文件得到 document 对象
		
		File fileDir = new File("E:\\demo\\sourceFile");
				
		for(File file :fileDir.listFiles()) { 	// 遍历里面的文件
			
			Document doc = new Document();
			
			String fileName = file.getName();
			Field fileNameField = new TextField("fileName",fileName,Store.YES);
			
			long fileSize = FileUtils.sizeOf(file);
			Field fileSizeField = new LongField("fileSize", fileSize, Store.YES); 		// 是否存储
			
			String filePath = file.getPath();
			Field filePathField = new StoredField("filePath", filePath);
			
			String fileContent = FileUtils.readFileToString(file);
			Field fileContentField = new TextField("fileContent", fileContent, Store.YES);
			
			doc.add(fileSizeField);
			doc.add(fileContentField);
			doc.add(filePathField);
			doc.add(fileNameField);
			
			indexWriter.addDocument(doc);
		}
		
		indexWriter.close(); 		// 关闭流 
	}
	
	@Test
	public void test03() throws IOException {
		
		// 创建一个 Directory 对象，也就是索引库存放的位置
		Directory directory = FSDirectory.open(new File("E:\\demo\\index")); 		// 创建索引库的位置 
		
		IndexReader indexReader = DirectoryReader.open(directory); 	
		
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
		Query query = new TermQuery( new Term("fileName", "4")); 	
		
		TopDocs topDocs = indexSearcher.search(query, 10);
		
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docId = scoreDoc.doc;
			Document doc = indexSearcher.doc(docId);
			
			String fileName = doc.get("fileName");
			System.out.println(fileName);
			
			String fileSize = doc.get("fileSize");
			System.out.println(fileSize);
			
			String fileContent = doc.get("fileContent");
			System.out.println(fileContent);
			
			String filePath = doc.get("filePath");
			System.out.println(filePath);
			
			System.out.println("--------------");
		}
		indexReader.close();
	}
	
	@Test
	public void testTokenStream() throws Exception {
		// 创建一个标准分析器对象
//		Analyzer analyzer = new StandardAnalyzer();
//		Analyzer analyzer = new CJKAnalyzer();
//		Analyzer analyzer = new SmartChineseAnalyzer();
		Analyzer analyzer = new IKAnalyzer();
		// 获得tokenStream对象
		// 第一个参数：域名，可以随便给一个
		// 第二个参数：要分析的文本内容
//		TokenStream tokenStream = analyzer.tokenStream("test",
//				"The Spring Framework provides a comprehensive programming and configuration model.");
		TokenStream tokenStream = analyzer.tokenStream("test",
				"高富帅可以用二维表结构来逻辑表达实现的数据");
		// 添加一个引用，可以获得每个关键词
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		// 添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		// 将指针调整到列表的头部
		tokenStream.reset();
		// 遍历关键词列表，通过incrementToken方法判断列表是否结束
		while (tokenStream.incrementToken()) {
			// 关键词的起始位置
			System.out.println("start->" + offsetAttribute.startOffset());
			// 取关键词
			System.out.println(charTermAttribute);
			// 结束位置
			System.out.println("end->" + offsetAttribute.endOffset());
		}
		tokenStream.close();
	}
	
	
}
