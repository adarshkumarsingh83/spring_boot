package com.espark.adarsh;

import org.apache.jackrabbit.oak.plugins.index.solr.embedded.EmbeddedSolrServerProvider;
import org.apache.jackrabbit.oak.plugins.index.solr.embedded.SolrServerConfiguration;
import org.apache.lucene.search.Query;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
//import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.core.CoreContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.server.support.HttpSolrClientFactory;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class ApplicationMain {

    @Value("${solr.core.name}")
    private String coreName;

    @Autowired
    ApplicationContext applicationContext;

    Resource resource = null;

    @PostConstruct
    public void init() throws Exception {
        resource = this.applicationContext.getResource("classpath:/solr/oak");
        if (!resource.exists()) {
            throw new FileNotFoundException("Solr Configuration Not Found");
        }
        System.setProperty("oak.data.dir", resource.getURL().getPath() + File.separator + "data");
        System.setProperty("oak.core.name", coreName);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SpringApplication.run(ApplicationMain.class, args);

    }

    @Bean
    public SolrClient createSolrServer() throws Exception {
        CoreContainer coreContainer = new CoreContainer(resource.getURL().getPath());
        coreContainer.load();
        EmbeddedSolrServer server = new EmbeddedSolrServer(coreContainer, coreName);
        return server;
    }



   /* @Bean
    public EmbeddedSolrServer createEmbeddedSolrServer() {
        String targetLocation = ApplicationMain.class
                .getProtectionDomain().getCodeSource().getLocation().getFile() + "/..";
        String solrHome = targetLocation + "/classes/solrembeded";
        // Note that the following property could be set through JVM level arguments too
        System.setProperty("solr.solr.home", solrHome);
        CoreContainer coreContainer = new CoreContainer(solrHome);
        coreContainer.load();
        EmbeddedSolrServer server = new EmbeddedSolrServer(coreContainer, "solrembeded");
        return server;
    }

*/

   /* @Bean
    public EmbeddedSolrServerProvider embeddedSolrServerProvider() {
        EmbeddedSolrServerProvider embeddedSolrServerProvider=null;
        try {
   *//* SolrServerConfiguration solrServerConfiguration = new SolrServerConfiguration("target/solr","target/solr/solr
   * .xml", "oak");
    EmbeddedSolrServerProvider embeddedSolrServerProvider = new EmbeddedSolrServerProvider(solrServerConfiguration);
    SolrServer solrServer = embeddedSolrServerProvider.getSolrServer();*//*

            Resource resource = this.applicationContext.getResource("classpath:/solr/oak");
            if(!resource.exists()) {
                throw new FileNotFoundException("Solr Configuration Not Found");
            }

            String path = resource.getFile().getPath();
            EmbeddedSolrServerConfiguration embeddedSolrServerConfiguration=new EmbeddedSolrServerConfiguration(path,
            "oak");
            embeddedSolrServerProvider = new EmbeddedSolrServerProvider(embeddedSolrServerConfiguration);
        } catch (Exception e) {
            log.error("label=EmbeddedSolrSearchIndexConfigure exception={}",e);
        }
        return embeddedSolrServerProvider;
    }*/


 /*  @Bean
   public EmbeddedSolrServer server() {
try {
    SolrServerConfiguration solrServerConfiguration = new SolrServerConfiguration("target/solr","target/solr/solr
    .xml", "oak");
    EmbeddedSolrServerProvider embeddedSolrServerProvider = new EmbeddedSolrServerProvider(solrServerConfiguration);
    embeddedSolrServerProvider.getSolrServer();

           File resource = new File("/Users/akumar38/Desktop/EmbededSolar/src/main/resources/solr/data");
           Path dataPath = Paths.get("/Users/akumar38/Desktop/EmbededSolar/src/main/resources/solr/data");
    EmbeddedSolrServer server = new EmbeddedSolrServer(dataPath,"embedded-core");
    return server;
       } catch (Exception e) {
           e.printStackTrace();
       }


       return null;
   }*/





   /* public HttpSolrClient solrClient(@Autowired HttpSolrServer solrServer) {
        *//*HttpSolrClient.Builder builder = new HttpSolrClient.Builder("http://localhost:8983/solrembeded");
        return builder.build();*//*

        HttpSolrClient httpSolrClient=new HttpSolrClient("http://localhost:8983/solr");
        return httpSolrClient;
    }


    public SolrTemplate solrTemplate(@Autowired HttpSolrClient client) throws Exception {
        return new SolrTemplate(client);
    }*/



   /* public HttpSolrServer createHttpSolrServer() {
        String url = "http://localhost:8983/solr";
        HttpSolrServer server = new HttpSolrServer(url);
        server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
        server.setConnectionTimeout(5000); // 5 seconds to establish TCP
        // Setting the XML response parser is only required for cross
        // version compatibility and only when one side is 1.4.1 or
        // earlier and the other side is 3.1 or later.
        server.setParser(new XMLResponseParser()); // binary parser is used by default
        // The following settings are provided here for completeness.
        // They will not normally be required, and should only be used
        // after consulting javadocs to know whether they are truly required.
        server.setSoTimeout(1000);  // socket read timeout
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);  // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        server.setAllowCompression(true);
        return server;
    }*/

}
