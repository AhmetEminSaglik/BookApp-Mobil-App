package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByName(String name);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    @Query("MATCH (u:User)-[r:Read]->(b:Book) RETURN u,r,b LIMIT 3")
    List<User> findAllWithBooks();

    @Query("MATCH (u:User)-[r:Read]->(b:Book)\n" +
            "WHERE ID(u)=$userId\n" +
            "AND ID(b)=$bookId\n" +
            "DETACH DELETE r")
    void removeUserReadBookConnection(long userId, long bookId);

    @Query("MATCH (u:User)-[:Follow]->(f:User)\n" +
            "WHERE ID(u) = $userId\n" +
            "RETURN f")
    List<User> findAllFollowedUsersByUserId(long userId);

    @Query("MATCH (u:User)<-[:Follow]-(f:User)\n" +
            "WHERE ID(u) = $userId\n" +
            "RETURN f")
    List<User> findAllFollowersOfUserId(long userId);

    @Query("MATCH (u1:User)-[f:Follow]->(u2:User)" +
            "WHERE ID(u1) = $userId\n" +
            "AND ID(u2) = $followedUserId\n" +
            "DETACH DELETE f")
    void removeUserFollowedRelationShipUser(long userId, long followedUserId);

    @Query("MATCH (u1:User)<-[f:Follow]-(u2:User)" +
            "WHERE ID(u1) = $userId\n" +
            "AND ID(u2) = $followedUserId\n" +
            "DETACH DELETE f")
    void removeUserFollowerRelationShipUser(long userId, long followedUserId);

    /*
    * This query return user's friends' most common following friends as recommened user*/
    @Query("MATCH (u:User)-[:Follow]->(fu:User)-[:Follow]->(f:User)\n" +
            "WHERE ID(u) = $userId AND NOT (u)-[:Follow]->(f)\n" +
            "WITH f, COUNT(DISTINCT fu) AS num_followers\n" +
            "ORDER BY num_followers DESC\n" +
            "RETURN f, num_followers LIMIT 5\n")
    List<User> findCommonUsersByFriends(long userId);
}
