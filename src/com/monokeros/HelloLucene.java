package com.monokeros;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;


public class HelloLucene {
  public static void main(String[] args) {
    HelloLucene hello = new HelloLucene();
    try {
      hello.createDemoIndex("d:/index/helloLucene");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 创建索引
   * */
  public void createDemoIndex(String pathname) throws Exception{
    Directory d = FSDirectory.open(new File(pathname));
    Analyzer analyzer = new ComplexAnalyzer();
    IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_40, analyzer);
    IndexWriter writer = new IndexWriter(d, conf);

    FieldType fieldType = new FieldType();
    fieldType.setIndexed(true);
    fieldType.setStored(true);
    
    Document doc = new Document();
    doc.add(new Field("name", "张三", fieldType));
    doc.add(new Field("id", "0", fieldType));
    writer.addDocument(doc);
    
    doc = new Document();
    doc.add(new Field("name", "李四", fieldType));
    doc.add(new Field("id", "1", fieldType));
    writer.addDocument(doc);
    
    writer.commit();
    writer.close();
  }
}
