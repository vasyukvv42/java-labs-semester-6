package edu.kpi.hotel.model.dao.morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.ValidationExtension;

public class MongoConnection {
    private static MongoConnection ourInstance = new MongoConnection();

    public static MongoConnection getInstance() {
        return ourInstance;
    }

    private MongoConnection() {
        final var env = System.getenv();
        final var mongoUri = env.getOrDefault("MONGODB_URI", "mongodb://localhost:27017");
        mongo = new MongoClient(new MongoClientURI(mongoUri));

        morphia = new Morphia();
        morphia.mapPackage("edu.kpi.hotel.model.entity");
        new ValidationExtension(morphia);

        final var dbName = env.getOrDefault("DBNAME", "hotel");
        datastore = morphia.createDatastore(mongo, dbName);
        datastore.ensureIndexes();
    }

    private MongoClient mongo;
    private Morphia morphia;
    private Datastore datastore;

    public MongoClient getMongo() {
        return mongo;
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
