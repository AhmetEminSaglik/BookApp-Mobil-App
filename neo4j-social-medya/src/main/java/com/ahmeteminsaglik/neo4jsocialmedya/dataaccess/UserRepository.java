package com.ahmeteminsaglik.neo4jsocialmedya.dataaccess;

import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import org.neo4j.cypherdsl.core.Match;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByName(String name);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    @Query("MATCH (u:User)-[r:Read]->(b:Book) RETURN u,r,b LIMIT 3")
    List<User> findAllWithBooks();

    @Query("MATCH (u:User)-[r:Read]->(b:Book) " +
            "WHERE ID(u)=$userId " +
            "AND ID(b)=$bookId " +
            "DETACH DELETE r")
    void removeUserReadBookConnection(long userId, long bookId);

    @Query("MATCH (u:User)-[:Follow]->(f:User) " +
            "WHERE ID(u) = $userId " +
            "RETURN f")
    List<User> findAllFollowedUsersByUserId(long userId);

    @Query("MATCH (u:User)<-[:Follow]-(f:User) " +
            "WHERE ID(u) = $userId " +
            "RETURN f")
    List<User> findAllFollowersOfUserId(long userId);

    @Query("MATCH (u1:User)-[f:Follow]->(u2:User)" +
            "WHERE ID(u1) = $userId " +
            "AND ID(u2) = $followedUserId " +
            "DETACH DELETE f")
    void removeUserFollowedRelationShipUser(long userId, long followedUserId);

    @Query("MATCH (u1:User)<-[f:Follow]-(u2:User)" +
            "WHERE ID(u1) = $userId " +
            "AND ID(u2) = $followedUserId " +
            "DETACH DELETE f")
    void removeUserFollowerRelationShipUser(long userId, long followedUserId);

    /*
     * This query return user's friends' most common following friends as recommened user*/
    @Query("MATCH (u:User)-[:Follow]->(fu:User)-[:Follow]->(f:User) " +
            "WHERE ID(u) = $userId AND NOT (u)-[:Follow]->(f) " +
            "WITH f, COUNT(DISTINCT fu) AS num_followers " +
            "ORDER BY num_followers DESC " +
            "RETURN f, num_followers LIMIT 5 ")
    List<User> findCommonUsersByFriends(long userId);

    @Query("MATCH (u:User) " +
            "WHERE NOT ID(u)= $userId " +
            "RETURN u LIMIT 5")
    List<User> findRandomUserToRecommend(long userId);

    @Query("MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (u2:User) WHERE ID(u2) = $friendUserId " +
            "MERGE (u)-[f:Follow]->(u2)")
    void createConnectionFollowFriend(long userId, long friendUserId);
}
