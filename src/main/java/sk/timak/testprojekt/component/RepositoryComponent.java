package sk.timak.testprojekt.component;

import com.bigdata.journal.Options;
import com.bigdata.rdf.sail.BigdataSail;
import com.bigdata.rdf.sail.BigdataSailRepository;
import org.openrdf.repository.RepositoryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import org.openrdf.repository.Repository;

import java.util.Properties;

@Component
public class RepositoryComponent {

    @Value("${resource.path.journal}")
    private String journalFileLocation;

    private Repository repository;

    @PostConstruct
    public void createRepository() throws RepositoryException {
        final Properties props = new Properties();
        props.put(Options.BUFFER_MODE, "DiskRW"); // persistent file system located journal
        props.put(Options.FILE, journalFileLocation); // journal file location

        final BigdataSail sail = new BigdataSail(props); // instantiate a sail
        this.repository = new BigdataSailRepository(sail); // create a Sesame repository

        this.repository.initialize();
    }

    public Repository getRepository() {
        return this.repository;
    }
}
