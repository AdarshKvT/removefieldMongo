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
		// query.containsKey(jobId);

		try {
			// condition if input is delete
			if (type == "delete") {

				// building a connection to mongo cluster using uri
				MongoClientURI uri = new MongoClientURI(
						"mongodb+srv://Test:Test@cluster0-emxhp.mongodb.net/aadarshdb?retryWrites=true&w=majority");
				MongoClient mongoClient = new MongoClient(uri);

				// connecting to mongo database and collection 
				//MongoDatabase database = mongoClient.getDatabase("aadarshdb");
				//MongoCollection<Document> collection = database.getCollection("testCollection");

				// connecting to mongo database and collection
				DB db = mongoClient.getDB("aadarshdb");
				DBCollection coll = db.getCollection("testCollection");

				// condition query
				BasicDBObject query = new BasicDBObject();
				query.put("userId", userId);

				BasicDBObject updatequery = new BasicDBObject("jobId", jobId);
				coll.update(query, new BasicDBObject("$pull", updatequery));

				mongoClient.close();

				System.out.println("Operation execution successfull");

			} else {

				System.out.println("entering into if statement where type equals to something");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
