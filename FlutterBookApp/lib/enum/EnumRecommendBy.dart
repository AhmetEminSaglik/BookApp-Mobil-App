enum EnumRecommendBy {
  BY_FRIEND(name: "BY FRIEND"),
  BESTSELLERS(name: "BESTSELLERS"),
  HIGHEST_RATING(name: "HIGHEST RATING"),
  MOST_READ(name: "MOST READ"),
  BY_RANDOM(name: "BY RANDOM");

  final String name;

  const EnumRecommendBy({required this.name});
}
