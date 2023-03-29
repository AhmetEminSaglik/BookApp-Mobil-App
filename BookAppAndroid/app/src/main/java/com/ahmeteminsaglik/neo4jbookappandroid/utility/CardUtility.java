package com.ahmeteminsaglik.neo4jbookappandroid.utility;

import androidx.annotation.DrawableRes;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumRecommendReason;

public class CardUtility {
    public static int getCardBackgroudColorByRecommendType(String reason) {

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
}
