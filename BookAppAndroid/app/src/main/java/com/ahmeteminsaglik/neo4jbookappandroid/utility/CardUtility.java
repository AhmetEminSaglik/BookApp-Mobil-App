package com.ahmeteminsaglik.neo4jbookappandroid.utility;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumRecommendReason;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumRelationship;

public class CardUtility {
    public static int getCardBackgroudColorByRecommendTypeForUser(String reason) {
        return R.color.card_constraintColor_0_default_light_blue;
    }
    public static int getCardBackgroudColorByRecommendTypeForBook(String reason) {

        if (reason.equals(EnumRecommendReason.BEST_SELLER.getName())) {
            return R.color.card_constraintColor_1_light_red;
        }
        if (reason.equals(EnumRecommendReason.FRIEND.getName())) {
            return R.color.card_constraintColor_2_light_green;
        }
        if (reason.equals(EnumRecommendReason.HIGH_POINT.getName())) {
            return R.color.card_constraintColor_3_light_purple;
        }
        return R.color.card_constraintColor_0_default_light_blue;
    }

    public static int getCardBackgroundColorByRelationshipType(String relationship) {
        if (relationship.equals(EnumRelationship.FOLLOWED.getName())) {
            return R.color.card_constraintColor_2_light_green;
        }
        if (relationship.equals(EnumRelationship.FOLLOWER.getName())) {
            return R.color.card_constraintColor_4_light_orange;
        }

        return R.color.card_constraintColor_0_default_light_blue;
    }
}
