package ex03.startup;

import ex03.connector.http.HttpConnector;

/*
 * the main class in the Bootstrap class instantiates the HttpConnector class and calls
 * its start method.
 */
public class Bootstrap {
	public static void main(String args[]){
	    HttpConnector connector = new HttpConnector();
	    connector.start();
	}
}
