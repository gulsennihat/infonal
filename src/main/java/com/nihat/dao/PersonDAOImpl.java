package com.nihat.dao;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.nihat.model.Person;

@Repository
public class PersonDAOImpl implements IPersonDAO {

	// log4j implementation
	private static final Logger logger = Logger.getLogger(PersonDAOImpl.class);

	// mongoDB database template
	@Autowired()
	private MongoTemplate mongoTemplate;

	// mongoDB database collection for personal information
	public static final String COLLECTION_NAME = "person";

	// adding new person to db
	@Override
	public Person addPerson(Person person) {

		if (!mongoTemplate.collectionExists(Person.class)) {
			mongoTemplate.createCollection(Person.class);
		}
		// generating random user id
		person.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(person, COLLECTION_NAME);
		// logging
		logger.info("person added");
		return person;

	}

	// getting all person list
	@Override
	public List<Person> listPerson() {
		return mongoTemplate.findAll(Person.class, COLLECTION_NAME);
	}

	// delete person process
	@Override
	public void deletePerson(String id) {

		Person prs = findById(id);
		mongoTemplate.remove(prs, COLLECTION_NAME);
		logger.info("person deleted");

	}

	// update personal information
	@Override
	public Person updatePerson(String id, String name, String surname, String telNumber) {

		DB db = mongoTemplate.getDb();
		DBCollection clc = db.getCollection(COLLECTION_NAME);
		BasicDBObject dbo = new BasicDBObject();
		BasicDBObject query = new BasicDBObject("_id", id);
		dbo.put("name", name);
		dbo.put("surname", surname);
		dbo.put("telNumber", telNumber);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("person", dbo);
		clc.update(query, (DBObject) updateObj.get("person"), false, false);
		logger.info("person updated");
		return null;

	}

	// find user by user id. Used for delete person
	@Override
	public Person findById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, Person.class, COLLECTION_NAME);
	}

}
