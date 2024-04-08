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

    @Query("MATCH (u:User)-[r:READ]->(b:Book) RETURN u,r,b LIMIT 2")
    List<User> findAllWithBooks();

    @Query("MATCH (u:User)-[r:READ]->(b:Book) " +
            "WHERE ID(u)=$userId " +
            "AND ID(b)=$bookId " +
            "DETACH DELETE r")
    void removeUserReadBookConnection(long userId, long bookId);

    @Query("MATCH (u:User)-[:FOLLOW]->(f:User) " +
            "WHERE ID(u) = $userId " +
            "RETURN f")
    List<User> findAllfollowingUsersByUserId(long userId);

    @Query("MATCH (u:User)<-[:FOLLOW]-(f:User) " +
            "WHERE ID(u) = $userId " +
            "RETURN f")
    List<User> findAllFollowersOfUserId(long userId);

    @Query("MATCH (u1:User)-[f:FOLLOW]->(u2:User)" +
            "WHERE ID(u1) = $userId " +
            "AND ID(u2) = $followingUserId " +
            "DETACH DELETE f")
    void removeUserfollowingRelationshipUser(long userId, long followingUserId);

    @Query("MATCH (u1:User)<-[f:FOLLOW]-(u2:User)" +
            "WHERE ID(u1) = $userId " +
            "AND ID(u2) = $followingUserId " +
            "DETACH DELETE f")
    void removeUserFollowerRelationshipUser(long userId, long followingUserId);

    /*
     * This query return user's friends' most common following friends as recommened user*/
    @Query("MATCH (u:User)-[:FOLLOW]->(fu:User)-[:FOLLOW]->(f:User) " +
            "WHERE ID(u) = $userId " +
            "AND NOT (u)-[:FOLLOW]->(f) " +
            "AND NOT ID(u)=ID(f)" +
            "WITH f, COUNT(DISTINCT fu) AS num_followers " +
            "ORDER BY num_followers DESC " +
            "RETURN f LIMIT 2")
    List<User> findCommonUsersByFriends(long userId);

    @Query("MATCH (u:User) " +
            "MATCH (u2:User)" +
            "WHERE ID(u)= $userId " +
            "AND NOT ID(u2)= $userId " +
            "AND NOT (u)-[:FOLLOW]->(u2) " +
            "RETURN u2 LIMIT 2 ")
    List<User> findRandomUserToRecommend(long userId);

    @Query("MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (u2:User) WHERE ID(u2) = $friendUserId " +
            "MERGE (u)-[f:FOLLOW]->(u2)")
    void createConnectionFollowFriend(long userId, long friendUserId);

    @Query("MATCH (u:User)<-[:FOLLOW]-(f:User)\nWITH u, COUNT(f) AS followers\nSET u.followers = followers\nWITH u\nMATCH (u)-[:FOLLOW]->(f:User)\nWITH u, COUNT(f) AS following\nSET u.following = following\n")
    void fixUserData();


    @Query("MATCH (u:User) WHERE ID(u) = $userId " +
            "MATCH (b:Book) WHERE ID(b) = $bookId " +
            "MERGE (u)-[r:READ{rate:$rate}]->(b)")
        /*@Query("CREATE (u:User)-[:READ{rate:$rate}]->(b:Book)" +
            "WHERE ID(u) = $userId" +
            "AND ID(b) = $bookId")*/
    void setConnectionUserReadBook(long userId, long bookId, int rate);
}
