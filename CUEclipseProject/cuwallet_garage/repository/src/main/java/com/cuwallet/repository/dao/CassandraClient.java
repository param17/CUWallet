package com.cuwallet.repository.dao;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;

@Repository
public class CassandraClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraClient.class);

	@Value("#{cassandraProperties['hosts']}")
	public String hosts;

	@Value("#{cassandraProperties['username']}")
	public String userName;
	
	@Value("#{cassandraProperties['password']}")
	public String password;
	
/*	@Value("#{cassandraProperties['authentication_enabled']}")
	public boolean authenticationEnabled;*/
	
	/*private final static String hosts = "127.0.0.1:9042";

	private final static String userName = "cassandra";
	
	private final static String password = "cassandra";*/
	
	private final static boolean authenticationEnabled = false;
	
	private Cluster cluster;

	private Session session;
	
	public Session getSession() {
		return session;
	}

	@PostConstruct
	public void initialize() {
		QueryOptions queryOptions = new QueryOptions();
        queryOptions.setConsistencyLevel(ConsistencyLevel.ALL);
        Builder builder =Cluster.builder()
                .addContactPointsWithPorts(parseSocketAddresses(hosts))
                .withQueryOptions(queryOptions)
                .withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE);
       

		if(authenticationEnabled){
			builder.withCredentials(userName, password);
		}
		
		cluster = builder.build();
		Metadata metadata = cluster.getMetadata();
		LOGGER.info("Connected to cluster:%s\n", metadata.getClusterName());
		for (Host host : metadata.getAllHosts()) {
			LOGGER.info("Datatacenter: {}; Host: {}; Rack: {}",
					new Object[] { host.getDatacenter(), host.getAddress(), host.getRack() });
		}
		session = cluster.connect();
	}

	private static Collection<InetSocketAddress> parseSocketAddresses(String address){
		Collection<InetSocketAddress> contactPoints = new HashSet<InetSocketAddress>();
		String[] addressStrings = address.split(",");
		for(int i=0;i<addressStrings.length;i++)
			contactPoints.add(parseSocketAddress(addressStrings[i]));
		return contactPoints;
	}
	
	private static InetSocketAddress parseSocketAddress(String address) {
		String[] parsedSocketAddress = address.split(":");
		String hostName = parsedSocketAddress[0];
		int port = Integer.parseInt(parsedSocketAddress[1]);
		return new InetSocketAddress(hostName,port);
	}

	@PreDestroy
	public void destroy() {
		session.close();
		cluster.close();
	}

	
}
