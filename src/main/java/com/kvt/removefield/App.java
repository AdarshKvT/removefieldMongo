package com.kvt.removefield;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Its KvT Time!!!
 *
 */
public class App {
	public static void main(String[] args) {

		String userId = "3590";
		String jobId = "12345";
		String type = "delete";

		try {
			// condition if input is delete
			if (type == "delete") {
				
				//build a local mongodb connection
				//MongoClient mongoclient = new MongoClient("127.0.0.1", 27017);
				
				// building a connection to mongo cluster using uri
				MongoClientURI uri = new MongoClientURI(
						"mongodb+srv://Test:Test@cluster0-emxhp.mongodb.net/aadarshdb?retryWrites=true&w=majority");
				MongoClient mongoClient = new MongoClient(uri);

				// connecting to mongo database and collection using mongo java driver 3.6.3
				//MongoDatabase database = mongoClient.getDatabase("aadarshdb");
				//MongoCollection<Document> collection = database.getCollection("testCollection");

				// connecting to mongo database and collection
				DB db = mongoClient.getDB("aadarshdb");
				DBCollection coll = db.getCollection("testCollection");

				// condition query
				BasicDBObject query = new BasicDBObject();
				query.put("userId", userId);
			
				BasicDBObject updatequery = new BasicDBObject("jobId", jobId);
				
				//operation need to be perfomed for removing/pulling the field(key value) from the document
				coll.update(query, new BasicDBObject("$pull", updatequery));

				mongoClient.close();

				System.out.println("Operation execution successfull");

			} else {

				System.out.println("entering into else statement");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
