package com.icss.hr.emp.index;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.pojo.EmpDto;

/**
 * 全文检索类
 * 
 * @author DLETC
 *
 */
@Component
public class EmpIndexDao {

	// 读取资源文件的配置内容，作为索引的位置路径
	@Value("#{prop.emp_index_path}")
	private String indexPath;

	// 中文分词器
	public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

	/**
	 * 新增索引
	 * 
	 * @param document
	 * @throws IOException
	 */
	public void create(Document document) throws IOException {
		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.addDocument(document);
		indexWriter.commit();
		indexWriter.close();

		System.out.println("索引被添加");
	}

	/**
	 * 根据Term条件删除索引，如果是数据库，根据ID就可以了
	 * 
	 * Term是搜索的最小单位，代表某个 Field 中的一个关键词，如：<title, lucene>
	 * 
	 * new Term( "title", "lucene" );
	 * 
	 * new Term( "id", "5" );
	 * 
	 * new Term( "id", UUID );
	 * 
	 * @param term
	 * @throws IOException
	 */
	public void delete(Term term) throws IOException {

		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.deleteDocuments(term);
		indexWriter.commit();
		indexWriter.close();

		System.out.println("删除索引");
	}

	/**
	 * 根据Term条件更新索引
	 * 
	 * @throws IOException
	 */
	public void update(Term term, Document document) throws IOException {

		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);

		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));
		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.updateDocument(term, document);
		indexWriter.commit();
		indexWriter.close();

		System.out.println("更新索引");
	}
	
	/**
	 * 分页显示查询结果
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public EmpDto search(Query query)
			throws IOException, InvalidTokenOffsetsException {

		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
		
		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		// IndexSearcher是用来搜索用的
		IndexReader ir = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
						
		// 得到满足条件的前100行
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		// 总记录数
		int recordCount = topDocs.totalHits;
		
		//返回结果集
		ScoreDoc[] sds = topDocs.scoreDocs;

		// 文档集合
		List<Emp> recordList = new ArrayList<Emp>();

		// ============高亮和截取某个字段的文本摘要设置=============
		// 设置环绕的首尾字符串
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(
				"<font color=red>", "</font>");
		// 语法高亮显示设置
		Highlighter highlighter = new Highlighter(formatter, new QueryScorer(
				query));
		// 100是是表示摘要的字数
		highlighter.setTextFragmenter(new SimpleFragmenter(100));
		// ===================================================
				
		//遍历结果集
		for (ScoreDoc scoreDoc : sds) {
			
			int docSn = scoreDoc.doc; // 文档内部编号
					
			Document doc = indexSearcher.doc(docSn); // 根据编号取出相应的文档
			
			//员工编号
			Integer empId = Integer.parseInt(doc.get("empId"));
			//员工姓名
			String empName = doc.get("empName");
			//员工电话
			String empPhone = doc.get("empPhone");

			// 得到高亮及摘要文字
			String empInfo = doc.get("empInfo");
			
			TokenStream tream = analyzer.tokenStream("empInfo",
					new StringReader(empInfo));
			String searchResult = highlighter
					.getBestFragment(tream, empInfo);

			// 如果内容 不包含关键字，会返回null，就截取前100个字符
			if (searchResult == null) {
				int minLen = empInfo.length() >= 100 ? 100 : empInfo
						.length();
				searchResult = empInfo.substring(0, minLen);
			}

			Emp emp = new Emp();
			emp.setEmpId(empId);
			emp.setEmpName(empName);
			emp.setEmpPhone(empPhone);
			emp.setEmpInfo(searchResult);			
			
			recordList.add(emp);			
		}	
		

		ir.close();

		return new EmpDto(recordCount, recordList);
	}

}